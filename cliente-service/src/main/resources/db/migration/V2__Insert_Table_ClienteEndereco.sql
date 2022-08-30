INSERT INTO endereco (id, bairro, cep, localidade, logradouro, uf) VALUES
(1, 'Jardim do Centauro', '06815-530', 'Embu das Artes', 'Rua Andrade, 158', 'SP'),
(2, 'Casa Blanca', '06814-450', 'Embu das Artes', 'Rua Joao, 158', 'SP');

INSERT INTO cliente
(id, ativado, cpf, email, nome_completo, telefone, endereco_id) VALUES
(1, 1, '478.430.358-23', 'ana.andrade@gmail.com', 'Ana Andrade', '(11)95885-2417', 1),
(2, 1, '455.587.358-23', 'joao.andrade@gmail.com', 'João Andrade', '(11)95485-2417', 2);
