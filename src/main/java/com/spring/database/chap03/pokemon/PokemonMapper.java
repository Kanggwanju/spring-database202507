package com.spring.database.chap03.pokemon;

import com.spring.database.chap03.pokemon.entity.Pokemon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PokemonMapper {
    // 포켓몬 생성 기능
    int save(Pokemon pokemon);
    // 포켓몬 정보 수정 기능
    int updatePokemonStatus(@Param("pokemon") Pokemon pokemon, @Param("id") Long id);
    // 포켓몬 정보 삭제 기능
    int deleteById(Long id);
    // 전체 조회
    List<Pokemon> findAll();
    // 개별 조회
    Pokemon findById(Long id);
    // 포켓몬 hp 업데이트 메서드
    int updateHp(@Param("defenderId") Long defenderId, @Param("hp") int hp);
}
