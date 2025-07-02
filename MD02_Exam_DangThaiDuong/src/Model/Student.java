package Model;

import java.sql.Date;

public class Student {
    private int id;
    private String name;
    private String email;
    private String phone;
    private  Date register;
    private boolean status;

    public Student(){

    }

    public Student(int id, String name, String email, String phone, Date register, boolean status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.register = register;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return "Student{" + "Student_Id = "+ id +", Full_Name= "+ name + ", Email= "+email+", Phone_Number= "+phone+ ", Register_Date= "+ register+ "Status="+(status ? "Hoạt động" : "Không hoạt động")+"}";
    }
}
