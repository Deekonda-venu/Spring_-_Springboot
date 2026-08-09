package com.example.Addressdetailes.Repo;

import com.example.Addressdetailes.Model.Addressdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Addressdetails, Long> {

    @Query(nativeQuery = true, value = "select a.id, a.employee_id, a.street, a.city, a.state, a.zip_code from address_table a join employedetailes e on a.employee_id = e.id where e.id = :employeeid")
    Optional<Addressdetails> findaddressbyemplyeeid(@Param("employeeid") Long employeeid);
}
