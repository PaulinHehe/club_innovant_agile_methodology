-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mer. 12 fév. 2025 à 21:45
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.4

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

--
-- Déchargement des données de la table `consultations`
--

INSERT INTO `consultations` (`id`, `details`, `resultats`, `traitements`, `idjoueurs`, `idmedecin`) VALUES
(1, 'Douleur au genou droit', 'Entorse légère', 'Repos 2 semaines', 1, 1),
(2, 'Fatigue musculaire', 'Examen normal', 'Hydratation et repos', 2, 2),
(3, 'Fracture du poignet', 'Plâtre 6 semaines', 'Immobilisation et rééducation', 3, 3),
(4, 'Tendinite', 'Inflammation détectée', 'Physiothérapie recommandée', 4, 1),
(5, 'Blessure aux ischio-jambiers', 'Déchirure partielle', 'Repos et renforcement musculaire', 5, 2),
(6, 'Douleur au genou droit', 'Entorse légère', 'Repos 2 semaines', 1, 1),
(7, 'Fatigue musculaire', 'Examen normal', 'Hydratation et repos', 2, 2),
(8, 'Fracture du poignet', 'Plâtre 6 semaines', 'Immobilisation et rééducation', 3, 3),
(9, 'Tendinite', 'Inflammation détectée', 'Physiothérapie recommandée', 4, 1);

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

--
-- Déchargement des données de la table `contrats`
--

INSERT INTO `contrats` (`id`, `type`, `dateDebut`, `dateFin`, `salaire`, `primes`, `devise`, `idJoueur`) VALUES
(1, 1, '2022-06-30 22:00:00', '2026-06-29 22:00:00', 5000000, 1000000, 'EUR', 1),
(2, 1, '2023-06-30 22:00:00', '2027-06-29 22:00:00', 4000000, 800000, 'EUR', 2),
(3, 1, '2021-06-30 22:00:00', '2025-06-29 22:00:00', 6000000, 1200000, 'EUR', 3),
(4, 1, '2020-06-30 22:00:00', '2024-06-29 22:00:00', 7000000, 1500000, 'EUR', 4),
(5, 1, '2021-06-30 22:00:00', '2025-06-29 22:00:00', 3500000, 500000, 'EUR', 5),
(6, 1, '2022-06-30 22:00:00', '2026-06-29 22:00:00', 5000000, 1000000, 'EUR', 1),
(7, 1, '2023-06-30 22:00:00', '2027-06-29 22:00:00', 4000000, 800000, 'EUR', 2),
(8, 1, '2021-06-30 22:00:00', '2025-06-29 22:00:00', 6000000, 1200000, 'EUR', 3),
(9, 1, '2020-06-30 22:00:00', '2024-06-29 22:00:00', 7000000, 1500000, 'EUR', 4),
(10, 1, '2021-06-30 22:00:00', '2025-06-29 22:00:00', 3500000, 500000, 'EUR', 5),
(11, 1, '2025-02-12 20:29:39', '0000-00-00 00:00:00', 2003000, 2000, 'EUR', 2);

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

--
-- Déchargement des données de la table `donnees_medicales`
--

INSERT INTO `donnees_medicales` (`id`, `allergies`, `vaccins`, `idConsultation`) VALUES
(1, 'Pollen', 'Tétanos, Hépatite B', 1),
(2, 'Aucune', 'COVID-19, Grippe', 2),
(3, 'Arachides', 'Tétanos, Rougeole', 3),
(4, 'Aucune', 'Hépatite B, Grippe', 4),
(5, 'Gluten', 'COVID-19, Hépatite A', 5),
(6, 'Poils de chat', 'Tétanos, Hépatite B', 1),
(7, 'Fruits de mer', 'COVID-19, Varicelle', 2),
(8, 'Aucune', 'Rougeole, Polio', 3),
(9, 'Lait', 'Grippe, COVID-19', 4),
(10, 'Aucune', 'Tétanos, Tuberculose', 5),
(11, 'Pollen', 'Tétanos, Hépatite B', 1),
(12, 'Aucune', 'COVID-19, Grippe', 2),
(13, 'Arachides', 'Tétanos, Rougeole', 3),
(14, 'Aucune', 'Hépatite B, Grippe', 4),
(15, 'Gluten', 'COVID-19, Hépatite A', 5),
(16, 'Poils de chat', 'Tétanos, Hépatite B', 1),
(17, 'Fruits de mer', 'COVID-19, Varicelle', 2),
(18, 'Aucune', 'Rougeole, Polio', 3),
(19, 'Lait', 'Grippe, COVID-19', 4),
(20, 'Aucune', 'Tétanos, Tuberculose', 5);

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

