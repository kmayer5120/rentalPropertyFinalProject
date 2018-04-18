package sqlrental;

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
        System.out.println(db.createDB("rentals.db"));
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
}
