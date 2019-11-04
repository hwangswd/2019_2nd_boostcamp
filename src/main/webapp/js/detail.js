/**
 * product를 클릭하면 이동하는 detail 페이지를 draw하고
 * 발생하는 이벤트를 처리
 * @author 황성욱
 * @since 2019-07-31
 */

const MIN_TITLE_IMAGE_COUNT = 1;
const MAX_TITLE_IMAGE_COUNT = 2;
const COMMENT_LIMIT = 3;

let detail = {
	init() {
		let displayInfoId = document.querySelector(".ct.main").dataset.displayId;
		detail.getDisplayInfo(displayInfoId);
		
		document.querySelector(".bk_more._open").addEventListener("click", detail.readMore);
		document.querySelector(".bk_more._close").addEventListener("click", detail.readLess);
		document.querySelector(".section_btn").addEventListener("click", detail.reserve);
		document.querySelector(".info_tab_lst").addEventListener("click", detail.changeTab);
	},

	getDisplayInfo(displayInfoId) {
		ajaxRequest.sendRequest("GET", "api/products/"+displayInfoId, detail.drawDisplayInfo);
	},

	drawDisplayInfo(xhr) {
		let res = xhr.response;
		detail.drawTitleImage(res.productImages);
		detail.drawProductContent(res.displayInfo.productContent);
		detail.drawEventInfo(res.displayInfo.productEvent);
		detail.drawAverageScore(res.averageScore);
		detail.drawComments(res.comments);
		detail.drawDetailInfo(res.displayInfo);
		detail.drawLocationInfo(res.displayInfo);
	},

	drawTitleImage(productImages) {
		let titleImageSection = document.querySelector(".visual_img.detail_swipe");
		titleImageSection.innerHTML += template.detail.titleImage(productImages[0]);
		
		if (productImages.length === 1) {
			// 이미지가 1장일 경우 왼쪽 버튼을 숨김
			document.querySelector(".spr_book2.ico_arr6_lt").classList.add("off");
		} else {
			titleImageSection.innerHTML += template.detail.titleImage(productImages[1]);
			document.querySelector(".num.off").lastChild.innerText = MAX_TITLE_IMAGE_COUNT;
			detail.addSlideEvents(productImages);
		}
	},
	
	addSlideEvents(images) {
		document.querySelector(".btn_nxt").addEventListener("click", detail.slideImage);
		document.querySelector(".btn_prev").addEventListener("click", detail.slideImage);
	},

	isDone : true, // 슬라이드 이동 중 클릭 이벤트를 받지 않기 위한 flag

	slideImage(click) {
		if (detail.isDone) {
			detail.isDone = false;
			
			let titleImageSection = document.querySelector(".visual_img.detail_swipe");
			let firstImage = titleImageSection.firstElementChild;
			let nextImage = firstImage.nextElementSibling;
			let direction = click.target.closest("a").className;
			let width = document.querySelector("#container").clientWidth;
			let imageNum = parseInt(document.querySelector(".figure_pagination .num").innerText);
			
			if (direction === "btn_nxt") {
				titleImageSection.insertAdjacentElement('beforeEnd', firstImage.cloneNode(true));
				titleImageSection.style.transitionDuration = "0.3s";
				titleImageSection.style.transform = "translateX(" + (-width) + "px)";
				setTimeout(() => {
					titleImageSection.removeChild(firstImage);
					titleImageSection.style.transitionDuration = "0s";
					titleImageSection.style.transform = "translateX(0)";
					detail.isDone = true;
					imageNum = imageNum % MAX_TITLE_IMAGE_COUNT + 1;
					document.querySelector(".figure_pagination .num").innerText = imageNum;
				}, 300);
			} else {
				titleImageSection.insertAdjacentElement('afterBegin', nextImage.cloneNode(true));
				titleImageSection.style.transitionDuration = "0s";
				titleImageSection.style.transform = "translateX(" + (-width) + "px)";
				setTimeout(() => {
					titleImageSection.style.transitionDuration = "0.3s";
					titleImageSection.style.transform = "translateX(0)";
				}, 1);
				setTimeout(() => {
					titleImageSection.removeChild(nextImage);
					detail.isDone = true;
					imageNum = imageNum % MAX_TITLE_IMAGE_COUNT + 1;
					document.querySelector(".figure_pagination .num").innerText = imageNum;
				}, 299);
			}
		}
	},

	drawProductContent(productContent) {
		document.querySelector(".store_details .dsc").innerText += productContent;
	},

	readMore() {
		document.querySelector(".store_details.close3").classList.remove("close3");
		document.querySelector(".bk_more._open").style.display = "none";
		document.querySelector(".bk_more._close").style.display = "";
	},

	readLess() {
		document.querySelector(".store_details").classList.add("close3");
		document.querySelector(".bk_more._open").style.display = "";
		document.querySelector(".bk_more._close").style.display = "none";
	},

	drawEventInfo(productEvent) {
		document.querySelector(".event_info .in_dsc").innerHTML += productEvent;
	},

	reserve() {
		let displayId = document.querySelector(".ct.main").dataset.displayId;
		location.href = "/reserve?id="+displayId;
	},

	drawAverageScore(averageScore) {
		averageScore = averageScore.toFixed(1);
		document.querySelector(".text_value").firstElementChild.innerText = averageScore;
		document.querySelector(".graph_value").style.width = averageScore * 100 / 5.0 + "%";
	},

	drawComments(comments) {
		if(comments.length === 0) {
			return;
		}
		
		let commentsHtml = comments.slice(0, COMMENT_LIMIT).reduce((innerHtml, comment) => {
			comment.score = comment.score.toFixed(1);
			return innerHtml + template.detail.comment(comment);
		}, "");
		
		document.querySelector(".list_short_review").innerHTML += commentsHtml;
		document.querySelector(".green").innerText = comments.length + "건";
	},

	drawDetailInfo(displayInfo) {
		document.querySelector(".detail_info_lst .in_dsc").innerText += displayInfo.productContent;
	},

	drawLocationInfo(displayInfo) {
		document.querySelector(".detail_location .store_name")
			.innerText = displayInfo.categoryName + " " + displayInfo.productDescription;
		document.querySelector(".detail_location .store_addr_bold")
			.innerText = displayInfo.placeStreet;
		document.querySelector(".detail_location .addr_old_detail")
			.innerText = displayInfo.placeLot;
		document.querySelector(".detail_location .addr_detail")
			.innerText = displayInfo.placeName;
		document.querySelector(".store_tel")
			.innerText = displayInfo.telephone;
		document.querySelector(".store_tel").href="tel:" + displayInfo.telephone;
	},

	changeTab(clicked) {
		if (clicked.target.tagName === 'A' || clicked.target.tagName === 'SPAN') {
			let activeTab = document.querySelector(".anchor.active").closest("li");
			let clickedTab = clicked.target.closest("li");

			if(activeTab.dataset.tabName === clickedTab.dataset.tabName) {
				return;
			} else {
				activeTab.firstElementChild.classList.remove("active");
				clickedTab.firstElementChild.classList.add("active");
				
				detail.changeInfo(activeTab.dataset.tabName, clickedTab.dataset.tabName);
			}
		}
	},

	changeInfo(active, clicked) {
		document.querySelectorAll("[data-info]").forEach((detailInfo) => {
			if (detailInfo.classList.contains("hide")) {
				if (detailInfo.dataset.info === clicked) {
					detailInfo.classList.remove("hide");
				}
			} else {
				detailInfo.classList.add("hide");
			}
		})
	}
}

document.addEventListener("DOMContentLoaded", () => {
	detail.init();
});
