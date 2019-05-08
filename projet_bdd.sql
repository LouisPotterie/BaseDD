-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mer. 08 mai 2019 à 16:01
-- Version du serveur :  10.1.37-MariaDB
-- Version de PHP :  7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projet_bdd`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

CREATE TABLE `adresse` (
  `adresse_id` int(11) NOT NULL,
  `numero_rue` bigint(20) NOT NULL,
  `nom_rue` varchar(50) NOT NULL,
  `code_postal` int(5) NOT NULL,
  `ville` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `adresse`
--

INSERT INTO `adresse` (`adresse_id`, `numero_rue`, `nom_rue`, `code_postal`, `ville`) VALUES
(1, 43, 'chaudet', 91180, 'SGS'),
(2, 52, 'genie', 75021, 'Paris');

-- --------------------------------------------------------

--
-- Structure de la table `coordonnee`
--

CREATE TABLE `coordonnee` (
  `coordonnee_id` int(11) NOT NULL,
  `adresse_id` int(11) NOT NULL,
  `numero_telephone` int(11) NOT NULL,
  `mail` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

CREATE TABLE `cours` (
  `cours_id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `année` int(11) NOT NULL,
  `coefficient_cours` double NOT NULL,
  `evaluation_id` int(11) NOT NULL,
  `matricule_professeur_id` int(11) NOT NULL,
  `pourcentage_DE` int(11) NOT NULL,
  `pourcentage_TP` int(11) NOT NULL,
  `pourcentage_Projet` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `matricule_etudiant_id` int(11) NOT NULL,
  `coordonnee_id` int(11) NOT NULL,
  `identite_id` int(11) NOT NULL,
  `responsable_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

CREATE TABLE `evaluation` (
  `evaluation_id` int(255) NOT NULL,
  `note` int(20) NOT NULL,
  `type_evaluation` varchar(3) NOT NULL,
  `pourcentage` int(100) NOT NULL,
  `matricule_professeur_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

