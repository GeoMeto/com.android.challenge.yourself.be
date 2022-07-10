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
-- Table structure for table `completed_challenge`
--

DROP TABLE IF EXISTS `completed_challenge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `completed_challenge` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `measurement` varchar(45) NOT NULL,
  `description` varchar(100) DEFAULT '',
  `comment` varchar(100) DEFAULT '',
  `result` int NOT NULL,
  `target` int NOT NULL,
  `is_shared` tinyint(1) DEFAULT '0',
  `is_completed` tinyint(1) DEFAULT '0',
  `is_deleted` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_completed_challenge_idx` (`user_id`),
  CONSTRAINT `user_completed_challenge` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `completed_challenge`
--

LOCK TABLES `completed_challenge` WRITE;
/*!40000 ALTER TABLE `completed_challenge` DISABLE KEYS */;
INSERT INTO `completed_challenge` VALUES (1,2,'Jogging','Minutes','Run in the park demo','',0,30,0,0,0,'2022-06-24 17:43:32','2022-06-24 17:43:32'),(2,2,'Jogging','Minutes','Run in the park demo','Wanna try sharing again ',12665,30,1,1,0,'2022-06-24 17:44:16','2022-06-26 14:25:11'),(3,2,'Jogging','Minutes','Run in the park lat','abv',123,30,1,1,0,'2022-06-24 18:55:57','2022-06-25 15:58:48'),(4,2,'Jogging for delete','Minutes','Run in the park',NULL,0,30,0,0,1,'2022-06-26 12:04:22',NULL),(5,5,'Jogging','Minutes','Run in the park','Some comment',50,40,1,1,0,'2022-06-29 15:03:22','2022-06-29 15:04:18'),(6,5,'Health','Cups of water','Stay hydrated','I had a lot of soft drinks as well',7,8,1,1,0,'2022-07-01 08:12:11','2022-07-01 08:13:05'),(7,7,'Attend lectures','Hours','Listen to udemy lectures daily','I spent my lunch break and after work to listen to lectures',4,3,0,1,1,'2022-07-06 21:00:00','2022-07-06 21:00:00'),(8,7,'Attend lectures','Hours','Listen to udemy lectures',NULL,0,3,0,0,1,'2022-07-06 21:00:00',NULL),(9,7,'Attend lectures','Hours','Listen to udemy lectures',NULL,0,3,0,0,1,'2022-07-06 21:00:00',NULL),(10,7,'Attend lectures','Hours','Attend lectures in university or other','I was really tired after work',2,3,1,1,0,'2022-07-06 21:00:00','2022-07-06 21:00:00'),(11,7,'Stay hydrated','Cups','Update desc','',7,8,1,1,1,'2022-07-06 21:00:00','2022-07-06 21:00:00'),(12,7,'Stay hydrated','Cups','Update desc','',5,8,0,1,1,'2022-07-06 21:00:00','2022-07-06 21:00:00'),(13,8,'Jogging','Minutes','Run in the morning','I had no time for more',20,30,1,1,0,'2022-07-06 21:00:00','2022-07-06 21:00:00'),(14,10,'Attend lectures','Hours','Attend lectures in university or other','Some comment',4,5,0,1,0,'2022-07-06 21:00:00','2022-07-06 21:00:00');
/*!40000 ALTER TABLE `completed_challenge` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-10 21:03:28
