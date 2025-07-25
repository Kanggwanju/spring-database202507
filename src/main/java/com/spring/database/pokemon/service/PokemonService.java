package com.spring.database.pokemon.service;

import com.spring.database.pokemon.entity.Pokemon;

import java.util.List;

public interface PokemonService {
    List<Pokemon> findAll();
    Pokemon findById(Long id);
    boolean save(Pokemon pokemon);
    boolean deleteById(Long id);
    boolean updatePokemonStatus(Pokemon pokemon, Long id);
    boolean attack(Long attackerId, Long defenderId);
}
