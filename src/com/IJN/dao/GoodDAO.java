package com.IJN.dao;

import com.IJN.pojo.Good;
import com.IJN.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodDAO {
    //新建一种商品加入数据库中，默认个数0
    public static void createGood(Good g){

        String sql = "insert into good (name,price) values(?,?) ";
        Connection c = new JdbcUtil().getConnection();
        try(
                PreparedStatement ps = c.prepareStatement(sql)
        ){
            ps.setString(1,g.name);
            ps.setDouble(2,g.price);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //增加指定数量的数据库中的指定商品
    public static void addGood(String name,int quantity){
        String sql = "update good set quantity = quantity + ? where name = ?";
        Connection c = new JdbcUtil().getConnection();
        try(
                PreparedStatement ps = c.prepareStatement(sql)
        ){
            ps.setInt(1,quantity);
            ps.setString(2,name);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //减少指定数量的数据库中的指定商品
    public static void removeGood(String name,int quantity){
        String sql = "update good set quantity = quantity - ? where name = ?";
        Connection c = new JdbcUtil().getConnection();
        try(
                PreparedStatement ps = c.prepareStatement(sql)
        ){
            ps.setInt(1,quantity);
            ps.setString(2,name);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //修改数据库中的指定商品信息
    public static void update(Good g){
        String sql = "update good set quantity = ?, price = ? where name = ?";
        Connection c = new JdbcUtil().getConnection();
        try(
                PreparedStatement ps = c.prepareStatement(sql)
        ){
            ps.setInt(1,g.quantity);
            ps.setDouble(2,g.price);
            ps.setString(3,g.name);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //通过商品对象将指定商品从数据库中删除
    public static void delete(Good g){
        String sql = "delete from good where name = ?";
        Connection c = new JdbcUtil().getConnection();
        try(
                PreparedStatement ps = c.prepareStatement(sql)
        ){
            ps.setString(1,g.name);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

/*    //更新数据库中指定商品对应的价格
    public static void updatePrice(Good g){
        String sql = "update good set price = ? where name = ?";
        Connection c = new JdbcUtil().getConnection();
        try(
                PreparedStatement ps = c.prepareStatement(sql)
        ){
            ps.setDouble(1,g.price);
            ps.setString(2,g.name);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }*/

    //打印所有的商品信息
    public static List<Good> list(){
        List<Good> goodList = new ArrayList<>();
        String sql = "select * from good";
        Connection c = new JdbcUtil().getConnection();
        try(
                PreparedStatement ps = c.prepareStatement(sql)
        ){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Good g = new Good();
                g.id = rs.getInt("id");
                g.name = rs.getString("name");
                g.price = rs.getDouble("price");
                g.quantity = rs.getInt("quantity");
                //插入ArrayList
                goodList.add(g);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return goodList;
    }

    //打印所有有货的商品信息
    public static List<Good> listAvailable(){
        List<Good> goodList = new ArrayList<>();
        String sql = "select * from good where quantity != 0";
        Connection c = new JdbcUtil().getConnection();
        try(
                PreparedStatement ps = c.prepareStatement(sql)
        ){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Good g = new Good();
                g.id = rs.getInt("id");
                g.name = rs.getString("name");
                g.price = rs.getDouble("price");
                g.quantity = rs.getInt("quantity");
                //插入ArrayList
                goodList.add(g);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return goodList;
    }



    //根据提供的商品名name参数从Good表中获取一条记录，
    // 并存入Good类对象中进行返回
    public static Good get(String name){
        Good g = null;
        String sql = "select * from good where name = ?";
        Connection c = new JdbcUtil().getConnection();
        try(
                PreparedStatement ps = c.prepareStatement(sql)
        ){
            //参数传入
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();//这里不要提供sql参数
            //查询出的是某一行
            if(rs.next()){
                //准备一个对象用于接收结果集的数据
                g = new Good();
                g.id = rs.getInt("id");
                g.quantity = rs.getInt("quantity");
                g.price = rs.getDouble("price");
                g.name = name;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //返回对象的引用
        return g;
    }





}
