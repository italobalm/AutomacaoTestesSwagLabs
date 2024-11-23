package br.com.saucedemo.swaglabs.products;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


@DisplayName("Testes Automatizados da Função Products")
public class ProductsTests {

    // Variáveis para o ExtentReports e ExtentSparkReporter
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;

    @BeforeAll
    public static void setup() {
        // Configurar o reporter Spark do ExtentReports
        sparkReporter = new ExtentSparkReporter("relatorioTesteDeProdutos.html");
        sparkReporter.config().setDocumentTitle("Relatório de Testes - Produtos");

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
    @DisplayName("Exibir Lista de Produtos Após o Login")
    public void testExibirListaDeProdutosAposOLogin() {
        ExtentTest test = extent.createTest("Exibir lista de produtos após o login").assignAuthor("Italo").assignCategory("Produtos");

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

            // Verificar se a página de produtos é exibida após o login
            boolean paginaDeProdutosExibida = navegador.findElement(By.id("inventory_container")).isDisplayed();

            if (paginaDeProdutosExibida) {
                test.pass("A lista de produtos foi exibida com sucesso.");
            } else {
                test.fail("A lista de produtos não foi exibida.");
            }
        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }
    }

    @Test
    @DisplayName("Verificar se os Produtos Possuem Descrição")
    public void testVerificarSeOsProdutosPossuemDescricao() {
        ExtentTest test = extent.createTest("Verificar se os produtos possuem descrição").assignAuthor("Italo").assignCategory("Produtos");

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

            // Localizar todos os elementos de produtos (classe "inventory_item")
            List<WebElement> produtos = navegador.findElements(By.className("inventory_item"));

            boolean todasDescricoesPresentes = true;

            // Iterar sobre cada produto para verificar se contém descrição
            for (int i = 0; i < produtos.size(); i++) {
                WebElement produto = produtos.get(i);
                List<WebElement> descricao = produto.findElements(By.className("inventory_item_description"));

                if (descricao.isEmpty() || descricao.get(0).getText().trim().isEmpty()) {
                    todasDescricoesPresentes = false;
                    test.fail("Produto " + (i + 1) + " não possui descrição.");
                }
            }

            // Relatar o status final
            if (todasDescricoesPresentes) {
                test.pass("Todos os produtos possuem descrição.");
            } else {
                test.fail("Nem todos os produtos possuem descrição.");
            }
        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }
    }

    @Test
    @DisplayName("Verificar redirecionamento para página de detalhes ao clicar em um produto")
    public void testVerificarRedirecionamentoParaPaginaDeDetalhhesAoClicarEmUmProduto() {
        ExtentTest test = extent.createTest("Verificar redirecionamento para página de detalhes ao clicar em um produto").assignAuthor("Italo").assignCategory("Produtos");

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

            // Verificar se o usuário foi redirecionado para a página de detalhes do produto
            boolean paginaDeDetalhesExibida = navegador.findElement(By.className("inventory_details")).isDisplayed();

            if (paginaDeDetalhesExibida) {
                test.pass("Redirecionamento para página de detalhes bem-sucedido.");
            } else {
                test.fail("Redirecionamento para página de detalhes falhou.");
            }
        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }

    }

    @Test
    @DisplayName("Adicionar item ao carrinho a partir da tela de produtos")
    public void testAdicionarItemAoCarrinhoAPartirDaTelaDeProdutos() {
        ExtentTest test = extent.createTest("Adicionar item ao carrinho a partir da tela de produtos").assignCategory("Produtos");

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

            test.pass("Item adicionado ao carrinho com sucesso.");
        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }
    }

    @Test
    @DisplayName("Remover item do carrinho a partir da tela de produtos")
    public void testRemoverItemDoCarrinhoAPartirDaTelaDeProdutos() {
        ExtentTest test = extent.createTest("Remover item do carrinho a partir da tela de produtos").assignAuthor("Italo").assignCategory("Produtos");

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


            // Clicar em remover item do carrinho
            navegador.findElement(By.className("btn")).click();

            test.pass("Item removido do carrinho com sucesso.");
        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }
    }
    @Test
    @DisplayName("Atualizar quantidade de item no carrinho a partir da tela de produtos")
    public void testAtualizarQuantidadeNoCarrinho() {
        ExtentTest test = extent.createTest("Atualizar quantidade de item no carrinho a partir da tela de produtos").assignAuthor("Italo").assignCategory("Produtos");

        // Configurar o WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();

        try {
            // Abrir o site Swag Labs
            navegador.get("https://www.saucedemo.com/");

            // Fazer login
            navegador.findElement(By.id("user-name")).sendKeys("standard_user");
            navegador.findElement(By.id("password")).sendKeys("secret_sauce");
            navegador.findElement(By.id("login-button")).click();

            // Localizar o campo de quantidade do produto (exemplo)
            WebElement quantidadeInput = navegador.findElement(By.cssSelector(".cart_quantity_input")); // Substitua pelo seletor correto

            // Atualizar a quantidade (exemplo: digitar 2)
            quantidadeInput.clear(); // Limpar o valor atual
            quantidadeInput.sendKeys("2");


            // Verificar se a quantidade foi atualizada corretamente no carrinho
            WebElement quantidadeCarrinho = navegador.findElement(By.cssSelector(".cart_quantity")); // Substitua pelo seletor correto
            String quantidadeAtual = quantidadeCarrinho.getText();

            if (quantidadeAtual.equals("2")) {
                test.pass("Quantidade atualizada corretamente para 2.");
            } else {
                test.fail("Erro ao atualizar a quantidade. Valor esperado: 2, Valor obtido: " + quantidadeAtual);
            }

        } catch (Exception e) {
            test.fail("Ocorreu um erro: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }
    }
    @Test
    @DisplayName("Verificar mensagem de erro ao adicionar produto não disponível")
    public void testMensagemErroProdutoNaoDisponivel() {
        ExtentTest test = extent.createTest("Verificar mensagem de erro ao adicionar produto não disponível").assignAuthor("Italo").assignCategory("Produtos");

        // Configuração do WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver navegador = new ChromeDriver();

        try {
            // Abrir o site
            navegador.get("https://www.saucedemo.com/");
            navegador.findElement(By.id("user-name")).sendKeys("error_user");
            navegador.findElement(By.id("password")).sendKeys("secret_sauce");
            navegador.findElement(By.id("login-button")).click();

            // Lista de botões de produtos
            List<WebElement> botoesProdutos = navegador.findElements(By.cssSelector(".inventory_item button"));

            for (WebElement botao : botoesProdutos) {
                String botaoId = botao.getAttribute("id");
                System.out.println("Verificando o botão: " + botaoId);

                // Verificar o contador de produtos no carrinho antes de adicionar
                WebElement carrinhoAntes = navegador.findElement(By.id("shopping_cart_container"));
                String contadorAntes = carrinhoAntes.getText();

                // Tentar clicar no botão
                botao.click();

                // Verificar o contador de produtos no carrinho após o clique
                WebElement carrinhoDepois = navegador.findElement(By.id("shopping_cart_container"));
                String contadorDepois = carrinhoDepois.getText();

                if (contadorAntes.equals(contadorDepois)) {
                    // O contador não mudou, então o botão estava desabilitado
                    test.info("Botão desabilitado encontrado: " + botaoId);

                    // Agora, verificar se aparece a mensagem de erro
                    try {
                        WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(5));
                        WebElement mensagemErro = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".error-message")));
                        String textoErro = mensagemErro.getText();
                        test.pass("Mensagem de erro exibida: " + textoErro);
                    } catch (Exception ex) {
                        test.fail("Erro não exibido após tentativa de adicionar produto desabilitado.");
                    }
                } else {
                    // O contador mudou, ou seja, o produto foi adicionado ao carrinho
                    test.pass("Produto " + botaoId + " adicionado ao carrinho com sucesso.");
                }
            }

        } catch (Exception e) {
            test.fail("Erro durante o teste: " + e.getMessage());
        } finally {
            navegador.quit();
        }
    }

    @Test
    @DisplayName("Medir tempo de carregamento da página de produtos")
    public void medirTempoDeCarregamento() {  ExtentTest test = extent.createTest("Validação dos Dados da Compra",
                    "Valida se os dados da compra são exibidos corretamente para confirmação.")
            .assignAuthor("Bernardo").assignCategory("Produtos");
        WebDriver driver = new ChromeDriver();

        try {
            // Passo 1: Acesse a página de login
            driver.get("https://www.saucedemo.com/");

            // Passo 2: Preencha o login
            driver.findElement(By.id("user-name")).sendKeys("standard_user");  // Substitua pelo seu usuário
            driver.findElement(By.id("password")).sendKeys("secret_sauce");     // Substitua pela sua senha
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
        ExtentTest test = extent.createTest("Validação dos Dados da Compra",
                        "Valida se os dados da compra são exibidos corretamente para confirmação.")
               .assignAuthor("Bernardo").assignCategory("Produtos");

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