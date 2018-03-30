package com.kaishengit;

import org.apache.ibatis.annotations.Insert;
import org.junit.Test;

import java.sql.*;

public class ProcedureTestCase {

    @Test
    public void testJDBC(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql:///kaishengit_db","root","longwear");
            String sql = "select * from products where prod_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"FB");
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                System.out.println(rs.getString("prod_name"));
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void testPro1(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql:///kaishengit_db","root","longwear");
            String sql = "{call p_1()}";
            CallableStatement callableStatement = connection.prepareCall(sql);


            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("prod_name"));
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }
    @Test
    public void testPro2(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql:///kaishengit_db","root","longwear");
            String sql = "{call p_2(?)}";
            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1,"1001");
            ResultSet rs = callableStatement.executeQuery();
           while (rs.next()){
                System.out.println(rs.getString("prod_name"));
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }
    @Test
    public void testPro4(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql:///kaishengit_db","root","longwear");
            String sql = "{call p_4(?,?)}";
            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1,"FB");
            callableStatement.registerOutParameter(2,Types.DECIMAL);
            callableStatement.executeUpdate();
            float price = callableStatement.getFloat(2);
            System.out.println(price);

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void testPro5(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql:///kaishengit_db","root","longwear");
            String sql = "{call p_5(?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1,"FB");
            callableStatement.setString(2,"5");
            callableStatement.registerOutParameter(3,Types.INTEGER);
            callableStatement.executeUpdate();
            Integer price = callableStatement.getInt(3);
            System.out.println(price);

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void testTransaction(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql:///kaishengit_db","root","longwear");
            conn.setAutoCommit(false);

            String sql = "delete from t_stu where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,12);
            preparedStatement.executeUpdate();

            Savepoint savepoint = conn.setSavepoint("s1");


            String sql1 = "delete from t_stu where id = ?";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement.setInt(1,1);
            preparedStatement.executeUpdate();

            conn.rollback(savepoint);
            conn.commit();
            preparedStatement.close();
            conn.close();



        }catch (Exception e){
            e.printStackTrace();}
    }


}
