package br.com.saucedemo.swaglabs.checkout;



import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

    @DisplayName("Testes Automatizados da Função Checkout")
    public class CheckoutTests {

        // Variáveis para o ExtentReports e ExtentSparkReporter
        private static ExtentReports extent;
        private static ExtentSparkReporter sparkReporter;

        @BeforeAll
        public static void setup() {
            // Configurar o reporter Spark do ExtentReports
            sparkReporter = new ExtentSparkReporter("relatorioTestesDeCheckout.html");
            sparkReporter.config().setDocumentTitle("Relatório de Testes - Checkout");

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
