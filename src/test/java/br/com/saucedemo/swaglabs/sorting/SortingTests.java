package br.com.saucedemo.swaglabs.sorting;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

@DisplayName("Testes Automatizados da Função Sorting")
public class SortingTests {
    // Variáveis para o ExtentReports e ExtentSparkReporter
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;

    @BeforeAll
    public static void setup() {
        // Configurar o reporter Spark do ExtentReports
        sparkReporter = new ExtentSparkReporter("relatorioTestesDeOrdenacao.html");
        sparkReporter.config().setDocumentTitle("Relatório de Testes - Ordenação");

        // Configurar o ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @AfterAll
    public static void tearDown() {
        // Gerar o relatório HTML após todos os testes
        extent.flush();
    }
    @Test
    @DisplayName("Filtrar listagem de produtos")
    public void testFiltrarListagemDeProdutos() {
        ExtentTest test = extent.createTest("Filtrar listagem de produtos").assignAuthor("Italo").assignCategory("Ordenação");

        // Abrir o Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();

        try {
            // Abrir o site Swag Labs
            navegador.get("https://www.saucedemo.com/");

            // Digitar credenciais
            navegador.findElement(By.id("user-name")).sendKeys("standard_user");
            navegador.findElement(By.id("password")).sendKeys("secret_sauce");

            // Clicar em entrar
            navegador.findElement(By.id("login-button")).click();

            //Clicar no botão de filtragem
            navegador.findElement(By.className("product_sort_container")).click();

            //Selecionar um filtro
            Select select = new Select(navegador.findElement(By.className("product_sort_container")));
            select.selectByValue("lohi");

        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }
    }

    @Test
    @DisplayName("Filtrar produtos por preço mais baixo")
    public void testFiltrarProdutosPorPrecoMaisBaixo() {
        ExtentTest test = extent.createTest("Filtrar produtos por preço mais baixo").assignAuthor("Italo").assignCategory("Ordenação");

        // Abrir o Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();

        try {
            // Abrir o site Swag Labs
            navegador.get("https://www.saucedemo.com/");

            // Digitar credenciais
            navegador.findElement(By.id("user-name")).sendKeys("standard_user");
            navegador.findElement(By.id("password")).sendKeys("secret_sauce");

            // Clicar em entrar
            navegador.findElement(By.id("login-button")).click();

            //Clicar no botão de filtragem
            navegador.findElement(By.className("product_sort_container")).click();


            //Selecionar um filtro
            Select select = new Select(navegador.findElement(By.className("product_sort_container")));
            select.selectByValue("lohi");



        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }
    }

    @Test
    @DisplayName("Filtrar produtos por nome crescente")
    public void testFiltrarProdutosPorNomeCrescente() {
        ExtentTest test = extent.createTest("Filtrar produtos por nome A-Z").assignAuthor("Italo").assignCategory("Ordenação");

        // Abrir o Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();

        try {
            // Abrir o site Swag Labs
            navegador.get("https://www.saucedemo.com/");

            // Digitar credenciais
            navegador.findElement(By.id("user-name")).sendKeys("standard_user");
            navegador.findElement(By.id("password")).sendKeys("secret_sauce");

            // Clicar em entrar
            navegador.findElement(By.id("login-button")).click();

            //Clicar no botão de filtragem
            navegador.findElement(By.className("product_sort_container")).click();


            //Selecionar um filtro
            Select select = new Select(navegador.findElement(By.className("product_sort_container")));
            select.selectByValue("az");



        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }
    }

    @Test
    @DisplayName("Filtrar produtos por preço mais alto")
    public void testFiltrarProdutosPorPrecoMaisAlto() {
        ExtentTest test = extent.createTest("Filtrar produtos por preço mais alto").assignAuthor("Italo").assignCategory("Ordenação");

        // Abrir o Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();

        try {
            // Abrir o site Swag Labs
            navegador.get("https://www.saucedemo.com/");

            // Digitar credenciais
            navegador.findElement(By.id("user-name")).sendKeys("standard_user");
            navegador.findElement(By.id("password")).sendKeys("secret_sauce");

            // Clicar em entrar
            navegador.findElement(By.id("login-button")).click();

            //Clicar no botão de filtragem
            navegador.findElement(By.className("product_sort_container")).click();

            //Selecionar um filtro
            Select select = new Select(navegador.findElement(By.className("product_sort_container")));
            select.selectByValue("hilo");


        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }
    }

    @Test
    @DisplayName("Filtrar produtos por nome descrecente")
    public void testFiltrarProdutosPorNomeDecrecente() {
        ExtentTest test = extent.createTest("Filtrar produtos por nome Z-A").assignAuthor("Italo").assignCategory("Ordenação");

        // Abrir o Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();

        try {
            // Abrir o site Swag Labs
            navegador.get("https://www.saucedemo.com/");

            // Digitar credenciais
            navegador.findElement(By.id("user-name")).sendKeys("standard_user");
            navegador.findElement(By.id("password")).sendKeys("secret_sauce");

            // Clicar em entrar
            navegador.findElement(By.id("login-button")).click();

            //Clicar no botão de filtragem
            navegador.findElement(By.className("product_sort_container")).click();

            //Selecionar um filtro
            Select select = new Select(navegador.findElement(By.className("product_sort_container")));
            select.selectByValue("za");


        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }
    }

}
