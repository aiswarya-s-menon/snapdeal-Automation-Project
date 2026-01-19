package testpackage;

import org.testng.annotations.Test;
import basepackage.BaseClass;
import pagepackage.PageClass;
import utilities.ExcelUtilities;

public class TestClass extends BaseClass {

    @Test
    public void verifyLoginAndShop() throws Exception {

        PageClass p1 = new PageClass(driver);

        // Excel path and sheet
        String xl = "C:\\Users\\Admin\\Desktop\\Book2.xlsx";
        String sheet = "Sheet1";

        int rowCount = ExcelUtilities.getRowCount(xl, sheet);
      //  System.out.println("Total rows in Excel: " + rowCount);

        // Loop through Excel rows (start from 1 if row 0 is header)
        for (int i = 1; i <= rowCount; i++) {

            // Read Excel data in order matching PageClass
            String email = ExcelUtilities.getCellValue(xl, sheet, i, 0);
            System.out.println("Email--------- " + email);
            String mobile = ExcelUtilities.getCellValue(xl, sheet, i, 1);
            System.out.println("Mobile-------- " + mobile);
            String name = ExcelUtilities.getCellValue(xl, sheet, i, 2);
            System.out.println("Name---------- " + name);
            String dob = ExcelUtilities.getCellValue(xl, sheet, i, 3);
            System.out.println("DOB----------- " + dob);
            String password = ExcelUtilities.getCellValue(xl, sheet, i, 4);
            System.out.println("Password-------- " + password);

           
            /* ================= LOGIN ================= */

            p1.openLoginPopup();
            p1.clickRedLoginButton();
            Thread.sleep(500);
            p1.enterEmail(email);
            Thread.sleep(500);
            p1.contnue();
            Thread.sleep(500);
            p1.enterMobile(mobile);
            Thread.sleep(500);
            p1.enterName(name);
            Thread.sleep(500);
            p1.selectDOB();

            Thread.sleep(500);
            p1.enterPassword(password);
            Thread.sleep(500);

            p1.contnuee(); 
            Thread.sleep(500);               // Continue after new UI login
            p1.closeLoginPopup();           // Close login popup

            /* ================= SEARCH & FILTER ================= */
            p1.searchProduct("Sandals for Women");
            p1.applyBlackFilter();
            p1.setPriceRange("106", "1000");
            p1.sortByPopularity();
            p1.clickPopularProduct();
            switchToProductWindow(); // BaseClass method
            p1.selectSize5();
            p1.addToCart();
            p1.navigateToHome();

            // Women's Fashion → T-Shirts → Cotton → Add to Cart
            p1.hoverWomensFashionAndClickTShirts();
            p1.expandFabricFilter();
            p1.selectCottonFabric();
            p1.clickFirstCottonTshirtFast();
            switchToProductWindow();
            p1.selectSizeL();
            p1.clickAddToCart();

            // Open cart and capture screenshot
            p1.clickCartIcon();
            p1.captureScreenshot();
        }
    }
}
