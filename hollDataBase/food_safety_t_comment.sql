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
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `t_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comment` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `jmyps` varchar(255) DEFAULT NULL COMMENT '建模样品数',
  `mxzcfs` varchar(255) DEFAULT NULL COMMENT '模型主成分数',
  `rmscv` varchar(255) DEFAULT NULL COMMENT '交叉验证均方根误差',
  `r_r` varchar(255) DEFAULT NULL COMMENT '决定系数',
  `memo` varchar(255) DEFAULT NULL,
  `analysis_id` varchar(36) DEFAULT NULL,
  `modal_attachment_id` varchar(36) DEFAULT NULL,
  `data_attachment_id` varchar(36) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL COMMENT '评价人',
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment`
--

LOCK TABLES `t_comment` WRITE;
/*!40000 ALTER TABLE `t_comment` DISABLE KEYS */;
INSERT INTO `t_comment` VALUES ('05fe2216-a9c3-481a-9c1b-b19c3aa3a424','测试','10','20','1','5','备注','5cea57fd-d1b2-47e0-a99e-018571cb2f3b','ed2a8385-cc00-441b-b79f-4b50ef504f1e','2887f9d1-406f-4494-85ed-610f68f7ec74','1872e8e6-fadc-4746-8b83-f7ecf4db5b2c','2016-05-17 11:46:17','2016-05-17 11:46:17'),('09cb98b9-dd14-45ec-a844-9be621f21aa2','1','11','11','11','11','11','cbcddf2f-7482-4fc5-9fe7-00d184846b64','c0d84ef1-83ab-46b4-bc9b-c344ab12ccd3','12b6ab04-ef51-4f1f-805f-ab4bbd8bf92f','1872e8e6-fadc-4746-8b83-f7ecf4db5b2c','2016-05-17 16:27:54','2016-05-17 16:27:54'),('6d7cdad3-8b31-4935-ad7e-d6d9e12c4844','111','','','','','','ca15a6c8-6bb2-49da-ab6a-5f1232ff801b','162cadb5-b55b-4908-b8ad-73b90b2e2137','52e3792f-13b4-4b7f-a8c4-4c8b88117011','1872e8e6-fadc-4746-8b83-f7ecf4db5b2c','2016-05-17 16:57:38','2016-05-17 16:57:38'),('ae152d24-725e-4e00-ac83-f77421c207a7','测试建模','10','10','1','10','测试建模','2738f0f7-7245-4634-9dc7-2fe6ccdc84e7','404a98ce-a621-44b3-8b0a-11047e3258de','466891b5-473a-497b-a295-807193c2c456','1872e8e6-fadc-4746-8b83-f7ecf4db5b2c','2016-05-17 16:16:46','2016-05-17 16:16:46');
/*!40000 ALTER TABLE `t_comment` ENABLE KEYS */;
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
