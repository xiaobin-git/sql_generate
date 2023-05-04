package com.boring.sql_generate.service

import com.alibaba.druid.pool.DruidDataSource
import com.boring.sql_generate.config.DBTypeEnum
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FindColumnTypeService {

    @Autowired
    lateinit var dbConnectService: DBConnectService

    fun testFindColumnType(tableName: String) {
        var druidDataSource: DruidDataSource? = dbConnectService.getDruidDataSource() ?: throw Exception("数据源为空")
        when (druidDataSource?.dbType) {
            DBTypeEnum.mysql.name -> print(1)
            DBTypeEnum.oracle.name -> print(2)
            DBTypeEnum.sqlServer.name -> print(3)
        }

    }

}