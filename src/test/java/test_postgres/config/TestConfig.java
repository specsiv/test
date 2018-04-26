package test_postgres.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"test_postgres.service"})
@EntityScan(basePackages = {"test_postgres.jpa.entity"})
@EnableJpaRepositories(basePackages = {"test_postgres.jpa.repository"})
public class TestConfig {

}