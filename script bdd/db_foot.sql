-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 11 fév. 2025 à 11:19
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `db_foot`
--

-- --------------------------------------------------------

--
-- Structure de la table `consultations`
--

CREATE TABLE `consultations` (
  `id` int(20) NOT NULL,
  `details` text NOT NULL,
  `resultats` text DEFAULT NULL,
  `traitements` text DEFAULT NULL,
  `idjoueurs` int(20) NOT NULL,
  `idmedecin` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `contrats`
--

CREATE TABLE `contrats` (
  `id` int(20) NOT NULL,
  `type` int(11) NOT NULL,
  `dateDebut` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `dateFin` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `salaire` float NOT NULL,
  `primes` float NOT NULL,
  `devise` tinytext NOT NULL,
  `idJoueur` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `donnees_medicales`
--

CREATE TABLE `donnees_medicales` (
  `id` int(20) NOT NULL,
  `allergies` varchar(255) NOT NULL,
  `vaccins` text NOT NULL,
  `idConsultation` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `donnees_physiques`
--

CREATE TABLE `donnees_physiques` (
  `id` int(11) NOT NULL,
  `vitesseMax` float NOT NULL,
  `distanceParcourue` float NOT NULL,
  `nombreSprints` int(4) NOT NULL,
  `vo2max` float NOT NULL,
  `acceleration` float NOT NULL,
  `puissance` float NOT NULL,
  `idJoueur` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `donnees_psychologiques`
--

CREATE TABLE `donnees_psychologiques` (
  `id` int(20) NOT NULL,
  `profilPsychologique` text NOT NULL,
  `suiviMental` text NOT NULL,
  `strategiesPreparation` text NOT NULL,
  `historiqueConsultations` text NOT NULL,
  `idJoueurs` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `joueurs`
--

CREATE TABLE `joueurs` (
  `id` int(20) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `dateNaissance` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `nationalite` varchar(255) NOT NULL,
  `numeroLicence` int(10) NOT NULL,
  `numeroMaillot` int(10) NOT NULL,
  `position` varchar(255) NOT NULL,
  `taille` float NOT NULL,
  `poids` float NOT NULL,
  `piedFort` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `performances`
--

CREATE TABLE `performances` (
  `id` int(20) NOT NULL,
  `buts` int(11) NOT NULL,
  `date_match` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `passes_decisives` int(11) NOT NULL,
  `tirs_cadres` int(20) NOT NULL,
  `duel_gagnes` int(20) NOT NULL,
  `dribbles_reussis` int(20) NOT NULL,
  `xg` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `mdp` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `consultations`
--
ALTER TABLE `consultations`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `contrats`
--
ALTER TABLE `contrats`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `donnees_medicales`
--
ALTER TABLE `donnees_medicales`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `donnees_physiques`
--
ALTER TABLE `donnees_physiques`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `donnees_psychologiques`
--
ALTER TABLE `donnees_psychologiques`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `joueurs`
--
ALTER TABLE `joueurs`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `performances`
--
ALTER TABLE `performances`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `consultations`
--
ALTER TABLE `consultations`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `contrats`
--
ALTER TABLE `contrats`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `donnees_medicales`
--
ALTER TABLE `donnees_medicales`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `donnees_physiques`
--
ALTER TABLE `donnees_physiques`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `donnees_psychologiques`
--
ALTER TABLE `donnees_psychologiques`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `joueurs`
--
ALTER TABLE `joueurs`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `performances`
--
ALTER TABLE `performances`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
