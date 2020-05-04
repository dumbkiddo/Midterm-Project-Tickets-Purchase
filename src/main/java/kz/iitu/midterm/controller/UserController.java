package kz.iitu.midterm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.iitu.midterm.entity.User;
import kz.iitu.midterm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(value = "User Controller class", description = "This class is used for editing accessing and editing user details")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/welcome")
    public String hello() {
        return "Welcome to the application!";
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @ApiOperation(value = "Method for adding new users")
    @GetMapping("/create")
    public void createUserByUsernamePassword(String username,
                                             String password) {
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);

        userService.createUser(user);
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        System.out.println("UserController.createUser");
        System.out.println("user = " + user);

        userService.createUser(user);
    }

    @ApiOperation(value = "Edit/update user by id")
    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id,
                           @RequestBody User user) {

        System.out.println("UserController.updateUser");
        System.out.println("id = " + id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication.getName() = " + authentication.getName());

        userService.updateUser(id, user);
    }
}
