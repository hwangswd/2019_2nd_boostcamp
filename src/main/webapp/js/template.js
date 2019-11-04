/**
 * 페이지에서 사용되는 템플릿을 모아둔 자바스크립트 파일
 * 2019-08-07 Handlebars를 적용하면서 jsp 페이지 하단으로 템플릿을 이동
 * 2019-08-14 reserve 페이지를 위한 부분 추가
 * @author 황성욱
 * @since 2019-08-01
 */

let handlebars = {
	category: undefined,
	promotion: undefined,
	product: undefined,
	
	titleImage: undefined,
	comment: undefined,
	
	rsvTitleImage: undefined,
	rsvDetail: undefined,
	productPrice: undefined,
	reqParam: undefined,
	
	precompile(bindTemplate, id, data) {
		if (bindTemplate === undefined) {
			let template = document.querySelector(id).innerText;
			bindTemplate = Handlebars.compile(template);
		}
		return bindTemplate(data);
	}
}

let template = {
	main: {
		category(data) {
			return handlebars.precompile(handlebars.category, "#category", data);
		},
		
		promotion(data) {
			return handlebars.precompile(handlebars.promotion, "#promotion", data);
		},
		
		product(data) {
			return handlebars.precompile(handlebars.product, "#product", data);
		}
	},

	detail: {
		titleImage(data) {
			return handlebars.precompile(handlebars.titleImage, "#titleImage", data);
		},
	
		comment(data) {
			return handlebars.precompile(handlebars.comment, "#comment", data);
		}
	},
	
	review: {
		comment(data) {
			return template.detail.comment(data);
		}
	},
	
	reserve : {
		titleImage(data) {
			return handlebars.precompile(handlebars.rsvTitleImage, "#titleImage", data);
		},
		
		detail(data) {
			return handlebars.precompile(handlebars.rsvDetail, "#detail", data);
		},
		
		productPrice(data) {
			return handlebars.precompile(handlebars.productPrice, "#productPrice", data);
		},
		
		reqParam(data) {
			return handlebars.precompile(handlebars.reqParam, "#hiddenForm", data);
		}
	}
}
