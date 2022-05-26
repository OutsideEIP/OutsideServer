package com.outside.database;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DatabaseRepository extends JpaRepository<Users, Long> {
    List<Users> findAll();
    List<Users> findByEmail(String email);
}