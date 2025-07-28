package com.spring.database.chap03.pokemon.routes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PokemonRouteController {

    @GetMapping("/pokemon-page")
    public String pokemonPage() {
        return "pokemon-page";
    }

    @GetMapping("/pokemonV2-page")
    public String pokemonV2Page() {
        return "pokemonV2-page";
    }
}
