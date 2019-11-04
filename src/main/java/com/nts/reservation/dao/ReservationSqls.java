package com.nts.reservation.dao;

/**
 * 예약관련 쿼리문을 모아둔 클래스
 * @author 황성욱
 * @since 2019-08-09
 */
public class ReservationSqls {
	public static final String SELECT_RESERVATION_INFO =
	    " SELECT reservation_info.id                AS reservation_info_id,"
	  + "		 reservation_info.product_id        AS product_id,"
	  + "		 reservation_info.display_info_id   AS display_info_id,"
	  + "		 reservation_info.reservation_name  AS reservation_name,"
	  + "		 reservation_info.reservation_tel   AS reservation_telephone,"
	  + "		 reservation_info.reservation_email AS reservation_email,"
	  + "		 reservation_info.cancel_flag       AS cancel_yn,"
	  + "		 reservation_info.reservation_date  AS reservation_date,"
	  + "		 reservation_info.create_date       AS create_date,"
	  + "		 reservation_info.modify_date       AS modify_date,"
	  + "		 SUM(product_price.price * (1 - (product_price.discount_rate / 100)) *"
	  + "			reservation_info_price.count)   AS total_price"
	  + " FROM   reservation_info"
	  + "	INNER JOIN reservation_info_price"
	  + "		ON reservation_info_price.reservation_info_id = reservation_info.id" 
	  + "	INNER JOIN product_price"
	  + "		ON product_price.id = reservation_info_price.product_price_id"
	  + " WHERE  reservation_info.reservation_email = :reservationEmail"
	  + " GROUP BY reservation_info.id;";

	public static final String SELECT_TOTAL_PRICE =
	    " SELECT SUM(price * count) AS total_price"
	  + " FROM   reservation_info_price"
	  + "	INNER JOIN product_price"
	  + "		ON product_price.id = reservation_info_price.product_price_id"
	  + " WHERE  reservation_info_price.reservation_info_id = :reservationInfoId";

	public static final String INSERT_RESERVATION =
	    " INSERT INTO reservation_info ("
	  + "			product_id,"
	  + "			display_info_id,"
	  + "			reservation_name,"
	  + "			reservation_tel,"
	  + "			reservation_email,"
	  + "			reservation_date,"
	  + "			cancel_flag,"
	  + "			create_date,"
	  + "			modify_date)"
	  + " VALUES (:productId,"
	  + "			:displayInfoId,"
	  + "			:reservationName,"
	  + "			:reservationTel,"
	  + "			:reservationEmail,"
	  + "			:reservationDate,"
	  + "			DEFAULT,"
	  + "			:createDate,"
	  + "			:modifyDate)";
	
	public static final String INSERT_PRICE =
		" INSERT INTO reservation_info_price ("
			+ "			reservation_info_id,"
			+ "			product_price_id,"
			+ "			count)"
			+ " VALUES (:reservationInfoId,"
			+ "			:productPriceId,"
			+ "			:count)";
	
	public static final String SELECT_RESPONSE =
	    " SELECT id                  AS reservation_info_id,"
	  + "		 product_id,"
	  + "		 display_info_id,"
	  + "		 reservation_name,"
	  + "		 reservation_tel     AS reservation_telephone,"
	  + "		 reservation_email,"
	  + "		 reservation_date,"
	  + "		 cancel_flag         AS cancel_yn,"
	  + "		 create_date,"
	  + "		 modify_date"
	  + " FROM   reservation_info"
	  + " WHERE id = :reservationInfoId";
	
	public static final String SELECT_PRICES =
	    " SELECT id AS reservation_info_price_id,"
	  + "		 reservation_info_id,"
	  + "		 product_price_id,"
	  + "		 count"
	  + " FROM   reservation_info_price"
	  + " WHERE  reservation_info_id = :reservationInfoId";
	
	public static final String UPDATE_RESERVATION =
	    " UPDATE reservation_info" 
	  + " SET    cancel_flag = 1"
	  + " WHERE id = :reservationInfoId";
	
	public static final String SELECT_RESERVATION_EXIST =
	    " SELECT EXISTS ("
	  + "			SELECT id" 
	  + "			FROM   reservation_info" 
	  + "			WHERE  reservation_email = :email"
	  + "			LIMIT 1"
	  + "		 )";
}
