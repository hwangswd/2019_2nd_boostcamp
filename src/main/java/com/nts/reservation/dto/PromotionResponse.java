package com.nts.reservation.dto;

import java.util.List;

/**
 * items field에 Promotion들을 담는다
 * @author 황성욱
 * @since 2019-07-30
 */
public class PromotionResponse {
	private List<Promotion> items;

	public List<Promotion> getItems() {
		return items;
	}

	public void setItems(List<Promotion> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "PromotionResponse [items=" + items + "]";
	}
}
