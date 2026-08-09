package com.example.Addressdetailes.Service;

import com.example.Addressdetailes.Model.Addressdetails;
import com.example.Addressdetailes.Repo.AddressRepo;
import com.example.Addressdetailes.Respose.AddressRespose;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Employeeservice {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private ModelMapper modelMapper;

    public AddressRespose getaddressbyemplyeeid(Long employeeid) {
        Optional<Addressdetails> address = addressRepo.findaddressbyemplyeeid(employeeid);
        Addressdetails addressdetails = address.orElseThrow(() -> new RuntimeException("Address not found for employee id: " + employeeid));
        return modelMapper.map(addressdetails, AddressRespose.class);
    }
}
