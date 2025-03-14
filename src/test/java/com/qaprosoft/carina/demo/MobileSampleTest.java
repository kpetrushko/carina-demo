/*
 * Copyright 2013-2021 QAPROSOFT (http://qaprosoft.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qaprosoft.carina.demo;

import com.zebrunner.agent.core.annotation.TestLabel;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;

import com.qaprosoft.carina.demo.mobile.gui.pages.common.CarinaDescriptionPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.ContactUsPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.LoginPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.UIElementsPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.WebViewPageBase;
import com.qaprosoft.carina.demo.mobile.gui.pages.common.WelcomePageBase;
import com.qaprosoft.carina.demo.utils.MobileContextUtils;
import com.qaprosoft.carina.demo.utils.MobileContextUtils.View;
import org.testng.asserts.SoftAssert;

import static com.qaprosoft.carina.demo.constans.IConstans.*;


public class MobileSampleTest implements IAbstractTest, IMobileUtils {


    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void testLoginUser() {
        String username = "Test user";
        String password = RandomStringUtils.randomAlphabetic(10);
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        Assert.assertTrue(welcomePage.isPageOpened(), "Welcome page isn't opened");
        LoginPageBase loginPage = welcomePage.clickNextBtn();
        Assert.assertFalse(loginPage.isLoginBtnActive(), "Login button is active when it should be disabled");
        loginPage.typeName(username);
        loginPage.typePassword(password);
        loginPage.selectMaleSex();
        loginPage.checkPrivacyPolicyCheckbox();
        CarinaDescriptionPageBase carinaDescriptionPage = loginPage.clickLoginBtn();
        Assert.assertTrue(carinaDescriptionPage.isPageOpened(), "Carina description page isn't opened");
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void testWebView() {
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        LoginPageBase loginPage = welcomePage.clickNextBtn();
        loginPage.login();
        WebViewPageBase webViewPageBase = initPage(getDriver(), WebViewPageBase.class);
        MobileContextUtils contextHelper = new MobileContextUtils();
        contextHelper.switchMobileContext(View.WEB);
        ContactUsPageBase contactUsPage = webViewPageBase.goToContactUsPage();
        contactUsPage.typeName("John Doe");
        contactUsPage.typeEmail("some@email.com");
        contactUsPage.typeQuestion("This is a message");
        //TODO: [VD] move page driver related action outside from test class!
        hideKeyboard();
        contactUsPage.submit();
        Assert.assertTrue(contactUsPage.isErrorMessagePresent() || contactUsPage.isRecaptchaPresent(),
                "Error message or captcha was not displayed");
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestLabel(name = "feature", value = {"mobile", "acceptance"})
    public void testUIElements() {
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        LoginPageBase loginPage = welcomePage.clickNextBtn();
        CarinaDescriptionPageBase carinaDescriptionPage = loginPage.login();
        UIElementsPageBase uiElements = carinaDescriptionPage.navigateToUIElementsPage();
        final String text = "some text";
        final String date = "22/10/2018";
        final String email = "some@email.com";
        uiElements.typeText(text);
        Assert.assertEquals(uiElements.getText(), text, "Text was not typed");
        uiElements.typeDate(date);
        Assert.assertEquals(uiElements.getDate(), date, "Date was not typed");
        uiElements.typeEmail(email);
        Assert.assertEquals(uiElements.getEmail(), email, "Email was not typed");
        uiElements.swipeToFemaleRadioButton();
        uiElements.checkCopy();
        Assert.assertTrue(uiElements.isCopyChecked(), "Copy checkbox was not checked");
        uiElements.clickOnFemaleRadioButton();
        Assert.assertTrue(uiElements.isFemaleRadioButtonSelected(), "Female radio button was not selected!");
        uiElements.clickOnOtherRadioButton();
        Assert.assertTrue(uiElements.isOthersRadioButtonSelected(), "Others radio button was not selected!");
    }

    @Test()
    @MethodOwner(owner = "kpetrushko")
    @TestLabel(name = "feature", value = {"mobile", "regression"})
    public void testVerifyLoginPage() {
        String username = "Test user";
        String password = RandomStringUtils.randomAlphabetic(10);
        SoftAssert softAssert = new SoftAssert();
        WelcomePageBase welcomePage = initPage(getDriver(), WelcomePageBase.class);
        Assert.assertTrue(welcomePage.isPageOpened(), "Welcome page isn't opened");
        LoginPageBase loginPage = welcomePage.clickNextBtn();
        Assert.assertTrue(loginPage.isOpened(), "Login page isn`t opened");
        softAssert.assertTrue(loginPage.isInputFieldPresent(NAME_INPUT_FIELD), "Name input field isn't present");
        softAssert.assertTrue(loginPage.isInputFieldPresent(PASSWORD_INPUT_FIELD), "Password input field is null");
        softAssert.assertTrue(loginPage.isSexRadioBtnPresent(MALE_RADIO_BUTTON_FIELD), "Male radio button is null");
        softAssert.assertTrue(loginPage.isSexRadioBtnPresent(FEMALE_RADIO_BUTTON_FIELD), "Female radio button is null");
        softAssert.assertTrue(loginPage.isPrivacyPolicyCheckboxPresent(), "Privacy Policy Checkbox is null");
        loginPage.typeName(username);
        Assert.assertEquals(loginPage.getUsername(), username, String.format("Username [%s] was not typed, [%s] was typed instead",
                username, loginPage.getUsername()));
        Assert.assertEquals(loginPage.getUsername(), username, "Name was not typed");
        loginPage.typePassword(password);
        Assert.assertEquals(loginPage.getPassword(), password, "Password was not typed");
        Assert.assertEquals(loginPage.getPassword(), password, String.format("Password [%s] was not typed, [%s] was typed instead",
                password, loginPage.getPassword()));
        loginPage.selectSexRadioBtn(MALE_SEX);
        Assert.assertTrue(loginPage.isSexRadioBtnChecked(MALE_SEX), String.format("Sex [%s] Radio Button was not checked",
                MALE_SEX));
        loginPage.checkPrivacyPolicyCheckbox();
        Assert.assertTrue(loginPage.isPrivacyPolicyCheckboxChecked(), "Privacy Policy Checkbox was not checked");
        CarinaDescriptionPageBase carinaDescription = loginPage.clickLoginBtn();
        Assert.assertTrue(carinaDescription.isPageOpened(), "Carina description page isn't opened");
        WebViewPageBase webViewPage = carinaDescription.navigateToWebViewPage();
        Assert.assertTrue(webViewPage.isOpened(), "Web View Page isn`t opened");
        Assert.assertTrue(webViewPage.isWebViewElementPresent(), "There is no web view element");
        softAssert.assertAll();
    }
}