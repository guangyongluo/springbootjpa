package com.vilin.jpa.repository;

import com.vilin.jpa.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

public interface UserJpaRepository  extends JpaRepository<User, Long> {

    @Query("select  u from User u where u.email = ?1")
    User findByEmail(String email);

    @Query("select u from User u where u.name like %?1")
    List<User> findByNameEndsWith(String name);

    @Query("select u from User u")
    Stream<User> findAllQueryAndStream();

//    @Query(value="select * from user where name = ?1 order by ?2 desc ", nativeQuery = true)
//    List<User> findByName(String name, String sort);

    @Query("select u from User u where u.name like ?1%")
    List<User> findByName(String name, Sort sort);

    @Query("select u from User u where u.name = ?1")
    List<User> findByNameByPage(String name, Pageable pageable);

    @Query("select u from User u where u.name = :userName")
    List<User> findByNamedParam(@Param("userName") String name);

    @Query("select u from #{#entityName} u where u.name = ?1")
    List<User> findBySpEL(String name);

    @Modifying
    @Query("update User u set u.email = ?1 where u.name = ?2")
    @Transactional
    int setFixedEmailFor(String email, String name);

    @Modifying
    @Query("delete from User u where u.id = ?1")
    @Transactional
    void deleteInBulkByUserId(Long userId);
}
