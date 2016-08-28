//
//           Suyog Lokhande
//              Copyright
//      Confidential and Proprietary
//          ALL RIGHTS RESERVED
//           405 - 200 - 7802

// Aug 27, 2016

package com.lokhande.selenium.pages.landingPage;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.lokhande.selenium.utils.TestUtils;
import com.lokhande.selenium.pages.common.Page;

public class LandingPage extends Page{
	
	private static final Logger logger = Logger.getLogger(LandingPage.class);
	
	public LandingPage(WebDriver driver) {
		super(driver);
	}
	
	//Better to use CSS value than xpath. So if developer moved from one panel to other it will not break this script.
	//Finding all required elements required for LandingPage    
   
    @FindBy(css="a[href='/pricing']")
    private WebElement pricingButton;
	
    //Find all other elements on this page and write methods to verify elements are present
    
    public boolean verifyPricingPresent(){
    	logger.info("Verifying 'PRICING' is present on landing page");
    	boolean pricingDisplayed = TestUtils.isDisplayed(driver, pricingButton);
    	return pricingDisplayed;
    }
    
    public void clickPricing(){
    	logger.info("Clickig on Pricing");
    	pricingButton.click();
    	TestUtils.waitForPageLoad(driver);    	
    }


}
