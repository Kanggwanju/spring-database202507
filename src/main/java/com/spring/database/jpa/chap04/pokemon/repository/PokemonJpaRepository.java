package com.spring.database.jpa.chap04.pokemon.repository;

import com.spring.database.jpa.chap04.pokemon.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonJpaRepository extends JpaRepository<Pokemon, String> {
    
    // 키워드를 통해 포켓몬 찾기
    List<Pokemon> findByNameContaining(String keyword);

}
