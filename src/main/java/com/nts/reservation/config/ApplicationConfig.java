package com.nts.reservation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * java config 설정하는 클래스
 * @author 황성욱
 * @since 2019-07-24
 */
@Configuration
@ComponentScan(basePackages = {"com.nts.reservation.dao", "com.nts.reservation.service"})
@Import({DBConfig.class})
public class ApplicationConfig {}