package com.nts.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.PromotionDao;
import com.nts.reservation.dto.Promotion;

/**
 * promotionService 인터페이스를 상속받아 구현한 class
 * @author 황성욱
 * @since 2019-07-25
 */
@Service
public class PromotionServiceImpl implements PromotionService {
	@Autowired
	private PromotionDao promotionDao;

	@Override
	public List<Promotion> getAll() {
		return promotionDao.selectAll();
	}
}
