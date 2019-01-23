/*
SQLyog 企业版 - MySQL GUI v8.14
MySQL - 5.7.21 : Database - iop-web
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`iop-web` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `iop-web`;

/*Table structure for table `iop_data_input` */

DROP TABLE IF EXISTS `iop_data_input`;

CREATE TABLE `iop_data_input` (
  `data_input_id` int(11) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(1024) NOT NULL,
  `table_desc` varchar(64) NOT NULL,
  `input_fields` varchar(2048) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`data_input_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `iop_data_input` */

/*Table structure for table `iop_sys_authc` */

DROP TABLE IF EXISTS `iop_sys_authc`;

CREATE TABLE `iop_sys_authc` (
  `authc_id` int(11) NOT NULL,
  `authc_name` varchar(256) NOT NULL,
  `authc_desc` varchar(1024) NOT NULL,
  PRIMARY KEY (`authc_id`),
  UNIQUE KEY `authc_name` (`authc_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_authc` */

insert  into `iop_sys_authc`(`authc_id`,`authc_name`,`authc_desc`) values (1001,'sys:user:page','用户管理界面'),(1002,'sys:role:page','角色管理界面'),(1003,'sys:authc:page','权限管理界面'),(1004,'sys:menu:page','菜单管理界面'),(1005,'sys:demo:page','演示界面'),(1001001,'sys:user:list','用户列表查询'),(1001002,'sys:user:single','单个用户查询'),(1001003,'sys:user:add','用户添加'),(1001004,'sys:user:update','用户更新'),(1001005,'sys:user:delete','用户删除'),(1001006,'sys:user:role','查看角色'),(1001007,'sys:user:assign','角色分配'),(1002001,'sys:role:list','角色列表查询'),(1002002,'sys:role:single','单个角色查询'),(1002003,'sys:role:add','角色添加'),(1002004,'sys:role:delete','角色删除'),(1002005,'sys:role:update','角色更新'),(1002006,'sys:role:authc','查看角色权限'),(1002007,'sys:role:assign','角色权限分配'),(1003001,'sys:authc:list','权限列表查询'),(1003002,'sys:authc:single','单个权限查询'),(1003003,'sys:authc:add','权限添加'),(1003004,'sys:authc:delete','权限删除'),(1003005,'sys:authc:update','权限更新');

/*Table structure for table `iop_sys_menu` */

DROP TABLE IF EXISTS `iop_sys_menu`;

CREATE TABLE `iop_sys_menu` (
  `menu_id` int(11) NOT NULL,
  `menu_name` varchar(64) NOT NULL,
  `menu_url` varchar(2048) DEFAULT NULL,
  `menu_level` int(11) DEFAULT NULL,
  `parent_mennu_id` varchar(1024) DEFAULT NULL,
  `menu_order` int(11) DEFAULT NULL,
  `authc_Id` int(11) NOT NULL,
  PRIMARY KEY (`menu_id`,`menu_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_menu` */

insert  into `iop_sys_menu`(`menu_id`,`menu_name`,`menu_url`,`menu_level`,`parent_mennu_id`,`menu_order`,`authc_Id`) values (1001,'系统管理','#',1,'0',1,0),(2001,'用户管理','/user/page',2,'1001',1,1001),(2002,'角色管理','/role/page',2,'1001',2,1002),(2003,'权限管理','/authc/page',2,'1001',3,1003),(2004,'菜单管理','/menu/page',2,'1001',4,1004);

/*Table structure for table `iop_sys_role` */

DROP TABLE IF EXISTS `iop_sys_role`;

CREATE TABLE `iop_sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(16) NOT NULL,
  `role_desc` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_role` */

insert  into `iop_sys_role`(`role_id`,`role_name`,`role_desc`) values (1,'超级管理员','具有所有权限'),(2,'权限管理员','具有权限分配权利'),(6,'普通用户','具有部分权限'),(8,'测试用户','测试用户账号'),(9,'分支用户','分支用户，具有部分权限');

/*Table structure for table `iop_sys_role_authc` */

DROP TABLE IF EXISTS `iop_sys_role_authc`;

CREATE TABLE `iop_sys_role_authc` (
  `role_authc_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `authc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_authc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_role_authc` */

insert  into `iop_sys_role_authc`(`role_authc_id`,`role_id`,`authc_id`) values (32,2,1001001),(33,2,1001002),(34,2,1001003),(35,2,1001004),(36,2,1001005),(37,2,1001006),(38,2,1001007),(39,2,1002),(40,2,1002001),(41,2,1002002),(42,2,1002003),(43,2,1002004),(44,2,1002005),(45,2,1002006),(46,2,1002007),(47,2,1003),(48,2,1003001),(49,2,1003002),(50,2,1003003),(51,2,1003004),(52,2,1003005),(64,1,1001001),(65,1,1001002),(66,1,1001003),(67,1,1001004),(68,1,1001005),(69,1,1001006),(70,1,1001007),(71,1,1002),(72,1,1002001),(73,1,1002002),(74,1,1002003),(75,1,1002004),(76,1,1002005),(77,1,1002006),(78,1,1002007),(79,1,1003),(80,1,1003001),(81,1,1003002),(82,1,1003003),(83,1,1003004),(84,1,1003005),(92,1,1004),(93,1,1005),(95,6,1001001),(96,6,1001006),(97,6,1002),(98,6,1002001),(100,6,1002006),(101,6,1003),(102,6,1003001),(104,1,1001),(105,6,1001),(106,2,1001);

/*Table structure for table `iop_sys_user` */

DROP TABLE IF EXISTS `iop_sys_user`;

CREATE TABLE `iop_sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `nickname` varchar(16) DEFAULT '',
  `password` varchar(1024) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_user` */

insert  into `iop_sys_user`(`user_id`,`username`,`nickname`,`password`) values (1,'admin','超级管理员','21232f297a57a5a743894a0e4a801fc3'),(2,'authcAdmin','权限管理员','e10adc3949ba59abbe56e057f20f883e'),(3,'sunzhen','开发人员','e10adc3949ba59abbe56e057f20f883e'),(4,'bravo','测试人员','e10adc3949ba59abbe56e057f20f883e'),(5,'abc1','测试1','123456'),(6,'abc2','测试2','123456'),(7,'abc3','测试3','123456'),(8,'abc4','测试4','123456'),(9,'abc5','测试5','123456'),(10,'abc6','测试6','123456');

/*Table structure for table `iop_sys_user_role` */

DROP TABLE IF EXISTS `iop_sys_user_role`;

CREATE TABLE `iop_sys_user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `iop_sys_user_role` */

insert  into `iop_sys_user_role`(`user_role_id`,`user_id`,`role_id`) values (16,2,2),(17,39,6),(20,40,8),(21,1,1),(22,3,1);

/*Table structure for table `iop_table_view` */

DROP TABLE IF EXISTS `iop_table_view`;

CREATE TABLE `iop_table_view` (
  `viewId` int(11) NOT NULL,
  `view_conf` text,
  PRIMARY KEY (`viewId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `iop_table_view` */

/*Table structure for table `iop_test_code` */

DROP TABLE IF EXISTS `iop_test_code`;

CREATE TABLE `iop_test_code` (
  `code_id` int(11) DEFAULT NULL,
  `city_code` varchar(10) DEFAULT NULL,
  `city_name` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `iop_test_code` */

insert  into `iop_test_code`(`code_id`,`city_code`,`city_name`) values (1,'0534','德州'),(2,'0633','日照'),(3,'0535','烟台'),(4,'0546','东营'),(5,'0634','莱芜'),(6,'0532','青岛'),(7,'0543','滨州'),(8,'0631','威海'),(9,'0533','淄博'),(10,'0632','枣庄'),(11,'0530','菏泽'),(12,'0531','济南'),(13,'0538','泰安'),(14,'0539','临沂'),(15,'0536','潍坊'),(16,'0635','聊城'),(17,'0537','济宁');

/*Table structure for table `iop_test_table` */

DROP TABLE IF EXISTS `iop_test_table`;

CREATE TABLE `iop_test_table` (
  `test_id` int(11) DEFAULT NULL,
  `test_name` varchar(64) DEFAULT NULL,
  `test_code` varchar(4) DEFAULT NULL,
  `test_date` datetime DEFAULT NULL,
  `test_order` int(11) DEFAULT NULL,
  `test_linkage` varchar(8) DEFAULT NULL,
  KEY `iop_test_table_id` (`test_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `iop_test_table` */

insert  into `iop_test_table`(`test_id`,`test_name`,`test_code`,`test_date`,`test_order`,`test_linkage`) values (1,'118d','0534','2019-03-01 11:29:12',564,'0534'),(2,'4ea8','0633','2019-11-01 11:29:12',481,'0633'),(3,'d656','0535','2019-06-01 11:29:12',320,'0535'),(4,'112a','0535','2019-07-01 11:29:12',80,'0535'),(5,'459a','0535','2019-04-01 11:29:12',485,'0535'),(6,'9428','0633','2019-01-01 11:29:12',403,'0633'),(7,'837e','0546','2019-10-01 11:29:12',241,'0546'),(8,'92cc','0532','2019-05-01 11:29:12',1,'0532'),(9,'3c85','0633','2019-04-01 11:29:12',406,'0633'),(10,'a2d6','0546','2019-02-01 11:29:12',324,'0546'),(11,'9221','0632','2019-11-01 11:29:12',162,'0632'),(12,'aaa5','0543','2019-10-01 11:29:12',489,'0543'),(13,'b5f7','0538','2019-02-01 11:29:12',328,'0538'),(14,'42ce','0631','2019-02-01 11:29:12',245,'0631'),(15,'77f7','0543','2019-09-01 11:29:12',84,'0543'),(16,'511d','0531','2019-09-01 11:29:12',474,'0531'),(17,'cf4d','0546','2019-04-01 11:29:12',312,'0546'),(18,'e59c','0536','2019-07-01 11:29:12',230,'0536'),(19,'7403','0635','2019-06-01 11:29:12',68,'0635'),(20,'ebc0','0539','2019-11-01 11:29:12',395,'0539'),(21,'aed2','0535','2019-10-01 11:29:12',233,'0535'),(22,'ef8a','0535','2019-04-01 11:29:12',151,'0535'),(23,'6efa','0537','2019-03-01 11:29:12',556,'0537'),(24,'e7c0','0535','2019-08-01 11:29:12',316,'0535'),(25,'6821','0543','2019-06-01 11:29:12',155,'0543'),(26,'bbf5','0532','2019-03-01 11:29:12',72,'0532'),(27,'13f3','0535','2019-09-01 11:29:12',478,'0535'),(28,'1704','0539','2019-03-01 11:29:12',237,'0539'),(29,'b9e7','0543','2019-07-01 11:29:12',76,'0543'),(30,'0224','0632','2019-05-01 11:29:12',560,'0632'),(31,'1938','0543','2019-10-01 11:29:12',399,'0543'),(32,'16b1','0543','2019-04-01 11:29:12',95,'0543'),(33,'9080','0633','2019-11-01 11:29:12',501,'0633'),(34,'a2e9','0538','2019-04-01 11:29:12',418,'0538'),(35,'8980','0538','2019-07-01 11:29:12',257,'0538'),(36,'a67f','0631','2019-10-01 11:29:12',16,'0631'),(37,'5a25','0632','2019-05-01 11:29:12',422,'0632'),(38,'95fa','0631','2019-06-01 11:29:12',339,'0631'),(39,'4c2e','0531','2019-11-01 11:29:12',178,'0531'),(40,'040f','0532','2019-05-01 11:29:12',505,'0532'),(41,'bc0b','0631','2019-07-01 11:29:12',343,'0631'),(42,'0051','0533','2019-02-01 11:29:12',260,'0533'),(43,'f60b','0546','2019-01-01 11:29:12',99,'0546'),(44,'cf67','0631','2019-03-01 11:29:12',426,'0631'),(45,'e088','0532','2019-07-01 11:29:12',264,'0532'),(46,'7d86','0632','2019-03-01 11:29:12',182,'0632'),(47,'64be','0533','2019-06-01 11:29:12',20,'0533'),(48,'b192','0533','2019-02-01 11:29:12',410,'0533'),(49,'e7fc','0532','2019-06-01 11:29:12',249,'0532'),(50,'e815','0534','2019-05-01 11:29:12',166,'0534'),(51,'103c','0635','2019-05-01 11:29:12',5,'0635'),(52,'8159','0633','2019-08-01 11:29:12',332,'0633'),(53,'1255','0633','2019-03-01 11:29:12',170,'0633'),(54,'784b','0537','2019-11-01 11:29:12',87,'0537'),(55,'b787','0546','2019-05-01 11:29:12',493,'0546'),(56,'37fa','0538','2019-07-01 11:29:12',253,'0538'),(57,'74c3','0535','2019-02-01 11:29:12',91,'0535'),(58,'af19','0534','2019-11-01 11:29:12',9,'0534'),(59,'55b1','0535','2019-10-01 11:29:12',414,'0535'),(60,'a4e4','0635','2019-04-01 11:29:12',174,'0635'),(61,'c9a5','0538','2019-11-01 11:29:12',12,'0538'),(62,'15ae','0546','2019-11-01 11:29:12',497,'0546'),(63,'b706','0546','2019-02-01 11:29:12',335,'0546'),(64,'54fd','0538','2019-03-01 11:29:12',32,'0538'),(65,'ec09','0543','2019-09-01 11:29:12',437,'0543'),(66,'87e3','0543','2019-11-01 11:29:12',355,'0543'),(67,'262f','0631','2019-04-01 11:29:12',193,'0631'),(68,'793f','0634','2019-02-01 11:29:12',520,'0634'),(69,'c732','0532','2019-06-01 11:29:12',358,'0532'),(70,'e48d','0635','2019-09-01 11:29:12',276,'0635'),(71,'ce10','0634','2019-01-01 11:29:12',114,'0634'),(72,'0f93','0538','2019-03-01 11:29:12',441,'0538'),(73,'5dec','0531','2019-05-01 11:29:12',280,'0531'),(74,'db30','0633','2019-04-01 11:29:12',197,'0633'),(75,'ebd4','0631','2019-07-01 11:29:12',36,'0631'),(76,'6f27','0632','2019-07-01 11:29:12',362,'0632'),(77,'105f','0631','2019-11-01 11:29:12',201,'0631'),(78,'1ff2','0530','2019-05-01 11:29:12',118,'0530'),(79,'ff39','0536','2019-09-01 11:29:12',524,'0536'),(80,'1413','0632','2019-09-01 11:29:12',347,'0632'),(81,'c3a2','0543','2019-03-01 11:29:12',185,'0543'),(82,'7125','0632','2019-01-01 11:29:12',103,'0632'),(83,'9062','0539','2019-06-01 11:29:12',508,'0539'),(84,'8470','0537','2019-10-01 11:29:12',268,'0537'),(85,'18c4','0631','2019-07-01 11:29:12',107,'0631'),(86,'35c0','0632','2019-11-01 11:29:12',24,'0632'),(87,'a81b','0531','2019-04-01 11:29:12',430,'0531'),(88,'ecba','0530','2019-09-01 11:29:12',189,'0530'),(89,'86ff','0543','2019-03-01 11:29:12',28,'0543'),(90,'fac2','0634','2019-08-01 11:29:12',512,'0634'),(91,'ecf7','0546','2019-03-01 11:29:12',351,'0546'),(92,'8402','0531','2019-11-01 11:29:12',111,'0531'),(93,'725b','0631','2019-04-01 11:29:12',516,'0631'),(94,'ffb7','0546','2019-10-01 11:29:12',433,'0546'),(95,'368d','0543','2019-05-01 11:29:12',272,'0543'),(96,'def2','0634','2019-06-01 11:29:12',535,'0634'),(97,'1c0e','0537','2019-08-01 11:29:12',374,'0537'),(98,'700a','0535','2019-04-01 11:29:12',291,'0535'),(99,'2b05','0631','2019-07-01 11:29:12',130,'0631'),(100,'d8b8','0635','2019-11-01 11:29:12',457,'0635');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
