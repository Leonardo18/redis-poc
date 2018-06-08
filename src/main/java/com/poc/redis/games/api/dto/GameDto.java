package com.poc.redis.games.api.dto;

import javax.validation.constraints.NotNull;

public class GameDto {

    @NotNull
    private Integer id;
    @NotNull
    private String gameName;
    @NotNull
    private String gameGenre;
    @NotNull
    private Integer gameRating;
    @NotNull
    private String gameReview;

    public GameDto(Integer id, String gameName, String gameGenre, Integer gameRating, String gameReview) {
        this.id = id;
        this.gameName = gameName;
        this.gameGenre = gameGenre;
        this.gameRating = gameRating;
        this.gameReview = gameReview;
    }

    public GameDto() {
    }

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
