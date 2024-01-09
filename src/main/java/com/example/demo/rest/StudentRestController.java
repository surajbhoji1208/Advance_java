package com.example.demo.rest;

import com.example.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private  List<Student> theStudent;
    //define @PostCostructor to load student data ... only once!

    @PostConstruct  //it will load at the beean is created
    public  void loadData()
    {
        theStudent = new ArrayList<>();
        theStudent.add(new Student("sanju","ghorpade"));
        theStudent.add(new Student("madhu","narake"));
        theStudent.add(new Student("aarya","salunke"));

    }
    @GetMapping("/students")
    public List<Student> getStudents()
    {
        return  theStudent;
    }

    //den=fine endpoint or "/students/{studentId}" - return strudent ad index


    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId)
    {
        //check student id against list size
        if((studentId >= theStudent.size()) || (studentId < 0))
        {
            throw new StudentNotFoundException("student id not found-" + studentId);
        }
        return theStudent.get(studentId);
    }



}
