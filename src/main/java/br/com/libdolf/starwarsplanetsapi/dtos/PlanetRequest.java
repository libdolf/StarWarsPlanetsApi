package br.com.libdolf.starwarsplanetsapi.dtos;

import br.com.libdolf.starwarsplanetsapi.entity.Planet;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanetRequest {
    private String name;
    private String weather;
    private String terrain;
    public static Planet toEntity(PlanetRequest planetRequest, Integer filmAppearances) {
        return Planet.builder()
                .name(planetRequest.getName())
                .weather(planetRequest.getWeather())
                .terrain(planetRequest.getTerrain())
                .filmAppearances(filmAppearances)
                .build();
    }
}