# DESOFS deliverables

## 1. Introduction

O seguinte projeto tem como tema principal um *meal delivery service*, no qual utilizadores do website podem encomendar
de refeições que serão confeccionadas pelos próprios. O propósito deste tema é essêncialmente permitir que,
as pessoas que não têm tempo para comprar ingredientes e pensar numa receita para cozinhar, possam encomendar um pacote,
que trará todos os ingredientes necessários para uma semana de refeições e as respetivas receitas.

## 2. User Stories

### 2.1. Utilizador

**US1** - Eu como utilizador quero poder criar uma conta no website para poder fazer encomendas. Eu não devo criar um
perfil
com
as mesmas credenciais que outra pessoa.

**US2** - Eu como utilizador quero poder fazer login no website para poder fazer encomendas. Eu não devo conseguir fazer
login
com credenciais inválidas ou de outros utilizadores.

**US3** - Eu como utilizador quero poder ver os pacotes dsiponíveis para encomenda, para poder escolher o que mais me
agrada.

**US4** - Eu como utilizador quero poder ver o conjunto de receitas disponíveis para cada pacote, para poder escolher o
que
mais me agrada.

**US5** - Eu como utilizador quero poder fazer o download do conjunto de receitas disponíveis para cada pacote, para
poder
ter acesso às receitas mesmo que não tenha acesso à internet. Eu não devo conseguir fazer download de receitas de
pacotes que não existam.

**US6** - Eu como utilizador quero conseguir efetuar uma encomenda de um pacote, para poder receber os ingredientes e as
receitas em casa. Eu não devo conseguir encomendar pacotes que não existam ou encomendar.

**US7** - Eu como utilizador quero poder ver o estado da minha encomenda, para poder saber quando é que a mesma será
entregue. Eu não devo conseguir ver o estado de encomendas de outros utilizadores.

**US8** - Eu como utilizador quero poder cancelar a minha encomenda, para poder receber o reembolso do valor pago. Eu
não
devo conseguir cancelar encomendas de outros utilizadores.

**US9** - Eu como utilizador quero poder ver o histórico das minhas encomendas, para poder ver o que já encomendei. Eu
não
devo conseguir ver o histórico de encomendas de outros utilizadores.

**US10** - Eu como utilizador quero poder ver reviews sobre os pacotes, para poder saber a opinião de outros
utilizadores
sobre os mesmos. Eu não devo conseguir ver reviews de pacotes que não existam.

**US11** - Eu como utilizador quero poder fazer uma review sobre um pacote, para poder partilhar a minha opinião sobre o
mesmo. Eu não devo conseguir fazer reviews de pacotes que não existam.

**US12** - Eu como utilizador quero poder faer uma alteração a uma review que fiz sobre um pacote, para poder corrigir
algum erro que tenha cometido. Eu não devo conseguir alterar reviews de pacotes que não existam ou editar reviews de
outros utilizadores.

**US13** - Eu como utilizador quero decidir qual o método de pagamento que quero utilizar para pagar a minha encomenda,
para poder escolher o método que me é mais conveniente. Eu não devo conseguir escolher métodos de pagamento que não
existam.

**US14** - Eu como utilizador quero poder adicionar um método de pagamento temporário, para poder pagar a encomenda.
Eu não devo conseguir adicionar métodos de pagamento que já existam.

**US15** - Eu como utilizador quero poder alterar informações do perfil da minha conta, para poder atualizar a minha
informação. Eu
não devo conseguir alterar o perfil de outros utilizadores.

**US16** - Eu como utilizador quero poder alterar a minha password, para poder atualizar a minha password. Eu não devo
conseguir alterar a password de outros utilizadores.

### 2.2. Administrador

**US17** - Eu como administrador quero poder efetuar o login no website, para poder gerir as encomendas. Eu não devo
conseguir fazer login com credenciais inválidas ou de outros utilizadores.

**US18** - Eu como administrador quero poder ver o total de encomendas feitas, para poder saber quantas encomendas foram
feitas. Eu não devo conseguir alterar os números de encomendas feitas.

**US19** - Eu como Administrador quero ter uma dashboard com cada pacoote e o número de encomendas feitas para cada um,
para poder saber quais os pacotes mais populares. Eu não devo conseguir alterar o número de encomendas feitas.

**US20** - Eu como Administrador quero poder criar um novo pacote, para poder adicionar novos pacotes ao website. Eu não
devo conseguir criar pacotes com o mesmo nome que pacotes já existentes.

**US21** - Eu como Administrador quero poder alterar um pacote, para poder atualizar a informação do pacote. Eu não devo
conseguir alterar pacotes que não existam.

**US22** - Eu como Administrador quero poder desativar um pacote, para poder remover pacotes do website. Eu não devo ser
capaz de desativar pacotes que não existam.

**US23** - Eu como Administrador quero poder adicionar receitas a um pacote, para poder adicionar receitas a pacotes.

**US24** - Eu como Administrador quero poder alterar receitas de um pacote, para poder atualizar as receitas de um
pacote.

**US26** - Eu como Administrador quero poder remover reviews com conteúdo impróprio, para poder manter o website limpo.

### 2.4 Gestor de Ficheiros

**US27** - Eu como Gestor de Ficheiros quero poder fazer login no website, para poder gerir as receitas. Eu não devo
conseguir fazer login com credenciais inválidas ou de outros utilizadores.

**US28** - Eu como Gestor de Ficheiros quero poder adicionar uma receita, para poder adicionar receitas a um pacote. Eu
não devo conseguir adicionar receitas que já existam.

**US29** - Eu como Gestor de Ficheiros quero poder alterar uma receita, para poder atualizar a informação da receita. Eu
não devo conseguir alterar receitas que não existam.

