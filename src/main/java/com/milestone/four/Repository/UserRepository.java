package com.milestone.four.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.milestone.four.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
