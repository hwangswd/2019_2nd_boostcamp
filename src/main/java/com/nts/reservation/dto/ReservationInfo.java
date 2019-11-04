package com.nts.reservation.dto;

/**
 * 예약정보 모델
 * @author 황성욱
 * @since 2019-08-09
 */
public class ReservationInfo {
	private int reservationInfoId; // 예약 Id
	private int productId; // 상품 id
	private int displayInfoId; // 전시상품 id
	private String reservationName; // 예약자명
	private String reservationTelephone; // 예약자 전화번호
	private String reservationEmail; // 예약자 이메일
	private boolean cancelYn; // 예약 취소 여부
	private String reservationDate; // 예약일 ($date)
	private String createDate; // 예약 생성일시 ($date-time)
	private String modifyDate; // 예약 수정일시 ($date-time)
	private DisplayInfo displayInfo; // 상품 전시 모델
	private long totalPrice; // 예약한 상품 총 가격

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

	public boolean isCancelYn() {
		return cancelYn;
	}

	public void setCancelYn(boolean cancelYn) {
		this.cancelYn = cancelYn;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
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

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "ReservationInfo [reservationInfoId=" + reservationInfoId + ", productId=" + productId
			+ ", displayInfoId=" + displayInfoId + ", reservationName=" + reservationName + ", reservationTelephone="
			+ reservationTelephone + ", reservationEmail=" + reservationEmail + ", cancelYn=" + cancelYn
			+ ", reservationDate=" + reservationDate + ", createDate=" + createDate + ", modifyDate=" + modifyDate
			+ ", displayInfo=" + displayInfo + ", totalPrice=" + totalPrice + "]";
	}
}
