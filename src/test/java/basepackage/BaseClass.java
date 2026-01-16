package basepackage;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {

    public static ChromeDriver driver;
    private ExtentSparkReporter reporter;
    private ExtentReports extent;
    protected ExtentTest test;

    @BeforeTest
    public void extentReport() {
        reporter = new ExtentSparkReporter("./Reports/myreport2.html");
        reporter.config().setDocumentTitle("Automation report");
        reporter.config().setReportName("Functional test");
        reporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("host name", "local host");
        extent.setSystemInfo("os", "Windows 10");
        extent.setSystemInfo("tester name", "Aiswarya");
        extent.setSystemInfo("browser name", "chrome");
    }

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.snapdeal.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    
 //ADD WINDOW HANDLE METHOD HERE (NO ANNOTATION)
    public void switchToProductWindow() {
        Set<String> windows = driver.getWindowHandles();
        for (String win : windows) {
            driver.switchTo().window(win);
        }
    }

    @BeforeMethod
    public void starttest(Method method) {
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test case failed: " + result.getName());
            test.log(Status.FAIL, result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test case skipped: " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test case passed: " + result.getName());
        }

        extent.flush();
       // driver.quit();
    }
   
}
