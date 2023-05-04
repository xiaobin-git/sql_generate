package com.boring.sql_generate.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.JdbcUtils;
import com.boring.sql_generate.config.DBTypeEnum;
import com.boring.sql_generate.util.CollectionUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @program: sql_generate
 * @description:
 * @author: bin.xiao
 * @create: 2022/1/21 17:50
 **/
@Service
public class GenerateSqlService {

    @Autowired
    private DBConnectService dbConnectService;

    private DruidDataSource druidDataSource = null;

    public void setDruidDataSource(DruidDataSource druidDataSource) {
        this.druidDataSource = druidDataSource;
    }

    public String getInsertSql(String tableName, Collection<Long> ids, LinkedHashSet<String> identityFields) throws SQLException {
        String sql = "SELECT * FROM " + tableName + String.format(" WHERE id in (%s)", Strings.join(ids, ','));
        List<Map<String, Object>> queryResults = JdbcUtils.executeQuery(druidDataSource, sql);
        //按主键删除
        boolean hasIdentityField = CollectionUtil.isNotEmpty(identityFields);
        StringBuilder resultBuilder = new StringBuilder();
        for (Map<String, Object> queryResult : queryResults) {
            StringBuilder deleteSqlBuilder = new StringBuilder(String.format("DELETE FROM %s WHERE ", tableName));
            StringBuilder insertSqlBuilder = new StringBuilder(String.format("INSERT INTO %s (", tableName));
            StringBuilder valueSqlBuilder = new StringBuilder("(");
            for (String column : queryResult.keySet()) {
                if (column.equalsIgnoreCase("ID")) continue;
                insertSqlBuilder.append(column).append(","); //拼insert语句的字段
                Object value = queryResult.get(column);
                String valueSql = turnValueSql(value);
                valueSqlBuilder.append(valueSql).append(","); //拼insert语句的value
                if (hasIdentityField && CollectionUtil.containsIgnoreCase(identityFields, column)) {
                    deleteSqlBuilder.append(column).append("=").append(valueSql).append(" AND ");
                }
            }
            valueSqlBuilder.deleteCharAt(valueSqlBuilder.lastIndexOf(","));
            valueSqlBuilder.append(");");
            insertSqlBuilder.deleteCharAt(insertSqlBuilder.lastIndexOf(","));
            insertSqlBuilder.append(") VALUES ").append(valueSqlBuilder);
            if (hasIdentityField) {
                deleteSqlBuilder.delete(deleteSqlBuilder.lastIndexOf("AND"), deleteSqlBuilder.length());
                deleteSqlBuilder.append(";");
                System.out.println(deleteSqlBuilder);
                resultBuilder.append(deleteSqlBuilder).append("\n");
            }
            resultBuilder.append(insertSqlBuilder).append("\n");
        }
        return resultBuilder.toString();
    }

    //得到value里的sql
    private String turnValueSql(Object value) {
        String valueSql = "";
        DBTypeEnum databaseType = getDatabaseType();
        if (null == value) {
            valueSql = " NULL";
        } else if (value instanceof String) {
            valueSql = String.format("'%s'", value.toString());
        } else if (value instanceof BigDecimal) {
            valueSql = String.format("'%s'", value.toString());
        } else if (value instanceof Date) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = simpleDateFormat.format((Date) value);
            if (DBTypeEnum.oracle.equals(databaseType)) {
                valueSql = String.format("to_date('%s', 'yyyy-MM-dd')", dateStr);
            } else {
                valueSql = String.format("'%s'", dateStr);
            }
        } else if (value instanceof LocalDateTime) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = Date.from(((LocalDateTime) value).atZone( ZoneId.systemDefault()).toInstant());
            String dateStr = simpleDateFormat.format(date);
            valueSql = String.format("'%s'", dateStr);
        } else {
            valueSql = String.format("%s", value.toString());
        }
        return valueSql;
    }

    public DBTypeEnum getDatabaseType() {
        String dbType = druidDataSource.getDbType();
        //todo 待实现
        return DBTypeEnum.mysql;
    }

}
