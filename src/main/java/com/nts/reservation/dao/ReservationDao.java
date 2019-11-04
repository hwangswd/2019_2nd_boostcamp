package com.nts.reservation.dao;

import static com.nts.reservation.dao.ReservationSqls.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationParam;
import com.nts.reservation.dto.ReservationPrice;
import com.nts.reservation.dto.ReservationResponse;

/**
 * 예약과 관련된 데이터에 접근하는 dao 클래스
 * @author 황성욱
 * @since 2019-08-09
 */
@Repository
public class ReservationDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationInfo> rsvnInfoRowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);
	private RowMapper<ReservationResponse> rsvnResRowMapper = BeanPropertyRowMapper.newInstance(ReservationResponse.class);
	private RowMapper<ReservationPrice> rsvnPriceRowMapper = BeanPropertyRowMapper.newInstance(ReservationPrice.class);

	public ReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ReservationInfo> selectReservationInfo(String reservationEmail) {
		Map<String, String> params = new HashMap<>();
		params.put("reservationEmail", reservationEmail);
		return jdbc.query(SELECT_RESERVATION_INFO, params, rsvnInfoRowMapper);
	}

	public long selectTotalPrice(int reservationInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("reservationInfoId", reservationInfoId);
		
		return jdbc.queryForObject(SELECT_TOTAL_PRICE, params, Long.class);
	}

	public int insertOne(ReservationParam reservationParam) {
		// TODO confirm datetime
//		Timestamp now = Timestamp.valueOf(LocalDateTime.now()); // 2018-07-26 01:06:55.323 
		LocalDateTime now = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()); // 2018-07-26T01:20:07.364
		KeyHolder holder = new GeneratedKeyHolder();
		
		SqlParameterSource paramSource = new MapSqlParameterSource()
			.addValue("productId", reservationParam.getProductId())
			.addValue("displayInfoId", reservationParam.getDisplayInfoId())
			.addValue("reservationName", reservationParam.getReservationName())
			.addValue("reservationTel", reservationParam.getReservationTelephone())
			.addValue("reservationEmail", reservationParam.getReservationEmail())
			.addValue("reservationDate", reservationParam.getReservationYearMonthDay())
			.addValue("createDate", now)
			.addValue("modifyDate", now);
		
		jdbc.update(INSERT_RESERVATION, paramSource, holder);
		int rsvnInfoId = holder.getKey().intValue();
		System.out.println(rsvnInfoId);
		return rsvnInfoId;
	}
	
	public void insertPrices(ReservationParam reservationParam, int rsvnInfoId) {
		Map<String, Integer> params = new HashMap<>();
		List<ReservationPrice> prices = reservationParam.getPrices();
		System.out.println("infoID"+rsvnInfoId);
		for (ReservationPrice price : prices) {
			System.out.println(price.toString());
			params.put("reservationInfoId", rsvnInfoId);
			params.put("productPriceId", price.getProductPriceId());
			params.put("count", price.getCount());
			jdbc.update(INSERT_PRICE, params);
		}
	}
	
	public ReservationResponse selectResponse(int reservationInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("reservationInfoId", reservationInfoId);
		return jdbc.queryForObject(SELECT_RESPONSE, params, rsvnResRowMapper);
	}
	
	public List<ReservationPrice> selectPrices(int reservationInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("reservationInfoId", reservationInfoId);
		return jdbc.query(SELECT_PRICES, params, rsvnPriceRowMapper);
	}
	
	public void updateCancel(int reservationInfoId) throws Exception {
		Map<String, Integer> params = new HashMap<>();
		params.put("reservationInfoId", reservationInfoId);
		jdbc.update(UPDATE_RESERVATION, params);
	}
	
	public int selectReservationExist(String email) {
		Map<String, String> params = new HashMap<>();
		params.put("email", email);
		return jdbc.queryForObject(SELECT_RESERVATION_EXIST, params, Integer.class);
	}
}
