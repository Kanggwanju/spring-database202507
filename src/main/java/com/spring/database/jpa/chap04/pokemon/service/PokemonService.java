package com.spring.database.jpa.chap04.pokemon.service;

import com.spring.database.jpa.chap04.pokemon.entity.Pokemon;

import java.util.List;

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
