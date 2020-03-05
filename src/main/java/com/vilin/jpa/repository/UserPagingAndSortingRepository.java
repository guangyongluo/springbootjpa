package com.vilin.jpa.repository;

import com.vilin.jpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserPagingAndSortingRepository extends PagingAndSortingRepository<User, Long> {

    Page<User> findAllByEmailContaining(String email, Pageable pageable);

    Slice<User> findByEmailContaining(String email, Pageable pageable);
}
