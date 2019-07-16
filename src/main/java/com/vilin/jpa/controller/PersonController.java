package com.vilin.jpa.controller;

import com.vilin.jpa.entity.Person;
import com.vilin.jpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(path = "/getbyname")
    @ResponseBody
    public Person getPersonByName(String firstName){
        return personRepository.getByFirstName(firstName);
    }

    @RequestMapping(path = "/getbynameandid")
    @ResponseBody
    public List<Person> getPersonsByFirstNameAndId(String firstName, Long id){
        return personRepository.getByFirstNameStartingWithAndIdGreaterThan(firstName, id);
    }

    @RequestMapping(path = "/getbyfirstname")
    @ResponseBody
    public List<Person> getPersonsByFirstName(String firstName, Long id){
        return personRepository.getByFirstNameContainingAndIdGreaterThan(firstName, id);
    }

    @RequestMapping(path = "/getbyfirstnameorderbyId")
    @ResponseBody
    public List<Person> getPersonsByFirstNameOrderById(String firstName){
        return personRepository.getByFirstNameEndingWithOrderByIdDesc(firstName);
    }

    @RequestMapping(path = "/getbyfirstnameandfirstname")
    @ResponseBody
    public List<Person> getPersonsByFirstNameOrderById(String firstName1, String firstName2){
        return personRepository.getByFirstNameOrFirstNameAndEmailAddressIsNotNull(firstName1, firstName2);
    }

    @RequestMapping(path = "/getbyfaddressid")
    @ResponseBody
    public List<Person> getPersonsByAddressId(Long id){
        return personRepository.getByAddressIdGreaterThan(id);
    }

    @RequestMapping(path = "/getminpersonid")
    @ResponseBody
    public Person getPersonsByAddressId(){

        return personRepository.getMiniPersonId();
    }

    @RequestMapping(path = "/getperson")
    @ResponseBody
    public List<Person> getPersons(String firstName, Long id){
        return personRepository.getPerson(firstName, id);
    }

    @RequestMapping(path = "/getpersonlike")
    @ResponseBody
    public List<Person> getPersonLike(String firstName){
        return personRepository.getPersonLike(firstName);
    }

    @RequestMapping(path = "/getpersonnum")
    @ResponseBody
    public Long getPersonNumber(){
        return personRepository.getPersonNumber();
    }

}
