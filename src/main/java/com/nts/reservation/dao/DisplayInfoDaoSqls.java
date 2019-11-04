package com.nts.reservation.dao;

/**
 * @author 황성욱
 * @since 2019-07-31
 */
public class DisplayInfoDaoSqls {
	public static final String SELECT_ONE =
	    " SELECT display_info.product_id AS product_id,"
	  + " 		 category.id AS category_id,"
	  + " 		 display_info.id AS display_info_id,"
	  + " 		 category.name AS category_name,"
	  + " 		 product.description AS product_description,"
	  + " 		 product.content AS product_content,"
	  + " 		 product.event AS product_event,"
	  + " 		 display_info.opening_hours AS opening_hours,"
	  + " 		 display_info.place_name AS place_name,"
	  + " 		 display_info.place_lot AS place_lot,"
	  + " 		 display_info.place_street AS place_street,"
	  + " 		 display_info.tel AS telephone,"
	  + " 		 display_info.homepage AS homepage,"
	  + " 		 display_info.email AS email,"
	  + " 		 display_info.create_date AS create_date,"
	  + " 		 display_info.modify_date AS modify_date"
	  + " FROM   display_info"
	  + " 	INNER JOIN product"
	  + " 		ON product.id = display_info.product_id"
	  + " 	INNER JOIN category"
	  + " 		ON category.id = product.category_id"
	  + " WHERE  display_info.id = :displayInfoId";
	
	public static final String SELECT_IMAGE =
	    " SELECT display_info_image.id AS display_info_image_id,"
	  + " 		 display_info_image.display_info_id AS display_info_id,"
	  + " 		 file_info.id AS file_id,"
	  + " 		 file_info.file_name AS file_name,"
	  + " 		 file_info.save_file_name AS save_file_name,"
	  + " 		 file_info.content_type AS content_type,"
	  + " 		 file_info.delete_flag AS delete_flag,"
	  + " 		 file_info.create_date AS create_date,"
	  + " 		 file_info.modify_date AS modify_date"
	  + " FROM   display_info_image"
	  + " 	INNER JOIN file_info"
	  + " 		ON display_info_image.file_id = file_info.id"
	  + " WHERE  display_info_image.display_info_id = :displayInfoId";
}
