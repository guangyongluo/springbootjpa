package com.vilin.jpa.controller;

import com.vilin.jpa.entity.Customer;
import com.vilin.jpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/findAllByExample")
    @ResponseBody
    public List<Customer> findAllByExample(String name, String address){
        return customerService.findAllCustomersByExample(name, address);
    }

    @RequestMapping("/saveCustomer")
    @ResponseBody
    public Customer saveCustomer(String name, String address){
        return customerService.saveCustomer(name, address);
    }
}
