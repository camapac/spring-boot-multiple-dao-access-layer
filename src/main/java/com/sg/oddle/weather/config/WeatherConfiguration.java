package com.sg.oddle.weather.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAsync
@EnableTransactionManagement
@EntityScan(basePackages = "com.sg.oddle.weather.entities")
@EnableJpaRepositories(basePackages = "com.sg.oddle.weather.repository")
public class WeatherConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}

	@Bean(name="sessionFactory")
	public LocalSessionFactoryBean sessionFactory() {
		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.sg.oddle.weather.entities" });
		sessionFactory.setHibernateProperties(additionalProperties());

		return sessionFactory;
	}

    @Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.sg.oddle.weather.entities" });

		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

    @Primary
	@Bean
	public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "false");
		hibernateProperties.setProperty("hibernate.allow_update_outside_transaction", "true");
		hibernateProperties.setProperty("hibernate.current_session_context_class",
				env.getProperty("hibernate.current_session_context_class"));

		return hibernateProperties;
	}

	@Bean(name = "myThreadPoolExecutor")
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(1000000);
		executor.initialize();
		return executor;
	}
}
