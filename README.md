# Projeto de Sistema de Gerenciamento de Salários e Impostos para Funcionários


Este projeto visa desenvolver uma API robusta e eficiente para o cálculo de ajuste de salários e impostos a serem pagos por funcionários de uma organização. 

O sistema será capaz de realizar cálculos precisos, considerando diversos parâmetros de acordo com o salário base, taxas de ajuste de salário e taxa de imposto de renda a ser pago de acordo com a faixa de valor que o colaborador, proporcionando uma forma mais automatizada e simples de fazer os cálculos, além de uma visão detalhada e transparente para empregadores e funcionários.

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)

## Configurações necessárias

- `Java Development Kit (JDK)`: versão 8 ou superior
- `Mvnw`: Para compilação e grenciamento de dependências
- `PostgreSQL`: Banco de dados instalado e configurado

## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/LuanLB99/Calculadora_Ajuste_Salario
```

Entre no diretório do projeto

```bash
  cd Calculadora_Ajuste_Salario
```

Instale as dependências

```bash
  ./mvnw clean install
```

Inicie o servidor

```bash
  /mvnw spring-boot:run
```

a aplicação estará acessível em http://localhost:8080.

### Configurar variáveis de ambiente

`DB_URL=jdbc:postgresql://localhost:5432/nome-do-banco`

`DB_USERNAME=seu-usuario`

`DB_PASSWORD=sua-senha`



## Documentação da API

#### Retorna todos os colaboradores cadastrados

```http
  GET /api/employee
```

#### Criar um novo colaborador

```http
  POST /api/employee
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `name`      | `string` | **Obrigatório**. Nome do colaborador |
| `cpf` | `string`      | **Obrigatório**. O cpf tem que ser do tipo "XXX.XXX.XXX-XX |
| `birthDay` | `string` | **Obrigatório**. Data de nascimento. |
| `phone`      | `string` | **Obrigatório**. Tem que ser um número com 11 dígitos.|
| `address`      | `string` | **Obrigatório**. Endereço do usuário. |
| `salary`      | `BigDecimal` | **Obrigatório**. Precisa ser maior que zero. |


```json
retorno do colaborador cadastrado.
{
  "id": 1,
  "name": "Jonas",
  "cpf": "421.265.258-00",
  "birthDay": "10-03-1999",
  "phone": "12980973443",
  "address": "Avenida Cinderela",
  "salary": 3002.00
}
```

#### Ajustar salário do colaborador

```http
  POST /api/adjustSalary
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `cpf` | `string`      | **Obrigatório**. Exemplo: "421.265.258-00" |

```json
retorno do salário ajustado, ajuste e a taxa correspondente.
{
  "cpf": "421.265.258-00",
  "newSalary": 3122.08,
  "adjustment": 120.08,
  "adjustmentRate": "4%"
}
```

#### Calcular salário imposto de renda do colaborador

```http
  POST /api/calculateIncomeTax
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `cpf` | `string`      | **Obrigatório**. Exemplo: "421.265.258-00" |

```json
retorno do imposto a ser pago.
{
  "cpf": "421.265.258-00",
  "taxToPay": "R$ 101.98"
}
```
