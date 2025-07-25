package com.spring.database.pokemon.routes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PokemonRouteController {

    @GetMapping("/pokemon-page")
    public String pokemonPage() {
        return "pokemon-page";
    }
}
