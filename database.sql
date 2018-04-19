-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 19 avr. 2018 à 10:55
-- Version du serveur :  5.7.19
-- Version de PHP :  7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `db_poetrysandwich`
--
CREATE DATABASE IF NOT EXISTS `db_poetrysandwich` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `db_poetrysandwich`;

-- --------------------------------------------------------

--
-- Structure de la table `tb_comment`
--

DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE IF NOT EXISTS `tb_comment` (
  `pk_comment` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fk_user` int(11) NOT NULL,
  `fk_poem` int(11) NOT NULL,
  PRIMARY KEY (`pk_comment`),
  KEY `fk_poem` (`fk_poem`),
  KEY `fk_user` (`fk_user`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `tb_comment`
--

INSERT INTO `tb_comment` (`pk_comment`, `content`, `creation_date`, `fk_user`, `fk_poem`) VALUES
(2, 'Comslol', '2018-04-04 14:33:11', 3, 13),
(3, 'Bonjour j\'adore ce poème', '2018-04-04 14:33:11', 3, 13),
(4, 'C\'est bien', '2018-04-04 14:33:11', 3, 12),
(5, 'Salut', '2018-04-04 14:33:11', 8, 2),
(6, 'J\'aime bien celui-ci.', '2018-04-04 14:44:49', 8, 13),
(7, 'Pourquoi pas weshhh', '2018-04-04 15:50:22', 8, 13),
(9, 'Foo', '2018-04-04 15:53:39', 3, 13),
(12, 'kek', '2018-04-04 17:30:23', 3, 14),
(13, 'Olol', '2018-04-04 17:32:50', 1, 14),
(14, 'Darwin', '2018-04-04 17:35:17', 3, 14),
(15, 'Test', '2018-04-13 13:48:28', 3, 20),
(16, 'hola', '2018-04-13 13:49:48', 3, 16);

-- --------------------------------------------------------

--
-- Structure de la table `tb_poem`
--

DROP TABLE IF EXISTS `tb_poem`;
CREATE TABLE IF NOT EXISTS `tb_poem` (
  `pk_poem` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fk_user` int(11) NOT NULL,
  `validated` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`pk_poem`),
  KEY `fk_user` (`fk_user`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `tb_poem`
--

INSERT INTO `tb_poem` (`pk_poem`, `title`, `content`, `creation_date`, `fk_user`, `validated`) VALUES
(1, 'Mon amour des champs', 'J\'adore les champs,\r\nJe les fume\r\nDe temps en temps,\r\nPar temps de brume', '2012-02-02 13:12:12', 3, 'd'),
(2, 'Neuch', 'Salut edited', '2018-04-02 10:33:40', 1, 'v'),
(4, 'Julien le saint', 'Il s\'appelait Julien, \r\nC\'était un saint.\r\nLe plus beau des saints.', '2018-04-02 15:15:33', 1, 'd'),
(5, 'Stairway', 'To heaven', '2018-04-02 15:46:33', 3, 'd'),
(6, 'Olol', 'okok', '2018-04-03 14:14:33', 3, 'p'),
(7, 'Test2', 'okok', '2018-04-03 14:15:01', 3, 'p'),
(8, 'Marcel Prinprenelle', 'okokpopo', '2018-04-03 14:27:48', 3, 'v'),
(9, 'Brigitte', 'okok', '2018-04-03 14:48:02', 1, 'd'),
(10, 'Croco', 'koko', '2018-04-03 14:51:25', 8, 'd'),
(11, 'jojo', 'kiko', '2018-04-03 14:55:13', 1, 'd'),
(12, 'Test', 'Mon beau test', '2018-04-03 14:55:25', 8, 'v'),
(13, 'Mercredi', 'Mercredi c\'est gentil', '2018-04-04 10:28:29', 3, 'v'),
(14, 'TestHTML', '<b>ahah</b> okok\r\nça marche ?', '2018-04-04 11:03:29', 3, 'v'),
(15, 'Jean marie le pen', 'Je l\'aime et je l\'aimerais', '2018-04-04 17:18:54', 3, 'p'),
(16, 'Darwin', 'Bonjour ls singes', '2018-04-04 17:35:24', 3, 'v'),
(19, 'Hello', 'MyPoemContent', '2018-04-13 13:35:14', 3, 'p'),
(20, 'Dawn', 'of war', '2018-04-13 13:40:30', 3, 'p'),
(21, 'Hola', 'Que tale', '2018-04-13 13:46:11', 3, 'p'),
(22, 'Franklin', 'Hé c\'est Franklin!', '2018-04-13 13:57:48', 3, 'p'),
(23, 'Bonjour', 'copain', '2018-04-13 14:24:55', 3, 'p');

-- --------------------------------------------------------

--
-- Structure de la table `tb_poem_tag`
--

DROP TABLE IF EXISTS `tb_poem_tag`;
CREATE TABLE IF NOT EXISTS `tb_poem_tag` (
  `pk_fk_poem` int(11) NOT NULL,
  `pk_fk_tag` int(11) NOT NULL,
  PRIMARY KEY (`pk_fk_poem`,`pk_fk_tag`),
  KEY `pk_fk_poem` (`pk_fk_poem`),
  KEY `pk_fk_tag` (`pk_fk_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `tb_poem_tag`
--

INSERT INTO `tb_poem_tag` (`pk_fk_poem`, `pk_fk_tag`) VALUES
(11, 1),
(12, 3),
(12, 7),
(13, 1),
(13, 3),
(14, 3),
(14, 8),
(15, 1),
(15, 5),
(15, 6),
(16, 8),
(16, 9),
(16, 12),
(19, 1),
(19, 2),
(20, 2),
(20, 5),
(21, 1),
(22, 6),
(23, 5),
(23, 7);

-- --------------------------------------------------------

--
-- Structure de la table `tb_role`
--

DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE IF NOT EXISTS `tb_role` (
  `pk_role` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`pk_role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `tb_role`
--

INSERT INTO `tb_role` (`pk_role`, `name`) VALUES
(1, 'ADMIN'),
(2, 'AUTHOR'),
(3, 'EDITOR');

-- --------------------------------------------------------

--
-- Structure de la table `tb_tag`
--

DROP TABLE IF EXISTS `tb_tag`;
CREATE TABLE IF NOT EXISTS `tb_tag` (
  `pk_tag` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`pk_tag`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `tb_tag`
--

INSERT INTO `tb_tag` (`pk_tag`, `name`) VALUES
(1, 'Amour'),
(2, 'Gloire'),
(3, 'Beauté'),
(5, 'Orange'),
(6, 'Bleu'),
(7, 'Rouge'),
(8, 'Noir'),
(9, 'Jaune'),
(10, 'Vert'),
(11, 'Violet'),
(12, 'Pourpre'),
(13, 'Cyan');

-- --------------------------------------------------------

--
-- Structure de la table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE IF NOT EXISTS `tb_user` (
  `pk_user` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `fk_role` int(11) DEFAULT NULL,
  `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pk_user`),
  UNIQUE KEY `mail` (`mail`),
  UNIQUE KEY `username` (`username`),
  KEY `fk_role` (`fk_role`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `tb_user`
--

INSERT INTO `tb_user` (`pk_user`, `username`, `password`, `mail`, `fk_role`, `creation_date`) VALUES
(1, 'admin', '3a5088295708d3304f06de0499b9243bfbd68d14878615c531f0e346f47b389d', 'okok', 1, '2012-02-02 13:12:12'),
(3, 'steve', '3a5088295708d3304f06de0499b9243bfbd68d14878615c531f0e346f47b389d', 'steve@gmail.com', 2, '2018-02-22 10:42:17'),
(8, 'anthony', '3a5088295708d3304f06de0499b9243bfbd68d14878615c531f0e346f47b389d', 'antho@fleury.com', 3, '2018-03-22 10:59:01');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `tb_user_tb_role`
-- (Voir ci-dessous la vue réelle)
--
DROP VIEW IF EXISTS `tb_user_tb_role`;
CREATE TABLE IF NOT EXISTS `tb_user_tb_role` (
`username` varchar(30)
,`password` varchar(100)
,`name` varchar(30)
);

-- --------------------------------------------------------

--
-- Structure de la vue `tb_user_tb_role`
--
DROP TABLE IF EXISTS `tb_user_tb_role`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `tb_user_tb_role`  AS  select `tb_user`.`username` AS `username`,`tb_user`.`password` AS `password`,`tb_role`.`name` AS `name` from (`tb_user` join `tb_role`) where (`tb_user`.`fk_role` = `tb_role`.`pk_role`) ;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `tb_comment`
--
ALTER TABLE `tb_comment`
  ADD CONSTRAINT `tb_comment_ibfk_1` FOREIGN KEY (`fk_user`) REFERENCES `tb_user` (`pk_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_comment_ibfk_2` FOREIGN KEY (`fk_poem`) REFERENCES `tb_poem` (`pk_poem`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `tb_poem`
--
ALTER TABLE `tb_poem`
  ADD CONSTRAINT `tb_poem_ibfk_1` FOREIGN KEY (`fk_user`) REFERENCES `tb_user` (`pk_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `tb_poem_tag`
--
ALTER TABLE `tb_poem_tag`
  ADD CONSTRAINT `tb_poem_tag_ibfk_1` FOREIGN KEY (`pk_fk_tag`) REFERENCES `tb_tag` (`pk_tag`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_poem_tag_ibfk_2` FOREIGN KEY (`pk_fk_poem`) REFERENCES `tb_poem` (`pk_poem`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `tb_user`
--
ALTER TABLE `tb_user`
  ADD CONSTRAINT `tb_user_ibfk_1` FOREIGN KEY (`fk_role`) REFERENCES `tb_role` (`pk_role`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
