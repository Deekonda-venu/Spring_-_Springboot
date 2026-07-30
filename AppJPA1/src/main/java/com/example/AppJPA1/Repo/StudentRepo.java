package com.example.AppJPA1.Repo;
import com.example.AppJPA1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StudentRepo extends JpaRepository<Student , Integer>{

    public abstract Student findBysfname(String sfname);
}
