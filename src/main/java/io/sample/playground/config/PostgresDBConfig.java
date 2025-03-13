package io.sample.playground.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class PostgresDBConfig {
    @Value("${postgres.url}")
    private String url;
    @Value("${postgres.jdbc_user}")
    private String username;
    @Value("${postgres.jdbc_pass}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;


    @Bean
    @Primary
    public DataSource getDataSource() {
        return DataSourceBuilder
                .create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }
}
