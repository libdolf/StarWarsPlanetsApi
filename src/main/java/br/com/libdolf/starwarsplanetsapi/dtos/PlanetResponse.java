package br.com.libdolf.starwarsplanetsapi.dtos;

import br.com.libdolf.starwarsplanetsapi.entity.Planet;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanetResponse {
    private Integer id;
    private String name;
    private String weather;
    private String terrain;
    private Integer filmAppearances;

    public static PlanetResponse toResponse(Planet planet) {
        return PlanetResponse.builder()
                .id(planet.getId())
                .name(planet.getName())
                .weather(planet.getWeather())
                .terrain(planet.getTerrain())
                .filmAppearances(planet.getFilmAppearances())
                .build();
    }
}