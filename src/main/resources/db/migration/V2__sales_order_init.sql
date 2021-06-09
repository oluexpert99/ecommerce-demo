--
-- Table structure for table `sales_order`
--


DROP TABLE IF EXISTS `sales_order`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_order` (
  `id`              bigint(20)     NOT NULL AUTO_INCREMENT,
  `order_status`    varchar(255)   NOT NULL,
  `order_date`      datetime       NOT NULL,
  `customer_id`     varchar(255)            DEFAULT NULL,
  `transaction_ref` varchar(255)            DEFAULT NULL,
  `amount`          decimal(19, 2) NOT NULL,
  `address_id`      bigint(20)     NOT NULL,
  `product_id`      bigint(20)     NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9u3h7nl8ljm4o7jc24gg9cxv5` (`product_id`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
