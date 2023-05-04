package com.boring.sql_generate.web

import com.boring.sql_generate.service.FindColumnTypeService
import com.boring.sql_generate.service.GenerateSqlService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.sql.SQLException

@RestController
@RequestMapping("/generate")
class GenerateSqlController {

    @Autowired
    private val generateSqlService: GenerateSqlService? = null

    @Autowired
    private val findColumnTypeService: FindColumnTypeService? = null

    @GetMapping("/generateSql")
    @Throws(SQLException::class)
    fun generateSql(@RequestParam tableName: String?, @RequestParam ids: Collection<Long?>?,
        @RequestParam fieldNames: Collection<String>?): String? {
        return generateSqlService!!.getInsertSql(tableName, ids, LinkedHashSet(fieldNames))
    }
}