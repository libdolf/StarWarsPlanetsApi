package br.com.libdolf.starwarsplanetsapi.controller;

import br.com.libdolf.starwarsplanetsapi.dtos.PlanetRequest;
import br.com.libdolf.starwarsplanetsapi.dtos.PlanetResponse;
import br.com.libdolf.starwarsplanetsapi.service.SWPService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1")
public class SWPController {
    private SWPService service;

    @PostMapping()
    public ResponseEntity<PlanetResponse> postNewPlanet(@RequestBody PlanetRequest planet) throws Exception {
        return ResponseEntity.ok(service.saveNewPlanet(planet));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlanetResponse>> getAllPlanets(){
        return ResponseEntity.ok(service.findAllPlanets());
    }

    @GetMapping()
    public ResponseEntity<PlanetResponse> searchPlanetByParam(@RequestParam(name = "name",required = false) String name,
                                                              @RequestParam(name = "id", required = false)Integer id) throws Exception {
        if (name == null){
            return ResponseEntity.ok(service.findPlanetById(id));
        }
        else return ResponseEntity.ok(service.findPlanetByName(name));
    }

    @DeleteMapping()
    public ResponseEntity deletePlanet(@RequestParam(name = "id") Integer id){
        service.deletePlanet(id);
        return ResponseEntity.ok().build();
    }
}