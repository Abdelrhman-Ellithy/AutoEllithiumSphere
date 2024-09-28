package Ellithium.Internal;

import Ellithium.Utilities.Colors;
import Ellithium.Utilities.PropertyHelper;
import Ellithium.Utilities.TestDataGenerator;
import Ellithium.Utilities.logsUtils;
import com.google.common.io.Files;
import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;
import io.restassured.RestAssured;

public class GeneralHandler implements TestLifecycleListener {
    private static Boolean BDDMode,flagReaded=false;
    public static File testFailed(WebDriver localDriver, String browserName, String testName)  {
        try {
            TakesScreenshot camera = (TakesScreenshot) localDriver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            String name = browserName.toUpperCase() + "-" + testName + "-" + TestDataGenerator.getTimeStamp();
            File screenShotFile = new File("Test-Output/ScreenShots/Failed/" + name + ".png");
            Files.move(screenshot, screenShotFile);
            return screenShotFile;
        } catch (IOException e) {
            logsUtils.logException(e);
            return null;
        }
    }
    public static void attachScreenshotToReport(File screenshot,String name, String browserName, String testName){
        try (FileInputStream fis = new FileInputStream(screenshot)) {
            Allure.description(browserName.toUpperCase() + "-" + testName + " FAILED");
            Allure.addAttachment(name, "image/png", fis, ".png");
        }catch (IOException e) {
            logsUtils.logException(e);
        }
    }
    public static void attachAndOpen(){
        String logFilePath = PropertyHelper.getDataFromProperties(
                "src" + File.separator + "main" + File.separator + "resources" + File.separator +
                        "properties" + File.separator + "default" + File.separator + "log4j2",
                "property.basePath"
        );
        logFilePath = logFilePath.concat(File.separator).concat(
                PropertyHelper.getDataFromProperties(
                        "src" + File.separator + "main" + File.separator + "resources" + File.separator +
                                "properties" + File.separator + "default" + File.separator + "log4j2",
                        "property.fileName"
                )
        );
        File logFile = new File(logFilePath);
        if (!logFile.exists()) {
            logsUtils.error("Log file not found at: " + logFilePath);
            Allure.step("Log file not found: " + logFilePath, Status.FAILED);
            return;
        }
        try (FileInputStream fis = new FileInputStream(logFile)) {
            String uuid = UUID.randomUUID().toString();
            TestResult result = new TestResult()
                    .setUuid(uuid)
                    .setName("Full Execution Log")
                    .setDescription("This is the execution log for the entire test run.")
                    .setStatus(Status.PASSED);
            Allure.getLifecycle().scheduleTestCase(result);
            Allure.getLifecycle().startTestCase(uuid);
            // Attach the log file
            Allure.addAttachment("Execution Log File", "text/plain", fis, ".log");
            Allure.getLifecycle().stopTestCase(uuid);
            Allure.getLifecycle().writeTestCase(uuid);  // Write the test case in the Allure report
            logsUtils.info("Log file successfully attached to the Allure report.");
        } catch (IOException e) {
            logsUtils.logException(e);
            Allure.step("Failed to attach log file: " + e.getMessage(), Status.FAILED);
        }
        AllureHelper.allureOpen();
    }
    public static boolean getBDDMode(){
        if(flagReaded.equals(false)){
            String configFilePath ="src" + File.separator + "main" + File.separator + "resources" + File.separator +
                    "properties" + File.separator + "default" + File.separator + "config";
            String mode=PropertyHelper.getDataFromProperties(configFilePath,"runMode");
            BDDMode=mode.equalsIgnoreCase("BDD");
            flagReaded=true;
        }
        return BDDMode;
    }
    public static String getLatestVersion(){
        return RestAssured.given().
                baseUri("https://api.github.com/").and().basePath("repos/Abdelrhman-Ellithy/Ellithium/releases/")
                .when().get("latest")
                .thenReturn().body().jsonPath().getString("name");
    }
    public static void solveVersion(){
        String latestVersion=getLatestVersion();
        String configFilePath ="src" + File.separator + "main" + File.separator + "resources" + File.separator +
                "properties" + File.separator + "default" + File.separator + "config";
        String currentVersion=PropertyHelper.getDataFromProperties(configFilePath,"EllithiumVersion");
        if(latestVersion.equalsIgnoreCase(currentVersion)){
            logsUtils.info(Colors.GREEN+"You Are Using the Latest version of Ellithium Version: "+latestVersion);
        }
        else {
            logsUtils.info(Colors.RED+"You Are Using Old Version of Ellithium Version: "+currentVersion +
                    " You Need To update to the latest Version: "+latestVersion);
        }
    }
}
