package com.serenity.steps;

import com.serenity.pages.BasePage;
import com.serenity.pages.MainPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

public CommonSteps extends ScenarioSteps {
    private static final long serialVersionUID = IL;

    private BasePage basePage;
    private MainPage mainPage;

    @Step
    @Given ("user on page")
    public void givenUserIsOnPage() {
        mainPage.openApplication();
        }
    }