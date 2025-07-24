package com.spring.database.chap01.pokemon.repository;

import com.spring.database.chap01.pokemon.entity.Pokemon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// 역할: Pokemon 데이터베이스에 접근해서 CRUD
@Repository("pjr")
@RequiredArgsConstructor
public class PokemonJdbcRepository implements PokemonRepository {

    private final DataSource dataSource;

    // INSERT 기능
    public boolean save(Pokemon pokemon) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = """
                    INSERT INTO POKEMON
                        (name, type1, hp, attack, defense, speed)
                    VALUES
                        (?, ?, ?, ?, ?, ?)
                    ;
                    """;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pokemon.getName());
            pstmt.setString(2, pokemon.getType1());
            pstmt.setInt(3, pokemon.getHp());
            pstmt.setInt(4, pokemon.getAttack());
            pstmt.setInt(5, pokemon.getDefense());
            pstmt.setInt(6, pokemon.getSpeed());

            return pstmt.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 포켓몬 정보 수정
    public boolean updatePokemonStatus(Pokemon pokemon, Long id) {
        try(Connection conn = dataSource.getConnection()) {

            String sql = """
                    UPDATE POKEMON
                    SET hp = ?, attack = ?, defense = ?, speed = ?
                    WHERE id = ?
                    """;

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pokemon.getHp());
            pstmt.setInt(2, pokemon.getAttack());
            pstmt.setInt(3, pokemon.getDefense());
            pstmt.setInt(4, pokemon.getSpeed());
            pstmt.setLong(5, id);

            return pstmt.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 포켓몬 정보 삭제
    public boolean deleteById(Long id) {
        try (Connection conn = dataSource.getConnection()) {

            String sql = """
                DELETE FROM POKEMON
                WHERE id = ?
                """;

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            return pstmt.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 전체 조회 - ORM (Object Relational Mapping)
    public List<Pokemon> findAll() {

        List<Pokemon> pokemonList = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {
            String sql = """
                        SELECT * FROM POKEMON
                    """;

            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Pokemon pokemon = new Pokemon(rs);
                pokemonList.add(pokemon);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pokemonList;
    }

    // id로 단일조회 메서드
    public Pokemon findById(Long id) {
        try (Connection conn = dataSource.getConnection()) {

            String sql = """
                        SELECT * FROM Pokemon
                        WHERE id = ?
                    """;

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) return new Pokemon(rs);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // 포켓몬 공격 메서드
    public boolean pokemonAttack(Long attackerId, Long defenderId) {
        try (Connection conn = dataSource.getConnection()) {

            Pokemon attackerPokemon = findById(attackerId);
            Pokemon defenderPokemon = findById(defenderId);

            int defenderHp = performAttack(attackerPokemon, defenderPokemon);

            String sql = """
                    UPDATE POKEMON
                    SET hp = ?
                    WHERE id = ?
                    """;
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, defenderHp);
            pstmt.setLong(2, defenderPokemon.getId());

            return pstmt.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static int performAttack(Pokemon attacker, Pokemon defender) {
        int power = defender.getDefense() - attacker.getAttack();
        if (power >= 0) {
            power = 0;
        }

        return defender.getHp() + power;
    }

}
