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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(10) NOT NULL,
  `is_deleted` tinyint DEFAULT '0',
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin@admin.com','admin','$2a$12$9AM9Jc.Itd8kvl4sZu1aIukYKVeR/AcyC1wPYBuPJou/NxSQhJcn6','ADMIN',0,NULL,'2022-06-06'),(2,'user@user.com','user123','$2a$10$AKcRAzYTKHBcOqYjd9jdFuoUEWq34BYtwQBwHiRMfhSjXZbI241/e','CLIENT',0,'2022-06-22','2022-06-23'),(4,'user1@user.com','User1','$2a$2a$12$lHcbGvfkau/VVaPTxoLqiufvZRN8c0h24/3Q4NQDZPxOaGJFALGg.','CLIENT',0,'2022-06-22',NULL),(5,'newuser@user.com','New usera','$2a$10$oLL6Tz1L9oAOacHtKjpc5uYSh22CfVD.oc7yM/aDytrUmnc/SArl.','ADMIN',0,'2022-06-28','2022-07-01'),(6,'user@demo.com','Demo app','$2a$10$IQDZrQOv253/O3HF9EL.fewFekW/aKEWiGh25M8lKLrqjo8w4.j1.','CLIENT',0,'2022-07-01',NULL),(7,'testing@email.com','Testing user','$2a$10$crnc.5xOqMStRAnmdM5KXer6cj/H1f0OQiqCASRYhnVs61OI36vpC','CLIENT',0,'2022-07-07','2022-07-07'),(8,'second@testing.com','Second testing','$2a$10$63FQeXcjb7HgdgUjhjcSK.4nXsylNTQbDSbN6XboDTF.m9rhE8dJy','CLIENT',0,'2022-07-07',NULL),(10,'newuser1@user.com','new user','$2a$10$Zxpmq6ZZ42jaOJSLyyG4eeBcbD2J/ga2RK0fffbjeIBgfO30/vJ/q','CLIENT',0,'2022-07-07',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
