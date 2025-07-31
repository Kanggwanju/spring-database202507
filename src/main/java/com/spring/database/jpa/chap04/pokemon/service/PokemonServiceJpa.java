package com.spring.database.jpa.chap04.pokemon.service;

import com.spring.database.jpa.chap04.pokemon.entity.Pokemon;
import com.spring.database.jpa.chap04.pokemon.repository.PokemonJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonServiceJpa implements PokemonService {

    private final PokemonJpaRepository pokemonJpaRepository;

    @Override
    public List<Pokemon> findAll() {
        return pokemonJpaRepository.findAll();
    }

    @Override
    public Pokemon findById(String id) {
        return pokemonJpaRepository.findById(id).orElseThrow();
    }

    @Override
    public Pokemon save(Pokemon pokemon) {
        return pokemonJpaRepository.save(pokemon);
    }

    @Override
    public void deleteById(String id) {
        pokemonJpaRepository.deleteById(id);
    }

    @Override
    public Pokemon updatePokemonStatus(Pokemon pokemon, String id) {
        Pokemon foundPokemon = pokemonJpaRepository.findById(id).orElseThrow();
        
        // 포켓몬 status 변경 편의 메서드
        foundPokemon.changeStatus(
                pokemon.getHp()
                , pokemon.getAttack()
                , pokemon.getDefense()
                , pokemon.getSpeed()
        );

        return pokemonJpaRepository.save(foundPokemon);
    }

    @Override
    public Pokemon attack(String attackerId, String defenderId) {
        Pokemon attacker = pokemonJpaRepository.findById(attackerId).orElseThrow();
        Pokemon defender = pokemonJpaRepository.findById(defenderId).orElseThrow();

        int power = defender.getDefense() - attacker.getAttack();
        if (power >= 0) power = 0;

        int newHp = defender.getHp() + power;
        if (newHp < 0) newHp = 0;

        defender.updateHp(newHp);

        return pokemonJpaRepository.save(defender);
    }

    @Override
    public List<Pokemon> searchByName(String keyword) {
        return pokemonJpaRepository.findByNameContaining(keyword);
    }

    @Override
    public int calculatePower(String id) {
        Pokemon foundPokemon = pokemonJpaRepository.findById(id).orElseThrow();
        return foundPokemon.getHp() + foundPokemon.getAttack()*2 + foundPokemon.getSpeed();
    }
}

