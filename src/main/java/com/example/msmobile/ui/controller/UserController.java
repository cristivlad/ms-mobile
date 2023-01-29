package com.example.msmobile.ui.controller;

import com.example.msmobile.ui.model.request.UpdateUserDetailsRequestModel;
import com.example.msmobile.ui.model.request.UserDetailsRequestModel;
import com.example.msmobile.ui.model.response.UserRest;
import com.example.msmobile.ui.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    Map<String, UserRest> users = new HashMap<>();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(@RequestParam(value = "pg", defaultValue = "1") int pg, @RequestParam(value = "sz", defaultValue = "15") int sz) {

        return "get users with pg " + pg + " and sz " + sz;
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserRest> getUser(@PathVariable(value = "userId") String userId) {
        if (users.containsKey(userId))
            return ok(users.get(userId));

        return noContent().build();
    }

    @PostMapping
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel user) {
        UserRest returnedUser = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnedUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserRest> updateUser(@PathVariable(value = "userId") String userId, @Valid @RequestBody UpdateUserDetailsRequestModel updateUser) {
        UserRest returnedUser = users.get(userId);
        returnedUser.setFirstName(updateUser.getFirstName());
        returnedUser.setLastName(updateUser.getLastName());

        users.put(userId, returnedUser);

        return ResponseEntity.ok(returnedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "userId") String userId) {
        users.remove(userId);
        return noContent().build();
    }
}
