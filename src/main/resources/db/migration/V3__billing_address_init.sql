--
-- Table structure for table `billing_address`
--


DROP TABLE IF EXISTS `billing_address`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `billing_address` (
  `id`       bigint(20)   NOT NULL AUTO_INCREMENT,
  `city`     varchar(255) ,
  `state`    varchar(255) ,
  `zip_code` varchar(255) ,
  `country`  varchar(255) ,

  PRIMARY KEY (`id`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
