package com.framework.learning.mysql.mysharding.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.framework.learning.mysql.mysharding.datasource.TulingMultiDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源的配置，作为配置
 * @author wanglu
 * @date 2019/12/31
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({TulingDruidProperties.class, TulingDsRoutingSetProperties.class})
public class DataSourceConfiguration {

    @Autowired
    private TulingDsRoutingSetProperties tulingDsRoutingSetProperties;
    @Autowired
    private TulingDruidProperties tulingDruidProperties;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid00")
    public DataSource dataSource00() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(tulingDruidProperties.getDruid00username());
        dataSource.setPassword(tulingDruidProperties.getDruid00password());
        dataSource.setUrl(tulingDruidProperties.getDruid00jdbcUrl());
        dataSource.setDriverClassName(tulingDruidProperties.getDruid00driverClass());
        return dataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid01")
    public DataSource dataSource01() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(tulingDruidProperties.getDruid01username());
        dataSource.setPassword(tulingDruidProperties.getDruid01password());
        dataSource.setUrl(tulingDruidProperties.getDruid01jdbcUrl());
        dataSource.setDriverClassName(tulingDruidProperties.getDruid01driverClass());
        return dataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid02")
    public DataSource dataSource02() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(tulingDruidProperties.getDruid02username());
        dataSource.setPassword(tulingDruidProperties.getDruid02password());
        dataSource.setUrl(tulingDruidProperties.getDruid02jdbcUrl());
        dataSource.setDriverClassName(tulingDruidProperties.getDruid02driverClass());
        return dataSource;
    }

    @Bean("tulingMultiDatasource")
    public TulingMultiDataSource dataSource() {
        TulingMultiDataSource tulingMultiDataSource = new TulingMultiDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("dataSource00", dataSource00());
        targetDataSources.put("dataSource01", dataSource01());
        targetDataSources.put("dataSource02", dataSource02());
        //把多个数据源和多数据进行关联
        tulingMultiDataSource.setTargetDataSources(targetDataSources);
        tulingMultiDataSource.setDefaultTargetDataSource(dataSource00());

        Map<Integer, String> setMappings = new HashMap<>();
        setMappings.put(0, "dataSource00");
        setMappings.put(1, "dataSource01");
        setMappings.put(2, "dataSource02");
        tulingDsRoutingSetProperties.setDataSourceKeysMapping(setMappings);

        return tulingMultiDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("tulingMultiDatasource") TulingMultiDataSource tulingMultiDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(tulingMultiDataSource);
        //sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("tulingMultiDatasource") TulingMultiDataSource tulingMultiDataSource) {
        return new DataSourceTransactionManager(tulingMultiDataSource);
    }

}
