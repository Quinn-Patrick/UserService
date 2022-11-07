package com.quinnsgames.userservice.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class JpaConfig {
	@Autowired
	private Environment environment;
	
	@Bean
    public DataSource getDataSource() {
		log.info(environment.getProperty("MYSQL_DB_URL"));
		log.info(System.getProperty("APPDATA"));
		log.info(System.getProperty("COMPUTERNAME"));
		log.info(System.getenv("NUM_CORES"));
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://quinns-flexible-db-server.mysql.database.azure.com:3306/dwr_monster_api")
                .username(environment.getProperty("MYSQL_DB_USER"))
                .password(environment.getProperty("MYSQL_DB_ROOT_PASSWORD"))
                .build();
    }
}
