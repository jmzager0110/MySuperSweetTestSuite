package com.zags.JavaContd;

import com.serenity.By;
import com.serenity.SeleniumUtils;
import com.serenity.Timeouts;
import java.time.Duration;
import net.serentiybdd.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }
    //TODO: Add testing methods that will correspond with steps

    @FindBy(xpath = "")
    private WebElement exampleElement;

    public WebElement getExampleElement() {
        return exampleElement;
    }

    public void selectExampleElement () {
        SeleniumUtils.safeClick(driver, exampleElement, SHORT_WAIT);
    }

    public void selectExampleElementTitle(String otherExampleElement) {
        WebElement otherExampleElement = getDriver().findElement(By.xpath("//header//section/ul[@class='menu']/li/a" +
                "[text()='" + otherExampleElement + "']"));
        SeleniumUtils.safeClick(driver, otherExampleElement, SHORT_WAIT);
    }
    //TODO: Add testing methods that will correspond with steps
}