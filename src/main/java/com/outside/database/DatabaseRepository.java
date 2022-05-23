package com.outside.database;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.outside.database.Users;

public interface DatabaseRepository extends JpaRepository<Users, Long> {
    List<Users> findAll();
    List<Users> findByEmail(String email);
}