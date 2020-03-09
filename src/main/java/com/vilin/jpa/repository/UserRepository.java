package com.vilin.jpa.repository;

import com.vilin.jpa.entity.User;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {
    @Procedure("pluslinout")
    Integer explicitlyNamedPluslinout(Integer arg);

    @Procedure(procedureName = "pluslinout")
    Integer pluslinout(Integer arg);

    @Procedure(name = "User.plus1")
    Integer entityAnnotatedCustomNamedProcedurePluslIO(@Param("arg") Integer param);

    User findByNameForNamedQuery(String name);
}
