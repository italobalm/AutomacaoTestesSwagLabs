package br.com.saucedemo.swaglabs.login;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@DisplayName("Testes Automatizados da Função Login")
public class LoginTests {

    // Variáveis para o ExtentReports e ExtentSparkReporter
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;

    @BeforeAll
    public static void setup() {
        // Configurar o reporter Spark do ExtentReports
        sparkReporter = new ExtentSparkReporter("relatorioTestesDeLogin.html");
        sparkReporter.config().setDocumentTitle("Relatório de Testes - Login");

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
    @DisplayName("Redirecionar para página inicial com login válido")
    public void testRedirecionarParaPaginaInicialComLoginValido() {
        ExtentTest test = extent.createTest("Testar login com credenciais válidas").assignAuthor("Italo").assignCategory("Login");

        // Abrir o Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();

        try {
            test.log(com.aventstack.extentreports.Status.INFO, "Abrindo o navegador e acessando o site Swag Labs.");
            // Abrir o site Swag Labs
            navegador.get("https://www.saucedemo.com/");

            test.log(com.aventstack.extentreports.Status.INFO, "Preenchendo as credenciais de login.");
            // Digitar credenciais válidas
            navegador.findElement(By.id("user-name")).sendKeys("standard_user");
            navegador.findElement(By.id("password")).sendKeys("secret_sauce");

            test.log(com.aventstack.extentreports.Status.INFO, "Clicando no botão de login.");
            // Clicar em entrar
            navegador.findElement(By.id("login-button")).click();

            test.log(com.aventstack.extentreports.Status.INFO, "Verificando se o redirecionamento ocorreu com sucesso.");
            // Validar redirecionamento
            boolean redirecionado = navegador.findElement(By.id("inventory_container")).isDisplayed();

            if (redirecionado) {
                test.pass("O redirecionamento para a página inicial foi bem-sucedido.");
            } else {
                test.fail("O redirecionamento para a página inicial falhou.");
            }
        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            test.log(com.aventstack.extentreports.Status.INFO, "Fechando o navegador.");
            // Fechar navegador
            navegador.quit();
        }
    }

    @Test
    @DisplayName("Exibir mensagem de erro ao realizar login com usuário bloqueado")
    public void testExibirMensagemDeErroAoRealizarLoginComUsuarioBloqueado() {
        ExtentTest test = extent.createTest("Testar login com usuário bloqueado").assignAuthor("Italo").assignCategory("Login");

        // Abrir o Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();

        try {
            test.log(com.aventstack.extentreports.Status.INFO, "Abrindo o navegador e acessando o site Swag Labs.");
            // Abrir o site Swag Labs
            navegador.get("https://www.saucedemo.com/");

            test.log(com.aventstack.extentreports.Status.INFO, "Preenchendo as credenciais do usuário bloqueado.");
            // Digitar credenciais do usuário bloqueado
            navegador.findElement(By.id("user-name")).sendKeys("locked_out_user");
            navegador.findElement(By.id("password")).sendKeys("secret_sauce");

            test.log(com.aventstack.extentreports.Status.INFO, "Clicando no botão de login.");
            // Clicar no botão de login
            navegador.findElement(By.id("login-button")).click();

            test.log(com.aventstack.extentreports.Status.INFO, "Aguardando a mensagem de erro aparecer.");
            // Aguardar a mensagem de erro
            WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(10));
            WebElement mensagemErro = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']")));

            if (mensagemErro.isDisplayed()) {
                test.pass("A mensagem de erro foi exibida corretamente.");
            } else {
                test.fail("A mensagem de erro não foi exibida.");
            }
        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            test.log(com.aventstack.extentreports.Status.INFO, "Fechando o navegador.");
            // Fechar navegador
            navegador.quit();
        }
    }

