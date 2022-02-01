package org.module.springmvc.dao;

import org.module.springmvc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUsers implements DAOInterface{
    private final static DAOUsers instance = new DAOUsers();
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    public static String driverClass = "oracle.jdbc.driver.OracleDriver";
    public static String url = "jdbc:oracle:thin:@localhost:1521:XE";
    public static String user = "STUDENT_LIST";
    public static String password = "365983";

    private DAOUsers() {super();}

    public static  DAOUsers getInstance() {return instance;}

    public void connect() {
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(url, user, password);
            if (!connection.isClosed()){
                System.out.println("Connected Successfully!");
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        } catch (ClassNotFoundException  e) {
            e.printStackTrace();
        }

    }
    public void disconnect(){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User parseUser (ResultSet resultSet) {
        User user = null;
        try {
            int user_id = resultSet.getInt("ID");
            String user_name = resultSet.getString("NAME");
            int user_max_point = resultSet.getInt("MAX_TOTAL_POINTS");
            user = new User(user_id,user_name,user_max_point);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        connect();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM USERS");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(parseUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User findUser(String name) {
        User user = new User();
        connect();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE NAME = ?");
            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = parseUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return user;
    }

    public void inputUser(User user) {
        connect();
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO STUDENT_LIST.USERS (NAME, MAX_TOTAL_POINTS) VALUES ( ?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getTotal_points());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    public void updateUser(User user) {
        connect();
        try {
            preparedStatement = connection.prepareStatement("UPDATE USERS set MAX_TOTAL_POINTS = ? WHERE ID = ?");
            preparedStatement.setInt(1, user.getTotal_points());
            preparedStatement.setInt(2, user.getId());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }
}
