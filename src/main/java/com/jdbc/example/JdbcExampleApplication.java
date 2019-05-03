package com.jdbc.example;

import java.sql.*;

public class JdbcExampleApplication {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/jdbc-example";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "secret";

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT id, name, prise FROM Product";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int prise = rs.getInt("prise");

                //Display values
                System.out.println("ID: " + id);
                System.out.println(", Name: " + name);
                System.out.println(", Prise: " + prise);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            //finally block used to close resources
            if (stmt != null)
                stmt.close();

        }
        System.out.println("Goodbye!");
    }

//    private static void insertRecordIntoTable() throws SQLException {
//
//        Connection dbConnection = null;
//        PreparedStatement preparedStatement = null;
//
//        String insertTableSQL = "INSERT INTO DBUSER"
//                + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) VALUES"
//                + "(?,?,?,?)";
//
//        try {
//            dbConnection = getDBConnection();
//            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
//
//            preparedStatement.setInt(1, 11);
//            preparedStatement.setString(2, "mkyong");
//            preparedStatement.setString(3, "system");
//            preparedStatement.setTimestamp(4, getCurrentTimeStamp());
//
//            // execute insert SQL stetement
//            preparedStatement.executeUpdate();
//
//            System.out.println("Record is inserted into DBUSER table!");
//
//        } catch (SQLException e) {
//
//            System.out.println(e.getMessage());
//
//        } finally {
//
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//
//            if (dbConnection != null) {
//                dbConnection.close();
//            }
//
//        }
//
//    }
}