--
-- Déchargement des données de la table `donnees_physiques`
--

INSERT INTO `donnees_physiques` (`id`, `vitesseMax`, `distanceParcourue`, `nombreSprints`, `vo2max`, `acceleration`, `puissance`, `idJoueur`) VALUES
(1, 35.2, 10.5, 25, 60.2, 4.1, 700, 1),
(2, 32.8, 9.3, 18, 58.7, 3.8, 650, 2),
(3, 34.5, 11, 22, 61.5, 4.3, 720, 3),
(4, 33.1, 8.9, 20, 57.4, 3.5, 680, 4),
(5, 36, 12.2, 30, 62.8, 4.5, 750, 5),
(6, 35.2, 10.5, 25, 60.2, 4.1, 700, 1),
(7, 32.8, 9.3, 18, 58.7, 3.8, 650, 2),
(8, 34.5, 11, 22, 61.5, 4.3, 720, 3),
(9, 33.1, 8.9, 20, 57.4, 3.5, 680, 4),
(10, 36, 12.2, 30, 62.8, 4.5, 750, 5);

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

--
-- Déchargement des données de la table `donnees_psychologiques`
--

INSERT INTO `donnees_psychologiques` (`id`, `profilPsychologique`, `suiviMental`, `strategiesPreparation`, `historiqueConsultations`, `idJoueurs`) VALUES
(1, 'Leader naturel, optimiste', 'Aucun trouble détecté', 'Méditation avant match', 'Consultation en 2024', 1),
(2, 'Anxieux sous pression', 'Besoin de suivi', 'Relaxation et gestion du stress', 'Suivi régulier', 2),
(3, 'Très compétitif, mais impulsif', 'Risque d’épuisement', 'Coaching mental intensif', 'Deux séances en 2023', 3),
(4, 'Calme et réfléchi', 'Stable mentalement', 'Visualisation des scénarios de match', 'Aucune consultation', 4),
(5, 'Manque de confiance en soi', 'En cours de thérapie', 'Exercices de motivation et encouragements', 'Début de suivi en 2025', 5),
(6, 'Leader naturel, optimiste', 'Aucun trouble détecté', 'Méditation avant match', 'Consultation en 2024', 1),
(7, 'Anxieux sous pression', 'Besoin de suivi', 'Relaxation et gestion du stress', 'Suivi régulier', 2),
(8, 'Très compétitif, mais impulsif', 'Risque d’épuisement', 'Coaching mental intensif', 'Deux séances en 2023', 3),
(9, 'Calme et réfléchi', 'Stable mentalement', 'Visualisation des scénarios de match', 'Aucune consultation', 4),
(10, 'Manque de confiance en soi', 'En cours de thérapie', 'Exercices de motivation et encouragements', 'Début de suivi en 2025', 5);

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

--
-- Déchargement des données de la table `joueurs`
--

