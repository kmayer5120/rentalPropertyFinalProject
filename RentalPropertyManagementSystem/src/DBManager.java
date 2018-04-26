import java.sql.*;
import java.util.HashMap;
import java.lang.StringBuilder;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JTable;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class DBManager
{
    Connection c = null;
	private String dbPath = "rentals.db";


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

    public void createDB()
    {
        String cmd = "";

        String url = "jdbc:sqlite:" + dbPath;

        try
        {
          Connection conn = DriverManager.getConnection(url);
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
    }

    public void createTable()
    {
        Connection c = null;
        Statement stmt = null;
        String url = "jdbc:sqlite:" + dbPath;


        try
        {
          Connection conn = DriverManager.getConnection(url);
            Class.forName("org.sqlite.JDBC");

            System.out.println("Opened database successfully");

            String sql =
            "CREATE TABLE IF NOT EXISTS Tenants" +
            "(" +
             "   tenantID INTEGER NOT NULL," +
	           "   firstName varchar(50) NOT NULL," +
   	         "   lastName varchar(50) NOT NULL," +
             "    age varchar(2) NOT NULL," +
             "    emailAddress varchar(50) NOT NULL," +
             "    billingAddress varchar(100) NOT NULL," +
            "    PRIMARY KEY (tenantID)" +
            ");";
            stmt = conn.createStatement();
            stmt.execute(sql);
            String sql2 =
            "CREATE TABLE IF NOT EXISTS Properties" +
            "(" +
            "    propertyID varchar(50) NOT NULL," +
            "    propertyAddress varchar(255) NOT NULL," +
            "    propertyDescription varchar(255) NOT NULL," +
            "    isAvailable char(1) NOT NULL," +
            "    isLate char(1) NOT NULL," +
            "    isEvicted char(1) NOT NULL," +
            "    isPaid char(1) NOT NULL," +
            "    leaseTerm char(1) NOT NULL," +
            "    rentalFee varchar(4) NOT NULL," +
            "    PRIMARY KEY (propertyID)" +
            ");";
            stmt = conn.createStatement();
            stmt.execute(sql2);
            String sql3 =
            "CREATE TABLE IF NOT EXISTS RentedProperties" +
            "(" +
            "    propertyID INTEGER NOT NULL," +
            "    tenantID INTEGER NOT NULL," +
            "    FOREIGN KEY (propertyID) REFERENCES Properties (propertyID)," +
            "    FOREIGN KEY (tenantID) REFERENCES Tenants (tenantID)" +
            ");";
            stmt = conn.createStatement();
            stmt.execute(sql3);
            String sql4 =
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
            stmt.execute(sql4);
            stmt.close();
            conn.close();
        } catch ( SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } catch (ClassNotFoundException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
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
    public static JTable select(String query)
    {
        Connection c = null;
        Statement stmt = null;
        JTable queryTable = new JTable();

        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:rentals.db");

            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            queryTable = new JTable(buildTableModel(rs));
            c.close();
          }

        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": "
                            + e.getMessage());
            System.exit(0);
        }
        System.out.println("Select successful.");
        return queryTable;
    }

    public static String update(String query)
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
            stmt.executeUpdate(query);
            c.commit();
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

    public static String delete(String query)
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
            stmt.executeUpdate(query);
            c.commit();
            c.close();
          }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": "
                            + e.getMessage());
            System.exit(0);
        }
        System.out.println("Delete successful.");
        return cmd;
    }

    //Used to convert the result set into a JTable
    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException
    {
      ResultSetMetaData metaData = rs.getMetaData();
      // names of columns
      Vector<String> columnNames = new Vector<String>();
      int columnCount = metaData.getColumnCount();
      for (int column = 1; column <= columnCount; column++)
      {
        columnNames.add(metaData.getColumnName(column));
      }
      // data of the table
      Vector<Vector<Object>> data = new Vector<Vector<Object>>();
      while (rs.next())
      {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
        {
          vector.add(rs.getObject(columnIndex));
        }
        data.add(vector);
      }

      return new DefaultTableModel(data, columnNames);
    }
}
