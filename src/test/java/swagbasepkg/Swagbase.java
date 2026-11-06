package swagbasepkg;



import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import swagpagepkg.Swagloginpage;

public class Swagbase {
	public  static WebDriver driver;
	public Swagloginpage swagobj;
	
	public  static ExtentSparkReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeTest
	public void browsersetup() {
		reporter=new ExtentSparkReporter("./Reports/myreport1.html"); //create a report
		reporter.config().setDocumentTitle("Automationreport");
		reporter.config().setReportName("functional test");
		reporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Hostname", "localname");
		extent.setSystemInfo("Os", "Windows 10");
		extent.setSystemInfo("Testername", "abc");
		extent.setSystemInfo("Browsername", "Chrome");
		
		
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		
		
	}
	@BeforeMethod
	public void mthd(Method method) {
		swagobj=new Swagloginpage(driver);
		
	}
	
	@AfterMethod
	public void aftmthd(ITestResult result) throws Exception{
		if(result.getStatus()==ITestResult.FAILURE) {
			
			test.log(Status.FAIL,"test case failed is "+ result.getName());
			test.log(Status.FAIL,"test case failed is "+ result.getThrowable());
			
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			test.pass("Test Passed : "+  result.getName());
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			test.skip("Test Skipped : "+ result.getName());
		}
	}
	@AfterTest
	public void endReport() {
		extent.flush();
		driver.quit();
	}
	
	
}
