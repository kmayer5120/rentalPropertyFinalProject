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
      map.put("age", tenant.getAge());
      map.put("emailAddress", tenant.getEmailAddress());
      map.put("billingAddress", tenant.getBillingAddress());
      return map;
    }
    public static HashMap<String, String> createHash(RentalProperty property)
    {
      HashMap<String, String> map = new HashMap<>();
      //Need to add ALL fields, not just name
      map.put("propertyID", property.getRentalID());
      map.put("propertyDescription", property.getPropertyDescription());
      map.put("isAvailable", property.getIsAvailable());
      //Below are test values. Otherwise NOT NULL triggers
      map.put("propertyAddress", "1234 Test");
      map.put("isLate", "1");
      map.put("isEvicted", "1");
      map.put("isPaid", "1");
      map.put("leaseTerm", "1");
      map.put("rentalFee", "23");
      return map;
    }
}
