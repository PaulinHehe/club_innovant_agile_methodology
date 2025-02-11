package models;

import java.util.HashMap;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseModel {
    protected String table;
    protected String idField = "id";
    
    protected Connection connection;
	protected HashMap<String, Object> data;

    public BaseModel() {
        try {
            this.connection = Database.getConnection();
            this.data = new HashMap<String, Object>();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public boolean create(String[] columns, Object[] values) {
        if (columns.length != values.length) return false;

        String sql = "INSERT INTO " + table + " (" + String.join(", ", columns) + ") VALUES (";
        sql += String.join(", ", Collections.nCopies(columns.length, "?")) + ")";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < values.length; i++) {
                stmt.setObject(i + 1, values[i]);
            }
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public ResultSet find(int id) {
        String sql = "SELECT * FROM " + table + " WHERE " + idField + " = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet all() {
        String sql = "SELECT * FROM " + table;
        try {
            Statement stmt = connection.createStatement();
        	return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(int id, String[] columns, Object[] values) {
        if (columns.length != values.length) return false;

        String sql = "UPDATE " + table + " SET ";
        for (String column : columns) sql += column + " = ?, ";
        sql = sql.substring(0, sql.length() - 2) + " WHERE " + idField + " = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) {
                stmt.setObject(i + 1, values[i]);
            }
            stmt.setInt(values.length + 1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM " + table + " WHERE " + idField + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
