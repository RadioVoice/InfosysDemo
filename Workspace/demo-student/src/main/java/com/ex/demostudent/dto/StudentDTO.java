package com.ex.demostudent.dto;

import com.ex.demostudent.entity.Student;

public class StudentDTO {

	Long studentID;
    String firstName;
    String lastName;
    Integer grade;
    char gender;

    public Long getStudentID() {
        return this.studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGrade() {
        return this.grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public char getGender() {
        return this.gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public static StudentDTO valueOf(Student student) {

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setGrade(student.getGrade());
        studentDTO.setStudentID(student.getStudentID());
        studentDTO.setGender(student.getGender());
        
        return studentDTO;
    }

    public Student createEntity(){
        Student stu = new Student();
        stu.setFirstName(this.firstName);
        stu.setLastName(this.lastName);
        stu.setStudentID(this.studentID);
        stu.setGrade(this.grade);
        stu.setGender(this.gender);
        return stu;
    }
    
}
