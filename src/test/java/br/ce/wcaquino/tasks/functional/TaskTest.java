package br.ce.wcaquino.tasks.functional;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TaskTest {
	
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		//System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = new DesiredCapabilities().chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.3.3:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.3.3:8001/tasks/");
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");
			
			//clicar em Salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar Mensagem de Sucesso
			
			String mensagem = driver.findElement(By.id("message")).getText();
			
			assertEquals(mensagem, "Success!");
		} finally {
			//Fechar o browser
			driver.quit();			
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");
			
			//clicar em Salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar Mensagem de Sucesso
			
			String mensagem = driver.findElement(By.id("message")).getText();
			
			assertEquals(mensagem, "Fill the task description");
		} finally {
			//Fechar o browser
			driver.quit();			
		}
		
	}
	
	@Test
	public void deveSalvarTarefaSemData() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//clicar em Salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar Mensagem de Sucesso
			
			String mensagem = driver.findElement(By.id("message")).getText();
			
			assertEquals(mensagem, "Fill the due date");
		} finally {
			//Fechar o browser
			driver.quit();			
		}
		
	}
	
	
	@Test
	public void deveSalvarTarefaComDataPassada() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
			
			//clicar em Salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar Mensagem de Sucesso
			
			String mensagem = driver.findElement(By.id("message")).getText();
			
			assertEquals(mensagem, "Due date must not be in past");
		} finally {
			//Fechar o browser
			driver.quit();			
		}
		
	}
	
	@Test
	public void deveRemoverTarefaComSucesso() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			//inserir tarefa
			
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");
			driver.findElement(By.id("saveButton")).click();
			String mensagem = driver.findElement(By.id("message")).getText();
			assertEquals(mensagem, "Success!");
			
			
			driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm']")).click();
			String mensagemRemove = driver.findElement(By.id("message")).getText();
			assertEquals(mensagemRemove, "Success!");
		} finally {
			//Fechar o browser
			driver.quit();			
		}
		
	}
}
