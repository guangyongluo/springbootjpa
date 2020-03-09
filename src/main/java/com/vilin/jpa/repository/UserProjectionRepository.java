package com.vilin.jpa.repository;

import com.vilin.jpa.entity.Person;
import com.vilin.jpa.projections.NamesOnly;
import org.springframework.data.repository.Repository;
import java.util.List;

public interface UserProjectionRepository extends Repository<Person, Long> {
    List<NamesOnly> findByLastName(String name);
}
