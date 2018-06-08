package com.poc.redis.games.api;

import com.poc.redis.games.entities.GameEntity;
import com.poc.redis.games.api.dto.GameDto;
import com.poc.redis.games.errors.Error;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.camel.ProducerTemplate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.stream.Stream;

@RestController
public class GameApi implements BaseVersion{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProducerTemplate producerTemplate;

    @ApiOperation(
            value = "Insert game informations",
            response = GameDto.class,
            notes = "This operation insert game data to a redis database"
    )
    @ApiResponses( value = {
            @ApiResponse(
                  code = 200,
                  message = "Succes to insert game into database",
                  response = GameDto.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "When have some error in data or game already exists in database",
                    response = Error.class
            )
    })
    @PostMapping(value = "/games")
    public ResponseEntity<?> insertGame(@Valid @RequestBody GameDto gameDto){

        return Stream.of(gameDto)
                .map(this::convertToEntity)
                .map(entity -> producerTemplate.requestBody("direct:insertGame", entity))
                .map(response -> convertToDto((GameEntity) response))
                .map(dto ->
                        inserted(
                                ServletUriComponentsBuilder
                                        .fromPath("/api/v1/games/{id}")
                                        .buildAndExpand(dto.getGameName())
                                        .toUri(),
                                dto
                        )
                )
                .findFirst()
                .get();
    }

    private GameEntity convertToEntity(GameDto gameDto) {
        return modelMapper.map(gameDto, GameEntity.class);
    }

    private GameDto convertToDto(GameEntity gameEntity){
        return modelMapper.map(gameEntity, GameDto.class);
    }

    protected <T> ResponseEntity<T> inserted(URI uri, T body) {
        return ResponseEntity.created(uri).body(body);
    }
}
