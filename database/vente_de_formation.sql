-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mer. 30 sep. 2026 à 07:15
-- Version du serveur : 11.7.1-MariaDB
-- Version de PHP : 8.5.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `vente_de_formation`
--
CREATE DATABASE IF NOT EXISTS `vente_de_formation` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci;
USE `vente_de_formation`;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `number_phone` varchar(20) DEFAULT NULL,
  `number_phone_prefix` varchar(5) DEFAULT NULL,
  `id_user` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_client`),
  KEY `fk_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id_client`, `last_name`, `first_name`, `email`, `address`, `number_phone`, `number_phone_prefix`, `id_user`) VALUES
(1, 'viande', 'pain', 'pain@gamil.com', '13 rue de pain', '0655675741', '33', 1),
(2, 'Test', 'Samuel', 'samuel@gmail.com', '13 rue', '0252545685', '33', 1);

-- --------------------------------------------------------

--
-- Structure de la table `contain`
--

DROP TABLE IF EXISTS `contain`;
CREATE TABLE IF NOT EXISTS `contain` (
  `id_formation` int(11) NOT NULL,
  `id_order` int(11) NOT NULL,
  `unit_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_formation`,`id_order`),
  KEY `id_order` (`id_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `contain`
--

INSERT INTO `contain` (`id_formation`, `id_order`, `unit_price`) VALUES
(1, 4, 102.00),
(3, 4, 120.00);

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

DROP TABLE IF EXISTS `formation`;
CREATE TABLE IF NOT EXISTS `formation` (
  `id_formation` int(11) NOT NULL AUTO_INCREMENT,
  `title_formation` varchar(50) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `number_of_days` int(11) DEFAULT NULL,
  `is_remote` tinyint(1) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `is_available` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_formation`),
  UNIQUE KEY `title_formation` (`title_formation`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`id_formation`, `title_formation`, `description`, `number_of_days`, `is_remote`, `price`, `is_available`) VALUES
(1, 'Java', 'Java SE 8 : Syntaxe & POO', 20, 0, 102.00, 1),
(2, 'Java avancé', 'Exceptions, fichiers, Jdbc, thread...', 20, 0, 150.00, 1),
(3, 'Spring', 'Spring Core/Mvc/Security', 20, 0, 120.00, 1),
(4, 'Php frameworks', 'Symphony', 15, 0, 50.00, 1),
(5, 'C#', 'DotNet Core', 20, 1, 90.00, 1);

-- --------------------------------------------------------

--
-- Structure de la table `order_`
--

DROP TABLE IF EXISTS `order_`;
CREATE TABLE IF NOT EXISTS `order_` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `date_commande` datetime DEFAULT NULL,
  `id_user` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  PRIMARY KEY (`id_order`),
  KEY `id_user` (`id_user`),
  KEY `id_client` (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `order_`
--

INSERT INTO `order_` (`id_order`, `date_commande`, `id_user`, `id_client`) VALUES
(4, '2026-09-30 00:00:00', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `user_`
--

DROP TABLE IF EXISTS `user_`;
CREATE TABLE IF NOT EXISTS `user_` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `hashed_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Déchargement des données de la table `user_`
--

INSERT INTO `user_` (`id_user`, `login`, `hashed_password`) VALUES
(1, 'Samuel', '117102116117');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`id_user`) REFERENCES `user_` (`id_user`);

--
-- Contraintes pour la table `contain`
--
ALTER TABLE `contain`
  ADD CONSTRAINT `contain_ibfk_1` FOREIGN KEY (`id_formation`) REFERENCES `formation` (`id_formation`),
  ADD CONSTRAINT `contain_ibfk_2` FOREIGN KEY (`id_order`) REFERENCES `order_` (`id_order`);

--
-- Contraintes pour la table `order_`
--
ALTER TABLE `order_`
  ADD CONSTRAINT `order__ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user_` (`id_user`),
  ADD CONSTRAINT `order__ibfk_2` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
