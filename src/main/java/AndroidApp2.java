import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import java.net.URL;
import java.util.HashMap;
public class AndroidApp2 {

    String userName = System.getenv("LT_USERNAME") == null ?
            "username" : System.getenv("LT_USERNAME"); //Add username here
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ?
            "accessKey" : System.getenv("LT_ACCESS_KEY"); //Add accessKey here

    public String gridURL = "@mobile-hub.lambdatest.com/wd/hub";

    AppiumDriver driver;

    @Test
    @org.testng.annotations.Parameters(value = {"device", "version", "platform"})
    public void AndroidApp1(String device, String version, String platform) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("build","Java TestNG Android");
            capabilities.setCapability("name",platform+" "+device+" "+version);
            capabilities.setCapability("deviceName", device);
            capabilities.setCapability("device", device);
            capabilities.setCapability("platformVersion",version);
            capabilities.setCapability("platformName", platform);
            capabilities.setCapability("isRealMobile", true);
            //AppURL (Create from Wikipedia.apk sample in project)
            capabilities.setCapability("app", "lt://APP1016020161708085194235484"); //Enter your app url
//            capabilities.setCapability("appID", "lt://proverbial-android"); //Enter your app url

            capabilities.setCapability("deviceOrientation", "PORTRAIT");
            capabilities.setCapability("console", true);
            capabilities.setCapability("network", true);
            HashMap<String, Object> networkConfig = new HashMap<>();
            networkConfig.put("bypassWebsocket", true);
            capabilities.setCapability("networkConfig",networkConfig);

            capabilities.setCapability("visual", true);
            capabilities.setCapability("devicelog", true);
            //capabilities.setCapability("geoLocation", "HK");

            String hub = "https://" + userName + ":" + accessKey + gridURL;
            driver = new AppiumDriver(new URL(hub), capabilities);



            Thread.sleep(10000);

            // Find an element by XPath and send text to it
            By inputBy = By.xpath("//*[@resource-id='sinet.startup.inDriver:id/ui_phone_layout_edit_text']");
            MobileElement inputElement = (MobileElement) driver.findElement(inputBy);

            // Clear existing text in the input field (optional, depending on your use case)
//            inputElement.clear();

            // Send text to the input field
            String inputText = "1234567890";
            inputElement.sendKeys(inputText);

            // Click on the next button
            By nextButtonBy = By.xpath("//*[@resource-id='sinet.startup.inDriver:id/authorization_phone_btn_next']");
            driver.findElement(nextButtonBy).click();

            // Wait for a moment to see the changes (not recommended for actual tests)
            Thread.sleep(20000);


            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
            try{
                driver.quit();
            }catch(Exception e1){
                e.printStackTrace();
            }
        }


    }

}
