package com.vilin.jpa.controller;

import com.vilin.jpa.entity.User;
import com.vilin.jpa.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/user3")
public class UserController3 {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserJpaRepository userJpaRepository;

    @RequestMapping("/findUserByEmail")
    @ResponseBody
    public User findUserByEmail(String email){
        return userJpaRepository.findByEmail(email);
    }

    @RequestMapping("/findByNameEndsWith")
    @ResponseBody
    public List<User> findByNameEndsWith(String name){
        return userJpaRepository.findByNameEndsWith(name);
    }

    @RequestMapping("/getUserIdList")
    @ResponseBody
    @Transactional
    public List<Long> getUserIdList(){
        Stream<User> stream = userJpaRepository.findAllQueryAndStream();
        return stream.map(User::getId).collect(Collectors.toList());
    }

    @RequestMapping("/findByNameBySort")
    @ResponseBody
    public List<User> findByName(String name){
        Sort sort = Sort.by("id").descending();
        return userJpaRepository.findByName(name, sort);
    }

    @RequestMapping("/findByNameSortBy")
    @ResponseBody
    public List<User> findByNameSort(String name, String sort){
        String sql = "select id, name, email from User order by " + sort +" desc";
        Query query = entityManager.createQuery(sql);
        List<User> list = query.getResultList();
        return list;
    }

    @RequestMapping("/findByNameByPage")
    @ResponseBody
    public List<User> findByNameByPage(String name){
        Pageable pageable = PageRequest.of(1, 5);
        return userJpaRepository.findByNameByPage(name, pageable);
    }

    @RequestMapping("/findByNamedParam")
    @ResponseBody
    public List<User> findByNamedParam(String name){
        return userJpaRepository.findByNamedParam(name);
    }

    @RequestMapping("/findBySpEL")
    @ResponseBody
    public List<User> findBySpEL(String name){
        return userJpaRepository.findBySpEL(name);
    }

    @RequestMapping("/setFixedEmail")
    @ResponseBody
    public int setFixedEmailFor(String email, String name){
        return userJpaRepository.setFixedEmailFor(email, name);
    }

    @RequestMapping("/deleteByUserId")
    @ResponseBody
    public void deleteUserById(Long id){
        userJpaRepository.deleteInBulkByUserId(id);
    }
}
