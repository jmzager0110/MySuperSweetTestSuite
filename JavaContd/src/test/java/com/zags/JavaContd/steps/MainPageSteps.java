package com.serenity.steps;

import com.serenity.pages.BasePage;
import com.serenity.pages.MainPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

public class MainPageSteps extends ScenarioSteps {

    @Step
    @When ("user is doing something")
    public void whenUserIsDoingSomething() { mainPage.selectExampleElement();
    }

    @Step
    @Then("user does something else")
    public void thenUserDoesSomethingElse() {
        mainPage.selectOtherExampleElement();
    }

    //TODO: Update these& add some more
}