INSERT INTO `joueurs` (`id`, `nom`, `prenom`, `dateNaissance`, `nationalite`, `numeroLicence`, `numeroMaillot`, `position`, `taille`, `poids`, `piedFort`) VALUES
(1, 'Mbappé', 'Kylian', '1998-12-19 23:00:00', 'France', 123456, 7, 'Attaquant', 1.78, 73, 'Droitier'),
(2, 'Messi', 'Lionel', '1987-06-23 22:00:00', 'Argentine', 789012, 10, 'Attaquant', 1.7, 72, 'Gaucher'),
(3, 'Ronaldo', 'Cristiano', '1985-02-04 23:00:00', 'Portugal', 345678, 7, 'Attaquant', 1.87, 83, 'Droitier'),
(4, 'Neymar', 'Junior', '1992-02-04 23:00:00', 'Brésil', 901234, 10, 'Ailier', 1.75, 68, 'Droitier'),
(5, 'De Bruyne', 'Kevin', '1991-06-27 22:00:00', 'Belgique', 567890, 17, 'Milieu', 1.81, 70, 'Droitier'),
(6, 'Modric', 'Luka', '1985-09-08 22:00:00', 'Croatie', 111222, 10, 'Milieu', 1.72, 66, 'Droitier'),
(7, 'Kanté', 'N’Golo', '1991-03-28 23:00:00', 'France', 333444, 7, 'Milieu', 1.68, 65, 'Droitier'),
(8, 'Van Dijk', 'Virgil', '1991-07-07 22:00:00', 'Pays-Bas', 555666, 4, 'Défenseur', 1.93, 92, 'Droitier'),
(9, 'Ramos', 'Sergio', '1986-03-29 23:00:00', 'Espagne', 777888, 4, 'Défenseur', 1.84, 82, 'Droitier'),
(10, 'Alisson', 'Becker', '1992-10-01 22:00:00', 'Brésil', 999000, 1, 'Gardien', 1.91, 86, 'Droitier'),
(11, 'Ter Stegene', 'Marc-André', '1992-04-29 22:00:00', 'Allemagne', 112233, 1, 'Gardien', 1.87, 85, 'Droitier'),
(12, 'Hakimi', 'Achraf', '1998-11-03 23:00:00', 'Maroc', 223344, 2, 'Défenseur', 1.81, 73, 'Droitier'),
(13, 'Marquinhos', 'Marcos', '1994-05-13 22:00:00', 'Brésil', 334455, 5, 'Défenseur', 1.83, 79, 'Droitier'),
(14, 'Robertson', 'Andrew', '1994-03-10 23:00:00', 'Écosse', 445566, 3, 'Défenseur', 1.78, 69, 'Gaucher'),
(15, 'Lewandowski', 'Robert', '1988-08-20 22:00:00', 'Pologne', 556677, 9, 'Attaquant', 1.85, 81, 'Droitier'),
(16, 'Griezmann', 'Antoine', '1991-03-20 23:00:00', 'France', 667788, 7, 'Attaquant', 1.76, 69, 'Gaucher'),
(17, 'Benzema', 'Karim', '1987-12-18 23:00:00', 'France', 778899, 9, 'Attaquant', 1.85, 81, 'Droitier'),
(18, 'Salah', 'Mohamed', '1992-06-14 22:00:00', 'Égypte', 889900, 11, 'Ailier', 1.75, 71, 'Gaucher'),
(19, 'Foden', 'Phil', '2000-05-27 22:00:00', 'Angleterre', 990011, 47, 'Milieu', 1.71, 67, 'Gaucher'),
(20, 'Pedri', 'González', '2002-11-24 23:00:00', 'Espagne', 101112, 8, 'Milieu', 1.74, 65, 'Droitier'),
(41, 'Mbappé', 'Kylian', '1998-12-19 23:00:00', 'France', 123456, 7, 'Attaquant', 1.78, 73, 'Droitier'),
(44, 'Messi', 'Lionel', '1987-06-23 22:00:00', 'Argentine', 789012, 10, 'Attaquant', 1.7, 72, 'Gaucher'),
(45, 'Ronaldo', 'Cristiano', '1985-02-04 23:00:00', 'Portugal', 345678, 7, 'Attaquant', 1.87, 83, 'Droitier'),
(46, 'Neymar', 'Junior', '1992-02-04 23:00:00', 'Brésil', 901234, 10, 'Ailier', 1.75, 68, 'Droitier'),
(47, 'De Bruyne', 'Kevin', '1991-06-27 22:00:00', 'Belgique', 567890, 17, 'Milieu', 1.81, 70, 'Droitier'),
(48, 'Modric', 'Luka', '1985-09-08 22:00:00', 'Croatie', 111222, 10, 'Milieu', 1.72, 66, 'Droitier'),
(49, 'Kanté', 'N’Golo', '1991-03-28 23:00:00', 'France', 333444, 7, 'Milieu', 1.68, 65, 'Droitier'),
(50, 'Van Dijk', 'Virgil', '1991-07-07 22:00:00', 'Pays-Bas', 555666, 4, 'Défenseur', 1.93, 92, 'Droitier'),
(51, 'Ramos', 'Sergio', '1986-03-29 23:00:00', 'Espagne', 777888, 4, 'Défenseur', 1.84, 82, 'Droitier'),
(52, 'Alisson', 'Becker', '1992-10-01 23:00:00', 'Brésil', 999000, 1, 'Gardien', 1.91, 86, 'Droitier'),
(53, 'Ter Stegen', 'Marc-André', '1992-04-29 22:00:00', 'Allemagne', 112233, 1, 'Gardien', 1.87, 85, 'Droitier'),
(54, 'Hakimi', 'Achraf', '1998-11-03 23:00:00', 'Maroc', 223344, 2, 'Défenseur', 1.81, 73, 'Droitier'),
(55, 'Marquinhos', 'Marcos', '1994-05-13 22:00:00', 'Brésil', 334455, 5, 'Défenseur', 1.83, 79, 'Droitier'),
(56, 'Robertson', 'Andrew', '1994-03-10 23:00:00', 'Écosse', 445566, 3, 'Défenseur', 1.78, 69, 'Gaucher'),
(57, 'Lewandowski', 'Robert', '1988-08-20 22:00:00', 'Pologne', 556677, 9, 'Attaquant', 1.85, 81, 'Droitier'),
(58, 'Griezmann', 'Antoine', '1991-03-20 23:00:00', 'France', 667788, 7, 'Attaquant', 1.76, 69, 'Gaucher'),
(59, 'Benzema', 'Karim', '1987-12-18 23:00:00', 'France', 778899, 9, 'Attaquant', 1.85, 81, 'Droitier'),
(60, 'Salah', 'Mohamed', '1992-06-14 22:00:00', 'Égypte', 889900, 11, 'Ailier', 1.75, 71, 'Gaucher'),
(61, 'Foden', 'Phil', '2000-05-27 22:00:00', 'Angleterre', 990011, 47, 'Milieu', 1.71, 67, 'Gaucher');

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

