package com.spring.database.chap01.pokemon.api;

import com.spring.database.chap01.pokemon.entity.Pokemon;
import com.spring.database.chap01.pokemon.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pokemon")
@RequiredArgsConstructor
@Slf4j
public class PokemonController {

    private final PokemonRepository pokemonRepository;

    // 전체 조회 요청
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(pokemonRepository.findAll());
    }

    // 생성 요청
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Pokemon pokemon) {
        pokemonRepository.save(pokemon);
        return ResponseEntity.ok("포켓몬 등록 성공!");
    }

    // 수정 요청
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePokemon(@RequestBody Pokemon pokemon, @PathVariable Long id) {
        pokemonRepository.updatePokemonStatus(pokemon, id);
        return ResponseEntity.ok("포켓몬 수정 성공!");
    }

    // 삭제 요청
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePokemon(@PathVariable Long id) {
        pokemonRepository.deleteById(id);
        return ResponseEntity.ok("포켓몬 삭제 성공!");
    }

    // 개별 조회 요청
    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(pokemonRepository.findById(id));
    }

    // 공격 요청
    @PostMapping("/{attackerId}/attack/{defenderId}")
    public ResponseEntity<?> attack(
            @PathVariable Long attackerId
            , @PathVariable Long defenderId
    ) {
        pokemonRepository.pokemonAttack(attackerId, defenderId);
        return ResponseEntity.ok("포켓몬 공격 성공!");
    }

}
