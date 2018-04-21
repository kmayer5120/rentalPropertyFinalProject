//package sqlrental;
import java.util.HashMap;
import java.util.Map;

public class SqlRental
{

    public static void main(String[] args)
    {
        SqlRental app =new SqlRental();
        // TODO code application logic here
    }

    public SqlRental()
    {
        //Create db test
        DBManager db = new DBManager();
        //System.out.println(db.createDB("rentals.db"));
        db.createTable();
         //Create Table test
        //DBManager db = new DBManager("/home/danielg/test.db");
        //Create hashmap
        //Key (Column Name), Value (Column Type)
        //System.out.println(db.createTable("tblbName"));

        //Insert Test
        //Create hashmap
        //Key (Column Name), Value = Value
        //System.out.println(db.createTable("tblbName"));
    }
    //Creates hashmap, need to make one for Rental Property too
    public static HashMap<String, String> createHash(Tenant tenant)
    {
      HashMap<String, String> map = new HashMap<>();
      //Need to add ALL fields, not just name
      map.put("firstName", tenant.getFirstName());
      map.put("lastName", tenant.getLastName());
      return map;
    }
}
