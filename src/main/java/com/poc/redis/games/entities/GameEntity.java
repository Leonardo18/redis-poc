package com.poc.redis.games.entities;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@EnableAutoConfiguration
@RedisHash("Game")
public class GameEntity implements Serializable {

    @Id
    private Integer id;
    private String gameName;
    private String gameGenre;
    private Integer gameRating;
    private String gameReview;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameGenre() {
        return gameGenre;
    }

    public void setGameGenre(String gameGenre) {
        this.gameGenre = gameGenre;
    }

    public Integer getGameRating() {
        return gameRating;
    }

    public void setGameRating(Integer gameRating) {
        this.gameRating = gameRating;
    }

    public String getGameReview() {
        return gameReview;
    }

    public void setGameReview(String gameReview) {
        this.gameReview = gameReview;
    }
}
