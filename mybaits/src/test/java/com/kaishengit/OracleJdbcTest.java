package com.kaishengit;

import org.junit.Test;

import java.sql.*;

public class OracleJdbcTest {

    @Test
    public void testOtacleJdbc(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL","scott","tiger");

            String sql = "select * from emp where empno = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,7566);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                System.out.println(rs.getString("ename"));
            }

            rs.close();
            preparedStatement.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
