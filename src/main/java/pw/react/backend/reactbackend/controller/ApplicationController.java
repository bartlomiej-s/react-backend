package pw.react.backend.reactbackend.controller;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.react.backend.reactbackend.entity.User;
import pw.react.backend.reactbackend.service.UserService;

@RestController
@RequestMapping("/postgresApp")
public class ApplicationController {

    @Resource
    UserService userService;

    /*@GetMapping(value = "/userList")
    public List<User> getUsers() {
        return userService.findAll();
    }*/



    @PostMapping(value = "/user")
    @ResponseBody
    public ResponseEntity createUser(@RequestBody User usr){
        return userService.insertUser(usr);
    }

    /*@PutMapping(value = "/updateUsr")
    public void updateEmployee(@RequestBody User usr) {
        userService.updateUser(usr);
    }*/

    /*@PutMapping(value = "/executeUpdateUsr")
    public void executeUpdateUser(@RequestBody User usr) {
        userService.executeUpdateUser(usr);
    }*/

    /*@DeleteMapping(value = "/deleteUsrById")
    public void deleteUser(@RequestBody User usr) {
        userService.deleteUser(usr);
    }*/

}