//
//           Suyog Lokhande
//              Copyright
//      Confidential and Proprietary
//          ALL RIGHTS RESERVED
//           405 - 200 - 7802

// Jul 29, 2016

package com.lokhande.selenium.pages.common;

import org.openqa.selenium.WebDriver;

public abstract class Page {

    protected WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }
}
