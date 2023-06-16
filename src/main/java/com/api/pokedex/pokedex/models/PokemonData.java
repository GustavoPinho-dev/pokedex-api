package com.api.pokedex.pokedex.models;

public class PokemonData {
    private String name;
    private String[] types;
    private String spriteUrl;

    public PokemonData(String name, String[] types, String spriteUrl) {
        this.name = name;
        this.types = types;
        this.spriteUrl = spriteUrl;
    }

    public String getName() {
        return name;
    }

    public String[] getTypes() {
        return types;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }
}
