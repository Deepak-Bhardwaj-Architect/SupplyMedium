-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.37-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema supplyme_db
--

CREATE DATABASE IF NOT EXISTS supplyme_db;
USE supplyme_db;

--
-- Definition of table `_coupon_master`
--

DROP TABLE IF EXISTS `_coupon_master`;
CREATE TABLE `_coupon_master` (
  `coupon_id` bigint(20) NOT NULL auto_increment,
  `coupon_code` varchar(255) default NULL,
  `date_of_issue` date default NULL,
  `valid_from` date default NULL,
  `valid_upto` date default NULL,
  `discount_in_percentage` int(11) default NULL,
  PRIMARY KEY  (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `_coupon_master`
--

/*!40000 ALTER TABLE `_coupon_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `_coupon_master` ENABLE KEYS */;


--
-- Definition of table `_error_master`
--

DROP TABLE IF EXISTS `_error_master`;
CREATE TABLE `_error_master` (
  `error_id` bigint(20) NOT NULL auto_increment,
  `error_date` date default NULL,
  `error_time` time default NULL,
  `error_message` varchar(60000) default NULL,
  PRIMARY KEY  (`error_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `_error_master`
--

/*!40000 ALTER TABLE `_error_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `_error_master` ENABLE KEYS */;


--
-- Definition of table `_logger_master`
--

DROP TABLE IF EXISTS `_logger_master`;
CREATE TABLE `_logger_master` (
  `logger_id` bigint(20) NOT NULL auto_increment,
  `logger_date` date default NULL,
  `logger_time` time default NULL,
  `logger_message` varchar(60000) default NULL,
  PRIMARY KEY  (`logger_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `_logger_master`
--

/*!40000 ALTER TABLE `_logger_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `_logger_master` ENABLE KEYS */;


--
-- Definition of table `_redeem_master`
--

DROP TABLE IF EXISTS `_redeem_master`;
CREATE TABLE `_redeem_master` (
  `redeem_id` bigint(20) NOT NULL auto_increment,
  `date_of_redeem` date default NULL,
  `coupon_code` varchar(255) default NULL,
  `company_id` varchar(255) default NULL,
  `package_selected_by_company` varchar(255) default NULL,
  `package_price_actual` int(11) default NULL,
  `discount_in_percent` int(11) default NULL,
  `package_price_discounted` int(11) default NULL,
  PRIMARY KEY  (`redeem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `_redeem_master`
--

/*!40000 ALTER TABLE `_redeem_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `_redeem_master` ENABLE KEYS */;


--
-- Definition of table `account_lockout_policies`
--

DROP TABLE IF EXISTS `account_lockout_policies`;
CREATE TABLE `account_lockout_policies` (
  `regn_rel_key` varchar(255) NOT NULL,
  `invalid_login_attempts` int(2) NOT NULL,
  `lockout_duration_min` int(3) NOT NULL,
  `reset_lockout_duration_min` int(3) NOT NULL,
  `admin_unlock_flag` tinyint(1) NOT NULL,
  `expire_session_min` int(3) NOT NULL,
  KEY `account_policies_rel_key` (`regn_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account_lockout_policies`
--

/*!40000 ALTER TABLE `account_lockout_policies` DISABLE KEYS */;
INSERT INTO `account_lockout_policies` (`regn_rel_key`,`invalid_login_attempts`,`lockout_duration_min`,`reset_lockout_duration_min`,`admin_unlock_flag`,`expire_session_min`) VALUES 
 ('9815990067',5,10,5,1,30),
 ('8288033280',5,10,5,1,30);
/*!40000 ALTER TABLE `account_lockout_policies` ENABLE KEYS */;


--
-- Definition of table `activation_pending`
--

DROP TABLE IF EXISTS `activation_pending`;
CREATE TABLE `activation_pending` (
  `user_rel_key` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `activation_pending`
--

/*!40000 ALTER TABLE `activation_pending` DISABLE KEYS */;
INSERT INTO `activation_pending` (`user_rel_key`,`password`,`created_timestamp`) VALUES 
 ('sfsdf@gmail.com','null','2014-09-18 15:11:34'),
 ('dilbags@webkrit.com','null','2014-09-17 15:38:27'),
 ('ddddd@gmail.com','null','2014-10-01 19:47:12'),
 ('hhhh@gmail.com','null','2014-10-01 19:47:44');
/*!40000 ALTER TABLE `activation_pending` ENABLE KEYS */;


--
-- Definition of table `ad_statistic`
--

DROP TABLE IF EXISTS `ad_statistic`;
CREATE TABLE `ad_statistic` (
  `ad_stat_id` bigint(20) NOT NULL auto_increment,
  `ad_rel_id` bigint(20) default NULL,
  `date` date NOT NULL,
  `count` int(10) NOT NULL,
  PRIMARY KEY  (`ad_stat_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ad_statistic`
--

/*!40000 ALTER TABLE `ad_statistic` DISABLE KEYS */;
/*!40000 ALTER TABLE `ad_statistic` ENABLE KEYS */;


--
-- Definition of table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
CREATE TABLE `advertisement` (
  `ad_id` bigint(20) NOT NULL auto_increment,
  `regn_rel_key` varchar(255) NOT NULL,
  `user_rel_key` varchar(255) NOT NULL,
  `alternate_text` text NOT NULL,
  `link` varchar(255) NOT NULL,
  `ad_image_path` varchar(255) NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`ad_id`),
  KEY `ad_id` (`ad_id`,`regn_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `advertisement`
--

/*!40000 ALTER TABLE `advertisement` DISABLE KEYS */;
/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;


--
-- Definition of table `business_categories`
--

DROP TABLE IF EXISTS `business_categories`;
CREATE TABLE `business_categories` (
  `business_category_id` bigint(20) unsigned NOT NULL auto_increment,
  `business_category_name` varchar(255) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`business_category_id`),
  KEY `business_category_name` (`business_category_name`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `business_categories`
--

/*!40000 ALTER TABLE `business_categories` DISABLE KEYS */;
INSERT INTO `business_categories` (`business_category_id`,`business_category_name`) VALUES 
 (1,'Aerospace/Defence'),
 (2,'Automobiles and Parts'),
 (3,'Bank/Financial Institution'),
 (4,'Biotechnology'),
 (5,'Cause'),
 (6,'Chemicals'),
 (7,'Church/Religious Organization'),
 (8,'Community Organization'),
 (9,'Company'),
 (10,'Computers/Technology'),
 (11,'Consulting/Business Services'),
 (12,'Education'),
 (13,'Energy/Utility'),
 (14,'Engineering/Construction'),
 (15,'Farming/Agriculture'),
 (16,'Food/Beverages'),
 (17,'Government Organization'),
 (18,'Health/Beauty'),
 (19,'Health/Medical/Pharmaceuticals'),
 (20,'Industrials'),
 (21,'Insurance Company'),
 (22,'Internet/Software'),
 (23,'Legal/Law'),
 (24,'Media/News/Publishing'),
 (25,'Mining/Materials'),
 (26,'Non-Governmental Organization (NGO)'),
 (27,'Non-Profit Organization'),
 (28,'Organization'),
 (29,'Political Organization'),
 (30,'Political Party'),
 (31,'Retail and Consumer Merchandise'),
 (32,'School'),
 (33,'Small Business'),
 (34,'Telecommunication'),
 (35,'Transport/Freight'),
 (36,'Travel/Leisure'),
 (37,'University');
/*!40000 ALTER TABLE `business_categories` ENABLE KEYS */;


--
-- Definition of table `company_feed`
--

DROP TABLE IF EXISTS `company_feed`;
CREATE TABLE `company_feed` (
  `company_feed_id` bigint(20) NOT NULL auto_increment,
  `regn_rel_key` varchar(255) NOT NULL,
  `user_rel_key` varchar(255) NOT NULL,
  `feed_title` text NOT NULL,
  `feed` text NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`company_feed_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company_feed`
--

/*!40000 ALTER TABLE `company_feed` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_feed` ENABLE KEYS */;


--
-- Definition of table `company_feed_like_comment_master`
--

DROP TABLE IF EXISTS `company_feed_like_comment_master`;
CREATE TABLE `company_feed_like_comment_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `news_feed_id` bigint(20) default NULL,
  `comment_detail` varchar(10000) default NULL,
  `commented_on` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company_feed_like_comment_master`
--

/*!40000 ALTER TABLE `company_feed_like_comment_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_feed_like_comment_master` ENABLE KEYS */;


--
-- Definition of table `company_registration`
--

DROP TABLE IF EXISTS `company_registration`;
CREATE TABLE `company_registration` (
  `company_id` varchar(255) collate utf8_unicode_ci NOT NULL,
  `company_name` varchar(255) collate utf8_unicode_ci NOT NULL,
  `company_logo_path` text collate utf8_unicode_ci NOT NULL,
  `business_category_name` varchar(255) collate utf8_unicode_ci NOT NULL,
  `company_type` varchar(255) collate utf8_unicode_ci NOT NULL,
  `company_theme` varchar(255) collate utf8_unicode_ci NOT NULL,
  `pricing_option` varchar(255) collate utf8_unicode_ci NOT NULL,
  `company_phoneno` varchar(255) collate utf8_unicode_ci NOT NULL,
  `uuid` varchar(255) collate utf8_unicode_ci NOT NULL,
  `company_status` varchar(255) collate utf8_unicode_ci NOT NULL,
  `segment_division_name` varchar(255) collate utf8_unicode_ci NOT NULL,
  `business_unit_name` varchar(255) collate utf8_unicode_ci NOT NULL,
  `regn_key` varchar(255) collate utf8_unicode_ci NOT NULL,
  `email` varchar(255) collate utf8_unicode_ci NOT NULL,
  `is_regn_vendor` tinyint(1) NOT NULL default '0',
  `created_date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  KEY `regn_key` (`regn_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `company_registration`
--

/*!40000 ALTER TABLE `company_registration` DISABLE KEYS */;
INSERT INTO `company_registration` (`company_id`,`company_name`,`company_logo_path`,`business_category_name`,`company_type`,`company_theme`,`pricing_option`,`company_phoneno`,`uuid`,`company_status`,`segment_division_name`,`business_unit_name`,`regn_key`,`email`,`is_regn_vendor`,`created_date`) VALUES 
 ('webkrit','webkrit','http://localhost:8084/SupplyMedium/webapps/CropData/webkrit-9815990067/Images/logo.jpg','Internet/Software,','Buyers/Suppliers','undefined','Basic','9815990067','6bf6a1f8-b434-4b62-ae41-98de34fd2f05','Active','','','9815990067','info@webkrit.com',0,'2014-09-12 08:31:50'),
 ('webkrit','hostkrit','http://localhost:8084/SupplyMedium/webapps/CropData/hostkrit-8288033280/Images/logo.jpg','Internet/Software,','Buyers/Suppliers','undefined','Basic','8288033280','7154d601-6590-4501-b98c-91f30bd074f3','Active','','','8288033280','info@hostkrit.com',0,'2014-09-12 08:32:57');
/*!40000 ALTER TABLE `company_registration` ENABLE KEYS */;


--
-- Definition of table `country_company_mapper`
--

DROP TABLE IF EXISTS `country_company_mapper`;
CREATE TABLE `country_company_mapper` (
  `regn_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `company_name` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `country_name` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  KEY `regn_key` (`regn_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `country_company_mapper`
--

/*!40000 ALTER TABLE `country_company_mapper` DISABLE KEYS */;
INSERT INTO `country_company_mapper` (`regn_key`,`company_name`,`country_name`) VALUES 
 ('9815990067','webkrit','India'),
 ('8288033280','hostkrit','India');
/*!40000 ALTER TABLE `country_company_mapper` ENABLE KEYS */;


--
-- Definition of table `department_feed_like_comment_master`
--

DROP TABLE IF EXISTS `department_feed_like_comment_master`;
CREATE TABLE `department_feed_like_comment_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `news_feed_id` bigint(20) default NULL,
  `comment_detail` varchar(10000) default NULL,
  `commented_on` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department_feed_like_comment_master`
--

/*!40000 ALTER TABLE `department_feed_like_comment_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `department_feed_like_comment_master` ENABLE KEYS */;


--
-- Definition of table `departments`
--

DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments` (
  `dept_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `dept_name` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `regn_rel_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  KEY `dept_key` (`dept_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `departments`
--

/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` (`dept_key`,`dept_name`,`regn_rel_key`) VALUES 
 ('9815990067:testing','testing','9815990067'),
 ('9815990067:devlopment','devlopment','9815990067'),
 ('8288033280:development','development','8288033280'),
 ('8288033280:testing','testing','8288033280');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;


--
-- Definition of table `dept_feed`
--

DROP TABLE IF EXISTS `dept_feed`;
CREATE TABLE `dept_feed` (
  `dept_feed_id` bigint(20) NOT NULL auto_increment,
  `regn_rel_key` varchar(255) NOT NULL,
  `user_rel_key` varchar(255) NOT NULL,
  `dept_rel_key` varchar(255) NOT NULL,
  `feed_title` text NOT NULL,
  `feed` text NOT NULL,
  `company_feed_flag` tinyint(1) NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`dept_feed_id`),
  KEY `regn_rel_key` (`regn_rel_key`,`user_rel_key`,`dept_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dept_feed`
--

/*!40000 ALTER TABLE `dept_feed` DISABLE KEYS */;
/*!40000 ALTER TABLE `dept_feed` ENABLE KEYS */;


--
-- Definition of table `dept_files`
--

DROP TABLE IF EXISTS `dept_files`;
CREATE TABLE `dept_files` (
  `dept_file_id` bigint(20) NOT NULL auto_increment,
  `regn_rel_key` varchar(255) NOT NULL,
  `dept_rel_key` varchar(255) NOT NULL,
  `dept_folder_rel_key` varchar(255) NOT NULL,
  `relative_path` varchar(1024) NOT NULL,
  `local_path` varchar(255) NOT NULL,
  `web_url` varchar(255) NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `file_type` varchar(255) NOT NULL,
  `file_size` decimal(20,2) NOT NULL default '0.00',
  `recycle_flag` tinyint(1) NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`dept_file_id`),
  KEY `regn_rel_key` (`regn_rel_key`,`dept_rel_key`,`dept_folder_rel_key`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dept_files`
--

/*!40000 ALTER TABLE `dept_files` DISABLE KEYS */;
INSERT INTO `dept_files` (`dept_file_id`,`regn_rel_key`,`dept_rel_key`,`dept_folder_rel_key`,`relative_path`,`local_path`,`web_url`,`file_name`,`file_type`,`file_size`,`recycle_flag`,`created_timestamp`) VALUES 
 (1,'9815990067','9815990067:ffffffff','9815990067:ffff','webapps/','/webapps/CropData/webkrit-9815990067/Docs/DeptFiles/1.jpg','http://localhost:8084/SupplyMedium','Jellyfish','jpg','18.04',0,'2014-09-17 14:16:46'),
 (2,'8288033280','8288033280:testing','8288033280:image','webapps/','/webapps/CropData/hostkrit-8288033280/Docs/DeptFiles/2.jpg','http://localhost:8084/SupplyMedium','Koala','jpg','27.37',0,'2014-09-29 15:42:04');
/*!40000 ALTER TABLE `dept_files` ENABLE KEYS */;


--
-- Definition of table `dept_folder_mapping`
--

DROP TABLE IF EXISTS `dept_folder_mapping`;
CREATE TABLE `dept_folder_mapping` (
  `dept_rel_key` varchar(255) NOT NULL,
  `dept_folder_rel_key` varchar(255) NOT NULL,
  `recycle_flag` tinyint(1) NOT NULL default '0',
  KEY `dept_folder_rel_key` (`dept_folder_rel_key`),
  KEY `dept_rel_key` (`dept_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dept_folder_mapping`
--

/*!40000 ALTER TABLE `dept_folder_mapping` DISABLE KEYS */;
INSERT INTO `dept_folder_mapping` (`dept_rel_key`,`dept_folder_rel_key`,`recycle_flag`) VALUES 
 ('8288033280:development','8288033280:common',0),
 ('8288033280:development','8288033280:doc',0),
 ('9815990067:devlopment','9815990067:doc files',0),
 ('9815990067:testing','9815990067:excel files',0),
 ('8288033280:testing','8288033280:image',0),
 ('8288033280:testing','8288033280:doc',1),
 ('8288033280:testing','8288033280:abcd',0);
/*!40000 ALTER TABLE `dept_folder_mapping` ENABLE KEYS */;


--
-- Definition of table `dept_folders`
--

DROP TABLE IF EXISTS `dept_folders`;
CREATE TABLE `dept_folders` (
  `dept_folder_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `folder_name` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `folder_path` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `regn_rel_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  KEY `dept_folder_key` (`dept_folder_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dept_folders`
--

/*!40000 ALTER TABLE `dept_folders` DISABLE KEYS */;
INSERT INTO `dept_folders` (`dept_folder_key`,`folder_name`,`folder_path`,`regn_rel_key`) VALUES 
 ('9815990067:excel files','excel files','null','9815990067'),
 ('9815990067:doc files','doc files','null','9815990067'),
 ('8288033280:doc','doc','null','8288033280'),
 ('8288033280:image','image','null','8288033280'),
 ('8288033280:common','common','null','8288033280'),
 ('8288033280:abcd','abcd','null','8288033280');
/*!40000 ALTER TABLE `dept_folders` ENABLE KEYS */;


--
-- Definition of table `dept_privileges`
--

DROP TABLE IF EXISTS `dept_privileges`;
CREATE TABLE `dept_privileges` (
  `dept_rel_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `privileges` int(32) NOT NULL,
  KEY `dept_rel_key` (`dept_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dept_privileges`
--

/*!40000 ALTER TABLE `dept_privileges` DISABLE KEYS */;
INSERT INTO `dept_privileges` (`dept_rel_key`,`privileges`) VALUES 
 ('9815990067:testing',101010),
 ('9815990067:lokesh',101010),
 ('8288033280:development',101010),
 ('8288033280:testing',101010);
/*!40000 ALTER TABLE `dept_privileges` ENABLE KEYS */;


--
-- Definition of table `external_page`
--

DROP TABLE IF EXISTS `external_page`;
CREATE TABLE `external_page` (
  `externalpageid` int(11) NOT NULL auto_increment,
  `compnayRegnKey` varchar(255) NOT NULL,
  `companyURLName` varchar(255) NOT NULL,
  `pageType` varchar(15) NOT NULL,
  PRIMARY KEY  (`externalpageid`),
  UNIQUE KEY `compnayRegnKey` (`compnayRegnKey`),
  KEY `compnayRegnKey_2` (`compnayRegnKey`),
  KEY `compnayRegnKey_3` (`compnayRegnKey`),
  KEY `compnayRegnKey_4` (`compnayRegnKey`),
  KEY `compnayRegnKey_5` (`compnayRegnKey`),
  KEY `compnayRegnKey_6` (`compnayRegnKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `external_page`
--

/*!40000 ALTER TABLE `external_page` DISABLE KEYS */;
/*!40000 ALTER TABLE `external_page` ENABLE KEYS */;


--
-- Definition of table `group_dept_mapping`
--

DROP TABLE IF EXISTS `group_dept_mapping`;
CREATE TABLE `group_dept_mapping` (
  `group_rel_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `dept_rel_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `group_dept_mapping_id` bigint(20) unsigned NOT NULL auto_increment,
  PRIMARY KEY  (`group_dept_mapping_id`),
  UNIQUE KEY `group_dept_mapping_id` (`group_dept_mapping_id`),
  KEY `group_rel_key` (`group_rel_key`),
  KEY `dept_rel_key` (`dept_rel_key`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_dept_mapping`
--

/*!40000 ALTER TABLE `group_dept_mapping` DISABLE KEYS */;
INSERT INTO `group_dept_mapping` (`group_rel_key`,`dept_rel_key`,`group_dept_mapping_id`) VALUES 
 ('9815990067:ascdf','9815990067:devlopment',1),
 ('9815990067:dfg','9815990067:devlopment',2),
 ('9815990067:manager','9815990067:devlopment',3),
 ('9815990067:programmer','9815990067:testing',4),
 ('8288033280:devlopment','8288033280:testing',5),
 ('8288033280:testing','8288033280:testing',6);
/*!40000 ALTER TABLE `group_dept_mapping` ENABLE KEYS */;


--
-- Definition of table `group_folder_access`
--

DROP TABLE IF EXISTS `group_folder_access`;
CREATE TABLE `group_folder_access` (
  `group_folder_access_id` bigint(20) NOT NULL auto_increment,
  `regn_rel_key` varchar(255) NOT NULL,
  `dept_rel_key` varchar(255) NOT NULL,
  `group_rel_key` varchar(255) NOT NULL,
  `folder_rel_key` varchar(255) NOT NULL,
  `read_flag` tinyint(1) NOT NULL,
  `read_write_flag` tinyint(1) NOT NULL,
  PRIMARY KEY  (`group_folder_access_id`),
  KEY `regn_rel_key` (`regn_rel_key`),
  KEY `group_rel_key` (`group_rel_key`),
  KEY `folder_rel_key` (`folder_rel_key`),
  KEY `dept_rel_key` (`dept_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_folder_access`
--

/*!40000 ALTER TABLE `group_folder_access` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_folder_access` ENABLE KEYS */;


--
-- Definition of table `group_privileges`
--

DROP TABLE IF EXISTS `group_privileges`;
CREATE TABLE `group_privileges` (
  `group_rel_key` varchar(255) NOT NULL,
  `privileges` bigint(20) NOT NULL,
  KEY `group_rel_key` (`group_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_privileges`
--

/*!40000 ALTER TABLE `group_privileges` DISABLE KEYS */;
INSERT INTO `group_privileges` (`group_rel_key`,`privileges`) VALUES 
 ('9815990067:manager',0),
 ('9815990067:programmer',0),
 ('8288033280:testing',0),
 ('8288033280:devlopment',0);
/*!40000 ALTER TABLE `group_privileges` ENABLE KEYS */;


--
-- Definition of table `groups`
--

DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `group_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `group_name` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `regn_rel_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  KEY `group_key` (`group_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groups`
--

/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` (`group_key`,`group_name`,`regn_rel_key`) VALUES 
 ('9815990067:manager','manager','9815990067'),
 ('9815990067:programmer','programmer','9815990067'),
 ('8288033280:testing','testing','8288033280'),
 ('8288033280:devlopment','devlopment','8288033280');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;


--
-- Definition of table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
  `invoice_id` bigint(20) NOT NULL auto_increment,
  `trans_id` bigint(20) NOT NULL,
  `from_regn_key` varchar(255) NOT NULL,
  `to_regn_key` varchar(255) NOT NULL,
  `user_from` varchar(255) NOT NULL,
  `user_to` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `action` varchar(255) NOT NULL,
  `is_outside_buyer` tinyint(1) NOT NULL,
  `outside_buyer_email` varchar(255) NOT NULL,
  `recurring` varchar(255) NOT NULL,
  `invoice_date` date NOT NULL,
  `total_list_price` double(20,2) NOT NULL,
  `tax_percentage` double(5,2) NOT NULL,
  `total_price` double(20,2) NOT NULL,
  `freight_handling` double(10,2) NOT NULL,
  `discount` double(20,2) NOT NULL,
  `invoice_billing_period` varchar(255) NOT NULL,
  `invoice_due_date` date NOT NULL,
  `invoice_no` varchar(255) NOT NULL,
  `freight_carrier` varchar(1024) NOT NULL,
  `bill_of_landing` bigint(20) NOT NULL,
  `freight_weight` double(10,2) NOT NULL,
  `freight_weight_unit` varchar(255) NOT NULL,
  `shipped_date` date NOT NULL,
  `is_nonpo_invoice` tinyint(1) NOT NULL,
  `po_num` varchar(255) NOT NULL,
  `is_diff_address` tinyint(1) NOT NULL,
  `diff_address` varchar(500) NOT NULL,
  `diff_email` varchar(255) NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `invoice_trans_id` varchar(100) default NULL,
  `outside_supplier_address` varchar(500) default NULL,
  PRIMARY KEY  (`invoice_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice`
--

/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` (`invoice_id`,`trans_id`,`from_regn_key`,`to_regn_key`,`user_from`,`user_to`,`status`,`action`,`is_outside_buyer`,`outside_buyer_email`,`recurring`,`invoice_date`,`total_list_price`,`tax_percentage`,`total_price`,`freight_handling`,`discount`,`invoice_billing_period`,`invoice_due_date`,`invoice_no`,`freight_carrier`,`bill_of_landing`,`freight_weight`,`freight_weight_unit`,`shipped_date`,`is_nonpo_invoice`,`po_num`,`is_diff_address`,`diff_address`,`diff_email`,`created_timestamp`,`invoice_trans_id`,`outside_supplier_address`) VALUES 
 (1,1,'8288033280','9815990067','info@hostkrit.com','','InvoiceCreated','CreateInvoice',0,'null','null','2014-09-12',26000.00,10.00,28600.00,10.00,0.00,'null','2014-09-12','2014-1','bharat transport',1452,1452.00,'KG','2014-09-12',1,'2014-1',0,'','','2014-09-12 08:42:49',NULL,'null, null, null, null, null, null'),
 (2,3,'8288033280','9815990067','info@hostkrit.com','info@webkrit.com','InvoiceAccepted','CreateInvoice',0,'null','null','2014-09-12',2600.00,15.00,2990.00,10.00,0.00,'null','2014-09-12','2014-2','bharat trnsport',1452,1452.00,'KG','2014-09-12',1,'1452-1',1,',,,,','','2014-09-12 08:46:12',NULL,'null, null, null, null, null, null'),
 (3,4,'8288033280','9815990067','info@hostkrit.com','info@webkrit.com','InvoiceAccepted','CreateInvoice',0,'null','null','2014-09-12',720.00,10.00,792.00,10.00,0.00,'null','2014-09-12','2014-3','',0,0.00,'KG','0001-05-31',1,'ppppp',0,'','','2014-09-12 10:51:57',NULL,'null, null, null, null, null, null');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;


--
-- Definition of table `invoice_item`
--

DROP TABLE IF EXISTS `invoice_item`;
CREATE TABLE `invoice_item` (
  `invoice_item_id` bigint(20) NOT NULL auto_increment,
  `invoice_id` bigint(20) NOT NULL,
  `trans_id` bigint(20) NOT NULL,
  `item_desc` text NOT NULL,
  `part_no` varchar(255) NOT NULL,
  `quantity_ordered` double(10,2) NOT NULL,
  `quantity_ordered_unit` varchar(255) NOT NULL,
  `price` double(20,2) NOT NULL,
  `currency` varchar(255) NOT NULL,
  `multiplier` int(10) NOT NULL,
  `quantity_shipped` double(10,2) NOT NULL,
  `quantity_shipped_unit` varchar(255) NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `barcode_id` varchar(100) default NULL,
  PRIMARY KEY  (`invoice_item_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice_item`
--

/*!40000 ALTER TABLE `invoice_item` DISABLE KEYS */;
INSERT INTO `invoice_item` (`invoice_item_id`,`invoice_id`,`trans_id`,`item_desc`,`part_no`,`quantity_ordered`,`quantity_ordered_unit`,`price`,`currency`,`multiplier`,`quantity_shipped`,`quantity_shipped_unit`,`created_timestamp`,`barcode_id`) VALUES 
 (1,1,1,'lenovo','b560',1.00,'KG',26000.00,'USD',1,1.00,'KG','2014-09-12 08:42:49','b560'),
 (2,2,3,'lenovo','b560',1.00,'KG',2600.00,'USD',1,1.00,'KG','2014-09-12 08:46:12','b560'),
 (3,3,4,'i','p',90.00,'KG',8.00,'USD',1,90.00,'KG','2014-09-12 10:51:57','b');
/*!40000 ALTER TABLE `invoice_item` ENABLE KEYS */;


--
-- Definition of table `login_attempts`
--

DROP TABLE IF EXISTS `login_attempts`;
CREATE TABLE `login_attempts` (
  `email` varchar(255) NOT NULL,
  `invalid_attempts_count` int(3) NOT NULL,
  `lock_out_timestamp` timestamp NULL default '0000-00-00 00:00:00',
  `attempt_timestamp` timestamp NOT NULL default '0000-00-00 00:00:00',
  KEY `email` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login_attempts`
--

/*!40000 ALTER TABLE `login_attempts` DISABLE KEYS */;
INSERT INTO `login_attempts` (`email`,`invalid_attempts_count`,`lock_out_timestamp`,`attempt_timestamp`) VALUES 
 ('info@webkrit.com',2,'2014-11-06 12:52:24','2014-11-06 12:52:24'),
 ('info@hostkrit.com',1,'2014-10-26 22:00:49','2014-10-26 22:00:49');
/*!40000 ALTER TABLE `login_attempts` ENABLE KEYS */;


--
-- Definition of table `login_status`
--

DROP TABLE IF EXISTS `login_status`;
CREATE TABLE `login_status` (
  `user_rel_key` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `login_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  KEY `user_rel_key` (`user_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login_status`
--

/*!40000 ALTER TABLE `login_status` DISABLE KEYS */;
INSERT INTO `login_status` (`user_rel_key`,`status`,`login_timestamp`) VALUES 
 ('info@webkrit.com','Connected','2014-09-18 14:32:37'),
 ('info@hostkrit.com','Not Connected','2014-10-26 23:23:04');
/*!40000 ALTER TABLE `login_status` ENABLE KEYS */;


--
-- Definition of table `mailing_address`
--

DROP TABLE IF EXISTS `mailing_address`;
CREATE TABLE `mailing_address` (
  `address_id` bigint(20) unsigned NOT NULL auto_increment,
  `user_id` bigint(20) unsigned NOT NULL default '0',
  `address_type` varchar(255) collate utf8_unicode_ci NOT NULL,
  `address` text collate utf8_unicode_ci NOT NULL,
  `city_name` varchar(255) collate utf8_unicode_ci NOT NULL,
  `state_name` varchar(255) collate utf8_unicode_ci NOT NULL,
  `zipcode` varchar(255) collate utf8_unicode_ci NOT NULL,
  `country_name` varchar(255) collate utf8_unicode_ci NOT NULL,
  `email` varchar(255) collate utf8_unicode_ci NOT NULL,
  `regn_key` varchar(255) collate utf8_unicode_ci NOT NULL,
  `mail_key` bigint(11) NOT NULL default '0',
  PRIMARY KEY  (`address_id`),
  KEY `email` (`email`),
  KEY `regn_key` (`regn_key`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `mailing_address`
--

/*!40000 ALTER TABLE `mailing_address` DISABLE KEYS */;
INSERT INTO `mailing_address` (`address_id`,`user_id`,`address_type`,`address`,`city_name`,`state_name`,`zipcode`,`country_name`,`email`,`regn_key`,`mail_key`) VALUES 
 (1,0,'Corporate Office','zirakpur','mohali','punjab','140603','India','info@webkrit.com','9815990067',1),
 (2,0,'Store','zirakpur','mohali','punjab','140603','India','info@hostkrit.com','8288033280',2),
 (3,0,'null','null','','','','India','dilbags@webkrit.com','9815990067',0),
 (4,0,'null','null','','','','India','sfsdf@gmail.com','9815990067',0),
 (5,0,'null','null','','','','India','dsdilbag345@gmail.com','8288033280',0),
 (8,0,'null','null','','','','India','ddddd@gmail.com','8288033280',0),
 (9,0,'null','null','','','','India','hhhh@gmail.com','8288033280',0);
/*!40000 ALTER TABLE `mailing_address` ENABLE KEYS */;


--
-- Definition of table `my_connection`
--

DROP TABLE IF EXISTS `my_connection`;
CREATE TABLE `my_connection` (
  `from_user_name` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL COMMENT 'Name of the user who sent the connection request',
  `from_comp_name` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL COMMENT 'Name of the user''s ( who sent the connection request) company',
  `from_user_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL COMMENT 'Profile key of the user who is sent the connection request',
  `from_regn_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL COMMENT 'Company key of the user who sent the connection request',
  `to_user_name` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL COMMENT 'Name of the user who receive the connection request',
  `to_comp_name` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL COMMENT 'Name of the user''s ( who receive the connection request) company',
  `to_user_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL COMMENT 'Profile key of the user who is receive the connection request',
  `to_regn_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL COMMENT 'Company key of the user who receive the connection request',
  `status` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL COMMENT 'Status of the connection',
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT 'Connection created time stamp',
  KEY `from_user_key` (`from_user_key`),
  KEY `to_user_key` (`to_user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `my_connection`
--

/*!40000 ALTER TABLE `my_connection` DISABLE KEYS */;
INSERT INTO `my_connection` (`from_user_name`,`from_comp_name`,`from_user_key`,`from_regn_key`,`to_user_name`,`to_comp_name`,`to_user_key`,`to_regn_key`,`status`,`created_timestamp`) VALUES 
 ('lokesh kakkar','webkrit','info@webkrit.com','9815990067','kakkar lokesh','hostkrit','info@hostkrit.com','8288033280','Accepted','2014-09-12 16:17:06');
/*!40000 ALTER TABLE `my_connection` ENABLE KEYS */;


--
-- Definition of table `news_feed_like_comment_master`
--

DROP TABLE IF EXISTS `news_feed_like_comment_master`;
CREATE TABLE `news_feed_like_comment_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `news_feed_id` bigint(20) default NULL,
  `comment_detail` varchar(10000) default NULL,
  `commented_on` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `news_feed_like_comment_master`
--

/*!40000 ALTER TABLE `news_feed_like_comment_master` DISABLE KEYS */;
INSERT INTO `news_feed_like_comment_master` (`id`,`news_feed_id`,`comment_detail`,`commented_on`) VALUES 
 (4,1,'like','2014-09-12 08:43:52'),
 (5,1,'like','2014-09-12 08:43:53'),
 (6,1,'its nice we are quoting here','2014-09-12 08:44:06');
/*!40000 ALTER TABLE `news_feed_like_comment_master` ENABLE KEYS */;


--
-- Definition of table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `notification_id` bigint(20) NOT NULL auto_increment,
  `receiver_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `receiver_regn_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `sender_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `sender_regn_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `notification_type` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `notification_type_id` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `notification_message` text character set utf8 collate utf8_unicode_ci NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `status` int(2) default '1',
  PRIMARY KEY  (`notification_id`),
  KEY `notification_id` (`notification_id`,`receiver_key`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notification`
--

/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` (`notification_id`,`receiver_key`,`receiver_regn_key`,`sender_key`,`sender_regn_key`,`notification_type`,`notification_type_id`,`notification_message`,`created_timestamp`,`status`) VALUES 
 (1,'info@hostkrit.com','8288033280','info@webkrit.com','9815990067','VendorRegistration','1','New Vendor registration request received from webkrit','2014-09-12 08:36:45',0),
 (2,'info@webkrit.com','9815990067','info@hostkrit.com','8288033280','VendorRegistration','1','hostkrit sent Vendor registration form','2014-09-12 08:36:57',0),
 (3,'info@hostkrit.com','8288033280','info@webkrit.com','9815990067','VendorRegistration','0','Vendor registration request accepted by webkrit','2014-09-12 08:38:23',0),
 (4,'info@hostkrit.com','8288033280','info@webkrit.com','9815990067','RFQ','1','Transaction RFQ is received from lokesh kakkar','2014-09-12 08:39:16',0),
 (5,'info@webkrit.com','9815990067','info@hostkrit.com','8288033280','RFQ','1','Transaction RFQ is accepted by kakkar lokesh','2014-09-12 08:39:29',0),
 (6,'info@hostkrit.com','8288033280','info@webkrit.com','9815990067','Quote','2','Transaction Quote is received from lokesh kakkar','2014-09-12 08:40:07',0),
 (7,'info@webkrit.com','9815990067','info@hostkrit.com','8288033280','Quote','1','Transaction Quote is received from kakkar lokesh','2014-09-12 08:40:58',0),
 (8,'info@hostkrit.com','8288033280','info@webkrit.com','9815990067','Quote','1','Transaction Quote is accepted by lokesh kakkar','2014-09-12 08:41:12',0),
 (9,'info@hostkrit.com','8288033280','info@webkrit.com','9815990067','PO','1','Transaction PO is received from lokesh kakkar','2014-09-12 08:41:35',0),
 (10,'info@webkrit.com','9815990067','info@hostkrit.com','8288033280','PO','1','Transaction PO is accepted by kakkar lokesh','2014-09-12 08:42:06',0),
 (11,'','9815990067','info@hostkrit.com','8288033280','Invoice','1','Transaction Invoice is received from kakkar lokesh','2014-09-12 08:42:49',1),
 (12,'info@webkrit.com','9815990067','info@hostkrit.com','8288033280','Invoice','3','Transaction Invoice is received from kakkar lokesh','2014-09-12 08:46:12',0),
 (13,'info@hostkrit.com','8288033280','info@webkrit.com','9815990067','Invoice','3','Transaction Invoice is accepted by lokesh kakkar','2014-09-12 08:46:25',0),
 (14,'info@webkrit.com','9815990067','info@hostkrit.com','8288033280','Invoice','4','Transaction Invoice is received from kakkar lokesh','2014-09-12 10:51:57',0),
 (15,'info@hostkrit.com','8288033280','info@webkrit.com','9815990067','Invoice','4','Transaction Invoice is accepted by lokesh kakkar','2014-09-12 10:58:34',0),
 (17,'info@webkrit.com','null','info@hostkrit.com','8288033280','NewConnectionResponse','null','kakkar lokesh accepted your connection request.','2014-09-12 16:17:17',0),
 (18,'info@webkrit.com','null','info@hostkrit.com','8288033280','PrivateMessage','-1','Private Message is received from kakkar lokesh','2014-09-12 16:17:39',0),
 (19,'info@hostkrit.com','8288033280','info@webkrit.com','9815990067','VendorRegistration','2','webkrit sent Vendor registration form','2014-09-12 16:32:56',0),
 (20,'info@webkrit.com','9815990067','info@hostkrit.com','8288033280','VendorRegistration','0','Vendor registration request accepted by hostkrit','2014-09-12 16:33:45',0),
 (21,'info@hostkrit.com','null','info@webkrit.com','9815990067','PrivateMessage','-1','Private Message is received from lokesh kakkar','2014-09-17 15:54:38',0),
 (22,'info@webkrit.com','null','info@hostkrit.com','8288033280','PrivateMessage','-1','Private Message is received from kakkar lokesh','2014-09-17 15:55:48',0),
 (23,'info@hostkrit.com','null','info@webkrit.com','9815990067','PrivateMessage','-1','Private Message is received from lokesh kakkar','2014-09-17 15:56:39',0),
 (24,'info@hostkrit.com','null','info@webkrit.com','9815990067','PrivateMessage','-1','Private Message is received from lokesh kakkar','2014-09-17 16:04:27',0),
 (25,'info@webkrit.com','null','info@hostkrit.com','8288033280','PrivateMessage','-1','Private Message is received from kakkar lokesh','2014-09-17 16:06:24',0),
 (26,'info@hostkrit.com','null','info@webkrit.com','9815990067','PrivateMessage','-1','Private Message is received from lokesh kakkar','2014-09-17 16:38:35',0),
 (27,'info@hostkrit.com','null','info@webkrit.com','9815990067','PrivateMessage','-1','Private Message is received from lokesh kakkar','2014-09-17 17:22:24',0);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;


--
-- Definition of table `password_history`
--

DROP TABLE IF EXISTS `password_history`;
CREATE TABLE `password_history` (
  `password_history_id` bigint(40) NOT NULL auto_increment,
  `regn_rel_key` varchar(255) NOT NULL,
  `user_rel_key` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `time_val` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`password_history_id`),
  KEY `user_rel_key` (`user_rel_key`),
  KEY `regn_rel_key` (`regn_rel_key`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `password_history`
--

/*!40000 ALTER TABLE `password_history` DISABLE KEYS */;
INSERT INTO `password_history` (`password_history_id`,`regn_rel_key`,`user_rel_key`,`password`,`time_val`) VALUES 
 (1,'9815990067','info@webkrit.com','123456@a','2014-09-12 08:33:03'),
 (2,'8288033280','info@hostkrit.com','123456@a','2014-09-12 08:33:16'),
 (3,'8288033280','dilbagss@webkrit.com','null','2014-09-28 17:07:01'),
 (4,'8288033280','dsdilbag345@gmail.com','null','2014-09-28 17:07:08');
/*!40000 ALTER TABLE `password_history` ENABLE KEYS */;


--
-- Definition of table `password_login_policies`
--

DROP TABLE IF EXISTS `password_login_policies`;
CREATE TABLE `password_login_policies` (
  `regn_rel_key` varchar(255) NOT NULL,
  `password_complexity_flag` tinyint(1) NOT NULL,
  `upper_lower_flag` tinyint(1) NOT NULL,
  `numeric_flag` tinyint(1) NOT NULL,
  `special_characters_flag` tinyint(1) NOT NULL,
  KEY `account_policies_rel_key` (`regn_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `password_login_policies`
--

/*!40000 ALTER TABLE `password_login_policies` DISABLE KEYS */;
INSERT INTO `password_login_policies` (`regn_rel_key`,`password_complexity_flag`,`upper_lower_flag`,`numeric_flag`,`special_characters_flag`) VALUES 
 ('9815990067',1,1,1,1),
 ('8288033280',1,1,1,1);
/*!40000 ALTER TABLE `password_login_policies` ENABLE KEYS */;


--
-- Definition of table `password_policies`
--

DROP TABLE IF EXISTS `password_policies`;
CREATE TABLE `password_policies` (
  `regn_rel_key` varchar(255) NOT NULL,
  `password_history_days` int(4) NOT NULL,
  `password_agemax_days` int(4) NOT NULL,
  `password_agemin_days` int(4) NOT NULL,
  `password_length` int(3) NOT NULL,
  `notification_remainder_nday` int(3) NOT NULL,
  `daily_remainder_flag` tinyint(1) NOT NULL,
  KEY `regn_rel_key` (`regn_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `password_policies`
--

/*!40000 ALTER TABLE `password_policies` DISABLE KEYS */;
INSERT INTO `password_policies` (`regn_rel_key`,`password_history_days`,`password_agemax_days`,`password_agemin_days`,`password_length`,`notification_remainder_nday`,`daily_remainder_flag`) VALUES 
 ('9815990067',5,5000,1,7,3,1),
 ('8288033280',5,5000,1,7,3,1);
/*!40000 ALTER TABLE `password_policies` ENABLE KEYS */;


--
-- Definition of table `po`
--

DROP TABLE IF EXISTS `po`;
CREATE TABLE `po` (
  `po_id` bigint(20) NOT NULL auto_increment,
  `trans_id` bigint(20) NOT NULL,
  `po_num` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `from_regn_key` varchar(255) NOT NULL,
  `to_regn_key` varchar(255) NOT NULL,
  `user_from` varchar(255) NOT NULL,
  `user_to` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `action` varchar(255) NOT NULL,
  `is_outside_supplier` tinyint(1) NOT NULL,
  `outside_supplier_email` varchar(255) NOT NULL,
  `recurring` varchar(255) NOT NULL,
  `po_date` date NOT NULL,
  `total_list_price` double(20,2) NOT NULL,
  `tax_percentage` double(5,2) NOT NULL,
  `total_price` double(20,2) NOT NULL,
  `is_invoice_created` tinyint(1) NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `po_trans_id` varchar(100) default NULL,
  `outside_supplier_address` varchar(500) default NULL,
  PRIMARY KEY  (`po_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `po`
--

/*!40000 ALTER TABLE `po` DISABLE KEYS */;
INSERT INTO `po` (`po_id`,`trans_id`,`po_num`,`from_regn_key`,`to_regn_key`,`user_from`,`user_to`,`status`,`action`,`is_outside_supplier`,`outside_supplier_email`,`recurring`,`po_date`,`total_list_price`,`tax_percentage`,`total_price`,`is_invoice_created`,`created_timestamp`,`po_trans_id`,`outside_supplier_address`) VALUES 
 (1,1,'2014-1','9815990067','8288033280','info@webkrit.com','info@hostkrit.com','POAccepted','CreatePO',0,'null','null','2014-09-12',26000.00,12.36,29213.60,1,'2014-09-12 08:41:35',NULL,'null, null, null, null, null, null');
/*!40000 ALTER TABLE `po` ENABLE KEYS */;


--
-- Definition of table `po_item`
--

DROP TABLE IF EXISTS `po_item`;
CREATE TABLE `po_item` (
  `po_item_id` bigint(20) NOT NULL auto_increment,
  `po_id` bigint(20) NOT NULL,
  `trans_id` bigint(20) NOT NULL,
  `item_desc` text NOT NULL,
  `part_no` varchar(255) NOT NULL,
  `quantity` double(5,2) NOT NULL,
  `ship_date` date NOT NULL,
  `quantity_unit` varchar(255) NOT NULL,
  `price` double(20,2) NOT NULL,
  `currency` varchar(255) NOT NULL,
  `multiplier` int(10) NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `barcode_id` varchar(100) default NULL,
  PRIMARY KEY  (`po_item_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `po_item`
--

/*!40000 ALTER TABLE `po_item` DISABLE KEYS */;
INSERT INTO `po_item` (`po_item_id`,`po_id`,`trans_id`,`item_desc`,`part_no`,`quantity`,`ship_date`,`quantity_unit`,`price`,`currency`,`multiplier`,`created_timestamp`,`barcode_id`) VALUES 
 (1,1,1,'lenovo','b560',1.00,'2014-09-12','KG',26000.00,'USD',26000,'2014-09-12 08:41:35','b560');
/*!40000 ALTER TABLE `po_item` ENABLE KEYS */;


--
-- Definition of table `private_message`
--

DROP TABLE IF EXISTS `private_message`;
CREATE TABLE `private_message` (
  `message_id` bigint(20) NOT NULL auto_increment,
  `from_user_key` varchar(255) NOT NULL,
  `to_user_key` varchar(255) NOT NULL,
  `message` text NOT NULL,
  `from_delete_flag` tinyint(1) NOT NULL default '0',
  `to_delete_flag` tinyint(1) NOT NULL default '0',
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`message_id`),
  KEY `message_id` (`message_id`,`from_user_key`,`to_user_key`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `private_message`
--

/*!40000 ALTER TABLE `private_message` DISABLE KEYS */;
INSERT INTO `private_message` (`message_id`,`from_user_key`,`to_user_key`,`message`,`from_delete_flag`,`to_delete_flag`,`created_timestamp`) VALUES 
 (1,'info@hostkrit.com','info@webkrit.com','sdsad',0,0,'2014-09-12 16:17:39'),
 (2,'info@webkrit.com','info@hostkrit.com','sdfdsf',0,0,'2014-09-17 15:54:38'),
 (3,'info@hostkrit.com','info@webkrit.com','fdgdf',0,0,'2014-09-17 15:55:48'),
 (4,'info@webkrit.com','info@hostkrit.com','hjjhk',0,0,'2014-09-17 15:56:39'),
 (5,'info@webkrit.com','info@hostkrit.com','zfdf',0,0,'2014-09-17 16:04:27'),
 (6,'info@hostkrit.com','info@webkrit.com','vbn',0,0,'2014-09-17 16:06:24'),
 (7,'info@webkrit.com','info@hostkrit.com','ghjh',0,0,'2014-09-17 16:38:35'),
 (8,'info@webkrit.com','info@hostkrit.com','hjkjhk',0,0,'2014-09-17 17:22:24');
/*!40000 ALTER TABLE `private_message` ENABLE KEYS */;


--
-- Definition of table `product_catalog`
--

DROP TABLE IF EXISTS `product_catalog`;
CREATE TABLE `product_catalog` (
  `item_key` varchar(501) NOT NULL COMMENT 'This key uniquely refers a product. This is created at the time of insert operation by combining regn_rel_key and part_number.',
  `item_desc` text NOT NULL COMMENT 'Description about the item. This is text type because description can be more than 255 characters.',
  `part_number` varchar(255) NOT NULL COMMENT 'Part Number. Assumed to be alpha-numeric and the limit set to 255 max.',
  `item_name` varchar(255) NOT NULL COMMENT 'Name of an item. Assumed - max limit is 255 characters.',
  `SKU` varchar(255) NOT NULL,
  `quantity` decimal(12,3) NOT NULL COMMENT 'Quantity of an item. data type is decimal(12, 3).',
  `quan_type_rel_key` varchar(255) NOT NULL COMMENT 'Quantity type. This refers to the table quantity_type. so it is set to 255 max.',
  `price` decimal(11,2) NOT NULL COMMENT 'Price of an item. data type is decimal(11, 2).',
  `hide_price` tinyint(1) NOT NULL,
  `currency_rel_key` varchar(255) NOT NULL COMMENT 'Currency. This refers to the table currencies. So it is set to 255 max.',
  `tax_rate` decimal(11,2) NOT NULL COMMENT 'Tax rate for an item. data type is decimal(11, 2).',
  `regn_rel_key` varchar(255) NOT NULL,
  FULLTEXT KEY `item_name` (`item_name`,`item_desc`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Product Catalog table that has all the items under the compa';

--
-- Dumping data for table `product_catalog`
--

/*!40000 ALTER TABLE `product_catalog` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_catalog` ENABLE KEYS */;


--
-- Definition of table `quote`
--

DROP TABLE IF EXISTS `quote`;
CREATE TABLE `quote` (
  `quote_id` bigint(20) NOT NULL auto_increment,
  `trans_id` bigint(20) NOT NULL,
  `quote_ref` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `from_regn_key` varchar(255) NOT NULL,
  `to_regn_key` varchar(255) NOT NULL,
  `user_from` varchar(255) NOT NULL,
  `user_to` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `action` varchar(255) NOT NULL,
  `is_outside_supplier` tinyint(1) NOT NULL,
  `outside_supplier_email` varchar(255) NOT NULL,
  `recurring` varchar(255) NOT NULL,
  `quote_date` date NOT NULL,
  `total_list_price` double(20,2) NOT NULL,
  `tax_percentage` double(10,2) NOT NULL,
  `total_price` double(20,2) NOT NULL,
  `is_po_created` tinyint(1) NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `quote_trans_id` varchar(100) default NULL,
  `outside_supplier_address` varchar(500) default NULL,
  PRIMARY KEY  (`quote_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `quote`
--

/*!40000 ALTER TABLE `quote` DISABLE KEYS */;
INSERT INTO `quote` (`quote_id`,`trans_id`,`quote_ref`,`from_regn_key`,`to_regn_key`,`user_from`,`user_to`,`status`,`action`,`is_outside_supplier`,`outside_supplier_email`,`recurring`,`quote_date`,`total_list_price`,`tax_percentage`,`total_price`,`is_po_created`,`created_timestamp`,`quote_trans_id`,`outside_supplier_address`) VALUES 
 (1,2,'','9815990067','8288033280','info@webkrit.com','info@hostkrit.com','QuoteCreated','CreateQuote',0,'null','null','2014-09-12',26000.00,25.00,32500.00,0,'2014-09-12 08:40:07',NULL,'null, null, null, null, null, null'),
 (2,1,'','8288033280','9815990067','info@hostkrit.com','info@webkrit.com','QuoteAccepted','CreateQuote',0,'null','null','2014-09-12',26000.00,50.00,39000.00,1,'2014-09-12 08:40:58',NULL,'null, null, null, null, null, null');
/*!40000 ALTER TABLE `quote` ENABLE KEYS */;


--
-- Definition of table `quote_item`
--

DROP TABLE IF EXISTS `quote_item`;
CREATE TABLE `quote_item` (
  `quote_item_id` bigint(20) NOT NULL auto_increment,
  `quote_id` bigint(20) NOT NULL,
  `trans_id` bigint(20) NOT NULL,
  `item_desc` text NOT NULL,
  `part_no` varchar(255) NOT NULL,
  `quantity` double(10,2) NOT NULL,
  `ship_date` date NOT NULL,
  `quantity_unit` varchar(255) NOT NULL,
  `price` double(20,2) NOT NULL,
  `currency` varchar(255) NOT NULL,
  `multiplier` int(10) NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `barcode_id` varchar(100) default NULL,
  PRIMARY KEY  (`quote_item_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `quote_item`
--

/*!40000 ALTER TABLE `quote_item` DISABLE KEYS */;
INSERT INTO `quote_item` (`quote_item_id`,`quote_id`,`trans_id`,`item_desc`,`part_no`,`quantity`,`ship_date`,`quantity_unit`,`price`,`currency`,`multiplier`,`created_timestamp`,`barcode_id`) VALUES 
 (1,1,2,'lenovo','b560',1.00,'2014-09-12','KG',26000.00,'USD',26000,'2014-09-12 08:40:07','b560'),
 (2,2,1,'lenovo','b560',1.00,'2014-09-12','KG',26000.00,'USD',26000,'2014-09-12 08:40:58','b560');
/*!40000 ALTER TABLE `quote_item` ENABLE KEYS */;


--
-- Definition of table `ratings_summary`
--

DROP TABLE IF EXISTS `ratings_summary`;
CREATE TABLE `ratings_summary` (
  `regn_rel_key` varchar(255) NOT NULL,
  `first_rating_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_rating_timestamp` timestamp NOT NULL default '0000-00-00 00:00:00',
  `avg_ratings` int(11) NOT NULL,
  `no_of_ratings` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ratings_summary`
--

/*!40000 ALTER TABLE `ratings_summary` DISABLE KEYS */;
/*!40000 ALTER TABLE `ratings_summary` ENABLE KEYS */;


--
-- Definition of table `regn_vendor_mapper`
--

DROP TABLE IF EXISTS `regn_vendor_mapper`;
CREATE TABLE `regn_vendor_mapper` (
  `regn_rel_key` varchar(255) NOT NULL COMMENT 'This refers to the primary company key regn_key in table company_registration.',
  `regn_rel_key_map` varchar(255) NOT NULL COMMENT 'This refers to the mapped company key in the table company_registration.',
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='regn_vendor_mapper table which maps the registered companies';

--
-- Dumping data for table `regn_vendor_mapper`
--

/*!40000 ALTER TABLE `regn_vendor_mapper` DISABLE KEYS */;
INSERT INTO `regn_vendor_mapper` (`regn_rel_key`,`regn_rel_key_map`,`created_timestamp`) VALUES 
 ('8288033280','9815990067','2014-09-12 16:33:45');
/*!40000 ALTER TABLE `regn_vendor_mapper` ENABLE KEYS */;


--
-- Definition of table `request_for_quote`
--

DROP TABLE IF EXISTS `request_for_quote`;
CREATE TABLE `request_for_quote` (
  `req_quote_no` varchar(255) NOT NULL COMMENT 'This refers to the Quote ref number given by the end user.',
  `item_id` varchar(501) NOT NULL COMMENT 'This will use to refer a product. This is auto gen id at the time of insert operation.',
  `item_desc` text NOT NULL COMMENT 'Description about the item. This is text type because description is more than 255 characters.',
  `part_number` varchar(255) NOT NULL COMMENT 'Part Number. Assumed - may be characters and limit set to 255 max.',
  `item_name` varchar(255) NOT NULL COMMENT 'Name of an item. Assumed - max limit is 255 characters.',
  `quantity` decimal(12,3) NOT NULL COMMENT 'Quantity of an item. data type is decimal(12, 3).',
  `quan_type_rel_key` varchar(255) NOT NULL COMMENT 'Quantity type. This refers to the table quantity_type. so it is set to 255 max.',
  `regn_rel_key` varchar(255) NOT NULL COMMENT 'The company key that requests this item. This refers to regn_key in table company_registration.',
  FULLTEXT KEY `item_name` (`item_name`,`item_desc`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `request_for_quote`
--

/*!40000 ALTER TABLE `request_for_quote` DISABLE KEYS */;
/*!40000 ALTER TABLE `request_for_quote` ENABLE KEYS */;


--
-- Definition of table `rfq`
--

DROP TABLE IF EXISTS `rfq`;
CREATE TABLE `rfq` (
  `rfq_Id` bigint(20) NOT NULL auto_increment,
  `trans_Id` bigint(20) NOT NULL,
  `quote_ref` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `from_regn_key` varchar(255) NOT NULL,
  `to_regn_key` varchar(255) NOT NULL,
  `user_from` varchar(255) NOT NULL,
  `user_to` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `action` varchar(1024) NOT NULL,
  `is_outside_supplier` tinyint(1) NOT NULL,
  `outside_supplier_email` varchar(255) NOT NULL,
  `recurring` varchar(255) NOT NULL,
  `rfq_date` date NOT NULL,
  `is_quote_created` tinyint(1) NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `rfq_trans_id` varchar(100) default NULL,
  `outside_supplier_address` varchar(500) default NULL,
  PRIMARY KEY  (`rfq_Id`),
  KEY `transId_` (`trans_Id`),
  KEY `from_` (`from_regn_key`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rfq`
--

/*!40000 ALTER TABLE `rfq` DISABLE KEYS */;
INSERT INTO `rfq` (`rfq_Id`,`trans_Id`,`quote_ref`,`from_regn_key`,`to_regn_key`,`user_from`,`user_to`,`status`,`action`,`is_outside_supplier`,`outside_supplier_email`,`recurring`,`rfq_date`,`is_quote_created`,`created_timestamp`,`rfq_trans_id`,`outside_supplier_address`) VALUES 
 (1,1,'','9815990067','8288033280','info@webkrit.com','info@hostkrit.com','RFQAccepted','null',0,'null','null','2014-09-12',1,'2014-09-12 08:40:58',NULL,'null, null, null, null, null, null');
/*!40000 ALTER TABLE `rfq` ENABLE KEYS */;


--
-- Definition of table `rfq_item`
--

DROP TABLE IF EXISTS `rfq_item`;
CREATE TABLE `rfq_item` (
  `rfq_item_id` bigint(20) NOT NULL auto_increment,
  `trans_Id` bigint(20) NOT NULL,
  `rfq_Id` bigint(20) NOT NULL,
  `item_desc` text NOT NULL,
  `part_no` varchar(255) NOT NULL,
  `quantity` double(10,4) NOT NULL,
  `ship_date` date NOT NULL,
  `quantity_unit` varchar(255) NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `barcode_id` varchar(100) default NULL,
  PRIMARY KEY  (`rfq_item_id`),
  KEY `RFQ_Id` (`rfq_Id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rfq_item`
--

/*!40000 ALTER TABLE `rfq_item` DISABLE KEYS */;
INSERT INTO `rfq_item` (`rfq_item_id`,`trans_Id`,`rfq_Id`,`item_desc`,`part_no`,`quantity`,`ship_date`,`quantity_unit`,`created_timestamp`,`barcode_id`) VALUES 
 (1,1,1,'lenovo','b560',1.0000,'2014-09-12','KG','2014-09-12 08:39:16','b560');
/*!40000 ALTER TABLE `rfq_item` ENABLE KEYS */;


--
-- Definition of table `sm_config`
--

DROP TABLE IF EXISTS `sm_config`;
CREATE TABLE `sm_config` (
  `reg_link_expire_days` int(5) NOT NULL,
  `recycle_files_expire_days` int(3) NOT NULL,
  `tax_percentage` double(5,2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config`
--

/*!40000 ALTER TABLE `sm_config` DISABLE KEYS */;
INSERT INTO `sm_config` (`reg_link_expire_days`,`recycle_files_expire_days`,`tax_percentage`) VALUES 
 (7,7,10.00);
/*!40000 ALTER TABLE `sm_config` ENABLE KEYS */;


--
-- Definition of table `sm_config_account_policies`
--

DROP TABLE IF EXISTS `sm_config_account_policies`;
CREATE TABLE `sm_config_account_policies` (
  `password_history_days` int(4) NOT NULL,
  `password_agemax_days` int(4) NOT NULL,
  `password_agemin_days` int(4) NOT NULL,
  `password_length` int(3) NOT NULL,
  `notification_remainder_nday` int(3) NOT NULL,
  `daily_remainder_flag` tinyint(1) NOT NULL,
  `invalid_login_attempts` int(2) NOT NULL,
  `lockout_duration_min` int(3) NOT NULL,
  `reset_lockout_min` int(3) NOT NULL,
  `admin_unlock_flag` tinyint(1) NOT NULL,
  `expire_session_min` int(3) NOT NULL,
  `password_complexity_flag` tinyint(1) NOT NULL,
  `upper_lower_flag` tinyint(1) NOT NULL,
  `numeric_flag` tinyint(1) NOT NULL,
  `special_characters_flag` tinyint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_account_policies`
--

/*!40000 ALTER TABLE `sm_config_account_policies` DISABLE KEYS */;
INSERT INTO `sm_config_account_policies` (`password_history_days`,`password_agemax_days`,`password_agemin_days`,`password_length`,`notification_remainder_nday`,`daily_remainder_flag`,`invalid_login_attempts`,`lockout_duration_min`,`reset_lockout_min`,`admin_unlock_flag`,`expire_session_min`,`password_complexity_flag`,`upper_lower_flag`,`numeric_flag`,`special_characters_flag`) VALUES 
 (5,5000,1,7,3,1,5,10,5,1,30,1,1,1,1);
/*!40000 ALTER TABLE `sm_config_account_policies` ENABLE KEYS */;


--
-- Definition of table `sm_config_carrier`
--

DROP TABLE IF EXISTS `sm_config_carrier`;
CREATE TABLE `sm_config_carrier` (
  `carrier_id` bigint(20) NOT NULL auto_increment,
  `carrier_name` varchar(255) NOT NULL,
  PRIMARY KEY  (`carrier_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_carrier`
--

/*!40000 ALTER TABLE `sm_config_carrier` DISABLE KEYS */;
INSERT INTO `sm_config_carrier` (`carrier_id`,`carrier_name`) VALUES 
 (1,'Professional courier'),
 (2,'First Flight');
/*!40000 ALTER TABLE `sm_config_carrier` ENABLE KEYS */;


--
-- Definition of table `sm_config_company_email_domain`
--

DROP TABLE IF EXISTS `sm_config_company_email_domain`;
CREATE TABLE `sm_config_company_email_domain` (
  `company_name` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `email_domain` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  KEY `company_name` (`company_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_company_email_domain`
--

/*!40000 ALTER TABLE `sm_config_company_email_domain` DISABLE KEYS */;
INSERT INTO `sm_config_company_email_domain` (`company_name`,`email_domain`) VALUES 
 ('Royal Dutch Shell','shell.com'),
 ('BP','bp.com'),
 ('Sinopec Group','sinopecgroup.com'),
 ('China National Petroleum','cnpc.com.cn'),
 ('State Grid','sgcc.com.cn'),
 ('Toyota Motor','toyota-global.com'),
 ('Total','total.com'),
 ('Volkswagen','volkswagenag.com'),
 ('Japan Post Holdings','japanpost.jp'),
 ('Glencore International','glencore.com'),
 ('Gazprom','gazprom.com'),
 ('E.ON','eon.com'),
 ('ENI','eni.com'),
 ('ING Group','ing.com'),
 ('Samsung Electronics','samsung.com'),
 ('Daimler','daimler.com'),
 ('Petrobras','petrobras.com.br'),
 ('AXA','axa.com'),
 ('Allianz','allianz.com'),
 ('Nippon Telegraph & Telephone','ntt.co.jp'),
 ('BNP Paribas','bnpparibas.com'),
 ('GDF Suez','gdfsuez.com'),
 ('Pemex','pemex.com'),
 ('PDVSA','pdvsa.com'),
 ('Hitachi','hitachi.com'),
 ('Carrefour','carrefour.com'),
 ('Statoil ','statoil.com'),
 ('JX Holdings','hd.jx-group.co.jp'),
 ('Nissan Motor','nissan-global.com'),
 ('Hon Hai Precision Industry','foxconn.com'),
 ('Banco Santander','santander.com'),
 ('EXOR Group','exor.com'),
 ('Siemens','siemens.com'),
 ('Assicurazioni Generali','generali.com'),
 ('Lukoil','lukoil.com'),
 ('Enel','enel.com'),
 ('HSBC Holdings','hsbc.com'),
 ('Industrial & Commercial Bank of China','icbc.com.cn'),
 ('Tesco','tescoplc.com'),
 ('BASF','basf.com'),
 ('Honda Motor','honda.com'),
 ('SK Holdings ','sk.com'),
 ('Panasonic','panasonic.net'),
 ('Petronas','petronas.com.my'),
 ('BMW','bmwgroup.com'),
 ('ArcelorMittal','arcelormittal.com'),
 ('Metro','metrogroup.de'),
 ('','edf.fr'),
 ('Nippon Life Insurance','nissay.co.jp'),
 ('Munich Re Group','munichre.com'),
 ('China Construction Bank','ccb.com'),
 ('China Mobile Communications','chinamobileltd.com'),
 ('Telef','telefonica.com'),
 ('Indian Oil','iocl.com'),
 ('Agricultural Bank of China','abchina.com'),
 ('Peugeot','psa-peugeot-citroen.com'),
 ('Sony','sony.net'),
 ('Banco do Brasil','bb.com.br'),
 ('Deutsche Telekom','telekom.com'),
 ('Repsol YPF','repsol.com'),
 ('Noble Group','thisisnoble.com'),
 ('Bank of China','boc.cn'),
 ('PTT','pttplc.com'),
 ('Meiji Yasuda Life Insurance','meijiyasuda.co.jp'),
 ('Toshiba','toshiba.co.jp'),
 ('Deutsche Post','dp-dhl.com'),
 ('Reliance Industries','ril.com'),
 ('China State Construction Engineering','cscec.com'),
 ('China National Offshore Oil','cnooc.com.cn'),
 ('Groupe BPCE ','bpce.fr'),
 ('Deutsche Bank','deutsche-bank.de'),
 ('Vodafone Group','vodafone.com'),
 ('BHP Billiton','bhpbilliton.com'),
 ('Robert Bosch','bosch.com'),
 ('China Railway Construction ','crcc.cn'),
 ('China Railway Group','crec.cn'),
 ('Sinochem Group','sinochem.com'),
 ('Mitsubishi ','mitsubishicorp.com'),
 ('Hyundai Motor','worldwide.hyundai.com'),
 ('Barclays','barclays.com'),
 ('ThyssenKrupp','thyssenkrupp.com'),
 ('RWE','rwe.com'),
 ('EADS','eads.com'),
 ('Tokyo Electric Power','tepco.co.jp'),
 ('Landesbank Baden-W','lbbw.de'),
 ('China Life Insurance','chinalife.com.cn'),
 ('SAIC Motor','saicmotor.com'),
 ('Lloyds Banking Group','lloydsbankinggroup.com'),
 ('Mitsui','mitsui.co.jp'),
 ('AEON','aeon.info'),
 ('U.S. Postal Service','usps.com'),
 ('Banco Bradesco','bradesco.com.br'),
 ('Rosneft Oil','rosneft.com'),
 ('Unilever','unilever.com'),
 ('France T','orange.com'),
 ('Dongfeng Motor Group ','dfmc.com.cn'),
 ('Royal Bank of Scotland Group','rbs.com'),
 ('Mitsubishi UFJ Financial Group','mufg.jp'),
 ('Dai-ichi Life Insurance','dai-ichi-life.co.jp'),
 ('POSCO','posco.com'),
 ('Aviva','aviva.com'),
 ('Groupe Auchan','groupe-auchan.com'),
 ('Seven & I Holdings','7andi.com'),
 ('China Southern Power Grid','csg.cn'),
 ('Rio Tinto Group','riotinto.com'),
 ('A.P. M','maersk.com'),
 ('Novartis','novartis.com'),
 ('Renault','renault.com'),
 ('Vale','vale.com'),
 ('Bunge','bunge.com'),
 ('Saint-Gobain','saint-gobain.com'),
 ('Prudential ','prudential.co.uk'),
 ('UniCredit Group ','unicreditgroup.eu'),
 ('China FAW Group','faw.com'),
 ('Fujitsu','fujitsu.com'),
 ('Marubeni','marubeni.com'),
 ('China Minmetals','minmetals.com'),
 ('Wesfarmers ','wesfarmers.com.au'),
 ('Itochu','itochu.co.jp'),
 ('Nokia','nokia.com'),
 ('Woolworths','woolworthslimited.com.au'),
 ('Am','americamovil.com'),
 ('Zurich Insurance Group','zurich.com'),
 ('Deutsche Bahn','deutschebahn.com'),
 ('Nippon Steel','nsc.co.jp'),
 ('Manulife Financial','manulife.com'),
 ('CNP Assurances','cnp.fr'),
 ('Vinci','vinci.com'),
 ('LyondellBasell Industries ','lyondellbasell.com'),
 ('Banco Bilbao Vizcaya Argentaria','bbva.com'),
 ('Bayer','bayer.com'),
 ('Sabic','sabic.com'),
 ('SSE','sse.com'),
 ('Sumitomo Mitsui Financial Group','smfg.co.jp'),
 ('Roche Group','roche.com'),
 ('Intesa Sanpaolo ','group.intesasanpaolo.com'),
 ('CITIC Group','citic.com'),
 ('LG Electronics','lg.com'),
 ('Baosteel Group','baosteel.com'),
 ('TNK-BP International','tnk-bp.com'),
 ('Idemitsu Kosan','idemitsu.com'),
 ('Fonci','fonciere-euris.fr'),
 ('Sanofi','sanofi.com'),
 ('Veolia Environnement','veolia.com'),
 ('Hyundai Heavy Industries','english.hhi.co.kr'),
 ('Credit Suisse Group','credit-suisse.com'),
 ('China North Industries Group ','norincogroup.com.cn'),
 ('Volvo','volvogroup.com'),
 ('MS&AD Insurance Group Holdings','ms-ad-hd.com'),
 ('OMV Group','omv.com'),
 ('Mitsubishi Electric','mitsubishielectric.com'),
 ('UBS','ubs.com'),
 ('China Communications Construction','ccccltd.cn'),
 ('Bouygues','bouygues.com'),
 ('SNCF','sncf.com'),
 ('KDDI','kddi.com'),
 ('China Telecommunications','chinatelecom.com.cn'),
 ('Ko','koc.com.tr'),
 ('Wilmar International','wilmar-international.com'),
 ('Canon','canon.com'),
 ('Bharat Petroleum','bharatpetroleum.in'),
 ('Commonwealth Bank of Australia','commbank.com.au'),
 ('Aegon','aegon.com'),
 ('Westpac Banking','westpac.com.au'),
 ('Iberdrola','iberdrola.es'),
 ('GlaxoSmithKline','gsk.com'),
 ('Safeway','safeway.com'),
 ('China Resources National','crc.com.hk'),
 ('Shenhua Group','shenhuagroup.com.cn'),
 ('GS Caltex','gscaltex.com'),
 ('Tokio Marine Holdings ','tokiomarinehd.com'),
 ('China South Industries Group','chinasouth.com.cn'),
 ('Sumitomo Life Insurance','sumitomolife.co.jp'),
 ('ACS','grupoacs.com'),
 ('Continental','continental-corporation.com'),
 ('Ping An Insurance ','pingan.com'),
 ('Royal Ahold','ahold.com');
/*!40000 ALTER TABLE `sm_config_company_email_domain` ENABLE KEYS */;


--
-- Definition of table `sm_config_countries`
--

DROP TABLE IF EXISTS `sm_config_countries`;
CREATE TABLE `sm_config_countries` (
  `country_id` bigint(20) unsigned NOT NULL auto_increment,
  `country_name` varchar(255) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`country_id`),
  KEY `country_name` (`country_name`)
) ENGINE=MyISAM AUTO_INCREMENT=232 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `sm_config_countries`
--

/*!40000 ALTER TABLE `sm_config_countries` DISABLE KEYS */;
INSERT INTO `sm_config_countries` (`country_id`,`country_name`) VALUES 
 (1,'Afghanistan'),
 (2,'Albania'),
 (3,'Algeria'),
 (4,'American Samoa'),
 (5,'Andorra'),
 (6,'Angola'),
 (7,'Anguilla'),
 (8,'Antarctica'),
 (9,'Antigua and Barbuda'),
 (10,'Argentina'),
 (11,'Armenia'),
 (12,'Aruba'),
 (13,'Ascension'),
 (14,'Australia'),
 (15,'Austria'),
 (16,'Azerbaijan'),
 (17,'Bahamas'),
 (18,'Bahrain'),
 (19,'Bangladesh'),
 (20,'Barbados'),
 (21,'Belarus'),
 (22,'Belgium'),
 (23,'Belize'),
 (24,'Benin'),
 (25,'Bermuda'),
 (26,'Bhutan'),
 (27,'Bolivia'),
 (28,'Bosnia and Herzegovina'),
 (29,'Botswana'),
 (30,'Brazil'),
 (31,'Brunei'),
 (32,'Bulgaria'),
 (33,'Burkina Faso'),
 (34,'Burundi'),
 (35,'Cambodia'),
 (36,'Cameroon'),
 (37,'Canada'),
 (38,'Cape Verde'),
 (39,'Cayman Islands'),
 (40,'Central African Republic'),
 (41,'Chad'),
 (42,'Chile'),
 (43,'China'),
 (44,'Christmas Island'),
 (45,'Cocos (Keeling) Islands'),
 (46,'Colombia'),
 (47,'Comoros'),
 (48,'Congo'),
 (49,'Cook Islands'),
 (50,'Costa Rica'),
 (51,'C'),
 (52,'Croatia'),
 (53,'Cuba'),
 (54,'Cyprus'),
 (55,'Czech Republic'),
 (56,'Denmark'),
 (57,'Diego Garcia'),
 (58,'Djibouti'),
 (59,'Dominica'),
 (60,'Dominican Republic'),
 (61,'East Timor'),
 (62,'Ecuador'),
 (63,'Egypt'),
 (64,'El Salvador'),
 (65,'Equatorial Guinea'),
 (66,'Eritrea'),
 (67,'Estonia'),
 (68,'Ethiopia'),
 (69,'Faeroe Islands'),
 (70,'Falkland Islands'),
 (71,'Fiji'),
 (72,'Finland'),
 (73,'France'),
 (74,'French Guiana'),
 (75,'French Polynesia'),
 (76,'Gabon'),
 (77,'Gambia'),
 (78,'Georgia'),
 (79,'Germany'),
 (80,'Ghana'),
 (81,'Gibraltar'),
 (82,'Greece'),
 (83,'Greenland'),
 (84,'Grenada'),
 (85,'Guadeloupe'),
 (86,'Guam'),
 (87,'Guatemala'),
 (88,'Guinea'),
 (89,'Guinea-Bissau'),
 (90,'Guyana'),
 (91,'Haiti'),
 (92,'Honduras'),
 (93,'Hong Kong'),
 (94,'Hungary'),
 (95,'Iceland'),
 (96,'India'),
 (97,'Indonesia'),
 (98,'Iran'),
 (99,'Iraq'),
 (100,'Ireland'),
 (101,'Israel'),
 (102,'Italy'),
 (103,'Jamaica'),
 (104,'Japan'),
 (105,'Jordan'),
 (106,'Kazakhstan'),
 (107,'Kenya'),
 (108,'Kiribati'),
 (109,'Korea (North)'),
 (110,'Korea (South)'),
 (111,'Kuwait'),
 (112,'Kyrgyzstan'),
 (113,'Laos'),
 (114,'Latvia'),
 (115,'Lebanon'),
 (116,'Lesotho'),
 (117,'Liberia'),
 (118,'Libya'),
 (119,'Liechtenstein'),
 (120,'Lithuania'),
 (121,'Luxembourg '),
 (122,'Macau'),
 (123,'Macedonia'),
 (124,'Madagascar'),
 (125,'Malawi'),
 (126,'Malaysia'),
 (127,'Maldives'),
 (128,'Mali'),
 (129,'Malta'),
 (130,'Marshall Islands'),
 (131,'Martinique'),
 (132,'Mauritania'),
 (133,'Mauritius'),
 (134,'Mayotte'),
 (135,'Mexico'),
 (136,'Micronesia'),
 (137,'Moldova'),
 (138,'Monaco'),
 (139,'Mongolia'),
 (140,'Montserrat'),
 (141,'Morocco'),
 (142,'Mozambique'),
 (143,'Myanmar'),
 (144,'Namibia'),
 (145,'Nauru'),
 (146,'Nepal'),
 (147,'Netherlands'),
 (148,'Netherlands Antilles'),
 (149,'New Caledonia'),
 (150,'New Zealand'),
 (151,'Nicaragua'),
 (152,'Niger'),
 (153,'Nigeria'),
 (154,'Niue'),
 (155,'Norfolk Island'),
 (156,'Northern Marianas'),
 (157,'Norway'),
 (158,'Oman'),
 (159,'Pakistan'),
 (160,'Palau'),
 (161,'Palestinian Settlements'),
 (162,'Panama'),
 (163,'Papua New Guinea'),
 (164,'Paraguay'),
 (165,'Peru'),
 (166,'Philippines'),
 (167,'Poland'),
 (168,'Portugal'),
 (169,'Puerto Rico'),
 (170,'Qatar'),
 (171,'R'),
 (172,'Romania'),
 (173,'Russia'),
 (174,'Rwanda'),
 (175,'Saint Helena'),
 (176,'Saint Kitts and Nevis'),
 (177,'Saint Lucia'),
 (178,'Saint Pierre and Miquelon'),
 (179,'Saint Vincent and Grenadines'),
 (180,'Samoa'),
 (181,'San Marino'),
 (182,'S'),
 (183,'Saudi Arabia'),
 (184,'Senegal'),
 (185,'Serbia'),
 (186,'Seychelles'),
 (187,'Sierra Leone'),
 (188,'Singapore'),
 (189,'Slovakia'),
 (190,'Slovenia'),
 (191,'Solomon Islands'),
 (192,'Somalia'),
 (193,'South Africa'),
 (194,'Spain'),
 (195,'Sri Lanka'),
 (196,'Sudan'),
 (197,'Suriname'),
 (198,'Swaziland'),
 (199,'Sweden'),
 (200,'Switzerland'),
 (201,'Syria'),
 (202,'Taiwan'),
 (203,'Tajikistan'),
 (204,'Tanzania'),
 (205,'Thailand'),
 (206,'Togo'),
 (207,'Tokelau'),
 (208,'Tonga'),
 (209,'Trinidad and Tobago'),
 (210,'Tunisia'),
 (211,'Turkey'),
 (212,'Turkmenistan'),
 (213,'Turks and Caicos Islands'),
 (214,'Tuvalu'),
 (215,'Uganda'),
 (216,'Ukraine'),
 (217,'United Arab Emirates'),
 (218,'United Kingdom'),
 (219,'United States'),
 (220,'Uruguay'),
 (221,'US Virgin Islands'),
 (222,'Uzbekistan'),
 (223,'Vanuatu'),
 (224,'Venezuela'),
 (225,'Vietnam'),
 (226,'Virgin Islands'),
 (227,'Wake Island'),
 (228,'Wallis and Futuna'),
 (229,'Yemen'),
 (230,'Zambia'),
 (231,'Zimbabwe');
/*!40000 ALTER TABLE `sm_config_countries` ENABLE KEYS */;


--
-- Definition of table `sm_config_country_code`
--

DROP TABLE IF EXISTS `sm_config_country_code`;
CREATE TABLE `sm_config_country_code` (
  `country_name` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `country_code` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  KEY `country_name` (`country_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_country_code`
--

/*!40000 ALTER TABLE `sm_config_country_code` DISABLE KEYS */;
INSERT INTO `sm_config_country_code` (`country_name`,`country_code`) VALUES 
 ('Afghanistan','93'),
 ('Albania','355'),
 ('Algeria','213'),
 ('American Samoa','1684'),
 ('Andorra','376'),
 ('Angola','244'),
 ('Anguilla','1264'),
 ('Antarctica','6721'),
 ('Antigua and Barbuda','1268'),
 ('Argentina','54'),
 ('Armenia','374'),
 ('Aruba','297'),
 ('Ascension','247'),
 ('Australia','61'),
 ('Austria','43'),
 ('Azerbaijan','994'),
 ('Bahamas','1242'),
 ('Bahrain','973'),
 ('Bangladesh','880'),
 ('Barbados','1246'),
 ('Belarus','375'),
 ('Belgium','32'),
 ('Belize','501'),
 ('Benin','229'),
 ('Bermuda','1441'),
 ('Bhutan','975'),
 ('Bolivia','591'),
 ('Bosnia and Herzegovina','387'),
 ('Botswana','267'),
 ('Brazil','55'),
 ('Brunei','673'),
 ('Bulgaria','359'),
 ('Burkina Faso','226'),
 ('Burundi','257'),
 ('Cambodia','855'),
 ('Cameroon','237'),
 ('Canada','1'),
 ('Cape Verde','238'),
 ('Cayman Islands','1345'),
 ('Central African Republic','236'),
 ('Chad','235'),
 ('Chile','56'),
 ('China','86'),
 ('Christmas Island','618'),
 ('Cocos (Keeling) Islands','618'),
 ('Colombia','57'),
 ('Comoros','269'),
 ('Congo','243'),
 ('Cook Islands','682'),
 ('Costa Rica','506'),
 ('C','225'),
 ('Croatia','385'),
 ('Cuba','53'),
 ('Cyprus','357'),
 ('Czech Republic','420'),
 ('Denmark','45'),
 ('Diego Garcia','246'),
 ('Djibouti','253'),
 ('Dominica','1767'),
 ('Dominican Republic','1809'),
 ('East Timor','670'),
 ('Ecuador','593'),
 ('Egypt','20'),
 ('El Salvador','503'),
 ('Equatorial Guinea','240'),
 ('Eritrea','291'),
 ('Estonia','372'),
 ('Ethiopia','251'),
 ('Faeroe Islands','500'),
 ('Falkland Islands','298'),
 ('Fiji','679'),
 ('Finland','358'),
 ('France','33'),
 ('French Guiana','594'),
 ('French Polynesia','689'),
 ('Gabon','241'),
 ('Gambia','220'),
 ('Georgia','995'),
 ('Germany','49'),
 ('Ghana','233'),
 ('Gibraltar','350'),
 ('Greece','30'),
 ('Greenland','299'),
 ('Grenada','1473'),
 ('Guadeloupe','590'),
 ('Guam','1671'),
 ('Guatemala','502'),
 ('Guinea','224'),
 ('Guinea-Bissau','245'),
 ('Guyana','592'),
 ('Haiti','509'),
 ('Honduras','504'),
 ('Hong Kong','852'),
 ('Hungary','36'),
 ('Iceland','354'),
 ('India','91'),
 ('Indonesia','62'),
 ('Iran','98'),
 ('Iraq','964'),
 ('Ireland','353'),
 ('Israel','972'),
 ('Italy','39'),
 ('Jamaica','1876'),
 ('Japan','81'),
 ('Jordan','962'),
 ('Kazakhstan','77'),
 ('Kenya','254'),
 ('Kiribati','686'),
 ('Korea (North)','850'),
 ('Korea (South)','82'),
 ('Kuwait','965'),
 ('Kyrgyzstan','996'),
 ('Laos','856'),
 ('Latvia','371'),
 ('Lebanon','961'),
 ('Lesotho','266'),
 ('Liberia','231'),
 ('Libya','218'),
 ('Liechtenstein','423'),
 ('Lithuania','370'),
 ('Luxembourg ','352'),
 ('Macau','853'),
 ('Macedonia','389'),
 ('Madagascar','261'),
 ('Malawi','265'),
 ('Malaysia','60'),
 ('Maldives','960'),
 ('Mali','223'),
 ('Malta','356'),
 ('Marshall Islands','692'),
 ('Martinique','596'),
 ('Mauritania','222'),
 ('Mauritius','230'),
 ('Mayotte','52'),
 ('Mexico','691'),
 ('Micronesia','373'),
 ('Moldova','377'),
 ('Monaco','976'),
 ('Mongolia','382'),
 ('Montserrat','1664'),
 ('Morocco','212'),
 ('Mozambique','258'),
 ('Myanmar','95'),
 ('Namibia','264'),
 ('Nauru','674'),
 ('Nepal','977'),
 ('Netherlands','31'),
 ('Netherlands Antilles','599'),
 ('New Caledonia','687'),
 ('New Zealand','64'),
 ('Nicaragua','505'),
 ('Niger','227'),
 ('Nigeria','234'),
 ('Niue','683'),
 ('Norfolk Island','6723'),
 ('Northern Marianas','1670'),
 ('Norway','47'),
 ('Oman','968'),
 ('Pakistan','92'),
 ('Palau','680'),
 ('Palestinian Settlements','970'),
 ('Panama','507'),
 ('Papua New Guinea','675'),
 ('Paraguay','595'),
 ('Peru','51'),
 ('Philippines','63'),
 ('Poland','48'),
 ('Portugal','351'),
 ('Puerto Rico','1787'),
 ('Qatar','974'),
 ('R','262'),
 ('Romania','40'),
 ('Russia','7'),
 ('Rwanda','250'),
 ('Saint Helena','290'),
 ('Saint Kitts and Nevis','1869'),
 ('Saint Lucia','1758'),
 ('Saint Pierre and Miquelon','508'),
 ('Saint Vincent and Grenadines','1784'),
 ('Samoa','685'),
 ('San Marino','378'),
 ('S','239'),
 ('Saudi Arabia','966'),
 ('Senegal','221'),
 ('Serbia','381'),
 ('Seychelles','248'),
 ('Sierra Leone','232'),
 ('Singapore','65'),
 ('Slovakia','421'),
 ('Slovenia','386'),
 ('Solomon Islands','677'),
 ('Somalia','252'),
 ('South Africa','27'),
 ('Spain','34'),
 ('Sri Lanka','94'),
 ('Sudan','249'),
 ('Suriname','597'),
 ('Swaziland','268'),
 ('Sweden','46'),
 ('Switzerland','41'),
 ('Syria','963'),
 ('Taiwan','886'),
 ('Tajikistan','992'),
 ('Tanzania','255'),
 ('Thailand','66'),
 ('Togo','228'),
 ('Tokelau','690'),
 ('Tonga','676'),
 ('Trinidad and Tobago','1868'),
 ('Tunisia','216'),
 ('Turkey','90'),
 ('Turkmenistan','993'),
 ('Turks and Caicos Islands','1649'),
 ('Tuvalu','688'),
 ('Uganda','256'),
 ('Ukraine','380'),
 ('United Arab Emirates','971'),
 ('United Kingdom','44'),
 ('United States','1'),
 ('Uruguay','598'),
 ('US Virgin Islands','1340'),
 ('Uzbekistan','998'),
 ('Vanuatu','678'),
 ('Venezuela','58'),
 ('Vietnam','84'),
 ('Virgin Islands','1284'),
 ('Wake Island','808'),
 ('Wallis and Futuna','681'),
 ('Yemen','967'),
 ('Zambia','260'),
 ('Zimbabwe','263');
/*!40000 ALTER TABLE `sm_config_country_code` ENABLE KEYS */;


--
-- Definition of table `sm_config_currencies`
--

DROP TABLE IF EXISTS `sm_config_currencies`;
CREATE TABLE `sm_config_currencies` (
  `currency_key` varchar(255) NOT NULL COMMENT 'Currency Key. Data limit is 255 max.',
  `currency_name` varchar(255) NOT NULL COMMENT 'Currency name. Data limit is 255 max.',
  PRIMARY KEY  (`currency_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Currency Master table that has USD, INR, etc.,';

--
-- Dumping data for table `sm_config_currencies`
--

/*!40000 ALTER TABLE `sm_config_currencies` DISABLE KEYS */;
INSERT INTO `sm_config_currencies` (`currency_key`,`currency_name`) VALUES 
 ('USD','US Dollar'),
 ('AED','United Arab Emirates dirham'),
 ('AFN','Afghani'),
 ('ALL','Lek'),
 ('AMD','Armenian Dram'),
 ('ANG','Netherlands Antillian Guilder'),
 ('AOA','Kwanza'),
 ('ARS','Argentine Peso'),
 ('AUD','Australian Dollar'),
 ('AWG','Aruban Guilder'),
 ('AZN','Azerbaijanian Manat'),
 ('BAM','Convertible Marks'),
 ('BBD','Barbados Dollar'),
 ('BDT','Bangladeshi Taka'),
 ('BGN','Bulgarian Lev'),
 ('BHD','Bahraini Dinar'),
 ('BIF','Burundian Franc'),
 ('BMD','Bermudian Dollar (customarily known as Bermuda Dollar)'),
 ('BND','Brunei Dollar'),
 ('BOB','Boliviano'),
 ('BOV','Bolivian Mvdol (Funds code)'),
 ('BRL','Brazilian Real'),
 ('BSD','Bahamian Dollar'),
 ('BTN','Ngultrum'),
 ('BWP','Pula'),
 ('BYR','Belarussian Ruble'),
 ('BZD','Belize Dollar'),
 ('CAD','Canadian Dollar'),
 ('CDF','Franc Congolais'),
 ('CHE','WIR Euro (complementary currency)'),
 ('CHF','Swiss Franc'),
 ('CHW','WIR Franc (complementary currency)'),
 ('CLF','Unidades de formento (Funds code)'),
 ('CLP','Chilean Peso'),
 ('CNY','Yuan Renminbi'),
 ('COP','Colombian Peso'),
 ('COU','Unidad de Valor Real'),
 ('CRC','Costa Rican Colon'),
 ('CUP','Cuban Peso'),
 ('CVE','Cape Verde Escudo'),
 ('CYP','Cyprus Pound'),
 ('CZK','Czech Koruna'),
 ('DJF','Djibouti Franc'),
 ('DKK','Danish Krone'),
 ('DOP','Dominican Peso'),
 ('DZD','Algerian Dinar'),
 ('EEK','Kroon'),
 ('EGP','Egyptian Pound'),
 ('ERN','Nakfa'),
 ('ETB','Ethiopian Birr'),
 ('EUR','Euro'),
 ('FJD','Fiji Dollar'),
 ('FKP','Falkland Islands Pound'),
 ('GBP','Pound Sterling'),
 ('GEL','Lari'),
 ('GHS','Cedi'),
 ('GIP','Gibraltar pound'),
 ('GMD','Dalasi'),
 ('GNF','Guinea Franc'),
 ('GTQ','Quetzal'),
 ('GYD','Guyana Dollar'),
 ('HKD','Hong Kong Dollar'),
 ('HNL','Lempira'),
 ('HRK','Croatian Kuna'),
 ('HTG','Haiti Gourde'),
 ('HUF','Forint'),
 ('IDR','Rupiah'),
 ('ILS','New Israeli Shekel'),
 ('INR','Indian Rupee'),
 ('IQD','Iraqi Dinar'),
 ('IRR','Iranian Rial'),
 ('ISK','Iceland Krona'),
 ('JMD','Jamaican Dollar'),
 ('JOD','Jordanian Dinar'),
 ('JPY','Japanese yen'),
 ('KES','Kenyan Shilling'),
 ('KGS','Som'),
 ('KHR','Riel'),
 ('KMF','Comoro Franc'),
 ('KPW','North Korean Won'),
 ('KRW','South Korean Won'),
 ('KWD','Kuwaiti Dinar'),
 ('KYD','Cayman Islands Dollar'),
 ('KZT','Tenge'),
 ('LAK','Kip'),
 ('LBP','Lebanese Pound'),
 ('LKR','Sri Lanka Rupee'),
 ('LRD','Liberian Dollar'),
 ('LSL','Loti'),
 ('LTL','Lithuanian Litas'),
 ('LVL','Latvian Lats'),
 ('LYD','Libyan Dinar'),
 ('MAD','Moroccan Dirham'),
 ('MDL','Moldovan Leu'),
 ('MGA','Malagasy Ariary'),
 ('MKD','Denar'),
 ('MMK','Kyat'),
 ('MNT','Tugrik'),
 ('MOP','Pataca'),
 ('MRO','Ouguiya'),
 ('MTL','Maltese Lira'),
 ('MUR','Mauritius Rupee'),
 ('MVR','Rufiyaa'),
 ('MWK','Kwacha'),
 ('MXN','Mexican Peso'),
 ('MXV','Mexican Unidad de Inversion (UDI) (Funds code)'),
 ('MYR','Malaysian Ringgit'),
 ('MZN','Metical'),
 ('NAD','Namibian Dollar'),
 ('NGN','Naira'),
 ('NIO','Cordoba Oro'),
 ('NOK','Norwegian Krone'),
 ('NPR','Nepalese Rupee'),
 ('NZD','New Zealand Dollar'),
 ('OMR','Rial Omani'),
 ('PAB','Balboa'),
 ('PEN','Nuevo Sol'),
 ('PGK','Kina'),
 ('PHP','Philippine Peso'),
 ('PKR','Pakistan Rupee'),
 ('PLN','Zloty'),
 ('PYG','Guarani'),
 ('QAR','Qatari Rial'),
 ('RON','Romanian New Leu'),
 ('RSD','Serbian Dinar'),
 ('RUB','Russian Ruble'),
 ('RWF','Rwanda Franc'),
 ('SAR','Saudi Riyal'),
 ('SBD','Solomon Islands Dollar'),
 ('SCR','Seychelles Rupee'),
 ('SDG','Sudanese Pound'),
 ('SEK','Swedish Krona'),
 ('SGD','Singapore Dollar'),
 ('SHP','Saint Helena Pound'),
 ('SKK','Slovak Koruna'),
 ('SLL','Leone'),
 ('SOS','Somali Shilling'),
 ('SRD','Surinam Dollar'),
 ('STD','Dobra'),
 ('SYP','Syrian Pound'),
 ('SZL','Lilangeni'),
 ('THB','Baht'),
 ('TJS','Somoni'),
 ('TMM','Manat'),
 ('TND','Tunisian Dinar'),
 ('TOP','Pa\'anga'),
 ('TRY','New Turkish Lira'),
 ('TTD','Trinidad and Tobago Dollar'),
 ('TWD','New Taiwan Dollar'),
 ('TZS','Tanzanian Shilling'),
 ('UAH','Hryvnia'),
 ('UGX','Uganda Shilling'),
 ('USN',''),
 ('USS',''),
 ('UYU','Peso Uruguayo'),
 ('UZS','Uzbekistan Som'),
 ('VEB','Venezuelan bol'),
 ('VND','Vietnamese ??ng'),
 ('VUV','Vatu'),
 ('WST','Samoan Tala'),
 ('XAF','CFA Franc BEAC'),
 ('XAG','Silver (one Troy ounce)'),
 ('XAU','Gold (one Troy ounce)'),
 ('XBA','European Composite Unit (EURCO) (Bonds market unit)'),
 ('XBB','European Monetary Unit (E.M.U.-6) (Bonds market unit)'),
 ('XBC','European Unit of Account 9 (E.U.A.-9) (Bonds market unit)'),
 ('XBD','European Unit of Account 17 (E.U.A.-17) (Bonds market unit)'),
 ('XCD','East Caribbean Dollar'),
 ('XDR','Special Drawing Rights'),
 ('XFO','Gold franc (special settlement currency)'),
 ('XFU','UIC franc (special settlement currency)'),
 ('XOF','CFA Franc BCEAO'),
 ('XPD','Palladium (one Troy ounce)'),
 ('XPF','CFP franc'),
 ('XPT','Platinum (one Troy ounce)'),
 ('XTS','Code reserved for testing purposes'),
 ('XXX','No currency'),
 ('YER','Yemeni Rial'),
 ('ZAR','South African Rand'),
 ('ZMK','Kwacha'),
 ('ZWD','Zimbabwe Dollar');
/*!40000 ALTER TABLE `sm_config_currencies` ENABLE KEYS */;


--
-- Definition of table `sm_config_naics`
--

DROP TABLE IF EXISTS `sm_config_naics`;
CREATE TABLE `sm_config_naics` (
  `naics_code` varchar(255) NOT NULL,
  `naics_description` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_naics`
--

/*!40000 ALTER TABLE `sm_config_naics` DISABLE KEYS */;
INSERT INTO `sm_config_naics` (`naics_code`,`naics_description`) VALUES 
 ('111','Crop Production '),
 ('112','Animal Production and Aquaculture '),
 ('113','Forestry and Logging '),
 ('114','Fishing, Hunting and Trapping'),
 ('115','Support Activities for Agriculture and Forestry'),
 ('211','Oil and Gas Extraction'),
 ('212','Mining (except Oil and Gas)'),
 ('213','Support Activities for Mining'),
 ('221','Utilities '),
 ('236','Construction of Buildings'),
 ('237','Heavy and Civil Engineering Construction'),
 ('238','Specialty Trade Contractors'),
 ('311','Food Manufacturing'),
 ('312','Beverage and Tobacco Product Manufacturing'),
 ('313','Textile Mills'),
 ('314','Textile Product Mills'),
 ('315','Apparel Manufacturing'),
 ('316','Leather and Allied Product Manufacturing'),
 ('321','Wood Product Manufacturing'),
 ('322','Paper Manufacturing'),
 ('323','Printing and Related Support Activities'),
 ('324','Petroleum and Coal Products Manufacturing'),
 ('325','Chemical Manufacturing'),
 ('326','Plastics and Rubber Products Manufacturing'),
 ('327','Nonmetallic Mineral Product Manufacturing'),
 ('331','Primary Metal Manufacturing'),
 ('332','Fabricated Metal Product Manufacturing'),
 ('333','Machinery Manufacturing'),
 ('334','Computer and Electronic Product Manufacturing'),
 ('335','Electrical Equipment, Appliance, and Component Manufacturing'),
 ('336','Transportation Equipment Manufacturing'),
 ('337','Furniture and Related Product Manufacturing'),
 ('339','Miscellaneous Manufacturing'),
 ('423','Merchant Wholesalers, Durable Goods '),
 ('424','Merchant Wholesalers, Nondurable Goods '),
 ('425','Wholesale Electronic Markets and Agents and Brokers '),
 ('441','Motor Vehicle and Parts Dealers '),
 ('442','Furniture and Home Furnishings Stores '),
 ('443','Electronics and Appliance Stores '),
 ('444','Building Material and Garden Equipment and Supplies Dealers '),
 ('445','Food and Beverage Stores '),
 ('446','Health and Personal Care Stores '),
 ('447','Gasoline Stations '),
 ('448','Clothing and Clothing Accessories Stores '),
 ('451','Sporting Goods, Hobby, Musical Instrument, and Book Stores '),
 ('452','General Merchandise Stores '),
 ('453','Miscellaneous Store Retailers '),
 ('454','Nonstore Retailers '),
 ('481','Air Transportation'),
 ('482','Rail Transportation'),
 ('483','Water Transportation'),
 ('484','Truck Transportation'),
 ('485','Transit and Ground Passenger Transportation'),
 ('486','Pipeline Transportation'),
 ('487','Scenic and Sightseeing Transportation'),
 ('488','Support Activities for Transportation'),
 ('491','Postal Service'),
 ('492','Couriers and Messengers'),
 ('493','Warehousing and Storage'),
 ('511','Publishing Industries (except Internet)'),
 ('512','Motion Picture and Sound Recording Industries'),
 ('515','Broadcasting (except Internet)'),
 ('517','Telecommunications'),
 ('518','Data Processing, Hosting, and Related Services'),
 ('519','Other Information Services'),
 ('521','Monetary Authorities-Central Bank'),
 ('522','Credit Intermediation and Related Activities'),
 ('523','Securities, Commodity Contracts, and Other Financial Investments and Related Activities'),
 ('524','Insurance Carriers and Related Activities'),
 ('525','Funds, Trusts, and Other Financial Vehicles '),
 ('531','Real Estate'),
 ('532','Rental and Leasing Services'),
 ('533','Lessors of Nonfinancial Intangible Assets (except Copyrighted Works)'),
 ('541','Professional, Scientific, and Technical Services'),
 ('551','Management of Companies and Enterprises'),
 ('561','Administrative and Support Services'),
 ('562','Waste Management and Remediation Services'),
 ('611','Educational Services'),
 ('621','Ambulatory Health Care Services'),
 ('622','Hospitals'),
 ('623','Nursing and Residential Care Facilities'),
 ('624','Social Assistance'),
 ('711','Performing Arts, Spectator Sports, and Related Industries'),
 ('712','Museums, Historical Sites, and Similar Institutions'),
 ('713','Amusement, Gambling, and Recreation Industries'),
 ('721','Accommodation'),
 ('722','Food Services and Drinking Places'),
 ('811','Repair and Maintenance'),
 ('812','Personal and Laundry Services'),
 ('813','Religious, Grantmaking, Civic, Professional, and Similar Organizations'),
 ('814','Private Households'),
 ('921','Executive, Legislative, and Other General Government Support '),
 ('922','Justice, Public Order, and Safety Activities '),
 ('923','Administration of Human Resource Programs '),
 ('924','Administration of Environmental Quality Programs '),
 ('925','Administration of Housing Programs, Urban Planning, and Community Development '),
 ('926','Administration of Economic Programs '),
 ('927','Space Research and Technology '),
 ('928','National Security and International Affairs ');
/*!40000 ALTER TABLE `sm_config_naics` ENABLE KEYS */;


--
-- Definition of table `sm_config_pricing_option`
--

DROP TABLE IF EXISTS `sm_config_pricing_option`;
CREATE TABLE `sm_config_pricing_option` (
  `pricing_option_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `plan` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `max_employee` bigint(15) NOT NULL,
  `max_transaction` bigint(20) NOT NULL,
  `disk_quota` bigint(20) NOT NULL COMMENT 'The disk_quota is stored in MB',
  KEY `pricing_option_key` (`pricing_option_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_pricing_option`
--

/*!40000 ALTER TABLE `sm_config_pricing_option` DISABLE KEYS */;
INSERT INTO `sm_config_pricing_option` (`pricing_option_key`,`plan`,`max_employee`,`max_transaction`,`disk_quota`) VALUES 
 ('Basic','Basic',25,250000,250),
 ('Plus','Plus',100,1000000,500),
 ('Premium','Premium',0,0,750);
/*!40000 ALTER TABLE `sm_config_pricing_option` ENABLE KEYS */;


--
-- Definition of table `sm_config_privileges`
--

DROP TABLE IF EXISTS `sm_config_privileges`;
CREATE TABLE `sm_config_privileges` (
  `user_privileges` bigint(20) NOT NULL,
  `group_privileges` bigint(20) NOT NULL,
  `dept_privileges` int(32) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_privileges`
--

/*!40000 ALTER TABLE `sm_config_privileges` DISABLE KEYS */;
INSERT INTO `sm_config_privileges` (`user_privileges`,`group_privileges`,`dept_privileges`) VALUES 
 (0,0,101010);
/*!40000 ALTER TABLE `sm_config_privileges` ENABLE KEYS */;


--
-- Definition of table `sm_config_quantity_type`
--

DROP TABLE IF EXISTS `sm_config_quantity_type`;
CREATE TABLE `sm_config_quantity_type` (
  `quan_type_key` varchar(255) NOT NULL COMMENT 'Quantity Type key to identify quantity type. Max limit is 255.',
  `quan_type` varchar(255) NOT NULL COMMENT 'Quantity type. Max limit is 255.',
  PRIMARY KEY  (`quan_type_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Quantity Type Master table with master entires such as KG, u';

--
-- Dumping data for table `sm_config_quantity_type`
--

/*!40000 ALTER TABLE `sm_config_quantity_type` DISABLE KEYS */;
INSERT INTO `sm_config_quantity_type` (`quan_type_key`,`quan_type`) VALUES 
 ('BAG','BAGS'),
 ('BAL','BALE'),
 ('BDL','BUNDLES'),
 ('BKL','BUCKLES'),
 ('BOU','BILLIONS OF UNITS'),
 ('BOX','BOX'),
 ('BTL','BOTTLES'),
 ('BUN','BUNCHES'),
 ('CAN','CANS'),
 ('CAS','CASE'),
 ('CBM','CUBIC METER'),
 ('CCM','CUBIC CENTIMETER'),
 ('CMS','CENTIMETER'),
 ('CTN','CARTONS'),
 ('DOZ','DOZEN'),
 ('DRM','DRUM'),
 ('FTS','FEET'),
 ('GGR','GREAT GROSS'),
 ('GMS','GRAMS'),
 ('GRS','GROSS'),
 ('GYD','GROSS YARDS'),
 ('KGA','KILOGRAM ACTIVITY'),
 ('KGS','KILOGRAMS'),
 ('KIT','KITS'),
 ('KLR','KILOLITER'),
 ('KME','KILOMETERS'),
 ('KWH','KILOWATTHOUR'),
 ('LBS','POUNDS'),
 ('LTR','LITERS'),
 ('MLT','MILLILITER'),
 ('MTR','METER'),
 ('MTS','METRIC TON'),
 ('NOS','NUMBER'),
 ('PAC','PACKS'),
 ('PCS','PIECES'),
 ('PRS','PAIRS'),
 ('QTL','QUINTAL'),
 ('RLS','ROLLS'),
 ('ROL','ROLLS'),
 ('SET','SETS'),
 ('SQF','SQUARE FEET'),
 ('SQM','SQUARE METER'),
 ('SQY','SQUARE YARDS');
/*!40000 ALTER TABLE `sm_config_quantity_type` ENABLE KEYS */;


--
-- Definition of table `sm_config_states`
--

DROP TABLE IF EXISTS `sm_config_states`;
CREATE TABLE `sm_config_states` (
  `state_id` bigint(20) unsigned NOT NULL auto_increment,
  `state_name` varchar(255) collate utf8_unicode_ci NOT NULL,
  `country_name` varchar(255) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`state_id`),
  KEY `state_name` (`state_name`)
) ENGINE=MyISAM AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `sm_config_states`
--

/*!40000 ALTER TABLE `sm_config_states` DISABLE KEYS */;
INSERT INTO `sm_config_states` (`state_id`,`state_name`,`country_name`) VALUES 
 (1,'Alabama','United States'),
 (2,'Alaska','United States'),
 (3,'Arizona','United States'),
 (4,'Arkansas','United States'),
 (5,'California','United States'),
 (6,'Colorado','United States'),
 (7,'Connecticut','United States'),
 (8,'Delaware','United States'),
 (9,'Florida','United States'),
 (10,'Georgia','United States'),
 (11,'Hawaii','United States'),
 (12,'Idaho','United States'),
 (13,'Illinois','United States'),
 (14,'Indiana','United States'),
 (15,'Iowa','United States'),
 (16,'Kansas','United States'),
 (17,'Kentucky','United States'),
 (18,'Louisiana','United States'),
 (19,'Maine','United States'),
 (20,'Maryland','United States'),
 (21,'Massachusetts','United States'),
 (22,'Michigan','United States'),
 (23,'Minnesota','United States'),
 (24,'Mississippi','United States'),
 (25,'Missouri','United States'),
 (26,'Montana','United States'),
 (27,'Nebraska','United States'),
 (28,'Nevada','United States'),
 (29,'New Hampshire','United States'),
 (30,'New Jersey','United States'),
 (31,'New Mexico','United States'),
 (32,'New York','United States'),
 (33,'North Carolina','United States'),
 (34,'North Dakota','United States'),
 (35,'Ohio','United States'),
 (36,'Oklahoma','United States'),
 (37,'Oregon','United States'),
 (38,'Pennsylvania','United States'),
 (39,'Rhode Island','United States'),
 (40,'South Carolina','United States'),
 (41,'South Dakota','United States'),
 (42,'Tennessee','United States'),
 (43,'Texas','United States'),
 (44,'Utah','United States'),
 (45,'Vermont','United States'),
 (46,'Virginia','United States'),
 (47,'Washington','United States'),
 (48,'West Virginia','United States'),
 (49,'Wisconsin','United States'),
 (50,'Wyoming','United States');
/*!40000 ALTER TABLE `sm_config_states` ENABLE KEYS */;


--
-- Definition of table `sm_config_trans_reject_reasons`
--

DROP TABLE IF EXISTS `sm_config_trans_reject_reasons`;
CREATE TABLE `sm_config_trans_reject_reasons` (
  `reject_resons_id` bigint(20) NOT NULL auto_increment,
  `reject_reason` varchar(1024) NOT NULL,
  PRIMARY KEY  (`reject_resons_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_trans_reject_reasons`
--

/*!40000 ALTER TABLE `sm_config_trans_reject_reasons` DISABLE KEYS */;
INSERT INTO `sm_config_trans_reject_reasons` (`reject_resons_id`,`reject_reason`) VALUES 
 (1,'Purchase Order number consists of invalid values'),
 (2,'Amount invoiced as zero'),
 (3,'Invoice amount does not total detail amount'),
 (4,'Invoice number not provided'),
 (5,'Date shipped not a valid date'),
 (6,'Duplicate vendor/invoice from vendor'),
 (7,'Invoice has been set to pay'),
 (8,'Invoice previously paid');
/*!40000 ALTER TABLE `sm_config_trans_reject_reasons` ENABLE KEYS */;


--
-- Definition of table `sm_config_user_account`
--

DROP TABLE IF EXISTS `sm_config_user_account`;
CREATE TABLE `sm_config_user_account` (
  `change_password_flag` tinyint(1) NOT NULL,
  `disable_account_flag` tinyint(1) NOT NULL,
  `account_expiration_days` int(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_user_account`
--

/*!40000 ALTER TABLE `sm_config_user_account` DISABLE KEYS */;
INSERT INTO `sm_config_user_account` (`change_password_flag`,`disable_account_flag`,`account_expiration_days`) VALUES 
 (0,0,2013);
/*!40000 ALTER TABLE `sm_config_user_account` ENABLE KEYS */;


--
-- Definition of table `sm_config_user_notifications`
--

DROP TABLE IF EXISTS `sm_config_user_notifications`;
CREATE TABLE `sm_config_user_notifications` (
  `notify_workinghours_flag` tinyint(1) NOT NULL,
  `notify_nonworkinghours_flag` tinyint(1) NOT NULL,
  `notify_workinghours_mode` varchar(255) NOT NULL,
  `notify_nonworkinghours_mode` varchar(255) NOT NULL,
  `notify_stop_flag` tinyint(1) NOT NULL,
  `notify_stop_fromtime` timestamp NOT NULL default '0000-00-00 00:00:00',
  `notify_stop_totime` timestamp NOT NULL default '0000-00-00 00:00:00'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_user_notifications`
--

/*!40000 ALTER TABLE `sm_config_user_notifications` DISABLE KEYS */;
INSERT INTO `sm_config_user_notifications` (`notify_workinghours_flag`,`notify_nonworkinghours_flag`,`notify_workinghours_mode`,`notify_nonworkinghours_mode`,`notify_stop_flag`,`notify_stop_fromtime`,`notify_stop_totime`) VALUES 
 (1,1,'Email','Email',0,'2013-05-04 00:00:00','2013-05-04 00:00:00');
/*!40000 ALTER TABLE `sm_config_user_notifications` ENABLE KEYS */;


--
-- Definition of table `sm_config_user_workinghours`
--

DROP TABLE IF EXISTS `sm_config_user_workinghours`;
CREATE TABLE `sm_config_user_workinghours` (
  `working_days` int(32) NOT NULL,
  `sun_fromtime` time NOT NULL,
  `sun_totime` time NOT NULL,
  `mon_fromtime` time NOT NULL,
  `mon_totime` time NOT NULL,
  `tue_fromtime` time NOT NULL,
  `tue_totime` time NOT NULL,
  `wed_fromtime` time NOT NULL,
  `wed_totime` time NOT NULL,
  `thu_fromtime` time NOT NULL,
  `thu_totime` time NOT NULL,
  `fri_fromtime` time NOT NULL,
  `fri_totime` time NOT NULL,
  `sat_fromtime` time NOT NULL,
  `sat_totime` time NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_user_workinghours`
--

/*!40000 ALTER TABLE `sm_config_user_workinghours` DISABLE KEYS */;
INSERT INTO `sm_config_user_workinghours` (`working_days`,`sun_fromtime`,`sun_totime`,`mon_fromtime`,`mon_totime`,`tue_fromtime`,`tue_totime`,`wed_fromtime`,`wed_totime`,`thu_fromtime`,`thu_totime`,`fri_fromtime`,`fri_totime`,`sat_fromtime`,`sat_totime`) VALUES 
 (1111111,'09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00');
/*!40000 ALTER TABLE `sm_config_user_workinghours` ENABLE KEYS */;


--
-- Definition of table `trans_inquire`
--

DROP TABLE IF EXISTS `trans_inquire`;
CREATE TABLE `trans_inquire` (
  `trans_id` bigint(20) NOT NULL,
  `trans_type` varchar(255) NOT NULL,
  `trans_type_id` bigint(20) NOT NULL,
  `from_regn_key` varchar(255) NOT NULL,
  `to_regn_key` varchar(255) NOT NULL,
  `user_from` varchar(255) NOT NULL,
  `user_to` varchar(255) NOT NULL,
  `details` text NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  KEY `trans_type_id` (`trans_type_id`),
  KEY `from` (`from_regn_key`),
  KEY `to` (`to_regn_key`),
  KEY `user_from` (`user_from`),
  KEY `user_to` (`user_to`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trans_inquire`
--

/*!40000 ALTER TABLE `trans_inquire` DISABLE KEYS */;
/*!40000 ALTER TABLE `trans_inquire` ENABLE KEYS */;


--
-- Definition of table `trans_reject`
--

DROP TABLE IF EXISTS `trans_reject`;
CREATE TABLE `trans_reject` (
  `trans_reject_id` bigint(20) NOT NULL auto_increment,
  `trans_id` bigint(20) NOT NULL,
  `trans_type_id` bigint(20) NOT NULL,
  `trans_type` varchar(255) NOT NULL,
  `from_regn_key` varchar(255) NOT NULL,
  `to_regn_key` varchar(255) NOT NULL,
  `user_from` varchar(255) NOT NULL,
  `user_to` varchar(255) NOT NULL,
  `reject_reason` varchar(1024) NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`trans_reject_id`),
  KEY `trans_id` (`trans_id`,`trans_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trans_reject`
--

/*!40000 ALTER TABLE `trans_reject` DISABLE KEYS */;
INSERT INTO `trans_reject` (`trans_reject_id`,`trans_id`,`trans_type_id`,`trans_type`,`from_regn_key`,`to_regn_key`,`user_from`,`user_to`,`reject_reason`,`created_timestamp`) VALUES 
 (1,27,11,'PO','4044082222','4044081111','supplymedium.test@gmail.com','jaffmd02@gmail.com','  ','2013-10-17 00:51:53');
/*!40000 ALTER TABLE `trans_reject` ENABLE KEYS */;


--
-- Definition of table `trans_tc`
--

DROP TABLE IF EXISTS `trans_tc`;
CREATE TABLE `trans_tc` (
  `regn_rel_key` bigint(10) NOT NULL,
  `trans_tc_id` bigint(10) NOT NULL auto_increment,
  `trans_type` varchar(255) NOT NULL,
  `tc` text NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`trans_tc_id`),
  KEY `regn_rel_key` (`regn_rel_key`,`trans_type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trans_tc`
--

/*!40000 ALTER TABLE `trans_tc` DISABLE KEYS */;
/*!40000 ALTER TABLE `trans_tc` ENABLE KEYS */;


--
-- Definition of table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `trans_table_id` bigint(20) NOT NULL auto_increment,
  `trans_id` bigint(20) NOT NULL,
  `trans_type` varchar(255) NOT NULL,
  `trans_type_id` bigint(20) NOT NULL,
  `action` varchar(255) NOT NULL,
  `from_regn_key` varchar(255) NOT NULL,
  `to_regn_key` varchar(255) NOT NULL,
  `user_from` varchar(255) NOT NULL,
  `user_to` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`trans_table_id`),
  KEY `trans_type_id` (`trans_type_id`),
  KEY `from` (`from_regn_key`),
  KEY `to` (`to_regn_key`),
  KEY `user_rom` (`user_from`),
  KEY `user_to` (`user_to`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` (`trans_table_id`,`trans_id`,`trans_type`,`trans_type_id`,`action`,`from_regn_key`,`to_regn_key`,`user_from`,`user_to`,`status`,`created_timestamp`) VALUES 
 (1,1,'RFQ',1,'','9815990067','8288033280','info@webkrit.com','info@hostkrit.com','RFQCreated','2014-09-12 08:39:16'),
 (2,1,'RFQ',1,'','8288033280','9815990067','info@hostkrit.com','info@webkrit.com','RFQAccepted','2014-09-12 08:39:29'),
 (3,2,'Quote',1,'','9815990067','8288033280','info@webkrit.com','info@hostkrit.com','QuoteCreated','2014-09-12 08:40:07'),
 (4,1,'Quote',2,'','8288033280','9815990067','info@hostkrit.com','info@webkrit.com','QuoteCreated','2014-09-12 08:40:58'),
 (5,1,'Quote',2,'','9815990067','8288033280','info@webkrit.com','info@hostkrit.com','QuoteAccepted','2014-09-12 08:41:12'),
 (6,1,'PO',1,'','9815990067','8288033280','info@webkrit.com','info@hostkrit.com','POCreated','2014-09-12 08:41:35'),
 (7,1,'PO',1,'','8288033280','9815990067','info@hostkrit.com','info@webkrit.com','POAccepted','2014-09-12 08:42:06'),
 (8,1,'Invoice',1,'','8288033280','9815990067','info@hostkrit.com','','InvoiceCreated','2014-09-12 08:42:49'),
 (9,3,'Invoice',2,'','8288033280','9815990067','info@hostkrit.com','info@webkrit.com','InvoiceCreated','2014-09-12 08:46:12'),
 (10,3,'Invoice',2,'','9815990067','8288033280','info@webkrit.com','info@hostkrit.com','InvoiceAccepted','2014-09-12 08:46:25'),
 (11,4,'Invoice',3,'','8288033280','9815990067','info@hostkrit.com','info@webkrit.com','InvoiceCreated','2014-09-12 10:51:57'),
 (12,4,'Invoice',3,'','9815990067','8288033280','info@webkrit.com','info@hostkrit.com','InvoiceAccepted','2014-09-12 10:58:34');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;


--
-- Definition of table `transaction_history`
--

DROP TABLE IF EXISTS `transaction_history`;
CREATE TABLE `transaction_history` (
  `trans_history_id` bigint(20) NOT NULL auto_increment,
  `trans_rel_id` bigint(20) NOT NULL,
  `from_regn_key` varchar(255) NOT NULL,
  `to_regn_key` varchar(255) NOT NULL,
  `amount` double(20,2) NOT NULL,
  `currency` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `created_time_stamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`trans_history_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction_history`
--

/*!40000 ALTER TABLE `transaction_history` DISABLE KEYS */;
INSERT INTO `transaction_history` (`trans_history_id`,`trans_rel_id`,`from_regn_key`,`to_regn_key`,`amount`,`currency`,`status`,`created_time_stamp`) VALUES 
 (1,3,'9815990067','8288033280',2990.00,'USD','Accepted','2014-09-12 08:46:25'),
 (2,4,'9815990067','8288033280',792.00,'USD','Accepted','2014-09-12 10:58:34');
/*!40000 ALTER TABLE `transaction_history` ENABLE KEYS */;


--
-- Definition of table `transaction_rating`
--

DROP TABLE IF EXISTS `transaction_rating`;
CREATE TABLE `transaction_rating` (
  `trans_rating_id` bigint(20) NOT NULL auto_increment,
  `trans_rel_id` bigint(20) NOT NULL,
  `from_regn_key` varchar(255) NOT NULL,
  `to_regn_key` varchar(255) NOT NULL,
  `star` tinyint(1) NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`trans_rating_id`),
  KEY `from_regn_key` (`from_regn_key`,`to_regn_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction_rating`
--

/*!40000 ALTER TABLE `transaction_rating` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_rating` ENABLE KEYS */;


--
-- Definition of table `transaction_remainder`
--

DROP TABLE IF EXISTS `transaction_remainder`;
CREATE TABLE `transaction_remainder` (
  `trans_remainder_id` bigint(20) NOT NULL auto_increment,
  `trans_rel_id` bigint(20) NOT NULL,
  `from_regn_key` varchar(255) NOT NULL,
  `to_regn_key` varchar(255) NOT NULL,
  `remainder` text NOT NULL,
  `due_date` datetime NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`trans_remainder_id`),
  KEY `trans_rel_id` (`trans_rel_id`,`from_regn_key`,`to_regn_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction_remainder`
--

/*!40000 ALTER TABLE `transaction_remainder` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_remainder` ENABLE KEYS */;


--
-- Definition of table `user_account_policies`
--

DROP TABLE IF EXISTS `user_account_policies`;
CREATE TABLE `user_account_policies` (
  `user_rel_key` varchar(255) NOT NULL,
  `change_password_flag` tinyint(1) NOT NULL,
  `disable_account_flag` tinyint(1) NOT NULL,
  `account_expiration_days` int(3) NOT NULL,
  KEY `user_rel_key` (`user_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_account_policies`
--

/*!40000 ALTER TABLE `user_account_policies` DISABLE KEYS */;
INSERT INTO `user_account_policies` (`user_rel_key`,`change_password_flag`,`disable_account_flag`,`account_expiration_days`) VALUES 
 ('dilbags@webkrit.com',0,0,2013),
 ('sfsdf@gmail.com',0,0,2013),
 ('dsdilbag345@gmail.com',0,0,2013),
 ('dilbagss@webkrit.com',0,0,2013),
 ('ddddd@gmail.com',0,0,2013),
 ('hhhh@gmail.com',0,0,2013);
/*!40000 ALTER TABLE `user_account_policies` ENABLE KEYS */;


--
-- Definition of table `user_dept_mapping`
--

DROP TABLE IF EXISTS `user_dept_mapping`;
CREATE TABLE `user_dept_mapping` (
  `user_rel_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `dept_rel_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  KEY `user_rel_key` (`user_rel_key`),
  KEY `dept_rel_key` (`dept_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_dept_mapping`
--

/*!40000 ALTER TABLE `user_dept_mapping` DISABLE KEYS */;
INSERT INTO `user_dept_mapping` (`user_rel_key`,`dept_rel_key`) VALUES 
 ('dilbags@webkrit.com','9815990067:devlopment'),
 ('sfsdf@gmail.com','9815990067:testing'),
 ('dilbagss@webkrit.com','8288033280:development'),
 ('dsdilbag345@gmail.com','8288033280:testing');
/*!40000 ALTER TABLE `user_dept_mapping` ENABLE KEYS */;


--
-- Definition of table `user_feed`
--

DROP TABLE IF EXISTS `user_feed`;
CREATE TABLE `user_feed` (
  `user_feed_id` int(25) NOT NULL auto_increment,
  `regn_rel_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `user_rel_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `feed_title` text character set utf8 collate utf8_unicode_ci NOT NULL,
  `feed` text character set utf8 collate utf8_unicode_ci NOT NULL,
  `relative_path` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `local_path` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `web_url` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`user_feed_id`),
  KEY `regn_rel_key` (`regn_rel_key`),
  KEY `user_rel_key` (`user_rel_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_feed`
--

/*!40000 ALTER TABLE `user_feed` DISABLE KEYS */;
INSERT INTO `user_feed` (`user_feed_id`,`regn_rel_key`,`user_rel_key`,`feed_title`,`feed`,`relative_path`,`local_path`,`web_url`,`created_timestamp`) VALUES 
 (1,'9815990067','info@webkrit.com','webkrit  has requested for quote (RFQ)','<table border=1><tr><th width=\"300\">SR NO.</th><th width=\"300\">ITEM DESC</th><th width=\"120\">PART NO.</th><th width=\"120\">BAR CODE</th><th width=\"120\">QUANTITY</th><th width=\"120\">SHIP DATE</th></tr><tr><td>1</td><td>lenovo</td><td>b560</td><td>b560</td><td>1 KG</td><td>12-Sep-2014</td></tr></table>','null','null','null','2014-09-12 08:39:16');
/*!40000 ALTER TABLE `user_feed` ENABLE KEYS */;


--
-- Definition of table `user_folder_access`
--

DROP TABLE IF EXISTS `user_folder_access`;
CREATE TABLE `user_folder_access` (
  `user_folder_access_id` bigint(20) NOT NULL auto_increment,
  `regn_rel_key` varchar(255) NOT NULL,
  `dept_rel_key` varchar(255) NOT NULL,
  `user_rel_key` varchar(255) NOT NULL,
  `folder_rel_key` varchar(255) NOT NULL,
  `read_flag` tinyint(1) NOT NULL,
  `read_write_flag` tinyint(1) NOT NULL,
  PRIMARY KEY  (`user_folder_access_id`),
  KEY `dept_rel_key` (`dept_rel_key`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_folder_access`
--

/*!40000 ALTER TABLE `user_folder_access` DISABLE KEYS */;
INSERT INTO `user_folder_access` (`user_folder_access_id`,`regn_rel_key`,`dept_rel_key`,`user_rel_key`,`folder_rel_key`,`read_flag`,`read_write_flag`) VALUES 
 (1,'9815990067','9815990067:ffffffff','dilbags@webkrit.com','9815990067:dfgdfg',1,0),
 (2,'9815990067','9815990067:ffffffff','dilbags@webkrit.com','9815990067:dfgdf',0,1);
/*!40000 ALTER TABLE `user_folder_access` ENABLE KEYS */;


--
-- Definition of table `user_group_mapping`
--

DROP TABLE IF EXISTS `user_group_mapping`;
CREATE TABLE `user_group_mapping` (
  `user_rel_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `group_rel_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  KEY `user_rel_key` (`user_rel_key`),
  KEY `group_rel_key` (`group_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_group_mapping`
--

/*!40000 ALTER TABLE `user_group_mapping` DISABLE KEYS */;
INSERT INTO `user_group_mapping` (`user_rel_key`,`group_rel_key`) VALUES 
 ('dilbags@webkrit.com','9815990067:manager'),
 ('dilbags@webkrit.com','9815990067:programmer'),
 ('sfsdf@gmail.com','9815990067:programmer'),
 ('sfsdf@gmail.com','9815990067:manager'),
 ('dsdilbag345@gmail.com','8288033280:testing'),
 ('dilbagss@webkrit.com','8288033280:devlopment');
/*!40000 ALTER TABLE `user_group_mapping` ENABLE KEYS */;


--
-- Definition of table `user_login`
--

DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login` (
  `login_id` bigint(20) unsigned NOT NULL auto_increment,
  `user_id` bigint(20) unsigned NOT NULL,
  `email` varchar(255) collate utf8_unicode_ci NOT NULL,
  `password` varchar(255) character set utf8 collate utf8_bin NOT NULL COMMENT 'Use fixed size if you are limiting the password length in your password policy',
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`login_id`),
  KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user_login`
--

/*!40000 ALTER TABLE `user_login` DISABLE KEYS */;
INSERT INTO `user_login` (`login_id`,`user_id`,`email`,`password`,`created_timestamp`) VALUES 
 (1,0,'info@webkrit.com',0x313233343536406141,'2014-09-18 11:46:45'),
 (2,0,'info@hostkrit.com',0x3132333435364061,'2014-09-12 08:33:16'),
 (4,0,'dsdilbag345@gmail.com',0x6E756C6C,'2014-09-28 17:07:08');
/*!40000 ALTER TABLE `user_login` ENABLE KEYS */;


--
-- Definition of table `user_notifications`
--

DROP TABLE IF EXISTS `user_notifications`;
CREATE TABLE `user_notifications` (
  `user_rel_key` varchar(255) NOT NULL,
  `notify_email` varchar(255) NOT NULL,
  `notify_mobile` varchar(255) NOT NULL,
  `notify_workinghours_flag` tinyint(1) NOT NULL,
  `notify_nonworkinghours_flag` tinyint(1) NOT NULL,
  `notify_workinghours_mode` varchar(255) NOT NULL,
  `notify_nonworkinghours_mode` varchar(255) NOT NULL,
  `notify_stop_flag` tinyint(1) NOT NULL,
  `notify_stop_fromtime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `notify_stop_totime` timestamp NOT NULL default '0000-00-00 00:00:00',
  KEY `user_rel_key` (`user_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_notifications`
--

/*!40000 ALTER TABLE `user_notifications` DISABLE KEYS */;
INSERT INTO `user_notifications` (`user_rel_key`,`notify_email`,`notify_mobile`,`notify_workinghours_flag`,`notify_nonworkinghours_flag`,`notify_workinghours_mode`,`notify_nonworkinghours_mode`,`notify_stop_flag`,`notify_stop_fromtime`,`notify_stop_totime`) VALUES 
 ('info@webkrit.com','info@webkrit.com','null',1,1,'Email','Email',0,'2013-05-04 00:00:00','2013-05-04 00:00:00'),
 ('info@hostkrit.com','info@hostkrit.com','null',1,1,'Email','Email',0,'2013-05-04 00:00:00','2013-05-04 00:00:00'),
 ('dilbags@webkrit.com','dilbags@webkrit.com','null',1,1,'Email','Email',0,'2013-05-04 00:00:00','2013-05-04 00:00:00'),
 ('sfsdf@gmail.com','sfsdf@gmail.com','null',1,1,'Email','Email',0,'2013-05-04 00:00:00','2013-05-04 00:00:00'),
 ('dsdilbag345@gmail.com','dsdilbag345@gmail.com','null',1,1,'Email','Email',0,'2013-05-04 00:00:00','2013-05-04 00:00:00'),
 ('dilbagss@webkrit.com','dilbagss@webkrit.com','null',1,1,'Email','Email',0,'2013-05-04 00:00:00','2013-05-04 00:00:00'),
 ('ddddd@gmail.com','ddddd@gmail.com','null',1,1,'Email','Email',0,'2013-05-04 00:00:00','2013-05-04 00:00:00'),
 ('hhhh@gmail.com','hhhh@gmail.com','null',1,1,'Email','Email',0,'2013-05-04 00:00:00','2013-05-04 00:00:00');
/*!40000 ALTER TABLE `user_notifications` ENABLE KEYS */;


--
-- Definition of table `user_privileges`
--

DROP TABLE IF EXISTS `user_privileges`;
CREATE TABLE `user_privileges` (
  `user_rel_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `privileges` bigint(20) NOT NULL,
  KEY `user_rel_key` (`user_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_privileges`
--

/*!40000 ALTER TABLE `user_privileges` DISABLE KEYS */;
INSERT INTO `user_privileges` (`user_rel_key`,`privileges`) VALUES 
 ('info@webkrit.com',11111111111111),
 ('info@hostkrit.com',11111111111111),
 ('dilbags@webkrit.com',0),
 ('sfsdf@gmail.com',0),
 ('dsdilbag345@gmail.com',0),
 ('ddddd@gmail.com',0),
 ('hhhh@gmail.com',0);
/*!40000 ALTER TABLE `user_privileges` ENABLE KEYS */;


--
-- Definition of table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile` (
  `user_id` bigint(20) unsigned NOT NULL auto_increment,
  `regn_key` varchar(255) collate utf8_unicode_ci NOT NULL,
  `user_profile_key` varchar(255) collate utf8_unicode_ci NOT NULL,
  `company_id` bigint(20) unsigned NOT NULL,
  `first_name` varchar(255) collate utf8_unicode_ci NOT NULL,
  `last_name` varchar(255) collate utf8_unicode_ci NOT NULL,
  `title` varchar(10) collate utf8_unicode_ci NOT NULL,
  `department` varchar(255) collate utf8_unicode_ci NOT NULL,
  `user_role` varchar(255) collate utf8_unicode_ci NOT NULL,
  `phone` varchar(255) collate utf8_unicode_ci NOT NULL,
  `cell` varchar(255) collate utf8_unicode_ci NOT NULL,
  `fax` varchar(255) collate utf8_unicode_ci NOT NULL,
  `email` varchar(255) collate utf8_unicode_ci NOT NULL,
  `user_type` varchar(255) collate utf8_unicode_ci NOT NULL,
  `user_status` varchar(255) collate utf8_unicode_ci NOT NULL,
  `created_date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `profile_picture_path` text collate utf8_unicode_ci,
  PRIMARY KEY  (`user_id`),
  KEY `regn_key` (`regn_key`),
  KEY `user_profile_key` (`user_profile_key`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user_profile`
--

/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` (`user_id`,`regn_key`,`user_profile_key`,`company_id`,`first_name`,`last_name`,`title`,`department`,`user_role`,`phone`,`cell`,`fax`,`email`,`user_type`,`user_status`,`created_date`,`profile_picture_path`) VALUES 
 (1,'9815990067','info@webkrit.com',0,'lokesh','kakkar','MR','','','9815990067','','','info@webkrit.com','Admin','Active','2014-09-12 10:48:18','http://localhost:8084/SupplyMedium///webapps/CropData/webkrit-9815990067/Images/UserProfile/info@webkrit.com.jpg'),
 (2,'8288033280','info@hostkrit.com',0,'kakkar','lokesh','MR','','','8288033280','','','info@hostkrit.com','Admin','Active','2014-09-12 16:01:14','http://localhost:8084/SupplyMedium///webapps/CropData/hostkrit-8288033280/Images/UserProfile/info@hostkrit.com.jpg'),
 (3,'9815990067','dilbags@webkrit.com',0,'first1','last1','null','','','11111111111','','','dilbags@webkrit.com','Intranet User','Pending','2014-09-18 15:06:37',NULL),
 (4,'9815990067','sfsdf@gmail.com',0,'first2','last2','null','','','111111','','','sfsdf@gmail.com','Intranet User','Pending','2014-09-18 15:11:34',NULL),
 (5,'8288033280','dsdilbag345@gmail.com',0,'first','last','null','','','99999999999','','','dsdilbag345@gmail.com','Regular','Active','2014-09-28 17:07:08',NULL),
 (7,'8288033280','ddddd@gmail.com',0,'abc','dsfds','null','','','1111111','','','ddddd@gmail.com','Intranet User','Pending','2014-10-01 19:47:12',NULL),
 (8,'8288033280','hhhh@gmail.com',0,'cde','dsfsd','null','','','33333333','','','hhhh@gmail.com','Regular','Pending','2014-10-01 19:47:44',NULL);
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;


--
-- Definition of table `user_ratings`
--

DROP TABLE IF EXISTS `user_ratings`;
CREATE TABLE `user_ratings` (
  `rattings_key` int(11) NOT NULL auto_increment,
  `regn_rel_key` varchar(255) character set utf8 collate utf8_unicode_ci NOT NULL,
  `user_profile_key` varchar(255) NOT NULL,
  `reviewer_name` varchar(255) NOT NULL,
  `company_regn_key` varchar(255) NOT NULL,
  `review_title` varchar(100) NOT NULL,
  `comments` varchar(255) NOT NULL,
  `review_date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `ratings` int(11) NOT NULL,
  PRIMARY KEY  (`rattings_key`),
  UNIQUE KEY `Rattings_Key` (`rattings_key`),
  KEY `Rattings_Key_2` (`rattings_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_ratings`
--

/*!40000 ALTER TABLE `user_ratings` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_ratings` ENABLE KEYS */;


--
-- Definition of table `user_working_hours`
--

DROP TABLE IF EXISTS `user_working_hours`;
CREATE TABLE `user_working_hours` (
  `user_rel_key` varchar(255) NOT NULL,
  `working_days` int(32) NOT NULL,
  `sun_fromtime` time NOT NULL,
  `sun_totime` time NOT NULL,
  `mon_fromtime` time NOT NULL,
  `mon_totime` time NOT NULL,
  `tue_fromtime` time NOT NULL,
  `tue_totime` time NOT NULL,
  `wed_fromtime` time NOT NULL,
  `wed_totime` time NOT NULL,
  `thu_fromtime` time NOT NULL,
  `thu_totime` time NOT NULL,
  `fri_fromtime` time NOT NULL,
  `fri_totime` time NOT NULL,
  `sat_fromtime` time NOT NULL,
  `sat_totime` time NOT NULL,
  KEY `user_rel_key` (`user_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_working_hours`
--

/*!40000 ALTER TABLE `user_working_hours` DISABLE KEYS */;
INSERT INTO `user_working_hours` (`user_rel_key`,`working_days`,`sun_fromtime`,`sun_totime`,`mon_fromtime`,`mon_totime`,`tue_fromtime`,`tue_totime`,`wed_fromtime`,`wed_totime`,`thu_fromtime`,`thu_totime`,`fri_fromtime`,`fri_totime`,`sat_fromtime`,`sat_totime`) VALUES 
 ('info@webkrit.com',1111111,'09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00'),
 ('info@hostkrit.com',1111111,'09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00'),
 ('dilbags@webkrit.com',1111111,'09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00'),
 ('sfsdf@gmail.com',1111111,'09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00'),
 ('dsdilbag345@gmail.com',1111111,'09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00'),
 ('dilbagss@webkrit.com',1111111,'09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00'),
 ('ddddd@gmail.com',1111111,'09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00'),
 ('hhhh@gmail.com',1111111,'09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00');
/*!40000 ALTER TABLE `user_working_hours` ENABLE KEYS */;


--
-- Definition of table `userkey_uuid_mapper`
--

DROP TABLE IF EXISTS `userkey_uuid_mapper`;
CREATE TABLE `userkey_uuid_mapper` (
  `user_rel_key` varchar(255) NOT NULL,
  `uuid` varchar(255) NOT NULL,
  KEY `user_rel_key` (`user_rel_key`),
  KEY `uuid` (`uuid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userkey_uuid_mapper`
--

/*!40000 ALTER TABLE `userkey_uuid_mapper` DISABLE KEYS */;
INSERT INTO `userkey_uuid_mapper` (`user_rel_key`,`uuid`) VALUES 
 ('dilbags@webkrit.com','22f7428e-8211-414d-bf9f-b5db0ecfa1d5'),
 ('info@webkrit.com','3d21bdf5-6108-4d34-b105-cf34758bda34'),
 ('sfsdf@gmail.com','3fe4d00f-2281-421e-a441-05fd8cb9833a'),
 ('dsdilbag345@gmail.com','dfdbafe6-0f3e-41cf-b8be-2d9e248b6fc0'),
 ('dilbagss@webkrit.com','caa22f46-bad3-471c-84a8-caf726f6836c'),
 ('ddddd@gmail.com','2af9244e-b6be-4788-bc04-9492c953a293'),
 ('hhhh@gmail.com','69f2564e-7eee-42a2-bb5f-6896f8f30122');
/*!40000 ALTER TABLE `userkey_uuid_mapper` ENABLE KEYS */;


--
-- Definition of table `vendor_inquire_details`
--

DROP TABLE IF EXISTS `vendor_inquire_details`;
CREATE TABLE `vendor_inquire_details` (
  `vendor_inquire_id` bigint(20) NOT NULL auto_increment,
  `vendor_regn_id` bigint(20) NOT NULL,
  `inquire_from` varchar(255) NOT NULL,
  `inquire_to` varchar(255) NOT NULL,
  `inquire_details` text NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`vendor_inquire_id`),
  KEY `inquire_from` (`inquire_from`,`inquire_to`),
  KEY `vendor_regn_id` (`vendor_regn_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vendor_inquire_details`
--

/*!40000 ALTER TABLE `vendor_inquire_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendor_inquire_details` ENABLE KEYS */;


--
-- Definition of table `vendor_registration`
--

DROP TABLE IF EXISTS `vendor_registration`;
CREATE TABLE `vendor_registration` (
  `vendor_regn_id` bigint(20) NOT NULL auto_increment,
  `regn_rel_key` varchar(255) NOT NULL,
  `regn_rel_map` varchar(255) NOT NULL,
  `user_rel_key` varchar(255) NOT NULL,
  `request_sender_type` varchar(255) NOT NULL default 'supplier',
  `company_level` varchar(255) NOT NULL,
  `business_contact_name` varchar(1024) NOT NULL,
  `business_tax_id` varchar(255) NOT NULL,
  `naics_code` varchar(255) NOT NULL,
  `w9tax_form_flag` tinyint(1) NOT NULL,
  `w9tax_form_path` varchar(1024) NOT NULL,
  `business_size` varchar(255) NOT NULL,
  `business_classification` text NOT NULL,
  `additional_details` text NOT NULL,
  `status` varchar(255) NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`vendor_regn_id`),
  KEY `regn_rel_key` (`regn_rel_key`,`regn_rel_map`,`user_rel_key`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vendor_registration`
--

/*!40000 ALTER TABLE `vendor_registration` DISABLE KEYS */;
INSERT INTO `vendor_registration` (`vendor_regn_id`,`regn_rel_key`,`regn_rel_map`,`user_rel_key`,`request_sender_type`,`company_level`,`business_contact_name`,`business_tax_id`,`naics_code`,`w9tax_form_flag`,`w9tax_form_path`,`business_size`,`business_classification`,`additional_details`,`status`,`created_timestamp`) VALUES 
 (2,'9815990067','8288033280','','Supplier','Individual','lokesh kakkar','','',0,'','Large','','fsdfg','Accepted','2014-09-12 16:32:56');
/*!40000 ALTER TABLE `vendor_registration` ENABLE KEYS */;


--
-- Definition of table `vr_mailing_address`
--

DROP TABLE IF EXISTS `vr_mailing_address`;
CREATE TABLE `vr_mailing_address` (
  `addr_id` bigint(20) NOT NULL auto_increment,
  `address_type` varchar(255) NOT NULL,
  `address` text NOT NULL,
  `city` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `zipcode` varchar(255) NOT NULL,
  `country_region` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `regn_key` bigint(11) NOT NULL,
  PRIMARY KEY  (`addr_id`),
  KEY `addr_id` (`addr_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vr_mailing_address`
--

/*!40000 ALTER TABLE `vr_mailing_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `vr_mailing_address` ENABLE KEYS */;


--
-- Definition of table `watchlist`
--

DROP TABLE IF EXISTS `watchlist`;
CREATE TABLE `watchlist` (
  `watchlist_id` int(25) NOT NULL auto_increment COMMENT 'Auto incremented id for watchlist',
  `user_rel_key` varchar(255) NOT NULL COMMENT 'Owner of the watchlist',
  `regn_rel_key` varchar(255) NOT NULL COMMENT 'Company regn key of the watchlist owner',
  `watchlist_name` varchar(255) NOT NULL COMMENT 'Name of the watchlist',
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT 'Watchlist created timestamp',
  PRIMARY KEY  (`watchlist_id`),
  KEY `user_rel_key` (`user_rel_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watchlist`
--

/*!40000 ALTER TABLE `watchlist` DISABLE KEYS */;
INSERT INTO `watchlist` (`watchlist_id`,`user_rel_key`,`regn_rel_key`,`watchlist_name`,`created_timestamp`) VALUES 
 (1,'info@hostkrit.com','8288033280','mca','2014-09-28 16:55:31'),
 (2,'info@hostkrit.com','8288033280','bca','2014-09-28 16:55:36');
/*!40000 ALTER TABLE `watchlist` ENABLE KEYS */;


--
-- Definition of table `watchlist_member_mapper`
--

DROP TABLE IF EXISTS `watchlist_member_mapper`;
CREATE TABLE `watchlist_member_mapper` (
  `watchlist_rel_id` int(25) NOT NULL,
  `user_rel_key` varchar(255) NOT NULL,
  `created_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  KEY `watchlist_rel_id` (`watchlist_rel_id`,`user_rel_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watchlist_member_mapper`
--

/*!40000 ALTER TABLE `watchlist_member_mapper` DISABLE KEYS */;
INSERT INTO `watchlist_member_mapper` (`watchlist_rel_id`,`user_rel_key`,`created_timestamp`) VALUES 
 (1,'info@webkrit.com','2014-09-28 18:16:03');
/*!40000 ALTER TABLE `watchlist_member_mapper` ENABLE KEYS */;


--
-- Definition of table `wk_user`
--

DROP TABLE IF EXISTS `wk_user`;
CREATE TABLE `wk_user` (
  `wk_user_id` int(11) NOT NULL auto_increment,
  `email` varchar(100) default NULL,
  `password` varchar(50) default NULL,
  `status` int(2) default NULL,
  PRIMARY KEY  (`wk_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `wk_user`
--

/*!40000 ALTER TABLE `wk_user` DISABLE KEYS */;
INSERT INTO `wk_user` (`wk_user_id`,`email`,`password`,`status`) VALUES 
 (1,'a@a.com','b@b.com',2);
/*!40000 ALTER TABLE `wk_user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
