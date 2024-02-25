package br.com.libdolf.starwarsplanetsapi.service;

import br.com.libdolf.starwarsplanetsapi.dtos.PlanetRequest;
import br.com.libdolf.starwarsplanetsapi.dtos.PlanetResponse;
import br.com.libdolf.starwarsplanetsapi.entity.Planet;
import br.com.libdolf.starwarsplanetsapi.exceptions.BadRequestException;
import br.com.libdolf.starwarsplanetsapi.exceptions.NotFoundException;
import br.com.libdolf.starwarsplanetsapi.repository.SWPRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SWPService {
    private SWAPI swapi;
    private SWPRepository repository;

    public PlanetResponse saveNewPlanet(PlanetRequest planetRequest) throws Exception{
        Planet saved = repository.save(PlanetRequest.toEntity(planetRequest, swapi.getFilmAppearances(planetRequest.getName())));
        return PlanetResponse.toResponse(saved);
    }

    public List<PlanetResponse> findAllPlanets() {
        List<Planet> planets = repository.findAll();
        List<PlanetResponse> planetResponses = new ArrayList<>();
        for(Planet planet : planets){
            planetResponses.add(PlanetResponse.toResponse(planet));
        }
        return planetResponses;
    }

    public PlanetResponse findPlanetByName(String name) throws Exception{
        Planet planet = repository.findPlanetByName(name).orElseThrow(()->new NotFoundException("Nome não encontrado"));
        planet.setFilmAppearances(swapi.getFilmAppearances(planet.getName()));

        return PlanetResponse.toResponse(planet);
    }

    public PlanetResponse findPlanetById(Integer id) throws Exception{
        Planet planet = repository.findById(id).orElseThrow(()-> new NotFoundException("Id não encontrado"));
        planet.setFilmAppearances(swapi.getFilmAppearances(planet.getName()));

        return PlanetResponse.toResponse(planet);
    }

    public void deletePlanet(Integer id) {
        repository.deleteById(id);
        Optional<Planet> planet = repository.findById(id);
        if (planet.isPresent()){
            throw new BadRequestException("Não deletado");
        }
    }
}
