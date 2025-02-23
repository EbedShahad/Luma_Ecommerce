package RamesTests;

import org.example.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CartManagementTesting {
    // Declare Actions and WebDriver instances for interacting with the browser
    Actions action, action1;
    WebDriver driver, driver1;
    TestUtils testUtils, testUtils1; // Utility class instances for interacting with elements
    SoftAssert softAssert = new SoftAssert(); // SoftAssert to collect assertions and report after test execution

    // This method will run before each test to set up the environment
    @BeforeMethod
    public void setUp() {
        driver = new EdgeDriver(); // Initialize the WebDriver (EdgeDriver) for browser interaction
        action = new Actions(driver); // Initialize Actions object for performing complex actions (e.g., hover)
        testUtils = new TestUtils(driver, action); // Initialize TestUtils class to interact with the page
    }

    // Test Case: Add an item to the cart from the product page
    @Test(priority = 0)
    public void addToCartFromProductPage() {
        // Open the website
        testUtils.openWebPage("https://magento.softwaretestingboard.com/");
        // Log in to the account
        testUtils.logIn("rana.saeed.m.s@gmail.com", "r8n9K5@7VY_zG$");

        // Hover over product item and click on it to open the product page
        testUtils.elementToHoverByClassName("product-item-info");
        testUtils.elementToClickByClassName("product-item-info");

        // Select size and color for the product
        testUtils.elementToClickById("option-label-size-143-item-166");
        testUtils.elementToClickById("option-label-color-93-item-50");

        // Add the product to the cart
        testUtils.elementToClickById("product-addtocart-button");

        // Check if the item was added to the cart successfully by verifying the success message
        boolean isElementPresent = testUtils.isElementPresent("//div[contains(@data-bind, 'message.type')]");
        softAssert.assertTrue(isElementPresent);
        String successMessage = driver.findElement(By.xpath("//div[contains(@data-bind, 'message.type')]")).getText();
        softAssert.assertEquals(successMessage, "You added Radiant Tee to your shopping cart.");
        softAssert.assertAll(); // Validate all assertions
    }

    // Test Case: Add an item to the cart from the wishlist page
    @Test(priority = 1)
    public void addToCartFromWishlistPage() throws InterruptedException {
        testUtils.openWebPage("https://magento.softwaretestingboard.com/");
        testUtils.logIn("rana.saeed.m.s@gmail.com", "r8n9K5@7VY_zG$");
        Thread.sleep(10000);

        // Hover over the wishlist item and add it to the cart
        testUtils.elementToHoverByPartialLink("Breathe-Easy Tank");
        testUtils.elementToClickByPartialLinkText("Breathe-Easy Tank");
        testUtils.elementToClickByXpath("//a[contains(@class, 'action towishlist')]");

        // Hover over the product in the wishlist and click to add it to cart
        testUtils.elementToHoverByXpath("//li[@class='product-item']");
        testUtils.elementToClickByXpath("//button[@data-role='tocart']");

        // Select size and color, then add to cart
        testUtils.elementToClickById("option-label-size-143-item-166");
        testUtils.elementToClickById("option-label-color-93-item-57");
        testUtils.elementToClickById("product-addtocart-button");
        Thread.sleep(10000);

        // Check if the item was successfully added to the cart
        boolean isElementPresent = testUtils.isElementPresent("//div[contains(@data-bind, 'message.type')]");
        softAssert.assertTrue(isElementPresent);
        String successMessage = driver.findElement(By.xpath("//div[contains(@data-bind, 'message.type')]")).getText();
        softAssert.assertEquals(successMessage, "You added Breathe-Easy Tank to your shopping cart.");
        softAssert.assertAll();
    }

    // Test Case: Update the item quantity in the cart (increase quantity)
    @Test(priority = 2, dependsOnMethods = {"addToCartFromProductPage"})
    public void UpdateItemQuantityIncrease() throws InterruptedException {
        testUtils.openWebPage("https://magento.softwaretestingboard.com/");
        testUtils.logIn("rana.saeed.m.s@gmail.com", "r8n9K5@7VY_zG$");
        Thread.sleep(5000);

        // Go to the shopping cart
        testUtils.elementToClickByXpath("//a[@class='action showcart']");
        testUtils.elementToClickByXpath("//a[@class='action viewcart']");

        // Increase the item quantity in the cart
        testUtils.sendDataToElementByXpath("//input[@data-cart-item-id='WS12-XS-Blue' and @data-role='cart-item-qty']", "2");
        testUtils.elementToClickByXpath("//button[@name='update_cart_action' and @class='action update']");
        Thread.sleep(5000);

        // Verify if the quantity has been updated correctly
        String quantity = driver.findElement(By.xpath("//input[@data-cart-item-id='WS12-XS-Blue' and @data-role='cart-item-qty']")).getAttribute("value");
        System.out.println("quantity: " + quantity);
        softAssert.assertEquals(quantity, "2");
        softAssert.assertAll();
    }

    // Test Case: Update the item quantity in the cart (decrease quantity)
    @Test(priority = 3, dependsOnMethods = {"UpdateItemQuantityIncrease"}) // Depends on the "UpdateItemQuantityIncrease" test
    public void UpdateItemQuantityDecrease() throws InterruptedException {
        testUtils.openWebPage("https://magento.softwaretestingboard.com/");
        testUtils.logIn("rana.saeed.m.s@gmail.com", "r8n9K5@7VY_zG$");
        Thread.sleep(5000);

        // Go to the shopping cart
        testUtils.elementToClickByXpath("//a[@class='action showcart']");
        testUtils.elementToClickByXpath("//a[@class='action viewcart']");

        // Decrease the item quantity in the cart
        testUtils.sendDataToElementByXpath("//input[@data-cart-item-id='WS12-XS-Blue' and @data-role='cart-item-qty']", "1");
        testUtils.elementToClickByXpath("//button[@name='update_cart_action' and @class='action update']");
        Thread.sleep(5000);

        // Verify if the quantity has been updated correctly
        String quantity = driver.findElement(By.xpath("//input[@data-cart-item-id='WS12-XS-Blue' and @data-role='cart-item-qty']")).getAttribute("value");
        System.out.println("quantity: " + quantity);
        softAssert.assertEquals(quantity, "1");
        softAssert.assertAll();
    }

    // Test Case: Remove an item from the cart
    @Test(priority = 4, dependsOnMethods = {"addToCartFromProductPage"})
    public void RemoveSingleItemFromCart() throws InterruptedException {
        testUtils.openWebPage("https://magento.softwaretestingboard.com/");
        testUtils.logIn("rana.saeed.m.s@gmail.com", "r8n9K5@7VY_zG$");
        Thread.sleep(5000);

        // Go to the shopping cart and remove an item
        testUtils.elementToClickByXpath("//a[@class='action showcart']");
        testUtils.elementToClickByXpath("//a[@class='action viewcart']");
        testUtils.elementToClickByXpath("//a[@title='Remove item' and @class='action action-delete']");
        Thread.sleep(5000);

        // Check if the item is deleted from the cart
        boolean isItemDeleted = testUtils.isItemDeleted("//a[contains(@title, 'Radiant Tee')]");
        softAssert.assertFalse(isItemDeleted);
        softAssert.assertAll();
    }

    // Test Case: Verify cart persistence after logging out
    @Test(priority = 5)
    public void CartPersistenceAfterLogout() throws InterruptedException {
        testUtils.openWebPage("https://magento.softwaretestingboard.com/");
        testUtils.logIn("rana.saeed.m.s@gmail.com", "r8n9K5@7VY_zG$");
        Thread.sleep(5000);

        // Waiting for the "Show Cart" button to be clickable
        testUtils.waitForElementToBeClickable("//a[@class='action showcart']");

        // Waiting for the "View Cart" button to be clickable
        testUtils.waitForElementToBeClickable("//a[@class='action viewcart']");

        // Get cart items before logging out
        String itemBeforeLogOut = driver.findElement(By.id("shopping-cart-table")).getText();
        System.out.println("itemBeforeLogOut: " + itemBeforeLogOut);

        // Log out and log back in
        testUtils.elementToClickByXpath("//button[@data-action='customer-menu-toggle']");
        testUtils.elementToClickByXpath("//a[@href='https://magento.softwaretestingboard.com/customer/account/logout/']");
        testUtils.logIn("rana.saeed.m.s@gmail.com", "r8n9K5@7VY_zG$");
        Thread.sleep(5000);
        // Waiting for the "Show Cart" button to be clickable
        testUtils.waitForElementToBeClickable("//a[@class='action showcart']");

        // Waiting for the "View Cart" button to be clickable
        testUtils.waitForElementToBeClickable("//a[@class='action viewcart']");

        // Verify if cart items are the same after logging back in
        String itemAfterLogOut = driver.findElement(By.id("shopping-cart-table")).getText();
        System.out.println("itemAfterLogOut: " + itemAfterLogOut);
        softAssert.assertEquals(itemAfterLogOut, itemBeforeLogOut);
        softAssert.assertAll();
    }

    // Test Case: Verify cart persistence after browser restart
    @Test(priority = 6)
    public void CartPersistenceAfterBrowserRestart() throws InterruptedException {
        // Test actions are similar to CartPersistenceAfterLogout, but browser is restarted in between
        testUtils.openWebPage("https://magento.softwaretestingboard.com/");
        testUtils.logIn("rana.saeed.m.s@gmail.com", "r8n9K5@7VY_zG$");
        Thread.sleep(5000);

        // Waiting for the "Show Cart" button to be clickable
        testUtils.waitForElementToBeClickable("//a[@class='action showcart']");

        // Waiting for the "View Cart" button to be clickable
        testUtils.waitForElementToBeClickable("//a[@class='action viewcart']");

        // Get cart items before restarting browser
        String itemBeforeLogOut = driver.findElement(By.id("shopping-cart-table")).getText();
        driver.close(); // Close the browser

        // Restart WebDriver (create a new session)
        driver1 = new EdgeDriver();
        action1 = new Actions(driver1);
        testUtils1 = new TestUtils(driver1, action1);
        driver1.get("https://magento.softwaretestingboard.com/");
        driver1.manage().window().maximize();

        // Log back in
        testUtils1.logIn("rana.saeed.m.s@gmail.com", "r8n9K5@7VY_zG$");
        Thread.sleep(5000);

        // Waiting for the "Show Cart" button to be clickable
        testUtils1.waitForElementToBeClickable("//a[@class='action showcart']");

        // Waiting for the "View Cart" button to be clickable
        testUtils1.waitForElementToBeClickable("//a[@class='action viewcart']");

        // Get cart items after restarting the browser
        String itemAfterLogOut = driver1.findElement(By.id("shopping-cart-table")).getText();

        // Verify if cart items are the same after browser restart
        softAssert.assertEquals(itemAfterLogOut, itemBeforeLogOut);
        softAssert.assertAll();
    }

    // Test Case: Validate the behavior when adding a very high quantity to cart
    @Test(priority = 7)
    public void AvailableQuantityWhenAddingToCart() throws InterruptedException {
        testUtils.openWebPage("https://magento.softwaretestingboard.com/");
        testUtils.logIn("rana.saeed.m.s@gmail.com", "r8n9K5@7VY_zG$");
        Thread.sleep(5000);

        // Hover over the product and click to open the product page
        testUtils.elementToHoverByClassName("product-item-info");
        testUtils.elementToClickByClassName("product-item-info");

        // Select size and color
        testUtils.elementToClickById("option-label-size-143-item-166");
        testUtils.elementToClickById("option-label-color-93-item-50");

        // Attempt to add an unreasonably high quantity to the cart
        testUtils.sendDataToElementByXpath("//input[@type='number' and @name='qty' and @id='qty']", "10001");
        testUtils.elementToClickById("product-addtocart-button");

        // Verify if error message is shown for invalid quantity
        boolean isElementPresent = testUtils.isElementPresent("//div[@for='qty' and @generated='true' and @class='mage-error']");
        softAssert.assertTrue(isElementPresent);
        softAssert.assertAll();
    }


    // After each test, close the browser and clean up resources
    @AfterMethod
    public void close() {
        // Quit the driver instances to close the browser properly
        if (driver != null) {
            driver.quit();
        }
        if (driver1 != null) {
            driver1.quit();
        }
    }
}
