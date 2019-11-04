package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.ReservationInfoResponse;
import com.nts.reservation.dto.ReservationParam;
import com.nts.reservation.dto.ReservationResponse;
import com.nts.reservation.service.ReservationService;

/**
 * reservation 관련 API 요청을 처리하는 controller
 * @author 황성욱
 * @since 2019-08-09
 */
@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationApiController {
	@Autowired
	private ReservationService rsvnService;

	@GetMapping
	public ReservationInfoResponse reservationInfo(
		@RequestParam String reservationEmail) {
		ReservationInfoResponse rsvnInfoRes = new ReservationInfoResponse();
		rsvnInfoRes.setReservations(rsvnService.getList(reservationEmail));
		return rsvnInfoRes;
	}
	
	@PostMapping
	public ReservationResponse reserve(
		@RequestBody ReservationParam reservationParam) {
		// TODO exception handling
		System.out.println(reservationParam);
		try {
			int rsvnId = rsvnService.makeReservation(reservationParam);
			return rsvnService.getResponse(rsvnId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping(path = "/{reservationId}")
	public ReservationResponse cancelReservation(
		@PathVariable int reservationId) {
		// TODO exception handling
		try {
			rsvnService.cancelReservation(reservationId);
			return rsvnService.getResponse(reservationId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
