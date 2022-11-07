package com.quinnsgames.userservice.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.quinnsgames.userservice.api.MessageResource;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class JpaConfig {
	@Bean
    public DataSource getDataSource() {
		log.info(System.getenv("MYSQL_DB_URL"));
		log.info(System.getenv("APPDATA"));
		log.info(System.getenv("COMPUTERNAME"));
		log.info(System.getenv("NUM_CORES"));
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://quinns-flexible-db-server.mysql.database.azure.com:3306/dwr_monster_api")
                .username(System.getenv("MYSQL_DB_USER"))
                .password(System.getenv("MYSQL_DB_ROOT_PASSWORD"))
                .build();
    }
}
