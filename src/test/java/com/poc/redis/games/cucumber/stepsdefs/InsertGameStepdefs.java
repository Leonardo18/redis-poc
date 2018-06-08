package com.poc.redis.games.cucumber.stepsdefs;


import com.poc.redis.games.api.dto.GameDto;
import com.poc.redis.games.cucumber.World;
import cucumber.api.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class InsertGameStepdefs implements En {

    @Autowired
    private World world;
    @LocalServerPort
    private String port;
    private RestTemplate restTemplate;

    public InsertGameStepdefs() {

        Before(() -> {
            restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        });

        Given("^o id do game (.*)$", (Integer gameId) -> {
            world.map.put("gameId", gameId);
        });

        Given("^o nome do game (.*)$", (String gameName) -> {
            world.map.put("gameName", gameName);
        });

        And("^o genêro do game (.*)$", (String gameGenre) -> {
            world.map.put("gameGenre", gameGenre);
        });

        And("^a note da análise pessoal (\\d+)$", (Integer evaluation) -> {
            world.map.put("evaluation", evaluation);
        });

        And("^a observação da análise (.*)$", (String observation) -> {
            world.map.put("observation", observation);
        });

        When("^insiro um game$", () -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<GameDto> entity = new HttpEntity<>(buildDto(), headers);

            try {
                world.map.put("inseredGame",
                        restTemplate.exchange(String.format("http://localhost:%s/api/games", port),
                                HttpMethod.POST,
                                entity,
                                GameDto.class)
                                .getBody()
                );
            } catch (HttpServerErrorException | HttpClientErrorException e) {
                world.status = e.getRawStatusCode();
            }
        });

        Then("^deve retornar que o game foi inserido com sucesso$", () -> {
            GameDto gameExpected = new GameDto();

            GameDto gameReturned = (GameDto) world.map.get("inseredGame");
            assertEquals(gameExpected.getId(), gameReturned.getId());
            assertEquals(gameExpected.getGameName(), gameReturned.getGameName());
            assertEquals(gameExpected.getGameGenre(), gameReturned.getGameGenre());
            assertEquals(gameExpected.getGameRating(), gameReturned.getGameRating());
            assertEquals(gameExpected.getGameReview(), gameReturned.getGameReview());
        });

        And("^deve retorar uma resposta com status (\\d+)$", (Integer expectedStatus) -> {
            assertEquals(expectedStatus, world.status);
        });
    }

    private GameDto buildDto(){
        return new GameDto();
    }
}
