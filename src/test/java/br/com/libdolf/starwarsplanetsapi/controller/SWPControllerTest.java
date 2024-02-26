package br.com.libdolf.starwarsplanetsapi.controller;

import br.com.libdolf.starwarsplanetsapi.dtos.PlanetResponse;
import br.com.libdolf.starwarsplanetsapi.entity.Planet;
import br.com.libdolf.starwarsplanetsapi.exceptions.BadRequestException;
import br.com.libdolf.starwarsplanetsapi.service.SWPService;
import br.com.libdolf.starwarsplanetsapi.util.PlanetResponseCreator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
class SWPControllerTest {
    @InjectMocks
    private SWPController controller;
    @Mock
    private SWPService service;

    @BeforeEach
    void setup() throws Exception {
        List<PlanetResponse> planetResponseList = List.of(PlanetResponseCreator.createValidPlanetResponse());
        BDDMockito.when(service.findAllPlanets()).thenReturn(planetResponseList);

        BDDMockito.when(service.findPlanetById(ArgumentMatchers.anyInt())).thenReturn(PlanetResponseCreator.createValidPlanetResponse());

        BDDMockito.when(service.findPlanetByName(ArgumentMatchers.anyString())).thenReturn(PlanetResponseCreator.createValidPlanetResponse());

        BDDMockito.when(service.saveNewPlanet(ArgumentMatchers.any())).thenReturn(PlanetResponseCreator.createValidPlanetResponse());

    }

    @Test
    void get_ReturnAllPlanets_whenSuccessful(){
        PlanetResponse planetResponseExpected = PlanetResponseCreator.createValidPlanetResponse();
        List<PlanetResponse> planets = controller.getAllPlanets().getBody();

        Assertions.assertThat(planets).isNotEmpty().hasSize(1);
        Assertions.assertThat(planets.getFirst().getId()).isEqualTo(planetResponseExpected.getId());
        Assertions.assertThat(planets.getFirst().getName()).isEqualTo(planetResponseExpected.getName());
        Assertions.assertThat(planets.getFirst().getWeather()).isEqualTo(planetResponseExpected.getWeather());
        Assertions.assertThat(planets.getFirst().getTerrain()).isEqualTo(planetResponseExpected.getTerrain());
        Assertions.assertThat(planets.getFirst().getFilmAppearances()).isEqualTo(planetResponseExpected.getFilmAppearances());

    }

    @Test
    void get_ReturnPlanetById_whenSuccessful() throws Exception {
        PlanetResponse planetResponseExpected = PlanetResponseCreator.createValidPlanetResponse();
        PlanetResponse planet = controller.searchPlanetByParam(null, 1).getBody();

        Assertions.assertThat(planet).isNotNull();
        Assertions.assertThat(planet.getId()).isEqualTo(planetResponseExpected.getId());
        Assertions.assertThat(planet.getName()).isEqualTo(planetResponseExpected.getName());
        Assertions.assertThat(planet.getWeather()).isEqualTo(planetResponseExpected.getWeather());
        Assertions.assertThat(planet.getTerrain()).isEqualTo(planetResponseExpected.getTerrain());
        Assertions.assertThat(planet.getFilmAppearances()).isEqualTo(planetResponseExpected.getFilmAppearances());

    }

    @Test
    void get_ReturnPlanetByName_whenSuccessful() throws Exception {
        PlanetResponse planetResponseExpected = PlanetResponseCreator.createValidPlanetResponse();
        PlanetResponse planet = controller.searchPlanetByParam("name", null).getBody();

        Assertions.assertThat(planet).isNotNull();
        Assertions.assertThat(planet.getId()).isEqualTo(planetResponseExpected.getId());
        Assertions.assertThat(planet.getName()).isEqualTo(planetResponseExpected.getName());
        Assertions.assertThat(planet.getWeather()).isEqualTo(planetResponseExpected.getWeather());
        Assertions.assertThat(planet.getTerrain()).isEqualTo(planetResponseExpected.getTerrain());
        Assertions.assertThat(planet.getFilmAppearances()).isEqualTo(planetResponseExpected.getFilmAppearances());

    }


    @Test
    void save_saveNewPlanet_whenSuccessful() throws Exception {
        PlanetResponse planetResponseExpected = PlanetResponseCreator.createValidPlanetResponse();
        PlanetResponse planet = controller.postNewPlanet(ArgumentMatchers.any()).getBody();

        Assertions.assertThat(planet).isNotNull();
        Assertions.assertThat(planet.getId()).isEqualTo(planetResponseExpected.getId());
        Assertions.assertThat(planet.getName()).isEqualTo(planetResponseExpected.getName());
        Assertions.assertThat(planet.getWeather()).isEqualTo(planetResponseExpected.getWeather());
        Assertions.assertThat(planet.getTerrain()).isEqualTo(planetResponseExpected.getTerrain());
        Assertions.assertThat(planet.getFilmAppearances()).isEqualTo(planetResponseExpected.getFilmAppearances());

    }

    @Test()
    void delete_deletePlanet_whenFails() throws Exception {
        Mockito.doThrow(new BadRequestException("")).when(service).deletePlanet(null);
        Assertions.assertThatThrownBy(()-> this.controller.deletePlanet(null)).isInstanceOf(BadRequestException.class);
    }



}