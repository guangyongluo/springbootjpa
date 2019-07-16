package com.vilin.jpa;

import com.vilin.jpa.entity.Person;
import com.vilin.jpa.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTests {

    @Autowired
    PersonService personService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void personUpAndDel(){
        personService.getPersonUpAndDel();
    }

    @Test
    public void addPersons(){
        ArrayList<Person> list = new ArrayList<Person>();
        for(int i = 0; i < 10; i++){
            Person person = new Person();
            person.setFirstName("000" + i);
            person.setLastName(i + "000");
            person.setEmailAddress("000" + i + "@outlook.com");
            list.add(person);
        }
        personService.addPersons(list);
    }

}
