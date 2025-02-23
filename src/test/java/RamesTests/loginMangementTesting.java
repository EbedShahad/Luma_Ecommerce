package RamesTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class loginMangementTesting {
    WebDriver driver;
    public String userName = "mtharwat871@gmail.com";
    public String password = "mtharwat871@";
    public void getInput() {
    }

    @BeforeTest
    public void OpenLoginPage() {
        this.driver = new EdgeDriver();
        //this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.manage().window().maximize();
        String LogInUrl = "https://magento.softwaretestingboard.com/customer/account/login";
        this.driver.navigate().to(LogInUrl);
    }

    //Enter the valid email and password then click the login button
    @Test
    public void LogInPositive() throws InterruptedException {
        WebElement UserNameTxt = this.driver.findElement(By.id("email"));
        WebElement PasswordTxt = this.driver.findElement(By.id("pass"));
        UserNameTxt.sendKeys(new CharSequence[]{userName});
        PasswordTxt.sendKeys(new CharSequence[]{password});
        WebElement LoginBtn = this.driver.findElement(By.id("send2"));
        LoginBtn.click();
        Thread.sleep(10000);
        boolean successMessage = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button")).isDisplayed();
        System.out.println(successMessage + " ======== Logged in Successfully ========");
        Assert.assertTrue(successMessage,"Login Failed");

    }

    //"Enter the null email and password then click the login button"
    @Test
    public void LogINNegative() throws InterruptedException {
        WebElement UserNameTxt = this.driver.findElement(By.id("email"));
        WebElement PasswordTxt = this.driver.findElement(By.id("pass"));
        UserNameTxt.sendKeys(new CharSequence[]{" "});
        PasswordTxt.sendKeys(new CharSequence[]{"mtharwat871@"});
        WebElement LoginBtn = this.driver.findElement(By.id("send2"));
        LoginBtn.click();
        Thread.sleep(10000);
        WebElement Message = driver.findElement(By.className("messages"));
        Assert.assertEquals(Message.getText(),"A login and a password are required.");
        System.out.println("------------ "+ Message.getText()+" -----------");
    }
    //"Enter the invalid email and password then click the login button"
    @Test
    public void InvalidEmailAndPassword() throws InterruptedException {
        WebElement UserNameTxt = this.driver.findElement(By.id("email"));
        WebElement PasswordTxt = this.driver.findElement(By.id("pass"));
        UserNameTxt.sendKeys(new CharSequence[]{"mthart8@gmail.com"});
        PasswordTxt.sendKeys(new CharSequence[]{"mtharw332"});
        WebElement LoginBtn = this.driver.findElement(By.id("send2"));
        LoginBtn.click();
        Thread.sleep(10000);
        WebElement Message = driver.findElement(By.className("messages"));
        Assert.assertEquals(Message.getText(),"The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.");
        System.out.println("------------ "+ Message.getText()+" -----------");
    }
    //"Enter the email and null password then click the login button"
    @Test
    public void CheckNullPassword() throws InterruptedException {
        WebElement UserNameTxt = this.driver.findElement(By.id("email"));
        WebElement PasswordTxt = this.driver.findElement(By.id("pass"));
        UserNameTxt.sendKeys(new CharSequence[]{"mthart871@gmail.com"});
        PasswordTxt.sendKeys(new CharSequence[]{""});
        WebElement LoginBtn = this.driver.findElement(By.id("send2"));
        LoginBtn.click();
        Thread.sleep(10000);
        WebElement Message = driver.findElement(By.className("messages"));
        Assert.assertEquals(Message.getText(),"A login and a password are required.");
        System.out.println("------------ "+ Message.getText()+" -----------");
    }
    // ENTER INVALID PASSWORD
    @Test
    public void InvalidPassword() throws InterruptedException {
        WebElement UserNameTxt = this.driver.findElement(By.id("email"));
        WebElement PasswordTxt = this.driver.findElement(By.id("pass"));
        UserNameTxt.sendKeys(new CharSequence[]{"mthart871@gmail.com"});
        PasswordTxt.sendKeys(new CharSequence[]{"helloo55"});
        WebElement LoginBtn = this.driver.findElement(By.id("send2"));
        LoginBtn.click();
        Thread.sleep(10000);
        WebElement Message = driver.findElement(By.className("messages"));
        Assert.assertEquals(Message.getText(),"The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.");
        System.out.println("------------ "+ Message.getText()+" -----------");
    }
    //Test With >> special characters in credentials
    @Test
    public void LogInWithSpecialCharacter() throws InterruptedException {
        WebElement UserNameTxt = this.driver.findElement(By.id("email"));
        WebElement PasswordTxt = this.driver.findElement(By.id("pass"));
        UserNameTxt.sendKeys(new CharSequence[]{"special@!#$.com"});
        PasswordTxt.sendKeys(new CharSequence[]{"mtharwat871@"});
        WebElement LoginBtn = this.driver.findElement(By.id("send2"));
        LoginBtn.click();
        Thread.sleep(10000);
        WebElement Message = driver.findElement(By.cssSelector("#email-error"));
        Assert.assertEquals(Message.getText(),"Please enter a valid email address (Ex: johndoe@domain.com).");
        System.out.println("------------ "+ Message.getText()+" -----------");
    }
    //Test Check Staying Logged In After Page Refreshing
    @Test
    public void CheckStayLoggedInAfterRefresh() throws InterruptedException {
        WebElement UserNameTxt = this.driver.findElement(By.id("email"));
        WebElement PasswordTxt = this.driver.findElement(By.id("pass"));
        UserNameTxt.sendKeys(new CharSequence[]{userName});
        PasswordTxt.sendKeys(new CharSequence[]{password});
        WebElement LoginBtn = this.driver.findElement(By.id("send2"));
        LoginBtn.click();
        Thread.sleep(10000);
        driver.navigate().refresh();
        Thread.sleep(10000);
        boolean successMessage = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button")).isDisplayed();
        System.out.println(successMessage + " ======== Stay Logged In After Refresh ========");
        Assert.assertTrue(successMessage,"Stay Login Failed");

    }
    // Test Log Out
    @Test
    public void LogOut() throws InterruptedException {
        WebElement UserNameTxt = this.driver.findElement(By.id("email"));
        WebElement PasswordTxt = this.driver.findElement(By.id("pass"));
        UserNameTxt.sendKeys(new CharSequence[]{"mtharwat871@gmail.com"});
        PasswordTxt.sendKeys(new CharSequence[]{"mtharwat871@"});
        WebElement LoginBtn = this.driver.findElement(By.id("send2"));
        LoginBtn.click();
        Thread.sleep(10000);
        WebElement DropDownMenu = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button"));
        DropDownMenu.click();
        WebElement LogoutLink = driver.findElement(By.className("authorization-link"));
        LogoutLink.click();
        Thread.sleep(10000);
        WebElement RedirectToHomePage = driver.findElement(By.className("page-title"));
        Assert.assertEquals(RedirectToHomePage.getText(),"Home Page");
    }
    @AfterTest
    public void CloseLoginPage() {
        driver.close();
    }

}
