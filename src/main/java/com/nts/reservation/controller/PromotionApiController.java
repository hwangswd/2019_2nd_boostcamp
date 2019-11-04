package com.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Promotion;
import com.nts.reservation.dto.PromotionResponse;
import com.nts.reservation.service.PromotionService;

/**
 * 메인 페이지 promotion 관련 API 요청을 처리하는 controller
 * @author 황성욱
 * @since 2019-07-31
 */
@RestController
@RequestMapping(path = "/api")
public class PromotionApiController {
	@Autowired
	private PromotionService promotionService;

	@GetMapping(path = "/promotions")
	public PromotionResponse promotionList() {
		List<Promotion> promotions = promotionService.getAll();
		PromotionResponse promotionResponse = new PromotionResponse();
		promotionResponse.setItems(promotions);

		return promotionResponse;
	}
}
