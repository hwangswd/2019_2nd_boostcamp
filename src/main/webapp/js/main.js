/**
 * 메인페이지를 로드
 * @author 황성욱
 * @since 2019-07-24
 */

let section = {
	promotion: '', // 애니메이션이 수행되는 프로모션 영역
	productLeft: '', // 메인페이지 상품리스트 영역의 왼쪽 부분
	productRight: '', // 메인페이지 상품리스트 영역의 오른쪽 부분	
}

let main = {
	init() {
		document.querySelector(".event_tab_lst").addEventListener("click", main.changeTab);
		document.querySelector(".btn").addEventListener("click", main.readMore);
		section.promotion = document.querySelector(".visual_img");
		section.productLeft = document.querySelector(".wrap_event_box").firstElementChild;
		section.productRight = document.querySelector('.wrap_event_box').firstElementChild.nextElementSibling;
		promotion.get();
		promotion.run();
		category.get();
		product.get();
	},

	/**
	 * 카테고리 탭을 선택하면 동작하는 함수
	 * 선택한 카테고리 탭의 데이터를 가져온다
	 */
	changeTab(clicked) {
		if (clicked.target.tagName !== 'A' && clicked.target.tagName !== 'SPAN') {
			return;
		}
		document.querySelector(".anchor.active").classList.remove("active");
		clicked.target.closest(".anchor").classList.add("active");
		main.hideReadMoreBtn(false);

		let clickedCategoryId = clicked.target.closest(".item").dataset.category;
		product.reset();
		product.get(clickedCategoryId);
	},

	hideReadMoreBtn(isHide) {
		if (isHide) {
			document.querySelector(".more .btn").style.display = "none";
		} else {
			document.querySelector(".more .btn").style.display = "block";
		}
	},
	
	readMore() {
		let categoryId = document.querySelector(".anchor.active").closest(".item").dataset.category;
		product.get(categoryId, product.getIndexOfLastProduct());
	}
}

let promotion = {
	get() {
		ajaxRequest.sendRequest("GET", "api/promotions", promotion.draw);
	},
	
	draw(xhr) {
		let promotions = xhr.response;
		promotions.items.forEach((promotion) => {
			section.promotion.innerHTML += template.main.promotion(promotion);
		});
	},
	
	run(numOfPromotions) {
		let idx = 0;
		setInterval(() => {
			let DOCUMENT_WIDTH = document.querySelector("#container").clientWidth; // 화면의 width
			section.promotion.style.transitionDuration = "1.5s";
			section.promotion.style.transform = "translateX(" + (-DOCUMENT_WIDTH * idx) + "px)";
			idx++;
			if(idx > numOfPromotions) {
				section.promotion.style.transform = "translateX(0)";
				idx = 0;
			}
		}, 2000)
	}
}

let category = {
	get() {
		ajaxRequest.sendRequest("GET", "api/categories", category.draw);
	},
	
	draw(xhr) {
		let categories = xhr.response;
		let categoryTab = document.querySelector(".event_tab_lst");
		categories.items.forEach((category) => {
			categoryTab.innerHTML += template.main.category(category);
		});
	}
}

let product = {
	get(categoryId, start) {
		let url = "api/products";
		if (categoryId !== undefined) {
			url += "?categoryId=" + categoryId;
	
			if(start !== undefined) {
				url += "&" + "start=" + start;
			}
		} else {
			if (start !== undefined) {
				url += "?start=" + start;
			}
		}
		ajaxRequest.sendRequest("GET", url, product.draw);
	},
	
	draw(xhr) {
		let productList = xhr.response;
		let productCountText = document.querySelector(".pink")
		productCountText.innerText = productList.totalCount;
		product.append(productList);
	},
	
	append(productList) {
		if (productList.items.length > 0) {
			let itemIndex = 0
			productList.items.forEach((product) => {
				if (itemIndex % 2 === 0) {
					section.productLeft.innerHTML += template.main.product(product);
				} else {
					section.productRight.innerHTML += template.main.product(product);
				}
				itemIndex++;
			})
		}
		
		if (productList.totalCount === product.getIndexOfLastProduct()) {
			main.hideReadMoreBtn(true);
		}
	},
	
	/**
	 * 클릭한 카테고리의 product를 가져오기 위해 현재 product를 모두 없앤다
	 */
	reset() {
		section.productLeft.innerHTML = "";
		section.productRight.innerHTML = "";
	},
	
	getIndexOfLastProduct() {
		return (section.productLeft.childElementCount + section.productRight.childElementCount);
	}
}

document.addEventListener("DOMContentLoaded", () => {
	main.init();
});
