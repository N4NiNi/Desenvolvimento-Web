-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 04-Mar-2023 às 16:20
-- Versão do servidor: 5.7.36
-- versão do PHP: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `dev_lanche`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `lanche`
--

DROP TABLE IF EXISTS `lanche`;
CREATE TABLE IF NOT EXISTS `lanche` (
  `Nome_lanche` varchar(20) NOT NULL,
  `Valor` float DEFAULT NULL,
  PRIMARY KEY (`Nome_lanche`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `lanche`
--

INSERT INTO `lanche` (`Nome_lanche`, `Valor`) VALUES
('X-Bacon', 15),
('X-Burger', 10),
('X-Frango', 16),
('X-Milho', 13),
('X-Salada', 12),
('X-Tudo', 20);

-- --------------------------------------------------------

--
-- Estrutura da tabela `lanchepedido`
--

DROP TABLE IF EXISTS `lanchepedido`;
CREATE TABLE IF NOT EXISTS `lanchepedido` (
  `Nome_lanche` varchar(20) DEFAULT NULL,
  `Id_pedido` int(11) NOT NULL,
  `observacao` varchar(50) DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  KEY `nome_lanche_fk` (`Nome_lanche`),
  KEY `id_pedido_fk` (`Id_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `lanchepedido`
--

INSERT INTO `lanchepedido` (`Nome_lanche`, `Id_pedido`, `observacao`, `quantidade`) VALUES
('X-Bacon', 1, 'Bastante bacon!', 2),
('X-Burger', 1, 'dobro de queijo!', 1),
('X-Salada', 1, 'sem cebola', 4),
('X-Bacon', 2, '1 com cebola e 2 sem.', 3),
('X-Milho', 2, 'mandar com maionese', 6),
('X-Bacon', 3, '', 1),
('X-Burger', 3, '', 3),
('X-Frango', 3, '', 2),
('X-Bacon', 4, '', 1),
('X-Burger', 4, '', 1),
('X-Frango', 4, '', 1),
('X-Tudo', 4, 'bastante calabresa', 2),
('X-Bacon', 5, '', 1),
('X-Burger', 5, '', 1),
('X-Frango', 5, '', 1),
('X-Milho', 5, '', 1),
('X-Salada', 5, '', 1),
('X-Tudo', 5, '', 1),
('X-Bacon', 6, '', 1),
('X-Burger', 6, '', 1),
('X-Frango', 6, 'sem ketchup', 1),
('X-Milho', 6, '', 1),
('X-Burger', 7, '', 1),
('X-Frango', 8, '', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido`
--

DROP TABLE IF EXISTS `pedido`;
CREATE TABLE IF NOT EXISTS `pedido` (
  `Id_pedido` int(11) NOT NULL AUTO_INCREMENT,
  `Valor_Total` float DEFAULT NULL,
  `Cliente_CPF` varchar(11) DEFAULT NULL,
  `Data_pedido` datetime DEFAULT CURRENT_TIMESTAMP,
  `endereco` varchar(80) NOT NULL,
  PRIMARY KEY (`Id_pedido`),
  KEY `FK_Pedido_2` (`Cliente_CPF`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pedido`
--

INSERT INTO `pedido` (`Id_pedido`, `Valor_Total`, `Cliente_CPF`, `Data_pedido`, `endereco`) VALUES
(1, 88, '42343156799', '2023-03-04 13:14:16', 'Rua Finlândia, 143 '),
(2, 123, '53275378677', '2023-03-04 13:15:24', 'Rua Brasil, 203'),
(3, 77, '32454289766', '2023-03-04 13:15:40', 'Rua Finlândia, 143 '),
(4, 81, '76532189788', '2023-03-04 13:16:12', 'Rua Italia, 67'),
(5, 86, '33467856789', '2023-03-04 13:16:47', 'Rua Grecia, 72'),
(6, 54, '44236765222', '2023-03-04 13:17:32', 'Rua Holanda, 61'),
(7, 10, '99878667634', '2023-03-04 13:18:50', 'Rua Alemanha, 71'),
(8, 32, '42343156799', '2023-03-04 13:19:41', 'Rua Brasil, 203');

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `lanchepedido`
--
ALTER TABLE `lanchepedido`
  ADD CONSTRAINT `id_pedido_fk` FOREIGN KEY (`Id_pedido`) REFERENCES `pedido` (`Id_pedido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `nome_lanche_fk` FOREIGN KEY (`Nome_lanche`) REFERENCES `lanche` (`Nome_lanche`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
