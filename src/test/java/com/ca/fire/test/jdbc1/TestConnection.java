package com.ca.fire.test.jdbc1;

import org.junit.Test;

import java.sql.*;

public class TestConnection {

    @Test
    public void test01() throws SQLException {
//        Driver driver = new com.mysql.jdbc.Driver();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fire", "root", "root");
        System.out.println(connection);
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from tb_user where id in (1,2)");
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
            //索引从1开始
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(resultSet.getString(i) + "\t");
            }
        }
        System.out.println(metaData);

        long l = statement.executeLargeUpdate("update tb_user set tel_phone = '1350098' where id = 8 ");
        System.out.println(l);
    }

    @Test
    public void test02() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fire", "root", "root");
        PreparedStatement prepareStatement = connection.prepareStatement("select * from tb_user where id in (?,?)");
        prepareStatement.setInt(1, 1);
        prepareStatement.setInt(2, 2);
        ResultSet resultSet = prepareStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
            //索引从1开始
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(resultSet.getString(i) + "\t");
            }
        }

        PreparedStatement statement = connection.prepareStatement("update tb_user set tel_phone =? where id = ? ");
        statement.setString(1, "222222");
        statement.setLong(2, 2);
        int i = statement.executeUpdate();
        System.out.println(i);

//        long l = statement.executeLargeUpdate("update tb_user set tel_phone = '1350098' where id = 8 ");
//        System.out.println(l);
    }

    @Test
    public void test03() {
//        SqlSessionFactory object = sqlSessionFactoryBean.getObject();
//        System.out.println(dataSource);
//
//        Connection conn = dataSource.getConnection();
//
//        ResultSet rs = conn.getMetaData().getPrimaryKeys(null, null, "co_putaway_task_m");
//
//        while (rs.next()) {
//            String column = rs.getString(RST_COLUMN_NAME);
//            Short seq = rs.getShort(RST_KEY_SEQ);
//            System.out.println(column + "=" + seq);
//        }
//
//        ResultSet tableRs = dataSource.getConnection().getMetaData().getTables(null, null, null, new String[]{"TABLE"});
//        StringBuffer sbTables = new StringBuffer();
//        ArrayList<String> list = new ArrayList<>();
//        while (tableRs.next()) {// ///TABLE_TYPE/REMARKS
//            sbTables.append("表名：" + tableRs.getString("TABLE_NAME") + "<br/>");
//            sbTables.append("表类型：" + tableRs.getString("TABLE_TYPE") + "<br/>");
//            sbTables.append("表所属数据库：" + tableRs.getString("TABLE_CAT") + "<br/>");
//            sbTables.append("表所属用户名：" + tableRs.getString("TABLE_SCHEM") + "<br/>");
//            sbTables.append("表备注：" + tableRs.getString("REMARKS") + "<br/>");
//            sbTables.append("------------------------------<br/>");
//            list.add(tableRs.getString("TABLE_NAME"));
//        }
//        System.out.println(list);
//
//        // 2、遍历数据库表，获取各表的字段等信息
//        StringBuffer sbCloumns = new StringBuffer();
//        for (String tableName : tables) {
//            String sql = "select * from " + tableName;
//            try {
//                PreparedStatement ps = connection.prepareStatement(sql);
//                ResultSet rs = ps.executeQuery();
//                ResultSetMetaData meta = rs.getMetaData();
//                int columeCount = meta.getColumnCount();
//                sbCloumns.append("表 " + tableName + "共有 " + columeCount + " 个字段。字段信息如下：<br/>");
//                for (int i = 1; i < columeCount + 1; i++) {
//                    sbCloumns.append("字段名：" + meta.getColumnName(i) + "<br/>");
//                    sbCloumns.append("类型：" + meta.getColumnType(i) + "<br/>");
//                    sbCloumns.append("------------------------------<br/>");
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            sbCloumns.append("------------------------------<br/>");
//        }
//
//        // 从数据库取主键信息
//        try (Connection conn = connection;
//             ResultSet rs = conn.getMetaData().getPrimaryKeys(null, null, "")) {
//            while (rs.next()) {
//                String column = rs.getString(RST_COLUMN_NAME);
//                Short seq = rs.getShort(RST_KEY_SEQ);
//                System.out.println(column + "=" + seq);
//
//            }
//        } catch (SQLException e) {
//
//        }
    }

    private static final String RST_COLUMN_NAME = "COLUMN_NAME";
    private static final String RST_INDEX_NAME = "INDEX_NAME";
    private static final String RST_KEY_SEQ = "KEY_SEQ";

}
