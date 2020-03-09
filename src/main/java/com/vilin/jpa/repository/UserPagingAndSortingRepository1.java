package com.vilin.jpa.repository;

import com.vilin.jpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserPagingAndSortingRepository1 extends PagingAndSortingRepository<User, Long> {

    List<User> findAllByEmailContaining(String email, Sort sort);

    List<User> findByEmailContaining(String email, Pageable pageable);

    User findFirstByOrderByNameAsc();

    User findTopByOrderByEmailDesc();

    Page<User> queryFirst7ByName(String name, Pageable pageable);

    Slice<User> findTop3ByName(String name, Pageable pageable);

    List<User> findFirst5ByName(String name, Sort sort);

    List<User> findTop6ByName(String name, Pageable pageable);

}
