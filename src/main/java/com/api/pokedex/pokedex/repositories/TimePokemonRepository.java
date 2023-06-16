package com.api.pokedex.pokedex.repositories;

import com.api.pokedex.pokedex.models.TimePokemonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TimePokemonRepository extends JpaRepository<TimePokemonModel, Long> {

}