CREATE TABLE `groupe` (
  `groupe_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `groupe`
--

INSERT INTO `groupe` (`groupe_id`) VALUES
(1),
(2),
(3);

-- --------------------------------------------------------

--
-- Structure de la table `groupe_cours`
--

CREATE TABLE `groupe_cours` (
  `groupe_cours_id` int(11) NOT NULL,
  `groupe_id` int(11) NOT NULL,
  `cours_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `groupe_etudiant`
--

CREATE TABLE `groupe_etudiant` (
  `groupe_etudiant_id` int(11) NOT NULL,
  `groupe_id` int(11) NOT NULL,
  `matricule_etudiant_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `identite`
--

CREATE TABLE `identite` (
  `identite_id` int(11) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `sexe` varchar(5) NOT NULL,
  `photo` longblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `professeur`
--

CREATE TABLE `professeur` (
  `matricule_professeur_id` int(11) NOT NULL,
  `coordonnée_id` int(11) NOT NULL,
  `identité_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `professeur_cours`
--

CREATE TABLE `professeur_cours` (
  `prof_cours_id` int(11) NOT NULL,
  `matricule_professeur_id` int(11) NOT NULL,
  `cours_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `responsable`
--

CREATE TABLE `responsable` (
  `responsable_id` int(11) NOT NULL,
  `coordonnee_id` int(11) NOT NULL,
  `identite_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD PRIMARY KEY (`adresse_id`);

--
-- Index pour la table `coordonnee`
--
ALTER TABLE `coordonnee`
  ADD PRIMARY KEY (`coordonnee_id`),
  ADD KEY `adresse_id` (`adresse_id`);

--
-- Index pour la table `cours`
--
ALTER TABLE `cours`
  ADD PRIMARY KEY (`cours_id`),
  ADD KEY `evaluation_id` (`evaluation_id`),
  ADD KEY `matricule_professeur_id` (`matricule_professeur_id`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`matricule_etudiant_id`),
  ADD KEY `coordonnee_id` (`coordonnee_id`),
  ADD KEY `identite_id` (`identite_id`),
  ADD KEY `responsable_id` (`responsable_id`);

--
-- Index pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD PRIMARY KEY (`evaluation_id`),
  ADD KEY `matricule_professeur_id` (`matricule_professeur_id`);

--
-- Index pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD PRIMARY KEY (`groupe_id`);

--
-- Index pour la table `groupe_cours`
--
ALTER TABLE `groupe_cours`
  ADD KEY `FK_COURS1` (`cours_id`),
  ADD KEY `FK_GRP` (`groupe_id`);

--
-- Index pour la table `groupe_etudiant`
--
ALTER TABLE `groupe_etudiant`
  ADD PRIMARY KEY (`groupe_etudiant_id`),
  ADD KEY `groupe_id` (`groupe_id`),
  ADD KEY `matricule_etudiant_id` (`matricule_etudiant_id`);

--
-- Index pour la table `identite`
--
ALTER TABLE `identite`
  ADD PRIMARY KEY (`identite_id`);

--
-- Index pour la table `professeur`
--
ALTER TABLE `professeur`
  ADD PRIMARY KEY (`matricule_professeur_id`),
  ADD KEY `FK_IDENTITE` (`identité_id`),
  ADD KEY `FK_COORDONNE` (`coordonnée_id`);

--
-- Index pour la table `professeur_cours`
--
ALTER TABLE `professeur_cours`
  ADD PRIMARY KEY (`prof_cours_id`),
  ADD KEY `matricule_professeur_id` (`matricule_professeur_id`),
  ADD KEY `cours_id` (`cours_id`);

--
-- Index pour la table `responsable`
--
ALTER TABLE `responsable`
  ADD PRIMARY KEY (`responsable_id`),
  ADD KEY `coordonnee_id` (`coordonnee_id`),
  ADD KEY `identite_id` (`identite_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `adresse`
--
ALTER TABLE `adresse`
  MODIFY `adresse_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `coordonnee`
--
ALTER TABLE `coordonnee`
  MODIFY `coordonnee_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `evaluation`
--
ALTER TABLE `evaluation`
  MODIFY `evaluation_id` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `identite`
--
ALTER TABLE `identite`
  MODIFY `identite_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `professeur`
--
ALTER TABLE `professeur`
  MODIFY `matricule_professeur_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `coordonnee`
--
ALTER TABLE `coordonnee`
  ADD CONSTRAINT `FK_ADRESSE` FOREIGN KEY (`adresse_id`) REFERENCES `adresse` (`adresse_id`);

--
-- Contraintes pour la table `cours`
--
ALTER TABLE `cours`
  ADD CONSTRAINT `FK_EVAL` FOREIGN KEY (`evaluation_id`) REFERENCES `evaluation` (`evaluation_id`),
  ADD CONSTRAINT `FK_PROFESSEUR` FOREIGN KEY (`matricule_professeur_id`) REFERENCES `professeur` (`matricule_professeur_id`);

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `FK_ANNUAIRE` FOREIGN KEY (`coordonnee_id`) REFERENCES `coordonnee` (`coordonnee_id`),
  ADD CONSTRAINT `FK_ID` FOREIGN KEY (`identite_id`) REFERENCES `identite` (`identite_id`),
  ADD CONSTRAINT `FK_RESPO` FOREIGN KEY (`responsable_id`) REFERENCES `responsable` (`responsable_id`);

--
-- Contraintes pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD CONSTRAINT `FK_PP` FOREIGN KEY (`matricule_professeur_id`) REFERENCES `professeur` (`matricule_professeur_id`);

--
-- Contraintes pour la table `groupe_cours`
--
ALTER TABLE `groupe_cours`
  ADD CONSTRAINT `FK_COURS1` FOREIGN KEY (`cours_id`) REFERENCES `cours` (`cours_id`),
  ADD CONSTRAINT `FK_GRP` FOREIGN KEY (`groupe_id`) REFERENCES `groupe` (`groupe_id`);

--
-- Contraintes pour la table `groupe_etudiant`
--
ALTER TABLE `groupe_etudiant`
  ADD CONSTRAINT `FK_ETUDIANT` FOREIGN KEY (`matricule_etudiant_id`) REFERENCES `etudiant` (`matricule_etudiant_id`),
  ADD CONSTRAINT `FK_GROUP2` FOREIGN KEY (`groupe_id`) REFERENCES `groupe` (`groupe_id`);

--
-- Contraintes pour la table `professeur`
--
ALTER TABLE `professeur`
  ADD CONSTRAINT `FK_COORDONNE` FOREIGN KEY (`coordonnée_id`) REFERENCES `professeur` (`matricule_professeur_id`),
  ADD CONSTRAINT `FK_IDENTITE` FOREIGN KEY (`identité_id`) REFERENCES `professeur` (`matricule_professeur_id`);

--
-- Contraintes pour la table `professeur_cours`
--
ALTER TABLE `professeur_cours`
  ADD CONSTRAINT `FK_COURS2` FOREIGN KEY (`cours_id`) REFERENCES `cours` (`cours_id`),
  ADD CONSTRAINT `FK_PROF2` FOREIGN KEY (`matricule_professeur_id`) REFERENCES `professeur` (`matricule_professeur_id`);

--
-- Contraintes pour la table `responsable`
--
ALTER TABLE `responsable`
  ADD CONSTRAINT `FK_COORDONNEE` FOREIGN KEY (`coordonnee_id`) REFERENCES `coordonnee` (`coordonnee_id`),
  ADD CONSTRAINT `FK_ID23` FOREIGN KEY (`identite_id`) REFERENCES `identite` (`identite_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
