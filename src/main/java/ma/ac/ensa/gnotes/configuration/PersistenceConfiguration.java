package ma.ac.ensa.gnotes.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {"ma.ac.ensa.gnotes.repository"},transactionManagerRef = "txManager")
public class PersistenceConfiguration {
    @Value("${mysql.driver}") String driver;
    @Value("${mysql.url}") String url;
    @Value("${mysql.username}") String username;
    @Value("${mysql.password}") String password;
    @Value("${hibernate.autoReconnect}") Boolean autoReconnect;
    @Value("${hibernate.autoReconnectForPools}") Boolean autoReconnectForPools;
    @Value("${hibernate.is-connection-validation-required}") Boolean required;
    @Value("${hibernate.dialect}") String dialect;
    @Value("${hibernate.namingStrategy}") String naming;
    @Value("${hibernate.showSql}") String showSql;
    @Value("${hibernate.format_sql}") String formatSql;
    @Value("${hibernate.hbm2ddl}") String hbm2ddl;
    @Value("${hibernate.charSet}") String charSet;
    @Value("${hibernate.validationMode}") String validationMode;
    @Value("${hibernate.packageToScan}") String packageToScan;
    private static final String VALIDATION_QUERY = "SELECT 1";

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setValidationQuery(VALIDATION_QUERY);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", dialect);
        jpaProperties.put("hibernate.ejb.naming_strategy", naming);
        jpaProperties.put("hibernate.format_sql", formatSql);
        jpaProperties.put("hibernate.show_sql", showSql);
        jpaProperties.put("hibernate.hbm2ddl.auto", hbm2ddl);
        jpaProperties.put("javax.persistence.validation.mode", validationMode);
        jpaProperties.put("hibernate.connection.charSet", charSet);
        jpaProperties.put("connection.autoReconnect", autoReconnect);
        jpaProperties.put("connection.autoReconnectForPools", autoReconnectForPools);
        jpaProperties.put("connection.is-connection-validation-required", required);

        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactory.setPackagesToScan(packageToScan);
        entityManagerFactory.setJpaProperties(jpaProperties);

        return entityManagerFactory;
    }

}
