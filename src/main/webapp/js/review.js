/**
 * 한줄평 더보기를 클릭하면 이동하는 review 페이지를 draw
 * @author 황성욱
 * @since 2019-08-02
 */

let review = {
	init() {
		let displayInfoId = document.querySelector(".ct").dataset.displayId;
		review.getComments(displayInfoId);
	},
	
	getComments(displayInfoId) {
		ajaxRequest.sendRequest("GET", "api/products/"+displayInfoId, review.drawReview);
	},
	
	drawReview(xhr) {
		let res = xhr.response;
		review.drawTitle(res.displayInfo)
		review.drawAverageScore(res.averageScore);
		review.drawComments(res.comments);
	},
	
	drawTitle(displayInfo) {
		let productDesc = displayInfo.productDescription;
		document.querySelector(".top_title .title").innerText = productDesc;
		titleSection.href = "https://search.naver.com/search.naver?query=" + productDesc;
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
		
		let commentsHtml = comments.reduce((innerHtml, comment) => {
			comment.score = comment.score.toFixed(1);
			return innerHtml + template.review.comment(comment);
		}, "")
		
		document.querySelector(".list_short_review").innerHTML += commentsHtml;
		document.querySelector(".green").innerText = comments.length + "건";
	}
}

document.addEventListener("DOMContentLoaded", () => {
	review.init();
});
