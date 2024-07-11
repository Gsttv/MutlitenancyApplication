package com.gustrv.mutlitenancy.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "suplanEntityManagerFactory",
        transactionManagerRef = "suplanTransactionManager",
        basePackages = {"com.gustrv.mutlitenancy.repository.suplan"})
public class SuplanDatabaseConnection {

    @Value("${spring.suplan.datasource.url}")
    private String url;

    @Value("${spring.suplan.datasource.username}")
    private String username;

    @Value("${spring.suplan.datasource.password}")
    private String password;

    @Primary
    @Bean(name="suplanDbDataSource")
    public DataSource suplanDbDataSource() {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Primary
    @Bean(name = "suplanEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean suplanEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("suplanDbDataSource") DataSource suplanDataSource) {
        return builder
                .dataSource(suplanDataSource)
                .packages("com.gustrv.mutlitenancy.model")
                .persistenceUnit("suplan")
                .build();
    }


    @Primary
    @Bean(name = "suplanTransactionManager")
    public PlatformTransactionManager suplanTransactionManager(
            @Qualifier("suplanEntityManagerFactory") EntityManagerFactory
                    primaryEntityManagerFactory) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }


}
