/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author yun
 */
public class DBConnectInfo {
    
    private static Statement stmt = null; 
    private static Connection conn = null;
    
    private static DBConnectInfo uniqueInstance;

    private DBConnectInfo() {
        ConnectDB();
    }
    
    public synchronized static DBConnectInfo getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new DBConnectInfo();
        }
        return uniqueInstance;
    }
    public Statement getStatement(){
        return this.stmt;
    }
    public Connection getConnection(){
        return this.conn;
    }
    //DB 연결
    private void ConnectDB() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String Url = "jdbc:mysql://127.0.0.1:3306/univ"; // URL정보 저장 변수
            String DBuser = "root"; // DBuser정보 저장 변수 
            String DBpassword = "1234"; // DBpassword정보 저장 변수 
   
            conn = DriverManager.getConnection(Url,DBuser,DBpassword);
            stmt = conn.createStatement();
            
        }catch(ClassNotFoundException | SQLException e){
                e.printStackTrace();
        }
    }
    
    
}
