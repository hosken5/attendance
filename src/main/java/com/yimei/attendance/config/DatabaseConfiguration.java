package com.yimei.attendance.config;

import com.yimei.attendance.extension.mapper.LocalDateTimeTypeHandler;
import com.yimei.attendance.extension.mapper.LocalDateTypeHandler;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.yimei.attendance.repository")
public class DatabaseConfiguration implements EnvironmentAware {
    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);
    private RelaxedPropertyResolver dataSourcePropertyResolver;
    private Environment env;

    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
        this.dataSourcePropertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
    }

    @Bean(destroyMethod = "shutdown")
    public DataSource dataSource() {
        log.debug("Configuring Datasource");
        //https://github.com/brettwooldridge/HikariCP/wiki/Configuration
        HikariConfig config = new HikariConfig();
        if (env.acceptsProfiles("prod")) {
            config.setDataSourceClassName(dataSourcePropertyResolver.getProperty("dataSourceClassName"));
            config.addDataSourceProperty("url", dataSourcePropertyResolver.getProperty("url"));
        } else {
            config.setDriverClassName(dataSourcePropertyResolver.getProperty("driverClassName"));
            config.setJdbcUrl(dataSourcePropertyResolver.getProperty("jdbcUrl"));
        }
        config.addDataSourceProperty("user", dataSourcePropertyResolver.getProperty("username"));
        config.addDataSourceProperty("password", dataSourcePropertyResolver.getProperty("password"));
        //MySQL optimizations, see https://github.com/brettwooldridge/HikariCP/wiki/MySQL-Configuration
        config.addDataSourceProperty("cachePrepStmts", dataSourcePropertyResolver.getProperty("cachePrepStmts", "true"));
        config.addDataSourceProperty("prepStmtCacheSize", dataSourcePropertyResolver.getProperty("prepStmtCacheSize", "250"));
        config.addDataSourceProperty("prepStmtCacheSqlLimit", dataSourcePropertyResolver.getProperty("prepStmtCacheSqlLimit", "2048"));
        return new HikariDataSource(config);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("/mybatis/**/*Mapper.xml"));
        sessionFactory.setTypeAliasesPackage("com.yimei.attendance.entity");
        sessionFactory.setTypeHandlers(new TypeHandler<?>[]{new LocalDateTimeTypeHandler(), new LocalDateTypeHandler()});
        return sessionFactory.getObject();
    }

}
