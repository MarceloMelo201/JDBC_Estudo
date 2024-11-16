package application;

import db.DB;
import db.DbException;

import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        Statement st = null;

        try{
            conn = DB.getConnection();

            conn.setAutoCommit(false);

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.commit();

            System.out.println("Rows1 "+ rows1);
            System.out.println("Rows2 "+ rows2);


        }
        catch (SQLException e){
           try {
               conn.rollback();
                throw new DbException(e.getMessage());
           }
           catch (SQLException e1){
               throw new DbException(e1.getMessage());
           }
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}