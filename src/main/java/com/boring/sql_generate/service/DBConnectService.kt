package com.boring.sql_generate.service

import com.alibaba.druid.pool.DruidDataSource
import com.boring.sql_generate.models.DBConnectConfig

interface DBConnectService {

    //获取数据源
    fun createDruidDataSource(dbConfig: DBConnectConfig) : DruidDataSource

    fun getDruidDataSource(): DruidDataSource?

}