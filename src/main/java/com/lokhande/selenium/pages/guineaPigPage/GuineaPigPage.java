//
//           Suyog Lokhande
//              Copyright
//      Confidential and Proprietary
//          ALL RIGHTS RESERVED
//           405 - 200 - 7802

// Aug 28, 2016

package com.lokhande.selenium.pages.guineaPigPage;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.lokhande.selenium.utils.TestUtils;
import com.lokhande.selenium.pages.common.Page;


public class GuineaPigPage extends Page{
	
	private static final Logger logger = Logger.getLogger(GuineaPigPage.class);
	
	public GuineaPigPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id="unchecked_checkbox")
	private WebElement uncheckedCheckbox;

	@FindBy(id="checked_checkbox")
	private WebElement checkedCheckbox;

	@FindBy(id="i am a link")
	private WebElement theActiveLink;

	@FindBy(id="i_am_a_textbox")
	private WebElement textInput;

	@FindBy(id="your_comments")
	private WebElement yourCommentsSpan;

	@FindBy(id="fbemail")
	private WebElement emailTextInput;

	@FindBy(id="comments")
	private WebElement commentsTextAreaInput;

	@FindBy(id="submit")
	private WebElement submitButton;
	
	public void checkUncheckedCheckBox() {
		TestUtils.setCheckCheckBoxState(this.uncheckedCheckbox, true);
	}

	public void enterEmail(String emailInput) {
		logger.info("Checking unchecked check box");
		checkUncheckedCheckBox();
		logger.info("Entering email: " +emailInput);
		TestUtils.write(emailTextInput, emailInput);
		logger.info("Clicking on 'Submit' button");
		submitButton.click();
	}

	public void clickOnLink() {
		logger.info("Clicking on 'i am a link'");
		theActiveLink.click();
		logger.info("Navigating back to previous page.");
		driver.navigate().back();
	}
	
}
