package com.boring.sql_generate.models;

import com.boring.sql_generate.config.DBTypeEnum;
import lombok.Data;

/**
 * @program: sql_generate
 * @description: 数据库连接配置
 * @author: bin.xiao
 * @create: 2022/2/2 16:52
 **/
@Data
public class DBConnectConfig2 {
    //数据库类型
    private DBTypeEnum dbType;

    private String userName;

    private String passWord;
    //数据库地址
    private String url;
    //驱动
    private String driverClassName;

    private String initialSize;

    private String minIdle;

    private String maxActive;

    private String maxWait;

    private Boolean UseGlobalDataSourceStat;
}
