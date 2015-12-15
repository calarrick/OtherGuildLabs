
-- liquibase formatted sql
-- changeset calarrick:1


-- CREATE DATABASE IF NOT EXISTS `dvd_library` ;
-- USE `dvd_library`;
-- MySQL dump 10.13  Distrib 5.5.46, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: dvd_library
-- ------------------------------------------------------
-- Server version	5.5.46-0ubuntu0.14.04.2



--
-- Table structure for table `Dvds`
--

-- DROP TABLE IF EXISTS `Dvds`;

CREATE TABLE `Dvds` (
  `dvd_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `release_date` date DEFAULT NULL,
  `mpaa_rating` varchar(5) DEFAULT NULL,
  `director` varchar(100) DEFAULT NULL,
  `studio` varchar(255) DEFAULT NULL,
  `note` text,
  PRIMARY KEY (`dvd_id`),
  KEY `title` (`title`),
  KEY `director` (`director`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;



-- changeset calarrick:2




