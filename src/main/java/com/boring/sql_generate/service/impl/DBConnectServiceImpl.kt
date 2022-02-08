package com.boring.sql_generate.service.impl

import com.alibaba.druid.filter.Filter
import com.alibaba.druid.filter.stat.StatFilter
import com.alibaba.druid.pool.DruidDataSource
import com.alibaba.druid.wall.WallFilter
import com.boring.sql_generate.models.DBConnectConfig
import com.boring.sql_generate.service.DBConnectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DBConnectServiceImpl: DBConnectService {

    /*@Autowired
    private lateinit var wallFilter: WallFilter
    @Autowired
    private lateinit var statFilter: StatFilter*/

    override fun getDruidDataSource(dbConfig: DBConnectConfig): DruidDataSource {
        val druidDataSource = DruidDataSource()
        druidDataSource.username = dbConfig.userName
        druidDataSource.password = dbConfig.passWord
        druidDataSource.url = dbConfig.url
        val filterList = ArrayList<Filter>()
       /* filterList.add(wallFilter)
        filterList.add(statFilter)*/
        druidDataSource.proxyFilters = filterList;
        return druidDataSource
    }

}