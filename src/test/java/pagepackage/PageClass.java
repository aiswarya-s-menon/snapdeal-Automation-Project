package pagepackage;

import java.io.File;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageClass {

    WebDriver driver;
    WebDriverWait wait;

    // ===== LOGIN =====
    @FindBy(xpath = "//span[text()='Login']")
    WebElement loginMenu;

    @FindBy(xpath = "//button[normalize-space()='LOGIN']")
    WebElement redLoginBtn;

    @FindBy(xpath = "//input[@placeholder='Mobile Number/ Email']")
    WebElement emailField;
    
    @FindBy(xpath = "//button[normalize-space()='CONTINUE']")
    WebElement continueBtn1;
    
    @FindBy(xpath = "//input[@placeholder='Enter Mobile Number']")
     WebElement mobileField;
    
    
    @FindBy(id="userName")
    WebElement nameField;

    @FindBy(id="userDob")
    WebElement dobField;

    @FindBy(id="userPassword")
    WebElement passwordField;

   // @FindBy(id="keepLoggedIn")
    //WebElement keepLoggedInCheckbox;

 //   @FindBy(xpath="//button[normalize-space()='Login']")
  //  WebElement loginBtn;
    
   // @FindBy(xpath = "//input[@placeholder='Code' and @maxlength='6']")
    //WebElement otpInputField;
    
    @FindBy(xpath = "//button[normalize-space()='CONTINUE']")
    WebElement continueBtn;

    @FindBy(xpath = "//button[contains(@class,'close') or @aria-label='Close']")
    WebElement closeLoginPopup;

    // ===== SEARCH =====
    @FindBy(id = "search-box-input")
    WebElement searchBox;

    @FindBy(xpath = "//label[@for='Color_s-Black']//span[contains(@class,'filter-color-tile')]")
    WebElement blackColorLabel;

    @FindBy(xpath = "//input[@name='fromVal']")
    WebElement minPriceBox;

    @FindBy(xpath = "//input[@name='toVal']")
    WebElement maxPriceBox;

    @FindBy(xpath = "//div[@class='price-go-arrow']/button") // GO button
    WebElement goBtn;

    @FindBy(xpath = "//div[contains(@class,'sort-selected')]")
    WebElement sortDropdown;

    @FindBy(xpath = "//li[@data-sorttype='plrty']")
    WebElement popularityOption;

    // ===== PRODUCT =====
    @FindBy(xpath = "//img[contains(@title,\"Women's Sandal\")]")
    WebElement firstPopularProduct;

    @FindBy(xpath = "//div[@class='attr-val' and text()='5']")
    WebElement size5;

    @FindBy(id = "add-cart-button-id")
    WebElement addToCartBtn;

    // Women's Fashion (main menu)
    @FindBy(xpath = "//div[normalize-space()=\"Women's Fashion\"]")
    WebElement womensFashion;

    // T-Shirts under Women's Fashion
    @FindBy(xpath = "//*[normalize-space()='T-Shirts']")
    WebElement tShirts;

    @FindBy(xpath = "//div[contains(@class,'filter-type-name') and contains(.,'Fabric')]/following-sibling::div[@class='filter-accordian']")
    WebElement fabricPlusIcon;

    @FindBy(xpath = "//label[@for='Fabric_s-Cotton']")
    WebElement cottonCheckbox;

    @FindBy(id = "filters")
    WebElement filterSection;

    // Selectable sizes (visible ones only)
    @FindBy(xpath = "//div[contains(@class,'attr-val') and text()='L']")
    WebElement sizeL;

    // Add to Cart button
    @FindBy(xpath = "//span[text()='add to cart']")
    WebElement addToCartButton;
	
    TakesScreenshot screenshot = (TakesScreenshot) driver;

    public PageClass(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // ===== LOGIN METHODS =====
    public void openLoginPopup() {
        Actions actions = new Actions(driver);
        actions.moveToElement(loginMenu).pause(Duration.ofSeconds(1)).click().perform();
    }

    public void clickRedLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(redLoginBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", redLoginBtn);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
    }
    public void contnue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn1));
        continueBtn1.click();
    }
    
   

    
    public void loginWithNewUIAutoSubmit(String mobile, String name, String dob, String password) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // MOBILE
        By mobileBy = By.xpath("//input[contains(@placeholder,'Mobile')]");
        WebElement mobileEl = wait.until(ExpectedConditions.presenceOfElementLocated(mobileBy));
        js.executeScript("arguments[0].scrollIntoView(true);", mobileEl);
        mobileEl.sendKeys(mobile);

        // NAME
        WebElement nameEl = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userName")));
        nameEl.sendKeys(name);

        // DOB
        WebElement dobEl = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userDob")));
        dobEl.sendKeys(dob);

        // PASSWORD
        WebElement pwdEl = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userPassword")));
        pwdEl.sendKeys(password);
    }

  //  public void enterOtp(String otp) {
    //    wait.until(ExpectedConditions.visibilityOf(otpInputField));
      //  emailField.clear();
       // emailField.sendKeys(otp);
    //}
   //  public void contnw() {
     //   wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
       // continueBtn.click();
   // }

    public void closeLoginPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(closeLoginPopup));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeLoginPopup);
    }

    // ===== SEARCH METHODS =====
    public void searchProduct(String productName) {
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBox);
        searchBox.clear();
        searchBox.sendKeys(productName);
        searchBox.sendKeys(Keys.ENTER);
    }

    public void applyBlackFilter() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", blackColorLabel);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", blackColorLabel);
    }

    public void setPriceRange(String minValue, String maxValue) {
        wait.until(ExpectedConditions.visibilityOf(minPriceBox));
        wait.until(ExpectedConditions.visibilityOf(maxPriceBox));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", maxPriceBox);
        minPriceBox.clear();
        minPriceBox.sendKeys(minValue);
        maxPriceBox.clear();
        maxPriceBox.sendKeys(maxValue);
        maxPriceBox.sendKeys(Keys.ENTER);
    }

    public void sortByPopularity() {
        wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
        sortDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(popularityOption));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", popularityOption);
    }

    // ===== PRODUCT ACTIONS =====
    public void clickPopularProduct() {
        wait.until(ExpectedConditions.visibilityOf(firstPopularProduct));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", firstPopularProduct);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstPopularProduct);
    }

    public void selectSize5() {
        wait.until(ExpectedConditions.visibilityOf(size5));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", size5);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", size5);
    }

    public void addToCart() {
        wait.until(ExpectedConditions.visibilityOf(addToCartBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", addToCartBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);
    }

    public void navigateToHome() {
        driver.navigate().to("https://www.snapdeal.com/");
    }

    public void hoverWomensFashionAndClickTShirts() {
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(womensFashion));
        actions.moveToElement(womensFashion).perform();
        wait.until(ExpectedConditions.elementToBeClickable(tShirts));
        actions.moveToElement(tShirts).click().perform();
    }

    public void applyCottonFabricFilter() {
        expandFabricFilter();
        selectCottonFabric();
    }

    public void expandFabricFilter() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", fabricPlusIcon);
        wait.until(ExpectedConditions.elementToBeClickable(fabricPlusIcon));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fabricPlusIcon);
    }

    public void selectCottonFabric() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", cottonCheckbox);
        wait.until(ExpectedConditions.elementToBeClickable(cottonCheckbox));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cottonCheckbox);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//img[contains(@class,'product-image') and contains(@title,'T-Shirt')])[1]")));
    }

    public void clickFirstCottonTshirtFast() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement tshirt = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//img[contains(@class,'product-image') and contains(@title,'T-Shirt')])[1]")));
        js.executeScript("window.scrollBy(0,200);");
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", tshirt);
        js.executeScript("arguments[0].dispatchEvent(new MouseEvent('click',{bubbles:true}));", tshirt);
        for (String win : driver.getWindowHandles()) {
            driver.switchTo().window(win);
        }
    }

    public void selectSizeL() {
        wait.until(ExpectedConditions.visibilityOf(sizeL));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", sizeL);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sizeL);
    }

    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", addToCartButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
    }

  
    public void clickCartIcon() throws InterruptedException {

        WebElement cartIcon = driver.findElement(By.xpath("//i[contains(@class,'cart')]"));
        cartIcon.click();

        // small pause so cart opens
        Thread.sleep(1500);
    }

  
    public void captureScreenshot() throws Exception {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        FileHandler.copy(
                src,
                new File("C:\\Users\\Admin\\Desktop\\luminar\\cart_m.png")
        );
    }


}