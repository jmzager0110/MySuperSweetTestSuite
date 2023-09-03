package com.zags.JavaContd;

import com.serenity.Asserts;
import com.serenity.SeleniumUtils;
import com.serenity.Timeouts;
import java.time.Duration;
import net.serentiybdd.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage extends PageObject implements Timeouts {

    WebDriver driver = getDriver();
    public static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public void openApplication() {
        SeleniumUtils.deleteAllCookies(driver);
        SeleniumUtils.maximizeWindow(driver);
        opanAt(SeleniumUtils.getBaseUrl());
    }

    //TODO: Add testing methods that will correspond with steps
}