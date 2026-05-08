package com.example.finance.service;

import com.example.finance.user.dtos.UserResponseDTO;
import com.example.finance.user.dtos.UserRegistrationDTO;
import com.example.finance.user.entities.User;
import com.example.finance.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository repository;

    public UserResponseDTO saveUser(UserRegistrationDTO dto) {
        User newUser = new User();
        newUser.setName(dto.name());
        newUser.setPassword(dto.password());
        newUser.setEmail(dto.email());

        newUser = repository.save(newUser);
        return UserResponseDTO.fromEntity(newUser);
    }

    public UserResponseDTO findUserById(String id) {
        System.out.println(id);
        User user = repository.findById(id).orElseThrow();
        return UserResponseDTO.fromEntity(user);
    }

    public void deleteUserById(String id) throws Exception {   // When implementing the financial system, correct this method
        if(!repository.existsById(id)) {
            System.out.println("Id not found");    // Replace this line with an exception
        }

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            System.out.println("Error: " + exception);     // Replace this line with an exception
        }
    }

    public List<UserResponseDTO> findAllUsers() {
        return repository.findAll().stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getPassword(),
                        user.getEmail()
                )).toList();
    }

    public UserResponseDTO updateUser(UserRegistrationDTO dto, String id) {
        User userToModify = repository.findById(id).orElseThrow();

        userToModify.setName(dto.name());
        userToModify.setEmail(dto.email());
        userToModify.setPassword(dto.password());

        userToModify = repository.save(userToModify);
        return UserResponseDTO.fromEntity(userToModify);
    }
}