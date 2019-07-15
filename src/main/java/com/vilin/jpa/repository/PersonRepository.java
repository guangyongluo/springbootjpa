package com.vilin.jpa.repository;

import com.vilin.jpa.entity.Person;
import org.springframework.data.repository.Repository;
import java.util.List;

public interface PersonRepository extends Repository<Person, Long> {

    Person getByFirstName(String firstName);

    List<Person> getByFirstNameEndingWithOrderByIdDesc(String firstName);

    List<Person> getByFirstNameStartingWithAndIdGreaterThan(String firstName, Long id);

    List<Person> getByFirstNameContainingAndIdGreaterThan(String firstName, Long id);

    List<Person> getByFirstNameOrFirstNameAndEmailAddressIsNotNull(String firstName1, String firstName2);

}
