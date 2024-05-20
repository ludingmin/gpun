CREATE DATABASE  IF NOT EXISTS `zzjp` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `zzjp`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: zzjp
-- ------------------------------------------------------
-- Server version	5.7.41-log

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
-- Table structure for table `tb_family`
--

DROP TABLE IF EXISTS `tb_family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_family` (
  `openid` varchar(50) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_family`
--

LOCK TABLES `tb_family` WRITE;
/*!40000 ALTER TABLE `tb_family` DISABLE KEYS */;
INSERT INTO `tb_family` VALUES ('oF3vk5b-W_57Q_DPL2KFxplCjwW4','15355555555');
/*!40000 ALTER TABLE `tb_family` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_hospital`
--

DROP TABLE IF EXISTS `tb_hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_hospital` (
  `id` int(11) NOT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `hospital_name` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_hospital`
--

LOCK TABLES `tb_hospital` WRITE;
/*!40000 ALTER TABLE `tb_hospital` DISABLE KEYS */;
INSERT INTO `tb_hospital` VALUES (1,'123',23.26093,113.8109,'123','市桥中心医院','151'),(2,'123',1.3,1.5,'123','佛山医院','151'),(3,'123',23.26093,113.8109,'这个医院没什么名气','沙湾人民医院','广东省广州市沙湾镇'),(4,'123',23.26091,113.8109,'这个医院好一点','广东省何贤医院','广东省广州市番禺区市桥街');
/*!40000 ALTER TABLE `tb_hospital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_order`
--

DROP TABLE IF EXISTS `tb_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_order` (
  `id` int(40) NOT NULL AUTO_INCREMENT,
  `connection_number` varchar(45) DEFAULT NULL,
  `is_infectious_diseases` tinyblob,
  `location_of_longitude` double DEFAULT NULL,
  `location_of_latitude` double DEFAULT NULL,
  `historical_cases` varchar(45) DEFAULT NULL,
  `hospital_id` int(11) DEFAULT NULL,
  `old_id` varchar(45) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `vlo_id` varchar(45) DEFAULT NULL,
  `creat_data` datetime DEFAULT NULL,
  `finish_data` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order`
--

LOCK TABLES `tb_order` WRITE;
/*!40000 ALTER TABLE `tb_order` DISABLE KEYS */;
INSERT INTO `tb_order` VALUES (1,'213213',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-04-20 09:12:49',NULL,NULL),(2,'JHGJ',_binary '1',113.8109,22.26093,'hjgjhG',3,'112233',1,NULL,'2023-04-20 09:12:49',NULL,NULL),(3,'jhgjh',_binary '1',113.8109,23.26093,'GJHGJH',3,'112233',10,'oF3vk5b-W_57Q_DPL2KFxplCjwW4','2023-04-20 09:12:49','2023-04-20 18:12:49',NULL),(4,'sadas',_binary '1',113.8109,23.26093,'dsasa',3,'112233',10,'oF3vk5b-W_57Q_DPL2KFxplCjwW4','2023-04-20 09:12:49','2023-05-21 17:15:44',NULL),(5,'sadsa',_binary '1',113.8109,23.26093,'sdsad',4,'oF3vk5b-W_57Q_DPL2KFxplCjwW4',4,'123','2023-04-20 09:12:49',NULL,NULL),(6,'asd',_binary '1',113.8109,23.26093,'sadas',3,'oF3vk5b-W_57Q_DPL2KFxplCjwW4',10,'123','2023-04-20 09:12:49','2023-04-20 18:12:49',NULL),(7,'asd',_binary '1',113.8109,23.26093,'sadas',3,'112233',10,'oF3vk5b-W_57Q_DPL2KFxplCjwW4','2023-04-20 09:12:49','2023-05-21 17:58:12',NULL),(8,'asd',_binary '1',113.8109,23.26093,'sadas',3,'112233',NULL,'oF3vk5b-W_57Q_DPL2KFxplCjwW4','2023-04-20 09:12:49',NULL,NULL),(9,'asdsa',_binary '1',113.288889,22.914722,'asdsad',2,'oF3vk5b-W_57Q_DPL2KFxplCjwW4',NULL,'oF3vk5b-W_57Q_DPL2KFxplCjwW4','2023-04-20 09:12:49',NULL,NULL),(10,'asdsa',_binary '1',113.1721,22.5453,'asdsa',1,'oF3vk5b-W_57Q_DPL2KFxplCjwW4',10,'123','2023-04-20 09:12:49','2023-04-20 18:12:49',NULL),(11,'asd',_binary '1',113.288889,22.914722,'asdsa',4,'oF3vk5b-W_57Q_DPL2KFxplCjwW4',NULL,NULL,'2023-04-20 09:12:49',NULL,NULL),(12,'',NULL,113.8109,23.26093,'',1,'oF3vk5b-W_57Q_DPL2KFxplCjwW4',10,'oF3vk5b-W_57Q_DPL2KFxplCjwW4','2023-04-20 09:12:49','2023-05-21 17:16:48','2023-05-21 17:16:44'),(13,'',NULL,113.8109,23.26093,'',3,'oF3vk5b-W_57Q_DPL2KFxplCjwW4',NULL,NULL,'2023-04-20 09:12:49',NULL,NULL),(14,'135666666',_binary '1',113.8109,23.26093,'这是新的订单',4,'oF3vk5b-W_57Q_DPL2KFxplCjwW4',NULL,NULL,'2023-05-20 02:00:00',NULL,NULL);
/*!40000 ALTER TABLE `tb_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user` (
  `openid` varchar(50) NOT NULL COMMENT '主键',
  `gender` char(1) DEFAULT NULL,
  `phone` varchar(11) NOT NULL COMMENT '手机号码',
  `password` varchar(128) DEFAULT '' COMMENT '密码，加密存储',
  `real_name` varchar(32) DEFAULT '' COMMENT '真实姓名',
  `nick_name` varchar(32) DEFAULT '' COMMENT '昵称，默认是用户id',
  `icon` varchar(255) DEFAULT '' COMMENT '人物头像',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `identity` int(1) DEFAULT NULL COMMENT '0为志愿者，1为长者，2为长者家属',
  PRIMARY KEY (`openid`) USING BTREE,
  UNIQUE KEY `uniqe_key_phone` (`phone`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES ('112233',NULL,'13256465456','','张大妈','','','2023-05-20 05:08:15','2023-05-20 05:08:15',NULL),('11522',NULL,'11355555555','','张泽坤','','','2023-05-20 01:09:09','2023-05-20 01:09:09',NULL),('123',NULL,'18814205998','','黄同学','','','2023-04-20 01:12:49','2023-05-20 06:28:44',0),('1649225428225552386',NULL,'11111111111','','','','','2023-04-21 01:35:57','2023-04-21 01:35:57',NULL),('1649227492506828802',NULL,'11111111113','','','','','2023-04-21 01:44:10','2023-04-21 01:44:10',NULL),('1649227871445397505',NULL,'11111111112','','','','','2023-04-21 01:45:40','2023-04-21 01:45:40',NULL),('oF3vk5b-W_57Q_DPL2KFxplCjwW4',NULL,'11111111116','','卢锭民','','','2023-05-06 10:07:26','2023-05-21 02:08:42',0);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_volunteer`
--

DROP TABLE IF EXISTS `tb_volunteer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_volunteer` (
  `openid` varchar(50) NOT NULL COMMENT '主键，用户id',
  `school` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`openid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_volunteer`
--

LOCK TABLES `tb_volunteer` WRITE;
/*!40000 ALTER TABLE `tb_volunteer` DISABLE KEYS */;
INSERT INTO `tb_volunteer` VALUES ('12','牛马广师大'),('oF3vk5b-W_57Q_DPL2KFxplCjwW4','广二师');
/*!40000 ALTER TABLE `tb_volunteer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-01 21:31:25
