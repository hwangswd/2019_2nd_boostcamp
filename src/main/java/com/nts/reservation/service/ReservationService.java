package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationParam;
import com.nts.reservation.dto.ReservationResponse;

/**
 * @author 황성욱
 * @since 2019-08-09
 */
public interface ReservationService {
	public List<ReservationInfo> getList(String reservationEmail);
	
	public int makeReservation(ReservationParam rsvnParam) throws Exception;
	
	public ReservationResponse getResponse(int reservationInfoId);

	public void cancelReservation(int reservationId) throws Exception;

	public boolean isReservationExist(String email);
}
