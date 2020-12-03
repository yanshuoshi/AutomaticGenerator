package com.yss.system;
import com.yss.pdm.Column;
import com.yss.pdm.Table;
import com.yss.utils.FreemarkerUtils;
import com.yss.utils.NameConverter;
import com.yss.utils.SqlType2JavaType;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Connection1 {
    static final String driver = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://127.0.0.1:3306/gateway_manage?useUnicode=true&characterEncoding=UTF-8&createDatabaseIfNotExist=true";
    static final String user = "root";
    static final String password = "root";

    //jdbc链接数据库,获取表名，字段名和数据
        public static void main(String[] args) throws Exception {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            if (!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            else
                System.err.println("connect filed");

            List<Table> tables = getTables(conn);

            System.out.printf("tables-------"+tables.size());
            printAllInfo(tables);
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
    private static List<Table> getTables(Connection conn) throws SQLException {
            List<Table> list = new ArrayList<>();
            DatabaseMetaData dbMetData = conn.getMetaData();
            // mysql convertDatabaseCharsetType null
            ResultSet rs = dbMetData.getTables(null,
                    convertDatabaseCharsetType("root", "mysql"), null,
                    new String[] { "TABLE", "VIEW" });
            String[] types = {"TABLE"};
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                String remarks = rs.getString("REMARKS");
                Table table = new Table();
                table.setCode(tableName);
                table.setName(remarks);
                table.setColumns(getColumnList(tableName));
                System.out.println("添加表——————"+tableName+"——————————"+"\n");
                list.add(table);
            }
            return list;
        }
    public static ArrayList<Column> getColumnList(String tableName) {

        Connection conn = null;
        DatabaseMetaData dbMetaData = null;
        ArrayList<Column> ret = new ArrayList<Column>();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            dbMetaData = conn.getMetaData();
            ResultSet rs = dbMetaData.getColumns(null, null, tableName, "%");
            while (rs.next()) {
                String tableCat = rs.getString("TABLE_CAT");

                String tableSchemaName = rs.getString("TABLE_SCHEM");

                String tableName_ = rs.getString("TABLE_NAME");

                String columnName = rs.getString("COLUMN_NAME");

                int dataType = rs.getInt("DATA_TYPE");

                String dataTypeName = rs.getString("TYPE_NAME");

                int columnSize = rs.getInt("COLUMN_SIZE");

                int decimalDigits = rs.getInt("DECIMAL_DIGITS");

                int numPrecRadix = rs.getInt("NUM_PREC_RADIX");

                int nullAble = rs.getInt("NULLABLE");

                String remarks = rs.getString("REMARKS");

                String columnDef = rs.getString("COLUMN_DEF");

                int sqlDataType = rs.getInt("SQL_DATA_TYPE");

                int sqlDatetimeSub = rs.getInt("SQL_DATETIME_SUB");

                int charOctetLength = rs.getInt("CHAR_OCTET_LENGTH");

                int ordinalPosition = rs.getInt("ORDINAL_POSITION");

                String isNullAble = rs.getString("IS_NULLABLE");

                String isAutoincrement = rs.getString("IS_AUTOINCREMENT");


                Column column = new Column();
                column.setCode(columnName);
                column.setFieldName(columnName);
                column.setName(remarks);

                String  pk = getAllPrimaryKeys(tableName);
                if (StringUtils.isNotBlank(pk) && StringUtils.equals(columnName,pk)) {
                    column.setPkFlag(true);
                }

                column.setType(dataTypeName);

                ret.add(column);
            }

            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public static String getAllPrimaryKeys(String tableName) {
        try {

            Connection conn = null;
            DatabaseMetaData dbMetaData = null;
            ArrayList<Column> ret = new ArrayList<Column>();
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            dbMetaData = conn.getMetaData();
            ResultSet rs = dbMetaData.getPrimaryKeys(null, null, tableName);
            if (!(rs.next()))
                return null;
            String columnName = rs.getString("COLUMN_NAME");
            // short keySeq = rs.getShort("KEY_SEQ");
            // String pkName = rs.getString("PK_NAME");
            if (conn != null) {
                conn.close();
            }

            return columnName;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void printAllInfo(List<Table> retlist) throws Exception {
        String prefix = "iot_";
        createEntity(retlist, prefix + "entity.ftl", "com.timon.yss.entity");
        createMyBatisXML(retlist, prefix + "mybatis.ftl", "com.timon.yss.domain");
        createMapper(retlist, prefix + "mapper.ftl", "com.timon.yss.mapper", "Mapper");
        createEntityParam(retlist, prefix + "param.ftl", "com.timon.yss.param");
        createDao(retlist, prefix + "service.ftl", "com.timon.yss.service", "Service");
        createDao(retlist, prefix + "service_impl.ftl", "com.timon.yss.service.impl", "ServiceImpl");
        System.out.printf("SUCCESS");
    }
    public static void createEntity(List<Table> retlist, String freemarker, String packageName) throws Exception {
        for (Table tableInfo : retlist) {
            Map<String, Object> map = new HashMap<String, Object>();

            //	String tablename = StringUtils.remove(tableInfo.getCode(), "t_");
            String tablename = tableInfo.getCode();
            String className = NameConverter.toJavaCase(tablename);
            map.put("table", tableInfo);
            map.put("className", className);
            map.put("packageName", packageName); //variablelist

            // System.err.println("表名"+tablename+"--------"+tableInfo.getColumns());

            ArrayList<Column> columnList = new ArrayList<>();

            try {
                for (Column column : tableInfo.getColumns()) {
                    column.setFieldName(column.getCode());
                    column.setCode(NameConverter.toJavaCase(column.getCode()));
                    column.setType(SqlType2JavaType.sqlType2JavaType(column.getType()));
                    columnList.add(column);
                }
            } catch (Exception e) {
                // TODO: handle exception

                System.err.println("表" + tableInfo);
                e.printStackTrace();
            }

            map.put("variablelist", columnList);

            String path = packageName.replace(".", "/");
            FreemarkerUtils.fprint(freemarker, map, path, StringUtils.capitalize(className).concat(".java"));
        }
    }

    public static void createMyBatisXML(List<Table> retlist, String freemarker, String packageName) throws Exception {
        for (Table tableInfo : retlist) {

			/*if (!StringUtils.contains(tableInfo.getCode(), "t_oauth_user")) {
                continue;
			}*/

            Map<String, Object> map = new HashMap<String, Object>();
            //String tablename = StringUtils.remove(tableInfo.getCode(), "t_");
            String tablename = tableInfo.getCode();
            String className = NameConverter.toJavaCase(tablename);
            map.put("table", tableInfo);
            map.put("className", className);
            map.put("packageName", packageName); //variablelist
            map.put("ognl", "com.zhihe.classroom.common.mybatis.MybatisOgnl"); //variablelist

            ArrayList<Column> columnList = new ArrayList<>();
            try {
                for (Column column : tableInfo.getColumns()) {

                    column.setCode(NameConverter.toJavaCase(column.getFieldName()));
                    column.setType(SqlType2JavaType.sqlType2JavaType(column.getType()));
                    column.setFieldName(NameConverter.toDbCase(column.getFieldName()));

                    if (StringUtils.isNotBlank(column.getPkFlag() + "") && StringUtils.contains(column.getPkFlag() + "", "true")) {
                        map.put("pkgFieldName", column.getFieldName());
                        map.put("pkgCodeName", column.getCode());
                    }

                    columnList.add(column);
                }
            } catch (Exception e) {
                // TODO: handle exception
                System.err.println("表" + tableInfo);
                e.printStackTrace();
            }
            map.put("variablelist", columnList);
            String path = "dao/sqlmap";
            FreemarkerUtils.fprint(freemarker, map, path, StringUtils.capitalize(className).concat("Mapper.xml"));
        }
    }

    public static void createMapper(List<Table> retlist, String freemarker, String packageName, String fileName) throws Exception {
        for (Table tableInfo : retlist) {
            Map<String, Object> map = new HashMap<String, Object>();

            String tablename = tableInfo.getCode();
            String className = NameConverter.toJavaCase(tablename);
            map.put("table", tableInfo);
            map.put("className", className);
            map.put("packageName", packageName); //variablelist

            // System.err.println("表名"+tablename+"--------"+tableInfo.getColumns());

            ArrayList<Column> columnList = new ArrayList<>();

            try {
                for (Column column : tableInfo.getColumns()) {
                    column.setFieldName(column.getCode());
                    column.setCode(NameConverter.toJavaCase(column.getCode()));
                    column.setType(SqlType2JavaType.sqlType2JavaType(column.getType()));
                    columnList.add(column);
                }
            } catch (Exception e) {
                // TODO: handle exception

                System.err.println("表" + tableInfo);
                e.printStackTrace();
            }

            map.put("variablelist", columnList);

            String path = packageName.replace(".", "/");
            FreemarkerUtils.fprint(freemarker, map, path, StringUtils.capitalize(className + fileName).concat(".java"));

        }
    }


    public static void createDao(List<Table> retlist, String freemarker, String packageName, String fileName) throws Exception {
        for (Table tableInfo : retlist) {
            Map<String, Object> map = new HashMap<String, Object>();

            String tablename = tableInfo.getCode();
            String className = NameConverter.toJavaCase(tablename);
            map.put("table", tableInfo);
            map.put("className", className);
            map.put("packageName", packageName); //variablelist

            // System.err.println("表名"+tablename+"--------"+tableInfo.getColumns());

            ArrayList<Column> columnList = new ArrayList<>();

            try {
                for (Column column : tableInfo.getColumns()) {
                    column.setFieldName(column.getCode());
                    column.setCode(NameConverter.toJavaCase(column.getCode()));
                    column.setType(SqlType2JavaType.sqlType2JavaType(column.getType()));
                    columnList.add(column);
                }
            } catch (Exception e) {
                // TODO: handle exception

                System.err.println("表" + tableInfo);
                e.printStackTrace();
            }

            map.put("variablelist", columnList);

            String path = packageName.replace(".", "/");
            FreemarkerUtils.fprint(freemarker, map, path, StringUtils.capitalize(className + fileName).concat(".java"));

        }
    }

    public static void createEntityParam(List<Table> retlist, String freemarker, String packageName) throws Exception {
        for (Table tableInfo : retlist) {
            Map<String, Object> map = new HashMap<String, Object>();

            //	String tablename = StringUtils.remove(tableInfo.getCode(), "t_");
            String tablename = tableInfo.getCode();
            String className = NameConverter.toJavaCase(tablename);
            map.put("table", tableInfo);
            map.put("className", className);
            map.put("packageName", packageName); //variablelist

            // System.err.println("表名"+tablename+"--------"+tableInfo.getColumns());

            ArrayList<Column> columnList = new ArrayList<>();

            try {
                for (Column column : tableInfo.getColumns()) {
                    column.setFieldName(column.getCode());
                    column.setCode(NameConverter.toJavaCase(column.getCode()));
                    column.setType(SqlType2JavaType.sqlType2JavaType(column.getType()));
                    columnList.add(column);
                }
            } catch (Exception e) {
                // TODO: handle exception

                System.err.println("表" + tableInfo);
                e.printStackTrace();
            }

            map.put("variablelist", columnList);

            String path = packageName.replace(".", "/");
            FreemarkerUtils.fprint(freemarker, map, path, StringUtils.capitalize(className).concat("Param.java"));

        }
    }

}
