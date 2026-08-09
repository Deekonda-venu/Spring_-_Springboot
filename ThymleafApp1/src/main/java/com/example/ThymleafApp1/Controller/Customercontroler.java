package com.example.ThymleafApp1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.ThymleafApp1.Model.Customer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.ThymleafApp1.Repo.CustomerRepo;

@Controller
public class Customercontroler {

    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/Home")
    String GetHomepage(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("customers", customerRepo.findAll());
        return "index";
    }
//    @Autowired
//    private CustomerRepo customerRepo;
//
    @PostMapping("/save")
    String saveCustomer(@ModelAttribute Customer customer) {
        customerRepo.save(customer);
        return "redirect:/Home";
    }

}
