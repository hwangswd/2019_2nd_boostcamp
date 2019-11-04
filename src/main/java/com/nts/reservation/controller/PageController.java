package com.nts.reservation.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 2019-08-09 revision from DetailPageController
 *            migrate from ReviewPageController
 * 2019-08-14 migrate from ReservationController
 * 2019-08-18 /bookinglogin move to BookingloginController
 *            /myreservation move to MyreservationController
 * 상품과 관련된 페이지로 연결하는 controller
 * display info를 보여주는 detail페이지 또는 한줄평 페이지인 review 페이지로 연결 
 * @author 황성욱
 * @since 2019-07-24
 */
@Controller
public class PageController {
	/**
	 * main 페이지에서 특정 product 클릭 시 detail.jsp 페이지로 이동
	 */
	@GetMapping(path = "/detail")
	public ModelAndView showProductDetail(@RequestParam int id) {
		ModelAndView detailPage = new ModelAndView("/detail");
		detailPage.addObject("id", id);
		return detailPage;
	}
	
	/**
	 * detail 페이지에서 한줄평 더보기 클릭 시 review.jsp 페이지로 이동
	 */
	@GetMapping(path = "/review")
	public ModelAndView readReviewMore(@RequestParam int id) {
		ModelAndView reviewPage = new ModelAndView("/review");
		reviewPage.addObject("id", id);
		return reviewPage;
	}

	@GetMapping(path = "/reserve")
	public ModelAndView reservation(
		@RequestParam int id) {
		ModelAndView reservePage = new ModelAndView("/reserve");
		
		reservePage.addObject("id", id);
		
		int randNum = (int)(Math.random() * 5) % 5;
		String reservationDate = LocalDateTime.now().plusDays(randNum).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
		reservePage.addObject("reservationDate", reservationDate);
		
		return reservePage;
	}
}
