package com.api.pokedex.pokedex.controllers;

import com.api.pokedex.pokedex.api.PokemonAPI;
import com.api.pokedex.pokedex.models.PokemonData;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pokemons")
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

    @GetMapping
    public List<PokemonData> getPokemons() {
        List<PokemonData> pokemons = new ArrayList<>();

        try {
            for (int i = 1; i <= 150; i++) {
                PokemonData pokemonData = PokemonAPI.getPokemonData(String.valueOf(i));
                if (pokemonData != null) {
                    pokemons.add(pokemonData);
                }
            }
        } catch (IOException e) {
            // Tratar o erro adequadamente
        }

        return pokemons;
    }
}
