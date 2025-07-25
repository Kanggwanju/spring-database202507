package com.spring.database.pokemon.api;

import com.spring.database.pokemon.entity.Pokemon;
import com.spring.database.pokemon.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/pokemon")
@RequiredArgsConstructor
public class PokemonMapperController {
    private final PokemonService pokemonService;

    // 1. 전체 조회
    @GetMapping
    public List<Pokemon> getAll() {
        return pokemonService.findAll();
    }

    // 2. 개별 조회
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getOne(@PathVariable Long id) {
        Pokemon p = pokemonService.findById(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    // 3. 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return pokemonService.deleteById(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    // 4. 생성
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Pokemon pokemon) {
        return pokemonService.save(pokemon)
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }

    // 5. 스탯 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @RequestBody Pokemon pokemon
    ) {
        return pokemonService.updatePokemonStatus(pokemon, id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }

    // 6. 공격 (hp 감소)
    @PostMapping("/attack")
    public ResponseEntity<Void> attack(
            @RequestParam Long attackerId,
            @RequestParam Long defenderId
    ) {
        return pokemonService.attack(attackerId, defenderId)
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }
}
