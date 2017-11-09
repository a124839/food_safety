-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: food_safety
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `t_algorithm_params`
--

DROP TABLE IF EXISTS `t_algorithm_params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_algorithm_params` (
  `id` varchar(36) NOT NULL,
  `algorithm_id` varchar(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_algorithm_params`
--

LOCK TABLES `t_algorithm_params` WRITE;
/*!40000 ALTER TABLE `t_algorithm_params` DISABLE KEYS */;
INSERT INTO `t_algorithm_params` VALUES ('039d7092-b986-4442-be34-b433f3194818','553e744c-d97f-47e5-8f62-3d69cf9e542b','test1',1),('103ad444-e584-441d-9883-60b1ba442cb2','74839f13-f278-494c-97fd-ce273c312e18','x',1),('13dfd02e-5532-4ea7-92ba-f08494cba56d','74839f13-f278-494c-97fd-ce273c312e18','y',2),('14e8df03-e1e9-4422-bb7e-99f1bdcdcff5','f85a84a8-3369-43bb-9247-9f0221565e6f','last',1),('1aa72acc-864b-4815-84b6-01b21fbe7c51','7c746c31-3e0d-474f-97b8-148480644dcb','x',1),('215fb6ad-bcd3-486a-8557-030799dded2f','f3563d53-c19e-4f91-8883-0391be715020','order',4),('2f1f1b6c-b5a5-479e-bf98-5446ce2818fd','8db0b1cf-1918-4e7b-8e8c-38906959f9c9','a',2),('35123e45-0a81-41b2-a2ec-c481241f078b','edc652a9-a9a0-471f-a6c8-5db134dbdc17','b',2),('387a2c8d-f77c-4f60-8d0b-487b3873b81b','0d0d3126-0049-40f4-9c73-cf0ea6e61e42','first',1),('3c4583bd-1051-431d-ad4f-b05fa764d657','d5d6d9f4-e1c6-45fe-9e93-86fea72f352d','z',3),('3eb80dd4-5f9d-4f78-ba8d-d407d51640c4','6e3c3bd9-f642-484e-93a0-4b98a711d42e','x',1),('4091dfc3-db35-4fdd-ac65-44ae3de7e12e','f3563d53-c19e-4f91-8883-0391be715020','window',5),('47f50017-df55-45a8-b6b0-28c062e1638a','21b4c0ea-bf69-4e01-b4d4-f0185c4d6fae','x',1),('4ba8c941-c00f-4928-bd07-1ed4c4326709','f85a84a8-3369-43bb-9247-9f0221565e6f','first',1),('5d3d8e36-69f4-4243-8b60-16236ae1b9e1','f3563d53-c19e-4f91-8883-0391be715020','der',3),('6ae5051a-10f2-4d69-a17c-3438a62d283e','553e744c-d97f-47e5-8f62-3d69cf9e542b','test2',2),('7170cdf7-9163-4d29-a9b8-1d439fa7cd35','8db0b1cf-1918-4e7b-8e8c-38906959f9c9','b',1),('8aac6e7f-00b0-449f-884e-cfe4b528c958','09a6454b-05dd-4c9d-bbc4-5126aa677c67','c',3),('919bb235-fcec-4c70-a28b-a18f58d52127','517c389d-3e4a-49ca-8798-52b487f33f0f','x',1),('c1793c2b-6dc7-4419-9774-4b8a217a8789','01ab37cf-9b10-4264-a747-9304736cebcf','scaltype',1),('c33a6af7-4c89-4ad9-8ccc-9292348da341','32f8bb24-9c96-4349-95b0-f5988095fc6f','y',2),('c6e11ef1-8756-4264-ae02-a9afc3aa682a','0d0d3126-0049-40f4-9c73-cf0ea6e61e42','last',1),('c7ab4f45-4743-411f-af42-76fb421c7501','edc652a9-a9a0-471f-a6c8-5db134dbdc17','x',1),('c9832a65-185d-471e-911d-ae097ba7644f','32f8bb24-9c96-4349-95b0-f5988095fc6f','x',1),('cd94e008-e761-402f-83e0-acb6e8e9972d','09a6454b-05dd-4c9d-bbc4-5126aa677c67','b',2),('d7fa8d4b-4387-4110-aa3f-a260dc5d646e','d5d6d9f4-e1c6-45fe-9e93-86fea72f352d','y',2),('e4ea710c-37b4-4763-bb68-5d7a5873d857','d5d6d9f4-e1c6-45fe-9e93-86fea72f352d','x',1),('f8fb40c0-2128-4f24-b8d1-c2e9596dbe43','09a6454b-05dd-4c9d-bbc4-5126aa677c67','a',1),('fabe7613-4fd7-45e3-adc0-8478f3e08831','1c1c1986-7463-49cd-866b-80e1e373234c','x',1),('fb5b6374-bada-40b2-bb54-4a6eab782121','683f706b-09cd-4026-9513-bf9da4e4687e','x',1);
/*!40000 ALTER TABLE `t_algorithm_params` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-09 21:34:03
