# Digital Bank
ANDAMENTO - Projeto pessoal sobre a criação de um banco digital em API RESTful e Microservices.

* O projeto é separado em 3 services, um service para dados dos clientes, outro service para as contas dos clientes do tipo poupança e corrente e outro service para transações realizadas nas contas.

* Nesta imagem estou tentando criar um rendimento e lançar na poupança do cliente, mas como o cliente já teve um rendimento lançado em menos de um mês, não foi autorizado.

![image](https://user-images.githubusercontent.com/101612046/187562309-12b80616-5d94-4b16-a8b1-a4c3cf692107.png)

</br>

* Json de listagem em pages de todas as contas cadastradas. Além disso, o Json traz as listas de rendimentos e tarifas das contas. 

```json
{
    "_embedded": {
        "contaCompletaVOList": [
            {
                "corrente": {
                    "cpfCliente": "455.587.358-23",
                    "valor": 700.00,
                    "tipoConta": "Universitária",
                    "tarifas": [],
                    "ultimaTarifa": null
                },
                "poupanca": {
                    "cpfCliente": "455.587.358-23",
                    "valor": 700.00,
                    "tipoConta": "Universitária",
                    "rendimentos": [],
                    "ultimoRendimento": null
                },
                "_links": {
                    "self": {
                        "href": "http://host.docker.internal:8100/conta-service/v1/455.587.358-23"
                    }
                }
            },
            {
                "corrente": {
                    "cpfCliente": "478.430.358-23",
                    "valor": 490.00,
                    "tipoConta": "Comum",
                    "tarifas": [
                        {
                            "percentual": 2.00,
                            "data": "2022-08-30",
                            "tipoMovimentacao": "Mensal"
                        }
                    ],
                    "ultimaTarifa": "2022-08-30"
                },
                "poupanca": {
                    "cpfCliente": "478.430.358-23",
                    "valor": 530.00,
                    "tipoConta": "Comum",
                    "rendimentos": [
                        {
                            "percentual": 6.00,
                            "data": "2022-08-30",
                            "tipoMovimentacao": "Anual"
                        }
                    ],
                    "ultimoRendimento": "2022-08-30"
                },
                "_links": {
                    "self": {
                        "href": "http://host.docker.internal:8100/conta-service/v1/478.430.358-23"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://host.docker.internal:8100/conta-service/v1?page=0&size=5&direction=asc"
        }
    },
    "page": {
        "size": 5,
        "totalElements": 2,
        "totalPages": 1,
        "number": 0
    }
}
```
</br>

* Aqui foi aplicado princípios de SOLID e Design Patterns

![image](https://user-images.githubusercontent.com/101612046/187564830-3fb9eb85-92b0-4778-870d-226e68df45ad.png)
![image](https://user-images.githubusercontent.com/101612046/187565556-b52d4b0e-c7ce-4743-b78c-0a38cc740862.png)
![image](https://user-images.githubusercontent.com/101612046/187565721-16609dff-4ca8-4405-aab0-b8041ed6c870.png)



