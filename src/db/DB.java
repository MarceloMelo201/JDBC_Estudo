package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    //Conexão com o banco de dados
    public static Connection getConnection(){
        if(conn == null){
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");

                //Conectando o banco de dados
                conn = DriverManager.getConnection(url, props);
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    //Fechando conexão com o banco de dados

    public static void closeConnection(){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    //Carregar propriedades do arquivo db.properties
    private static Properties loadProperties() {
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();

            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    //Métodos para auxiliar o fechamento das conexões
    public static void closeStatement(Statement st){
        if(st != null){
            try {
                st.close();
            }
            catch (SQLException e) {
                throw new DbException((e.getMessage()));
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            }
            catch (SQLException e) {
                throw new DbException((e.getMessage()));
            }
        }
    }

}
