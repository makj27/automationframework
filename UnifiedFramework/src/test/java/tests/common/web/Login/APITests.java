package tests.common.web.Login;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.base.TestCaseInit;
import utilities.LoggerUtil;
import utilities.RestAssuredUtil;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;

import static utilities.GenericMethods.inputStreamToString;

@Log4j
public class APITests extends TestCaseInit {


    @Parameters({ "country" })
    @Test
    public void validateLoginTest(String country){
        try {
            LoggerUtil.logEntryForTestMethod(new Exception(),log);

            String xmlString = inputStreamToString(new FileInputStream(".\\src\\test\\resources\\payload.xml"));
            XmlMapper xmlMapper = new XmlMapper();
            xmlString = xmlMapper.writeValueAsString(xmlString);
            Response response = RestAssuredUtil.postResponse(ContentType.XML,
                    "https://ordering.hughesapi.net.uat1.net/pim/MessageReceiver.servlet",
                    "","5141","5141",xmlString);

        }
        catch(Exception ex){
            LoggerUtil.logErrorStackTrace(ex,log,null);
        }
        finally {
            LoggerUtil.logExitForTestMethod(new Exception(),log);
        }
    }


    public void getPageObject(String country) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        String className = country+"LoginPage";
        Class cls = Class.forName("pages."+country.toLowerCase()+".web."+className);
        //loginPage = (LoginBasePage) cls.getDeclaredConstructor().newInstance();

    }

}



