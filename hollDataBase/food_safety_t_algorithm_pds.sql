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
-- Table structure for table `t_algorithm_pds`
--

DROP TABLE IF EXISTS `t_algorithm_pds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_algorithm_pds` (
  `id` varchar(36) NOT NULL,
  `Xm` double NOT NULL,
  `Xs` double NOT NULL,
  `Xc` double NOT NULL,
  `wleft` int(11) NOT NULL,
  `wright` int(11) NOT NULL,
  `numcomp` int(11) NOT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `result` double NOT NULL,
  `ct` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_algorithm_pds`
--

LOCK TABLES `t_algorithm_pds` WRITE;
/*!40000 ALTER TABLE `t_algorithm_pds` DISABLE KEYS */;
INSERT INTO `t_algorithm_pds` VALUES ('ed417336-57a7-473a-b2cc-cb0f3e007c79',0.5561366094625213,0.29440430264153616,0.2680584772021808,2,3,5,NULL,0.49600913386441503,'2017-07-10 16:26:29'),('4ed623f6-8082-441b-a45d-96a2b469294c',0.18741547289707572,0.27305226472144106,0.015274348646227898,2,3,5,NULL,0.2622862980510481,'2017-07-10 16:26:29'),('2e79b607-1f23-4896-8fcb-e9cfe932b574',0.45529753656677485,0.9967179336142139,0.4349776997277943,2,3,5,NULL,0.048897797613506655,'2017-07-10 16:26:30'),('383cd16c-a7e3-4401-9f81-f74b5c3cd537',0.8124875172794347,0.04225100426903616,0.11218342915818613,2,3,5,NULL,0.27380392127577957,'2017-07-10 16:26:30'),('788b764f-ce9e-4650-9c04-84ae9b2c5dd2',0.7599630214466483,0.11871412422555916,0.6935818790453647,2,3,5,NULL,0.8880767357254639,'2017-07-10 16:26:30'),('609de813-66cc-45f5-b3f8-c98635968733',0.4646432346388851,0.32684086082498787,0.07950167203638137,2,3,5,NULL,0.8364269510709277,'2017-07-10 16:26:30'),('1f53adb6-e699-4348-924c-adcf8e25c311',0.7721924393709858,0.7021033734455572,0.02497791329780963,2,3,5,NULL,0.8685152713556764,'2017-07-10 16:26:30'),('2af991e9-04e3-44ba-b7f9-d8b416ea1f11',0.25631071985724163,0.48560231246117935,0.7156685576239257,2,3,5,NULL,0.8053285968736866,'2017-07-10 16:26:30'),('7bfb148c-f19d-4664-92f7-b45bec786781',0.6926449089431462,0.9154010434340788,0.9883551088868399,2,3,5,NULL,0.6275852843836122,'2017-07-10 16:26:30'),('eadc38f1-1013-4c19-820a-ba5fa07f0dc6',0.9462108346219281,0.6374446396861797,0.29194309215087566,2,3,5,NULL,0.731366579113806,'2017-07-10 16:26:30'),('11130268-273f-44de-9f12-460c5581f244',0.5326942879129535,0.3851709034426094,0.48377669937545753,2,3,5,NULL,0.38893294325118066,'2017-07-10 16:26:30'),('4cd76173-05c5-41cb-b0c1-bef29ca596d4',0.7319367388340177,0.4839201098995942,0.732213968055283,2,3,5,NULL,0.8595682151938289,'2017-07-10 16:26:30'),('cf23d51d-c8cf-4aef-a417-53c9d329d606',0.9287628737525183,0.01755756708273226,0.5824280603130203,2,3,5,NULL,0.8542114027565267,'2017-07-10 16:26:30'),('77da7f1d-46c0-4926-b40d-ce705a8870f7',0.46781825349287864,0.3249472100023152,0.6817849230076785,2,3,5,NULL,0.9718755548263257,'2017-07-10 16:26:30'),('0799c154-f6d8-4636-a433-17f1804106f7',0.2946517480020069,0.6158501350413513,0.7303425611450869,2,3,5,NULL,0.16705977842793507,'2017-07-10 16:26:31'),('2e07d505-90bc-48b9-8143-8823c3f94cd2',0.8398526809195107,0.8673992977379399,0.8695249470107396,2,3,5,NULL,0.944121064911821,'2017-07-10 16:26:31'),('80e00fce-45c1-49a8-a12a-b44f389e657d',0.04840720450503966,0.47058773177584123,0.39425402016797684,2,3,5,NULL,0.20959838340426595,'2017-07-10 16:26:31'),('664882d8-0c03-4035-ba9b-d530a2e2179d',0.0659083845999392,0.05421161222734383,0.37747619055649195,2,3,5,NULL,0.39589064068019164,'2017-07-10 16:26:31'),('f26a6efc-0124-4966-9781-596e9db3b887',0.13909729276647198,0.9755917917102981,0.9555632776601645,2,3,5,NULL,0.9160648958417636,'2017-07-10 16:26:31'),('2fdd0151-7281-4325-8a93-2139dbb261e0',0.9370419981805398,0.9857943890705149,0.1831415540960969,2,3,5,NULL,0.37891836555938596,'2017-07-10 16:26:31'),('4d859a42-d920-4500-929a-48d5f490e1bb',0.7275160488989519,0.15831453775317839,0.11483377656755878,2,3,5,NULL,0.5878419428519779,'2017-07-10 16:26:31'),('affdab08-429b-43c9-9da2-30bff9b81066',0.8931053515014715,0.4863411840857488,0.019134398558615873,2,3,5,NULL,0.9092567909577108,'2017-07-10 16:26:31'),('ebfcc7e9-1c59-45f3-9f44-942439cb2f68',0.3624558302354608,0.7139643484534677,0.733447209109879,2,3,5,NULL,0.7820732920509486,'2017-07-10 16:26:31'),('de1f98f0-85f4-45e2-b7f9-16952eebc9a0',0.6972273686915916,0.48390190077913364,0.17303854433053967,2,3,5,NULL,0.36458106386370204,'2017-07-10 16:26:31'),('d3603700-e969-4d32-acd1-eb80f425eb1b',0.7562086946618602,0.31452196723864556,0.5722177921428596,2,3,5,NULL,0.8579270887734867,'2017-07-10 16:26:31'),('021d4385-713b-449c-8ac6-01258e4abacc',0.8654409574116059,0.6325388725112969,0.5704760235618704,2,3,5,NULL,0.15613326773014202,'2017-07-10 16:26:31'),('9c086426-fc16-408c-87c3-7ef925faf92f',0.08723133784709414,0.19165662831840635,0.00935324005753646,2,3,5,NULL,0.7233254212348588,'2017-07-10 16:26:31'),('91611cde-0b03-4ee5-8080-20870e7a7b9e',0.40039629066889926,0.2799643472640807,0.026505214867146742,2,3,5,NULL,0.5860309926505882,'2017-07-10 16:26:31'),('54b4fd20-fb06-4b09-9a6e-0aa2aef46762',0.6374121514927127,0.44098367340248834,0.3094669544273444,2,3,5,NULL,0.6574148683803316,'2017-07-10 16:26:32'),('bd2306e0-73de-491a-afcf-d8ccbae5864b',0.739882424067301,0.9548449268313941,0.7468810704111754,2,3,5,NULL,0.7016602083981874,'2017-07-10 16:26:32'),('26924948-019e-42b0-98d8-2eca772bb2d7',0.3034887358186583,0.8703244149249257,0.11485330802615679,2,3,5,NULL,0.4120815938388399,'2017-07-10 16:26:32'),('a4a6d2e7-0b9c-4c58-b011-ee3b3560357a',0.6662516414590675,0.3097530378472213,0.2694131570397881,2,3,5,NULL,0.5800253768330726,'2017-07-10 16:26:32'),('584520aa-c8a1-471c-bb2a-a56b14a5effb',0.45165739216028944,0.2458067540616624,0.6153842110089008,2,3,5,NULL,0.19945918928417794,'2017-07-10 16:26:32'),('250c27d1-2ea9-4a13-8845-c47b004e1d23',0.06174026244024078,0.38643722156393023,0.5070606087594866,2,3,5,NULL,0.3206400253811327,'2017-07-10 16:26:32'),('d096d82f-7081-46c7-acbb-cfce0693afae',0.1338530710414021,0.7906951321967671,0.7805885780363342,2,3,5,NULL,0.8209129841267085,'2017-07-10 16:26:32'),('266a2d9a-8457-4a45-a320-531caf346635',0.051724672825340545,0.06394243730892135,0.8628940868245437,2,3,5,NULL,0.06476912205870311,'2017-07-10 16:26:32'),('2846581b-a573-4b37-99d4-8ee80870a64e',0.08105995410804867,0.6958979208999598,0.745934854835249,2,3,5,NULL,0.8211171649043669,'2017-07-10 16:26:32'),('63381b0f-6988-49ee-b5f6-5c74472944f7',0.08953843894667157,0.2083935113154196,0.16392918817057356,2,3,5,NULL,0.7303332601747202,'2017-07-10 16:26:32'),('a4fc0cd1-2864-4841-8e88-4856a3bf14b4',0.9651603816923748,0.4507804518921642,0.24686093668442144,2,3,5,NULL,0.07392795546117048,'2017-07-10 16:26:32'),('5b8fd9e0-7ee8-4982-a5ff-f499c9f81679',0.07304520827951166,0.5175408970501205,0.7565356916138488,2,3,5,NULL,0.6001818725440939,'2017-07-10 16:26:32'),('e7afd9a8-b1dd-4e73-8b2a-a87ae92c1b16',0.5968409046941939,0.8850794065891027,0.9772999763386776,2,3,5,NULL,0.6027468296906733,'2017-07-10 16:26:32'),('22b084a8-2937-4086-9f9a-2606b433d222',0.6378445050888246,0.7089862328921606,0.48667040513547233,2,3,5,NULL,0.7072660474485478,'2017-07-10 16:26:32'),('b6354b83-3667-4d6f-8092-30955aabaf9a',0.05297458802120736,0.009073832539147064,0.3229002747360984,2,3,5,NULL,0.06723222240924454,'2017-07-10 16:26:32'),('50cca92c-fbc5-4169-95f8-2dfb6f34f96f',0.3278198808773888,0.715314978151342,0.9487071506862272,2,3,5,NULL,0.20404491480456777,'2017-07-10 16:26:32'),('be6b2a9c-7b74-4f66-bee6-00fca5e0480b',0.4359589099139567,0.45874972325305063,0.2033393501686478,2,3,5,NULL,0.5394375865716009,'2017-07-10 16:26:32'),('129ce256-7702-44cb-9c9b-424bd9f18303',0.500731933081064,0.4398530929480139,0.2206280185926911,2,3,5,NULL,0.20815546452976952,'2017-07-10 16:26:33'),('7109bb25-4886-47dd-9d58-70e455c00e94',0.7964637051138023,0.14918539150099108,0.3425983235161485,2,3,5,NULL,0.4132516643344123,'2017-07-10 16:26:33'),('ce6b2f69-3aae-4659-833d-a17b43828689',0.6297955455365362,0.22291499805612724,0.7371780853633831,2,3,5,NULL,0.7435620450653909,'2017-07-10 16:26:33'),('e5dc4860-c5ae-4137-9a6e-f6f035bcd658',0.02328527552815507,0.04630171277027395,0.7530315931278962,2,3,5,NULL,0.15637453641711774,'2017-07-10 16:26:33'),('9e5c29eb-9978-4391-8871-c7be0e6778e1',0.2445147380676863,0.05931948385461694,0.5172946044491677,2,3,5,NULL,0.22352590421647933,'2017-07-10 16:26:33');
/*!40000 ALTER TABLE `t_algorithm_pds` ENABLE KEYS */;
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
