package com.boring.sql_generate.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @program: sql_generate
 * @description:
 * @author: bin.xiao
 * @create: 2022/1/21 18:04
 **/
public class CollectionUtil {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean containsIgnoreCase(Collection<String> collection, String value) {
        String upperCase = value.toUpperCase();
        String match = collection.stream().map(String::toUpperCase).filter(s -> s.equals(upperCase))
                .findAny().orElse(null);
        return null != match;
    }

}
