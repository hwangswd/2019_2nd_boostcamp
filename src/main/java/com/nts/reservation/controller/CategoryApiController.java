package com.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.CategoryResponse;
import com.nts.reservation.service.CategoryService;

/**
 * 메인 페이지 category 관련 API 요청을 처리하는 controller
 * @author 황성욱
 * @since 2019-07-31
 */
@RestController
@RequestMapping(path = "/api")
public class CategoryApiController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping(path = "/categories")
	public CategoryResponse categoryList() {
		List<Category> categorys = categoryService.getAll();
		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setItems(categorys);

		return categoryResponse;
	}
}
