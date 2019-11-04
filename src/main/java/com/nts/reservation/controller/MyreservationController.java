package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nts.reservation.service.ReservationService;

/**
 * bookinglogin 페이지에서 로그인 시도 요청을 처리하는 controller
 * @author 황성욱
 * @since 2019-08-18
 */
@Controller
public class MyreservationController {
	@Autowired
	private ReservationService rsvnService;
	
	/**
	 * bookinglogin 페이지에서 로그인 성공 시 이동
	 */
	@GetMapping(path = "/myreservation")
	public ModelAndView showMyreservation(
		@RequestParam String reservationEmail) {
		ModelAndView nextPage = new ModelAndView();
		
		if (rsvnService.isReservationExist(reservationEmail)) {
			nextPage.setViewName("/myreservation");
			nextPage.addObject("reservationEmail", reservationEmail);
		} else {
			nextPage.setViewName("redirect:/");
			nextPage.addObject("msg", "InputEmailIsNotExists");
			// TODO ModelMap setAttribute 등으로 처리
		}
		return nextPage;
	}
	
//	public String myreservation(@CookieValue String email, ModelMap modelmap, HttpServletResponse response) {
//		// email 쿠키로 reservationInfoResponse를 가져옴
//		ReservationInfoResponse reservationInfoResponse = reservationService.getReservations(email);
//
//		// modelMap으로 보낼 list 객체
//		List<ReservationInfo> confirmedReservations = new ArrayList<ReservationInfo>();
//		List<ReservationInfo> completedReservations = new ArrayList<ReservationInfo>();
//		List<ReservationInfo> canceledReservations = new ArrayList<ReservationInfo>();
//
//		// 예매 내역이 있으면 쿠키를 저장, 없으면 쿠키 제거
//		if (reservationInfoResponse.getSize() == 0) {
//			Cookie cookie = new Cookie("email", null);
//			cookie.setMaxAge(0);
//			cookie.setPath("/");
//			response.addCookie(cookie);
//		} else {
//			Date now = new Date();
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
//
//			// reservationInfoResponse 객체 분류 - 취소된 예약/이용 완료/예약 확정 
//			for (ReservationInfo reservationInfo : reservationInfoResponse.getReservations()) {
//				if (reservationInfo.getCancelYn() == true) {
//					canceledReservations.add(reservationInfo);
//					continue;
//				}
//				try {
//					Date reservationDate = formatter.parse(reservationInfo.getReservationDate());
//					if (now.compareTo(reservationDate) == -1) {
//						confirmedReservations.add(reservationInfo);
//						continue;
//					}
//				} catch (Exception e) {
//					confirmedReservations.add(reservationInfo);
//					continue;
//				}
//				completedReservations.add(reservationInfo);
//			}
//		}
//		// 위에서 구한 reservation들을 VO에 담아서 보냄, VO는 read-only한 특성을 가짐
//		modelmap.addAttribute("myreservationVo",
//			new MyreservationVo(confirmedReservations, completedReservations, canceledReservations));
//
//		return "myreservation";
//	}

}
