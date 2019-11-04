/**
 * 내 예약페이지를 보여주기 위해 이메일로 로그인
 * @author 황성욱
 * @since 2019-08-18
 */

let bookinglogin = {
	init() {
		bookinglogin.addLoginEvent();
	},
	
	addLoginEvent() {
		document.querySelector(".login_btn.confirm").addEventListener("click", function() {
			bookinglogin.checkEmailValid();
		});
	},
	
	checkEmailValid() {
		let email = document.querySelector("#resrv_id").value;
		let emailValid = (/^[\w]\w+\@\w+\.\w+\.*\w*/).test(email);
		if (emailValid) {
			console.log(email);
		} else {
			document.querySelector(".booking_login").innerHTML +=
				`<div class="warning_msg">올바른 이메일 형식이 아닙니다</div>`;
			return;
		}
	}
}

document.addEventListener("DOMContentLoaded", () => {
	bookinglogin.init();
});
