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

    @GetMapping(value = "/user")
    @ResponseBody
    public Object getUser(@RequestBody String login) {
        return userService.findUser(login);
    }

    @PostMapping(value = "/user")
    @ResponseBody
    public ResponseEntity createUser(@RequestBody User usr){
        return userService.insertUser(usr);
    }

    @PutMapping(value = "/user")
    public ResponseEntity updateEmployee(@RequestBody User usr) {
        return userService.updateUser(usr);
    }

    @DeleteMapping(value = "/user")
    public ResponseEntity deleteUser(@RequestBody String login) {
        return userService.deleteUser(login);
    }

}