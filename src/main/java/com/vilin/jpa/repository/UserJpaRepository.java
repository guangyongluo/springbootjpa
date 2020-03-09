package com.vilin.jpa.repository;

import com.vilin.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository  extends JpaRepository<User, Long> {
}
