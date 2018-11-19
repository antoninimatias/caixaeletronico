# Caixa Eletrônico - API

**Tecnologias usadas para desenvolver a Api:**

>Spring Data Jpa
>Spring Boot
>DevTools
>Maven
>Migrações FlyWay (Banco de Dados)
>MySql

**Instruções para Uso**

>Faça o download da aplicação e importe como maven project. Após isso faça uso de um dos endpoint's listados abaixo

**Endpoints**

| Descrição | Path | Método |
| ------ | ------ | ------ |
| Criação de uma conta | `localhost:8080/conta` | PUT |
| Depósito em uma conta | `localhost:8080/conta/idConta/depositar`  | PUT |
| Ver Saldo de uma conta | `localhost:8080/conta/idConta/saldo` | GET |
| Saque em uma conta | `localhost:8080/conta/idConta/sacar` | PUT |
| Bloqueio de uma conta | `localhost:8080/conta/idConta/ativar` | PUT |
| Extrato de uma conta | `localhost:8080/conta/idConta/gerarExtrato` | GET|

**Banco de Dados**

**Obs.:** Para a criação de uma conta você deverá informar o Json abaixo:

```json
{
"pessoa":{
	"idPessoa": 5
},
"saldo": "3000.00",
"limiteSaqueDiario": "1500.00",
"flagAtivo": 1,
"tipoConta": "CORRENTE",
"dataCriacao": "2018-11-06"
}
```