package com.example.webservicedemo.service;

import com.example.webservicedemo.model.Student;
import com.example.webservicedemo.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo repo;


    private List<Student> studentList = Arrays.asList(
            new Student(1, "Jammy"),
            new Student(2, "Jannfer"),
            new Student(3, "Mila"),
            new Student(4, "Tom"),
            new Student(5, "Jerry"));


    public List<Student> findAll() {
        return repo.findAll();
    }

    public Student addNewStudent(Student student) {
        return repo.save(student);
    }

    public Optional<Student> findById(int id) {
        return repo.findById(id);
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }

//    public ResponseEntity<Student> updateStudent(int id, String name) {
//        return repo.findById(id);
//    }
}
