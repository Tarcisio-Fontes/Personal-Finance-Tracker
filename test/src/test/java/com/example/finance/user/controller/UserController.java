package com.example.finance.user.controllers;

import com.example.finance.user.dtos.UserResponseDTO;
import com.example.finance.user.dtos.UserRegistrationDTO;
import com.example.finance.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.findAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String id) {
        UserResponseDTO dto = userService.findUserById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> insertUser(@RequestBody UserRegistrationDTO dto) {
        UserResponseDTO userResponseDTO = userService.saveUser(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.email()).toUri();
        return ResponseEntity.created(uri).body(userResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) throws Exception {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String id, @RequestBody UserRegistrationDTO dto) {
        UserResponseDTO userRegistrationDTO = userService.updateUser(dto,id);
        return ResponseEntity.ok(userRegistrationDTO);
    }
}