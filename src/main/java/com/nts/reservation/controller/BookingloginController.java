package com.nts.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingloginController {
	/**
	 * main 페이지에서 예약확인 클릭 시 이동
	 */
	@GetMapping(path = "/bookinglogin")
	public String login() {
		return "bookinglogin";
	}
	
	// form을 입력받은 후 쿠키를 생성하는 함수
//	@PostMapping(path = "/bookinglogin")
//	public View makeCookie(@ModelAttribute("url") String url,
//		@ModelAttribute("reservation_email") String reservation_email,
//		@CookieValue(required = false) String email,
//		HttpServletResponse response) {
//
//		Cookie cookie = new Cookie("email", reservation_email);
//		cookie.setMaxAge(60 * 30); // 30분동안 쿠키 유지
//		cookie.setPath("/");
//		response.addCookie(cookie);
//
//		RedirectView redirectView = new RedirectView(url, true);
//		redirectView.setExposeModelAttributes(false); // redirect할때 붙는 GET parameter 제거
//		return redirectView;
//	}

	
	
	
	
	
	/*
	 * 비회원 로그인 요청 (email)
	 */
//	@PostMapping(path = "/bookinglogin")
//	public String login(@RequestParam(name = "resrv_email") String reservationEmail,
//		@CookieValue(value = "loginedEmail", required = false) String loginedEmail,
//		HttpServletResponse response,
//		RedirectAttributes redirectAttr) throws Exception {
//
//		try {
//			userService.login(reservationEmail);
//
//			Cookie cookie = new Cookie("loginedEmail", reservationEmail);
//			cookie.setMaxAge(60 * 60); // 1hours 동안 유지.
//			cookie.setPath("/"); // / 경로 이하에 모두 쿠키 적용. 
//			response.addCookie(cookie);
//
//			return "redirect:/myreservation";
//		} catch (Exception exception) {
//			//TODO RedirectAttribute 사용말고, 데이터 보내기
//			return "redirect:/bookinglogin?message=error";
//		}
//	}


}
