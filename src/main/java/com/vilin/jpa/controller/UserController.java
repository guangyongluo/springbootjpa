package com.vilin.jpa.controller;

import com.vilin.jpa.entity.User;
import com.vilin.jpa.repository.UserPagingAndSortingRepository;
import com.vilin.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/demo")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPagingAndSortingRepository userPagingAndSortingRepository;

    @GetMapping(path = "/add")
    public void addNewUser(@RequestParam String name, @RequestParam String email){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/info")
    @ResponseBody
    public User findOne(@RequestParam Long id){
        return userRepository.findById(id).get();
    }

    @GetMapping(path = "/delete")
    public void delete(@RequestParam Long id){
        userRepository.deleteById(id);
    }

    @GetMapping(path = "/page")
    @ResponseBody
    public Page<User> getAllUserByPage(){
        Sort.Order order = Sort.Order.asc("name");
        Page<User> page =  userPagingAndSortingRepository.findAll(PageRequest.of(0, 2, Sort.by(order)));
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("总条数：" +page.getTotalElements());
        return page;
    }

    @GetMapping(path = "/sort")
    @ResponseBody
    public Iterable<User> getAllUserWithSort(){
        Sort.Order order = Sort.Order.asc("name");
        return userPagingAndSortingRepository.findAll(Sort.by(order));
    }

    @GetMapping("/page2")
    @ResponseBody
    public List<User> getUsersByPage(String email){
        Pageable pageable = PageRequest.of(0, 2);
        Page<User> page = userPagingAndSortingRepository.findAllByEmailContaining(email, pageable);
        System.out.println(page.getTotalElements());
        return page.getContent();
    }

    @GetMapping("/slice")
    @ResponseBody
    public List<User> getUsersBySlice(String email){
        Pageable pageable = PageRequest.of(0, 2);
        Slice<User> slice = userPagingAndSortingRepository.findByEmailContaining(email, pageable);
        return slice.getContent();
    }
}
