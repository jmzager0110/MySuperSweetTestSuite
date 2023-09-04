package com.serenity.utils;



import net.serenitybdd.core.Serenity;

import org.openqa.selenium.*;

import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;



import static com.serenity.utils.Timeouts.VERY_LONG_WAIT;



public class SeleniumUtils extends Asserts {



    /**

     * StepButtons are buttons found in the Serenity Report.  They currently accept a single string

     * and opens window when clicked in browser.  The following methods are designed for initializing,

     * building, and submitting the StepButton.

     */



    private static String stepButtonDetails = "";

    private static String stepButtonTitle = "";



    /**

     * This method sets the StepButton's Title.

     */

    public static void setStepButtonTitle(String title) {

        stepButtonTitle = title;

    }



    /**

     * This method builds on to the StepButton's content, stepButtonDetails, adding a line break

     * afterwards.

     */

    public static void addStepButtonDetails(String detail) {

        stepButtonDetails += detail + "\n";

    }



    /**

     * This method resets the StepButton's title

     */

    public static void clearStepButtonTitle() {

        stepButtonTitle = "";

    }



    /**

     * This method resets the StepButton's content

     */

    public static void clearStepButtonDetails() {

        stepButtonDetails = "";

    }



    public static String getStepButtonTitle() {

        return stepButtonTitle;

    }



    public static String getStepButtonDetails() {

        return stepButtonDetails;

    }



    /**

     * This method constructs the button and uses addStepButton to generate it.  After, the method

     * clears the StepButton's title and content.

     */

    public static void submitButtonDetailsToStepButton() {

        addStepButton(getStepButtonTitle(), getStepButtonDetails());

        clearStepButtonTitle();

        clearStepButtonDetails();

    }



    /**

     * This method creates the button on the Serenity Report.  It can be used independently of the

     * methods above to generate a quick StepButton.

     */

    public static void addStepButton(String buttonTitle, String details) {

        Serenity.recordReportData().withTitle(buttonTitle).andContents(details);

    }



    public static final Logger LOGGER = LoggerFactory.getLogger(SeleniumUtils.class);



    public static String getBaseUrl() {

        String jenkinsURL = "";

        if (System.getProperty("url") != null) {

            jenkinsURL = System.getProperty("url");

        } else {

            jenkinsURL = System.getProperty("webdriver.base.url");

        }

        LOGGER.info("The test URL is: " + jenkinsURL);

        return jenkinsURL;

    }



