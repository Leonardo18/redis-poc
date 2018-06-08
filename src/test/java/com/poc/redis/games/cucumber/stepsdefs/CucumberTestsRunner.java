package com.poc.redis.games.cucumber.stepsdefs;

import com.poc.redis.games.PocApplication;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.poc.redis.games.poc")
@ContextConfiguration(loader = SpringBootContextLoader.class, classes = PocApplication.class)
public class CucumberTestsRunner {
}
