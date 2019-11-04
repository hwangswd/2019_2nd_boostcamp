package com.nts.reservation.dao;

/**
 * ProductDao에서 수행하는 sql문을 모아둔 class
 * @author 황성욱
 * @since 2019-07-24
 */
public class ProductDaoSqls {
	public static final String SELECT_COUNT_ALL =
	    " SELECT COUNT(display_info.id) AS total_count"
	  + " FROM   display_info";

	public static final String SELECT_COUNT_BY_CATEGORY_ID =
	      SELECT_COUNT_ALL
	  + "	INNER JOIN product"
	  + "		ON display_info.product_id = product.id"
	  + " WHERE category_id = :categoryId";

	public static final String SELECT_ALL_FROM_START =
	    " SELECT display_info.id AS display_info_id,"
	  + "		 product.id AS product_id,"
	  + "		 product.description AS product_description,"
	  + "		 display_info.place_name AS place_name,"
	  + "		 product.content AS product_content,"
	  + "		 file_info.save_file_name AS product_image_url"
	  + " FROM   product"
	  + " 	INNER JOIN display_info"
	  + "		ON display_info.product_id = product.id"
	  + "	INNER JOIN product_image"
	  + "		ON product_image.product_id = product.id"
	  + "	INNER JOIN file_info"
	  + "		ON file_info.id = product_image.file_id"
	  + " WHERE  product_image.type='th'"
	  + " ORDER BY product.create_date DESC"
	  + "	LIMIT :start, :productsPerPage";

	public static final String SELECT_BY_CATEGORY_ID_FROM_START =
	    " SELECT display_info.id AS display_info_id,"
	  + "		 product.id AS product_id,"
	  + "		 product.description AS product_description,"
	  + "		 display_info.place_name AS place_name,"
	  + "		 product.content AS product_content,"
	  + "		 file_info.save_file_name AS product_image_url"
	  + " FROM   product"
	  + "	INNER JOIN display_info"
	  + "		ON display_info.product_id = product.id"
	  + "	INNER JOIN product_image"
	  + "		ON product_image.product_id = product.id"
	  + "	INNER JOIN file_info"
	  + "		ON file_info.id = product_image.file_id"
	  + " WHERE  product_image.type='th'"
	  + "		AND category_id = :categoryId"
	  + " ORDER BY product.create_date DESC"
	  + "	LIMIT :start, :productsPerPage";

	public static final String SELECT_IMAGES =
	    " SELECT product_image.product_id AS product_id,"
	  + "		 product_image.id AS product_image_id,"
	  + "		 product_image.type AS type,"
	  + "		 file_info.id AS file_info_id,"
	  + "		 file_info.file_name AS file_name,"
	  + "		 file_info.save_file_name AS save_file_name,"
	  + "		 file_info.content_type AS content_type,"
	  + "		 file_info.delete_flag AS delete_flag,"
	  + "		 file_info.create_date AS create_date,"
	  + "		 file_info.modify_date AS modify_date"
	  + " FROM   product_image"
	  + " 	INNER JOIN file_info"
	  + " 		ON product_image.file_id = file_info.id"
	  + " WHERE  product_id = :productId"
	  + " 		AND (type = 'ma' OR type = 'et')";
	
	public static final String SELECT_PRICES =
	    " SELECT id AS product_price_id,"
	  + "		 product_id,"
	  + "		 price_type_name,"
	  + "		 price,"
	  + "		 discount_rate,"
	  + "		 create_date,"
	  + "		 modify_date"
	  + " FROM   product_price"
	  + " WHERE  product_id = :productId"
	  + " ORDER BY id DESC";
}
