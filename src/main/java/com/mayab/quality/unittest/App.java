package com.mayab.quality.unittest;

import java.sql.*;

import com.mayab.quality.loginunittest.dao.UserMysqlDAO;
import com.mayab.quality.loginunittest.model.User;

class App {
    public static void main(String args[]) throws SQLException {
        /*
         * Connection con = null;
         * try {
         * Class.forName("com.mysql.cj.jdbc.Driver");
         * String dbURL = "jdbc:mysql://localhost:3307/calidad2024";
         * System.out.println("jdbcurl=" + dbURL);
         * String strUserID = "root";
         * String strPassword = "123456";
         * con = DriverManager.getConnection(dbURL, strUserID, strPassword);
         * System.out.println("Connected to the database.");
         * Statement stmt = con.createStatement();
         * System.out.println("Executing query");
         * ResultSet rs = stmt.executeQuery("SELECT 1 FROM DUAL");
         * while (rs.next())
         * System.out.println(rs.getInt("1"));
         * con.close();
         * } catch (Exception e) {
         * System.out.println(e);
         * } finally {
         * con.close();
         * }
         */
        UserMysqlDAO dao = new UserMysqlDAO();
        dao.registerUser(new User("Elias", "elias@anahuac.mx", "chi"));

    }

}