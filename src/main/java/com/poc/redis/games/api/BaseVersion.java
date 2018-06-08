package com.poc.redis.games.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public interface BaseVersion {
}
