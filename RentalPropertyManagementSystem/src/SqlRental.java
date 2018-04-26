//package sqlrental;
import java.util.HashMap;
import java.util.Map;

public class SqlRental
{

    public static void main(String[] args)
    {
        SqlRental app =new SqlRental();
    }

    public SqlRental()
    {
        DBManager db = new DBManager();
        db.createTable();
    }
    public static HashMap<String, String> createHash(Tenant tenant)
    {
      HashMap<String, String> map = new HashMap<>();
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
      map.put("propertyID", property.getRentalID());
      map.put("propertyDescription", property.getPropertyDescription());
      map.put("isAvailable", property.getIsAvailable());
      map.put("propertyAddress", property.getPropertyAddress());
      map.put("isLate", "1");
      map.put("isEvicted", "1");
      map.put("isPaid", "1");
      map.put("leaseTerm", "1");
      map.put("rentalFee", property.getRentCost());
      return map;
    }
}
