import java.sql.*;
import java.util.*;

class DBTest
{
    public static void main(String[] args)
    {
        //variables needed for connection
        String url = "jdbc:derby:rentals";
        String user = "ATEAM"; 
        String pass = "ATEAM";
        Connection con = null;

        try
        {
            con = DriverManager.getConnection(url, user, pass);
            Statement statement = con.createStatement();

            String sqlCommand = "INSERT INTO Tenant (123, 'kyle', 'mayer', 'email@email.com''1234 Address St. ')";

            int returnCode = statement.executeUpdate(sqlCommand);

            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
}
