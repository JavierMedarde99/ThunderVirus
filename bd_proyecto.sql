-- MariaDB dump 10.19  Distrib 10.4.24-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: bd_proyecto
-- ------------------------------------------------------
-- Server version	10.4.24-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comentarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_jugador` int(11) NOT NULL,
  `id_usu` int(11) NOT NULL,
  `comentario` varchar(124) NOT NULL,
  `likes` int(11) NOT NULL,
  `fecha_coment` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `FK_comentarios_jugadores` (`id_jugador`),
  KEY `FK_comentarios_usuarios` (`id_usu`),
  CONSTRAINT `FK_comentarios_jugadores` FOREIGN KEY (`id_jugador`) REFERENCES `jugadores` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_comentarios_usuarios` FOREIGN KEY (`id_usu`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(124) NOT NULL,
  `nombre` varchar(124) NOT NULL,
  `id_jugador` int(11) NOT NULL,
  `premio` varchar(50) NOT NULL,
  `ganador` int(11) DEFAULT NULL,
  `fecha_ini_evento` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `foto_evento` varchar(50) NOT NULL DEFAULT 'current_timestamp()',
  `fecha_fin_evento` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_evento_jugadores` (`id_jugador`),
  KEY `FK_evento_usuarios` (`ganador`),
  CONSTRAINT `FK_evento_jugadores` FOREIGN KEY (`id_jugador`) REFERENCES `jugadores` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_evento_usuarios` FOREIGN KEY (`ganador`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES (8,'patata','Evento especial',1,'pantalla',NULL,'2022-06-13 12:46:56','\"\"',NULL);
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugadores`
--

DROP TABLE IF EXISTS `jugadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jugadores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_nacimiento` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `nombre_jugador` varchar(20) NOT NULL,
  `foto` varchar(20) NOT NULL,
  `juegos` varchar(50) NOT NULL,
  `fecha_nac` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugadores`
--

LOCK TABLES `jugadores` WRITE;
/*!40000 ALTER TABLE `jugadores` DISABLE KEYS */;
INSERT INTO `jugadores` VALUES (1,'2022-06-13 12:04:46','jajavimed','','League of Legends,Valorant,Parchis,Brawl start','1999-12-11','javi'),(2,'2022-06-13 13:05:36','thunderAnotnio','','League of Legends,Valorant,Parchis,Brawl start','1999-02-15','antonio'),(3,'2022-06-13 13:41:30','Ivan3B','','League of Legends,Valorant,Parchis,Brawl start','2000-09-30','ivan'),(4,'2022-06-13 13:48:35','darness','','League of Legends,Valorant,Parchis,Brawl start','2000-01-15','adri'),(5,'2022-06-13 13:54:08','Srpatata','','League of Legends,Valorant,Parchis,Brawl start','1996-01-8','angie'),(6,'2022-06-13 13:58:33','patata','','League of Legends,Valorant,Parchis,Brawl start','1998-06-12','tono');
/*!40000 ALTER TABLE `jugadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participacion`
--

DROP TABLE IF EXISTS `participacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participacion` (
  `id_usu` int(11) NOT NULL,
  `id_evento` int(11) NOT NULL,
  `fecha_part` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `num_part` int(11) NOT NULL,
  PRIMARY KEY (`id_usu`,`id_evento`),
  KEY `FK_participacion_evento` (`id_evento`),
  CONSTRAINT `FK_participacion_evento` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_participacion_usuarios` FOREIGN KEY (`id_usu`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participacion`
--

LOCK TABLES `participacion` WRITE;
/*!40000 ALTER TABLE `participacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `participacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(200) NOT NULL,
  `email` varchar(50) NOT NULL,
  `file` varchar(50) DEFAULT 'sin_foto.jpeg',
  `fecha_nac` date NOT NULL,
  `nombre_completo` varchar(30) NOT NULL,
  `fecha_ini_usu` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `fecha_fin_usu` timestamp NULL DEFAULT NULL,
  `sub` tinyint(1) NOT NULL,
  `dinero` double DEFAULT NULL,
  `tarjeta_credito` int(11) DEFAULT NULL,
  `tarifa` double DEFAULT NULL,
  `fecha_ini_sub` timestamp NULL DEFAULT NULL,
  `fecha_fin_sub` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (31,'q','$2a$10$UtXfC.ylWCNYD82/F0TO0e2XVL7jUVccNtN5hWkc0DBYiSXWhu4pa','sss@sss.com','q.png','2022-06-01','q','2022-06-08 11:46:00',NULL,0,0,0,0,NULL,NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-13 16:23:03
