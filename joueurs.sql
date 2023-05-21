-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 21 mai 2023 à 03:12
-- Version du serveur : 10.4.21-MariaDB
-- Version de PHP : 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `joueurs`
--

-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

CREATE TABLE `joueur` (
  `id` int(11) NOT NULL,
  `numJoueur` int(11) NOT NULL,
  `poste` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `nbBut` int(11) NOT NULL,
  `nbExperience` int(11) NOT NULL,
  `club` varchar(255) NOT NULL,
  `equipeNational` varchar(255) NOT NULL,
  `utilisateurId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `joueur`
--

INSERT INTO `joueur` (`id`, `numJoueur`, `poste`, `nom`, `prenom`, `nbBut`, `nbExperience`, `club`, `equipeNational`, `utilisateurId`) VALUES
(3, 17, 'AIG', 'Mane', 'Sadio', 17, 11, 'Bayern Munich', 'Senegal', 1),
(4, 8, 'MO', 'Fernandes', 'Bruno', 9, 11, 'Manchester United', 'Portugal', 1),
(5, 12, 'AID', 'Kolmuani', 'Randal', 3, 2, 'Lerverkusen', 'France', 1),
(7, 1, 'Gardien', 'Smith', 'John', 0, 5, 'Arsenal', 'Angleterre', 1),
(8, 2, 'Défenseur', 'Jones', 'Michael', 2, 10, 'Manchester United', 'Angleterre', 1),
(9, 3, 'Milieu', 'Johnson', 'Emma', 3, 8, 'Liverpool', 'Angleterre', 1),
(10, 4, 'Attaquant', 'Williams', 'David', 5, 15, 'Chelsea', 'Angleterre', 1),
(11, 26, 'Attaquant', 'Kali', 'David', 50, 2, 'JZK', 'Calom', 1),
(12, 4, 'Attaquant', 'Williams', 'David', 5, 15, 'Roma', 'Angleterre', 1);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `pseudo` varchar(255) NOT NULL,
  `genre` varchar(255) NOT NULL,
  `motDePasse` varchar(255) NOT NULL,
  `estConnecte` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `email`, `nom`, `prenom`, `pseudo`, `genre`, `motDePasse`, `estConnecte`) VALUES
(1, 'kali@kali.com', 'Kali', 'Kali', 'KaliNO', '', '553225726ea919e57e3d61bcaf4a1b24', 0),
(2, 'vil@turn.com', 'Vil', 'Turn', 'VT', '', '47acd14735103aa3216884843ff8b484', 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `joueur`
--
ALTER TABLE `joueur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `utilisateurId` (`utilisateurId`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `joueur`
--
ALTER TABLE `joueur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `joueur`
--
ALTER TABLE `joueur`
  ADD CONSTRAINT `joueur_ibfk_1` FOREIGN KEY (`utilisateurId`) REFERENCES `utilisateur` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
