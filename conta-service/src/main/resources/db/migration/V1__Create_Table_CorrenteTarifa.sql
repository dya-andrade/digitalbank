CREATE TABLE `corrente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ativada` tinyint(1) DEFAULT '1',
  `cpf_cliente` varchar(255) DEFAULT NULL,
  `tipo_conta` varchar(255) DEFAULT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `ultima_tarifa` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `tarifa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `tipo_movimentacao` varchar(255) DEFAULT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `corrente_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `KF_corrente_id` (`corrente_id`),
  CONSTRAINT `KF_corrente_id` FOREIGN KEY (`corrente_id`) REFERENCES `corrente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;