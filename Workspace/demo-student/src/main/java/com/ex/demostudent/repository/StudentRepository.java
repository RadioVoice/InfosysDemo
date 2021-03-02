package com.ex.demostudent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.demostudent.entity.*;

public interface StudentRepository extends JpaRepository<Student, Long> {

	

}
