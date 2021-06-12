package testing;

import java.io.File;
//import java.io.IOException;
import javax.imageio.ImageIO;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Testing {

	private static final int WIDTH = 1204;
	private static final int HEIGHT = 768;

	public static void main(String... strings) {

		try { 
			System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			
			
			driver.get("https://en.wikipedia.org/wiki/java_(programming_language)");
			driver.manage().window().maximize();

			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);

			ImageIO.write(screenshot.getImage(), "jpg", new File("C:\\Users\\HP\\eclipse-workspace\\screenshot\\APISS\\test.jpg"));
			
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
