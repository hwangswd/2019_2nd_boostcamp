package com.nts.reservation.dto;

import java.util.List;

/**
 * 예약하기 Response 모델
 * @author 황성욱
 * @since 2019-08-09
 */
public class ReservationResponse {
	private int reservationInfoId; // 예약 id
	private int productId; // 상품 Id
	private int displayInfoId; // 전시상품 id
	private String reservationName; // 예약자명
	private String reservationTelephone; // 예약자 전화번호
	private String reservationEmail; // 예약자 이메일
	private String reservationDate; // 예약일 ($date)
	private boolean cancelYn; // 취소여부
	private String createDate; // 예약 생성일시 ($date-time)
	private String modifyDate; // 예약 수정일시 ($date-time)
	private List<ReservationPrice> prices;

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public boolean isCancelYn() {
		return cancelYn;
	}

	public void setCancelYn(boolean cancelYn) {
		this.cancelYn = cancelYn;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public List<ReservationPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<ReservationPrice> prices) {
		this.prices = prices;
	}

	@Override
	public String toString() {
		return "ReservationResponse [reservationInfoId=" + reservationInfoId + ", productId=" + productId
			+ ", displayInfoId=" + displayInfoId + ", reservationName=" + reservationName + ", reservationTelephone="
			+ reservationTelephone + ", reservationEmail=" + reservationEmail + ", reservationDate=" + reservationDate
			+ ", cancelYn=" + cancelYn + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", prices="
			+ prices + "]";
	}
}
