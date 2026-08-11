package com.example.Resturant.Service;

import com.example.Resturant.Model.ResturantDetails;
import com.example.Resturant.Model.ResturantStatus;
//import com.example.Resturant.Repo.Fooditemsrepo;
import com.example.Resturant.Repo.ResturentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class Resturentservice {

    @Autowired
    private ResturentRepo resturentRepo;

//    @Autowired
//    private Fooditemsrepo fooditemsrepo;

    public ResturantDetails saveResturentDetils(ResturantDetails resturantDetails) {
        resturantDetails.setStatus(ResturantStatus.OPEN);
        resturantDetails.setCreatedAt(LocalDateTime.now());
        resturantDetails.setUpdatedAt(LocalDateTime.now());
        return resturentRepo.save(resturantDetails);
    }

    public List<ResturantDetails> getAllResturentDetails() {
        return resturentRepo.findAll();
    }

    public Optional<ResturantDetails> getResturentDetailsById(Long id) {
        return resturentRepo.findById(id);
    }
    public ResturantDetails updateResturentDetailsById(Long id, ResturantDetails resturantDetails) {
        ResturantDetails existing = resturentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Resturant not found with id: " + id));

        existing.setResturantName(resturantDetails.getResturantName());
        existing.setDescription(resturantDetails.getDescription());
        existing.setPhone(resturantDetails.getPhone());
        existing.setEmail(resturantDetails.getEmail());
        existing.setAddress(resturantDetails.getAddress());
        existing.setCity(resturantDetails.getCity());
        existing.setOpeningTime(resturantDetails.getOpeningTime());
        existing.setClosingTime(resturantDetails.getClosingTime());
        existing.setStatus(resturantDetails.getStatus());
        existing.setUpdatedAt(LocalDateTime.now());

        return resturentRepo.save(existing);
    }
    public void deleteResturentDetailsById(Long id) {
        if (!resturentRepo.existsById(id)) {
            throw new RuntimeException("Resturant not found with id " + id);
        }
        resturentRepo.deleteById(id);
    }
    public ResturantDetails patchResturentDetailsById(Long id, ResturantDetails resturantDetails){
        ResturantDetails existing = resturentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Resturant not found with id: " + id));
        if (resturantDetails.getResturantName() != null) {
            existing.setResturantName(resturantDetails.getResturantName());
        }
        if (resturantDetails.getPhone() != null) {
            existing.setPhone(resturantDetails.getPhone());
        }
        if (resturantDetails.getEmail() != null) {
            existing.setEmail(resturantDetails.getEmail());
        }
        if (resturantDetails.getDescription() != null) {
            existing.setDescription(resturantDetails.getDescription());
        }
        if (resturantDetails.getAddress() != null) {
            existing.setAddress(resturantDetails.getAddress());
        }
        if (resturantDetails.getCity() != null) {
            existing.setCity(resturantDetails.getCity());
        }
        if (resturantDetails.getOpeningTime() != null) {
            existing.setOpeningTime(resturantDetails.getOpeningTime());
        }
        if (resturantDetails.getClosingTime() != null) {
            existing.setClosingTime(resturantDetails.getClosingTime());
        }
        if (resturantDetails.getStatus() != null) {
            existing.setStatus(resturantDetails.getStatus());
        }
        existing.setUpdatedAt(LocalDateTime.now());

        return resturentRepo.save(existing);

    }

}
