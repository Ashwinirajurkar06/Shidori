package com.shidori.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shidori.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

}
