package framework.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;


public class MobileDriverFactory{
    private RemoteWebDriver driver;
    public RemoteWebDriver getDriver(MobileDriverType mobileDriverType, MobilePlatform mobilePlatform) throws MalformedURLException {

        try {

            if (driver == null) {
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

                desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, mobilePlatform.toString());
                desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                desiredCapabilities.setCapability("appPackage", "");
                desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
                //move to config all the details

                switch (mobileDriverType.toString().toUpperCase()){

                    case "REAL_DEVICE": {
                        desiredCapabilities.setCapability(MobileCapabilityType.APP, "");
                        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1.0");
                        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "vivo 1807");
                        if(mobilePlatform.toString().equalsIgnoreCase("ANDROID"))
                            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
                        else if(mobilePlatform.toString().equalsIgnoreCase("IOS"))
                            driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
                        break;
                    }
                    case "SAUCE_LABS": {
                        desiredCapabilities.setCapability("testobject_api_key", "6028BD7BC5F94414B04525AE35342055");
                        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
                        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung Galaxy S9");
                         if(mobilePlatform.toString().equalsIgnoreCase("ANDROID"))
                             driver = new AndroidDriver(new URL("https://appium.testobject.com/wd/hub"), desiredCapabilities);
                         else if(mobilePlatform.toString().equalsIgnoreCase("IOS"))
                             driver = new IOSDriver(new URL("https://appium.testobject.com/wd/hub"), desiredCapabilities);
                        break;
                    }
                    case "BROWSERSTACK_JENKINS": {
                        String username = System.getenv("BROWSERSTACK_USERNAME").split("-")[0];
                        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
                        desiredCapabilities.setCapability("app", "bs://40f24e3d0ee5930b920223435ec9d986658c4494");
                        desiredCapabilities.setCapability("os_version", "9.0");
                        desiredCapabilities.setCapability("device", "Samsung Galaxy S9 Plus");
                        desiredCapabilities.setCapability("browserstack.appium_version", "1.16.0");
                        desiredCapabilities.setCapability("browserstack.use_w3c", "true");
                        if(mobilePlatform.toString().equalsIgnoreCase("ANDROID"))
                            driver = new AndroidDriver(new URL("https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), desiredCapabilities);
                        else if(mobilePlatform.toString().equalsIgnoreCase("IOS"))
                            driver = new IOSDriver(new URL("https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), desiredCapabilities);
                        break;
                    }
                    case "BROWSERSTACK": {

                        desiredCapabilities.setCapability("app", "bs://40f24e3d0ee5930b920223435ec9d986658c4494");
                        desiredCapabilities.setCapability("os_version", "9.0");
                        desiredCapabilities.setCapability("device", "Samsung Galaxy S9 Plus");
                        desiredCapabilities.setCapability("browserstack.appium_version", "1.16.0");
                        desiredCapabilities.setCapability("browserstack.use_w3c", "true");
                        if(mobilePlatform.toString().equalsIgnoreCase("ANDROID"))
                            driver = new AndroidDriver(new URL("https://xyz@hub-cloud.browserstack.com/wd/hub"), desiredCapabilities);
                        else if(mobilePlatform.toString().equalsIgnoreCase("IOS"))
                            driver = new IOSDriver(new URL("https://xyz@hub-cloud.browserstack.com/wd/hub"), desiredCapabilities);
                        break;
                    }
                    case "ANDROID_EMULATOR": {
                        desiredCapabilities.setCapability(MobileCapabilityType.APP, "");
                        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
                        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
                        if(mobilePlatform.toString().equalsIgnoreCase("ANDROID"))
                            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
                        else if(mobilePlatform.toString().equalsIgnoreCase("IOS"))
                            driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
                        break;
                    }
                    default:
                        desiredCapabilities.setCapability(MobileCapabilityType.APP, "");
                        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
                        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
                        if(mobilePlatform.toString().equalsIgnoreCase("ANDROID"))
                            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
                        else if(mobilePlatform.toString().equalsIgnoreCase("IOS"))
                            driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
                        break;
                }

            }
            return driver;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return driver;
        }
    }


}

