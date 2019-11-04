/**
 * 예약페이지를 로드
 * @author 황성욱
 * @since 2019-08-13
 */

let reserve = {
	init() {
		let displayInfoId = document.querySelector(".ct").dataset.displayId
		reserve.getDisplayInfo(displayInfoId);
	},
	
	getDisplayInfo(displayInfoId) {
		ajaxRequest.sendRequest("GET", "api/products/"+displayInfoId, reserve.drawPage);
	},

	drawPage(xhr) {
		let res = xhr.response;
		reserve.drawTitleImage(res.productImages);
		reserve.drawContent(res.displayInfo);
		reserve.drawPrices(res.productPrices);
		
		reserve.drawBookingForm();
		document.querySelector(".ct").dataset.productId = res.displayInfo.productId;
	},

	drawTitleImage(productImages) {
		productImages.forEach(function(image) {
			if (image.type === "ma") {
				document.querySelector(".group_visual").innerHTML +=
					template.reserve.titleImage(image);
			}
		})
	},
	
	drawContent(displayInfo) {
		document.querySelector(".section_store_details").innerHTML +=
			template.reserve.detail(displayInfo)
	},
	
	drawPrices(prices) {
		let pricesHtml = prices.reduce(function(innerHtml, price) {
			let data = {
					"priceId": price.productPriceId,
					"price": price.price,
					"discountRate": price.discountRate,
					"discountPrice": price.price * (100 - price.discountRate) / 100,
					"priceTypeName": price.priceTypeName,
					"priceType": reserve.priceTypes[price.priceTypeName],
			}
			return innerHtml + template.reserve.productPrice(data);
		}, "");
		
		document.querySelector(".ticket_body").innerHTML = pricesHtml;
		
		document.querySelectorAll("._ticket_count_control").forEach(function(btn) {
			btn.addEventListener("click", reserve.updateBooking);
		});
	},

	priceTypes : {
		'A' : "성인(만 19~64세)",
		'Y' : "청소년(만 13~18세)",
		'B' : "어린이(만 4~12세)",
		'S' : "셋트",
		'D' : "장애인",
		'C' : "지역주민",
		'E' : "얼리버드",
		'V' : "VIP",
		'R' : "R석"
	},

	updateBooking(clicked) {
		if (clicked.target.classList.contains("disabled") ||
				clicked.target.classList.contains("count_control_input")) {
			return;
		}
		
		let count = reserve.updateCount(clicked);
		reserve.updatePrice(clicked, count);
	},
	
	updateCount(clicked) {
		let countWrapper = clicked.target.closest("._ticket_count_control");
		let count = countWrapper.firstElementChild.nextElementSibling;
		let totalCount = document.querySelector("#totalCount");
		
		if (clicked.target.classList.contains("ico_minus3")) {
			count.value = Number(count.value) - 1;
			totalCount.innerText = Number(totalCount.innerText) - 1;
			if (Number(count.value) === 0) {
				reserve.makeBtnDisabled(countWrapper.firstElementChild, true);
			}
		} else {
			if (Number(count.value) === 0) {
				reserve.makeBtnDisabled(countWrapper.firstElementChild, false);
			}
			count.value = Number(count.value) + 1;
			totalCount.innerText = Number(totalCount.innerText) + 1;
		}
		
		return count;
	},
	
	makeBtnDisabled(target, bool) {
		if (bool) {
			target.classList.add("disabled");
			target.nextElementSibling.classList.add("disabled");
		} else {
			target.classList.remove("disabled");
			target.nextElementSibling.classList.remove("disabled");
		}
	},
	
	updatePrice(clicked, count) {
		let priceSection = clicked.target.closest(".count_control").querySelector(".total_price");
		
		let totalPrice = Number(priceSection.parentElement.dataset.dcPrice * count.value);
		priceSection.innerText = totalPrice;
		
		if (totalPrice === 0) {
			priceSection.parentElement.classList.remove("on_color");
		} else if (totalPrice > 0) {
			priceSection.parentElement.classList.add("on_color");
		}
	},
	
	drawBookingForm() {
		document.querySelectorAll(".btn_agreement").forEach(function(btn) {
			btn.addEventListener("click", reserve.toggleAgreement);
		});
		document.querySelector(".chk_txt_label").addEventListener("click", reserve.checkAgreement);
		document.querySelector(".bk_btn_wrap").addEventListener("click", reserve.checkForm);
	},
	
	toggleAgreement(clicked) {
		let agreementSection = clicked.target.closest(".agreement");
		if (agreementSection.classList.contains("open")) {
			agreementSection.classList.remove("open");
		} else {
			agreementSection.classList.add("open");
		}
	},
	
	checkAgreement() {
		let makeReservationBtn = document.querySelector(".bk_btn_wrap");
		if (makeReservationBtn.classList.contains("disable")) {
			makeReservationBtn.classList.remove("disable");
		} else {
			makeReservationBtn.classList.add("disable");
		}
	},
	
	checkForm() {
		if (document.querySelector(".bk_btn_wrap").classList.contains("disable")) {
			return;
		}
		
		let nameSection = document.querySelector("#name");
		let telSection = document.querySelector("#tel");
		let emailSection = document.querySelector("#email");
		
		let nameValid = (/[[a-zA-Z가-힣]{1,17}/).test(nameSection.value);
		let telValid = (/01[01789]-\d{3,4}-\d{4}/).test(telSection.value);
		let emailValid = (/^[\w]\w+\@\w+\.\w+\.*\w*/).test(emailSection.value);
		
		nameSection.parentElement.classList.add("name_wrap");
		telSection.parentElement.classList.add("tel_wrap");
		emailSection.parentElement.classList.add("email_wrap");
		
		if (nameValid === false) {
			nameSection.focus();
			nameSection.parentElement.classList.remove("name_wrap");
		}
		if (telValid === false) {
			telSection.focus();
			telSection.parentElement.classList.remove("tel_wrap");
		}
		if (emailValid === false) {
			emailSection.focus();
			emailSection.parentElement.classList.remove("email_wrap");
		}
		
		if (nameValid && telValid && emailValid) {
			reserve.makeReservationParam(nameSection.value, telSection.value, emailSection.value);
			document.querySelector(".form_horizontal").submit();
		}
	},

	makeReservationParam(name, tel, email) {
		if (Number(document.querySelector("#totalCount").innerText) === 0) {
			alert("티켓 수량을 선택해주세요!");
			return;
		}
		
		let paramHtml = "";
		let data = {};
		
		data.name = "displayInfoId";
		data.value = document.querySelector(".ct").dataset.displayId;
		paramHtml += template.reserve.reqParam(data);
		
		data.name = "productId";
		data.value = document.querySelector(".ct").dataset.productId;
		paramHtml += template.reserve.reqParam(data);
		
		data.name = "reservationName";
		data.value = name;
		paramHtml += template.reserve.reqParam(data);
		
		data.name = "reservationTelephone";
		data.value = tel;
		paramHtml += template.reserve.reqParam(data);
		
		data.name = "reservationEmail";
		data.value = email;
		paramHtml += template.reserve.reqParam(data);
		
		data.name = "reservationYearMonthDay";
		data.value = document.querySelector(".last").dataset.reservationDate;
		paramHtml += template.reserve.reqParam(data);
		
		document.querySelectorAll(".qty").forEach(function(price, index) {
			let count = Number(price.querySelector(".count_control_input").value);
			if (count !== 0) {
				data.name = "prices[" + index + "].count";
				data.value = count;
				paramHtml += template.reserve.reqParam(data);
				
				data.name = "prices[" + index + "].productPriceId";
				data.value = price.dataset.priceId;
				paramHtml += template.reserve.reqParam(data);
			}
		});
		
		document.querySelector(".form_horizontal").innerHTML += paramHtml;
		debugger;
	}
}

document.addEventListener("DOMContentLoaded", () => {
	reserve.init();
});
