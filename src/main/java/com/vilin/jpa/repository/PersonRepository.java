package com.vilin.jpa.repository;

import com.vilin.jpa.entity.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    Person getByFirstName(String firstName);

    List<Person> getByFirstNameEndingWithOrderByIdDesc(String firstName);

    List<Person> getByFirstNameStartingWithAndIdGreaterThan(String firstName, Long id);

    List<Person> getByFirstNameContainingAndIdGreaterThan(String firstName, Long id);

    List<Person> getByFirstNameOrFirstNameAndEmailAddressIsNotNull(String firstName1, String firstName2);

    List<Person> getByAddressIdGreaterThan(Long id);

    @Query("from Person p where p.id=(select min(p1.id) from Person p1)")
    Person getMiniPersonId();

    @Query("From Person p where p.firstName =?1 and p.id =?2")
    List<Person> getPerson(String firstName, Long id);

    @Query("From Person p where p.firstName =:firstName and p.id =:id")
    List<Person> getPerson2(@Param("firstName") String firstName, @Param("id") Long id);

    @Query("from Person p where p.firstName like %?1%")
    List<Person> getPersonLike(String firstName);

    @Query(value="select count(id) from jpa_person", nativeQuery = true)
    Long getPersonNumber();

    @Modifying
    @Query("update Person p set p.lastName =:lastName where p.id=:id")
    void getPersonUpAndDel(@Param("lastName") String lastName, @Param("id") Long id);


}
