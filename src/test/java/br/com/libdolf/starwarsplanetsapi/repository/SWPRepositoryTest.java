package br.com.libdolf.starwarsplanetsapi.repository;

import br.com.libdolf.starwarsplanetsapi.entity.Planet;
import br.com.libdolf.starwarsplanetsapi.util.PlanetCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@DisplayName("Testes para o Repository")
class SWPRepositoryTest {
    @Autowired
    private SWPRepository repository;



    @Test
    public void save_persistPlanet_whenSuccessful(){
        Planet planetToBeSaved = PlanetCreator.createPlanetToBeSaved();

        Planet planetSaved = this.repository.save(planetToBeSaved);

        Assertions.assertThat(planetSaved).isNotNull();
        Assertions.assertThat(planetSaved.getId()).isNotNull();
        Assertions.assertThat(planetSaved.getName()).isEqualTo(planetToBeSaved.getName());
        Assertions.assertThat(planetSaved.getTerrain()).isEqualTo(planetToBeSaved.getTerrain());
        Assertions.assertThat(planetSaved.getWeather()).isEqualTo(planetToBeSaved.getWeather());
        Assertions.assertThat(planetSaved.getFilmAppearances()).isEqualTo(planetToBeSaved.getFilmAppearances());
    }

    @Test
    public void find_findPlanetById_whenSuccessful()  {
        Planet planetToBeSaved = PlanetCreator.createPlanetToBeSaved();

        Planet planetSaved = this.repository.save(planetToBeSaved);

        Integer id = planetSaved.getId();

        Optional<Planet> planet = this.repository.findById(id);

        Assertions.assertThat(planet).isNotEmpty()
                .contains(planetSaved);

    }


    @Test
    public void find_findPlanetByName_whenSuccessful()  {
        Planet planetToBeSaved = PlanetCreator.createPlanetToBeSaved();

        Planet planetSaved = this.repository.save(planetToBeSaved);

        String name = planetSaved.getName();

        Optional<Planet> planet = this.repository.findPlanetByName(name);

        Assertions.assertThat(planet).isNotEmpty()
                .contains(planetSaved);

    }

    @Test
    public void find_findAllPlanets_whenSuccessful()  {
        Planet planetToBeSaved = PlanetCreator.createPlanetToBeSaved();

        Planet planetSaved = this.repository.save(planetToBeSaved);


        List<Planet> planet = this.repository.findAll();

        Assertions.assertThat(planet).isNotEmpty()
                .contains(planetSaved);
    }

    @Test
    public void delete_deletePlanetById_whenSuccessful()  {
        Planet planetToBeSaved = PlanetCreator.createPlanetToBeSaved();

        Planet planetSaved = this.repository.save(planetToBeSaved);

        this.repository.delete(planetSaved);

        Optional<Planet> planetSearch = this.repository.findById(planetToBeSaved.getId());

        Assertions.assertThat(planetSearch).isEmpty();
    }

}