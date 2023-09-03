package com.zags.JavaContd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerentiy;
import org.junit.runner.RunWith;

//@SpringBootTest
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features")
public class JavaContdApplicationTests {

	@Test
	void contextLoads() {
	}

}
