package com.fullstackapp.fullstackbackend.repository;

import com.fullstackapp.fullstackbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// uses jpa repository with user and long as args to implement id
public interface UserRepo extends JpaRepository<User, Long> {

}
