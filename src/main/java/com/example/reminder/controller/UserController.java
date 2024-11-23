package com.example.reminder.controller;

import com.example.reminder.dto.UserDtoRq;
import com.example.reminder.dto.UserDtoRs;
import com.example.reminder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reminder/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDtoRq oAuth2User) {
        UserDtoRs userDtoRs = userService.saveOrUpdateUser(oAuth2User);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoRs);
    }

    @GetMapping("/{id}")
    public UserDtoRs getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
