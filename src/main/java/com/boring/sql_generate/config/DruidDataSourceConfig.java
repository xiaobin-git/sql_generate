package com.boring.sql_generate.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Primary
public class DruidDataSourceConfig {

    @Bean
    public DataSource dataSource () {
        return new DruidDataSource();
    }

/*    @Bean(name = "springDruidDataSource")
    @ConfigurationProperties("spring.datasource") // 该注解可以自动注入对象的属性(对应配置文件spring.datasource下的属性)
    public DruidDataSource newsPoiDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        List<Filter> filterList = new ArrayList<>();
        filterList.add(wallFilter());
        filterList.add(statFilter());
        druidDataSource.setProxyFilters(filterList);
        return druidDataSource;
    }*/

    /*@Bean
    public WallFilter wallFilter() {
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig());
        return wallFilter;
    }

    @Bean
    public StatFilter statFilter(){
        return new StatFilter();
    }

    @Bean
    public WallConfig wallConfig() {
        WallConfig config = new WallConfig();
        config.setMultiStatementAllow(true);//允许一次执行多条语句
        return config;
    }*/

}
