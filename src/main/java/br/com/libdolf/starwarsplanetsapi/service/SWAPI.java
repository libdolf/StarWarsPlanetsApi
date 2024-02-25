package br.com.libdolf.starwarsplanetsapi.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SWAPI {
    private static RestTemplate restTemplate = new RestTemplate();
    private static String URL = "https://swapi.dev/api/planets?search=";
    public Integer getFilmAppearances(String planetName) {
        String response = restTemplate.getForObject(URL+planetName, String.class);

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

        int planetExist = jsonObject.get("count").getAsInt();
        if(planetExist == 1){
            JsonObject result = jsonObject.get("results").getAsJsonArray().get(0).getAsJsonObject();
            JsonArray films = result.get("films").getAsJsonArray();
            return films.size();
        }

        return 0;
    }
}
