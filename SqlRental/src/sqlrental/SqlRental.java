/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlrental;



/**
 *
 * @author danielg
 */
public class SqlRental
{
    /**
     * @param args the command line arguments
     */
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
