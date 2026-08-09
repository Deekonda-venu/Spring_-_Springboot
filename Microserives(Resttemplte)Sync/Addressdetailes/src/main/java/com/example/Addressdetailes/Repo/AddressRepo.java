package com.example.Addressdetailes.Repo;

import com.example.Addressdetailes.Model.Addressdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Addressdetails, Long> {

    @Query(nativeQuery = true, value = "select * from address_table join employee_table on address_table.employee_id = employee_table.id where employee_table.id = :employeeid")
    Optional<Addressdetails> findaddressbyemplyeeid(@Param("employeeid") Long employeeid);
}
