package com.IJN.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
* 封装的jdbc工具类，提供一个connection对象
* */


public class JdbcUtil {
    public static final String url = "jdbc:mysql://127.0.0.1:3306/shopservice?characterEncoding=UTF-8";
    public static final String username = "root";
    public static final String password = "123";
    public static Connection c = null;


    public JdbcUtil(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        if(c == null) {
            try {
                c = DriverManager.getConnection(url,username,password);
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                return c;
            }
        }else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return c;
        }

    }

}
