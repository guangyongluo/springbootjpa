package com.vilin.jpa.controller;

import com.vilin.jpa.entity.User;
import com.vilin.jpa.repository.UserPagingAndSortingRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user1")
public class UserController1 {
    @Autowired
    private UserPagingAndSortingRepository1 userPagingAndSortingRepository1;

    @RequestMapping("/sort1")
    @ResponseBody
    public List<User> findAllByEmailContaining(String email){
        Sort sort = Sort.by("name");
        return userPagingAndSortingRepository1.findAllByEmailContaining(email, sort);
    }

    @RequestMapping("/sortByPage1")
    @ResponseBody
    public List<User> findByEmailContaining(String email){
        Pageable pageable = PageRequest.of(0, 2);
        return userPagingAndSortingRepository1.findByEmailContaining(email, pageable);
    }

    @RequestMapping("/findFirstUser")
    @ResponseBody
    public User findFirstByOrderByNameAsc(){
        return userPagingAndSortingRepository1.findFirstByOrderByNameAsc();
    }

    @RequestMapping("/findTopUser")
    @ResponseBody
    public User findTopByOrderByEmailDesc(){
        return userPagingAndSortingRepository1.findTopByOrderByEmailDesc();
    }

    @RequestMapping("/queryFirst5Users")
    @ResponseBody
    public List<User> queryFirst5ByName(String name){
        Pageable pageable = PageRequest.of(0, 3);
        return userPagingAndSortingRepository1.queryFirst7ByName(name, pageable).getContent();
    }

    @RequestMapping("/findTop3Users")
    @ResponseBody
    public List<User> findTop3ByName(String name){
        Pageable pageable = PageRequest.of(1, 5);
        return userPagingAndSortingRepository1.findTop3ByName(name, pageable).getContent();
    }

    @RequestMapping("/findFirst5Users")
    @ResponseBody
    public List<User> findFirst5ByName(String name){
        Sort sort = Sort.by("email");
        return userPagingAndSortingRepository1.findFirst5ByName(name, sort);
    }

    @RequestMapping("/findTop6Users")
    @ResponseBody
    public List<User> findTop6ByName(String name){
        Pageable pageable = PageRequest.of(1, 7);
        return userPagingAndSortingRepository1.findTop6ByName(name, pageable);
    }
}
