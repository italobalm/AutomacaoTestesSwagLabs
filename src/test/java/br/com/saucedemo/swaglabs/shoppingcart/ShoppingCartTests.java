package br.com.saucedemo.swaglabs.shoppingcart;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


@DisplayName("Testes Automatizados da Função Shopping Cart")
public class ShoppingCartTests {
    // Variáveis para o ExtentReports e ExtentSparkReporter
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;

    @BeforeAll
    public static void setup() {
        // Configurar o reporter Spark do ExtentReports
        sparkReporter = new ExtentSparkReporter("relatorioTestesCarrinhoDeCompras.html");
        sparkReporter.config().setDocumentTitle("Relatório de Testes - Carrinho de Compras");

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
    @DisplayName("Adicionar produto no carrinho pela tela de detalhes")
    public void testAdicionarProdutoNoCarrinhoPelaTelaDeDetalhes() {
        ExtentTest test = extent.createTest("Adicionar produto no carrinho pela tela de detalhes").assignAuthor("Italo").assignCategory("CarrinhoDeCompras");

        // Abrir o Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();

        try {
            // Abrir o site Swag Labs
            navegador.get("https://www.saucedemo.com/");

            // Digitar credenciais válidas
            navegador.findElement(By.id("user-name")).sendKeys("standard_user");
            navegador.findElement(By.id("password")).sendKeys("secret_sauce");

            // Clicar no botão de login
            navegador.findElement(By.id("login-button")).click();

            // Clicar em um produto
            navegador.findElement(By.className("inventory_item_img")).click();
            Thread.sleep(1000);

            //Adionar esse produto ao carrinho
            navegador.findElement(By.id("add-to-cart")).click();
            Thread.sleep(1000);
        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }

    }

    @Test
    @DisplayName("Atualizar quantidade de itens no carrinho")
    public void testAtualizarQuantidadeDeItensNoCarrinho() {
        ExtentTest test = extent.createTest("Atualizar quantidade de itens no carrinho").assignAuthor("Italo").assignCategory("CarrinhoDeCompras");

        // Configurar o WebDriver
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

            // Clicar em adicionar o primeiro produto ao carrinho
            navegador.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
            Thread.sleep(2000); // Espera para observar o processo

            // Obter o texto do ícone do carrinho (quantidade de itens)
            String contagemCarrinho = navegador.findElement(By.className("shopping_cart_container")).getText();

            // Verificar que possui 1 produto no carrinho
            if (contagemCarrinho.equals("1")) {
                test.pass("O carrinho foi atualizado corretamente com 1 item.");
            } else {
                test.fail("A contagem do carrinho não está correta. Valor esperado: 1, Valor obtido: " + contagemCarrinho);
            }

            // Adicionar o segundo produto ao carrinho
            navegador.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            Thread.sleep(2000); // Espera para observar o processo

            // Obter novamente a contagem do carrinho
            contagemCarrinho = navegador.findElement(By.className("shopping_cart_container")).getText();

            // Verificar que agora possui 2 produtos no carrinho
            if (contagemCarrinho.equals("2")) {
                test.pass("O carrinho foi atualizado corretamente com 2 itens.");
            } else {
                test.fail("A contagem do carrinho não está correta após adicionar o segundo item. Valor esperado: 2, Valor obtido: " + contagemCarrinho);
            }

        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }
    }

