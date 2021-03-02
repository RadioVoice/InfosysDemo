package com.ex.demostudent.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.ex.demostudent.dto.*;
import com.ex.demostudent.service.StudentService;

@RestController
@CrossOrigin
public class StudentController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentService studentService;

    @PostMapping(value = "/students", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createStudent(@RequestBody StudentDTO stuDTO){
        logger.info("Creation request for student {}", stuDTO);
		studentService.createStudent(stuDTO);
    }

    @GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDTO> getStudents(){
        logger.info("Get request for all students");

        List<StudentDTO> studentList = studentService.getAllStudents();
        return studentList;
    }

    @GetMapping(value = "/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO getStudentProfile(@PathVariable("id") Long studentID){
        logger.info("Get request for student {}", studentID);

        try{
            StudentDTO studentDTO = studentService.getStudentProfile(studentID);
            return studentDTO;
        } catch (EntityNotFoundException exc){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with that ID not found");
        } catch (Exception exc){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Other Server Side Error");
        }
    }

    @PutMapping(value="/students", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStudent(@RequestBody StudentDTO studentDTO){
        logger.info("Update request for student {}", studentDTO);

        try{
            studentService.updateStudent(studentDTO);
        } catch (EntityNotFoundException exc){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with that ID not found");
        } catch (Exception exc){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Other Server Side Error");
        }
    }

    @DeleteMapping(value = "/students/{id}")
    public void deleteStudent(@PathVariable("id") Long studentID){
        logger.info("Delete request for student {}", studentID);
        try{
            studentService.deleteStudent(studentID);
        } catch (EntityNotFoundException exc){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with that ID not found");
        } catch (Exception exc){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Other Server Side Error");
        }
    }
    
}
