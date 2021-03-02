package com.ex.demostudent.controller;

import com.ex.demostudent.dto.StudentDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("StudentMS")
public interface StudentFeign {
    
    @RequestMapping(value="/student/{studentID}")
	StudentDTO getSpecificStudent(@PathVariable("studentID") int studentID);

}
