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
-- Table structure for table `t_sample_indicator`
--

DROP TABLE IF EXISTS `t_sample_indicator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sample_indicator` (
  `id` varchar(36) NOT NULL,
  `sample_id` varchar(36) DEFAULT NULL,
  `indicator_name` varchar(255) DEFAULT NULL COMMENT '指标名称',
  `indicator_value` varchar(255) DEFAULT NULL COMMENT '指标值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sample_indicator`
--

LOCK TABLES `t_sample_indicator` WRITE;
/*!40000 ALTER TABLE `t_sample_indicator` DISABLE KEYS */;
INSERT INTO `t_sample_indicator` VALUES ('2b47484a-b9f6-46a4-a9d6-63715ddf90ac','7451e97d-7952-440b-ac09-88dafa4e0b46','面筋','3'),('41354d6f-6d58-4564-81ee-10d27b89deb7','7451e97d-7952-440b-ac09-88dafa4e0b46','水分','1'),('4c0f3d5d-2acb-4a1a-a2ba-b56ae40eaa31','03f92e8f-fc15-4517-834c-b7a6b530ddcd','y','0.2'),('4deee2c7-49a2-4f11-b717-86aa5b4fb81c','277dc41f-387b-4824-a4ab-cd88eff09132','x','1'),('55082220-3524-4e2e-8697-bc242baa713f','c0482502-b69e-491f-b63d-c316adf48894','水分','1'),('57f962cd-b3b0-4060-a48b-45149d318ad7','0426ade1-f1c6-42b3-95bb-c7205028b0f8','a','a'),('5ce1a69d-fceb-4d80-881f-6b444f3724c9','3ede3ee7-c5f0-4651-ac7c-2ba331be6167','面筋','1'),('852a45ab-5aac-4007-8161-47c8dfe11daa','03f92e8f-fc15-4517-834c-b7a6b530ddcd','x','0.1'),('a5abaac3-3eb7-406a-bc75-c962353c534a','277dc41f-387b-4824-a4ab-cd88eff09132','y','2'),('ae1dc207-3ad7-45b9-b5b2-b28ebee2aeea','cdf7db02-aa13-4335-8ccb-4d04b23d28d1','x','0.1'),('af969024-4503-4a8b-9065-a9a435bf7fbf','a8af8725-926b-4e90-858c-587839bae27a','水分','1'),('b86a25e3-d8bd-492e-a1f0-f28d5253a593','cdf7db02-aa13-4335-8ccb-4d04b23d28d1','y','0.2'),('bb8c5b22-996e-4eb3-89a1-0a8ea91925c9','193ae222-fb0e-4ca6-9b95-ed510e94f508','a','a'),('bdcd87b6-24bd-4a53-adca-f808c08599d0','1a1cf9a5-6bfc-488a-9bc1-a920fe93d635','水分','90'),('c8da99c4-c28f-46d0-9058-023c193975bd','7168cd87-002a-44ee-a359-1ea10c2dcfbd','x','0.1'),('d2413cbf-f0dd-4a51-88da-752832e88763','193ae222-fb0e-4ca6-9b95-ed510e94f508','a','d'),('da313928-5055-4564-a114-226721270f47','3ede3ee7-c5f0-4651-ac7c-2ba331be6167','灰分','1'),('e6480676-7265-4a89-b239-f9ed59bb45f9','7168cd87-002a-44ee-a359-1ea10c2dcfbd','y','0.2'),('f03be4f6-82d5-47ff-84a5-c33c8cc8c269','0426ade1-f1c6-42b3-95bb-c7205028b0f8','a','d');
/*!40000 ALTER TABLE `t_sample_indicator` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-09 21:34:02
