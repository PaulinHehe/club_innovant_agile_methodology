INSERT INTO `joueurs` (`nom`, `prenom`, `dateNaissance`, `nationalite`, `numeroLicence`, `numeroMaillot`, `position`, `taille`, `poids`, `piedFort`) VALUES
('Mbappé', 'Kylian', '1998-12-20 00:00:00', 'France', 123456, 7, 'Attaquant', 1.78, 73, 'Droitier'),
('Messi', 'Lionel', '1987-06-24 00:00:00', 'Argentine', 789012, 10, 'Attaquant', 1.70, 72, 'Gaucher'),
('Ronaldo', 'Cristiano', '1985-02-05 00:00:00', 'Portugal', 345678, 7, 'Attaquant', 1.87, 83, 'Droitier'),
('Neymar', 'Junior', '1992-02-05 00:00:00', 'Brésil', 901234, 10, 'Ailier', 1.75, 68, 'Droitier'),
('De Bruyne', 'Kevin', '1991-06-28 00:00:00', 'Belgique', 567890, 17, 'Milieu', 1.81, 70, 'Droitier'),
('Modric', 'Luka', '1985-09-09 00:00:00', 'Croatie', 111222, 10, 'Milieu', 1.72, 66, 'Droitier'),
('Kanté', 'N’Golo', '1991-03-29 00:00:00', 'France', 333444, 7, 'Milieu', 1.68, 65, 'Droitier'),
('Van Dijk', 'Virgil', '1991-07-08 00:00:00', 'Pays-Bas', 555666, 4, 'Défenseur', 1.93, 92, 'Droitier'),
('Ramos', 'Sergio', '1986-03-30 00:00:00', 'Espagne', 777888, 4, 'Défenseur', 1.84, 82, 'Droitier'),
('Alisson', 'Becker', '1992-10-02 00:00:00', 'Brésil', 999000, 1, 'Gardien', 1.91, 86, 'Droitier'),
('Ter Stegen', 'Marc-André', '1992-04-30 00:00:00', 'Allemagne', 112233, 1, 'Gardien', 1.87, 85, 'Droitier'),
('Hakimi', 'Achraf', '1998-11-04 00:00:00', 'Maroc', 223344, 2, 'Défenseur', 1.81, 73, 'Droitier'),
('Marquinhos', 'Marcos', '1994-05-14 00:00:00', 'Brésil', 334455, 5, 'Défenseur', 1.83, 79, 'Droitier'),
('Robertson', 'Andrew', '1994-03-11 00:00:00', 'Écosse', 445566, 3, 'Défenseur', 1.78, 69, 'Gaucher'),
('Lewandowski', 'Robert', '1988-08-21 00:00:00', 'Pologne', 556677, 9, 'Attaquant', 1.85, 81, 'Droitier'),
('Griezmann', 'Antoine', '1991-03-21 00:00:00', 'France', 667788, 7, 'Attaquant', 1.76, 69, 'Gaucher'),
('Benzema', 'Karim', '1987-12-19 00:00:00', 'France', 778899, 9, 'Attaquant', 1.85, 81, 'Droitier'),
('Salah', 'Mohamed', '1992-06-15 00:00:00', 'Égypte', 889900, 11, 'Ailier', 1.75, 71, 'Gaucher'),
('Foden', 'Phil', '2000-05-28 00:00:00', 'Angleterre', 990011, 47, 'Milieu', 1.71, 67, 'Gaucher'),
('Pedri', 'González', '2002-11-25 00:00:00', 'Espagne', 101112, 8, 'Milieu', 1.74, 65, 'Droitier');



INSERT INTO `contrats` (`type`, `dateDebut`, `dateFin`, `salaire`, `primes`, `devise`, `idJoueur`) VALUES
(1, '2022-07-01 00:00:00', '2026-06-30 00:00:00', 5000000, 1000000, 'EUR', 1),
(1, '2023-07-01 00:00:00', '2027-06-30 00:00:00', 4000000, 800000, 'EUR', 2),
(1, '2021-07-01 00:00:00', '2025-06-30 00:00:00', 6000000, 1200000, 'EUR', 3),
(1, '2020-07-01 00:00:00', '2024-06-30 00:00:00', 7000000, 1500000, 'EUR', 4),
(1, '2021-07-01 00:00:00', '2025-06-30 00:00:00', 3500000, 500000, 'EUR', 5);



