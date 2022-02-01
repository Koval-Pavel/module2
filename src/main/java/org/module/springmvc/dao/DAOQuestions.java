package org.module.springmvc.dao;

import org.module.springmvc.model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOQuestions implements DAOInterface{
    private final static DAOQuestions instance = new DAOQuestions();
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    public static String driverClass = "oracle.jdbc.driver.OracleDriver";
    public static String url = "jdbc:oracle:thin:@localhost:1521:XE";
    public static String user = "STUDENT_LIST";
    public static String password = "365983";

    private DAOQuestions() {super();}

    public static  DAOQuestions getInstance() {return instance;}

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





    private Question parseQuestion (ResultSet resultSet) {
        Question question = null;
        try {
            int question_id = resultSet.getInt("ID");
            String question_question = resultSet.getString("QUESTIONS");
            String question_answer = resultSet.getString("ANSWER");
            int question_points = resultSet.getInt("POINTS");
            question = new Question(question_id, question_question, question_answer, question_points);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }

    public List<Question> findQuestions() {
        List<Question> listQuestion = new ArrayList<Question>();
        connect();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM (SELECT * FROM STUDENT_LIST.QUESTIONS ORDER BY DBMS_RANDOM.RANDOM) WHERE  rownum < 6");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listQuestion.add(parseQuestion(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return listQuestion;
    }

}
