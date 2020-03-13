package com.vilin.jpa.service;

import com.vilin.jpa.dao.CustomerDao;
import com.vilin.jpa.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> findAllCustomersByExample(String name, String address){
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withIgnorePaths("focus");
        Example<Customer> example = Example.of(customer, matcher);
        List<Customer> list = customerDao.findAll(example);
        return list;
    }

    public Customer saveCustomer(String name, String address){
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        return customerDao.save(customer);
    }
}
