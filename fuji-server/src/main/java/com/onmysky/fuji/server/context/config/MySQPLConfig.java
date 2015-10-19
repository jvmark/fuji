package com.onmysky.fuji.server.context.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by mark on 15/10/18.
 */
@Configuration
@Service
public class MySQPLConfig {
  @Value("${mysql.maxActive}")
  private int maxActive;

  @Value("${mysql.ds1.master.url}")
  private String ds1MasterUrl;
  @Value("${mysql.ds1.master.username}")
  private String ds1MasterUsername;
  @Value("${mysql.ds1.master.password}")
  private String ds1MasterPassword;

  @Value("${mysql.ds1.slave1.url}")
  private String ds1Slave1Url;
  @Value("${mysql.ds1.slave1.username}")
  private String ds1Slave1Username;
  @Value("${mysql.ds1.slave1.password}")
  private String ds1Slave1Password;

  private BasicDataSource parentDataSource() {
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    basicDataSource.setDefaultAutoCommit(true);
    basicDataSource.setMaxActive(maxActive);
    basicDataSource.setMaxIdle(60);
    basicDataSource.setMinIdle(60);
    basicDataSource.setInitialSize(1);
    basicDataSource.setMaxWait(30000);
    basicDataSource.setTestOnBorrow(false);
    basicDataSource.setTestOnReturn(false);
    basicDataSource.setTestWhileIdle(false);
    return basicDataSource;
  }

  @Bean(name = "ds1Master")
  public BasicDataSource ds1Master() {
    BasicDataSource ds1master = parentDataSource();
    ds1master.setUrl(ds1MasterUrl);
    ds1master.setUsername(ds1MasterUsername);
    ds1master.setPassword(ds1MasterPassword);
    return ds1master;
  }

  @Bean(name = "ds1Slave")
  public BasicDataSource ds1Slave1() {
    BasicDataSource ds1slave = parentDataSource();
    ds1slave.setUrl(ds1Slave1Url);
    ds1slave.setUsername(ds1Slave1Username);
    ds1slave.setPassword(ds1Slave1Password);
    return ds1slave;
  }

  @Bean
  public SqlSessionFactory sqlSessionWrite() throws Exception {
    final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(ds1Master());
//        WARN. 太过简短的package是否有性能问题?
    sessionFactory.setTypeAliasesPackage("com.onmysky.fuji.server");
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    Resource[] resources = resolver.getResources("classpath:com/onmysky/fuji/server/*/*.xml");
    sessionFactory.setMapperLocations(resources);
    return sessionFactory.getObject();
  }

  @Bean
  public SqlSessionFactory sqlSessionRead() throws Exception {
    final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(ds1Slave1());
    sessionFactory.setTypeAliasesPackage("com.onmysky.fuji.server");
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    Resource[] resources = resolver.getResources("classpath:com/onmysky/fuji/server/*/*.xml");
    sessionFactory.setMapperLocations(resources);
    return sessionFactory.getObject();
  }

  @Bean
  public SqlSessionTemplate writeTpl() throws Exception {
    return new SqlSessionTemplate(sqlSessionWrite());
  }

  @Bean
  public SqlSessionTemplate readTpl() throws Exception {
    return new SqlSessionTemplate(sqlSessionRead());
  }

  @Bean
  public DataSourceTransactionManager transactionManager() {
    return new DataSourceTransactionManager(ds1Master());
  }

  @Bean
  public TransactionTemplate transactionTpl() {
    return new TransactionTemplate(transactionManager());
  }
}
