package br.ce.wcaquino.tasks.functional;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TaskTest {
	
	
	public WebDriver acessarAplicacao() {
		
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		return driver;
		
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		
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
	public void naoDeveSalvarTarefaSemDescricao() {
		
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
	public void deveSalvarTarefaSemData() {
		
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
	public void deveSalvarTarefaComDataPassada() {
		
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
}
