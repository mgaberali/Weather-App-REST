package com.m.weatherapp.model.config;

import com.m.weatherapp.model.ModelPackageMarker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;

@Configuration
@ComponentScan(basePackageClasses = ModelPackageMarker.class)
@Import({DataSourceConfig.class, JPAConfig.class})
public class ModelConfig {

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

}
