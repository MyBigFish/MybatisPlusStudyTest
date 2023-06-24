package com.my.test.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dayuqichengbao
 * @version 创建时间 2023/6/23 11:26
 */
@Configuration
@MapperScan("com.my.test.mapper")
public class MyConfig {

}

