package br.com.libdolf.starwarsplanetsapi.util;

import br.com.libdolf.starwarsplanetsapi.dtos.PlanetResponse;
import br.com.libdolf.starwarsplanetsapi.entity.Planet;

public class PlanetResponseCreator {

    public static PlanetResponse createValidPlanetResponse(){
        return PlanetResponse.builder()
                .id(1)
                .name("Tatooine")
                .terrain("Arid")
                .weather("Hot")
                .filmAppearances(5)
                .build();
    }
}
