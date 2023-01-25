package connection;
import java.sql.*;

public class ConnectionClass {
    public Connection connection;
    ResultSet resultSet;
    int val;
    Statement statement;
    public  Connection getConnection(){


        String dbName="bank";
        String userName="root";
        String password=null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName,userName,password);
//            System.out.println("connected");

        } catch (Exception e) {
            e.printStackTrace();
        }


        return connection;
    }

    //    function to retrieve data using prepared Statement
    public ResultSet retrieve (PreparedStatement preparedStatement){
        try{
            resultSet=preparedStatement.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public int manipulate(PreparedStatement preparedStatement){
        try{
            val=preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return val;
    }
}