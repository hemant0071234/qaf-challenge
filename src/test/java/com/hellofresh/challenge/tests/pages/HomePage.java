package com.hellofresh.challenge.tests.pages;

import com.hellofresh.challenge.base.BasePage;
import com.hellofresh.challenge.driver.WebUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage<HomePage> {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getURL() {
        return "/";
    }

    @FindBy(css = "a.login")
    private WebElement linkLogin;

    @FindBy(id = "search_query_top")
    private WebElement inputSearchQuery;

    @FindBy(name = "submit_search")
    private WebElement btnSubmitSearch;

    public AuthenticationPage signIn(){
        WebUtils.clickWithWaitForElement(wd, linkLogin, 5000);

        WebUtils.waitForPageLoad(wd);
        return PageFactory.initElements(wd, AuthenticationPage.class);
    }

}


