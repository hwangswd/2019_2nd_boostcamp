package com.nts.reservation.dao;

/**
 * @author 황성욱
 * @since 2019-07-31
 */
public class CommentDaoSqls {
	public static final String SELECT_ALL =
	    " SELECT user_comment.comment AS comment,"
	  + " 		 user_comment.id AS comment_id,"
	  + " 		 user_comment.create_date AS create_date,"
	  + " 		 user_comment.modify_date AS modify_date,"
	  + " 		 user_comment.product_id AS product_id,"
	  + " 		 reservation_info.reservation_date AS reservation_date,"
	  + " 		 reservation_info.reservation_email AS reservation_email,"
	  + " 		 reservation_info.id AS reservation_info_id,"
	  + " 		 reservation_info.reservation_name AS reservation_name,"
	  + " 		 reservation_info.reservation_tel AS reservation_telephone,"
	  + " 		 user_comment.score AS score"
	  + " FROM reservation_user_comment AS user_comment"
	  + " 	INNER JOIN reservation_info"
	  + " 		ON reservation_info.id = user_comment.reservation_info_id"
	  + " WHERE  reservation_info.display_info_id = :displayInfoId"
	  + " ORDER BY comment_id DESC";
	
	public static final String SELECT_IMAGES =
	    " SELECT file_info.content_type AS content_type,"
	  + " 		 file_info.create_date AS create_date,"
	  + " 		 file_info.delete_flag AS delete_flag,"
	  + " 		 file_info.id AS file_id,"
	  + " 		 file_info.file_name AS file_name,"
	  + " 		 reservation_user_comment_image.id AS image_id,"
	  + " 		 file_info.modify_date AS modify_date,"
	  + " 		 reservation_user_comment_image.reservation_info_id AS reservation_info_id,"
	  + " 		 reservation_user_comment.id AS reservation_user_comment_id,"
	  + " 		 file_info.save_file_name AS save_file_name"
	  + " FROM file_info"
	  + " 	INNER JOIN reservation_user_comment_image"
	  + " 		ON file_info.id = reservation_user_comment_image.file_id"
	  + " 	INNER JOIN reservation_user_comment"
	  + " 		ON reservation_user_comment_image.reservation_user_comment_id = reservation_user_comment.id"
	  + " WHERE  reservation_user_comment.id = :reservationUserCommentId";
	
	public static final String SELECT_AVERAGE_SCORE =
	    " SELECT AVG(score) AS average_score"
	  + " FROM reservation_user_comment"
	  + " 	INNER JOIN reservation_info"
	  + " 		ON reservation_info.id = reservation_user_comment.reservation_info_id"
	  + " WHERE reservation_info.display_info_id = :displayInfoId";
}
