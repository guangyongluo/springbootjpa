package com.vilin.jpa.service;

import com.vilin.jpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public void getPersonUpAndDel(){
        personRepository.getPersonUpAndDel("孙悟空", 1l);
    }
}
