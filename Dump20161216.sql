-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: localhost    Database: amazon
-- ------------------------------------------------------
-- Server version	5.7.16

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `username` varchar(20) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `creditcardid` int(11) NOT NULL,
  PRIMARY KEY (`username`),
  KEY `creditcardFK_idx` (`creditcardid`),
  CONSTRAINT `creditcardFk` FOREIGN KEY (`creditcardid`) REFERENCES `creditcard` (`creditcardid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('joe','joe','bloggs','abcd',19),('minnie','Minnie','Mouse','abcd',21),('mmouse','Mickey','Mouse','abcd',20),('pbalachandran','Pradeep','Balachandran','amazon',17),('pluto','Pluto','Dog','pluto',23),('rchandrasekaran','Renuka','Chandrasekaran','amazon',24),('vvinay','Vinay','Balachandran','abce',22);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `categoryid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`categoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Electronics'),(2,'Furniture'),(3,'Home Appliances'),(4,'Shoes');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditcard`
--

DROP TABLE IF EXISTS `creditcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creditcard` (
  `creditcardid` int(11) NOT NULL AUTO_INCREMENT,
  `creditcardnumber` varchar(45) NOT NULL,
  `creditcardtypeid` int(11) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `expirydate` varchar(5) NOT NULL,
  `securitycode` varchar(3) NOT NULL,
  PRIMARY KEY (`creditcardid`),
  KEY `creditcardtypeFK_idx` (`creditcardtypeid`),
  CONSTRAINT `creditcardtypeFK` FOREIGN KEY (`creditcardtypeid`) REFERENCES `creditcardtype` (`creditcardtypeid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditcard`
--

LOCK TABLES `creditcard` WRITE;
/*!40000 ALTER TABLE `creditcard` DISABLE KEYS */;
INSERT INTO `creditcard` VALUES (17,'182881991882',14,'Pradeep','Balachandran','12/20','189'),(18,'19999118181',15,'joe','bloggs','1220','102'),(19,'19999118181',16,'joe','bloggs','1220','102'),(20,'10101018001091828',17,'Mickey','Mouse','1220','122'),(21,'19191938288282',18,'Minnie','Mouse','1220','123'),(22,'191928238282828',19,'Vinay','Balachandran','1220','123'),(23,'191919182',20,'Pluto','Dog','1230','123'),(24,'1992828188181881',21,'Renuka','Chandrasekaran','1221','123');
/*!40000 ALTER TABLE `creditcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditcardtype`
--

DROP TABLE IF EXISTS `creditcardtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creditcardtype` (
  `creditcardtypeid` int(11) NOT NULL AUTO_INCREMENT,
  `creditcardtypename` varchar(45) NOT NULL,
  PRIMARY KEY (`creditcardtypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditcardtype`
--

LOCK TABLES `creditcardtype` WRITE;
/*!40000 ALTER TABLE `creditcardtype` DISABLE KEYS */;
INSERT INTO `creditcardtype` VALUES (14,'MASTERCARD'),(15,'MASTERCARD'),(16,'MASTERCARD'),(17,'DISCOVER'),(18,'DISCOVER'),(19,'MASTERCARD'),(20,'MASTERCARD'),(21,'MASTERCARD');
/*!40000 ALTER TABLE `creditcardtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventoryitem`
--

DROP TABLE IF EXISTS `inventoryitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventoryitem` (
  `inventoryitemid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(100) NOT NULL,
  `unitprice` double NOT NULL,
  `inventory` int(11) NOT NULL,
  `categoryid` int(11) NOT NULL,
  PRIMARY KEY (`inventoryitemid`),
  KEY `categoryFK_idx` (`categoryid`),
  CONSTRAINT `categoryFK` FOREIGN KEY (`categoryid`) REFERENCES `category` (`categoryid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventoryitem`
--

LOCK TABLES `inventoryitem` WRITE;
/*!40000 ALTER TABLE `inventoryitem` DISABLE KEYS */;
INSERT INTO `inventoryitem` VALUES (19,'Computer Table & Chair','Computer Table & Straightback Chair',1099.99,98,2),(20,'Electric Kettle','Krups Electric Kettle',105.99,97,3),(21,'Leather Chair','Top Grain Leather Chair',299.99,100,2),(22,'Rocking Chair','Oak Rocking Chair',299.99,100,2),(23,'Coffee Maker','Krupps Coffee Maker',50.99,95,3),(24,'iPhone7','Apple iPhone7 8Gb',500.99,100,1),(25,'iPad','Apple iPad 8Gb',400.99,100,1),(26,'Oster Blender','Oster Blender',105.99,95,3),(27,'Mens Size10, Asics Gel Kayano','Asics Gel Kayano',100.99,98,4),(28,'Womens Size7, Asics Gel Kayano','Asics Gel Kayano',100.99,100,4);
/*!40000 ALTER TABLE `inventoryitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `invoiceid` int(11) NOT NULL AUTO_INCREMENT,
  `totalbeforetaxes` double DEFAULT NULL,
  `taxes` double DEFAULT NULL,
  `invoicetotal` double DEFAULT NULL,
  `transactionconfirmation` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `shippinginfoid` int(11) DEFAULT NULL,
  PRIMARY KEY (`invoiceid`),
  KEY `shippinginfoFK_idx` (`shippinginfoid`),
  CONSTRAINT `shippinginfoFK` FOREIGN KEY (`shippinginfoid`) REFERENCES `shippinginfo` (`shippinginfoid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitem`
--

DROP TABLE IF EXISTS `orderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderitem` (
  `orderitemid` int(11) NOT NULL AUTO_INCREMENT,
  `inventoryitemid` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `shoppingcartid` int(11) NOT NULL,
  PRIMARY KEY (`orderitemid`),
  KEY `inventoryitemFK_idx` (`inventoryitemid`),
  KEY `shoppingcartFK_idx` (`shoppingcartid`),
  CONSTRAINT `inventoryitemFK` FOREIGN KEY (`inventoryitemid`) REFERENCES `inventoryitem` (`inventoryitemid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `shoppingcartFK` FOREIGN KEY (`shoppingcartid`) REFERENCES `shoppingcart` (`shoppingcartid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitem`
--

LOCK TABLES `orderitem` WRITE;
/*!40000 ALTER TABLE `orderitem` DISABLE KEYS */;
INSERT INTO `orderitem` VALUES (95,23,1,169),(96,19,1,170),(97,23,1,170),(98,27,1,174),(102,26,1,177),(103,23,1,177),(104,20,1,178),(105,23,2,183),(106,26,1,184),(107,26,1,185),(108,20,1,186),(110,26,1,187),(113,27,1,188),(114,20,1,190),(115,20,1,194);
/*!40000 ALTER TABLE `orderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shippinginfo`
--

DROP TABLE IF EXISTS `shippinginfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shippinginfo` (
  `shippinginfoid` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `address` varchar(100) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(2) NOT NULL,
  `zip` varchar(5) NOT NULL,
  PRIMARY KEY (`shippinginfoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shippinginfo`
--

LOCK TABLES `shippinginfo` WRITE;
/*!40000 ALTER TABLE `shippinginfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `shippinginfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingcart`
--

DROP TABLE IF EXISTS `shoppingcart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingcart` (
  `shoppingcartid` int(11) NOT NULL AUTO_INCREMENT,
  `total` double DEFAULT NULL,
  `invoiceid` int(11) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`shoppingcartid`),
  KEY `cartFK_idx` (`invoiceid`),
  KEY `actFK_idx` (`username`),
  CONSTRAINT `accountFK` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `invoiceFK` FOREIGN KEY (`invoiceid`) REFERENCES `invoice` (`invoiceid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingcart`
--

LOCK TABLES `shoppingcart` WRITE;
/*!40000 ALTER TABLE `shoppingcart` DISABLE KEYS */;
INSERT INTO `shoppingcart` VALUES (166,NULL,NULL,'mmouse','active'),(167,NULL,NULL,'mmouse','active'),(168,0,NULL,'mmouse','active'),(169,50.99,NULL,'mmouse','active'),(170,1150.98,NULL,'pluto','active'),(171,NULL,NULL,'mmouse','active'),(172,NULL,NULL,'mmouse','active'),(173,NULL,NULL,'mmouse','active'),(174,100.99,NULL,'mmouse','active'),(175,NULL,NULL,'mmouse','active'),(176,0,NULL,'mmouse','active'),(177,156.98,NULL,'pbalachandran','active'),(178,105.99,NULL,'pbalachandran','active'),(179,NULL,NULL,'pbalachandran','active'),(180,NULL,NULL,'mmouse','active'),(181,NULL,NULL,'pbalachandran','active'),(182,NULL,NULL,'pbalachandran','active'),(183,101.98,NULL,'pbalachandran','active'),(184,105.99,NULL,'pbalachandran','active'),(185,105.99,NULL,'pbalachandran','active'),(186,105.99,NULL,'pbalachandran','active'),(187,105.99,NULL,'pbalachandran','active'),(188,100.99,NULL,'pbalachandran','active'),(189,NULL,NULL,'rchandrasekaran','active'),(190,105.99,NULL,'rchandrasekaran','active'),(191,NULL,NULL,'pbalachandran','active'),(192,NULL,NULL,'rchandrasekaran','active'),(193,NULL,NULL,'rchandrasekaran','active'),(194,105.99,NULL,'rchandrasekaran','active'),(195,NULL,NULL,'pbalachandran','active');
/*!40000 ALTER TABLE `shoppingcart` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-16  0:23:51
