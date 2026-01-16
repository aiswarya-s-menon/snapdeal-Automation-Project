package testpackage;

import org.testng.annotations.Test;
import basepackage.BaseClass;
import pagepackage.PageClass;
import utilities.ExcelUtilities;

public class TestClass extends BaseClass {

	@Test
	public void verifyLoginAndShop() throws Exception {

	    PageClass p1 = new PageClass(driver);

	    String xl = "C:\\Users\\Admin\\Desktop\\Book2.xlsx";
	    String sheet = "Sheet1";

	    String email = ExcelUtilities.getCellValue(xl, sheet, 1, 0);
	    String mobile = ExcelUtilities.getCellValue(xl, sheet, 1, 1);
	    String name = ExcelUtilities.getCellValue(xl, sheet, 1, 2);
	    String dob = ExcelUtilities.getCellValue(xl, sheet, 1, 3);
	    String password = ExcelUtilities.getCellValue(xl, sheet, 1, 4);

	    // LOGIN
	    p1.openLoginPopup();
	    p1.clickRedLoginButton();
	    p1.enterEmail(email);
	    p1.contnue();
	    p1.loginWithNewUIAutoSubmit(mobile, name, dob, password);
	   // p1.contnw();
	   // p1.closeLoginPopupIfPresent();

	    // SHOP
	    p1.searchProduct("Sandals for Women");
	    p1.applyBlackFilter();
	    p1.setPriceRange("106", "1000");
	    p1.sortByPopularity();
	    p1.clickPopularProduct();
	    switchToProductWindow();
	    p1.selectSize5();
	    p1.addToCart();
	    p1.navigateToHome();

	    p1.hoverWomensFashionAndClickTShirts();
	    p1.expandFabricFilter();
	    p1.selectCottonFabric();
	    p1.clickFirstCottonTshirtFast();
	    switchToProductWindow();
	    p1.selectSizeL();
	    p1.clickAddToCart();

	    p1.clickCartIcon();
	    p1.captureScreenshot();
	}
}