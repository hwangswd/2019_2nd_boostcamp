package com.nts.reservation.dao;

import static com.nts.reservation.dao.DisplayInfoDaoSqls.*;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.DisplayInfo;
import com.nts.reservation.dto.DisplayInfoImage;

/**
 * display info와 관련된 데이터에 접근하는 dao 클래스
 * @author 황성욱
 * @since 2019-07-31
 */
@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfo> displayInfoRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private RowMapper<DisplayInfoImage> imageRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);

	public DisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public DisplayInfo selectDisplayInfo(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		return jdbc.queryForObject(SELECT_ONE, params, displayInfoRowMapper);
	}
	
	public DisplayInfoImage selectDisplayImages(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		
		return jdbc.queryForObject(SELECT_IMAGE, params, imageRowMapper);
	}
}
