package tests.brazil.web.Login;

import lombok.extern.log4j.Log4j;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.base.web.BaseLoginPage;
import tests.base.TestCaseInit;

import java.lang.reflect.InvocationTargetException;

@Log4j
public class LoginTests extends TestCaseInit {

    BaseLoginPage loginPage;

    @Parameters({ "country" })
    @Test
    public void validateLoginTest(String country) throws Exception {
        log.info("Inside login test");
        getPageObject(country);
        loginPage.navigateToLoginPage();
        loginPage.enterCredentialsAndSubmit();
        loginPage.validateHomeTitle();
    }

    public void getPageObject(String country) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        String className = country+"LoginPage";
        Class cls = Class.forName("pages."+country.toLowerCase()+".web."+className);
        loginPage = (BaseLoginPage) cls.getDeclaredConstructor().newInstance();

    }

}



