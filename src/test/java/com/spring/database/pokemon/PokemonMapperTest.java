package com.spring.database.pokemon;

import com.spring.database.chap03.pokemon.PokemonMapper;
import com.spring.database.chap03.pokemon.entity.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PokemonMapperTest {

    @Autowired
    PokemonMapper pokemonMapper;

    @Test
    @DisplayName("포켓몬 정보를 주면 데이터베이스 pokemon 테이블에 저장된다.")
    void saveTest() {
        //given
        Pokemon givenPokemon = Pokemon.builder()
                .name("칠색조")
                .type1("바람")
                .hp(300)
                .attack(40)
                .defense(17)
                .speed(30)
                .build();
        //when
        boolean flag = pokemonMapper.save(givenPokemon) == 1;

        //then
        Assertions.assertTrue(flag);
    }

    @Test
    @DisplayName("포켓몬의 체력, 공격력, 방어력, 속도를 수정해야 한다.")
    void updateTest() {
        //given
        Long givenId = 11L;
        Pokemon updatedPokemon = Pokemon.builder()
                .hp(300)
                .attack(50)
                .defense(17)
                .speed(35)
                .build();
        //when
        boolean flag = pokemonMapper.updatePokemonStatus(updatedPokemon, givenId) == 1;
        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("id를 주면 pokemon 테이블에서 해당 id를 가진 행이 삭제된다.")
    void deleteTest() {
        //given
        Long givenId = 12L;
        //when
        boolean flag = pokemonMapper.deleteById(givenId) == 1;
        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("전체 조회를 하면 포켓몬의 리스트가 반환된다.")
    void findAllTest() {
        //given

        //when
        List<Pokemon> pokemonList = pokemonMapper.findAll();
        //then
        pokemonList.forEach(System.out::println);

        assertEquals(5, pokemonList.size());
        assertNotNull(pokemonList.get(0));
//        assertEquals("전기", pokemonList.get(0).getType1());
    }

    @Test
    @DisplayName("적합한 id를 통해 개별조회를 하면 포켓몬 1개의 객체가 반환된다.")
    void findOneTest() {
        //given
        Long givenId = 4L;

        //when
        Pokemon foundPokemon = pokemonMapper.findById(givenId);

        //then
        System.out.println("foundPokemon = " + foundPokemon);

        assertNotNull(foundPokemon);
        assertEquals("롱스톤", foundPokemon.getName());
    }

    @Test
    @DisplayName("update hp test")
    void updateHpTest() {
        //given
        Long id = 4L;
        int newHp = 200;
        //when
        boolean flag = pokemonMapper.updateHp(id, newHp) == 1;
        //then
        assertTrue(flag);
    }



}