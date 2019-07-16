package com.vilin.jpa.service;

import com.vilin.jpa.entity.Person;
import com.vilin.jpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public void getPersonUpAndDel(){
        personRepository.getPersonUpAndDel("孙悟空", 1l);
    }

    @Transactional
    public void addPersons(List<Person> list){
        personRepository.saveAll(list);
    }

    @Transactional
    public Page<Person> getPaging(){
        return personRepository.findAll(PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "id")));
    }

    @Transactional
    public void JpaRepository(Person person){
        personRepository.saveAndFlush(person);
    }
}
