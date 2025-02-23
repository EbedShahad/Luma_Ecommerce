package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestUtils {

    WebDriver driver;
    Actions action;
    WebDriverWait wait;

    // Constructor to initialize WebDriver, Actions, and WebDriverWait
    public TestUtils(WebDriver driver, Actions action) {
        this.driver = driver;
        this.action = action;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Global wait initialization
    }

    // Opens a webpage and maximizes the browser window
    public void openWebPage(String url) {
        driver.get(url);
        driver.manage().window().maximize(); // Maximize the window after opening the page
    }

    // Performs login by entering email and password and clicking the sign-in button
    public void logIn(String email, String password) {
        clickWhenReady(By.xpath("//a[contains(text(), 'Sign In')]")); // Click the sign-in link
        sendDataToElementById("email", email); // Enter the email
        sendDataToElementById("pass", password); // Enter the password
        clickWhenReady(By.id("send2")); // Click the login button
    }

    // Enters text into an input field using its ID
    public void sendDataToElementById(String elementId, String data) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));
        element.clear(); // Clear any pre-existing text
        element.sendKeys(data); // Enter the provided data
    }

    // Enters text into an input field using its XPath
    public void sendDataToElementByXpath(String elementXpath, String data) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
        element.clear(); // Clear any pre-existing text
        element.sendKeys(data); // Enter the provided data
    }

    // Hover over an element identified by its class name
    public void elementToHoverByClassName(String elementClass) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(elementClass)));
        action.moveToElement(element).perform(); // Hover over the element
        action.pause(8000).perform(); // Pause for 8 seconds (this might be unnecessary for real tests)
    }

    // Hover over an element identified by its XPath
    public void elementToHoverByXpath(String elementXpath) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
        action.moveToElement(element).perform(); // Hover over the element
        action.pause(8000).perform(); // Pause for 8 seconds
    }

    // Hover over an element identified by a partial link text
    public void elementToHoverByPartialLink(String elementPartialLink) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(elementPartialLink)));
        action.moveToElement(element).perform(); // Hover over the element
        action.pause(8000).perform(); // Pause for 8 seconds
    }

    // Click an element identified by its class name
    public void elementToClickByClassName(String elementClass) {
        clickWhenReady(By.className(elementClass)); // Wait until clickable and click the element
    }

    // Click an element identified by its ID
    public void elementToClickById(String elementId) {
        clickWhenReady(By.id(elementId)); // Wait until clickable and click the element
    }

    // Click an element identified by its XPath
    public void elementToClickByXpath(String elementXpath) {
        clickWhenReady(By.xpath(elementXpath)); // Wait until clickable and click the element
    }

    // Click an element identified by its partial link text
    public void elementToClickByPartialLinkText(String elementText) {
        clickWhenReady(By.partialLinkText(elementText)); // Wait until clickable and click the element
    }

    // Helper method to click an element when it's clickable (used by other methods)
    private void clickWhenReady(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator)); // Wait until element is clickable
        element.click(); // Click the element
    }

    // Checks if an element is present on the page based on its XPath
    public boolean isElementPresent(String xpath) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))); // Wait for element presence
            return true; // Return true if element is found
        } catch (Exception e) {
            return false; // Return false if element is not found
        }
    }

    // Check if an item has been successfully deleted by looking for a success message
    public boolean isItemDeleted(String successMessageXpath) {
        try {
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(successMessageXpath)));
            return successMessage.isDisplayed(); // Return true if success message is displayed
        } catch (Exception e) {
            return false; // Return false if item deletion is not confirmed
        }
    }

    // Wait for an element to be clickable and then click it
    public void waitForElementToBeClickable(String xpath) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))); // Wait for element to be clickable
        element.click(); // Click the element
    }
    //*************************************************************
    // Wait for an element to get text
    public String waitForElementToGetText(String xpath) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))); // Wait for element to be clickable
       return element.getText(); // Click the element
    }
}

