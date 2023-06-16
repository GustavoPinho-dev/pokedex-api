package com.api.pokedex.pokedex.services;

import com.api.pokedex.pokedex.models.TimePokemonModel;
import com.api.pokedex.pokedex.repositories.TimePokemonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TimePokemonService {

    final TimePokemonRepository timePokemonRepository;

    public TimePokemonService(TimePokemonRepository timePokemonRepository) {
        this.timePokemonRepository = timePokemonRepository;
    }

    @Transactional
    public TimePokemonModel save(TimePokemonModel timePokemonModel) {
        return timePokemonRepository.save(timePokemonModel);
    }

    public List<TimePokemonModel> findAll() {
        return timePokemonRepository.findAll();
    }

    public Optional<TimePokemonModel> findById(Long id) {
        return timePokemonRepository.findById(id);
    }

    public void delete(TimePokemonModel timePokemonModel) {
        timePokemonRepository.delete(timePokemonModel);
    }
}
