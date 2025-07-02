import Model.Student;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static StudentDAO studentDAO = new StudentDAO();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        while (true){
            System.out.println("*****************STUDENT MANAGEMENT*****************");
            System.out.println("1. Danh sách sinh viên");
            System.out.println("2. Thêm mới sinh viên");
            System.out.println("3. Cập nhật sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Tìm kiếm sinh viên theo tên");
            System.out.println("6. Sắp xếp sinh viên theo ngày đăng ký");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            byte opt = Byte.parseByte(scanner.nextLine());
            switch (opt){
                case 1:
                    displayAllStudents();
                    break;
                case 2:
                    addNewStudent();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    sortStudent();
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Vui lòng chọn từ 1 đến 7");
                    break;
            }

        }
    }
    public static void displayAllStudents(){
        System.out.println("---Danh sách sinh viên---");
        List<Student> studentList = studentDAO.gellAllStudentDAO();
        for (Student student : studentList){
            System.out.println(student);
        }
    }
    public static void addNewStudent(){
        System.out.println("---Thêm mới sinh viên---");
        Student student = new Student();
        System.out.println("Nhập tên học viên");
        student.setName(scanner.nextLine());
        System.out.println("Nhập Email học viên");
        student.setEmail(scanner.nextLine());
        System.out.println("Nhập Số Điện Thoại học viên");
        student.setEmail(scanner.nextLine());
        student.setRegister(new Date(System.currentTimeMillis())) ;
        if (studentDAO.addNewStudent(student)){
        System.out.println("Thêm học viên thành công ");
        }
        else{
            System.out.println("Thêm học viên thất bại ");
        }
    }
    public static void updateStudent(){
        System.out.println("---Update thông tin học viên---");
        System.out.print("Nhập ID của học viên cần cập nhật: ");
        int id = Integer.parseInt(scanner.nextLine());
        Student studentToUpdate = studentDAO.getStudentById(id);
        if (studentToUpdate == null){
            System.out.println("Không tìm thấy học viên");
            return;
        }else{
            System.out.println("Đã tìm thấy" + studentToUpdate);
            System.out.println("Nhập họ tên mới: ");
            String newName = scanner.nextLine();
            studentToUpdate.setName(newName);
            System.out.println("Nhập Email mới: ");
            String newEmail = scanner.nextLine();
            studentToUpdate.setEmail(newEmail);
            System.out.println("Nhập SĐT mới: ");
            String newPhone = scanner.nextLine();
            studentToUpdate.setPhone(newPhone);
            System.out.println("Nhập trạng thái mới: 1 hoặc 0");
            String newStatus = scanner.nextLine();
            if (newStatus.equals("0")){
                studentToUpdate.setStatus(false);
            }else{
                studentToUpdate.setStatus(true);
            }

            if(studentDAO.updateStudent(studentToUpdate)){
                System.out.println("Cập nhật thành công!");
            }else{
                System.out.println("Cập nhật thất bại!");
            }
        }
    }
    public static void deleteStudent(){
        System.out.println("---Xóa học viên---");
        System.out.println("Nhập ID học viên cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Bạn có chắc muốn xóa học viên này>? Y/N");
        String confirm = scanner.nextLine();
        if(confirm.equals("Y")){
            if (studentDAO.deleteStudent(id)){
                System.out.println("Đã xóa");
            }
            else {
                System.out.println("Xóa Thất Bại");
            }
        }else {
            System.out.println("Hủy Xóa!");
        }
    }
    public static void searchStudent(){
        System.out.println("---Tìm kiếm học viên---");
        System.out.println("Nhập tên học viên cần tìm: ");
        String name = scanner.nextLine();
        List<Student> studentSearchList = studentDAO.searchStudents(name);
        if (studentSearchList.isEmpty()){
            System.out.println("Không có sinh viên tên là: "+name);
        }else{
            System.out.println("Kết quả tìm kiếm: ");
            for (Student student : studentSearchList){
                System.out.println(student);
            }
        }
    }
    public static void sortStudent(){
        List<Student> sortStudent = studentDAO.sortStudents();
        System.out.println("---Danh sách học viên đã sắp xếp theo ngày đăng ký:");
        if (sortStudent.isEmpty()){
            System.out.println("Dữ liệu trống!");
        }else{
            for (Student student : sortStudent){
                System.out.println(student);
            }
        }
    }
}