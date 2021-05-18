package com.sika.code.database.common.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.sika.code.basic.constant.PropertyConstant;
import com.sika.code.database.mysql.interceptor.MyBatisSqlLogInterceptor;
import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author daiqi
 */
@Configuration
@EnableTransactionManagement
@ConditionalOnProperty(value = PropertyConstant.JDBC_FIRE, havingValue = "true")
@ConfigurationProperties(prefix = "mybatis")
@Data
public class DataBaseConfig {
    /** 映射器位置 *///
    private String mapperLocations;
    private boolean banner = true;
    @Autowired(required = false)
    private MetaObjectHandler metaObjectHandler;

   /* @Bean
    @ConditionalOnMissingBean
    public SqlLogInterceptorProperties sqlLogInterceptorProperties() {
        return new SqlLogInterceptorProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlLogInterceptor sqlLogInterceptor() {
        return new SqlLogInterceptor().openLog(sqlLogInterceptorProperties().getFire());
    }*/

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MyBatisSqlLogInterceptor mybatisPlusInterceptor = new MyBatisSqlLogInterceptor();
        return mybatisPlusInterceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = GlobalConfigUtils.defaults();
        globalConfig.setBanner(banner);
        globalConfig.setMetaObjectHandler(metaObjectHandler);
        return globalConfig;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        System.out.println(dataSource);
        // 添加XML目录
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setGlobalConfig(globalConfig());
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
            sessionFactory.setPlugins(mybatisPlusInterceptor());
            return sessionFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
