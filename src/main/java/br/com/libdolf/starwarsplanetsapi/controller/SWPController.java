package br.com.libdolf.starwarsplanetsapi.controller;

import br.com.libdolf.starwarsplanetsapi.dtos.PlanetRequest;
import br.com.libdolf.starwarsplanetsapi.dtos.PlanetResponse;
import br.com.libdolf.starwarsplanetsapi.service.SWPService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Salva um novo planeta", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo com sucesso!")
    })
    @PostMapping()
    public ResponseEntity<PlanetResponse> postNewPlanet(@RequestBody PlanetRequest planet) throws Exception {
        return ResponseEntity.ok(service.saveNewPlanet(planet));
    }

    @Operation(summary = "Lista todos os Planetas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos os planetas")
    })
    @GetMapping("/all")
    public ResponseEntity<List<PlanetResponse>> getAllPlanets(){
        return ResponseEntity.ok(service.findAllPlanets());
    }


    @Operation(summary = "Busca um planeta por nome ou id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado"),
            @ApiResponse(responseCode = "404", description = "Não Encontrado")
    })
    @GetMapping()
    public ResponseEntity<PlanetResponse> searchPlanetByParam(@RequestParam(name = "name",required = false) String name,
                                                              @RequestParam(name = "id", required = false)Integer id) throws Exception {
        if (name == null){
            return ResponseEntity.ok(service.findPlanetById(id));
        }
        else return ResponseEntity.ok(service.findPlanetByName(name));
    }


    @Operation(summary = "Deleta um planeta por id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado"),
            @ApiResponse(responseCode = "400", description = "Não Deletado")
    })
    @DeleteMapping()
    public ResponseEntity deletePlanet(@RequestParam(name = "id") Integer id){
        service.deletePlanet(id);
        return ResponseEntity.ok().build();
    }
}