package pw.react.backend.reactbackend.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
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
    public Object getUser(@RequestBody String login, @RequestHeader("password") String password) {
        if (password.equals("postgrespass")) return userService.findUser(login);
        else return new ResponseEntity("You have entered wrong password in security \"password\" header.", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value = "/user")
    @ResponseBody
    public ResponseEntity createUser(@RequestBody User usr, @RequestHeader("password") String password){
        if (password.equals("postgrespass")) return userService.insertUser(usr);
        else return new ResponseEntity("You have entered wrong password in security \"password\" header.", HttpStatus.UNAUTHORIZED);
    }

    @PutMapping(value = "/user")
    public ResponseEntity updateEmployee(@RequestBody User usr, @RequestHeader("password") String password) {
        if (password.equals("postgrespass")) return userService.updateUser(usr);
        else return new ResponseEntity("You have entered wrong password in security \"password\" header.", HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping(value = "/user")
    public ResponseEntity deleteUser(@RequestBody String login, @RequestHeader("password") String password) {
        if (password.equals("postgrespass")) return userService.deleteUser(login);
        else return new ResponseEntity("You have entered wrong password in security \"password\" header.", HttpStatus.UNAUTHORIZED);
    }

}