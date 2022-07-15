package com.example.webservicedemo.controller;

import com.example.webservicedemo.model.Student;
import com.example.webservicedemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/")
public class StudentController {

    @Autowired
    private StudentService service;


    @PostMapping("/addstudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        service.addNewStudent(student);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students")
    public List<Student> allStudents() {
        return service.findAll();
    }

    @GetMapping("/studentbyid/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable(value = "id") int id) {
        Optional<Student> optionalStudent = service.findById(id);
        if (optionalStudent.isPresent()) {
            return new ResponseEntity<>(optionalStudent.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updatestudent/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") int id, @RequestBody Student student) {
        Optional<Student> optionalStudent = service.findById(id);

        if (optionalStudent.isPresent()) {
            Student s = optionalStudent.get();
            s.setName(student.getName());

            return new ResponseEntity<>(service.addNewStudent(s), HttpStatus.ACCEPTED);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable(value = "id") int id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}


