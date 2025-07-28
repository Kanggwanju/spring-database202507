package com.spring.database.jpa.pokemon.service;

import com.spring.database.jpa.pokemon.entity.Pokemon;

import java.util.List;
import java.util.Optional;

public interface PokemonService {
    List<Pokemon> findAll();
    Pokemon findById(String id);
    Pokemon save(Pokemon pokemon);
    void deleteById(String id);
    Pokemon updatePokemonStatus(Pokemon pokemon, String id);
    Pokemon attack(String attackerId, String defenderId);
    List<Pokemon> searchByName(String keyword);
    int calculatePower(String id);
}
