package com.nts.reservation.dto;

/**
 * Promotion 영역은 메인 화면의 이미지 애니메이션 부분이다
 * 애니메이션을 수행하기 위한 product의 정보를 담을 dto class 
 * @author 황성욱
 * @since 2019-07-25
 */
public class Promotion {
	private int id;
	private int productId;
	private String productImageUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", productId=" + productId + ", productImageUrl=" + productImageUrl + "]";
	}
}