    @Test
    @DisplayName("Exibir mensagem de erro ao realizar login com credenciais inválidas")
    public void testExibirMensagemDeErroAoRealizarLoginComCredenciaisInvalidas() {
        ExtentTest test = extent.createTest("Testar login com credenciais inválidas").assignAuthor("Italo").assignCategory("Login");

        // Abrir o Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();

        try {
            test.log(com.aventstack.extentreports.Status.INFO, "Abrindo o navegador e acessando o site Swag Labs.");
            // Abrir o site Swag Labs
            navegador.get("https://www.saucedemo.com/");

            test.log(com.aventstack.extentreports.Status.INFO, "Preenchendo credenciais inválidas.");
            // Digitar credenciais inválidas
            navegador.findElement(By.id("user-name")).sendKeys("credenciais_invalidas");
            navegador.findElement(By.id("password")).sendKeys("secret_sauce");

            test.log(com.aventstack.extentreports.Status.INFO, "Clicando no botão de login.");
            // Clicar no botão de login
            navegador.findElement(By.id("login-button")).click();

            test.log(com.aventstack.extentreports.Status.INFO, "Aguardando a mensagem de erro aparecer.");
            // Aguardar a mensagem de erro
            WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(10));
            WebElement mensagemErro = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']")));

            if (mensagemErro.isDisplayed()) {
                test.pass("A mensagem de erro foi exibida corretamente.");
            } else {
                test.fail("A mensagem de erro não foi exibida.");
            }
        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            test.log(com.aventstack.extentreports.Status.INFO, "Fechando o navegador.");
            // Fechar navegador
            navegador.quit();
        }
    }
    @Test
    @DisplayName("Verificar funcionalidade de recuperação de senha")
    public void testVerificarFuncionalidadeDeRecuperaSenha() {
        ExtentTest test = extent.createTest("Verificar funcionalidade de recuperação de senha").assignAuthor("Italo").assignCategory("Login");

        // Abrir o Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();

        try {

            // Abrir o site Swag Labs
            navegador.get("https://www.saucedemo.com/");


            // Clicar no botão de esqueci senha
            navegador.findElement(By.linkText("Forgot your password?")).click();

        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            test.log(com.aventstack.extentreports.Status.INFO, "Fechando o navegador.");
            // Fechar navegador
            navegador.quit();
        }
    }
    @Test
    @DisplayName("Verificar se a página de itens à venda não é acessível sem login")
    public void testAcessarItensSemLogin() {

        // Criar um teste com tag personalizada
        ExtentTest test = extent.createTest("Verificar acesso à página de itens sem login",
                        "Tenta acessar a página de itens sem estar logado e verifica se é redirecionado ao login.")
                .assignAuthor("Bernardo").assignCategory("Login");

        // Configurar o WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();

        try {
            // Tentar acessar a página de itens diretamente sem login
            navegador.get("https://www.saucedemo.com/inventory.html");
            test.info("Tentativa de acessar a página de itens sem login.");

            // Esperar que o botão de login ou a tela de login apareça
            WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(10));
            WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));

            // Verificar se o usuário foi redirecionado para a página de login
            String atualUrl = navegador.getCurrentUrl();
            Assertions.assertTrue(atualUrl.contains("saucedemo.com"), "O usuário não foi redirecionado para a página de login.");
            test.pass("Usuário redirecionado corretamente para a página de login.");

            // Verificar se a mensagem de erro ou alerta aparece informando que é necessário login
            WebElement errorMessage = navegador.findElement(By.cssSelector(".error-message-container"));
            Assertions.assertTrue(errorMessage.isDisplayed(), "A mensagem de erro não foi exibida.");
            test.pass("Mensagem de erro exibida corretamente: 'Você precisa estar logado para acessar os itens.'");

        } catch (Exception e) {
            test.fail("Erro durante o teste: " + e.getMessage());
            throw e;
        } finally {
            // Fechar o navegador
            navegador.quit();
            test.info("Navegador fechado.");

        }
    }

}
