package com.spring.aurora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AuroraApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AuroraApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(AuroraApplication.class, args);
    }

    //    @Bean
    //    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
    //        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    //        sessionFactory.setDataSource(dataSource);
    //        sessionFactory.setPackagesToScan("com.spring.aurora.model");
    //        // sessionFactory.setHibernateProperties(hibernateProperties());
    //        return sessionFactory;
    //    }
    //
    //    @Bean
    //    public PlatformTransactionManager hibernateTransactionManager(DataSource dataSource) {
    //        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    //        transactionManager.setSessionFactory(this.sessionFactory(dataSource).getObject());
    //        return transactionManager;
    //    }
}