/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlrental;

import java.sql.*;
import java.util.HashMap;

/**
 *
 * @author danielg
 */
public class DBManager {
    
    
    
    public DBManager(){
        
    }
    
    public DBManager(String dbName){
        
    }
    
    public String createDB(String dbName){
        String cmd = "";
        return cmd;
    }
    
    public String createTable(String tblName, HashMap<String,String> fields){
        String cmd = "";
        return cmd;   
    }
    
    public String insert(String tblName, HashMap<String,String> fields){
        String cmd = "";
        return cmd;       
    }
        
    public String select(String tblName, HashMap<String,String> fields){
        String cmd = "";
        return cmd;       
    }
    
}
