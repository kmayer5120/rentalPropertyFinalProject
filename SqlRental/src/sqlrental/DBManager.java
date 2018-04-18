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

                              String sql =
                                        " CREATE TABLE Tenants\n" +
                                        "(\n" +
                                        "    tenantID INT NOT NULL GENERATED ALWAYS AS IDENTITY, \n" +
                                        "    firstName varchar(50) NOT NULL, \n" +
                                        "    lastName varchar(50) NOT NULL,\n" +
                                        "    PRIMARY KEY (tenantID)\n" +
                                        ");\n" +
                                        "\n" +
                                        "CREATE TABLE Properties\n" +
                                        "(\n" +
                                        "    propertyID varchar(50) NOT NULL,\n" +
                                        "    propertyAddress varchar(255) NOT NULL,\n" +
                                        "    propertyDescription varchar(255) NOT NULL,\n" +
                                        "    isAvailable char(1) NOT NULL,\n" +
                                        "    isLate char(1), NOT NULL,\n" +
                                        "    isEvicted char(1), NOT NULL,\n" +
                                        "    isPaid char(1), NOT NULL,\n" +
                                        "    leaseTerm INT NOT NULL,\n" +
                                        "    rentalFee float(2),\n" +
                                        "    moveInDate date,\n" +
                                        "    PRIMARY KEY (propertyID)\n" +
                                        ");\n" +
                                        "\n" +
                                        "CREATE TABLE RentedProperties\n" +
                                        "(\n" +
                                        "    propertyID INT NOT NULL,\n" +
                                        "    tenantID INT NOT NULL,\n" +
                                        "    FOREIGN KEY (propertyID) REFERENCES Properties (propertyID),\n" +
                                        "    FOREIGN KEY (tenantID) REFERENCES Tenants (tenantID)\n" +
                                        ");\n" +
                                        "\n" +
                                        "CREATE TABLE People\n" +
                                        "(\n" +
                                        "    personID INT NOT NULL,\n" +
                                        "    tenantID INT NOT NULL,\n" +
                                        "    firstName varchar(50) NOT NULL, \n" +
                                        "    lastName varchar(50) NOT NULL,\n" +
                                        "    age INT NOT NULL,\n" +
                                        "    PRIMARY KEY (personID),\n" +
                                        "    FOREIGN KEY (tenantID) REFERENCES Tenants (tenantID)\n" +
                                        ");";
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
                    Connection c = null;
                    Statement stmt = null;

                    try
                    {
                      Class.forName("org.sqlite.JDBC");
                      c = DriverManager.getConnection("jdbc:sqlite:rentals.db");
                      c.setAutoCommit(false);
                      System.out.println("Opened database successfully");

                      stmt = c.createStatement();
                      /*Replace * with a variable too? */
                      ResultSet rs = stmt.executeQuery("SELECT * FROM " + tblName + ";");
                      //Modified from https://www.tutorialspoint.com/sqlite/sqlite_java.htm
                      if (tblName.equals("Tenants"))
                      {
                        int id = rs.getInt("tenantID");
                        String firstName = rs.getString("firstName");
                        String lastName = rs.getString("lastName");
                      }
                      else if (tblName.equals("Properties"))
                      {
                        int id = rs.getInt("propertyID");
                        String address = rs.getString("propertyAddress");
                        String propertyDescription = rs.getString("propertyDescription");
                        int isAvailable = rs.getInt("isAvailable");
                        int isLate = rs.getInt("isLate");
                        int isEvicted = rs.getInt("isEvicted");
                        int isPaid = rs.getInt("isPaid");
                        int leaseTerm = rs.getInt("leaseTerm");
                        float rentalFee = rs.getFloat("rentalFee");
                        //Not sure which type for date, will come back to this
                        //date moveInDate = rs.getDate("moveInDate");
                      }
                      else if (tblName.equals("RentedProperties"))
                      {
                        int propertyID = rs.getInt("propertyID");
                        int tenantID = rs.getInt("tenantID");
                      }
                      else if (tblName.equals("People"))
                      {
                        int personID = rs.getInt("personID");
                        int tenantID = rs.getInt("tenantID");
                        String firstName = rs.getString("firstName");
                        String lastName = rs.getString("lastName");
                        int age = rs.getInt("age");
                      }
                      else
                      {
                        System.out.println("Table not found.");
                      }
                    }
                    catch (Exception e)
                    {
                      System.err.println(e.getClass().getName() + ": "
                                        + e.getMessage());
                      System.exit(0);
                    }
                    System.out.println("Select successful.");
                    return cmd;
            }
}
