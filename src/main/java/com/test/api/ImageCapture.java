package com.test.api;

import java.io.File;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.bean.ScreenshotRequest;
import com.test.bean.ScreenshotResponse;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

@Controller
public class ImageCapture {

	@RequestMapping(value = "/getWebImage")
	public ResponseEntity<ScreenshotResponse> initiateAPICall(@RequestBody ScreenshotRequest apiRequest,
			HttpServletRequest request) {

		ScreenshotResponse apiResponse = new ScreenshotResponse();

		StringBuilder sbLogger = new StringBuilder();

		try {
			synchronized (this) {

				String sUUID = UUID.randomUUID().toString();

				File fImage = new File(sUUID + ".jpg");

				System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
				WebDriver driver = new ChromeDriver();

				driver.get(apiRequest.getUrl());
				driver.manage().window().maximize();

				Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
						.takeScreenshot(driver);

				ImageIO.write(screenshot.getImage(), "jpg", fImage);

				driver.close();

				byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(fImage));

				apiResponse.setImage(new String(encoded));
				apiResponse.setURL(apiRequest.getUrl());

				fImage.delete();

			}

		} catch (Exception e) {

			System.err.println(sbLogger + "Exception Occured in initiateAPICall() -> [" + e + "]");
			e.printStackTrace();

		} finally {

		}

		return new ResponseEntity<ScreenshotResponse>(apiResponse, HttpStatus.OK);

	}

}
