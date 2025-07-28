package com.spring.database.jpa.pokemon.repository;


import com.spring.database.jpa.chap01.entity.Product;
import com.spring.database.jpa.pokemon.entity.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static com.spring.database.jpa.chap01.entity.Product.Category.ELECTRONIC;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan(basePackages = "com.spring.database.jpa.pokemon")
class PokemonJpaRepositoryTest {

    @Autowired
    PokemonJpaRepository pokemonJpaRepository;

    @BeforeEach
    void bulkSave() {
        Pokemon s1 = Pokemon.builder()
                .name("칠색조")
                .type1("바람")
                .hp(300)
                .attack(40)
                .defense(17)
                .speed(40)
                .build();

        Pokemon s2 = Pokemon.builder()
                .name("롱스톤")
                .type1("바위")
                .hp(400)
                .attack(60)
                .defense(20)
                .speed(15)
                .build();

        Pokemon s3 = Pokemon.builder()
                .name("피카츄")
                .type1("전기")
                .hp(250)
                .attack(55)
                .defense(17)
                .speed(30)
                .build();

        pokemonJpaRepository.saveAllAndFlush(
                List.of(s1, s2, s3)
        );
    }

    @Test
    @DisplayName("포켓몬 정보를 데이터베이스에 저장한다.")
    void saveTest() {
        //given
        Pokemon newPokemon = Pokemon.builder()
                .name("파이리")
                .type1("불")
                .hp(320)
                .attack(45)
                .defense(15)
                .speed(20)
                .build();
        //when
        Pokemon saved = pokemonJpaRepository.save(newPokemon);

        //then
        assertNotNull(saved);
    }

}