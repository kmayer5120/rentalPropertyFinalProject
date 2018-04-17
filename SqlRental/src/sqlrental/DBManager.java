/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlrental;

/*
* Documentation for db
* Data types: https://www.tutorialspoint.com/sqlite/sqlite_data_types.htm
* https://www.tutorialspoint.com/sqlite/sqlite_java.htm
*/

import java.sql.*;
import java.util.HashMap;


public class DBManager
{
          Connection c = null;
 
    
          public DBManager()
          {
        
        
          }
    
          public DBManager(String dbName)
          {
                    //connect to DB upon object creation
                    Connection c = null;
      
                    try {
                       Class.forName("org.sqlite.JDBC");
                       c = DriverManager.getConnection("jdbc:sqlite:test.db");
                    } catch ( SQLException e ) {
                       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                       System.exit(0);
                    } catch (ClassNotFoundException e) {
                              System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                              System.exit(0);
                    }
                    System.out.println("Opened database successfully");
                    
          }

          public String createDB(String dbName)
          {
                    String cmd = "";
                     
                    String url = "jdbc:sqlite:" + dbName;
 
                    try (Connection conn = DriverManager.getConnection(url)) {
                              if (conn != null) 
                              {
                                        DatabaseMetaData meta = conn.getMetaData();
                                        System.out.println("A new database has been created.");
                              }
                    } catch (SQLException e) {
                              System.out.println(e.getMessage());
                    }
                    
                    return cmd;
          }

          public String createTable(String tblName, HashMap<String,String> fields)
          {
                    String cmd = "";
                    Connection c = null;
                    Statement stmt = null;
      
                    try {
                              Class.forName("org.sqlite.JDBC");
                              //change db name
                              c = DriverManager.getConnection("jdbc:sqlite:rentals.db");
                              System.out.println("Opened database successfully");

                              stmt = c.createStatement();
                              
                              String sql = "CREATE TABLE COMPANY " +
                                                  "(ID INT PRIMARY KEY     NOT NULL," +
                                                  " NAME           TEXT    NOT NULL, " + 
                                                  " AGE            INT     NOT NULL, " + 
                                                  " ADDRESS        CHAR(50), " + 
                                                  " SALARY         REAL)"; 
                              stmt.executeUpdate(sql);
                              stmt.close();
                              c.close();
                    } catch ( SQLException e ) {
                              System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                              System.exit(0);
                    } catch (ClassNotFoundException e) {
                              System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                              System.exit(0);
                    }                   
                    
                    return cmd;   
          }

          public String insert(String tblName, HashMap<String,String> fields)
          {
                    String cmd = "";
                    return cmd;
          }

            public String select(String tblName, HashMap<String,String> fields)
            {
                    String cmd = "";
                    return cmd;
            }  
}
