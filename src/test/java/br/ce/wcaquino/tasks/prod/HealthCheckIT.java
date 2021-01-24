package br.ce.wcaquino.tasks.prod;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



public class HealthCheckIT {
	
	@Test
	public void healthCheck() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities().chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.3.3:4444/wd/hub"), cap);
		try {
			driver.navigate().to("http://192.168.3.3:9999/tasks");
			String version = driver.findElement(By.id("version")).getText();
			System.out.println(version);
			Assert.assertTrue(version.startsWith("build"));			
		} finally {
			driver.quit();			
		}
	}

}
