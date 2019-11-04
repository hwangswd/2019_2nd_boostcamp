


let myreservation = {
	init() {
		
	}
	
//	init : function() {
//		let cardSection = document.querySelector(".list_cards");
//		let countSection = document.querySelector(".summary_board", "cancel");
//		if(cardSection !== null) {
//			myreservation.initCancelButtonEvent(cardSection, countSection);
//		}
//	}
//	/**
//	 * 예약 정보 카드를 옮기는 함수
//	 */
//	moveTargetCard : function(parentNode, event) {
//		let card = event.target.closest(".card_item")
//		parentNode.appendChild(card);
//	},
//	
//	/**
//	 * 상단의 이용예정/이용완료/취소.환불 영역의 숫자를 수정하는 함수
//	 */
//	changeCount : function(countSection, eventName) {
//		if(eventName === "cancel") {
//			countSection.querySelector(".count_confirm").innerHTML--;
//			countSection.querySelector(".count_cancel").innerHTML++;
//		}
//	},
//	
//	/**
//	 * 취소 버튼을 제거하는 함수 
//	 */
//	removeCancelButton : function(event) {
//		let cancelButton = event.target.closest(".booking_cancel");
//		cancelButton.style.display = "none";
//	},
//	/**
//	 * 최초 버튼에 이벤트를 추가하는 함수
//	 */
//	initCancelButtonEvent : function(cardSection, countSection) {
//		let confirmedSection = cardSection.querySelector(".confirmed");
//		let canceledSection = cardSection.querySelector(".cancel");
//		
//		confirmedSection.addEventListener("click", function(event) {
//			if(!event.target.closest(".button_cancel")) {
//				return;
//			}		
//			if(!confirm("취소하시겠습니까?")) {
//				return;
//			}
//			let reservationId = event.target.closest(".card_item").dataset.id;
//			let url = "api/reservations/" + reservationId;
//			common.sendAjaxLoadRequest("PUT", url, function() {
//				myreservation.moveTargetCard(canceledSection, event);
//				myreservation.removeCancelButton(event);
//				myreservation.changeCount(countSection, "cancel");
//			});
//		});
//		
//	},
	
	
	
//	//예매 취소 버튼 이벤트 등록 
//	addButtonClickListener : function(cardListSection) {
//		let confirmedSection = cardListSection.querySelector(".confirmed");
//		let canceledSection = cardListSection.querySelector(".cancel");
//
//		confirmedSection.addEventListener("click", function(event) {
//			if(!event.target.closest(".booking_cancel")) {
//				return;
//			}
//
//			if(!confirm("예약을 취소 하시겠습니까?")) {
//				return;
//			}
//
//			let reservationId = event.target.closest(".card_item").dataset.id;
//			ajax.put("api/reservations/"+reservationId);
//		});
//	},
//
//	/*
//	 * 나의 예약 페이지의  최초 DOM 로드시 init 함수 
//	 */
//	init : function () {
//		let cardListSection = document.querySelector(".list_cards");
//		myReservation.addButtonClickListener(cardListSection);
//	}

}

document.addEventListener("DOMContentLoaded", () => {
	myreservation.init();
});