package com.poc.redis.games.cucumber;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import wiremock.com.google.common.collect.Maps;

import java.util.Map;

@Component
@Scope("cucumber-glue")
public class World {

    public Map<String, Object> map = Maps.newHashMap();
    public WireMockServer wireMockServerEstoque;
    public Integer status;
}


