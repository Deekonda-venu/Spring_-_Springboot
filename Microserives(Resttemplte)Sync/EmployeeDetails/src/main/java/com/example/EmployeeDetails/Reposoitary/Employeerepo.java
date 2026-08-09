package com.example.EmployeeDetails.Reposoitary;

import com.example.EmployeeDetails.Model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Employeerepo extends JpaRepository<Employe, Integer> {

}
