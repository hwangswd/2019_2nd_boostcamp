package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.DisplayInfo;
import com.nts.reservation.dto.DisplayInfoImage;
import com.nts.reservation.dto.Product;
import com.nts.reservation.dto.ProductImage;
import com.nts.reservation.dto.ProductPrice;

/**
 * @author 황성욱
 * @since 2019-07-24
 */
public interface ProductService {
	public int getCountAll();

	public int getCountByCategoryId(int categoryId);

	public List<Product> getAllFromStart(int start);

	public List<Product> getByCategoryIdFromStart(int categoryId, int start);

	public DisplayInfo getDisplayInfo(int displayInfoId);

	public List<ProductImage> getImages(int productId);

	public DisplayInfoImage getDisplayImages(int displayInfoId);

	public List<Comment> getComments(int displayInfoId);

	public double getAverageScore(int displayInfoId);

	public List<ProductPrice> getPrices(int productId);
}
