package com.example.vscapp01.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Configuration
@PropertySource("classpath:config/db.properties")
@MapperScan(basePackages = {"com.example.vscapp01.mapper"})
public class DataConfig {
    private Logger logger = LogManager.getLogger(DataConfig.class);

    @Autowired
    Environment env;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
	public DataSource dataSource() {
        String profile = "dev";
        
        // if(env != null){
        //     profile = env.getActiveProfiles()[0];
        //     logger.info("profile::::::::"+profile);
          
        //     if("dev|local".indexOf(profile) < 0){
        //         profile = "prod";
        //     }
        //     logger.info("profile::::::::"+profile);
    
        // }else{
        //     profile = "prod";
        // }
        
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty(profile + ".main.driverClassName"));
		dataSource.setUrl(env.getProperty(profile + ".main.url"));
		dataSource.setUsername(env.getProperty(profile + ".main.username"));
		dataSource.setPassword(env.getProperty(profile + ".main.password"));

        logger.info("profile::::::::"+env.getProperty(profile + ".main.driverClassName"));

		return dataSource;
	}
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        //sqlSessionFactoryBean.setMapperLocations(applicationContext.getResource("classpath:com/example/vscapp01/mapper/**/*.xml"));

        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        //sqlSessionFactoryBean.setConfigLocation(patternResolver.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(patternResolver.getResources("classpath:com/example/vscapp01/mapper/**/*.xml"));
        //sqlSessionFactoryBean.setTypeAliasesPackage("mybatis.mapper");
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory mainSqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(mainSqlSessionFactory);
        return sqlSessionTemplate;
    }

     @Bean
     public PlatformTransactionManager txManager(DataSource dataSource) {
         return new DataSourceTransactionManager(dataSource);
     }
}
