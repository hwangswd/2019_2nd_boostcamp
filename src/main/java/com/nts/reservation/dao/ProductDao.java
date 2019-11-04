package com.nts.reservation.dao;

import static com.nts.reservation.dao.ProductDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Product;
import com.nts.reservation.dto.ProductImage;
import com.nts.reservation.dto.ProductPrice;

/**
 * 상품과 관련된 데이터에 접근하는 dao 클래스
 * @author 황성욱
 * @since 2019-07-24
 */
@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> productRowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private RowMapper<ProductImage> imageRowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	private RowMapper<ProductPrice> priceRowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int selectCountAll() {
		return jdbc.queryForObject(SELECT_COUNT_ALL, Collections.emptyMap(), Integer.class);
	}

	public int selectCountByCategoryId(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		
		return jdbc.queryForObject(SELECT_COUNT_BY_CATEGORY_ID, params, Integer.class);
	}

	public List<Product> selectAllFromStart(int start, int productsPerPage) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("productsPerPage", productsPerPage);

		return jdbc.query(SELECT_ALL_FROM_START, params, productRowMapper);
	}

	public List<Product> selectByCategoryIdFromStart(int categoryId, int start, int productsPerPage) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("productsPerPage", productsPerPage);

		return jdbc.query(SELECT_BY_CATEGORY_ID_FROM_START, params, productRowMapper);
	}
	
	public List<ProductImage> selectImages(int productId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", productId);
		
		return jdbc.query(SELECT_IMAGES, params, imageRowMapper);
	}

	public List<ProductPrice> selectPrices(int productId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", productId);
		
		return jdbc.query(SELECT_PRICES, params, priceRowMapper);
	}
}
