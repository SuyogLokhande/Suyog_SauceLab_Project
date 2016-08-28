//
//           Suyog Lokhande
//              Copyright
//      Confidential and Proprietary
//          ALL RIGHTS RESERVED
//           405 - 200 - 7802

// Jul 30, 2016

package com.lokhande.selenium.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestUtils {
	private static int defaultTimeout = 120;
 
    public static void write(WebElement we, String s) {
        we.clear();
        // Do not send null as this will fail for Chrome. From my experience :)
        we.sendKeys(s == null ? "": s);
    }
    	
    public static String getText(WebDriver driver, WebElement value) {
        
        String res = "";
        //The text value to capture
        String textOfValue = value.getText().trim();

        res = textOfValue;     
        return res;
    }
    public static void turnOnImplicitWait(WebDriver driver) {
        setWaitTime(driver, defaultTimeout, TimeUnit.SECONDS);
    }

    /**
     * Doesn't really turn off the implicit wait, but sets to 3 seconds timeout.
     */
    public static void turnOffImplicitWait(WebDriver driver) {
        setWaitTime(driver, 3, TimeUnit.SECONDS);
    }
    
    public static void setWaitTime(WebDriver driver, int time, TimeUnit unit) {
        driver.manage().timeouts().implicitlyWait(time, unit);
        driver.manage().timeouts().setScriptTimeout(time, unit);
        driver.manage().timeouts().pageLoadTimeout(time, unit);
    }
    
    public static boolean isDisplayed(WebDriver driver, WebElement target) {
        boolean isDisplayed;
        turnOffImplicitWait(driver);
        try {
            isDisplayed = target.isDisplayed();
        } catch (NoSuchElementException e) {
            isDisplayed = false;
        } finally {
            turnOnImplicitWait(driver);
        }
        return isDisplayed;
    }
    
    public static void highlightElement(WebDriver driver, WebElement element) {
		for (int i = 0; i < 2; i++) { 
			JavascriptExecutor js = (JavascriptExecutor) driver; 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 5px solid yellow;"); 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, ""); 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: green; border: 5px solid green;"); 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, ""); 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: blue; border: 5px solid blue;"); 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");  
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 5px solid red;"); 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 5px solid yellow;"); 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		}
		
	}
    
    public static void waitForPageLoad(final WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wdriver) {
                return ((JavascriptExecutor) driver).executeScript(
                    "return document.readyState"
                ).equals("complete");
            }
        });
    } 
    
    
    public static void setCheckCheckBoxState(WebElement checkBox, boolean checked)
            throws InvalidElementStateException {
        if (!checkBox.getAttribute("type").contentEquals("checkbox") ||
                !checkBox.getTagName().toLowerCase().contentEquals("input")){
            throw new InvalidElementStateException("This web element is not a checkbox!");
        }
        //we may wanna check if it is displayed and enabled, when performing actions.
        if ( checkBox.isDisplayed() && checkBox.isEnabled()){
            if (checkBox.isSelected() != checked) {
                checkBox.click();
            }
        } else {
            throw new InvalidElementStateException("Checkbox by "
                    + checkBox.getAttribute("id")
                    + " is disabled!");
        }

    }
    
    
    public static HashMap<String,String> combineListsIntoOrderedMap (List<String> keys, List<String> values) {
        if (keys.size() != values.size())
          throw new IllegalArgumentException ("Cannot combine lists with dissimilar sizes");
        HashMap<String,String> map = new LinkedHashMap<String,String>();
        for (int i=0; i<keys.size(); i++) {
          map.put(keys.get(i), values.get(i));
        }
        return map;
      }
    
}