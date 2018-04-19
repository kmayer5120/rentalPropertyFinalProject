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
import java.lang.StringBuilder;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;


public class DBManager
{
    Connection c = null;
	private String dbPath = "src/rentals.db";


    public DBManager()
    {


    }

    public DBManager(String dbName)
    {
	//connect to DB upon object creation
	Connection c = null;

	try
	{
	    Class.forName("org.sqlite.JDBC");
	    c = DriverManager.getConnection("jdbc:sqlite:rentals.db");
        } catch ( SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
	    System.exit(0);
        } catch (ClassNotFoundException e) {
	    System.err.println( e.getClass().getName() + ": " + e.getMessage());
	    System.exit(0);
        }
      System.out.println("Opened database successfully");

    }

    public String createDB()
    {
        String cmd = "";

        String url = "jdbc:sqlite:" + this.dbPath;

        try (Connection conn = DriverManager.getConnection(url))
        {
            if (conn != null)
            {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("A new database has been created.");
				conn.close();
            }
        } catch (SQLException e) {
			System.out.println("Error SQL Exception");
            System.out.println(e.getMessage());
        }

        return cmd;
    }

    public String createTable()
    {
        //original method signature. We may not need to pass strings if
        //these tables are just going to be created with hardcoding
        //public String createTable(String tblName, HashMap<String,String> fields)
        String cmd = "";
        Connection c = null;
        Statement stmt = null;
        String url = "jdbc:sqlite:" + this.dbPath;


        try(Connection conn = DriverManager.getConnection(url))
        {
            Class.forName("org.sqlite.JDBC");

            System.out.println("Opened database successfully");


            String sql =
            " CREATE TABLE IF NOT EXISTS Tenants" +
            "(" +
            "    tenantID INTEGER NOT NULL, " +
	    "    firstName varchar(50) NOT NULL, " +
   	    "    lastName varchar(50) NOT NULL," +
            "    PRIMARY KEY (tenantID)" +
            ");" +

            "CREATE TABLE IF NOT EXISTS Properties" +
            "(" +
            "    propertyID varchar(50) NOT NULL," +
            "    propertyAddress varchar(255) NOT NULL," +
            "    propertyDescription varchar(255) NOT NULL," +
            "    isAvailable char(1) NOT NULL," +
            "    isLate char(1), NOT NULL," +
            "    isEvicted char(1), NOT NULL," +
            "    isPaid char(1), NOT NULL," +
            "    leaseTerm INTEGER NOT NULL," +
            "    rentalFee float(2)," +
            "    moveInDate date," +
            "    PRIMARY KEY (propertyID)" +
            ");" +

            "CREATE TABLE IF NOT EXISTS RentedProperties" +
            "(" +
            "    propertyID INTEGER NOT NULL," +
            "    tenantID INTEGER NOT NULL," +
            "    FOREIGN KEY (propertyID) REFERENCES Properties (propertyID)," +
            "    FOREIGN KEY (tenantID) REFERENCES Tenants (tenantID)" +
            ");" +

            "CREATE TABLE IF NOT EXISTS People" +
            "(" +
            "    personID INTEGER NOT NULL," +
            "    tenantID INTEGER NOT NULL," +
            "    firstName varchar(50) NOT NULL, " +
            "    lastName varchar(50) NOT NULL," +
            "    age INTEGER NOT NULL," +
            "    PRIMARY KEY (personID)," +
            "    FOREIGN KEY (tenantID) REFERENCES Tenants (tenantID)" +
            ");";
            stmt = conn.createStatement();
			System.out.println("After sql command statement");
            stmt.execute(sql);
			System.out.println("After stmt.execute(); statement");
            stmt.close();
            conn.close();
        } catch ( SQLException e ) {
			System.out.println("Inside first catch SQL e");
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } catch (ClassNotFoundException e) {
			System.out.println("Inside second catch CNF e");
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return cmd;
    }

    public static String insert(String tblName, HashMap<String,String> fields)
    {
      String cmd = "";
      Connection c = null;
      Statement stmt = null;
      Set keyset = fields.entrySet();
      Iterator i = keyset.iterator();

      try
      {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:rentals.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully.");

        stmt = c.createStatement();
        //Line 160 - 185 creates INSERT query as a StringBuilder
        StringBuilder sqlKeys = new StringBuilder("INSERT INTO ");
        StringBuilder sqlValues = new StringBuilder("VALUES (");
        StringBuilder sqlFinal = new StringBuilder();
        sqlKeys.append(tblName + " (");

        while (i.hasNext())
        {
          Map.Entry current = (Map.Entry)i.next();
          sqlKeys.append(current.getKey() + ",");
          if (current.getValue() instanceof String)
          {
            sqlValues.append("'" + current.getValue() + "'");
          }
          else
          {
            sqlValues.append(current.getValue());
          }
          sqlValues.append(",");
        }
        //Removes commas
        sqlKeys.setLength(sqlKeys.length() - 1);
        sqlValues.setLength(sqlValues.length() - 1);
        //Creates final query
        sqlFinal.append(sqlKeys + ") " + sqlValues + ");");
        String query = sqlFinal.toString();

        stmt.executeUpdate(query);
        System.out.println(query);

        stmt.close();
        c.commit();
        c.close();
      }
      catch (Exception e)
      {
        System.err.println(e.getClass().getName() + ": "
                          + e.getMessage());
        System.exit(0);
      }
      System.out.println("Records created successfully.");
      return cmd;
    }
    //Hashmap may be unnecessary for this method, TBD
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

    public String update(String tblName, HashMap<String,String> fields)
    {
        String cmd = "";
        Connection c = null;
        Statement stmt = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:rentals.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully.");

            stmt = c.createStatement();

            //Incomplete StringBuilder
            StringBuilder sql = new StringBuilder("UPDATE " + tblName + "set");
            String query = "";
            /*Once again, probably want to use StringBuilder to create
            sql query*/
            stmt.executeUpdate(query);
            c.commit();

            /*Left out result set stuff, seemed redundant copy and
            paste from select method if necessary*/
            stmt.close();
            c.close();
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": "
                              + e.getMessage());
            System.exit(0);
        }
        System.out.println("Update successful.");
        return cmd;
    }
}
