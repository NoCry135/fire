package com.ca.fire.test.string;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestStringBuild {

    private String sqlInsert = "INSERT INTO %s (%s) VALUES (%s)";

    @Test
    public void test01() {
        List<String> list = Arrays.asList("id", "name", "create_time", "is_delete");
        String tb_user = getInsertSQL("tb_user", list);
        System.out.println(tb_user);
    }

    private String getInsertSQL(String tableName, List<String> fieldNames) {
        if (fieldNames == null || fieldNames.size() <= 0) {
            return null;
        }

        StringBuilder fsb = new StringBuilder(10 * fieldNames.size());
        StringBuilder vsb = new StringBuilder(3 * fieldNames.size());
        for (String key : fieldNames) {
            fsb.append(key).append(", ");//2018-8-16liyong,优化sql拼写中列名有mysql关键字的问题,但是小强不支持又调整回去
            vsb.append("?").append(", ");
        }
        fsb.delete(fsb.length() - 2, fsb.length());
        vsb.delete(vsb.length() - 2, vsb.length());

        String sql = String.format(sqlInsert,
                tableName, fsb.toString(), vsb.toString());

        return sql;
    }


}
