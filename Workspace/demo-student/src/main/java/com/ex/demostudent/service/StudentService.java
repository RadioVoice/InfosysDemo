package com.ex.demostudent.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex.demostudent.dto.StudentDTO;
import com.ex.demostudent.entity.*;

import com.ex.demostudent.repository.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class StudentService {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository studentRepo;

	// Creates an entry for a student from a DTO

	@HystrixCommand(fallbackMethod = "createFallback")
	public void createStudent(StudentDTO studentDTO) {
		logger.info("Creation request for student {}", studentDTO);
		Student stu = studentDTO.createEntity();
		studentRepo.save(stu);
	}

	private void createFallback(StudentDTO studentDTO){
		logger.info("Something went wrong, this is the Hystrix Fallback");
	}

	// Fetches full profile of a specific student

	@HystrixCommand(fallbackMethod = "getSpecificFallback")
	public StudentDTO getStudentProfile(Long studentID) {
	    StudentDTO studentDTO = null;
		logger.info("Profile request for student {}", studentID);
		Optional<Student> optStu = studentRepo.findById(studentID);
		if (optStu.isPresent()) {
			Student stu = optStu.get();
			studentDTO = StudentDTO.valueOf(stu);
		} else {
			throw new EntityNotFoundException(); 
		}
		logger.info("Profile for student : {}", studentDTO);
		return studentDTO;
	}

	private StudentDTO getSpecificFallback(Long studentID){
		logger.info("Something went wrong, this is the Hystrix Fallback");
		throw new EntityNotFoundException();
	}

	// Fetches a list of all students in database

	@HystrixCommand(fallbackMethod = "getAllFallback")
    public List<StudentDTO> getAllStudents(){
        logger.info("Profile request for all students");
        List<StudentDTO> allStudents = new ArrayList<StudentDTO>();

        List<Student> allStudentEntity = studentRepo.findAll();
        for (Student s : allStudentEntity){
            allStudents.add(StudentDTO.valueOf(s));
        }

        return allStudents;
    }
	private List<StudentDTO> getAllFallback(){
		logger.info("Something went wrong, this is the Hystrix Fallback");
		return new ArrayList<StudentDTO>();
	}

	// Updates a student from a DTO to the new version

	@HystrixCommand(fallbackMethod = "updateFallback")
	public void updateStudent(StudentDTO sdto){
		logger.info("Requesting to update profile for student {}", sdto.getStudentID());
		Optional<Student> optStu = studentRepo.findById(sdto.getStudentID());
		if (optStu.isPresent()) {
			studentRepo.delete(optStu.get());
			Student newStu = sdto.createEntity();
			studentRepo.save(newStu);
		} else {
			throw new EntityNotFoundException(); 
		}
	}

	private void updateFallback(StudentDTO studentDTO){
		logger.info("Something went wrong, this is the Hystrix Fallback");
		throw new EntityNotFoundException();
	}

	// Deletes a student with the given ID

	@HystrixCommand(fallbackMethod = "deleteFallback")
	public void deleteStudent(Long studentID){
		logger.info("Delete request for student {}", studentID);
		Optional<Student> optStu = studentRepo.findById(studentID);
		if (optStu.isPresent()) {
			studentRepo.delete(optStu.get());
		} else {
			throw new EntityNotFoundException(); 
		}
	}

	private void deleteFallback(Long studentID){
		logger.info("Something went wrong, this is the Hystrix Fallback");
		throw new EntityNotFoundException();
	}

	

}
