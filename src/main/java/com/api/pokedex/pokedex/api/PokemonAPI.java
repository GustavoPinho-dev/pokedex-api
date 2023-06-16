package com.api.pokedex.pokedex.api;

import com.api.pokedex.pokedex.models.PokemonData;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class PokemonAPI {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    public static PokemonData getPokemonData(String pokemonName) throws IOException {
        String apiUrl = BASE_URL + pokemonName.toLowerCase();
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            JsonElement jsonElement = JsonParser.parseReader(inputStreamReader);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            String name = jsonObject.get("name").getAsString();
            JsonArray typesArray = jsonObject.getAsJsonArray("types");
            String[] types = new String[typesArray.size()];

            for (int i = 0; i < typesArray.size(); i++) {
                JsonObject typeObject = typesArray.get(i).getAsJsonObject();
                JsonObject typeData = typeObject.getAsJsonObject("type");
                types[i] = typeData.get("name").getAsString();
            }

            String spriteUrl = jsonObject.getAsJsonObject("sprites")
                    .get("front_default").getAsString();

            return new PokemonData(name, types, spriteUrl);
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            String pokemonName = "zapdos";
            PokemonData pokemonData = PokemonAPI.getPokemonData(pokemonName);

            if (pokemonData != null) {
                System.out.println("Nome do Pokémon: " + pokemonData.getName());
                System.out.println("Tipos do Pokémon: " + Arrays.toString(pokemonData.getTypes()));
                System.out.println("URL da imagem do Pokémon: " + pokemonData.getSpriteUrl());
            } else {
                System.out.println("Pokémon não encontrado.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao acessar a PokeAPI: " + e.getMessage());
        }
    }
}

