package com.example.ThymleafApp1.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ThymleafApp1.Model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
