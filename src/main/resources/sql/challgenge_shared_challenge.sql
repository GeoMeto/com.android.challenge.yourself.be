-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: challgenge
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `shared_challenge`
--

DROP TABLE IF EXISTS `shared_challenge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shared_challenge` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ch_id` int NOT NULL,
  `user_id` int NOT NULL,
  `likes` int DEFAULT '0',
  `reports` int DEFAULT '0',
  `is_deleted` tinyint(1) DEFAULT '0',
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `shared_challenge_fk_idx` (`ch_id`),
  KEY `shared_user_fk_idx` (`user_id`),
  CONSTRAINT `shared_challenge_fk` FOREIGN KEY (`ch_id`) REFERENCES `completed_challenge` (`id`),
  CONSTRAINT `shared_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shared_challenge`
--

LOCK TABLES `shared_challenge` WRITE;
/*!40000 ALTER TABLE `shared_challenge` DISABLE KEYS */;
INSERT INTO `shared_challenge` VALUES (1,3,2,1,0,0,'2022-06-26','2022-06-28'),(3,2,2,2,1,0,'2022-06-26','2022-07-02'),(4,5,5,2,0,1,'2022-06-29','2022-07-01'),(5,6,5,1,1,0,'2022-07-01','2022-07-02'),(6,10,7,0,0,0,'2022-07-07',NULL),(7,11,7,1,1,1,'2022-07-07','2022-07-07'),(8,13,8,2,0,0,'2022-07-07','2022-07-07');
/*!40000 ALTER TABLE `shared_challenge` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-10 21:03:29
