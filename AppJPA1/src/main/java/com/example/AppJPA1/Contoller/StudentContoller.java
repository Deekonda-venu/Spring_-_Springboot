package com.example.AppJPA1.Contoller;
import com.example.AppJPA1.model.Student;
import org.springframework.web.bind.annotation.*;
import com.example.AppJPA1.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/API/v1")
public class StudentContoller {

    @Autowired
    private StudentRepo studentRepo;

//    @GetMapping("/student")
//    Student getAllStudent(){
//
//        return ;
//    }

    @GetMapping("/hel")
    String hello(){

        return "Hello";
    }

    @GetMapping("/getstudentlist")
    List<Student> getAllStudent(){

        List<Student> list = new ArrayList<>();
        list = studentRepo.findAll();

        return list;
    }
    @GetMapping("/getById/{id}")
    Optional<Student> getStudentById(@PathVariable int id){
        Optional<Student> student = studentRepo.findById(id);

        return student;
    }

    @GetMapping("/getByFname/{fname}")
    Student getStudentById(@PathVariable String fname){
        Student student = studentRepo.findBysfname(fname);
        return student;
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    // student alsi we can put as return type but we can based on req , if you want to show student datikls then put student otherwise put sting somthunb
    Student createStudent(@RequestBody Student student){

        student = studentRepo.save(student);

        return student;


    }

    @PutMapping("/update/{sid}")
    ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable int sid){

        Student student1 = studentRepo.findById(sid)
                .orElseThrow(() -> new ResourcenotnullExexption("Invalid id"));

        student1.setSfname(student.getSfname());
        student1.setSlname(student.getSlname());
        student1.setSphone(student.getSphone());
        student1.setSemail(student.getSemail());

        Student updatedStudent = studentRepo.save(student1);
        return ResponseEntity.ok(updatedStudent);
    }
//    String createStudent(@RequestBody Student student){



//        Student existingStudent = studentRepo.findBysfname(student.getSfname());
//        if(existingStudent != null){
//            return "Student already exists";
//        }
//
//        studentRepo.save(student);
//        return "successfully created";
//        }

//        my logic
//        List<Student> studentAllList = new ArrayList<>();
//        studentAllList = studentRepo.findAll();
//        if(!studentAllList.contains(student.getSfname())){
//            student = studentRepo.save(student);
//            return "successfully creared";
//
//        }

//        return "Student already exists";


//    }

    @DeleteMapping("/Delete/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)  --> if you use this you no need to add retuen for method
    String deleteStudent(@PathVariable int id){
        studentRepo.deleteById(id);

        return "Succeslly deleted student";
    }



}