    public static boolean waitForVisibilityOfElement(WebDriver driver, WebElement element,

                                                     int timeOutInSeconds) {

        try {

            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);

            wait.until(ExpectedConditions.visibilityOf(element));

            LOGGER.info(element + " is visible");

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            LOGGER.error(element + " is not visible");

            return false;

        }

    }



    public static boolean waitForElementToBeClickable(WebDriver driver, WebElement element,

                                                      int timeOutInSeconds) {

        try {

            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);

            wait.until(ExpectedConditions.elementToBeClickable(element));

            LOGGER.info(element + " is Clickable");

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            LOGGER.error(element + " is not Clickable");

            return false;

        }

    }



    public static WebElement waitForPresenceOfElement(WebDriver driver, By by, int timeOutInSeconds) {

        WebElement element = null;

        try {

            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);

            element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

            LOGGER.info(element + " is Clickable");

            return element;

        } catch (Exception e) {

            e.printStackTrace();

            LOGGER.error(element + " is not Clickable");

            throw e;

        }

    }



    public static WebElement waitForPresenceOfElement(WebDriver driver, By by) {

        WebElement element = null;

        try {

            WebDriverWait wait = new WebDriverWait(driver, VERY_LONG_WAIT);

            element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

            LOGGER.info(element + " is Clickable");

            return element;

        } catch (Exception e) {

            e.printStackTrace();

            LOGGER.error(element + " is not Clickable");

            throw e;

        }

    }



    public static boolean waitForTitleContains(WebDriver driver, String title, int timeOutInSeconds) {

        try {

            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);

            wait.until(ExpectedConditions.titleContains(title));

            LOGGER.info(title + " is found");

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            LOGGER.info(title + " is not found");

            throw e;

        }

    }



    public static void highlightElement(WebDriver driver, WebElement element) {



        String attributevalue = "border:3px solid red;";

        JavascriptExecutor executor = (JavascriptExecutor) driver;

        String getattrib = element.getAttribute("style");

        executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,

                attributevalue);

        try {

            Thread.sleep(100);

        } catch (InterruptedException e) {

            System.out.println("Sleep interrupted - " + e);

        }

        executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, getattrib);

    }



    public static void scrollIntoElementView(WebDriver driver, WebElement element) {

        try {

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

            LOGGER.info("Scrolled to " + element);

        } catch (StaleElementReferenceException e) {

            LOGGER.error("Element with " + element + " is not attached to the page document" + e);

            throw e;

        } catch (NoSuchElementException e) {

            LOGGER.error("Element " + element + " was not found in DOM" + e);

            throw e;

        } catch (Exception e) {

            e.printStackTrace();

            LOGGER.error("Unable to scroll the page to find " + element + "\n" + e);

            throw e;

        }

    }



    public static void safeClick(WebDriver driver, WebElement element, int timeOutInSeconds) {

        try {

            waitForVisibilityOfElement(driver, element, timeOutInSeconds);

            highlightElement(driver, element);

            element.click();

        } catch (StaleElementReferenceException e) {

            LOGGER.error("Element with " + element + " is not attached to the page document" + e);

            throw e;

        } catch (NoSuchElementException e) {

            LOGGER.error("Element " + element + " was not found in DOM" + e);

            throw e;

        } catch (Exception e) {

            LOGGER.error("Element " + element + " was not clickable" + e);

            throw e;

        }

    }



    public static void jsClick(WebDriver driver, WebElement element) {

        try {

            JavascriptExecutor jse = (JavascriptExecutor) driver;

            jse.executeScript("arguments[0].click();", element);

            LOGGER.info(element + "is Clickable");

        } catch (StaleElementReferenceException e) {

            LOGGER.error("Element with " + element + "is not attached to the page document" + e);

            throw e;

        } catch (NoSuchElementException e) {

            LOGGER.error("Element " + element + " was not found in DOM" + e);

            throw e;

        } catch (Exception e) {

            LOGGER.error("Unable to click on element " + element + e);

            throw e;

        }

    }



    public void actionsClick(WebDriver driver, WebElement element, int timeOutInSeconds) {

        try {

            waitForVisibilityOfElement(driver, element, timeOutInSeconds);

            highlightElement(driver, element);

            Actions builder = new Actions(driver);

            builder.moveToElement(element).click().build().perform();

        } catch (StaleElementReferenceException e) {

            LOGGER.error("Element with " + element + " is not attached to the page document" + e);

            throw e;

        } catch (NoSuchElementException e) {

            LOGGER.error("Element " + element + " was not found in DOM" + e);

            throw e;

        } catch (Exception e) {

            LOGGER.error("Unable to click the cursor on " + element + e);

            throw e;

        }

    }



    public static void safeClearAndType(WebDriver driver, WebElement element, int timeOutInSeconds,

                                        String text) {

        try {

            waitForVisibilityOfElement(driver, element, timeOutInSeconds);

            highlightElement(driver, element);

            new Actions(driver).click(element).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)

                    .sendKeys(Keys.BACK_SPACE).build().perform();

            element.sendKeys(text);

        } catch (StaleElementReferenceException e) {

            LOGGER.error("Element with " + element + " is not attached to the page document" + e);

            throw e;

        } catch (NoSuchElementException e) {

            LOGGER.error("Element " + element + " was not found in DOM" + e);

            throw e;

        } catch (Exception e) {

            LOGGER.error(

                    "Unable to clear and enter '" + text + "' text in field with locator -" + element + e);

            throw e;

        }

    }



    public static void selectDropDownOptionByText(WebElement element, String text) {

        try {

            Select dropDown = new Select(element);

            dropDown.selectByVisibleText(text);

            LOGGER.info("Selected option from " + element + " dropdown");

        } catch (StaleElementReferenceException e) {

            LOGGER.error("Element with " + element + " is not attached to the page document" + e);

            throw e;

        } catch (NoSuchElementException e) {

            LOGGER.error("Element " + element + " was not found in DOM" + e);

            throw e;

        } catch (Exception e) {

            LOGGER.error("Unable to select option from " + element + " dropdown" + e);

            throw e;

        }

    }



    public static void selectDropDownOptionByIndex(WebElement element, int index) {

        try {

            Select dropDown = new Select(element);

            dropDown.selectByIndex(index);

            LOGGER.info("Selected option from " + element + " dropdown");

        } catch (StaleElementReferenceException e) {

            LOGGER.error("Element with " + element + " is not attached to the page document" + e);

            throw e;

        } catch (NoSuchElementException e) {

            LOGGER.error("Element " + element + " was not found in DOM" + e);

            throw e;

        } catch (Exception e) {

            LOGGER.error("Unable to select option from " + element + " dropdown" + e);

            throw e;

        }

    }



    public static void selectDropDownOptionByValue(WebElement element, String value) {

        try {

            Select dropDown = new Select(element);

            dropDown.selectByValue(value);

            LOGGER.info("Selected option from " + element + " dropdown");

        } catch (StaleElementReferenceException e) {

            LOGGER.error("Element with " + element + " is not attached to the page document" + e);

            throw e;

        } catch (NoSuchElementException e) {

            LOGGER.error("Element " + element + " was not found in DOM" + e);

            throw e;

        } catch (Exception e) {

            LOGGER.error("Unable to select option from " + element + " dropdown" + e);

            throw e;

        }

    }



    public static void hoverOnElement(WebDriver driver, WebElement element) {

        try {

            Actions builder = new Actions(driver);

            builder.moveToElement(element).click().build().perform();

            try {

                Thread.sleep(2000);

            } catch (InterruptedException e) {

                LOGGER.error("Exception" + e);

            }

            LOGGER.info("Hovered on element " + element);

        } catch (NoSuchElementException e) {

            LOGGER.error("Element " + element + " was not found in DOM");

            throw e;

        } catch (Exception e) {



            LOGGER.error("Unable to hover the cursor on " + element);

            throw e;

        }

    }



    public static void deleteAllCookies(WebDriver driver) {

        try {

            driver.manage().deleteAllCookies();

        } catch (Exception e) {

            LOGGER.error("Some exception occurred while deleting all cookies" + e);

            throw e;

        }

    }



    public static void maximizeWindow(WebDriver driver) {

        try {

            driver.manage().window().maximize();

        } catch (Exception e) {

            LOGGER.error("Some exception occurred while deleting all cookies" + e);

            throw e;

        }

    }



    public static void refreshThePage(WebDriver driver) {

        try {

            driver.navigate().refresh();

        } catch (Exception e) {

            LOGGER.error("Some exception occurred while refreshing the page" + e);

            throw e;

        }

    }



    /**

     * This method returns the text of the specified element.

     */

    public static String getText(WebDriver driver, WebElement element, int timeOutInSeconds) {

        String elementText;

        try {

            waitForVisibilityOfElement(driver, element, timeOutInSeconds);

            elementText = element.getText().trim();

        } catch (Exception e) {

            LOGGER.error("Some exception occurred while getting the text of " + element + " -- " + e);

            throw e;

        }

        return elementText;

    }

}