INSERT INTO `performances` (`buts`, `date_match`, `passes_decisives`, `tirs_cadres`, `duel_gagnes`, `dribbles_reussis`, `xg`) VALUES
(2, '2025-02-10 18:00:00', 1, 5, 10, 3, 1.4),
(1, '2025-02-09 20:00:00', 2, 4, 8, 5, 0.9),
(3, '2025-02-08 17:30:00', 1, 6, 12, 7, 2.1),
(0, '2025-02-07 19:00:00', 0, 2, 6, 1, 0.3),
(1, '2025-02-06 15:00:00', 1, 3, 9, 4, 0.8);



INSERT INTO `consultations` (`details`, `resultats`, `traitements`, `idjoueurs`, `idmedecin`) VALUES
('Douleur au genou droit', 'Entorse légère', 'Repos 2 semaines', 1, 1),
('Fatigue musculaire', 'Examen normal', 'Hydratation et repos', 2, 2),
('Fracture du poignet', 'Plâtre 6 semaines', 'Immobilisation et rééducation', 3, 3),
('Tendinite', 'Inflammation détectée', 'Physiothérapie recommandée', 4, 1),
('Blessure aux ischio-jambiers', 'Déchirure partielle', 'Repos et renforcement musculaire', 5, 2);



INSERT INTO `utilisateurs` (`nom`, `prenom`, `username`, `mdp`, `role`) VALUES
('Dupont', 'Jean', 'jdupont', 'password123', 'Admin'),
('Martin', 'Sophie', 'smartin', 'securepass', 'Médecin'),
('Lopez', 'Carlos', 'clopez', 'football2025', 'Entraîneur'),
('Dubois', 'Claire', 'cdubois', 'coachlife', 'Préparateur physique'),
('Toure', 'Ahmed', 'atoure', 'mdpsuper', 'Médecin'),
('Bernard', 'Lucas', 'lbernard', 'strongpass', 'Kinésithérapeute'),
('Lefevre', 'Emma', 'elefevre', 'psymind', 'Psychologue du sport'),
('Gomez', 'Hugo', 'hgomez', 'analyst2025', 'Analyste vidéo'),
('Rossi', 'Marco', 'mrossi', 'scoutpro', 'Recruteur'),
('Fernandez', 'Elena', 'efernandez', 'statsguru', 'Statisticien');



INSERT INTO `donnees_medicales` (`allergies`, `vaccins`, `idConsultation`) VALUES
('Pollen', 'Tétanos, Hépatite B', 1),
('Aucune', 'COVID-19, Grippe', 2),
('Arachides', 'Tétanos, Rougeole', 3),
('Aucune', 'Hépatite B, Grippe', 4),
('Gluten', 'COVID-19, Hépatite A', 5),
('Poils de chat', 'Tétanos, Hépatite B', 1),
('Fruits de mer', 'COVID-19, Varicelle', 2),
('Aucune', 'Rougeole, Polio', 3),
('Lait', 'Grippe, COVID-19', 4),
('Aucune', 'Tétanos, Tuberculose', 5);



INSERT INTO `donnees_physiques` (`vitesseMax`, `distanceParcourue`, `nombreSprints`, `vo2max`, `acceleration`, `puissance`, `idJoueur`) VALUES
(35.2, 10.5, 25, 60.2, 4.1, 700, 1),
(32.8, 9.3, 18, 58.7, 3.8, 650, 2),
(34.5, 11.0, 22, 61.5, 4.3, 720, 3),
(33.1, 8.9, 20, 57.4, 3.5, 680, 4),
(36.0, 12.2, 30, 62.8, 4.5, 750, 5);



INSERT INTO `donnees_psychologiques` (`profilPsychologique`, `suiviMental`, `strategiesPreparation`, `historiqueConsultations`, `idJoueurs`) VALUES
('Leader naturel, optimiste', 'Aucun trouble détecté', 'Méditation avant match', 'Consultation en 2024', 1),
('Anxieux sous pression', 'Besoin de suivi', 'Relaxation et gestion du stress', 'Suivi régulier', 2),
('Très compétitif, mais impulsif', 'Risque d’épuisement', 'Coaching mental intensif', 'Deux séances en 2023', 3),
('Calme et réfléchi', 'Stable mentalement', 'Visualisation des scénarios de match', 'Aucune consultation', 4),
('Manque de confiance en soi', 'En cours de thérapie', 'Exercices de motivation et encouragements', 'Début de suivi en 2025', 5);
