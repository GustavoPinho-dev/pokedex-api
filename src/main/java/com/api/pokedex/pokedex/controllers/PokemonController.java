package com.api.pokedex.pokedex.controllers;

import com.api.pokedex.pokedex.api.PokemonAPI;
import com.api.pokedex.pokedex.models.PokemonData;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pokemon")
public class PokemonController {

    @GetMapping("/{name}")
    public PokemonData getPokemonData(@PathVariable String name) {
        try {
            System.out.println(name);
            return PokemonAPI.getPokemonData(name);
        } catch (IOException e) {
            // Tratar o erro adequadamente
            return null;
        }
    }
}
