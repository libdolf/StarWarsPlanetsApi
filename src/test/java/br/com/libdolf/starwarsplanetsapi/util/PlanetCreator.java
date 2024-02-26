package br.com.libdolf.starwarsplanetsapi.util;

import br.com.libdolf.starwarsplanetsapi.entity.Planet;

public class PlanetCreator {

    public static Planet createPlanetToBeSaved(){
        return Planet.builder()
                .name("Tatooine")
                .terrain("Arid")
                .weather("Hot")
                .filmAppearances(5)
                .build();
    }
    public static Planet createValidPlanet(){
        return Planet.builder()
                .id(1)
                .name("Tatooine")
                .terrain("Arid")
                .weather("Hot")
                .filmAppearances(5)
                .build();
    }

}
