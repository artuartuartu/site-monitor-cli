# Site Monitor CLI 🌐

[Java CI with Maven](https://github.com/artuartuartu/site-monitor-cli/actions)

**Versão Atual:** 2.0.0  
**Autor:** Arthur Martins de Andrade  
**Link do Repositório:** [https://github.com/artuartuartu/site-monitor-cli](https://github.com/artuartuartu/site-monitor-cli)

---

## Introdução
O **Site Monitor CLI** é uma aplicação de linha de comando leve e eficiente projetada para verificar a disponibilidade de serviços web. A solução permite validar se "portais de interesse público" estão operacionais, ajudando na fiscalização da continuidade de serviços digitais essenciais. Sem ferramentas acessíveis para monitoramento, o cidadão comum e organizações sociais têm dificuldade em auditar a disponibilidade desses serviços, o que compromete a transparência pública e a inclusão digital.

## Público-Alvo
* **Organizações Sociais e ONGs:** Para auditoria de portais de dados abertos.
* **Desenvolvedores e Administradores:** Para monitoramento rápido de infraestrutura.
* **Cidadãos Engajados:** Que buscam verificar a estabilidade de serviços públicos digitais.

## Funcionalidades Principais
* **Verificação Individual:** Checagem instantânea de disponibilidade de uma URL.
* **Processamento em Lote:** Leitura automatizada de múltiplos domínios a partir de um arquivo de texto.
* **Geolocalização de Servidores:** Integração com API externa para identificar onde o servidor do site está hospedado.
* **Resiliência de Execução:** Tratamento de erros para URLs malformadas e timeouts.
* **Garantia de Qualidade:** Pipeline automatizado com testes unitários e análise estática de código.

## Integração com API Externa

A aplicação consome a IP-API, uma API REST pública e gratuita, utilizada para enriquecer os dados de monitoramento. Quando um site é verificado, o programa extrai o domínio e realiza uma requisição HTTP assíncrona para obter a localização geográfica do servidor.

* **Endpoint utilizado:** `http://ip-api.com/json/{dominio passado pelo usuário}`
* **Dados extraídos:** País (`country`), Região/Estado (`regionName`) e Cidade (`city`).
* **Objetivo:** Permitir que o usuário saiba não apenas se o site está online, mas em qual região do mundo a infraestrutura daquele serviço público ou portal está hospedada.

## Funcionamento do Processamento em Lote

A funcionalidade de verificação em lote permite que o usuário monitore uma lista extensa de endereços de forma automatizada, sem a necessidade de entrada manual para cada site. Caso uma linha esteja em branco ou contenha uma URL inválida, o programa ignora o erro e prossegue para a próxima linha, garantindo que o monitoramento não seja interrompido.

### Requisito do Arquivo de Entrada
Para utilizar esta opção, é necessário que exista um arquivo de texto no **mesmo diretório** onde o programa está sendo executado:

* **Nome do arquivo** (ex: `sites.txt`)
* **Formato** Uma URL completa por linha (ex: `https://www.google.com`).
* **Localização:** Deve estar na mesma pasta do executável `.jar` ou na raiz do código-fonte..

### Exemplo de conteúdo para o arquivo de texto:
```text
google.com
github.com
microsoft.com
apple.com
nasa.gov
who.int
bbc.com
nytimes.com
https://www.theguardian.com
https://www.reddit.com
https://www.linkedin.com
https://www.spotify.com
https://www.youtube.com
https://www.instagram.com
https://www.facebook.com
```

## Tecnologias Utilizadas
* **Java 25:** Linguagem base utilizando a API nativa `HttpClient`.
* **Maven:** Gestão de dependências e ciclo de vida do projeto.
* **GSON (Google):** Biblioteca para parser e manipulação dos dados JSON retornados pela API.
* **JUnit 5:** Implementação de testes automatizados.
* **Checkstyle:** Garantia de padrões de código (Sun Checks).
* **GitHub Actions:** Workflow de CI para validação automática de cada commit.

---

## Como instalar e rodar (Modo Usuário)

O projeto está configurado para gerar um **Fat JAR** (um arquivo único `.jar` contendo todas as dependências embutidas). Isso elimina a necessidade de ter o Maven instalado para rodar a aplicação em ambiente de produção.

### Passo a Passo para Execução:

1.  **Pré-requisitos:** Ter o [**JDK 25**](https://www.oracle.com/java/technologies/javase/jdk25-archive-downloads.html) instalado em sua máquina.
2. Acesse a seção de **[Releases](https://github.com/artuartuartu/site-monitor-cli/releases)** no menu lateral do repositório.
3. Baixe o arquivo binário executável da versão estável: `site-monitor-cli-2.0.0.jar`.
4. Mova o arquivo `.jar` baixado para uma pasta de sua preferência.
5. Abra o terminal (Prompt de Comando ou PowerShell no Windows, Terminal no Linux/macOS) dentro dessa pasta e execute o comando abaixo:

```bash
java -jar site-monitor-cli-2.0.0.jar
```


## Como Instalar e Rodar (Modo Desenvolvedor)

1.  **Pré-requisitos:** Ter o [**JDK 25**](https://www.oracle.com/java/technologies/javase/jdk25-archive-downloads.html) e o [**Maven**](https://maven.apache.org/download.cgi) instalados em sua máquina.
2.  **Clonar o repositório:**
    ```bash
    git clone https://github.com/artuartuartu/site-monitor-cli.git
    cd site-monitor-cli
    ```
3.  **Instalar dependências:**
    ```bash
    mvn install
    ```
    
## Execução

Para rodar a aplicação:
```bash
mvn -q exec:java
```
<img width="527" height="248" alt="Demonstração da execução da aplicação" src="https://github.com/user-attachments/assets/e34927b6-4e92-451e-bede-40d5d22ce58d" />


## Testes 

Para executar os testes unitários:
```bash
mvn test
```
<img width="1149" height="867" alt="Demonstração da execução dos testes" src="https://github.com/user-attachments/assets/eea5cd18-2408-4f39-b2a1-1422c19d3d29" />


## Lint

Para executar a análise estática (Checkstyle):
```bash
mvn checkstyle:check
```
<img width="731" height="354" alt="Demonstração do uso da ferramente de lint" src="https://github.com/user-attachments/assets/9373b83e-5f64-433b-92f6-6d815db579a1" />

