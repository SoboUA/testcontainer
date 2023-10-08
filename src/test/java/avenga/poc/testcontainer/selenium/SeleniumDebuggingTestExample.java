package avenga.poc.testcontainer.selenium;

//import com.github.terma.javaniotcpproxy.StaticTcpProxyConfig;
//import com.github.terma.javaniotcpproxy.TcpProxy;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriverException;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.testcontainers.containers.BrowserWebDriverContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.util.concurrent.ThreadLocalRandom;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Collectors;

//@Testcontainers
public class SeleniumDebuggingTestExample {


//    @Container
//   static BrowserWebDriverContainer<?> container = new BrowserWebDriverContainer<>();

//    @Test
//    public void runSelenium() {
//
////container.withCapabilities(new ChromeOptions());
//
//
//        container.start();
//
//        var config = new StaticTcpProxyConfig(
//                5900,
//                container.getContainerIpAddress(),
//                container.getMappedPort(5900)
//        );
//        config.setWorkerCount(1);
//        var tcpProxy = new TcpProxy(config);
//        tcpProxy.start();
//        System.out.println("Running VNC proxy on vnc://localhost:5900, password: 'secret'");
//
//        RemoteWebDriver driver = container.getWebDriver();
//        driver.manage().window().maximize();
//        driver.get("https://testcontainers.org");
//
//        while (true) {
//            try {
//                var links = driver.findElements(By.tagName("a")).stream()
//                        .filter(WebElement::isDisplayed)
//                        .filter(it -> {
//                            var href = it.getAttribute("href");
//                            return href.startsWith("https://www.testcontainers.org/");
//                        })
//                        .collect(Collectors.toList());
//
//                var element = links.get(ThreadLocalRandom.current().nextInt(links.size()));
//
//                System.out.println("Clicking on " + element.getAttribute("href"));
//                element.click();
//
//                TimeUnit.SECONDS.sleep(2);
//            } catch (WebDriverException e) {
//                // ¯\_(ツ)_/¯
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }
}
