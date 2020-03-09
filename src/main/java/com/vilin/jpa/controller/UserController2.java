package com.vilin.jpa.controller;

import com.vilin.jpa.projections.NamesOnly;
import com.vilin.jpa.repository.UserProjectionRepository;
import com.vilin.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user2")
public class UserController2 {

    @Autowired
    UserProjectionRepository userProjectionRepository;

    @RequestMapping("/projection")
    @ResponseBody
    public String getUserLastName(String lastName){
        List<NamesOnly> list = userProjectionRepository.findByLastName(lastName);
        return list.toString();
    }

}
