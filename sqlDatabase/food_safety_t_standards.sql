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
-- Table structure for table `t_standards`
--

DROP TABLE IF EXISTS `t_standards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_standards` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '类型 ',
  `summary` varchar(255) DEFAULT NULL COMMENT '概述',
  `issuance_department` varchar(255) DEFAULT NULL,
  `release_time` date DEFAULT NULL COMMENT '发布时间',
  `period` int(11) DEFAULT NULL COMMENT '使用年限',
  `attachment_id` varchar(36) DEFAULT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '1 正常 2 过期',
  `memo` text,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_standards`
--

LOCK TABLES `t_standards` WRITE;
/*!40000 ALTER TABLE `t_standards` DISABLE KEYS */;
INSERT INTO `t_standards` VALUES ('2cf0eba0-5c53-4494-bfe4-c21b5d1da8e5','面粉质量','饺子粉 高筋粉','0002','1','','国家质量检测部','2006-04-12',10,'4a9808bd-6537-43c5-bb9c-82683c0ccd3a','1872e8e6-fadc-4746-8b83-f7ecf4db5b2c',2,'','2016-04-08 16:42:03','2016-04-13 01:00:00'),('328ac0e1-b995-4c08-b785-3ca25928ce74','','','','1','','',NULL,NULL,NULL,NULL,1,'','2016-04-08 15:19:29','2016-04-08 15:19:29'),('5c971c80-bf45-4396-95a5-365cfa142ebf','食用油','大豆油 花生油','001','3','针对食用油的一些规定',NULL,'2006-04-12',10,'21f3a59c-3fbc-4f1d-b931-b8749c561b9a','1872e8e6-fadc-4746-8b83-f7ecf4db5b2c',2,'针对食用油的一些规定','2016-04-06 15:33:32','2016-04-13 01:00:00'),('c784728f-63b5-4d71-9e27-aaebe1c770d5','','','','1','','',NULL,NULL,NULL,NULL,NULL,'','2016-04-08 15:21:18','2016-04-08 15:21:18'),('d08f92e4-997f-4493-930a-8b88463fa3a5','','','','1','','',NULL,NULL,NULL,NULL,NULL,'','2016-04-08 15:15:42','2016-04-08 15:15:42'),('db62b38c-f25c-4d1f-9d0f-037df8efed35','','','','1','','',NULL,NULL,NULL,NULL,NULL,'','2016-04-08 15:19:02','2016-04-08 15:19:02'),('e3bc7533-ff52-438e-8584-7f9ec8ef78c1','aaa','asdf','1','2','asdf','asdfasd','2017-06-15',12,'1978b7a3-442a-4fbd-8896-e1eae2682cb3','1',1,'','2017-06-20 14:22:12','2017-06-20 14:22:12'),('f2bc8413-7e15-45dc-9cbe-0d095f90ff80','玫瑰花指标','玫瑰花','002','1','','质量监督管理','2013-10-01',3,'aafe0e73-46bd-4cd9-9199-3664221d80e2','1872e8e6-fadc-4746-8b83-f7ecf4db5b2c',1,'','2016-04-22 10:21:26','2016-04-22 10:21:26'),('fd9dc12b-369b-4082-ba7d-88a312010e02','','','aa333','1','','',NULL,NULL,NULL,NULL,NULL,'','2016-04-08 15:25:34','2016-04-08 15:25:34');
/*!40000 ALTER TABLE `t_standards` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-09 21:31:02
