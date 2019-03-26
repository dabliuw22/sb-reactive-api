
package com.leyton.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableR2dbcRepositories
public class DatabaseConfiguration {

    @Value(
            value = "${spring.data.r2db.postgres.host}")
    private String host;

    @Value(
            value = "${spring.data.r2db.postgres.port}")
    private int port;

    @Value(
            value = "${spring.data.r2db.postgres.database}")
    private String database;

    @Value(
            value = "${spring.data.r2db.postgres.username}")
    private String username;

    @Value(
            value = "${spring.data.r2db.postgres.password}")
    private String password;

    @Bean
    public PostgresqlConnectionConfiguration connectionConfiguration() {
        return PostgresqlConnectionConfiguration.builder().host(host).port(port).database(database)
                .username(username).password(password).build();
    }

    @Bean
    public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient.builder().connectionFactory(connectionFactory).build();
    }
}
