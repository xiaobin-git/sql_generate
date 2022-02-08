package com.boring.sql_generate.web;

import com.boring.sql_generate.service.GenerateSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @program: sql_generate
 * @description: 生成sql
 * @author: bin.xiao
 * @create: 2022/1/21 20:46
 **/
@RestController
@RequestMapping("/generate")
public class GenerateSqlController {

    @Autowired
    private GenerateSqlService generateSqlService;

    @GetMapping("/generateSql")
    public String generateSql(@RequestParam String tableName, @RequestParam Collection<Long> ids,
                              @RequestParam Collection<String> fieldNames) throws SQLException {
        String insertSql = generateSqlService.getInsertSql(tableName, ids, new LinkedHashSet<>(fieldNames));
        return insertSql;
    }
}
