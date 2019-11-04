package com.nts.reservation.dto;

import java.util.List;

/**
 * items field 에 전시상품 (Product) 들을 담는다
 * @author 황성욱
 * @since 2019-07-30
 */
public class ProductResponse {
	private int totalCount; // 카테고리에 해당하는 전체 상품 수
	private List<Product> items;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<Product> getItems() {
		return items;
	}

	public void setItems(List<Product> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "ProductResponse [items=" + items + ", totalCount=" + totalCount + "]";
	}
}
