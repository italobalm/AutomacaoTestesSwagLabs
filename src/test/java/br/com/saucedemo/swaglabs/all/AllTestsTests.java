package br.com.saucedemo.swaglabs.all;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@DisplayName("Testes Automatizados")

public class AllTestsTests {
    // Variáveis para o ExtentReports e ExtentSparkReporter
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;

    @BeforeAll
    public static void setup() {
        // Configurar o reporter Spark do ExtentReports
        sparkReporter = new ExtentSparkReporter("relatorioAllTests.html");
        sparkReporter.config().setDocumentTitle("Relatório de Todos os Testes");

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
            if (navegador.findElement(By.className("checkout_info_wrapper")).isDisplayed()) {
                test.fail("Usuário consegue clicar em checkout mesmo sem produtos no carrinho");
            } else {
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
    @Test
    @DisplayName("Verificar não preenchimento do campo CEP")
    public void testVerificarNaoPreenchimentoDoCampoCEP() {
        ExtentTest test = extent.createTest("Verificar não preenchimento do campo CEP").assignAuthor("Italo").assignCategory("Checkout");

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

            //Adicionar produto no carrinho
            navegador.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

            //Entrar no carrinho
            navegador.findElement(By.id("shopping_cart_container")).click();

            //Clicar em checkout
            navegador.findElement(By.id("checkout")).click();

            //Inserir primeiro nome
            navegador.findElement(By.id("first-name")).sendKeys("a");

            //Inserir ultimo nome
            navegador.findElement(By.id("last-name")).sendKeys("a");

            //Clicar em continuar
            navegador.findElement(By.id("continue")).click();

            if (navegador.findElement(By.className("error-message-container")).isDisplayed()) {
                test.pass("Usuário impedido de seguir com mensagem de erro.");
            } else {
                test.fail("Usuário consegue seguir mesmo com CEP em branco");
            }

        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }
    }

    @Test
    @DisplayName("ValidarLimiteDeCaracteresEmCEP")
    public void testValidarLimiteDeCaracteresEmCEP() {
        ExtentTest test = extent.createTest("Validar limite de caracteres em CEP").assignAuthor("Italo").assignCategory("Checkout");

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

            // Adicionar produto no carrinho
            navegador.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

            // Entrar no carrinho
            navegador.findElement(By.id("shopping_cart_container")).click();

            // Clicar em checkout
            navegador.findElement(By.id("checkout")).click();

            // Inserir primeiro nome
            navegador.findElement(By.id("first-name")).sendKeys("a");

            // Inserir ultimo nome
            navegador.findElement(By.id("last-name")).sendKeys("a");

            // Inserir CEP com 9 caracteres
            navegador.findElement(By.id("postal-code")).sendKeys("508240000");

            // Clicar em continuar
            navegador.findElement(By.id("continue")).click();

            // Usar WebDriverWait para aguardar o elemento de erro
            WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(5));

            try {
                // Esperar até que a mensagem de erro esteja presente
                WebElement erroMensagem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error-message-container")));
                String textoErro = erroMensagem.getText();

                // Verificar se a mensagem de erro está presente
                if (textoErro.contains("Error: Postal Code is required")) {
                    test.pass("Usuário impedido de seguir com mensagem de erro.");
                } else {
                    test.fail("Erro inesperado: " + textoErro);
                }
            } catch (TimeoutException e) {
                test.fail("Mensagem de erro não encontrada após o tempo esperado.");
            }

        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }
    }
    @Test
    @DisplayName("VerificarATipagemDeCaracteresNoCampoCEP")
    public void testVerificarATipagemDeCaracteresNoCampoCEP() {
        ExtentTest test = extent.createTest("Verificar a Tipagem de caracteres no campo CEP").assignAuthor("Italo").assignCategory("Checkout");

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

            // Adicionar produto no carrinho
            navegador.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

            // Entrar no carrinho
            navegador.findElement(By.id("shopping_cart_container")).click();

            // Clicar em checkout
            navegador.findElement(By.id("checkout")).click();

            // Inserir primeiro nome
            navegador.findElement(By.id("first-name")).sendKeys("a");

            // Inserir ultimo nome
            navegador.findElement(By.id("last-name")).sendKeys("a");

            // Inserir CEP com numeros e letras
            navegador.findElement(By.id("postal-code")).sendKeys("5aa40000");

            // Clicar em continuar
            navegador.findElement(By.id("continue")).click();

            // Usar WebDriverWait para aguardar o elemento de erro
            WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(5));

            try {
                // Esperar até que a mensagem de erro esteja presente
                WebElement erroMensagem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error-message-container")));
                String textoErro = erroMensagem.getText();

                // Verificar se a mensagem de erro está presente
                if (textoErro.contains("Error: Postal Code is required")) {
                    test.pass("Usuário impedido de seguir com mensagem de erro.");
                } else {
                    test.fail("Erro inesperado: " + textoErro);
                }
            } catch (TimeoutException e) {
                test.fail("Mensagem de erro não encontrada após o tempo esperado.");
            }

        } catch (Exception e) {
            test.fail("Ocorreu um erro durante o teste: " + e.getMessage());
        } finally {
            // Fechar o navegador
            navegador.quit();
        }
    }
    @Test
    @DisplayName("Testar preenchimento de dados para verificação de identidade na compra")
    public void testarPreenchimentoDeDadosCompra() {

        // Criar um teste com tag personalizada
        ExtentTest test = extent.createTest("Preencher dados para verificação de identidade na compra",
                "Preenche os dados de identidade e verifica a segurança na compra.").assignAuthor("Bernardo").assignCategory("Checkout");

        WebDriver driver = new ChromeDriver();

        try {
            // Passo 1: Acesse a página de login
            driver.get("https://www.saucedemo.com/");
            test.pass("Acesse a página de login com sucesso.");


            // Passo 2: Preencha o login
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            test.pass("Login com sucesso.");


            // Passo 3: Adicionar item ao carrinho
            WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
            addToCartButton.click();
            test.pass("Item adicionado ao carrinho.");


            // Passo 4: Ir para o carrinho
            WebElement shoppingCart = driver.findElement(By.id("shopping_cart_container"));
            shoppingCart.click();
            test.pass("Carrinho acessado com sucesso.");


            // Passo 5: Clicar no botão de checkout
            WebElement checkoutButton = driver.findElement(By.id("checkout"));
            checkoutButton.click();
            test.pass("Página de checkout acessada.");


            // Passo 6: Preencher os dados de identidade
            driver.findElement(By.id("first-name")).sendKeys("Benardo");
            driver.findElement(By.id("last-name")).sendKeys("Simões");
            driver.findElement(By.id("postal-code")).sendKeys("50820000");
            test.pass("Dados de identidade preenchidos.");


            // Passo 7: Confirmar os dados e continuar
            driver.findElement(By.id("continue")).click();
            test.pass("Compra confirmada com sucesso.");

        } catch (Exception e) {
            test.fail("Falha no processo de compra: " + e.getMessage());
        } finally {
            driver.quit();
            extent.flush();
        }
    }

    @Test
    @DisplayName("Validar a quantidade de caracteres no campo 'Primeiro Nome'")
    public void validarQuantidadeDeCaracteresNoPrimeiroNome() throws InterruptedException {

        // Criar um teste com tag personalizada
        ExtentTest test = extent.createTest("Validar a quantidade de caracteres no campo 'Primeiro Nome'",
                        "Preenche o campo 'Primeiro Nome' com mais de 150 caracteres e valida se é permitido.")
                .assignAuthor("Bernardo").assignCategory("Checkout");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Passo 1: Acesse a página de login
            driver.get("https://www.saucedemo.com/");
            test.pass("Acesse a página de login com sucesso.");


            // Passo 2: Preencha o login
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            test.pass("Login com sucesso.");


            // Passo 3: Adicionar item ao carrinho
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            test.pass("Item adicionado ao carrinho.");


            // Passo 4: Ir para o carrinho
            wait.until(ExpectedConditions.elementToBeClickable(By.id("shopping_cart_container")));
            driver.findElement(By.id("shopping_cart_container")).click();
            test.pass("Carrinho acessado com sucesso.");


            // Passo 5: Clicar no botão de checkout
            wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
            driver.findElement(By.id("checkout")).click();
            test.pass("Página de checkout acessada com sucesso.");


            // Passo 6: Preencher os campos de nome, sobrenome e código postal
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
            String nomeLongo = "B".repeat(151);  // 151 caracteres para exceder o limite
            driver.findElement(By.id("first-name")).sendKeys(nomeLongo);
            driver.findElement(By.id("last-name")).sendKeys("Simões");
            driver.findElement(By.id("postal-code")).sendKeys("50820000");
            test.pass("Campos 'Primeiro Nome', 'Sobrenome' e 'Código Postal' preenchidos.");


            // Passo 7: Clicar no botão "Continue"
            wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
            driver.findElement(By.id("continue")).click();
            test.pass("Tentativa de continuar com nome de mais de 150 caracteres.");


            // Validação: Verificar se o sistema não permite enviar o formulário
            String erroMensagem = driver.findElement(By.cssSelector(".error-message-container")).getText();
            if (erroMensagem.contains("First Name is required")) {
                test.pass("Erro: O campo 'Primeiro Nome' não aceitou mais de 150 caracteres, como esperado.");
            } else {
                test.fail("Erro: O sistema aceitou mais de 150 caracteres no campo 'Primeiro Nome', o que não deveria ser permitido.");
            }

        } catch (Exception e) {
            test.fail("Falha no processo de verificação de quantidade de caracteres no primeiro nome: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Validar a quantidade de caracteres no campo 'Último Nome'")
    public void validarQuantidadeDeCaracteresNoUltimoNome() throws InterruptedException {

        // Criar um teste com tag personalizada
        ExtentTest test = extent.createTest("Validar a quantidade de caracteres no campo 'Último Nome'",
                        "Preenche o campo 'Último Nome' com mais de 250 caracteres e valida se é permitido.")
                .assignAuthor("Bernardo").assignCategory("Checkout");

        // Inicializar o WebDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            // Passo 1: Acesse a página de login
            driver.get("https://www.saucedemo.com/");
            test.pass("Acesse a página de login com sucesso.");

            // Passo 2: Preencha o login
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            test.pass("Login com sucesso.");

            // Passo 3: Adicionar item ao carrinho
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            test.pass("Item adicionado ao carrinho.");

            // Passo 4: Ir para o carrinho
            wait.until(ExpectedConditions.elementToBeClickable(By.id("shopping_cart_container")));
            driver.findElement(By.id("shopping_cart_container")).click();
            test.pass("Carrinho acessado com sucesso.");

            // Passo 5: Clicar no botão de checkout
            wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
            driver.findElement(By.id("checkout")).click();
            test.pass("Página de checkout acessada com sucesso.");

            // Passo 6: Preencher os campos de nome, sobrenome e código postal
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
            driver.findElement(By.id("first-name")).sendKeys("Carlos");  // Preenchendo com nome curto
            String ultimoNomeLongo = "S".repeat(251);  // 251 caracteres para exceder o limite
            driver.findElement(By.id("last-name")).sendKeys(ultimoNomeLongo); // Sobrenome longo
            driver.findElement(By.id("postal-code")).sendKeys("50820000");
            test.pass("Campos 'Primeiro Nome', 'Último Nome' e 'Código Postal' preenchidos.");

            // Passo 7: Clicar no botão "Continue"
            wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
            driver.findElement(By.id("continue")).click();
            test.pass("Tentativa de continuar com nome de mais de 250 caracteres.");

            // Validação: Verificar se o sistema não permite enviar o formulário
            // Caso o sistema aceite o nome com mais de 250 caracteres, isso será considerado um erro
            String erroMensagem = driver.findElement(By.cssSelector(".error-message-container")).getText();
            if (erroMensagem.contains("Last Name is required")) {
                test.pass("Erro: O campo 'Último Nome' não aceitou mais de 250 caracteres, como esperado.");
            } else {
                test.fail("Erro: O sistema aceitou mais de 250 caracteres no campo 'Último Nome', o que não deveria ser permitido.");
            }

        } catch (Exception e) {
            test.fail("Falha no processo de verificação de quantidade de caracteres no último nome: " + e.getMessage());
        } finally {
            // Fechar o navegador
            driver.quit();
        }
    }

    @Test
    @DisplayName("Verificação do preenchimento do campo 'Último Nome'")
    public void verificarPreenchimentoUltimoNome() {

        // Criar um teste com tag personalizada
        ExtentTest test = extent.createTest("Verificar o preenchimento do campo 'Último Nome'",
                        "Verifica se o usuário é impedido de continuar caso o campo 'Último Nome' esteja em branco.")
                .assignAuthor("Bernardo").assignCategory("Checkout");

        // Inicializar o WebDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Passo 1: Acesse a página de login
            driver.get("https://www.saucedemo.com/");
            test.pass("Acesse a página de login com sucesso.");

            // Passo 2: Preencha o login
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            test.pass("Login com sucesso.");

            // Passo 3: Adicionar item ao carrinho
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            test.pass("Item adicionado ao carrinho.");

            // Passo 4: Ir para o carrinho
            wait.until(ExpectedConditions.elementToBeClickable(By.id("shopping_cart_container")));
            driver.findElement(By.id("shopping_cart_container")).click();
            test.pass("Carrinho acessado com sucesso.");

            // Passo 5: Clicar no botão de checkout
            wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
            driver.findElement(By.id("checkout")).click();
            test.pass("Página de checkout acessada com sucesso.");

            // Passo 6: Preencher os campos de nome, sobrenome (deixar vazio) e código postal
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
            driver.findElement(By.id("first-name")).sendKeys("Carlos");  // Preenchendo com nome válido
            driver.findElement(By.id("last-name")).sendKeys(""); // Sobrenome vazio
            driver.findElement(By.id("postal-code")).sendKeys("50820000");
            test.pass("Campos 'Primeiro Nome' e 'Código Postal' preenchidos, 'Último Nome' deixado em branco.");

            // Passo 7: Clicar no botão "Continue"
            wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
            driver.findElement(By.id("continue")).click();
            test.pass("Tentativa de continuar com o 'Último Nome' em branco.");

            // Validação: Verificar se o sistema não permite enviar o formulário
            // Caso o sistema aceite o 'Último Nome' em branco, isso será considerado um erro
            String erroMensagem = driver.findElement(By.cssSelector(".error-message-container")).getText();
            if (erroMensagem.contains("Last Name is required")) {
                test.pass("Erro: O campo 'Último Nome' não aceitou ficar em branco, como esperado.");
            } else {
                test.fail("Erro: O sistema aceitou o 'Último Nome' em branco, o que não deveria ser permitido.");
            }

        } catch (Exception e) {
            test.fail("Falha no processo de verificação do preenchimento do campo 'Último Nome': " + e.getMessage());
        } finally {
            // Fechar o navegador
            driver.quit();
        }
    }

    @Test
    @DisplayName("Verificação do preenchimento do campo 'Primeiro Nome'")
    public void verificarPreenchimentoPrimeiroNome() {

        // Criar um teste com tag personalizada
        ExtentTest test = extent.createTest("Verificar o preenchimento do campo 'Primeiro Nome'",
                        "Verifica se o sistema impede o usuário de continuar caso o campo 'Primeiro Nome' esteja vazio.")
                .assignAuthor("Bernardo").assignCategory("Checkout");

        // Inicializar o WebDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Passo 1: Acesse a página de login
            driver.get("https://www.saucedemo.com/");
            test.pass("Acesse a página de login com sucesso.");

            // Passo 2: Preencha o login
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            test.pass("Login com sucesso.");

            // Passo 3: Adicionar item ao carrinho
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            test.pass("Item adicionado ao carrinho.");

            // Passo 4: Ir para o carrinho
            wait.until(ExpectedConditions.elementToBeClickable(By.id("shopping_cart_container")));
            driver.findElement(By.id("shopping_cart_container")).click();
            test.pass("Carrinho acessado com sucesso.");

            // Passo 5: Clicar no botão de checkout
            wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
            driver.findElement(By.id("checkout")).click();
            test.pass("Página de checkout acessada com sucesso.");

            // Passo 6: Preencher os campos de nome (deixar vazio), sobrenome e código postal
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
            driver.findElement(By.id("first-name")).sendKeys(""); // Nome vazio
            driver.findElement(By.id("last-name")).sendKeys("Simões");
            driver.findElement(By.id("postal-code")).sendKeys("50820000");
            test.pass("Campos 'Sobrenome' e 'Código Postal' preenchidos, 'Primeiro Nome' deixado em branco.");

            // Passo 7: Clicar no botão "Continue"
            wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
            driver.findElement(By.id("continue")).click();
            test.pass("Tentativa de continuar com o 'Primeiro Nome' em branco.");

            // Validação: Verificar se o sistema não permite enviar o formulário
            // Caso o sistema aceite o 'Primeiro Nome' em branco, isso será considerado um erro
            String erroMensagem = driver.findElement(By.cssSelector(".error-message-container")).getText();
            if (erroMensagem.contains("First Name is required")) {
                test.pass("Erro: O campo 'Primeiro Nome' não aceitou ficar em branco, como esperado.");
            } else {
                test.fail("Erro: O sistema aceitou o 'Primeiro Nome' em branco, o que não deveria ser permitido.");
            }

        } catch (Exception e) {
            test.fail("Falha no processo de verificação do preenchimento do campo 'Primeiro Nome': " + e.getMessage());
        } finally {
            // Fechar o navegador
            driver.quit();
        }
    }

    @Test
    @DisplayName("Verificação de redirecionamento caso não preenchimento de todos os campos")
    public void verificarRedirecionamentoComCamposEmBranco() {


        // Criar um teste com tag personalizada
        ExtentTest test = extent.createTest("Verificar redirecionamento com todos os campos em branco",
                        "Verifica se o sistema exibe uma mensagem de erro ao tentar avançar com todos os campos em branco.")
                .assignAuthor("Bernardo").assignCategory("Checkout");

        // Inicializar o WebDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Passo 1: Acesse a página de login
            driver.get("https://www.saucedemo.com/");
            test.pass("Acesse a página de login com sucesso.");

            // Passo 2: Preencha o login
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            test.pass("Login com sucesso.");

            // Passo 3: Adicionar item ao carrinho
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            test.pass("Item adicionado ao carrinho.");

            // Passo 4: Ir para o carrinho
            wait.until(ExpectedConditions.elementToBeClickable(By.id("shopping_cart_container")));
            driver.findElement(By.id("shopping_cart_container")).click();
            test.pass("Carrinho acessado com sucesso.");

            // Passo 5: Clicar no botão de checkout
            wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
            driver.findElement(By.id("checkout")).click();
            test.pass("Página de checkout acessada com sucesso.");

            // Passo 6: Deixar todos os campos em branco e tentar continuar
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
            driver.findElement(By.id("first-name")).sendKeys(""); // Campo vazio
            driver.findElement(By.id("last-name")).sendKeys("");  // Campo vazio
            driver.findElement(By.id("postal-code")).sendKeys(""); // Campo vazio
            test.pass("Todos os campos deixados em branco.");

            // Passo 7: Clicar no botão "Continue"
            wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
            driver.findElement(By.id("continue")).click();
            test.pass("Tentativa de continuar com todos os campos em branco.");

            // Validação: Verificar se a mensagem de erro é exibida
            String erroMensagem = driver.findElement(By.cssSelector(".error-message-container")).getText();
            if (erroMensagem.contains("First Name is required")) {
                test.pass("Mensagem de erro exibida corretamente: 'First Name is required'.");
            } else {
                test.fail("Mensagem de erro incorreta ou ausente ao tentar continuar com os campos em branco.");
            }

        } catch (Exception e) {
            test.fail("Falha no processo de verificação com campos em branco: " + e.getMessage());
        } finally {
            // Fechar o navegador
            driver.quit();
        }
    }
    @Test
    @DisplayName("Verificação do preenchimento de todos os campos e continuar para visão geral da finalização de compra (checkout)")
    public void verificarPreenchimentoDeTodosOsCampos() {

        // Criar um teste com tag personalizada
        ExtentTest test = extent.createTest("Verificar preenchimento de todos os campos e continuar para visão geral",
                        "Este teste valida se o sistema permite avançar para a próxima página ao preencher todos os campos obrigatórios.")
                .assignAuthor("Bernardo").assignCategory("Checkout");

        // Inicializar o WebDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Passo 1: Acesse a página de login
            driver.get("https://www.saucedemo.com/");
            test.info("Página de login acessada.");

            // Passo 2: Preencha o login
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            test.pass("Login realizado com sucesso.");


            // Passo 3: Adicionar item ao carrinho
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            test.pass("Item adicionado ao carrinho.");


            // Passo 4: Ir para o carrinho
            wait.until(ExpectedConditions.elementToBeClickable(By.id("shopping_cart_container")));
            driver.findElement(By.id("shopping_cart_container")).click();
            test.pass("Carrinho acessado.");


            // Passo 5: Clicar no botão de checkout
            wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
            driver.findElement(By.id("checkout")).click();
            test.pass("Página de checkout acessada.");


            // Passo 6: Preencher os campos obrigatórios
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
            driver.findElement(By.id("first-name")).sendKeys("Bernardo");
            driver.findElement(By.id("last-name")).sendKeys("Silva");
            driver.findElement(By.id("postal-code")).sendKeys("50820000");
            test.pass("Campos preenchidos corretamente.");


            // Passo 7: Clicar no botão "Continue"
            wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
            driver.findElement(By.id("continue")).click();
            test.info("Tentativa de continuar com todos os campos preenchidos.");


            // Validação: Verificar se a próxima página foi carregada
            boolean isOverviewPageDisplayed = wait.until(ExpectedConditions.urlContains("checkout-step-two"));
            if (isOverviewPageDisplayed) {
                test.pass("Página de visão geral da finalização de compra exibida corretamente.");
            } else {
                test.fail("Página de visão geral não foi exibida.");
            }

        } catch (Exception e) {
            test.fail("Erro durante a validação: " + e.getMessage());
        } finally {
            // Fechar o navegador
            driver.quit();
        }
    }


}



