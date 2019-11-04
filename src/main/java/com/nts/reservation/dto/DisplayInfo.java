package com.nts.reservation.dto;

/**
 * 상품 전시 (Display) 모델
 * @author 황성욱
 * @since 2019-07-30
 */
public class DisplayInfo {
	private int productId; // 상품 id
	private int categoryId; // 카테고리 id
	private int displayInfoId; // 전시(display_info) id
	private String categoryName; // 카테고리 이름
	private String productDescription; // 상품 설명
	private String productContent; // 상품 내용
	private String productEvent; // 상품 이벤트
	private String openingHours; // 전시 시간
	private String placeName; // 전시장
	private String placeLot; // 전시 번지명
	private String placeStreet; // 전시 도로명
	private String telephone;
	private String homepage;
	private String email;
	private String createDate; // 생성일 ($date-time)
	private String modifyDate; // 수정일 ($date-time)

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public String getProductEvent() {
		return productEvent;
	}

	public void setProductEvent(String productEvent) {
		this.productEvent = productEvent;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceLot() {
		return placeLot;
	}

	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}

	public String getPlaceStreet() {
		return placeStreet;
	}

	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public String toString() {
		return "DisplayInfo [productId=" + productId + ", categoryId=" + categoryId + ", displayInfoId=" + displayInfoId
			+ ", categoryName=" + categoryName + ", productDescription=" + productDescription + ", productContent="
			+ productContent + ", productEvent=" + productEvent + ", openingHours=" + openingHours + ", placeName="
			+ placeName + ", placeLot=" + placeLot + ", placeStreet=" + placeStreet + ", telephone=" + telephone
			+ ", homepage=" + homepage + ", email=" + email + ", createDate=" + createDate + ", modifyDate="
			+ modifyDate + "]";
	}
}
