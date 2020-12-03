package com.yss.system;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class connection {


    //jdbc链接数据库,获取表名，字段名和数据
        public static void main(String[] args) throws Exception {

            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://172.16.16.9:3306/nutzsite?useUnicode=true&characterEncoding=UTF-8&createDatabaseIfNotExist=true";
            String user = "root";
            String password = "Admin123!";

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            if (!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            else
                System.err.println("connect filed");

            List<String> tables = getTables(conn);

            conn.close();

        }

        public static String convertDatabaseCharsetType(String in, String type) {
            String dbUser;
            if (in != null) {
                if (type.equals("oracle")) {
                    dbUser = in.toUpperCase();
                } else if (type.equals("postgresql")) {
                    dbUser = "public";
                } else if (type.equals("mysql")) {
                    dbUser = null;
                } else if (type.equals("mssqlserver")) {
                    dbUser = null;
                } else if (type.equals("db2")) {
                    dbUser = in.toUpperCase();
                } else {
                    dbUser = in;
                }
            } else {
                dbUser = "public";
            }
            return dbUser;
        }

        private static List<String> getTables(Connection conn) throws SQLException {
            List<String> list = new ArrayList<>();
            DatabaseMetaData dbMetData = conn.getMetaData();
            ResultSet rs = dbMetData.getTables(null,
                    convertDatabaseCharsetType("root", "mysql"), null,
                    new String[] { "TABLE", "VIEW" });

            while (rs.next()) {
                if (rs.getString(4) != null
                        && (rs.getString(4).equalsIgnoreCase("TABLE") || rs
                        .getString(4).equalsIgnoreCase("VIEW"))) {
                    String tableName = rs.getString(3).toLowerCase();
                    list.add(tableName);
                    System.out.print("!!!!!!!!!!!!!!!!!!!!!!"+tableName +"!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+ "\t");
                    // 根据表名提前表里面信息：
                    ResultSet colRet = dbMetData.getColumns(null, "%", tableName,
                            "%");
                    while (colRet.next()) {
                        String columnName = colRet.getString("COLUMN_NAME");
                        String columnType = colRet.getString("TYPE_NAME");
                        int datasize = colRet.getInt("COLUMN_SIZE");
                        int digits = colRet.getInt("DECIMAL_DIGITS");
                        int nullable = colRet.getInt("NULLABLE");
                         System.out.println(columnName + "%%" + columnType + "%%"+
                         datasize + "%%" + digits + "%%" + nullable);
                    }

                }
            }
            return list;
        }

    }
