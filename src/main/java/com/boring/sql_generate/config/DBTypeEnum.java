package com.boring.sql_generate.config;

/**
 * @program: sql_generate
 * @description: 数据库类型
 * @author: bin.xiao
 * @create: 2022/2/2 16:55
 **/
public enum DBTypeEnum {

    mysql("com.mysql.jdbc.Driver"),
    oracle("oracle.jdbc.driver.OracleDriver"),
    sqlServer("com.microsoft.sqlserver.jdbc.SQLServerDriver");

    DBTypeEnum(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    private String driverClassName;

    public String getDriverClassName() {
        return this.driverClassName;
    }
}
