# Host: localhost  (Version: 5.5.28)
# Date: 2013-10-15 19:14:41
# Generator: MySQL-Front 5.3  (Build 4.35)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "ss_permission"
#

DROP TABLE IF EXISTS `ss_permission`;
CREATE TABLE `ss_permission` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(100) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "ss_permission"
#


#
# Structure for table "ss_role"
#

DROP TABLE IF EXISTS `ss_role`;
CREATE TABLE `ss_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(100) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "ss_role"
#

INSERT INTO `ss_role` VALUES ('1234567890','ccc','ccc',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('sdfasd234234','aaa','bbb',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

#
# Structure for table "ss_role_permission"
#

DROP TABLE IF EXISTS `ss_role_permission`;
CREATE TABLE `ss_role_permission` (
  `fk_role_id` varchar(32) DEFAULT NULL,
  `fk_permission_id` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "ss_role_permission"
#


#
# Structure for table "ss_user"
#

DROP TABLE IF EXISTS `ss_user`;
CREATE TABLE `ss_user` (
  `id` varchar(32) NOT NULL,
  `login_name` varchar(80) NOT NULL DEFAULT '',
  `nice_name` varchar(80) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `user_registered` datetime DEFAULT NULL,
  `user_activation_key` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(100) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "ss_user"
#

INSERT INTO `ss_user` VALUES ('2c9f84e04100f741014100f7451e0000','admin','Gongle','龚乐','ac0d9e488d22b3964afa9b61607cdb55a8bc2e81','2c237d8bdd81c02b','skygongle@gmail.com','2013-09-09 12:25:07',NULL,NULL,NULL,NULL,'SYSTEM','2013-09-09 12:25:07','SYSTEM','2013-10-06 22:34:03'),('2c9f84e04101431a01410143476e0000','test0.47607862282159197',NULL,'测试用户','7c37140af35f0488727ff5e635bc02b3c7103d5a','809a19e9dd25609a',NULL,'2013-09-09 13:48:08',NULL,NULL,NULL,NULL,'SYSTEM','2013-09-09 13:48:08','SYSTEM','2013-09-09 13:48:08'),('2c9f84e041015af80141015bdad80000','test0.7906546260036095',NULL,'测试用户','e0c4a6ac792270ab76554783ff6c7c84721439a8','6274531b3aeccd64',NULL,'2013-09-09 14:14:58',NULL,NULL,NULL,NULL,'SYSTEM','2013-09-09 14:14:58','SYSTEM','2013-09-09 14:14:58'),('2c9f84e041015af801410163a64f0001','test0.8164822964448348',NULL,'测试用户','214381f5807e08049df85f9c5425dad3411a3130','3071cf1fdd657108',NULL,'2013-09-09 14:23:29',NULL,NULL,NULL,NULL,'SYSTEM','2013-09-09 14:23:29','SYSTEM','2013-09-09 14:23:29'),('2c9f84e041015af801410164f2ac0002','test0.22356134793668336',NULL,'测试用户','4dec9ecf450e6af95faeee4754198fd3000350f3','ff99106d89ffc8a8',NULL,'2013-09-09 14:24:54',NULL,NULL,NULL,NULL,'SYSTEM','2013-09-09 14:24:54','SYSTEM','2013-09-09 14:24:54'),('2c9f84e041015af80141016558890003','test0.8721666197436774',NULL,'测试用户','4dcf23f62858017195afaca2c72876f48310e41d','ae33b0cddc450900',NULL,'2013-09-09 14:25:21',NULL,NULL,NULL,NULL,'SYSTEM','2013-09-09 14:25:21','SYSTEM','2013-09-09 14:25:21'),('2c9f84e041015af801410165a87c0004','test0.1150524640352466',NULL,'测试用户','3669a3fdd06f10a66de30f6d119d152569e25375','263b310fe0714dd7',NULL,'2013-09-09 14:25:41',NULL,NULL,NULL,NULL,'SYSTEM','2013-09-09 14:25:41','SYSTEM','2013-09-09 14:25:41'),('2c9f84e041015af801410165e6fe0005','test0.4735199067607019',NULL,'测试用户','35d7235f92edc51bce23014d411bc96b511e517b','4b8cbdd7df4da03f',NULL,'2013-09-09 14:25:57',NULL,NULL,NULL,NULL,'SYSTEM','2013-09-09 14:25:57','SYSTEM','2013-09-09 14:25:57'),('2c9f84e041015af80141018921ab0006','test0.8673041785489827',NULL,'测试用户','af3386bf15769539bee738f0c3f1c487d9a1917d','ab66609107cce779',NULL,'2013-09-09 15:04:26',NULL,NULL,NULL,NULL,'SYSTEM','2013-09-09 15:04:26','SYSTEM','2013-09-09 15:04:26');

#
# Structure for table "ss_user_role"
#

DROP TABLE IF EXISTS `ss_user_role`;
CREATE TABLE `ss_user_role` (
  `fk_user_id` varchar(32) DEFAULT NULL,
  `fk_role_id` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "ss_user_role"
#

