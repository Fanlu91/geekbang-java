package com.fanlu.jdbc;

import java.sql.*;
import java.util.*;


public class MysqlJdbc {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    private void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println();
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find mysql jdbc driver");
            e.printStackTrace();
            return;
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://148.70.112.16:3306/test", "fanlu", "qwe123asd");
            if (connection != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Connection failed!");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }

    public boolean insert(String table, String column, List<Object> values) {
        try {
            String insertTemplate = buildInsertSqlTemplate(table, column, values.size());
            preparedStatement = connection.prepareStatement(insertTemplate);
            for (int i = 0; i < values.size(); i++) {
                preparedStatement.setObject(i + 1, values.get(i));
            }

            System.out.println(preparedStatement.toString());

            preparedStatement.execute();
            System.out.println("Insert successfully");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String buildInsertSqlTemplate(String table, String column, int valueAmount) {
        StringBuilder insert = new StringBuilder("insert into " + table + " " + column + " values (?");
        for (int i = 0; i < valueAmount - 1; i++) {
            insert.append(",?");
        }
        insert.append(")");
        return insert.toString();
    }

    public List<Map<String, Object>> query(String table, Map<String, Object> values, String condition) {
        try {
            String sqlTemplate = buildQuerySqlTemplate(table, values, condition);
            System.out.println("query " + sqlTemplate);
            preparedStatement = connection.prepareStatement(sqlTemplate);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Map<String, Object>> list = new ArrayList<>();
            while (resultSet.next()) {
                for (String key : values.keySet()) {
                    values.put(key, resultSet.getObject(key));
                }
                list.add(new HashMap<>(values));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String buildQuerySqlTemplate(String table, Map<String, Object> values, String condition) {
        String sqlTemplate = "select " + values.keySet().toString().substring(1,
                values.keySet().toString().length() - 1) + " from " + table;
        if (condition != null) {
            sqlTemplate += " where " + condition;
        }
        return sqlTemplate;
    }

    private void close() throws SQLException {
        preparedStatement.close();
        connection.close();
        System.out.println("Connection close");
    }

    public static void main(String[] args) {
        Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("name", "sensor_10");
        valuesMap.put("id", 100);
        valuesMap.put("tmp", 100);
//        System.out.println(valuesMap.keySet().toString().substring(1, valuesMap.keySet().toString().length() - 1));

        try {
            MysqlJdbc mysqlJdbc = new MysqlJdbc();
            mysqlJdbc.createConnection();

            String table = "sensor";
//            String columns = "(name, id, tmp)";
//            List<Object> values = Arrays.asList("sensor_10", 10, 10);
//            mysqlJdbc.insert(table, columns, values);

            List<Map<String, Object>> results = mysqlJdbc.query(table, valuesMap, null);
            for (Map r : results) {
                System.out.println(r.toString());
            }
            mysqlJdbc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
