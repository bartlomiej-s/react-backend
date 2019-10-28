package pw.react.backend.reactbackend.controller;

import java.util.List;
import javax.annotation.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import pw.react.backend.reactbackend.entity.User;
import pw.react.backend.reactbackend.dao.UserDaoImpl;

@RestController
@RequestMapping("/postgresApp")
public class ApplicationController {

    @Resource
    UserDaoImpl userService;

    @GetMapping(value = "/userList")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @PostMapping(value = "/createUsr") //consumes = "application/json"
    @ResponseBody
    //public void createUser(HttpEntity<String> httpEntity) throws Exception {
    public void createUser(@RequestBody User usr) {
        /*String json = httpEntity.getBody();
        json = json.replaceAll("\n", "");
        User usr = new User();*/

        /*Long idX = null;
        StringBuilder s = new StringBuilder(""+json.charAt(0)+json.charAt(1));
        if (s.toString()=="id")
        {
            idX = Long.parseLong(""+s.charAt(5));
        }
        for (int i = 2; i < json.length(); i++)
        {
            s.setCharAt(0, s.toString().charAt(1));
            s.setCharAt(1, json.charAt(i));
            if (s.toString()=="id")
            {
                idX = Long.parseLong(""+s.charAt(5));
                break;
            }
        }*/
        userService.insertUser(usr);
    }

    @PutMapping(value = "/updateUsr")
    public void updateEmployee(@RequestBody User usr) {
        userService.updateUser(usr);
    }

    @PutMapping(value = "/executeUpdateUsr")
    public void executeUpdateUser(@RequestBody User usr) {
        userService.executeUpdateUser(usr);
    }

    @DeleteMapping(value = "/deleteUsrById")
    public void deleteUser(@RequestBody User usr) {
        userService.deleteUser(usr);
    }

}