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
-- Table structure for table `t_algorithm_sst`
--

DROP TABLE IF EXISTS `t_algorithm_sst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_algorithm_sst` (
  `id` varchar(36) NOT NULL,
  `Xm` double NOT NULL,
  `Xs` double NOT NULL,
  `Xc` double NOT NULL,
  `numcomp` int(11) NOT NULL,
  `operator` varchar(36) DEFAULT NULL,
  `result` double NOT NULL,
  `ct` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_algorithm_sst`
--

LOCK TABLES `t_algorithm_sst` WRITE;
/*!40000 ALTER TABLE `t_algorithm_sst` DISABLE KEYS */;
INSERT INTO `t_algorithm_sst` VALUES ('eb4ba270-07f4-48e7-8c5c-71f459dfc47f',0.0076004522517848505,0.9618230176530371,0.19704580037568853,5,NULL,0.10537465801105206,'2017-07-10 19:41:20'),('d0ac7aed-8623-4f33-b47a-eb8adeeb76e4',0.870020023578467,0.7602575733566852,0.9925041266711984,5,NULL,0.998580556802395,'2017-07-10 19:41:20'),('77708da8-cbf3-4ecc-8666-afceaf9e6802',0.8704605877476879,0.17781847377114002,0.9193181839617681,5,NULL,0.9143754676329809,'2017-07-10 19:41:20'),('421ae695-266e-4540-9b44-806959ee070d',0.561221330039496,0.7096020572271657,0.8164033284293822,5,NULL,0.34162566888148516,'2017-07-10 19:41:20'),('17679d65-80e7-4cd2-9d7c-cdec4ab38464',0.10742734758592243,0.8080080352692393,0.05734143147273085,5,NULL,0.1708006765924096,'2017-07-10 19:41:20'),('1d9b0728-0daa-441f-b7db-6965848e34af',0.8610650345876669,0.7064191002518959,0.11500219424062619,5,NULL,0.7951914891759161,'2017-07-10 19:41:20'),('8064afcb-4c6d-45dc-b468-53de2d005c12',0.5477058804203057,0.8669452542995485,0.4640370198369764,5,NULL,0.030205241513339143,'2017-07-10 19:41:20'),('9ec5f069-fe73-40fd-9117-395d7d66ad92',0.49812993030214214,0.24856975835488504,0.7344898816439327,5,NULL,0.8982805405338382,'2017-07-10 19:41:20'),('0b310e0d-6fcb-48fb-9db8-a8e58fc7f205',0.41289282411334416,0.6006023837045534,0.19300380167233,5,NULL,0.09299082751239773,'2017-07-10 19:41:20'),('15348539-aef2-4e14-bb5f-821d7ca7c7bd',0.34925990616745284,0.6947645460345937,0.8597931017154095,5,NULL,0.7531629102291424,'2017-07-10 19:41:20'),('a0d57598-12b5-4797-a97b-481a2a679380',0.5794090521826243,0.3565836473459081,0.8161134189615173,5,NULL,0.330509043961542,'2017-07-10 19:41:20'),('3aae7458-e8ef-47b4-957e-047fd6e03251',0.53533073559497,0.2593913562506538,0.5214478901070051,5,NULL,0.8905772210106355,'2017-07-10 19:41:20'),('c33eddc2-0dcf-4133-a453-e284d16417f8',0.3326431353076954,0.49840336781013783,0.3496743033173281,5,NULL,0.6458437844668518,'2017-07-10 19:41:20'),('570e580b-69ab-4c5f-a485-5b48fdb4d75a',0.8639980687892002,0.9404140366586969,0.16320670084598865,5,NULL,0.5122894226626034,'2017-07-10 19:41:20'),('b70a5d04-b560-4c8c-9c87-265cb4d5a36a',0.7261946259977556,0.664151072973012,0.2815017477504196,5,NULL,0.1456156343566215,'2017-07-10 19:41:20'),('7c650a82-7932-4669-bf3e-37513cbb6c0a',0.4850504938229152,0.9382282723377807,0.39483800968499194,5,NULL,0.6836725843966105,'2017-07-10 19:41:20'),('dfb1d442-2d2c-4045-a1e7-04f2a1b026e7',0.9228181780386061,0.1872898079806542,0.24397553748173084,5,NULL,0.8697124194715878,'2017-07-10 19:41:20'),('bd9c0958-644c-40e9-89c4-f8bff6d7a97c',0.39506314221769623,0.21346223240359385,0.6610609715563658,5,NULL,0.9986982102351405,'2017-07-10 19:41:20'),('db91e51b-bc42-4c67-a832-46eaf087a2f0',0.1902940753865361,0.21753390471965095,0.9376195389674634,5,NULL,0.9248815490258228,'2017-07-10 19:41:21'),('c333a330-c7a6-4584-ae56-087abff767bd',0.5652033332662788,0.5374310036722778,0.41440059247467986,5,NULL,0.1111337851396198,'2017-07-10 19:41:21'),('ebdb624d-abc8-4152-895f-e9c0116ac2f6',0.8339324426054431,0.0032008770840885603,0.2726178440164745,5,NULL,0.5273413281283243,'2017-07-10 19:41:21'),('e1456ded-2f8d-43c7-84bc-5de9f7df820c',0.19116211880534484,0.9465896994862749,0.40890803231185724,5,NULL,0.49710167908733827,'2017-07-10 19:41:21'),('f3c3c28a-a971-47d1-985b-418802a8638a',0.04567694552302026,0.7980459936547942,0.8268040937369437,5,NULL,0.17114248780372865,'2017-07-10 19:41:21'),('8340b2cf-40c4-4012-9021-1c4a29e96307',0.8248476893658278,0.21064171680679566,0.9395862598665329,5,NULL,0.5720116771123951,'2017-07-10 19:41:21'),('1e4ed4d8-3cac-4a16-9084-5a8d99d588cf',0.11291188737417623,0.5135980133422074,0.12502318688592928,5,NULL,0.16781550447072058,'2017-07-10 19:41:21'),('41296b62-df7a-41ab-a8c7-c3949e662c59',0.7617508441128704,0.7226188396096146,0.690444719471972,5,NULL,0.463907211539312,'2017-07-10 19:41:21'),('d6b2658d-ee12-4b52-81de-86ef8c38070c',0.9158232832439429,0.019140995435160457,0.5305193126399653,5,NULL,0.8933857846741282,'2017-07-10 19:41:21'),('a5af9c0d-0bc3-48fb-8baf-623399107d5c',0.8048510269733598,0.04994953203648156,0.6350795580932823,5,NULL,0.007710080826617838,'2017-07-10 19:41:21'),('a9c7814c-7320-47d7-9ffc-fd05678d201f',0.6777043938989652,0.4647645303951383,0.8125014975205327,5,NULL,0.59276464730366,'2017-07-10 19:41:21'),('f3cbe637-660a-4771-92c5-434063c0ab7b',0.056507450792985914,0.42096381708679687,0.6258776443537853,5,NULL,0.872422618915371,'2017-07-10 19:41:21'),('1fe9284b-1bdd-4882-b910-23fe1d3b0f5d',0.08611909408479135,0.09457488593368457,0.22442905160828575,5,NULL,0.8976633039133974,'2017-07-10 19:41:21'),('9d229644-36c9-4b1e-977e-398e195fdc8e',0.853809993602512,0.14716254197669487,0.9557426275408332,5,NULL,0.03150558196663211,'2017-07-10 19:41:21'),('69b3eda3-7257-488b-92fd-5b759cf2013a',0.8774370410158131,0.9794871515247738,0.04980842919149353,5,NULL,0.4079701453313006,'2017-07-10 19:41:22'),('c8ca91e3-2ecd-4cbf-af46-e02074717944',0.010896580533742317,0.1346397961760616,0.9229071179817991,5,NULL,0.6660804405372173,'2017-07-10 19:41:22'),('e360891b-c505-4260-b830-36ec9e787eca',0.6251005810572037,0.45516932645457875,0.2968292655230589,5,NULL,0.8860110893672989,'2017-07-10 19:41:22'),('29d670ac-7a5f-447e-a060-8e565496bb4f',0.7966601925283798,0.37914519598724206,0.5591855603500324,5,NULL,0.27723867216018905,'2017-07-10 19:41:22'),('65c52b43-e113-4002-9db4-19901e6829be',0.5808246399997586,0.6411713721301886,0.9603789124580996,5,NULL,0.19257143064039883,'2017-07-10 19:41:22'),('24ca8ed8-6040-4155-a729-5eb30901c16b',0.6953624257877093,0.6251599636236824,0.06883835319901366,5,NULL,0.8959966664487286,'2017-07-10 19:41:22'),('764fa27e-f588-4019-be96-c89a926df3c7',0.7699441799256209,0.42552895168081784,0.38713363858336236,5,NULL,0.919962266030074,'2017-07-10 19:41:22'),('f4c37c79-0bb9-45be-9185-b7475f83a4cd',0.53207709902765,0.6437031902967629,0.9853854292537069,5,NULL,0.7565604809654634,'2017-07-10 19:41:22'),('fda05cb3-fa82-4622-8a68-f1eea3ee5eb0',0.8763679937362637,0.3158792907694814,0.0444891361826516,5,NULL,0.7284814434792843,'2017-07-10 19:41:22'),('2bad8df5-b188-45ec-a910-dd4f5c7ea450',0.013958545504445863,0.026181831483732432,0.14522336123010005,5,NULL,0.4191268673823495,'2017-07-10 19:41:22'),('4b822a73-c2ce-4fb1-b07b-c5a5886b79e8',0.6338940830206107,0.12167852860889838,0.4852478920289619,5,NULL,0.41259322948788035,'2017-07-10 19:41:22'),('2c630375-d949-4f2c-bd3a-57dd4d60d113',0.07277758462614736,0.34810861107529834,0.6533917869949553,5,NULL,0.9091797834775605,'2017-07-10 19:41:22'),('f789ce37-3ead-4e30-a5b5-41701557d8b9',0.7289203643284653,0.3894294112518203,0.31127046660974567,5,NULL,0.5353566502266514,'2017-07-10 19:41:22'),('03610806-678a-47da-a19f-1c81850d5c0b',0.3676180565330285,0.1881942898184451,0.654708155718782,5,NULL,0.5991150007734641,'2017-07-10 19:41:22'),('6d82a0ee-4d67-49f1-a9f5-9304b7cae184',0.034896732756131255,0.4886820038430051,0.746135285850216,5,NULL,0.41748866603142376,'2017-07-10 19:41:22'),('ef9f522c-2da7-4a2c-b433-3e9920e88e96',0.10577431706888252,0.974222469330257,0.13204797681019675,5,NULL,0.09859229800364222,'2017-07-10 19:41:22'),('1824231a-a22b-4af9-821c-ff9803a607d3',0.9622018433633438,0.6903737808639969,0.8950601133149383,5,NULL,0.5823549976668063,'2017-07-10 19:41:22');
/*!40000 ALTER TABLE `t_algorithm_sst` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-09 21:30:59
