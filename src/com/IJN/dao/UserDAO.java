package com.IJN.dao;


import com.IJN.pojo.User;
import com.IJN.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    //添加一个用户
    public static void addUser(User u){

        String sql = "insert into user (username,password,balance,creditcardbalance,access) values(?,?,?,?,?) ";
        Connection c = new JdbcUtil().getConnection();
        try(
                PreparedStatement ps = c.prepareStatement(sql)
        ){
            ps.setString(1,u.username);
            ps.setString(2,u.password);
            ps.setDouble(3,u.balance);
            ps.setDouble(4,u.creditCardBalance);
            ps.setInt(5,u.access);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //通过用户名和金额进行余额充值
    public static void charge(String name,double amount){
        String sql =
                "update user set balance = balance + ?, creditcardbalance = creditcardbalance - ? where username = ?";
        Connection c = new JdbcUtil().getConnection();
        try(
                PreparedStatement ps = c.prepareStatement(sql)
        ){
            ps.setDouble(1,amount);
            ps.setDouble(2,amount);
            ps.setString(3,name);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //更新账户所有信息
    public static void update(User u){
        String sql =
                "update user set password = ?, balance = ?, creditcardbalance = ? where username = ?";
        Connection c = new JdbcUtil().getConnection();
        try(
                PreparedStatement ps = c.prepareStatement(sql)
        ){
            ps.setString(1,u.password);
            ps.setDouble(2,u.balance);
            ps.setDouble(3,u.creditCardBalance);
            ps.setString(4,u.username);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static User get(String name){
        User u = null;
        String sql = "select * from user where username = ?";
        Connection c = new JdbcUtil().getConnection();
        try(
                PreparedStatement ps = c.prepareStatement(sql)
        ){
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                //准备一个对象用于接收结果集的数据
                u = new User();
                u.id = rs.getInt("id");
                u.password = rs.getString("password");
                u.balance = rs.getDouble("balance");
                u.creditCardBalance = rs.getDouble("creditCardBalance");
                u.access = rs.getInt("access");
                u.username = name;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //返回对象的引用
        return u;
    }



}
