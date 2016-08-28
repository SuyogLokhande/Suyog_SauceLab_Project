//
//           Suyog Lokhande
//              Copyright
//      Confidential and Proprietary
//          ALL RIGHTS RESERVED
//           405 - 200 - 7802

// Aug 27, 2016

package com.lokhande.selenium.test.homework;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.net.URL;

import com.lokhande.selenium.utils.SauceHelpers;
import com.lokhande.selenium.utils.TestUtils;
import com.lokhande.selenium.test.homework.SauceTestBase;

import com.lokhande.selenium.pages.common.Page;
import com.lokhande.selenium.pages.guineaPigPage.GuineaPigPage;
import com.lokhande.selenium.pages.landingPage.LandingPage;
import com.lokhande.selenium.pages.pricingDetails.PricingDetails;



public class HomeworkMainTest {

	public static WebDriver driver;
	public static String buildTag = null;
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();   
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();
    
    
	public static String[] testNames = {"pricingPlanFeatureTest", "guineaPigEmailTest", "guineaPigLinkTest"};  
    final static Logger logger = Logger.getLogger(HomeworkMainTest.class);
    public static int testId = 0;
	public static final String USERNAME = "SuyogLokh";
	public static final String ACCESS_KEY = "9a43cc19-962a-4192-8d79-8dd59acb5e8d";
	public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
	
	
    private LandingPage landingPage;
    private PricingDetails pricingDetails;
    private GuineaPigPage guineaPigPage;
    private static String baseUrl1 = "https://saucelabs.com";
    private static String baseUrl2 = "https://saucelabs.com/test/guinea-pig";
    private String emailInput = "test1234@test.com";
    
    
    @DataProvider( name = "pricingForIndividual", parallel = true ) 
	public Object[][] pricingForIndividual(){
		return new Object[][]{
			{"INDIVIDUAL", "$49.99"}
		};		
	}
    
    
    @BeforeMethod
	public void beforeTest() throws InterruptedException, MalformedURLException {
    	
    	/*
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "46.0");
        caps.setCapability("name", testNames[testId]);
        caps.setCapability("tags", "Tag-1");
        caps.setCapability("build", "Build-0.0.1");

        webDriver.set(new RemoteWebDriver(new URL(URL), caps));
    	*/
		//this.driver = new FirefoxDriver();
		//driver = new ChromeDriver();	
	
        //webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);				
		//testId++;		
    }
    
    protected void createDriver(String browser, String version, String os, String methodName)
            throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // set desired capabilities to launch appropriate browser on Sauce
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        capabilities.setCapability(CapabilityType.VERSION, version);
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability("name", methodName);

        if (buildTag != null) {
            capabilities.setCapability("build", buildTag);
        }

        SauceHelpers.addSauceConnectTunnelId(capabilities);
        
        webDriver.set(new RemoteWebDriver(new URL(URL), capabilities));
        /*
        // Launch remote browser and set it as the current thread
        driver = new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + seleniumURI +"/wd/hub"),
                capabilities));
*/
        // set current sessionId
        String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        sessionId.set(id);
    }
    
    
    @AfterMethod
    public void teardown(){
    	webDriver.get().quit();
    }
    
    public WebDriver getWebDriver() {
        return webDriver.get();
    }
    
    public String getSessionId() {
        return sessionId.get();
    }
    
    
    @Test(priority=1, dataProvider="pricingForIndividual")
    public void pricingPlanFeatureTest(String planName, String expectedPlanValue) throws MalformedURLException{
    	String browser = "firefox";
    	String version = "46";
    	String os = "Windows 10";
    	String methodName = "pricingPlanFeatureTest";
    	
    	this.createDriver(browser, version, os, methodName);
        WebDriver driver = this.getWebDriver();
        
    	driver.get(baseUrl1);
    	TestUtils.waitForPageLoad(driver);
    	logger.info("Going to : "+baseUrl1);
		landingPage = PageFactory.initElements(driver, LandingPage.class);
		pricingDetails = PageFactory.initElements(driver, PricingDetails.class);
		
    	logger.info("\nRunning 'landingPageVerification' test case:");
    	boolean pricingVerification = landingPage.verifyPricingPresent();
    	Assert.assertEquals(pricingVerification, true);
    	landingPage.clickPricing(); 
    	
    	String individualCost = pricingDetails.getPricingForAPlan(pricingDetails.getPricingForEachPlan(), planName);
    	individualCost = "$"+individualCost;
    	logger.info("Cost for \"Individual\" membership is: " + individualCost );
    	Assert.assertEquals(expectedPlanValue, individualCost);    	
    }
	

    
    @Test(priority=2)
    public void guineaPigEmailTest() throws MalformedURLException{
    	String browser = "firefox";
    	String version = "46";
    	String os = "Windows 10";
    	String methodName = "guineaPigEmailTest";
    	
    	this.createDriver(browser, version, os, methodName);
        WebDriver driver = this.getWebDriver();
    	
    	logger.info("\nRunning 'guineaPigEmailTest' test case:");
    	driver.get(baseUrl2);
    	TestUtils.waitForPageLoad(driver);
    	logger.info("Going to : "+baseUrl2);
    	guineaPigPage = PageFactory.initElements(driver, GuineaPigPage.class);
    	guineaPigPage.enterEmail(emailInput);
    }
    
    @Test(priority=3)
    public void guineaPigLinkTest() throws MalformedURLException{
    	String browser = "firefox";
    	String version = "46";
    	String os = "Windows 10";
    	String methodName = "guineaPigLinkTest";
    	
    	this.createDriver(browser, version, os, methodName);
        WebDriver driver = this.getWebDriver();
        
    	logger.info("\nRunning 'guineaPigLinkTest' test case:");
    	driver.get(baseUrl2);
    	TestUtils.waitForPageLoad(driver);
    	logger.info("Going to : "+baseUrl2);
    	guineaPigPage = PageFactory.initElements(driver, GuineaPigPage.class);
    	guineaPigPage.clickOnLink();
    }
    
}
