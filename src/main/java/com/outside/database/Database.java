package com.outside.database;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.ArrayList;

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
