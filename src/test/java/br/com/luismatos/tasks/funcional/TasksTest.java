package br.com.luismatos.tasks.funcional;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() {
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driver/chromedriver.exe");
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//driver//chromedriver");
		WebDriver driver = new ChromeDriver();
//		driver.navigate().to("http://localhost:8001/tasks");
		driver.navigate().to("http://172.28.0.1:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();

		try {
			driver.findElement(By.id("addTodo")).click();

			Random random = new Random();

			driver.findElement(By.id("task")).sendKeys("Fazer_" + random.nextInt(999999));

			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

			driver.findElement(By.id("saveButton")).click();

			String message = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Success!", message);

		} finally {
			driver.close();
			driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();

		try {
			driver.findElement(By.id("addTodo")).click();

			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

			driver.findElement(By.id("saveButton")).click();

			String message = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Fill the task description", message);

		} finally {
			driver.close();
			driver.quit();
		}

	}

	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAplicacao();

		try {
			driver.findElement(By.id("addTodo")).click();

			Random random = new Random();

			driver.findElement(By.id("task")).sendKeys("Fazer_" + random.nextInt(999999));

			driver.findElement(By.id("saveButton")).click();

			String message = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Fill the due date", message);

		} finally {
			driver.close();
			driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAplicacao();

		try {
			driver.findElement(By.id("addTodo")).click();

			Random random = new Random();

			driver.findElement(By.id("task")).sendKeys("Fazer_" + random.nextInt(999999));

			driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");

			driver.findElement(By.id("saveButton")).click();

			String message = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Due date must not be in past", message);

		} finally {
			driver.close();
			driver.quit();
		}
	}

}
