package com.outside.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

import com.outside.database.Database;
import com.outside.database.Users;

@Component
class RegisterService {
    @Autowired
    private Database database;

    protected Map<String, Object> registerNatif(String email, String password, String confirmPassword) {
        List<Users> users = null;

        if (!password.equals(confirmPassword)) {
            return Map.of(
                    "success", false,
                    "errorMsg", "password dont correspond to confirmPassword");
        }

        users = database.getUser(email);
        if (users != null && users.size() > 0)
            return Map.of(
                    "success", false,
                    "errorMsg", "User with this adress mail already exist");

        database.insertUser(new Users(email, password, "natif"));
        return Map.of(
                "success", true,
                "user", email /* should be token of jwt */);
    }
}
