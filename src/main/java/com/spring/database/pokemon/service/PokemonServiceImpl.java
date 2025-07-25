package com.spring.database.pokemon.service;

import com.spring.database.pokemon.PokemonMapper;
import com.spring.database.pokemon.entity.Pokemon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

    private final PokemonMapper pokemonMapper;

    @Override
    public List<Pokemon> findAll() {
        return pokemonMapper.findAll();
    }

    @Override
    public Pokemon findById(Long id) {
        return pokemonMapper.findById(id);
    }

    @Override
    public boolean save(Pokemon pokemon) {
        return pokemonMapper.save(pokemon) == 1;
    }

    @Override
    public boolean deleteById(Long id) {
        return pokemonMapper.deleteById(id) == 1;
    }

    @Override
    public boolean updatePokemonStatus(Pokemon pokemon, Long id) {
        return pokemonMapper.updatePokemonStatus(pokemon, id) == 1;
    }

    @Override
    public boolean attack(Long attackerId, Long defenderId) {
        Pokemon attacker = pokemonMapper.findById(attackerId);
        Pokemon defender = pokemonMapper.findById(defenderId);

        if (attacker == null || defender == null) return false;

        int power = defender.getDefense() - attacker.getAttack();
        if (power >= 0) power = 0;

        int newHp = defender.getHp() + power;
        if (newHp < 0) newHp = 0;

        return pokemonMapper.updateHp(defenderId, newHp) == 1;
    }
}