--
-- Déchargement des données de la table `performances`
--

INSERT INTO `performances` (`id`, `buts`, `date_match`, `passes_decisives`, `tirs_cadres`, `duel_gagnes`, `dribbles_reussis`, `xg`) VALUES
(1, 2, '2025-02-10 17:00:00', 1, 5, 10, 3, 1.4),
(2, 1, '2025-02-09 19:00:00', 2, 4, 8, 5, 0.9),
(3, 3, '2025-02-08 16:30:00', 1, 6, 12, 7, 2.1),
(4, 0, '2025-02-07 18:00:00', 0, 2, 6, 1, 0.3),
(5, 1, '2025-02-06 14:00:00', 1, 3, 9, 4, 0.8),
(6, 2, '2025-02-10 17:00:00', 1, 5, 10, 3, 1.4),
(7, 1, '2025-02-09 19:00:00', 2, 4, 8, 5, 0.9),
(8, 3, '2025-02-08 16:30:00', 1, 6, 12, 7, 2.1),
(10, 1, '2025-02-06 14:00:00', 1, 3, 9, 4, 0.8);

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
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `nom`, `prenom`, `username`, `mdp`, `role`) VALUES
(1, 'Dupont', 'Jean', 'jdupont', 'password123', 'Admin'),
(2, 'Martin', 'Sophie', 'smartin', 'securepass', 'Médecin'),
(3, 'Lopez', 'Carlos', 'clopez', 'football2025', 'Entraîneur'),
(4, 'Dubois', 'Claire', 'cdubois', 'coachlife', 'Préparateur physique'),
(5, 'Toure', 'Ahmed', 'atoure', 'mdpsuper', 'Médecin'),
(6, 'Bernard', 'Lucas', 'lbernard', 'strongpass', 'Kinésithérapeute'),
(7, 'Lefevre', 'Emma', 'elefevre', 'psymind', 'Psychologue du sport'),
(8, 'Gomez', 'Hugo', 'hgomez', 'analyst2025', 'Analyste vidéo'),
(9, 'Rossi', 'Marco', 'mrossi', 'scoutpro', 'Recruteur'),
(10, 'Fernandez', 'Elena', 'efernandez', 'statsguru', 'Statisticien'),
(11, 'Dupont', 'Jean', 'jdupont', 'password123', 'Admin'),
(12, 'Martin', 'Sophie', 'smartin', 'securepass', 'Médecin'),
(13, 'Lopez', 'Carlos', 'clopez', 'football2025', 'Entraîneur'),
(14, 'Dubois', 'Claire', 'cdubois', 'coachlife', 'Préparateur physique'),
(15, 'Toure', 'Ahmed', 'atoure', 'mdpsuper', 'Médecin'),
(16, 'Bernard', 'Lucas', 'lbernard', 'strongpass', 'Kinésithérapeute'),
(17, 'Lefevre', 'Emma', 'elefevre', 'psymind', 'Psychologue du sport'),
(18, 'Gomez', 'Hugo', 'hgomez', 'analyst2025', 'Analyste vidéo'),
(19, 'Rossi', 'Marco', 'mrossi', 'scoutpro', 'Recruteur'),
(20, 'Fernandez', 'Elena', 'efernandez', 'statsguru', 'Statisticien'),
(21, 'Admin', 'Amin', 'admin.compte', '1234', 'Admin');

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
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `contrats`
--
ALTER TABLE `contrats`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `donnees_medicales`
--
ALTER TABLE `donnees_medicales`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `donnees_physiques`
--
ALTER TABLE `donnees_physiques`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `donnees_psychologiques`
--
ALTER TABLE `donnees_psychologiques`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `joueurs`
--
ALTER TABLE `joueurs`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT pour la table `performances`
--
ALTER TABLE `performances`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
