package org.example.dao.implementations;

import org.apache.log4j.Logger;
import org.example.dao.connection.Oracle;
import org.example.dao.interfaces.DAOStudentTask;
import org.example.entities.Student;
import org.example.entities.StudentSubject;
import org.example.entities.StudentTask;
import org.example.entities.Task;
import org.example.tools.custom.exceptions.WrongEntityIdException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.example.tools.strings.Query.*;

@Repository
public class DAOStudentTaskImpl extends Oracle implements DAOStudentTask {

    private static final Logger logger = Logger.getLogger(DAOStudentTaskImpl.class);

    @Override
    public StudentTask getStudentTaskById(String taskId, String studentTaskId) throws WrongEntityIdException {
        try {
            connect();
            statement = connection.prepareStatement(
                    STUDENT_TASK_BY_ID.getQuery());

            statement.setInt(1, Integer.parseInt(taskId));
            statement.setInt(2, Integer.parseInt(studentTaskId));

            result = statement.executeQuery();
            if(result.next()) {
                return StudentTask.parse(result);
            } else {
                throw new WrongEntityIdException("desc ");
            }
        } catch (SQLException | WrongEntityIdException e) {
            e.printStackTrace();
            throw new WrongEntityIdException("desc ", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public List<StudentTask> getStudentTaskByStudentSubjectId(String studentSubjectId) throws WrongEntityIdException {
        try {
            connect();
            List<StudentTask> list = new ArrayList<>();
            statement = connection.prepareStatement(STUDENT_TASKS_BY_STUDENT_SUBJECT_ID.getQuery());

            statement.setInt(1, Integer.parseInt(studentSubjectId));

            result = statement.executeQuery();
            while (result.next()) {
                list.add(StudentTask.parse(result));
            }
            return list;
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new WrongEntityIdException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void addStudentTask(StudentTask studentTask) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(ADD_STUDENT_TASK.getQuery());
            statement.setInt(1, Integer.parseInt(studentTask.getStudentSubjectId()));
            statement.setInt(2, Integer.parseInt(studentTask.getTaskId()));
            statement.setInt(3, Integer.parseInt(studentTask.getSubjectId()));
            statement.setInt(4, studentTask.getGrade());

            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void updateStudentTask(StudentTask studentTask) {
        //mb remove
    }

    @Override
    public void updateStudentTaskGrade(String taskId, String  studentSubjectId, int newGrade) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(UPDATE_STUDENT_TASK_GRADE.getQuery());

            statement.setInt(1, newGrade);
            statement.setInt(2, Integer.parseInt(studentSubjectId));
            statement.setInt(3, Integer.parseInt(taskId));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void deleteStudentTask(String taskId, String studentTaskId) {
        try {
            connect();
            statement = connection.prepareStatement(DELETE_STUDENT_TASK_BY_ID.getQuery());
            statement.setInt(1, Integer.parseInt(studentTaskId));
            statement.setInt(2, Integer.parseInt(taskId));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc");
        } finally {
            disconnect();
        }
    }

    @Override
    public void deleteStudentTasksBySubjectId(String subjectId) {
        try {
            connect();
            statement = connection.prepareStatement(DELETE_STUDENT_TASK_BY_SUBJECT_ID.getQuery());
            statement.setInt(1, Integer.parseInt(subjectId));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc");
        } finally {
            disconnect();
        }
    }

    @Override
    public void deleteStudentTasksByStudentSubjectId(String studentSubjectId) {
        try {
            connect();
            statement = connection.prepareStatement(DELETE_STUDENT_TASK_BY_STUDENT_SUBJECT_ID.getQuery());
            statement.setInt(1, Integer.parseInt(studentSubjectId));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc");
        } finally {
            disconnect();
        }
    }

    @Override
    public void deleteStudentTasksByTaskId(String taskId) {
        try {
            connect();
            statement = connection.prepareStatement(DELETE_STUDENT_TASK_BY_TASK_ID.getQuery());
            statement.setInt(1, Integer.parseInt(taskId));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc");
        } finally {
            disconnect();
        }
    }

    @Override
    public void deleteStudentTasksByStudentId(String studentId) {
        try {
            connect();
            statement = connection.prepareStatement(DELETE_STUDENT_TASK_BY_STUDENT_ID.getQuery());
            statement.setInt(1, Integer.parseInt(studentId));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc");
        } finally {
            disconnect();
        }
    }

    @Override
    public HashMap<Task, StudentTask> getStudentTasksInfoByStudentSubjectId(String studentSubjectId) throws WrongEntityIdException {
        try {
            connect();
            HashMap<Task, StudentTask> studentTaskMap = new HashMap<>();
            statement = connection.prepareStatement(STUDENT_TASK_MAP_BY_STUDENT_SUBJECT_ID.getQuery());
            statement.setInt(1, Integer.parseInt(studentSubjectId));
            result = statement.executeQuery();
            while (result.next()) {
                studentTaskMap.put(Task.parse(result), StudentTask.parse(result));
            }
            return studentTaskMap;
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new WrongEntityIdException("desc", e);
        } finally {
            disconnect();
        }
    }
}
