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
-- Table structure for table `t_instrument`
--

DROP TABLE IF EXISTS `t_instrument`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_instrument` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL COMMENT '型号',
  `category` varchar(36) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `sn` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT '资产编号',
  `production_date` date DEFAULT NULL COMMENT '生产日期',
  `price` varchar(255) DEFAULT NULL,
  `purchase_date` date DEFAULT NULL COMMENT '购买日期',
  `install_end_date` date DEFAULT NULL,
  `install_start_date` date DEFAULT NULL,
  `y` varchar(255) DEFAULT NULL,
  `x` varchar(255) DEFAULT NULL,
  `manufactor` varchar(255) DEFAULT NULL COMMENT '厂家',
  `performances` text COMMENT '性能指标',
  `attachment_id` varchar(36) DEFAULT NULL,
  `memo` text COMMENT '备注',
  `status` tinyint(4) DEFAULT NULL COMMENT '1： 正常； 0： 停用',
  `operator` varchar(36) DEFAULT NULL,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_instrument`
--

LOCK TABLES `t_instrument` WRITE;
/*!40000 ALTER TABLE `t_instrument` DISABLE KEYS */;
INSERT INTO `t_instrument` VALUES ('7547f85a-3c91-4319-95e6-c586e359716b','测试','1','1','1','1','1','2017-06-14','1',NULL,'2017-06-20','2017-06-20','波长','吸光度','112','11',NULL,'',1,'1','2017-06-20 14:57:44','2017-06-20 14:57:44');
/*!40000 ALTER TABLE `t_instrument` ENABLE KEYS */;
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
