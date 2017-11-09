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
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `id` varchar(36) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `shiro_name` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES ('49e2740f-4b2a-47fa-b22a-1a1606cad1ad','检测员','checker',1,'2016-01-15 10:41:37','2016-04-21 17:26:42'),('51475cf8-3c5a-458a-a55a-4e10bedca9c5','导师','tutor',1,'2016-06-30 10:47:26',NULL),('61808c74-6e9a-425b-8a4f-0d535ba3df6c','访客','guest',0,'2016-05-16 16:09:38','2016-05-26 16:24:18'),('82533bf3-9448-403c-880e-7577169a1e4a','学生','student',1,'2016-05-09 13:35:40',NULL),('a43419a9-cef0-444c-9edf-643dff455479','管理员','admin',1,'2016-01-19 12:17:55','2016-01-19 12:17:55'),('c42d72de-2adf-46da-8591-faddbd54e008','xiaoTest','xiao',0,'2016-05-20 11:27:53','2016-05-27 13:28:28'),('c5560dcf-986e-4b17-9555-c8bb63243a36','质量管理员','manager',1,'2016-01-15 10:43:16','2016-01-15 10:43:16'),('d681216a-cf6e-41d9-9525-7c24ac628031','111','1',0,'2016-06-29 11:58:07','2016-06-29 11:58:23'),('f0821759-40e1-4d5d-99de-744115393c1f','测试09','test6',0,'2016-04-18 11:31:56','2016-04-18 11:31:56');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-09 21:34:01
