package com.spring.database.chap01.pokemon.repository;

import com.spring.database.chap01.pokemon.entity.Pokemon;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.spring.database.chap01.pokemon.repository.PokemonJdbcRepository.performAttack;

@Repository("psr")
@RequiredArgsConstructor
public class PokemonSpringRepository implements PokemonRepository {

    private final JdbcTemplate template;

    @Override
    public boolean save(Pokemon pokemon) {
        String sql = """
                    INSERT INTO POKEMON
                        (name, type1, hp, attack, defense, speed)
                    VALUES
                        (?, ?, ?, ?, ?, ?)
                    ;
                    """;
        return template.update(
                sql,
                pokemon.getName(),
                pokemon.getType1(),
                pokemon.getHp(),
                pokemon.getAttack(),
                pokemon.getDefense(),
                pokemon.getSpeed()
        ) == 1;
    }

    @Override
    public boolean updatePokemonStatus(Pokemon pokemon, Long id) {
        String sql = """
                    UPDATE POKEMON
                    SET hp = ?, attack = ?, defense = ?, speed = ?
                    WHERE id = ?
                    """;

        return template.update(
                sql,
                pokemon.getHp(),
                pokemon.getAttack(),
                pokemon.getDefense(),
                pokemon.getSpeed(),
                id
        ) == 1;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = """
                DELETE FROM POKEMON
                WHERE id = ?
                """;
        return template.update(sql, id) == 1;
    }

    @Override
    public List<Pokemon> findAll() {
        String sql = """
                SELECT * FROM POKEMON
                """;

        return template.query(
                sql,
                (rs, rowNum) -> new Pokemon(rs)
        );
    }

    @Override
    public Pokemon findById(Long id) {
        String sql = """
                        SELECT * FROM Pokemon
                        WHERE id = ?
                    """;
        return template.queryForObject(
                sql,
                (rs, n) -> new Pokemon(rs),
                id
        );
    }

    @Override
    public boolean pokemonAttack(Long attackerId, Long defenderId) {
        Pokemon attackerPokemon = findById(attackerId);
        Pokemon defenderPokemon = findById(defenderId);

        int defenderHp = performAttack(attackerPokemon, defenderPokemon);

        String sql = """
                    UPDATE POKEMON
                    SET hp = ?
                    WHERE id = ?
                    """;

        return template.update(
                sql,
                defenderHp,
                defenderId
        ) == 1;
    }
}
