package com.example.clickrush.repositories;

import com.example.clickrush.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
