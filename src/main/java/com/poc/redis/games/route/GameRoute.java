package com.poc.redis.games.route;

import com.poc.redis.games.entities.GameEntity;
import com.poc.redis.games.repository.GameRepository;
import io.swagger.models.auth.In;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;
import java.util.Optional;

@Component
public class GameRoute extends RouteBuilder {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public void configure() throws Exception {
        from("direct:insertGame")
            .choice()
                .when(this::gameExists)
                    .log("Already exists a game in database with specified id")
                    .process(this::throwKeyAlreadyExistsException)
                .otherwise()
                    .log("Inserting game")
                    .process(this::saveGame)
            .end();

        from("direct:findGameById")
                .process(this::findGameById)
                .choice()
                    .when(this::gameNotExists)
                    .process(this::setEmptyGameAtBody)
                .endChoice()
                .end();
    }

    private Boolean gameExists(Exchange exchange) {
        GameEntity gameEntity = exchange.getIn().getBody(GameEntity.class);
        return gameRepository.exists(gameEntity.getId());
    }

    private void saveGame(Exchange exchange) {
        GameEntity gameEntity = exchange.getIn().getBody(GameEntity.class);
        gameRepository.save(gameEntity);
    }

    private void throwKeyAlreadyExistsException(Exchange exchange) {
        GameEntity gameEntity = exchange.getIn().getBody(GameEntity.class);
        throw new KeyAlreadyExistsException(
                String.format("Already existis a game with id %s", gameEntity.getId()));
    }

    private void findGameById(Exchange exchange){
        Integer id = exchange.getIn().getHeader("id", Integer.class);
        GameEntity gameEntity = gameRepository.findOne(id);
        exchange.getIn().setBody(gameEntity);
    }

    private Boolean gameNotExists(Exchange exchange){
        GameEntity gameEntity = (GameEntity) exchange.getIn().getBody();
        return !Optional.ofNullable(gameEntity).isPresent();
    }

    private void setEmptyGameAtBody(Exchange exchange){
        exchange.getIn().setBody(new GameEntity());
    }
}
