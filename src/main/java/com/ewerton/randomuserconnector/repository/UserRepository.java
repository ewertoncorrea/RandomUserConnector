package com.ewerton.randomuserconnector.repository;

import com.ewerton.randomuserconnector.models.RandomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<RandomUser, Long> {
}
