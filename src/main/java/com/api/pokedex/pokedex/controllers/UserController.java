package com.api.pokedex.pokedex.controllers;

import com.api.pokedex.pokedex.dtos.TimePokemonDto;
import com.api.pokedex.pokedex.dtos.UserDto;
import com.api.pokedex.pokedex.dtos.UserRespostaDto;
import com.api.pokedex.pokedex.models.UserModel;
import com.api.pokedex.pokedex.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto){
        try {
            UserModel user = new UserModel();
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());

            userService.save(user);

            return ResponseEntity.ok("Usuário criado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar usuário");
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserModel> users = userService.findAll();
        List<UserDto> userDTOs = new ArrayList<>();

        for (UserModel user : users) {
            UserDto userDTO = new UserDto();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTOs.add(userDTO);
        }
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") Long id){
        Optional<UserModel> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserModel user = optionalUser.get();
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());

        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long id){
        Optional<UserModel> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserModel user = optionalUser.get();
        userService.delete(user);

        return ResponseEntity.ok("Usuário excluído com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") Long id,
                                                    @RequestBody @Valid UserDto userDto){
        Optional<UserModel> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserModel user = optionalUser.get();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        userService.save(user);

        return ResponseEntity.ok("Usuário atualizado com sucesso");
    }
}
