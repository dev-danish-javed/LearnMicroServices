package com.microservice.student.controllers;

import com.microservice.student.models.StudentDto;
import com.microservice.student.models.StudentRequest;
import com.microservice.student.models.StudentResponse;
import com.microservice.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentsController {
    @Autowired
    StudentService service;

    @PostMapping
    public ResponseEntity<StudentResponse> addStudent(@RequestBody StudentRequest studentRequest) {
        StudentDto studentDto = new StudentDto(studentRequest);
        StudentDto savedStudent = service.addStudent(studentDto);
        return savedStudent != null ? ResponseEntity.ok(new StudentResponse(savedStudent)) : new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable long id){
        StudentDto savedStudent = service.getStudent(id);
        return savedStudent != null ? ResponseEntity.ok(new StudentResponse(savedStudent)) : new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
