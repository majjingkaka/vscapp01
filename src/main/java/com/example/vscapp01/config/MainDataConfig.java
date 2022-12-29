package com.example.vscapp01.config;

//import com.yjsolutions.theassetfundweb.repository.support.Main;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.PlatformTransactionManager;


//@Configuration
//@PropertySource("classpath:config/db.properties")
//@MapperScan(value = "com.example.vscapp01", sqlSessionTemplateRef = "mainSqlSessionTemplate") //, annotationClass = Main.class
public class MainDataConfig extends DataConfig{

    // @Autowired
    // Environment env;

    // @Bean(destroyMethod = "close")
    // public BasicDataSource mainDataSource() {
    //     String profile = "dev" ;//env.getActiveProfiles()[0];
    //     BasicDataSource basicDataSource = new BasicDataSource();
    //     basicDataSource.setDriverClassName("org.mariadb.jdbc.Driver");
    //     basicDataSource.setUrl(env.getProperty(profile + ".main.url"));
    //     basicDataSource.setUsername(env.getProperty(profile + ".main.username"));
    //     basicDataSource.setPassword(env.getProperty(profile + ".main.password"));

    //     // if("prod".equals(profile)) {
    //     //     basicDataSource.setInitialSize(10);
    //     //     basicDataSource.setMaxActive(30);
    //     //     basicDataSource.setMaxIdle(30);
    //     //     basicDataSource.setMinIdle(10);
    //     //     basicDataSource.setTestWhileIdle(true);
    //     //     basicDataSource.setValidationQuery("SELECT 1");
    //     // }

    //     return basicDataSource;
    // }

    // @Bean
    // public SqlSessionFactory mainSqlSessionFactory(BasicDataSource mainDataSource) throws Exception {
    //     return super.sqlSessionFactory(mainDataSource);
    // }

    // @Bean
    // public SqlSessionTemplate mainSqlSessionTemplate(SqlSessionFactory mainSqlSessionFactory) {
    //     return super.sqlSessionTemplate(mainSqlSessionFactory);
    // }

    // @Bean
    // public PlatformTransactionManager mainTxManager(BasicDataSource mainDataSource) {
    //     return super.txManager(mainDataSource);
    // }
}
