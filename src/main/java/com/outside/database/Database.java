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
            List<Users> users = databaseRepository.findByEmail(email);

            return users;
        } catch (Exception e) {
            return null;
        }
    }

    public void insertUser(Users user) {
        try {
            databaseRepository.save(new Users(user.getEmail(), user.getToken(), user.getAccountType()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // public Connection Connect() {
    // String url =
    // "jdbc:postgresql://localhost:5432/outside?user=postgres&password=password";

    // try {
    // return DriverManager.getConnection(url);
    // } catch (SQLException e) {
    // return null;
    // }
    // }

}
