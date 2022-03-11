package com.boring.sql_generate.web

import com.boring.sql_generate.models.DBConnectConfig
import com.boring.sql_generate.service.DBConnectService
import com.boring.sql_generate.service.GenerateSqlService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {

    @Autowired
    private lateinit var dbConnectService: DBConnectService

    @Autowired
    private lateinit var generateSqlService: GenerateSqlService

    @GetMapping("/getConnect")
    fun tryGetDBConnect(@ModelAttribute config: DBConnectConfig): String {
        println("哦耶")
        var druidDataSource = dbConnectService.getDruidDataSource(config)
        println(druidDataSource.url)
        generateSqlService.setDruidDataSource(druidDataSource)
        return druidDataSource.dbType
    }


}