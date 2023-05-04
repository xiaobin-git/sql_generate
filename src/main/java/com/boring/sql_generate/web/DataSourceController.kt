package com.boring.sql_generate.web

import com.boring.sql_generate.models.DBConnectConfig
import com.boring.sql_generate.service.DBConnectService
import com.boring.sql_generate.service.GenerateSqlService
import com.boring.sql_generate.vo.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dataSource")
class DataSourceController {

    @Autowired
    private lateinit var dbConnectService: DBConnectService

    @Autowired
    private lateinit var generateSqlService: GenerateSqlService

    @GetMapping("/getConnect")
    fun tryGetDBConnect(@ModelAttribute config: DBConnectConfig): Response {
        var druidDataSource = dbConnectService.createDruidDataSource(config)
        println(druidDataSource.url)
        generateSqlService.setDruidDataSource(druidDataSource)
        if (null != druidDataSource) {
            return Response("连接成功")
        } else{
            return Response("连接失败")
        }
    }


}