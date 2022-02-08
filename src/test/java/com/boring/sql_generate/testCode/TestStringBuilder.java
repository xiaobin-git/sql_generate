package com.boring.sql_generate.testCode;

/**
 * @program: sql_generate
 * @description:
 * @author: bin.xiao
 * @create: 2022/1/21 21:14
 **/
public class TestStringBuilder {

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder(" where 1=1 and 2=2 and ");
        stringBuilder.delete(stringBuilder.lastIndexOf("and"), stringBuilder.length());
        System.out.println(stringBuilder);
    }
}
