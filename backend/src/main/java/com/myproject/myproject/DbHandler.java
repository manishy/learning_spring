package com.myproject.myproject;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;
import java.util.Map;

public class DbHandler {
    private static final String jdbcDriverClassName = "org.postgresql.Driver";
    private static final String SET_SQL = "SET search_path to sample_user;";
    private static final String SELECT_SQL = "SELECT * FROM users";
    private static final String INSERT_SQL = "INSERT INTO users (user_name) VALUES (?)";

    public DbHandler() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName(jdbcDriverClassName).newInstance();
    }

    public JSONArray getUsers() throws SQLException, JSONException {
        Connection conn = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = DbConnections.create();
            statement = conn.createStatement();
            statement.execute(SET_SQL);
            ResultSet resultSet = statement.executeQuery(SELECT_SQL);


            JSONArray json = new JSONArray();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            while(resultSet.next()) {
                int numColumns = rsmd.getColumnCount();
                JSONObject obj = new JSONObject();
                for (int i=1; i<=numColumns; i++) {
                    String column_name = rsmd.getColumnName(i);
                    obj.put(column_name, resultSet.getObject(column_name));
                }
                json.put(obj);
            }
            return json;
//            conn.commit();
        } catch (Exception e) {
            DbConnections.rollback(conn);
            throw e;
        }finally {
            DbConnections.closeStatement(statement);
            DbConnections.closeStatement(preparedStatement);
            DbConnections.destroy(conn);
        }
    }

    public void addUser(Map<String, String> user) throws SQLException {
        String userName = user.get("username");
        Connection conn = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = DbConnections.create();
            statement = conn.createStatement();
            statement.execute(SET_SQL);
            preparedStatement = conn.prepareStatement(INSERT_SQL);
            preparedStatement.setString(1, userName);
            preparedStatement.execute();
            System.out.println("Added user ....");
            conn.commit();
        } catch (Exception e) {
            DbConnections.rollback(conn);
            throw e;
        }finally {
            DbConnections.closeStatement(statement);
            DbConnections.closeStatement(preparedStatement);
            DbConnections.destroy(conn);
        }
    }

}