    @Test
    @DisplayName("Adicionar produto diretamente da tela do carrinho")
    public void testAdicionarProdutoDiretamenteDaTelaDoCarrinho() {
        ExtentTest test = extent.createTest("Adicionar produto diretamente da tela do carrinho").assignAuthor("Italo").assignCategory("CarrinhoDeCompras");

        // Configurar o WebDriver
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

            //Clicar no icone do carrinho
            navegador.findElement(By.className("shopping_cart_container")).click();

            //Clicar em adicionar um produto no carrinho
            navegador.findElement(By.id("add-to-cart")).click();

        } catch (
                Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }
    }

    @Test
    @DisplayName("Remover produto do carrinho na tela do carrinho")
    public void testRemoverProdutoDoCarrinhoNaTelaDoCarrinho() {
        ExtentTest test = extent.createTest("Remover produto do carrinho na tela do carrinho").assignAuthor("Italo").assignCategory("CarrinhoDeCompras");

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

            // Clicar em adicionar ao carrinho
            navegador.findElement(By.className("btn")).click();

            //Clicar no icone do carrinho
            navegador.findElement(By.className("shopping_cart_container")).click();

            //Remover produto do carrinho
            navegador.findElement(By.id("remove-sauce-labs-backpack")).click();
        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }
    }
    @Test
    @DisplayName("Impedir prosseguimento para checkout com carrinho vazio")
    public void testImpedirProsseguimentoParaCheckoutComCarrinhoVazio() {
        ExtentTest test = extent.createTest("Impedir prosseguimento para checkout com carrinho vazio").assignAuthor("Italo").assignCategory("CarrinhoDeCompras");

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

            //Clicar no carrinho
            navegador.findElement(By.id("shopping_cart_container")).click();

            //Clicar em checkout
            navegador.findElement(By.id("checkout")).click();

            //Verificar se é possível prosseguir
            if (navegador.findElement(By.className("checkout_info_wrapper")).isDisplayed()){
                test.fail("Usuário consegue clicar em checkout mesmo sem produtos no carrinho");} else {
            }
            test.pass("Teste concluído com sucesso!");
        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }

    }
    @Test
    @DisplayName("Medir tempo de carregamento da página de produtos")
    public void medirTempoDeCarregamento() {
        ExtentTest test = extent.createTest("Medir Tempo de Carregamento").assignAuthor("Bernardo").assignCategory("CarrinhoDeCompras");
        WebDriver driver = new ChromeDriver();

        try {
            // Passo 1: Acesse a página de login
            driver.get("https://www.saucedemo.com/");

            // Passo 2: Preencha o login
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            // Passo 3: Aguarde até a página de itens à venda ser carregada
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));

            // Passo 4: Medir o tempo de carregamento da página de itens à venda
            long startTime = System.currentTimeMillis();
            driver.get("https://www.saucedemo.com/inventory.html");
            long loadTime = System.currentTimeMillis() - startTime;

            System.out.println("Tempo de carregamento dos itens: " + loadTime + "ms");

            // Passo 5: Medir o tempo de carregamento das imagens
            long imageStartTime = System.currentTimeMillis();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img.inventory_item_img")));
            long imageLoadTime = System.currentTimeMillis() - imageStartTime;

            System.out.println("Tempo de carregamento das imagens: " + imageLoadTime + "ms");

        } finally {
            // Passo 6: Fechar o navegador
            driver.quit();
        }
    }

    @Test
    @DisplayName("Validar exibição dos dados da compra")
    public void validarDadosDaCompra() throws InterruptedException {

        // Criar um teste com tag personalizada
        ExtentTest test = extent.createTest("Validação dos Dados da Compra")
                .assignAuthor("Bernardo").assignCategory("CarrinhoDeCompras");

        // Inicializar o WebDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Passo 1: Acesse a página de login
            driver.get("https://www.saucedemo.com/");
            test.pass("Página de login acessada com sucesso.");

            // Passo 2: Realizar login
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            test.pass("Login realizado com sucesso.");

            // Passo 3: Adicionar produto ao carrinho
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            test.pass("Produto adicionado ao carrinho.");

            // Passo 4: Ir para o carrinho
            wait.until(ExpectedConditions.elementToBeClickable(By.id("shopping_cart_container")));
            driver.findElement(By.id("shopping_cart_container")).click();
            test.pass("Carrinho acessado com sucesso.");

            // Passo 5: Realizar checkout
            wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
            driver.findElement(By.id("checkout")).click();
            test.pass("Página de checkout acessada com sucesso.");

            // Passo 6: Preencher os campos de checkout
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
            driver.findElement(By.id("first-name")).sendKeys("Bernardo");
            driver.findElement(By.id("last-name")).sendKeys("Simões");
            driver.findElement(By.id("postal-code")).sendKeys("50820000");
            test.pass("Campos preenchidos corretamente.");

            // Passo 7: Continuar para exibição dos dados da compra
            wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
            driver.findElement(By.id("continue")).click();
            test.pass("Ação de continuar realizada com sucesso.");

            // Passo 8: Validar exibição dos dados da compra
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("summary_info")));
            String resumoCompra = driver.findElement(By.className("summary_info")).getText();
            if (resumoCompra.contains("Payment Information") && resumoCompra.contains("Shipping Information")) {
                test.pass("Dados da compra exibidos corretamente para confirmação.");
            } else {
                test.fail("Erro: Dados da compra não exibidos corretamente.");
            }

        } catch (Exception e) {
            test.fail("Erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            driver.quit();
        }
    }
}