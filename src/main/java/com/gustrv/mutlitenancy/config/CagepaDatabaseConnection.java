package com.gustrv.mutlitenancy.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "cagepaEntityManagerFactory",
        transactionManagerRef = "cagepaTransactionManager",
        basePackages = {"com.gustrv.mutlitenancy.repository.cagepa"})
public class CagepaDatabaseConnection {

    @Value("${spring.cagepa.datasource.url}")
    private String url;

    @Value("${spring.cagepa.datasource.username}")
    private String username;

    @Value("${spring.cagepa.datasource.password}")
    private String password;

    @Bean(name = "cagepaDbDataSource")
    public DataSource cagepaDbDataSource() {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Bean(name = "cagepaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean cagepaEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("cagepaDbDataSource") DataSource cagepaDataSource) {
        return builder
                .dataSource(cagepaDataSource)
                .packages("com.gustrv.mutlitenancy.model")
                .persistenceUnit("cagepa")
                .build();
    }

    @Bean(name = "cagepaTransactionManager")
    public PlatformTransactionManager cagepaTransactionManager(
            @Qualifier("cagepaEntityManagerFactory") EntityManagerFactory cagepaEntityManagerFactory) {
        return new JpaTransactionManager(cagepaEntityManagerFactory);
    }
}
