package com.nts.reservation.dto;

import java.util.List;

/**
 * 예약조회 Response 모델
 * @author 황성욱
 * @since 2019-08-09
 */
public class ReservationInfoResponse {
	private List<ReservationInfo> reservations; // 예약 정보들
	private int size; // 예약 수

	public List<ReservationInfo> getReservations() {
		return reservations;
	}

	public void setReservations(List<ReservationInfo> reservations) {
		this.reservations = reservations;
	}

	public int getSize() {
		setSize();
		return size;
	}

	public void setSize() {
		this.size = this.reservations.size();
	}

	@Override
	public String toString() {
		return "ReservationInfoResponse [reservations=" + reservations + ", size=" + size + "]";
	}
}
