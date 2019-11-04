package com.nts.reservation.dao;

import static com.nts.reservation.dao.CommentDaoSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.CommentImage;

/**
 * 전시정보의 한줄평과 관련된 데이터에 접근하는 dao 클래스
 * @author 황성욱
 * @since 2019-07-31
 */
@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Comment> commentRowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	private RowMapper<CommentImage> imageRowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);

	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Comment> selectAll(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		List<Comment> commentList = jdbc.query(SELECT_ALL, params, commentRowMapper);
		for (Comment comment : commentList) {
			comment.setCommentImages(selectImages(comment.getCommentId()));
		}
		return commentList;
	}

	public List<CommentImage> selectImages(int reservationUserCommentId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("reservationUserCommentId", reservationUserCommentId);

		return jdbc.query(SELECT_IMAGES, params, imageRowMapper);
	}

	public double selectAverageScore(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_AVERAGE_SCORE, params, Double.class);
	}
}
