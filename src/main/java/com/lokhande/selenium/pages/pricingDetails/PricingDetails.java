//
//           Suyog Lokhande
//              Copyright
//      Confidential and Proprietary
//          ALL RIGHTS RESERVED
//           405 - 200 - 7802

// Aug 27, 2016

package com.lokhande.selenium.pages.pricingDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;

import com.lokhande.selenium.utils.TestUtils;
import com.lokhande.selenium.pages.common.Page;


public class PricingDetails extends Page{
	private static final Logger logger = Logger.getLogger(PricingDetails.class);
	
	public PricingDetails(WebDriver driver) {
		super(driver);
	}
	
	//Better to use CSS value than xpath. So if developer moved from one panel to other it will not break this script.
	//Finding all required elements required for LandingPage 

    
    @FindBy(css="[class='_2nr8 _2SWT _19fv _16Dj qHNJ']")
    private WebElement annualPricingButton;
    
    @FindBys({@FindBy(className="hsW9")})
    private List<WebElement> planList;
    
    @FindBys({@FindBy(className="_36Ed")})
    private List<WebElement> pricingList;
    
    public HashMap<String, String> getPricingForEachPlan(){
    	logger.info("Reading 'PRICING' for different plan:");
    	List<String> planNameList = new ArrayList<String>();
    	List<String> priceList = new ArrayList<String>();
    	
    	 for(WebElement el : planList) {
    	       logger.info(el.getText());
    	       planNameList.add(el.getText());
    	  }
    	 
    	 for(WebElement el : pricingList) {
  	       logger.info(el.getText());
  	       priceList.add(el.getText());
    	 }
    	 //Removing Enterprise from name list as it doesn't have price associated with it. 
    	 planNameList.remove(planNameList.size() - 1);
    	 
    	 HashMap<String,String> pricingMap = TestUtils.combineListsIntoOrderedMap (planNameList, priceList);
    	 logger.info(pricingMap);
    	 
    	 return pricingMap;
    	 
    }
    
    
    public String getPricingForAPlan(HashMap<String, String> pricingMap, String planName){
		
    	String value = pricingMap.get(planName);
    	
    	return value;   	
    	
    }
    
    
}
