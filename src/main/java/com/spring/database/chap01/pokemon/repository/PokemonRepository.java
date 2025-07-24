package com.spring.database.chap01.pokemon.repository;

import com.spring.database.chap01.pokemon.entity.Pokemon;

import java.util.List;

public interface PokemonRepository {

    // 포켓몬 생성 기능
    boolean save(Pokemon pokemon);
    // 포켓몬 정보 수정 기능
    boolean updatePokemonStatus(Pokemon pokemon, Long id);
    // 포켓몬 정보 삭제 기능
    boolean deleteById(Long id);
    // 전체 조회
    List<Pokemon> findAll();
    // 개별 조회
    Pokemon findById(Long id);
    // 포켓몬 공격 메서드
    boolean pokemonAttack(Long attackerId, Long defenderId);
}
