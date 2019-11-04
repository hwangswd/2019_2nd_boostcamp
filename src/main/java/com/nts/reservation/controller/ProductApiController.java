package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.DisplayInfoResponse;
import com.nts.reservation.dto.ProductResponse;
import com.nts.reservation.service.ProductService;

/**
 * product 관련 API 요청을 처리하는 controller
 * @author 황성욱
 * @since 2019-07-24
 */
@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {
	@Autowired
	private ProductService productService;

	private final int ALL_CATEGORIES = 0;

	@GetMapping
	public ProductResponse productList(
		@RequestParam(required = false, defaultValue = "0") int categoryId,
		@RequestParam(required = false, defaultValue = "0") int start) {
		
		ProductResponse productResponse = new ProductResponse();
		if (categoryId == ALL_CATEGORIES) {
			productResponse.setTotalCount(productService.getCountAll());
			productResponse.setItems(productService.getAllFromStart(start));
		} else {
			productResponse.setTotalCount(productService.getCountByCategoryId(categoryId));
			productResponse.setItems(productService.getByCategoryIdFromStart(categoryId, start));
		}

		return productResponse;
	}

	@GetMapping(path = "/{displayInfoId}")
	public DisplayInfoResponse displayInfo(@PathVariable("displayInfoId") int displayInfoId) {
		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();
		// displayInfo
		displayInfoResponse.setDisplayInfo(productService.getDisplayInfo(displayInfoId));
		int productId = displayInfoResponse.getDisplayInfo().getProductId();
		// productImages
		displayInfoResponse.setProductImages(productService.getImages(productId));
		// displayInfoimage
		displayInfoResponse.setDisplayInfoImage(productService.getDisplayImages(displayInfoId));
		// comments
		displayInfoResponse.setComments(productService.getComments(displayInfoId));
		// averageScore
		displayInfoResponse.setAverageScore(productService.getAverageScore(displayInfoId));
		// productPrices
		displayInfoResponse.setProductPrices(productService.getPrices(productId));

		return displayInfoResponse;
	}
}
