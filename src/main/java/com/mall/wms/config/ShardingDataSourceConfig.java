package com.mall.wms.config;

import com.google.common.collect.Maps;
import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(ShardingConfig.class)
@ConditionalOnProperty({ "sharding.jdbc.data-sources.ds-master.jdbc-url",
        "sharding.jdbc.master-slave-rule.master-data-source-name" })
@MapperScan(basePackages = {"com.mall.wms.mapper"}, sqlSessionTemplateRef = "shardingWmsSqlSessionTemplate")
public class ShardingDataSourceConfig {

    @Autowired
    private ShardingConfig shardingMasterSlaveConfig;

    @Bean(name = "shardingWmsDataSource")
    @Primary
    public DataSource masterSlaveDataSource() throws SQLException {
        Map<String, DataSource> dataSourceMap = Maps.newHashMap();
        dataSourceMap.putAll(shardingMasterSlaveConfig.getDataSources());
        DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap,
                shardingMasterSlaveConfig.getMasterSlaveRule(), Maps.newHashMap());
        System.out.println("masterSlaveDataSource config complete");
        return dataSource;
    }

    @Bean(name = "shardingWmsSqlSessionFactory")
    @Primary
    public SqlSessionFactory musicSqlSessionFactory(@Qualifier("shardingWmsDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "shardingWmsTransactionManager")
    @Primary
    public DataSourceTransactionManager musicTransactionManager(@Qualifier("shardingWmsDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "shardingWmsSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate musicSqlSessionTemplate(@Qualifier("shardingWmsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
