package avenga.poc.testcontainer.selenium;

//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.testcontainers.containers.BrowserWebDriverContainer;
//import org.testcontainers.containers.VncRecordingContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.io.File;
//import java.util.concurrent.TimeUnit;
//
//import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

//@Testcontainers
public class SeleniumTestExample {

//
//    @Container
//    public BrowserWebDriverContainer<?> chrome = new BrowserWebDriverContainer<>()
//            .withCapabilities(new FirefoxOptions())
//            .withRecordingMode(RECORD_ALL, new File("c:\\dev\\"));
//
//    @Test
//    public void testme() {
//        doSimpleExplore(chrome);
//    }
//
//    protected static void doSimpleExplore(BrowserWebDriverContainer<?> rule) {
//        RemoteWebDriver driver = setupDriverFromRule(rule);
//        System.out.println("Selenium remote URL is: " + rule.getSeleniumAddress());
//        System.out.println("VNC URL is: " + rule.getVncAddress());
//
//        driver.get("https://www.google.com");
//        System.out.println(driver.getPageSource());
//        WebElement title = driver.findElement(By.tagName("h1"));
//        System.out.println(title);
//    }
//
//    private static RemoteWebDriver setupDriverFromRule(BrowserWebDriverContainer<?> rule) {
//        RemoteWebDriver driver = rule.getWebDriver();
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        return driver;
//    }


}
