package com.quinnsgames.userservice.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {
	@Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url(System.getenv("MYSQL_DB_URL"))
                .username(System.getenv("MYSQL_DB_USER"))
                .password(System.getenv("MYSQL_DB_ROOT_PASSWORD"))
                .build();
    }
}
