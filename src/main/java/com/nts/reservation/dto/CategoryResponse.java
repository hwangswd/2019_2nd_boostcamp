package com.nts.reservation.dto;

import java.util.List;

/**
 * items field에 Category들을 담는다
 * @author 황성욱
 * @since 2019-07-30
 */
public class CategoryResponse {
	private List<Category> items;

	public List<Category> getItems() {
		return items;
	}

	public void setItems(List<Category> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "CategoryResponse [items=" + items + "]";
	}
}
