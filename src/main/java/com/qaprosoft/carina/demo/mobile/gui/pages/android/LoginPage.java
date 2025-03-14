package com.qaprosoft.carina.demo.mobile.gui.pages.android;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType.Type;
import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.CarinaDescriptionPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.LoginPageBase;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import java.util.List;

import static com.qaprosoft.carina.demo.constans.IConstans.*;


@DeviceType(pageType = Type.ANDROID_PHONE, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase implements IMobileUtils {

    public static final Logger LOGGER = Logger.getLogger(LoginPage.class);

    @FindBy(id = "name")
    private ExtendedWebElement nameInputField;

    @FindBy(id = "password")
    private ExtendedWebElement passwordInputField;

    @FindBy(className = "android.widget.RadioButton")
    private List<ExtendedWebElement> sexRadioBtns;

    @FindBy(id = "radio_male")
    private ExtendedWebElement maleRadioBtn;

    @FindBy(id = "radio_female")
    private ExtendedWebElement femaleRadioBtn;

    @FindBy(id = "checkbox")
    private ExtendedWebElement privacyPolicyCheckbox;

    @FindBy(id = "login_button")
    private ExtendedWebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isInputFieldPresent(String fieldName) {
        if (fieldName.equals(NAME_INPUT_FIELD))
            return nameInputField.isPresent();
        if (fieldName.equals(PASSWORD_INPUT_FIELD))
            return passwordInputField.isPresent();
        else
            LOGGER.info("There is no input field that you choose on the page.");
        return false;
    }

    @Override
    public void selectSexRadioBtn(String sex) {
        if (sexRadioBtns.isEmpty())
            Assert.fail("There are no sex buttons on the page.");
        if (sex.equals(MALE_SEX))
            sexRadioBtns.get(0).click();
        else {
            if (sex.equals(FEMALE_SEX))
                sexRadioBtns.get(1).click();
            else
                Assert.fail("There is no sex that you choose.");
        }
    }

    @Override
    public boolean isSexRadioBtnPresent(String fieldSex) {
        if (fieldSex.equals(MALE_RADIO_BUTTON_FIELD))
            return maleRadioBtn.isPresent();
        if (fieldSex.equals(FEMALE_RADIO_BUTTON_FIELD))
            return femaleRadioBtn.isPresent();
        else
            LOGGER.info("There is no input field that you choose on the page.");
        return false;
    }

    @Override
    public boolean isPrivacyPolicyCheckboxPresent() {
        return privacyPolicyCheckbox.isPresent();
    }

    @Override
    public String getUsername() {
        return nameInputField.getText();
    }

    @Override
    public String getPassword() {
        return passwordInputField.getText();
    }

    @Override
    public void typeName(String name) {
        nameInputField.type(name);
        hideKeyboard();
    }

    @Override
    public void typePassword(String password) {
        passwordInputField.type(password);
    }

    @Override
    public void selectMaleSex() {
        maleRadioBtn.click();
    }

    @Override
    public boolean isSexRadioBtnChecked(String sex) {
       if (sexRadioBtns.isEmpty())
           Assert.fail("There are no sex buttons on the page.");
       if (sex.equals(MALE_SEX))
          return sexRadioBtns.get(0).isChecked();
        if (sex.equals(FEMALE_SEX))
            return sexRadioBtns.get(1).isChecked();
        else
            LOGGER.info("There is no sex that you choose.");
        return false;
    }

    @Override
    public boolean isPrivacyPolicyCheckboxChecked() {
        return privacyPolicyCheckbox.isChecked();
    }

    @Override
    public void checkPrivacyPolicyCheckbox() {
        privacyPolicyCheckbox.click();
    }

    @Override
    public CarinaDescriptionPageBase clickLoginBtn() {
        loginBtn.click();
        return initPage(getDriver(), CarinaDescriptionPageBase.class);
    }


    @Override
    public boolean isLoginBtnActive() {
        return Boolean.parseBoolean(loginBtn.getAttribute("enabled"));
    }

    @Override
    public CarinaDescriptionPageBase login() {
        String username = "Test user";
        String password = RandomStringUtils.randomAlphabetic(10);
        typeName(username);
        typePassword(password);
        selectMaleSex();
        checkPrivacyPolicyCheckbox();
        return clickLoginBtn();
    }

    @Override
    public boolean isOpened() {
        return loginBtn.isElementPresent();
    }

}
