/**
 * ajax 통신이 성공하면 callBack에 지정된 함수를 호출
 * @author 황성욱
 * @since 2019-08-02
 */

const RESPONSE_OK = 200;

let ajaxRequest = {
	sendRequest(method, url, callBack) {
		let xhr = new XMLHttpRequest();
		xhr.responseType = 'json';
		
		xhr.addEventListener("load", () => {
			if (xhr.status === RESPONSE_OK) {
				callBack(xhr);
			} else {
				alert("문제가 발생했습니다.")
			}
		});
		
		xhr.open(method, url);
		xhr.send();
	}
}
