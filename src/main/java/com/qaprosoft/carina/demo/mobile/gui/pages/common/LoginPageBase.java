package com.qaprosoft.carina.demo.mobile.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.qaprosoft.carina.core.gui.AbstractPage;

public abstract class LoginPageBase extends AbstractPage {

    public LoginPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void typeName(String name);

    public abstract void typePassword(String password);

    public abstract void selectMaleSex();

    public abstract void checkPrivacyPolicyCheckbox();

    public abstract CarinaDescriptionPageBase clickLoginBtn();

    public abstract boolean isLoginBtnActive();

    public abstract CarinaDescriptionPageBase login();

    public abstract boolean isOpened();

    public abstract boolean isPrivacyPolicyCheckboxPresent();

    public abstract void selectSexRadioBtn(String sex);

    public abstract boolean isSexRadioBtnPresent(String fieldSex);

    public abstract boolean isInputFieldPresent(String fieldName);

    public abstract String getUsername();

    public abstract String getPassword();

    public abstract boolean isSexRadioBtnChecked(String sex);

    public abstract boolean isPrivacyPolicyCheckboxChecked();

}
