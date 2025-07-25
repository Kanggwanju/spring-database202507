package com.spring.database.chap01.pokemon.repository;

import com.spring.database.pokemon.entity.Pokemon;
import com.spring.database.pokemon.repository.PokemonSpringRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PokemonSpringRepositoryTest {

    @Autowired
    PokemonSpringRepository pokemonSpringRepository;

    @Test
    @DisplayName("포켓몬 정보를 주면 데이터베이스 pokemon 테이블에 저장된다.")
    void saveTest() {
        //given
        Pokemon givenPokemon = Pokemon.builder()
                .name("메타몽")
                .type1("노말")
                .hp(500)
                .attack(30)
                .defense(0)
                .speed(5)
                .build();
        //when
        boolean flag = pokemonSpringRepository.save(givenPokemon);

        //then
        Assertions.assertTrue(flag);
    }

    @Test
    @DisplayName("포켓몬의 체력, 공격력, 방어력, 속도를 수정해야 한다.")
    void updateTest() {
        //given
        Long givenId = 4L;
        Pokemon updatedPokemon = Pokemon.builder()
                .hp(450)
                .attack(45)
                .defense(23)
                .speed(8)
                .build();
        //when
        boolean flag = pokemonSpringRepository.updatePokemonStatus(updatedPokemon, givenId);
        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("id를 주면 pokemon 테이블에서 해당 id를 가진 행이 삭제된다.")
    void deleteTest() {
        //given
        Long givenId = 8L;
        //when
        boolean flag = pokemonSpringRepository.deleteById(givenId);
        //then
        assertTrue(flag);
    }


    @Test
    @DisplayName("전체 조회를 하면 포켓몬의 리스트가 반환된다.")
    void findAllTest() {
        //given

        //when
        List<Pokemon> pokemonList = pokemonSpringRepository.findAll();
        //then
        pokemonList.forEach(System.out::println);

        assertEquals(5, pokemonList.size());
        assertNotNull(pokemonList.get(0));
        assertEquals("전기", pokemonList.get(0).getType1());
    }

    @Test
    @DisplayName("적합한 id를 통해 개별조회를 하면 포켓몬 1개의 객체가 반환된다.")
    void findOneTest() {
        //given
        Long givenId = 4L;

        //when
        Pokemon foundPokemon = pokemonSpringRepository.findById(givenId);

        //then
        System.out.println("foundPokemon = " + foundPokemon);

        assertNotNull(foundPokemon);
        assertEquals("롱스톤", foundPokemon.getName());
    }

    @Test
    @DisplayName("공격하는 포켓몬의 공격력과 방어하는 포켓몬의 방어력을 비교하여 차이만큼 방어하는 포켓몬의 체력이 작아진다.")
    void attackTest() {
        //given
        Long givenAttackerId = 4L;
        Long givenDefenderId = 9L;

        //when
        boolean flag = pokemonSpringRepository.pokemonAttack(givenAttackerId, givenDefenderId);

        //then
        assertTrue(flag);
    }

}