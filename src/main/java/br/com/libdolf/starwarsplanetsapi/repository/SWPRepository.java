package br.com.libdolf.starwarsplanetsapi.repository;

import br.com.libdolf.starwarsplanetsapi.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SWPRepository extends JpaRepository<Planet, Integer> {
    public Optional<Planet> findPlanetByName(String name);
}
