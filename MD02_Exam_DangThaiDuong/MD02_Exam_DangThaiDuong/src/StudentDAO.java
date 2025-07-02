import Model.Student;
import java.sql.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<Student> gellAllStudentDAO(){
        List<Student> studentList = new ArrayList<>();
        Connection connection = null;
        try{
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{ CALL get_student_by_id}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("Student_Id"));
                student.setName(resultSet.getString("Full_Name"));
                student.setEmail(resultSet.getString("Email"));
                student.setPhone(resultSet.getString("Phone_Number"));
                student.setRegister(resultSet.getDate("Register_Date"));
                student.setStatus(resultSet.getBoolean("Status"));
                studentList.add(student);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return studentList;
    }

    public boolean addNewStudent(Student student){
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = ConnectionDB.openConnection();
            connection.setAutoCommit(false);
            CallableStatement callableStatement = connection.prepareCall("{CALL add_student(?,?,?,?,?)}");
            callableStatement.setString(1,student.getName());
            callableStatement.setString(2,student.getEmail());
            callableStatement.setString(3, student.getPhone());
            callableStatement.setDate(4, student.getRegister());
            callableStatement.setBoolean(5,student.isStatus());
            int rowAffected = callableStatement.executeUpdate();
            if (rowAffected >0){
                isSuccess = true;
            }
            connection.commit();
        } catch (SQLException e) {
            try{
                if (connection != null){
                    connection.rollback();
                }
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();

        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return isSuccess;
    }
    public Student getStudentById(int studentId){
        Student student = null;
        try{
            Connection connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL get_student_by_id}");
            callableStatement.setInt(1, studentId);
            try {
                ResultSet resultSet = callableStatement.executeQuery();
                if (resultSet.next()){
                    student = new Student();
                    student.setId(resultSet.getInt("Student_Id"));
                    student.setName(resultSet.getString("Full_Name"));
                    student.setEmail(resultSet.getString("Email"));
                    student.setPhone(resultSet.getString("Phone_Number"));
                    student.setRegister(resultSet.getDate("Register_Date"));
                    student.setStatus(resultSet.getBoolean("Status"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
    public boolean updateStudent(Student student){
        try{
            Connection connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL update_student(?,?,?,?,?)}");
            callableStatement.setInt(1,student.getId());
            callableStatement.setString(2, student.getName());
            callableStatement.setString(3, student.getEmail());
            callableStatement.setString(4, student.getPhone());
            callableStatement.setBoolean(5,student.isStatus());
            return callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteStudent(int studentId){
        try{
            Connection connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL delete_student}");
            callableStatement.setInt(1,studentId);
            return callableStatement.executeUpdate() >0;
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
    }
    public List<Student> searchStudents(String name){
        List<Student> studentList = new ArrayList<>();
        try{
            Connection connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL search_student}");
            callableStatement.setString(1, name);
            try{
                ResultSet resultSet = callableStatement.executeQuery();
                while (resultSet.next()){
                    Student student = new Student();
                    student.setId(resultSet.getInt("Student_Id"));
                    student.setName(resultSet.getString("Full_Name"));
                    student.setEmail(resultSet.getString("Email"));
                    student.setPhone(resultSet.getString("Phone_Number"));
                    student.setRegister(resultSet.getDate("Register_Date"));
                    student.setStatus(resultSet.getBoolean("Status"));
                    studentList.add(student);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }return studentList;
    }
    public List<Student> sortStudents(){
        List<Student> studentList = new ArrayList<>();
        try{
            Connection connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL sort_students}");
            try{
                ResultSet resultSet = callableStatement.executeQuery();
                while (resultSet.next()){
                    Student student = new Student();
                    student.setId(resultSet.getInt("Student_Id"));
                    student.setName(resultSet.getString("Full_Name"));
                    student.setEmail(resultSet.getString("Email"));
                    student.setPhone(resultSet.getString("Phone_Number"));
                    student.setRegister(resultSet.getDate("Register_Date"));
                    student.setStatus(resultSet.getBoolean("Status"));
                    studentList.add(student);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }return studentList;
    }
}
