package com.outside.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

import com.outside.database.Database;
import com.outside.database.Users;

@Component
class LoginService {
    @Autowired
    private Database database;

    protected Map<String, Object> loginNatif(String email, String password) {
        List<Users> users = database.getUser(email);

        if (users == null || users.isEmpty())
            return Map.of(
                    "success", false,
                    "errorMsg", "User not found");
        if (users.get(0).getToken().equals(password) == false) {
            return Map.of(
                    "success", false,
                    "errorMsg", "Password incorrect");
        }
        return Map.of(
                "success", true,
                "user", users.get(0));
    }
}