**US30** - Eu como Gestor de Ficheiros quero poder remover uma receita, para poder remover receitas de um pacote. Eu não
devo conseguir remover receitas que não existam.

### 2.5 Requisitos de Segurança

**RS1** - Uso de uma checklist de boas práticas de código
**RS2** - Efetuar revisões de código
**RS3** - Implementar um design seguro
**RS4** - Implementar a arquitetura Onion para a API
**RS5** - Fazer pseudo requests com o uso da api do sveltekit
**RS6** - Usar o dependabot
**RS7** - Usar o github actions para CI/CD
**RS8** - Usar o DockerScout
**RS9** - Usar o OWASP ASVS checklist
**RS10** - Seguir uma semantica fixa para o controlo de versões
**RS11** - Seguir normas para efetuar commits
**RS12** - Usar Trunk Based Development
**RS13** - Apenas efetuar o Release please após a revisão de código por parte de todos os membros da equipa
**RS14** - Usar ferramentas de threat modeling como a Microsoft Threat Modeling Tool e o OWASP Threat Dragon
**RS15** - Usar ferramentas de segurança como o OWASP ZAP

# Exit Points

| ID | Name                                 |
|----|--------------------------------------|
| 1  | HTTP Response                        |
| 2  | Pedidos à API                        |
| 3  | Falha na validação dos dados         |
| 4  | Operação de escrita na base de dados |
| 5  | Finalização de uma transação         |
| 6  | Tratar de Erros                      |
| 7  | Logging                              |
| 8  | Término da Sessão                    |
| 9  | Interação dos utilizadores           |

# Threat Analysis

## 1. STRIDE

| Categoria              | Descrição                                                                                                                                                                                                                                                                                        |
|------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Spoofing               | **Threat 1**: A aplicação tem de ser accessivel aos utilizadores, consequentemente tem de ser fácil de user, contudo não se pode introduzir uma ameaça<br/> **Threat 2**: Visto esta aplicação ser delivery service, implica que haja a possibilidade de alguem se fazer passar por outra pessoa |
| Tampering              | **Threat 1**: Como existem receitas para fazer download, que se encontram no servidor , é possivel que estas sejam editadas                                                                                                                                                                      |
| Repudiation            | Sem Ameaças                                                                                                                                                                                                                                                                                      |
| Information disclosure | **Threat 1**: É possivel que haja XSS visto haver formas de inserir scripts de javascript, com isto é possivel adquirir informação de outros utilizadores  <br/> **Threat 2**: É possivel interceptar pedidos de Http, consequentemente obter informação que não era suposto                     |
| Denial of service      | **Threat 1**: Visto ser um sistema monólitico, é possivel efetuar um DoS                                                                                                                                                                                                                         |
| Elevation of privilege | Sem Ameaças                                                                                                                                                                                                                                                                                      |

## 2. ASF

| Category                 | Description                                                                                                                                             |
|--------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| Auditing and Logging     | **Threat 1**: Como não existe sistema de logging, não é possivel saber quem fez o que e quando. <br/>**Threat 2**: Qualquer pessoa pode aceder aos logs |
| Authentication           | **Threat 1**: Com o sistema simples que está em uso, é relativamente fácil de usar credenciais de outros utilizadores                                   |
| Authorization            | **Threat 1**: Qualquer pessoa pode fazer download das receitas, criando um possivel ponto de entrada                                                    |
| Configuration management | **Threat 1**: A aplicação está a correr com todas as permições, logo é uma possível ameaça                                                              |

# Countermeasures

### 1. STRIDE

| Categoria              | Descrição                                                                                                                                                                                                                                                                                                                                                                                      |
|------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Spoofing               | **Countermeasure 1**: Utilização de autenticação de dois fatores<br/> **Countermeasure 2**: É obrigatório ter passwords fortes <br/> **Countermeasure 3**: É utilizado Jwt em vez de uma autênticação baseada na base de dados                                                                                                                                                                 |
| Tampering              | **Countermeasure 1**: Utilização de Hashing para as receitas<br/> **Countermeasure 2**: Utilização de HTTPS para as comunicações entre o cliente e o servidor <br/> **Countermeasure 3**: Criar diferentes definições de acessos                                                                                                                                                               |
| Repudiation            | **Countermeasure 1**: Todas as compras são guardadas na base de dados em formato de event streaming                                                                                                                                                                                                                                                                                            |
| Information disclosure | **Countermeasure 1**: Utilização de HTTPS para as comunicações entre o cliente e o servidor<br/> **Countermeasure 2**: Utilização de JWT para autenticação<br/> **Countermeasure 3**: Utilização de CORS para proteger a API <br/> **Countermeasure 3**: Aplicar algoritmos de validação a inputs                                                                                              |
| Denial of service      | **Countermeasure 1**: Utilização de um sistema distribuido em vez de um sistema monolitico<br/> **Countermeasure 2**: Utilização de um sistema de rate limiting                                                                                                                                                                                                                                |
| Elevation of privilege | **Countermeasure 1**: Utilização de HTTPS para as comunicações entre o cliente e o servidor<br/> **Countermeasure 2**: Utilização de JWT para autenticação<br/> **Countermeasure 3**: Utilização de CORS para proteger a API <br/> **Countermeasure 3**: Aplicar algoritmos de validação a inputs <br/> **Countermeasure 4**: Ninguem deve ter a capacidade de mudar os privilégios do sistema |

## Ferramentas de teste

https://owasp.org/www-community/api_security_tools

https://owasp.org/www-community/Free_for_Open_Source_Application_Security_Tools

### 1. SAST

### 2. DAST

### 3. IAST

https://www.contrastsecurity.com/contrast-community-edition