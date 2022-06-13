package com.example.demo.facade;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserFasade {
    public UserDTO userToUserDto(User user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setFirstname(user.getName());
    userDTO.setLastname(user.getLastname());
    userDTO.setUsername(user.getUsername());
    userDTO.setBio(user.getBio());
    userDTO.setPull(user.getPull());
    userDTO.setBench(user.getBench());
    userDTO.setSquat(user.getSquat());
    return userDTO;


    }
}
