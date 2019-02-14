package com.myproject.myproject;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;

public class DbHandler {
    private static final String jdbcDriverClassName = "org.postgresql.Driver";
    private static final String SET_SQL = "SET search_path to sample_user;";
    private static final String SELECT_SQL = "SELECT * FROM users";

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

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException, JSONException {
        DbHandler dbHandler = new DbHandler();
        System.out.println(dbHandler.getUsers());
    }

}
