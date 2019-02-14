package com.myproject.myproject;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
public class controller {

    @RequestMapping("/users")
    public String getUsers() throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException, JSONException {
        System.out.println("Request aayi...");
        DbHandler dbHandler = new DbHandler();
        JSONArray users = dbHandler.getUsers();
        System.out.println(users);
        return users.toString();
    }
    @PostMapping("/add_user")
    public void addTodo(@RequestParam Map map){

    }
}