package com.nts.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.CommentDao;
import com.nts.reservation.dao.DisplayInfoDao;
import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.DisplayInfo;
import com.nts.reservation.dto.DisplayInfoImage;
import com.nts.reservation.dto.Product;
import com.nts.reservation.dto.ProductImage;
import com.nts.reservation.dto.ProductPrice;

/**
 * ProductService 인터페이스를 상속받아 구현한 class
 * @author 황성욱
 * @since 2019-07-24
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private DisplayInfoDao displayInfoDao;

	// 더보기 클릭시 늘어나는 product 수
	public final int PRODUCTS_PER_PAGE = 4;
	public final int NOT_MASKING_LENGTH = 4;

	@Override
	public int getCountAll() {
		return productDao.selectCountAll();
	}

	@Override
	public int getCountByCategoryId(int categoryId) {
		return productDao.selectCountByCategoryId(categoryId);
	}

	@Override
	public List<Product> getAllFromStart(int start) {
		return productDao.selectAllFromStart(start, PRODUCTS_PER_PAGE);
	}

	@Override
	public List<Product> getByCategoryIdFromStart(int categoryId, int start) {
		return productDao.selectByCategoryIdFromStart(categoryId, start, PRODUCTS_PER_PAGE);
	}

	@Override
	public DisplayInfo getDisplayInfo(int displayInfoId) {
		return displayInfoDao.selectDisplayInfo(displayInfoId);
	}

	@Override
	public List<ProductImage> getImages(int productId) {
		return productDao.selectImages(productId);
	}

	@Override
	public DisplayInfoImage getDisplayImages(int displayInfoId) {
		return displayInfoDao.selectDisplayImages(displayInfoId);
	}

	@Override
	public List<Comment> getComments(int displayInfoId) {
		List<Comment> commentList = commentDao.selectAll(displayInfoId);
		for (Comment comment : commentList) {
			// 이메일 주소 마스킹
			String email = comment.getReservationEmail();
			int idLength = email.indexOf('@');

			email = email.substring(0, idLength > NOT_MASKING_LENGTH ? NOT_MASKING_LENGTH : idLength);
			email += "****";
			comment.setReservationEmail(email);

			// datetime 출력 형식 변경
			String date = comment.getReservationDate();

			date = date.substring(0, 10).replace('-', '.') + ".";
			comment.setReservationDate(date);
		}

		return commentList;
	}

	@Override
	public double getAverageScore(int displayInfoId) {
		try {
			return commentDao.selectAverageScore(displayInfoId);
		} catch (NullPointerException e) {
			return 0;
		}
	}

	@Override
	public List<ProductPrice> getPrices(int productId) {
		return productDao.selectPrices(productId);
	}
}
