package com.api.pokedex.pokedex.controllers;

import com.api.pokedex.pokedex.dtos.TimePokemonDto;
import com.api.pokedex.pokedex.models.TimePokemonModel;
import com.api.pokedex.pokedex.models.UserModel;
import com.api.pokedex.pokedex.services.TimePokemonService;
import com.api.pokedex.pokedex.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/times-pokemon")
public class TimePokemonController {

    final TimePokemonService timePokemonService;
    final UserService userService;

    public TimePokemonController(TimePokemonService timePokemonService, UserService userService) {
        this.timePokemonService = timePokemonService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveTimePokemon(@RequestBody @Valid TimePokemonDto timePokemonDto){
        try {
            // Buscar o usuário pelo ID
            Optional<UserModel> optionalUser = userService.findById(timePokemonDto.getUserId());
            if (optionalUser.isEmpty()) {
                return ResponseEntity.badRequest().body("Usuário não encontrado");
            }

            // Salvar o TimePokemon no banco de dados
            timePokemonService.save(TimePokemonDto.toObject(timePokemonDto, optionalUser.get()));

            return ResponseEntity.ok("Time Pokemon criado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar Time Pokemon");
        }
    }

    @GetMapping
    public ResponseEntity<List<TimePokemonDto>> getAllPokemonTeams(){
        return ResponseEntity.ok(TimePokemonDto.listToDTO(timePokemonService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePokemonTeam(@PathVariable(value = "id") Long id){
        Optional<TimePokemonModel> optionalTimePokemon = timePokemonService.findById(id);
        if (optionalTimePokemon.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        TimePokemonDto timePokemonDTO = TimePokemonDto.toDTO(optionalTimePokemon.get());

        return ResponseEntity.ok(timePokemonDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTimePokemon(@PathVariable("id") Long id, @RequestBody TimePokemonDto timePokemonDTO) {
        Optional<TimePokemonModel> optionalTimePokemon = timePokemonService.findById(id);
        if (optionalTimePokemon.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<UserModel> optionalUser = userService.findById(timePokemonDTO.getUserId());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário não encontrado");
        }
        UserModel user = optionalUser.get();

        TimePokemonModel timePokemon = optionalTimePokemon.get();
        timePokemon.setPokemon1(timePokemonDTO.getPokemon1());
        timePokemon.setPokemon2(timePokemonDTO.getPokemon2());
        timePokemon.setPokemon3(timePokemonDTO.getPokemon3());
        timePokemon.setPokemon4(timePokemonDTO.getPokemon4());
        timePokemon.setPokemon5(timePokemonDTO.getPokemon5());
        timePokemon.setPokemon6(timePokemonDTO.getPokemon6());
        timePokemon.setUser(user);

        timePokemonService.save(timePokemon);

        return ResponseEntity.ok("Time Pokemon atualizado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePokemonTeam(@PathVariable(value = "id") Long id){
        Optional<TimePokemonModel> optionalTimePokemon = timePokemonService.findById(id);
        if (optionalTimePokemon.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TimePokemonModel timePokemon = optionalTimePokemon.get();
        timePokemonService.delete(timePokemon);

        return ResponseEntity.ok("Time Pokemon excluído com sucesso");
    }
}
