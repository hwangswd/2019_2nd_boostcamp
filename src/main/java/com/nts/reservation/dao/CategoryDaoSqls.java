package com.nts.reservation.dao;

/**
 * CategoryDao에서 수행하는 sql문을 모아둔 class
 * @author 황성욱
 * @since 2019-07-24
 */
public class CategoryDaoSqls {
	public static final String SELECT_ALL =
	    " SELECT category.id AS id,"
	  + "		 category.name AS name,"
	  + "		 count(product.id) AS count"
	  + " FROM   category"
	  + "	INNER JOIN product"
	  + "		ON category.id = product.category_id"
	  + "	INNER JOIN display_info"
	  + "		ON product.id = display_info.product_id"
	  + " GROUP BY id";
}
