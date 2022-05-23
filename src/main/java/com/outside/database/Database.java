package com.outside.database;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import com.outside.database.Users;
import com.outside.database.DatabaseRepository;

@Component
public class Database {

    @Autowired
	DatabaseRepository databaseRepository;

	public List<Users> getUser(String email) {
		try {
			List<Users> users = new ArrayList<Users>();

			databaseRepository.findByEmail(email).forEach(users::add);
			return users;
		} catch (Exception e) {
            return null;
		}
	}

	// public Connection Connect() {
    //     String url = "jdbc:postgresql://localhost:5432/outside?user=postgres&password=password";

    //     try {
    //         return DriverManager.getConnection(url);
    //     } catch (SQLException e) {
    //         return null;
    //     }
	// }


}
