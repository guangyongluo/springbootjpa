package com.vilin.jpa;

import com.vilin.jpa.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

}
