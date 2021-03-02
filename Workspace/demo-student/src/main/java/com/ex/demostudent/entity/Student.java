package com.ex.demostudent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {

	@Id
	@Column(name = "student_id", nullable = false)
	Long studentID;
    @Column(nullable = false, length = 50)
    String firstName;
    @Column(nullable = false, length = 50)
    String lastName;
    @Column(nullable = false, length = 2)
    Integer grade;
    @Column(nullable = false, length = 1)
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

	public Student() {
		super();
	}
}
