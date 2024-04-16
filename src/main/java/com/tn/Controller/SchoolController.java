package com.tn.Controller;


import com.tn.School;
import com.tn.SchoolReponsitory;
import com.tn.reg.SchoolregUpdate;
import com.tn.reg.Schoolreq;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class SchoolController {

    @Autowired
    private SchoolReponsitory schoolRepo;

    @GetMapping("getAll")
    public ResponseEntity<?> getAll() {
        List<School> schools = schoolRepo.findAll();

        return new ResponseEntity<>(schools, HttpStatus.OK);
    }
    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody Schoolreq schoolreq) {
        School school = new School();
        school.setSchoolName(schoolreq.getSchoolName());
        school.setAddress(schoolreq.getAddress());
        school.setNumTeacher(schoolreq.getNumTeacher());
        schoolRepo.save(school);
        return new ResponseEntity<>(school, HttpStatus.OK);
    }

    @PutMapping("school/{id}")
    public ResponseEntity<?>update(@PathVariable Integer id,
                                   @RequestBody SchoolregUpdate schoolregUpdate) {
        School school = schoolRepo.findById(id).orElse(null);
        if (school != null) {
            school.setSchoolName(schoolregUpdate.getSchoolName());
            school.setAddress(schoolregUpdate.getAddress());
            school.setNumTeacher(schoolregUpdate.getNumTeacher());
            schoolRepo.save(school);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("school/{id}")
    public ResponseEntity<?>delete(@PathVariable Integer id) {
        schoolRepo.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }


}
