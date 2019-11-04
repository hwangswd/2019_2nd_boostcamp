package com.nts.reservation.dao;

/**
 * PromotionDao에서 수행하는 sql문을 모아둔 class
 * @author 황성욱
 * @since 2019-07-25
 */
public class PromotionDaoSqls {
	public static final String SELECT_ALL =
	    " SELECT promotion.id AS id,"
	  + "		 promotion.product_id AS product_id,"
	  + "		 file_info.save_file_name AS productImageUrl"
	  + " FROM promotion"
	  + "	INNER JOIN product"
	  + "		ON product.id = promotion.product_id"
	  + "	INNER JOIN product_image"
	  + "		ON product_image.product_id = product.id"
	  + "	INNER JOIN file_info"
	  + "		ON file_info.id = product_image.file_id"
	  + " WHERE product_image.type='th'";
}
