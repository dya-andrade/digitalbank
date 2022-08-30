CREATE TABLE `poupanca` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ativada` tinyint(1) DEFAULT '1',
  `cpf_cliente` varchar(255) DEFAULT NULL,
  `tipo_conta` varchar(255) DEFAULT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `ultimo_rendimento` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `rendimento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `tipo_movimentacao` varchar(255) DEFAULT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `poupanca_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_poupanca_id` (`poupanca_id`),
  CONSTRAINT `FK_poupanca_id` FOREIGN KEY (`poupanca_id`) REFERENCES `poupanca` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3