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

    public abstract void selectSexRadioBtn(String MALE_SEX);

    public abstract boolean isMaleRadioBtnPresent();

    public abstract boolean isFemaleRadioBtnPresent();

    public abstract boolean isPasswordInputFieldPresent();

    public abstract boolean isNameInputFieldPresent();

    public abstract String getUsername();

    public abstract String getPassword();

    public abstract boolean isSexRadioBtnChecked(String MALE_SEX);

    public abstract boolean isPrivacyPolicyCheckboxChecked();

}
