package RamesTests;

import org.example.TestUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class WishlistMangementTesting {
    Actions action, action1;
    WebDriver driver, driver1;
    TestUtils testUtils, testUtils1; // Utility class instances for interacting with elements
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setUp() {
        driver = new EdgeDriver(); // Initialize the WebDriver (EdgeDriver) for browser interaction
        action = new Actions(driver); // Initialize Actions object for performing complex actions (e.g., hover)
        testUtils = new TestUtils(driver, action); // Initialize TestUtils class to interact with the page
    }

    //test1 - > add aproduct to wishlist from product page
    // precondation :only valid login
    @Test (priority =0)
    public void addProductToWishlistFromProductPage() {
        testUtils.openWebPage("https://magento.softwaretestingboard.com/");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {// hover on women
            testUtils.logIn("hipolo3437@perceint.com", "1234@AAssDD");
            testUtils.elementToHoverByXpath("//div[2]/nav/ul/li[2]/a/span[2]");
           //hover on tops
            testUtils.elementToHoverByXpath("//nav/ul/li[2]/ul/li[1]/a/span[2]");
            //clicck on jackets
            testUtils.elementToClickByXpath("//nav/ul/li[2]/ul/li[1]/ul/li[1]/a/span");
            //click the jacket Nadia Elements Shell
            testUtils.elementToClickByPartialLinkText("Nadia Elements Shell");
            //click add to wishlist button
            testUtils.elementToClickByXpath("//div/div[1]/div[5]/div/a[1]/span");
            // check if the success massage appears or not
            boolean addMassage = driver.findElement(By.xpath("//div[2]/main/div[1]/div[2]/div/div/div")).isDisplayed();
            System.out.println("massage display status :"+ addMassage);
            Assert.assertTrue(addMassage,"No success massage  ");
        } catch (NoSuchElementException e) {
            System.err.println("additonalFiles.Wishlist button or success message not found: " + e.getMessage());
            throw new NoSuchElementException("additonalFiles.Wishlist button or success message not found.");
        } catch (Exception e) {
            System.err.println("An error occurred adding the product to wishlist: " + e.getMessage());
            throw new NoSuchSessionException("Session error during wishlist addition.");
        }
    }
    //test2 addToCartFromProductPage
    @Test(dependsOnMethods = {"addToCartFromProductPage"})
    public void addProductToWishlistFromCart() throws InterruptedException {
        testUtils.openWebPage("https://magento.softwaretestingboard.com/");
        // Log in to the account
        testUtils.logIn("domosa2356@btcours.com", "1234@AAssDD");
        //we have item in the cart
        Thread.sleep(200);
        //lets open cart
        testUtils.elementToClickByXpath("//div[2]/header/div[2]/div[1]/a");
        //lets chhose view details
        testUtils.elementToClickByXpath("//div[1]/div/div/div/div[2]/div[5]/div/a/span");
        //click on add to wishlist button
        testUtils.elementToClickByXpath("//*[@id=\"shopping-cart-table\"]/tbody/tr[2]/td/div/a[1]/span");
        //sucess massage appears
        boolean massage = testUtils.isElementPresent("//*[@id=\"maincontent\"]/div[2]/div[2]/div/div/div");
        softAssert.assertTrue(massage,"Add item to the wishlist was not succesed");


        System.out.println("Radiant Tee moved to wishlist successfully.");
        softAssert.assertAll();
    }
    //test3
    //test4 ->remove  items from wishlist
 @Test(priority = 2)
    public void RemoveItemFromFullWishlist() throws InterruptedException {
        testUtils.openWebPage("https://magento.softwaretestingboard.com/");
        //first login
        testUtils.logIn("domosa2356@btcours.com", "1234@AAssDD");
        //open dropdown menu
        testUtils.elementToClickByXpath("//header/div[1]/div/ul/li[2]/span/button");
        //choose wishlist from the menu
        testUtils.elementToClickByXpath("//header/div[1]/div/ul/li[2]/div/ul/li[2]/a");
        Thread.sleep(2000);
        //select an item to delete the "Radiant Tee" first :hover
        testUtils.elementToHoverByXpath("//div/a/span/span/img");
        //click on delete button
        testUtils.elementToClickByXpath("//div/div[3]/div[3]/a[2]");
        //check if the massage appears or not
        boolean massage = testUtils.isElementPresent("//main/div[1]/div[2]/div/div/div");
        softAssert.assertTrue(massage, "Remove was not successful ");
        softAssert.assertAll();
    }
        //Expected valuse
        String ExpectedName = "Set of Sprite Yoga";
        String price = "$14.00";

        //wishlist valuse ---product-item-name
      /* List<WebElement> wishlistItems = driver.findElements(By.cssSelector(".product-item-name a")); // Adjust selector if needed
       List<String> wishlistProductNames = new ArrayList<>();
       for (WebElement wishlistItem : wishlistItems) {
           wishlistProductNames.add(wishlistItem.getText().trim());
           System.out.println(" this product name is "+ wishlistItem.getText().trim());
       }

       // 5. Compare the product names
       Assert.assertEquals(wishlistProductNames, productName, "Product names do not match between product page and wishlist.");

//       String image="";
        //String productImageSrcWishlist = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-item-photo img"))).getAttribute("src");
    //add the product to wishlist=> hover to wishlist/click
        testUtils.elementToClickByXpath("//ol/li[1]/div/div/div[3]/div/div[2]/a[1]");
        //check the massage
       boolean AddMAssage= testUtils.isElementPresent("isElementPresent");
       softAssert.assertTrue(AddMAssage,"Add product doesn't work ");
       //find the product in the wishlist page

        List<WebElement> productItems = driver.findElements(By.cssSelector(".products.grid.search .product-item"));
        List<String> actualProductNames = new ArrayList<>();
        for (WebElement item : productItems) {
            WebElement productNameElement = item.findElement(By.cssSelector(".product-item-name a span"));
            actualProductNames.add(productNameElement.getText().trim());

        }
        boolean nameStauts,priceStauts,imageStatus;
        if (actualProductNames.contains(productName)) {
            System.out.println("same name  " + productName);
        } else {
            System.out.println("List does not contain: " + productName);
        }

       softAssert.assertAll();*/

    //test8-> share emails
@Test(dependsOnMethods = {"addProductToWishlistFromProductPage"})
public void ShareWishlistPositive() throws InterruptedException {
    //CartMangment cart = new addToCartFromProductPage();
    testUtils.openWebPage("https://magento.softwaretestingboard.com/");
    testUtils.logIn("hipolo3437@perceint.com", "1234@AAssDD");
    Thread.sleep(5000);
    testUtils.elementToClickByXpath("//div[2]/header/div[1]/div/ul/li[2]/span/button");
    testUtils.elementToClickByXpath("//div[2]/header/div[1]/div/ul/li[2]/div/ul/li[2]");
    testUtils.elementToClickByXpath("//*[@id=\"wishlist-view-form\"]/div[2]/div[1]/button[2]");
    testUtils.sendDataToElementByXpath("//*[@id=\"email_address\"]","rana.saeed.m.s@gmail.com");
    testUtils.sendDataToElementByXpath("//*[@id=\"message\"]","Whish list shared by email");
    testUtils.elementToClickByXpath("//*[@id=\"form-validate\"]/div/div[1]/button");
    Thread.sleep(5000);
    String successMessage = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")).getText();
    softAssert.assertEquals(successMessage, "Your wish list has been shared.");
    softAssert.assertAll();
}

   // test->9
   @Test(priority = 4)
   public void ShareWishlistMultiEmails() throws InterruptedException {
       //CartMangment cart = new addToCartFromProductPage();
       testUtils.openWebPage("https://magento.softwaretestingboard.com/");
       testUtils.logIn("rana.saeed.m.s@gmail.com", "r8n9K5@7VY_zG$");
       Thread.sleep(5000);
       testUtils.elementToClickByXpath("//div[2]/header/div[1]/div/ul/li[2]/span/button");
       testUtils.elementToClickByXpath("//div[2]/header/div[1]/div/ul/li[2]/div/ul/li[2]");
       testUtils.elementToClickByXpath("//*[@id=\"wishlist-view-form\"]/div[2]/div[1]/button[2]");
       testUtils.sendDataToElementByXpath("//*[@id=\"email_address\"]","johndoe@domain.com, johnsmith@domain.com.");
       testUtils.sendDataToElementByXpath("//*[@id=\"message\"]","Whish list shared by email");
       testUtils.elementToClickByXpath("//*[@id=\"form-validate\"]/div/div[1]/button");
       Thread.sleep(5000);
       boolean successMessage = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")).isDisplayed();
       softAssert.assertTrue(successMessage, "problem in sharing wishlist");
       softAssert.assertAll();
   }
   //-----------------
   // this method should be linked with rana's code ----/
    @Test(priority =1)
    public void addToCartFromProductPage() {
        // Open the website
        testUtils.openWebPage("https://magento.softwaretestingboard.com/");
        // Log in to the account
        testUtils.logIn("domosa2356@btcours.com", "1234@AAssDD");

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
    @AfterMethod
    public void CloseLoginPage() {
        driver.quit();
    }
}
