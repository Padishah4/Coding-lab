package com.example.demo.web;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.facade.UserFasade;
import com.example.demo.services.UserService;
import com.example.demo.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.http.HttpHeaders;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserFasade userFasade;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @GetMapping("/")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal){
        User user = userService.getCurrentUser(principal);
        UserDTO userDTO = userFasade.userToUserDto(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable("userId") String userId){
        User user = userService.getUserById(Long.parseLong(userId));
        UserDTO userDTO = userFasade.userToUserDto(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        User user = userService.updateUser(userDTO,principal);

        UserDTO userUpdated = userFasade.userToUserDto(user);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @GetMapping("/pull")
    public ResponseEntity<Page<UserDTO>> getAllUsers(@PageableDefault(size = 1, sort = {"pull"}, direction = Sort.Direction.DESC) Pageable pageable)
    {
        Page<UserDTO> pageDTO = getSortedUsers(pageable);

        return new ResponseEntity<>(pageDTO, HttpStatus.OK);
    }
    @GetMapping("/squat")
    public ResponseEntity<Page<UserDTO>> getAllUsersSquat(@PageableDefault(size = 1, sort = {"squat"}, direction = Sort.Direction.DESC) Pageable pageable)
    {
        Page<UserDTO> pageDTO = getSortedUsers(pageable);

        return new ResponseEntity<>(pageDTO, HttpStatus.OK);
    }


    private Page<UserDTO> getSortedUsers(Pageable pageable) {
        Page<User> page = userService.getAllUsers(pageable);
        Page<UserDTO> pageDTO = page.map(userFasade::userToUserDto);
        return pageDTO;
    }




}
