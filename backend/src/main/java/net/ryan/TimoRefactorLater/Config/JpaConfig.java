package net.ryan.TimoRefactorLater.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {
    @Value("${DATABASE_URL:jdbc:postgresql://localhost:5432/postgres}")
    private String databaseUrl;

    @Value("${DATABASE_USERNAME:postgres}")
    private String databaseUsername;

    @Value("${DATABASE_PASSWORD:password}")
    private String databasePassword;

    @Bean
    public DataSource dataSource() {
        System.out.println("DATABASE_URL: " + databaseUrl);
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url(databaseUrl);
        dataSourceBuilder.username(databaseUsername);
        dataSourceBuilder.password(databasePassword);
        return dataSourceBuilder.build();
    }
}
