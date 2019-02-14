package com.myproject.myproject;
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
//        System.out.println(users);
        return dbHandler.getUsers().toString();
    }
    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public String addAndGetUsers(@RequestParam Map<String,String> body) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException, JSONException {
//        System.out.println("Received POST request:" + body.get("username"));
        DbHandler dbHandler = new DbHandler();
        dbHandler.addUser(body);
        return dbHandler.getUsers().toString();
    }
}