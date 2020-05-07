CREATE DATABASE  IF NOT EXISTS `clinicasanluis` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `clinicasanluis`;
-- MySQL dump 10.13  Distrib 5.7.28, for Linux (x86_64)
--
-- Host: localhost    Database: clinicasanluis
-- ------------------------------------------------------
-- Server version	5.7.28-0ubuntu0.19.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cita`
--

DROP TABLE IF EXISTS `cita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cita` (
  `id_cita` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_cita` date DEFAULT NULL,
  `fecha_emision` timestamp NULL DEFAULT NULL,
  `id_medico` int(11) DEFAULT NULL,
  `id_paciente` int(11) DEFAULT NULL,
  `motivo` varchar(45) DEFAULT NULL,
  `hora` varchar(45) DEFAULT NULL,
  `estado` char(1) DEFAULT NULL,
  PRIMARY KEY (`id_cita`),
  KEY `fk_cita_medico_idx` (`id_medico`),
  KEY `fk_cita_paciente_idx` (`id_paciente`),
  CONSTRAINT `fk_cita_medico` FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id_medico`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cita_paciente` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id_paciente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cita`
--

LOCK TABLES `cita` WRITE;
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
INSERT INTO `cita` VALUES (1,'2019-01-01','2019-12-07 18:50:24',5,1,'Dolor de diente','7:00','H'),(2,'2019-12-19','2019-12-07 18:49:37',4,1,'Dolor de Cabeza','9:00','H'),(3,'2019-12-12','2019-12-07 18:50:58',3,1,'Dolor de estomago','10:00','H'),(4,'2019-12-12','2019-12-07 18:51:33',1,1,'Dolor de espalda','3:00','H');
/*!40000 ALTER TABLE `cita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidad`
--

DROP TABLE IF EXISTS `especialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `especialidad` (
  `id_especialidad` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `estado` char(1) DEFAULT NULL,
  PRIMARY KEY (`id_especialidad`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidad`
--

LOCK TABLES `especialidad` WRITE;
/*!40000 ALTER TABLE `especialidad` DISABLE KEYS */;
INSERT INTO `especialidad` VALUES (1,'MEDICINA GENERAL','H'),(2,'NEUROLOGÍA','H'),(3,'PSICLOGÍA','H'),(4,'DERMATOLOGÍA','H'),(5,'UROLOGÍA','H');
/*!40000 ALTER TABLE `especialidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario`
--

DROP TABLE IF EXISTS `horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horario` (
  `id_horario` int(11) NOT NULL AUTO_INCREMENT,
  `hora` varchar(45) DEFAULT NULL,
  `id_medico` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_horario`),
  KEY `FKe3jtoi98amkl7s844lna0gn1y` (`id_medico`),
  CONSTRAINT `FKe3jtoi98amkl7s844lna0gn1y` FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id_medico`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario`
--

LOCK TABLES `horario` WRITE;
/*!40000 ALTER TABLE `horario` DISABLE KEYS */;
INSERT INTO `horario` VALUES (1,'7:00',NULL),(2,'7:30',NULL),(3,'8:00',NULL),(4,'8:30',NULL),(5,'9:00',NULL),(6,'9:30',NULL),(7,'10:00',NULL),(8,'10:30',NULL),(9,'11:00',NULL),(10,'11:30',NULL),(11,'12:00',NULL),(12,'12:30',NULL),(13,'1:00',NULL),(14,'1:30',NULL),(15,'2:00',NULL),(16,'2:30',NULL),(17,'3:00',NULL),(18,'3:30',NULL),(19,'4:00',NULL),(20,'4:30',NULL),(21,'5:00',NULL),(22,'5;30',NULL),(23,'6:00',NULL),(24,'6:30',NULL),(25,'7:00',NULL);
/*!40000 ALTER TABLE `horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medico`
--

DROP TABLE IF EXISTS `medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medico` (
  `id_medico` int(11) NOT NULL AUTO_INCREMENT,
  `ape_pat_med` varchar(45) DEFAULT NULL,
  `ape_mat_med` varchar(45) DEFAULT NULL,
  `names` varchar(45) DEFAULT NULL,
  `dni` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `estado` char(1) DEFAULT NULL,
  `id_especialidad` int(11) DEFAULT NULL,
  `formacion` varchar(700) DEFAULT NULL,
  PRIMARY KEY (`id_medico`),
  KEY `fk_medico_especialidad_idx` (`id_especialidad`),
  CONSTRAINT `fk_medico_especialidad` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidad` (`id_especialidad`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medico`
--

LOCK TABLES `medico` WRITE;
/*!40000 ALTER TABLE `medico` DISABLE KEYS */;
INSERT INTO `medico` VALUES (1,'Gimenez','Mendez','Samuel','78945218','samuel@gmail.com','H',1,'Especializado en Dermatología y Medicina Interna en la Universidad de Guayaquil, Posgrado en Viena, Austria'),(2,'Perez','Aguilar','Daniela','8564924','daniela@gmail.com','H',3,'Graduada en Medicina en la Universidad Católica de Cuenca, Doctorado en Psicología Clínica en la Universidad Católica de Cuenca, Especializada en Terapia individual, familiar y de pareja, Diplomado en Psicoterapia de pareja y Familia en la Escuela Sistemática de Terapia Familiar, Guayaquil'),(3,'Carpio','Rodas','Luis Aruro','74589612','luis@hotmail.com','H',2,'Especializado en el Instituto de Neurología, México, Especializado en la Universidad de Columbia, Estados Unidos.'),(4,'Ambrosi','Ordoñes','Juan José','956545221','juan@gmail.com','H',4,'Graduado en la Universidad Estatal, Cuenca, Especializado en Dermatología en la Universidad de Chile, Especializado en Cirugía Dermatológica en la Universidad de Tel-Aviv, Israel, Exvicepresidente de la Sociedad Ecuatoriana de Dermatología, Expresidente del Colegio de Médicos del Azuay.'),(5,'Gonzales','Campoverde','Richard','78963154','richard@hotmail.com','H',5,'Especializado en Urología en Hospital Español – Universidad de Buenos Aires, Argentina, Miembro de la Sociedad Argentina de Urología');
/*!40000 ALTER TABLE `medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paciente` (
  `id_paciente` int(11) NOT NULL AUTO_INCREMENT,
  `ape_pat` varchar(45) DEFAULT NULL,
  `ape_mat` varchar(45) DEFAULT NULL,
  `names` varchar(45) DEFAULT NULL,
  `dni` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_paciente`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES (1,'Ramos','Dias','Raquel','45789635','Av. Los pinos','@gmail.com','admin','H'),(2,'Sánchez','Rosas','Enrique','85496315','Av. LaJasmines','enrique@gmail.com','enrique','H');
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-07  9:21:07
