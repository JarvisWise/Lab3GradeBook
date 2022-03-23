package org.example.dao.connection;

import org.example.entities.Student;
import org.example.entities.Teacher;

import java.sql.*;

//old
public class DAOPostgreSQL implements DAOInterface {

    private static final DAOPostgreSQL instance = new DAOPostgreSQL();
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;

    private DAOPostgreSQL(){super();}
    public static DAOPostgreSQL getInstance(){
        return instance;
    }

    @Override
    public boolean connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "ama_user","1234");
            return !connection.isClosed();
        } catch (SQLException | ClassNotFoundException throwables) {
            return false;
        }
    }

    @Override
    public boolean disconnect() {
        try {
            if(connection!=null){
                connection.close();
            }
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }

    //studentBy
    /*private Student parseStudent(ResultSet result) throws SQLException {
        return new Student(
                result.getString("student_id"),
                result.getString("login_name"),
                result.getString("first_name"),
                result.getString("last_name"),
                result.getString("password"),
                result.getString("headman"),
                result.getString("group_id")
        );
    }

    public Student getStudentByIdAndPassword(String studentId, String password) {
        connect();
        try {
            statement = connection.prepareStatement(
                    "SELECT * FROM student WHERE student_id = ? AND password = ?");

            //statement.setString(1, studentId);
            statement.setInt(1, Integer.parseInt(studentId));
            statement.setString(2, password);

            result = statement.executeQuery();
            if(result.next()) {
                return parseStudent(result);
            } else {
                return null;
            }
        } catch (SQLException e) {
            //throw new SQLException("Unable to select from emp", e);
            e.printStackTrace();
            return null;//
        } finally {
            disconnect();
        }
    }

    //TeacherBy
    private Teacher parseTeacher(ResultSet result) throws SQLException {
        return new Teacher(
                result.getString("teacher_id"),
                result.getString("login_name"),
                result.getString("first_name"),
                result.getString("last_name"),
                result.getString("password")
        );
    }

    public Teacher getTeacherByIdAndPassword(String teacherId, String password) {
        connect();
        try {
            statement = connection.prepareStatement(
                    "SELECT * FROM teacher WHERE teacher_id = ? AND password = ?");

            //statement.setString(1, teacherId);
            statement.setInt(1, Integer.parseInt(teacherId));
            statement.setString(2, password);

            result = statement.executeQuery();
            if(result.next()) {
                return parseTeacher(result);
            } else {
                return null;
            }
        } catch (SQLException e) {
            //throw new SQLException("Unable to select from emp", e);
            e.printStackTrace();
            return null;//
        } finally {
            disconnect();
        }
    }*/
}
