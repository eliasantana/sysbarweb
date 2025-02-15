<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SysBarWeb - Sistema de Gerenciamento de Bares e Restaurantes</title>
</head>
<body>
    <h1>SysBarWeb - Sistema de Gerenciamento de Bares e Restaurantes</h1>

    <p>
        Bem-vindo ao SysBarWeb, um sistema abrangente projetado para otimizar a gestão de bares e restaurantes.
        Este README fornece informações detalhadas sobre as funcionalidades da API, incluindo exemplos de uso e
        endpoints disponíveis.
    </p>

    <h2>Funcionalidades</h2>

    <div>
        <h3><img src="https://img.icons8.com/color/32/000000/waiter.png" alt="Cadastro de Garçom" title="Cadastro de Garçom"> Cadastro de Garçom</h3>
        <p>
            Permite o cadastro e gerenciamento de garçons, incluindo informações como nome, CPF, etc.
        </p>
        <ul>
            <li><b>Endpoint:</b> /garcons</li>
            <li><b>Método:</b> POST</li>
            <li><b>Exemplo:</b>
                <pre>
                    <code>
                        {
                            "nome": "João da Silva",
                            "cpf": "123.456.789-00"
                        }
                    </code>
                </pre>
            </li>
        </ul>
    </div>

    <div>
        <h3><img src="https://img.icons8.com/color/32/000000/table.png" alt="Cadastro de Mesas" title="Cadastro de Mesas"> Cadastro de Mesas</h3>
        <p>
            Permite o cadastro e gerenciamento de mesas, incluindo informações como número, capacidade, etc.
        </p>
        <ul>
            <li><b>Endpoint:</b> /mesas</li>
            <li><b>Método:</b> POST</li>
            <li><b>Exemplo:</b>
                <pre>
                    <code>
                        {
                            "numero": 1,
                            "capacidade": 4
                        }
                    </code>
                </pre>
            </li>
        </ul>
    </div>

    <div>
        <h3><img src="https://img.icons8.com/color/32/000000/company.png" alt="Cadastro de Empresa" title="Cadastro de Empresa"> Cadastro de Empresa</h3>
        <p>
            Permite o cadastro e gerenciamento de informações da empresa, como nome, CNPJ, etc.
        </p>
        <ul>
            <li><b>Endpoint:</b> /empresas</li>
            <li><b>Método:</b> POST</li>
            <li><b>Exemplo:</b>
                <pre>
                    <code>
                        {
                            "nome": "Bar do Zé",
                            "cnpj": "12.345.678/0001-90"
                        }
                    </code>
                </pre>
            </li>
        </ul>
    </div>

    <div>
        <h3><img src="https://img.icons8.com/color/32/000000/warehouse.png" alt="Cadastro de Estoque" title="Cadastro de Estoque"> Cadastro de Estoque</h3>
        <p>
            Permite o cadastro e gerenciamento de produtos em estoque, incluindo informações como nome, quantidade, etc.
        </p>
        <ul>
            <li><b>Endpoint:</b> /estoque</li>
            <li><b>Método:</b> POST</li>
            <li><b>Exemplo:</b>
                <pre>
                    <code>
                        {
                            "nome": "Cerveja Brahma",
                            "quantidade": 100
                        }
                    </code>
                </pre>
            </li>
        </ul>
    </div>

    <div>
        <h3><img src="https://img.icons8.com/color/32/000000/order-food.png" alt="Cadastro de Pedido" title="Cadastro de Pedido"> Cadastro de Pedido</h3>
        <p>
            Permite o cadastro e gerenciamento de pedidos, incluindo informações como cliente, itens, etc.
        </p>
        <ul>
            <li><b>Endpoint:</b> /pedidos</li>
            <li><b>Método:</b> POST</li>
            <li><b>Exemplo:</b>
                <pre>
                    <code>
                        {
                            "cliente": "João da Silva",
                            "itens": [
                                {
                                    "produto": "Cerveja Brahma",
                                    "quantidade": 2
                                }
                            ]
                        }
                    </code>
                </pre>
            </li>
        </ul>
    </div>

    <div>
        <h3><img src="https://img.icons8.com/color/32/000000/product.png" alt="Cadastro de Produto" title="Cadastro de Produto"> Cadastro de Produto</h3>
        <p>
            Permite o cadastro e gerenciamento de produtos, incluindo informações como nome, preço, etc.
        </p>
        <ul>
            <li><b>Endpoint:</b> /produtos</li>
            <li><b>Método:</b> POST</li>
            <li><b>Exemplo:</b>
                <pre>
                    <code>
                        {
                            "nome": "Cerveja Brahma",
                            "preco": 5.00
                        }
                    </code>
                </pre>
            </li>
        </ul>
    </div>

</body>
</html>
