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
-- Create schema supplymedium
--

CREATE DATABASE IF NOT EXISTS supplymedium;
USE supplymedium;

--
-- Definition of table `account_policy_master`
--

DROP TABLE IF EXISTS `account_policy_master`;
CREATE TABLE `account_policy_master` (
  `account_policy_key` bigint(20) NOT NULL auto_increment,
  `company_key` bigint(20) default NULL,
  `user_key` bigint(20) default NULL,
  `enforce_password_history` int(11) default NULL,
  `max_password_age` int(11) default NULL,
  `min_password_age` int(11) default NULL,
  `min_password_length` int(11) default NULL,
  `send_email_before_password_expire` int(11) default NULL,
  `send_daily_reminder_after_date` varchar(3) default NULL,
  `password_complexity` varchar(3) default NULL,
  `upper_lower_case_letter` varchar(3) default NULL,
  `numerical_characters` varchar(3) default NULL,
  `non_alphanummeric_characters` varchar(3) default NULL,
  `account_lock_after_attempts` int(11) default NULL,
  `lockout_duration` int(11) default NULL,
  `reset_lockout_counter_after` int(11) default NULL,
  `account_unlocked_by_admin` varchar(3) default NULL,
  `session_end_time` int(11) default NULL,
  PRIMARY KEY  (`account_policy_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account_policy_master`
--

/*!40000 ALTER TABLE `account_policy_master` DISABLE KEYS */;
INSERT INTO `account_policy_master` (`account_policy_key`,`company_key`,`user_key`,`enforce_password_history`,`max_password_age`,`min_password_age`,`min_password_length`,`send_email_before_password_expire`,`send_daily_reminder_after_date`,`password_complexity`,`upper_lower_case_letter`,`numerical_characters`,`non_alphanummeric_characters`,`account_lock_after_attempts`,`lockout_duration`,`reset_lockout_counter_after`,`account_unlocked_by_admin`,`session_end_time`) VALUES 
 (1,1,1,1,5000,1,7,3,'yes','yes','yes','yes','yes',5,10,5,'yes',30),
 (2,2,2,1,5000,1,7,3,'yes','yes','yes','yes','yes',5,10,5,'yes',30),
 (3,3,3,1,5000,1,7,3,'yes','yes','yes','yes','yes',5,10,5,'yes',30);
/*!40000 ALTER TABLE `account_policy_master` ENABLE KEYS */;


--
-- Definition of table `company_advertisement_master`
--

DROP TABLE IF EXISTS `company_advertisement_master`;
CREATE TABLE `company_advertisement_master` (
  `advertisement_key` bigint(20) NOT NULL auto_increment,
  `company_key` bigint(20) default NULL,
  `user_key` bigint(20) default NULL,
  `hover_text` varchar(300) default NULL,
  `link_page` varchar(300) default NULL,
  `image_path` varchar(500) default NULL,
  PRIMARY KEY  (`advertisement_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company_advertisement_master`
--

/*!40000 ALTER TABLE `company_advertisement_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_advertisement_master` ENABLE KEYS */;


--
-- Definition of table `company_business_category_master`
--

DROP TABLE IF EXISTS `company_business_category_master`;
CREATE TABLE `company_business_category_master` (
  `cbc_key` bigint(20) NOT NULL auto_increment,
  `company_key` bigint(20) default NULL,
  `business_category_key` bigint(20) default NULL,
  PRIMARY KEY  (`cbc_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company_business_category_master`
--

/*!40000 ALTER TABLE `company_business_category_master` DISABLE KEYS */;
INSERT INTO `company_business_category_master` (`cbc_key`,`company_key`,`business_category_key`) VALUES 
 (1,1,20),
 (2,1,21),
 (3,1,22),
 (4,2,20),
 (5,2,21),
 (6,2,22),
 (10,3,20),
 (11,3,21),
 (12,3,22);
/*!40000 ALTER TABLE `company_business_category_master` ENABLE KEYS */;


--
-- Definition of table `company_mailing_address_master`
--

DROP TABLE IF EXISTS `company_mailing_address_master`;
CREATE TABLE `company_mailing_address_master` (
  `mailing_key` bigint(20) NOT NULL auto_increment,
  `company_key` varchar(100) default NULL,
  `branch` varchar(100) default NULL,
  `country` varchar(100) default NULL,
  `address` varchar(200) default NULL,
  `city` varchar(200) default NULL,
  `state` varchar(200) default NULL,
  `zipcode` varchar(200) default NULL,
  `address_type` varchar(300) default NULL,
  `added_by_user_key` bigint(20) default NULL,
  `created_on` datetime default NULL,
  PRIMARY KEY  (`mailing_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company_mailing_address_master`
--

/*!40000 ALTER TABLE `company_mailing_address_master` DISABLE KEYS */;
INSERT INTO `company_mailing_address_master` (`mailing_key`,`company_key`,`branch`,`country`,`address`,`city`,`state`,`zipcode`,`address_type`,`added_by_user_key`,`created_on`) VALUES 
 (1,'1','Corporate Office','96','','','78','','Permanent',0,'2014-11-27 18:40:22'),
 (2,'2','Corporate Office','96','','','78','','Permanent',0,'2014-11-27 18:41:53'),
 (4,'3','Corporate Office','96','','','Punjab','','Permanent',0,'2014-11-27 18:45:05');
/*!40000 ALTER TABLE `company_mailing_address_master` ENABLE KEYS */;


--
-- Definition of table `company_master`
--

DROP TABLE IF EXISTS `company_master`;
CREATE TABLE `company_master` (
  `company_key` bigint(20) NOT NULL auto_increment,
  `company_type` varchar(50) default NULL,
  `company_logo_path` varchar(50) default NULL,
  `company_name` varchar(200) default NULL,
  `company_id` varchar(100) default NULL,
  `segment_division` varchar(100) default NULL,
  `business_unit` varchar(100) default NULL,
  `pricing_key` bigint(20) default NULL,
  `amount_genrated` decimal(20,2) default NULL,
  `amount_platform` decimal(20,2) default NULL,
  `amount_paid` decimal(20,2) default NULL,
  `created_on` datetime default NULL,
  PRIMARY KEY  (`company_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company_master`
--

/*!40000 ALTER TABLE `company_master` DISABLE KEYS */;
INSERT INTO `company_master` (`company_key`,`company_type`,`company_logo_path`,`company_name`,`company_id`,`segment_division`,`business_unit`,`pricing_key`,`amount_genrated`,`amount_platform`,`amount_paid`,`created_on`) VALUES 
 (1,'Buyers/Suppliers','null','webkrit','webkrit','','',0,'0.00','0.00','0.00','2014-11-27 18:40:22'),
 (2,'Buyers/Suppliers','null','edukrit','edukrit','','',0,'0.00','0.00','0.00','2014-11-27 18:41:53'),
 (3,'Buyers/Suppliers','null','lucky','lucky','','',0,'0.00','0.00','0.00','2014-11-27 18:43:03');
/*!40000 ALTER TABLE `company_master` ENABLE KEYS */;


--
-- Definition of table `company_website_master`
--

DROP TABLE IF EXISTS `company_website_master`;
CREATE TABLE `company_website_master` (
  `company_website_key` bigint(20) NOT NULL auto_increment,
  `company_key` bigint(20) default NULL,
  `url_name` varchar(300) default NULL,
  `website_url` varchar(300) default NULL,
  PRIMARY KEY  (`company_website_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company_website_master`
--

/*!40000 ALTER TABLE `company_website_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_website_master` ENABLE KEYS */;


--
-- Definition of table `connection_master`
--

DROP TABLE IF EXISTS `connection_master`;
CREATE TABLE `connection_master` (
  `connection_key` bigint(20) NOT NULL auto_increment,
  `user_key_from` bigint(20) default NULL,
  `user_key_to` bigint(20) default NULL,
  `company_key_from` bigint(20) default NULL,
  `company_key_to` bigint(20) default NULL,
  `status` varchar(50) default NULL,
  `sent_on` datetime default NULL,
  PRIMARY KEY  (`connection_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `connection_master`
--

/*!40000 ALTER TABLE `connection_master` DISABLE KEYS */;
INSERT INTO `connection_master` (`connection_key`,`user_key_from`,`user_key_to`,`company_key_from`,`company_key_to`,`status`,`sent_on`) VALUES 
 (1417094158271,1,2,1,2,'Accepted','2014-11-27 18:45:58'),
 (1417094181278,2,1,2,1,'Accepted','2014-11-27 18:46:21');
/*!40000 ALTER TABLE `connection_master` ENABLE KEYS */;


--
-- Definition of table `department_folder_master`
--

DROP TABLE IF EXISTS `department_folder_master`;
CREATE TABLE `department_folder_master` (
  `department_folder_key` bigint(20) NOT NULL auto_increment,
  `company_key` bigint(20) default NULL,
  `department_key` bigint(20) default NULL,
  `folder_key` varchar(30) default NULL,
  PRIMARY KEY  (`department_folder_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department_folder_master`
--

/*!40000 ALTER TABLE `department_folder_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `department_folder_master` ENABLE KEYS */;


--
-- Definition of table `department_group_master`
--

DROP TABLE IF EXISTS `department_group_master`;
CREATE TABLE `department_group_master` (
  `department_group_key` bigint(20) NOT NULL auto_increment,
  `company_key` bigint(20) default NULL,
  `department_key` bigint(20) default NULL,
  `group_key` bigint(20) default NULL,
  PRIMARY KEY  (`department_group_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department_group_master`
--

/*!40000 ALTER TABLE `department_group_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `department_group_master` ENABLE KEYS */;


--
-- Definition of table `department_master`
--

DROP TABLE IF EXISTS `department_master`;
CREATE TABLE `department_master` (
  `department_key` bigint(20) NOT NULL auto_increment,
  `company_key` bigint(20) default NULL,
  `department_name` varchar(255) default NULL,
  PRIMARY KEY  (`department_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department_master`
--

/*!40000 ALTER TABLE `department_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `department_master` ENABLE KEYS */;


--
-- Definition of table `department_user_master`
--

DROP TABLE IF EXISTS `department_user_master`;
CREATE TABLE `department_user_master` (
  `department_user_key` bigint(20) NOT NULL auto_increment,
  `company_key` bigint(20) default NULL,
  `department_key` bigint(20) default NULL,
  `user_key` bigint(20) default NULL,
  PRIMARY KEY  (`department_user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department_user_master`
--

/*!40000 ALTER TABLE `department_user_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `department_user_master` ENABLE KEYS */;


--
-- Definition of table `feed_comment_master`
--

DROP TABLE IF EXISTS `feed_comment_master`;
CREATE TABLE `feed_comment_master` (
  `feed_comment_key` bigint(20) NOT NULL auto_increment,
  `feed_key` bigint(20) default NULL,
  `user_key` bigint(20) default NULL,
  `comment` varchar(200) default NULL,
  `commented_on` datetime default NULL,
  PRIMARY KEY  (`feed_comment_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feed_comment_master`
--

/*!40000 ALTER TABLE `feed_comment_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `feed_comment_master` ENABLE KEYS */;


--
-- Definition of table `feed_like_master`
--

DROP TABLE IF EXISTS `feed_like_master`;
CREATE TABLE `feed_like_master` (
  `feed_like_key` bigint(20) NOT NULL auto_increment,
  `feed_key` bigint(20) default NULL,
  `user_key` bigint(20) default NULL,
  `liked_on` datetime default NULL,
  PRIMARY KEY  (`feed_like_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feed_like_master`
--

/*!40000 ALTER TABLE `feed_like_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `feed_like_master` ENABLE KEYS */;


--
-- Definition of table `feed_master`
--

DROP TABLE IF EXISTS `feed_master`;
CREATE TABLE `feed_master` (
  `feed_key` bigint(20) NOT NULL auto_increment,
  `feed_type` varchar(25) default NULL,
  `company_key` bigint(20) default NULL,
  `user_key` bigint(20) default NULL,
  `department_key` bigint(20) default NULL,
  `is_feed_company_wide` varchar(3) default NULL,
  `feed_title` varchar(100) default NULL,
  `feed_description` varchar(200) default NULL,
  `file_path` varchar(200) default NULL,
  `posted_on` datetime default NULL,
  PRIMARY KEY  (`feed_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feed_master`
--

/*!40000 ALTER TABLE `feed_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `feed_master` ENABLE KEYS */;


--
-- Definition of table `folder_master`
--

DROP TABLE IF EXISTS `folder_master`;
CREATE TABLE `folder_master` (
  `folder_key` varchar(30) NOT NULL,
  `company_key` bigint(20) default NULL,
  `folder_name` varchar(255) default NULL,
  PRIMARY KEY  (`folder_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `folder_master`
--

/*!40000 ALTER TABLE `folder_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `folder_master` ENABLE KEYS */;


--
-- Definition of table `group_master`
--

DROP TABLE IF EXISTS `group_master`;
CREATE TABLE `group_master` (
  `group_key` bigint(20) NOT NULL auto_increment,
  `company_key` bigint(20) default NULL,
  `group_name` varchar(255) default NULL,
  PRIMARY KEY  (`group_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_master`
--

/*!40000 ALTER TABLE `group_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_master` ENABLE KEYS */;


--
-- Definition of table `group_user_master`
--

DROP TABLE IF EXISTS `group_user_master`;
CREATE TABLE `group_user_master` (
  `group_user_key` bigint(20) NOT NULL auto_increment,
  `company_key` bigint(20) default NULL,
  `group_key` bigint(20) default NULL,
  `user_key` bigint(20) default NULL,
  PRIMARY KEY  (`group_user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_user_master`
--

/*!40000 ALTER TABLE `group_user_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_user_master` ENABLE KEYS */;


--
-- Definition of table `inquire_master`
--

DROP TABLE IF EXISTS `inquire_master`;
CREATE TABLE `inquire_master` (
  `inquiry_key` bigint(20) NOT NULL auto_increment,
  `inquire_type` varchar(3) default NULL,
  `vr_key` bigint(20) default NULL,
  `rfq_key` bigint(20) default NULL,
  `transaction_key` bigint(20) default NULL,
  `inquire_from` bigint(20) default NULL,
  `inquire_to` bigint(20) default NULL,
  `inquire_details` varchar(1000) default NULL,
  `sent_on` datetime default NULL,
  PRIMARY KEY  (`inquiry_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inquire_master`
--

/*!40000 ALTER TABLE `inquire_master` DISABLE KEYS */;
INSERT INTO `inquire_master` (`inquiry_key`,`inquire_type`,`vr_key`,`rfq_key`,`transaction_key`,`inquire_from`,`inquire_to`,`inquire_details`,`sent_on`) VALUES 
 (1,'VR',1417094139608,0,0,2,1,'hi','2014-11-27 18:46:42');
/*!40000 ALTER TABLE `inquire_master` ENABLE KEYS */;


--
-- Definition of table `invite_master`
--

DROP TABLE IF EXISTS `invite_master`;
CREATE TABLE `invite_master` (
  `invite_key` bigint(20) NOT NULL auto_increment,
  `user_key` bigint(20) default NULL,
  `email` varchar(300) default NULL,
  `invited_on` datetime default NULL,
  PRIMARY KEY  (`invite_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invite_master`
--

/*!40000 ALTER TABLE `invite_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `invite_master` ENABLE KEYS */;


--
-- Definition of table `message_master`
--

DROP TABLE IF EXISTS `message_master`;
CREATE TABLE `message_master` (
  `message_key` bigint(20) NOT NULL auto_increment,
  `user_key_from` bigint(20) default NULL,
  `user_key_to` bigint(20) default NULL,
  `message` varchar(1000) default NULL,
  `delete_from` bigint(20) default NULL,
  `delete_to` bigint(20) default NULL,
  `message_on` datetime default NULL,
  `mstatus` varchar(6) default 'unread',
  PRIMARY KEY  (`message_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `message_master`
--

/*!40000 ALTER TABLE `message_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_master` ENABLE KEYS */;


--
-- Definition of table `notification_master`
--

DROP TABLE IF EXISTS `notification_master`;
CREATE TABLE `notification_master` (
  `notification_key` bigint(20) NOT NULL auto_increment,
  `user_key_from` bigint(20) default NULL,
  `user_key_to` bigint(20) default NULL,
  `company_key_from` bigint(20) default NULL,
  `company_key_to` bigint(20) default NULL,
  `notification_type` varchar(100) default NULL,
  `notification_type_id` bigint(20) default NULL,
  `notification_message` varchar(500) default NULL,
  `status` varchar(6) default NULL,
  `notification_on` datetime default NULL,
  PRIMARY KEY  (`notification_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notification_master`
--

/*!40000 ALTER TABLE `notification_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification_master` ENABLE KEYS */;


--
-- Definition of table `product_master`
--

DROP TABLE IF EXISTS `product_master`;
CREATE TABLE `product_master` (
  `item_key` bigint(20) NOT NULL auto_increment,
  `company_key` bigint(20) default NULL,
  `item_name` varchar(100) default NULL,
  `item_description` varchar(1000) default NULL,
  `part_no` varchar(50) default NULL,
  `sku` varchar(50) default NULL,
  `barcode` varchar(50) default NULL,
  `quantity` decimal(11,2) default NULL,
  `quantity_key` bigint(20) default NULL,
  `price` decimal(12,2) default NULL,
  `currency_key` bigint(20) default NULL,
  `tax` decimal(11,2) default NULL,
  `product_display` varchar(3) default NULL,
  `pics_count` varchar(1000) default NULL,
  PRIMARY KEY  (`item_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_master`
--

/*!40000 ALTER TABLE `product_master` DISABLE KEYS */;
INSERT INTO `product_master` (`item_key`,`company_key`,`item_name`,`item_description`,`part_no`,`sku`,`barcode`,`quantity`,`quantity_key`,`price`,`currency_key`,`tax`,`product_display`,`pics_count`) VALUES 
 (1,1,'lenovo','laptop','b560','b560','b560','0.00',0,'0.00',0,'0.00','NO',', '),
 (2,1,'lenovo','mobile','s660','s660','s660','0.00',0,'0.00',0,'0.00','NO',', '),
 (3,1,'asdfg','asdfg','000','000','000','0.00',0,'0.00',0,'0.00','NO',', '),
 (4,1,'i','d','1','2','3','0.00',0,'0.00',0,'0.00','NO',', '),
 (5,1,'i','d','1','2','3','0.00',0,'0.00',0,'0.00','NO',', '),
 (6,1,'i','d','1','2','3','0.00',0,'0.00',0,'0.00','NO',', '),
 (7,1,'','','','','','0.00',0,'0.00',0,'0.00','NO',', '),
 (8,1,'','','','','','0.00',0,'0.00',0,'0.00','NO',', '),
 (9,1,'n','d','p','s','b','0.00',0,'0.00',0,'0.00','NO',', '),
 (10,1,'n','d','p','s','b','0.00',0,'0.00',0,'0.00','NO',', '),
 (11,1,'n3','d3','p3','s3','b3','0.00',0,'0.00',0,'0.00','NO',', '),
 (12,1,'n5','d5','p5','s5','b5','0.00',0,'0.00',0,'0.00','NO',', ');
/*!40000 ALTER TABLE `product_master` ENABLE KEYS */;


--
-- Definition of table `rating_master`
--

DROP TABLE IF EXISTS `rating_master`;
CREATE TABLE `rating_master` (
  `rating_key` bigint(20) NOT NULL auto_increment,
  `user_key_from` bigint(20) default NULL,
  `user_key_to` bigint(20) default NULL,
  `company_key_from` bigint(20) default NULL,
  `company_key_to` bigint(20) default NULL,
  `rating_title` varchar(100) default NULL,
  `rating_comment` varchar(200) default NULL,
  `rating` bigint(20) default NULL,
  `rating_on` datetime default NULL,
  PRIMARY KEY  (`rating_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rating_master`
--

/*!40000 ALTER TABLE `rating_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `rating_master` ENABLE KEYS */;


--
-- Definition of table `sm_business_category_master`
--

DROP TABLE IF EXISTS `sm_business_category_master`;
CREATE TABLE `sm_business_category_master` (
  `business_category_key` bigint(20) NOT NULL auto_increment,
  `business_category_name` varchar(255) default NULL,
  PRIMARY KEY  (`business_category_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_business_category_master`
--

/*!40000 ALTER TABLE `sm_business_category_master` DISABLE KEYS */;
INSERT INTO `sm_business_category_master` (`business_category_key`,`business_category_name`) VALUES 
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
/*!40000 ALTER TABLE `sm_business_category_master` ENABLE KEYS */;


--
-- Definition of table `sm_country_master`
--

DROP TABLE IF EXISTS `sm_country_master`;
CREATE TABLE `sm_country_master` (
  `country_key` bigint(20) NOT NULL auto_increment,
  `country_name` varchar(255) default NULL,
  `country_code` varchar(255) default NULL,
  PRIMARY KEY  (`country_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_country_master`
--

/*!40000 ALTER TABLE `sm_country_master` DISABLE KEYS */;
INSERT INTO `sm_country_master` (`country_key`,`country_name`,`country_code`) VALUES 
 (1,'Afghanistan','93'),
 (2,'Albania','355'),
 (3,'Algeria','213'),
 (4,'American Samoa','1684'),
 (5,'Andorra','376'),
 (6,'Angola','244'),
 (7,'Anguilla','1264'),
 (8,'Antarctica','6721'),
 (9,'Antigua and Barbuda','1268'),
 (10,'Argentina','54'),
 (11,'Armenia','374'),
 (12,'Aruba','297'),
 (13,'Ascension','247'),
 (14,'Australia','61'),
 (15,'Austria','43'),
 (16,'Azerbaijan','994'),
 (17,'Bahamas','1242'),
 (18,'Bahrain','973'),
 (19,'Bangladesh','880'),
 (20,'Barbados','1246'),
 (21,'Belarus','375'),
 (22,'Belgium','32'),
 (23,'Belize','501'),
 (24,'Benin','229'),
 (25,'Bermuda','1441'),
 (26,'Bhutan','975'),
 (27,'Bolivia','591'),
 (28,'Bosnia and Herzegovina','387'),
 (29,'Botswana','267'),
 (30,'Brazil','55'),
 (31,'Brunei','673'),
 (32,'Bulgaria','359'),
 (33,'Burkina Faso','226'),
 (34,'Burundi','257'),
 (35,'Cambodia','855'),
 (36,'Cameroon','237'),
 (37,'Canada','1'),
 (38,'Cape Verde','238'),
 (39,'Cayman Islands','1345'),
 (40,'Central African Republic','236'),
 (41,'Chad','235'),
 (42,'Chile','56'),
 (43,'China','86'),
 (44,'Christmas Island','618'),
 (45,'Cocos (Keeling) Islands','618'),
 (46,'Colombia','57'),
 (47,'Comoros','269'),
 (48,'Congo','243'),
 (49,'Cook Islands','682'),
 (50,'Costa Rica','506'),
 (51,'C','225'),
 (52,'Croatia','385'),
 (53,'Cuba','53'),
 (54,'Cyprus','357'),
 (55,'Czech Republic','420'),
 (56,'Denmark','45'),
 (57,'Diego Garcia','246'),
 (58,'Djibouti','253'),
 (59,'Dominica','1767'),
 (60,'Dominican Republic','1809'),
 (61,'East Timor','670'),
 (62,'Ecuador','593'),
 (63,'Egypt','20'),
 (64,'El Salvador','503'),
 (65,'Equatorial Guinea','240'),
 (66,'Eritrea','291'),
 (67,'Estonia','372'),
 (68,'Ethiopia','251'),
 (69,'Faeroe Islands','500'),
 (70,'Falkland Islands','298'),
 (71,'Fiji','679'),
 (72,'Finland','358'),
 (73,'France','33'),
 (74,'French Guiana','594'),
 (75,'French Polynesia','689'),
 (76,'Gabon','241'),
 (77,'Gambia','220'),
 (78,'Georgia','995'),
 (79,'Germany','49'),
 (80,'Ghana','233'),
 (81,'Gibraltar','350'),
 (82,'Greece','30'),
 (83,'Greenland','299'),
 (84,'Grenada','1473'),
 (85,'Guadeloupe','590'),
 (86,'Guam','1671'),
 (87,'Guatemala','502'),
 (88,'Guinea','224'),
 (89,'Guinea-Bissau','245'),
 (90,'Guyana','592'),
 (91,'Haiti','509'),
 (92,'Honduras','504'),
 (93,'Hong Kong','852'),
 (94,'Hungary','36'),
 (95,'Iceland','354'),
 (96,'India','91'),
 (97,'Indonesia','62'),
 (98,'Iran','98'),
 (99,'Iraq','964'),
 (100,'Ireland','353'),
 (101,'Israel','972'),
 (102,'Italy','39'),
 (103,'Jamaica','1876'),
 (104,'Japan','81'),
 (105,'Jordan','962'),
 (106,'Kazakhstan','77'),
 (107,'Kenya','254'),
 (108,'Kiribati','686'),
 (109,'Korea (North)','850'),
 (110,'Korea (South)','82'),
 (111,'Kuwait','965'),
 (112,'Kyrgyzstan','996'),
 (113,'Laos','856'),
 (114,'Latvia','371'),
 (115,'Lebanon','961'),
 (116,'Lesotho','266'),
 (117,'Liberia','231'),
 (118,'Libya','218'),
 (119,'Liechtenstein','423'),
 (120,'Lithuania','370'),
 (121,'Luxembourg ','352'),
 (122,'Macau','853'),
 (123,'Macedonia','389'),
 (124,'Madagascar','261'),
 (125,'Malawi','265'),
 (126,'Malaysia','60'),
 (127,'Maldives','960'),
 (128,'Mali','223'),
 (129,'Malta','356'),
 (130,'Marshall Islands','692'),
 (131,'Martinique','596'),
 (132,'Mauritania','222'),
 (133,'Mauritius','230'),
 (134,'Mayotte','52'),
 (135,'Mexico','691'),
 (136,'Micronesia','373'),
 (137,'Moldova','377'),
 (138,'Monaco','976'),
 (139,'Mongolia','382'),
 (140,'Montserrat','1664'),
 (141,'Morocco','212'),
 (142,'Mozambique','258'),
 (143,'Myanmar','95'),
 (144,'Namibia','264'),
 (145,'Nauru','674'),
 (146,'Nepal','977'),
 (147,'Netherlands','31'),
 (148,'Netherlands Antilles','599'),
 (149,'New Caledonia','687'),
 (150,'New Zealand','64'),
 (151,'Nicaragua','505'),
 (152,'Niger','227'),
 (153,'Nigeria','234'),
 (154,'Niue','683'),
 (155,'Norfolk Island','6723'),
 (156,'Northern Marianas','1670'),
 (157,'Norway','47'),
 (158,'Oman','968'),
 (159,'Pakistan','92'),
 (160,'Palau','680'),
 (161,'Palestinian Settlements','970'),
 (162,'Panama','507'),
 (163,'Papua New Guinea','675'),
 (164,'Paraguay','595'),
 (165,'Peru','51'),
 (166,'Philippines','63'),
 (167,'Poland','48'),
 (168,'Portugal','351'),
 (169,'Puerto Rico','1787'),
 (170,'Qatar','974'),
 (171,'R','262'),
 (172,'Romania','40'),
 (173,'Russia','7'),
 (174,'Rwanda','250'),
 (175,'Saint Helena','290'),
 (176,'Saint Kitts and Nevis','1869'),
 (177,'Saint Lucia','1758'),
 (178,'Saint Pierre and Miquelon','508'),
 (179,'Saint Vincent and Grenadines','1784'),
 (180,'Samoa','685'),
 (181,'San Marino','378'),
 (182,'S','239'),
 (183,'Saudi Arabia','966'),
 (184,'Senegal','221'),
 (185,'Serbia','381'),
 (186,'Seychelles','248'),
 (187,'Sierra Leone','232'),
 (188,'Singapore','65'),
 (189,'Slovakia','421'),
 (190,'Slovenia','386'),
 (191,'Solomon Islands','677'),
 (192,'Somalia','252'),
 (193,'South Africa','27'),
 (194,'Spain','34'),
 (195,'Sri Lanka','94'),
 (196,'Sudan','249'),
 (197,'Suriname','597'),
 (198,'Swaziland','268'),
 (199,'Sweden','46'),
 (200,'Switzerland','41'),
 (201,'Syria','963'),
 (202,'Taiwan','886'),
 (203,'Tajikistan','992'),
 (204,'Tanzania','255'),
 (205,'Thailand','66'),
 (206,'Togo','228'),
 (207,'Tokelau','690'),
 (208,'Tonga','676'),
 (209,'Trinidad and Tobago','1868'),
 (210,'Tunisia','216'),
 (211,'Turkey','90'),
 (212,'Turkmenistan','993'),
 (213,'Turks and Caicos Islands','1649'),
 (214,'Tuvalu','688'),
 (215,'Uganda','256'),
 (216,'Ukraine','380'),
 (217,'United Arab Emirates','971'),
 (218,'United Kingdom','44'),
 (219,'United States','1'),
 (220,'Uruguay','598'),
 (221,'US Virgin Islands','1340'),
 (222,'Uzbekistan','998'),
 (223,'Vanuatu','678'),
 (224,'Venezuela','58'),
 (225,'Vietnam','84'),
 (226,'Virgin Islands','1284'),
 (227,'Wake Island','808'),
 (228,'Wallis and Futuna','681'),
 (229,'Yemen','967'),
 (230,'Zambia','260'),
 (231,'Zimbabwe','263');
/*!40000 ALTER TABLE `sm_country_master` ENABLE KEYS */;


--
-- Definition of table `sm_currency_master`
--

DROP TABLE IF EXISTS `sm_currency_master`;
CREATE TABLE `sm_currency_master` (
  `currency_key` bigint(20) NOT NULL auto_increment,
  `currency_code` varchar(50) default NULL,
  `currency_description` varchar(255) default NULL,
  PRIMARY KEY  (`currency_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_currency_master`
--

/*!40000 ALTER TABLE `sm_currency_master` DISABLE KEYS */;
INSERT INTO `sm_currency_master` (`currency_key`,`currency_code`,`currency_description`) VALUES 
 (1,'USD','US Dollar'),
 (2,'AED','United Arab Emirates dirham'),
 (3,'AFN','Afghani'),
 (4,'ALL','Lek'),
 (5,'AMD','Armenian Dram'),
 (6,'ANG','Netherlands Antillian Guilder'),
 (7,'AOA','Kwanza'),
 (8,'ARS','Argentine Peso'),
 (9,'AUD','Australian Dollar'),
 (10,'AWG','Aruban Guilder'),
 (11,'AZN','Azerbaijanian Manat'),
 (12,'BAM','Convertible Marks'),
 (13,'BBD','Barbados Dollar'),
 (14,'BDT','Bangladeshi Taka'),
 (15,'BGN','Bulgarian Lev'),
 (16,'BHD','Bahraini Dinar'),
 (17,'BIF','Burundian Franc'),
 (18,'BMD','Bermudian Dollar (customarily known as Bermuda Dollar)'),
 (19,'BND','Brunei Dollar'),
 (20,'BOB','Boliviano'),
 (21,'BOV','Bolivian Mvdol (Funds code)'),
 (22,'BRL','Brazilian Real'),
 (23,'BSD','Bahamian Dollar'),
 (24,'BTN','Ngultrum'),
 (25,'BWP','Pula'),
 (26,'BYR','Belarussian Ruble'),
 (27,'BZD','Belize Dollar'),
 (28,'CAD','Canadian Dollar'),
 (29,'CDF','Franc Congolais'),
 (30,'CHE','WIR Euro (complementary currency)'),
 (31,'CHF','Swiss Franc'),
 (32,'CHW','WIR Franc (complementary currency)'),
 (33,'CLF','Unidades de formento (Funds code)'),
 (34,'CLP','Chilean Peso'),
 (35,'CNY','Yuan Renminbi'),
 (36,'COP','Colombian Peso'),
 (37,'COU','Unidad de Valor Real'),
 (38,'CRC','Costa Rican Colon'),
 (39,'CUP','Cuban Peso'),
 (40,'CVE','Cape Verde Escudo'),
 (41,'CYP','Cyprus Pound'),
 (42,'CZK','Czech Koruna'),
 (43,'DJF','Djibouti Franc'),
 (44,'DKK','Danish Krone'),
 (45,'DOP','Dominican Peso'),
 (46,'DZD','Algerian Dinar'),
 (47,'EEK','Kroon'),
 (48,'EGP','Egyptian Pound'),
 (49,'ERN','Nakfa'),
 (50,'ETB','Ethiopian Birr'),
 (51,'EUR','Euro'),
 (52,'FJD','Fiji Dollar'),
 (53,'FKP','Falkland Islands Pound'),
 (54,'GBP','Pound Sterling'),
 (55,'GEL','Lari'),
 (56,'GHS','Cedi'),
 (57,'GIP','Gibraltar pound'),
 (58,'GMD','Dalasi'),
 (59,'GNF','Guinea Franc'),
 (60,'GTQ','Quetzal'),
 (61,'GYD','Guyana Dollar'),
 (62,'HKD','Hong Kong Dollar'),
 (63,'HNL','Lempira'),
 (64,'HRK','Croatian Kuna'),
 (65,'HTG','Haiti Gourde'),
 (66,'HUF','Forint'),
 (67,'IDR','Rupiah'),
 (68,'ILS','New Israeli Shekel'),
 (69,'INR','Indian Rupee'),
 (70,'IQD','Iraqi Dinar'),
 (71,'IRR','Iranian Rial'),
 (72,'ISK','Iceland Krona'),
 (73,'JMD','Jamaican Dollar'),
 (74,'JOD','Jordanian Dinar'),
 (75,'JPY','Japanese yen'),
 (76,'KES','Kenyan Shilling'),
 (77,'KGS','Som'),
 (78,'KHR','Riel'),
 (79,'KMF','Comoro Franc'),
 (80,'KPW','North Korean Won'),
 (81,'KRW','South Korean Won'),
 (82,'KWD','Kuwaiti Dinar'),
 (83,'KYD','Cayman Islands Dollar'),
 (84,'KZT','Tenge'),
 (85,'LAK','Kip'),
 (86,'LBP','Lebanese Pound'),
 (87,'LKR','Sri Lanka Rupee'),
 (88,'LRD','Liberian Dollar'),
 (89,'LSL','Loti'),
 (90,'LTL','Lithuanian Litas'),
 (91,'LVL','Latvian Lats'),
 (92,'LYD','Libyan Dinar'),
 (93,'MAD','Moroccan Dirham'),
 (94,'MDL','Moldovan Leu'),
 (95,'MGA','Malagasy Ariary'),
 (96,'MKD','Denar'),
 (97,'MMK','Kyat'),
 (98,'MNT','Tugrik'),
 (99,'MOP','Pataca'),
 (100,'MRO','Ouguiya'),
 (101,'MTL','Maltese Lira'),
 (102,'MUR','Mauritius Rupee'),
 (103,'MVR','Rufiyaa'),
 (104,'MWK','Kwacha'),
 (105,'MXN','Mexican Peso'),
 (106,'MXV','Mexican Unidad de Inversion (UDI) (Funds code)'),
 (107,'MYR','Malaysian Ringgit'),
 (108,'MZN','Metical'),
 (109,'NAD','Namibian Dollar'),
 (110,'NGN','Naira'),
 (111,'NIO','Cordoba Oro'),
 (112,'NOK','Norwegian Krone'),
 (113,'NPR','Nepalese Rupee'),
 (114,'NZD','New Zealand Dollar'),
 (115,'OMR','Rial Omani'),
 (116,'PAB','Balboa'),
 (117,'PEN','Nuevo Sol'),
 (118,'PGK','Kina'),
 (119,'PHP','Philippine Peso'),
 (120,'PKR','Pakistan Rupee'),
 (121,'PLN','Zloty'),
 (122,'PYG','Guarani'),
 (123,'QAR','Qatari Rial'),
 (124,'RON','Romanian New Leu'),
 (125,'RSD','Serbian Dinar'),
 (126,'RUB','Russian Ruble'),
 (127,'RWF','Rwanda Franc'),
 (128,'SAR','Saudi Riyal'),
 (129,'SBD','Solomon Islands Dollar'),
 (130,'SCR','Seychelles Rupee'),
 (131,'SDG','Sudanese Pound'),
 (132,'SEK','Swedish Krona'),
 (133,'SGD','Singapore Dollar'),
 (134,'SHP','Saint Helena Pound'),
 (135,'SKK','Slovak Koruna'),
 (136,'SLL','Leone'),
 (137,'SOS','Somali Shilling'),
 (138,'SRD','Surinam Dollar'),
 (139,'STD','Dobra'),
 (140,'SYP','Syrian Pound'),
 (141,'SZL','Lilangeni'),
 (142,'THB','Baht'),
 (143,'TJS','Somoni'),
 (144,'TMM','Manat'),
 (145,'TND','Tunisian Dinar'),
 (146,'TOP','Pa\'anga'),
 (147,'TRY','New Turkish Lira'),
 (148,'TTD','Trinidad and Tobago Dollar'),
 (149,'TWD','New Taiwan Dollar'),
 (150,'TZS','Tanzanian Shilling'),
 (151,'UAH','Hryvnia'),
 (152,'UGX','Uganda Shilling'),
 (153,'USN',''),
 (154,'USS',''),
 (155,'UYU','Peso Uruguayo'),
 (156,'UZS','Uzbekistan Som'),
 (157,'VEB','Venezuelan bol'),
 (158,'VND','Vietnamese ??ng'),
 (159,'VUV','Vatu'),
 (160,'WST','Samoan Tala'),
 (161,'XAF','CFA Franc BEAC'),
 (162,'XAG','Silver (one Troy ounce)'),
 (163,'XAU','Gold (one Troy ounce)'),
 (164,'XBA','European Composite Unit (EURCO) (Bonds market unit)'),
 (165,'XBB','European Monetary Unit (E.M.U.-6) (Bonds market unit)'),
 (166,'XBC','European Unit of Account 9 (E.U.A.-9) (Bonds market unit)'),
 (167,'XBD','European Unit of Account 17 (E.U.A.-17) (Bonds market unit)'),
 (168,'XCD','East Caribbean Dollar'),
 (169,'XDR','Special Drawing Rights'),
 (170,'XFO','Gold franc (special settlement currency)'),
 (171,'XFU','UIC franc (special settlement currency)'),
 (172,'XOF','CFA Franc BCEAO'),
 (173,'XPD','Palladium (one Troy ounce)'),
 (174,'XPF','CFP franc'),
 (175,'XPT','Platinum (one Troy ounce)'),
 (176,'XTS','Code reserved for testing purposes'),
 (177,'XXX','No currency'),
 (178,'YER','Yemeni Rial'),
 (179,'ZAR','South African Rand'),
 (180,'ZMK','Kwacha'),
 (181,'ZWD','Zimbabwe Dollar');
/*!40000 ALTER TABLE `sm_currency_master` ENABLE KEYS */;


--
-- Definition of table `sm_naic_master`
--

DROP TABLE IF EXISTS `sm_naic_master`;
CREATE TABLE `sm_naic_master` (
  `naics_key` bigint(20) NOT NULL auto_increment,
  `naics_code` varchar(50) default NULL,
  `naics_description` varchar(255) default NULL,
  PRIMARY KEY  (`naics_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_naic_master`
--

/*!40000 ALTER TABLE `sm_naic_master` DISABLE KEYS */;
INSERT INTO `sm_naic_master` (`naics_key`,`naics_code`,`naics_description`) VALUES 
 (1,'111','Crop Production '),
 (2,'112','Animal Production and Aquaculture '),
 (3,'113','Forestry and Logging '),
 (4,'114','Fishing, Hunting and Trapping'),
 (5,'115','Support Activities for Agriculture and Forestry'),
 (6,'211','Oil and Gas Extraction'),
 (7,'212','Mining (except Oil and Gas)'),
 (8,'213','Support Activities for Mining'),
 (9,'221','Utilities '),
 (10,'236','Construction of Buildings'),
 (11,'237','Heavy and Civil Engineering Construction'),
 (12,'238','Specialty Trade Contractors'),
 (13,'311','Food Manufacturing'),
 (14,'312','Beverage and Tobacco Product Manufacturing'),
 (15,'313','Textile Mills'),
 (16,'314','Textile Product Mills'),
 (17,'315','Apparel Manufacturing'),
 (18,'316','Leather and Allied Product Manufacturing'),
 (19,'321','Wood Product Manufacturing'),
 (20,'322','Paper Manufacturing'),
 (21,'323','Printing and Related Support Activities'),
 (22,'324','Petroleum and Coal Products Manufacturing'),
 (23,'325','Chemical Manufacturing'),
 (24,'326','Plastics and Rubber Products Manufacturing'),
 (25,'327','Nonmetallic Mineral Product Manufacturing'),
 (26,'331','Primary Metal Manufacturing'),
 (27,'332','Fabricated Metal Product Manufacturing'),
 (28,'333','Machinery Manufacturing'),
 (29,'334','Computer and Electronic Product Manufacturing'),
 (30,'335','Electrical Equipment, Appliance, and Component Manufacturing'),
 (31,'336','Transportation Equipment Manufacturing'),
 (32,'337','Furniture and Related Product Manufacturing'),
 (33,'339','Miscellaneous Manufacturing'),
 (34,'423','Merchant Wholesalers, Durable Goods '),
 (35,'424','Merchant Wholesalers, Nondurable Goods '),
 (36,'425','Wholesale Electronic Markets and Agents and Brokers '),
 (37,'441','Motor Vehicle and Parts Dealers '),
 (38,'442','Furniture and Home Furnishings Stores '),
 (39,'443','Electronics and Appliance Stores '),
 (40,'444','Building Material and Garden Equipment and Supplies Dealers '),
 (41,'445','Food and Beverage Stores '),
 (42,'446','Health and Personal Care Stores '),
 (43,'447','Gasoline Stations '),
 (44,'448','Clothing and Clothing Accessories Stores '),
 (45,'451','Sporting Goods, Hobby, Musical Instrument, and Book Stores '),
 (46,'452','General Merchandise Stores '),
 (47,'453','Miscellaneous Store Retailers '),
 (48,'454','Nonstore Retailers '),
 (49,'481','Air Transportation'),
 (50,'482','Rail Transportation'),
 (51,'483','Water Transportation'),
 (52,'484','Truck Transportation'),
 (53,'485','Transit and Ground Passenger Transportation'),
 (54,'486','Pipeline Transportation'),
 (55,'487','Scenic and Sightseeing Transportation'),
 (56,'488','Support Activities for Transportation'),
 (57,'491','Postal Service'),
 (58,'492','Couriers and Messengers'),
 (59,'493','Warehousing and Storage'),
 (60,'511','Publishing Industries (except Internet)'),
 (61,'512','Motion Picture and Sound Recording Industries'),
 (62,'515','Broadcasting (except Internet)'),
 (63,'517','Telecommunications'),
 (64,'518','Data Processing, Hosting, and Related Services'),
 (65,'519','Other Information Services'),
 (66,'521','Monetary Authorities-Central Bank'),
 (67,'522','Credit Intermediation and Related Activities'),
 (68,'523','Securities, Commodity Contracts, and Other Financial Investments and Related Activities'),
 (69,'524','Insurance Carriers and Related Activities'),
 (70,'525','Funds, Trusts, and Other Financial Vehicles '),
 (71,'531','Real Estate'),
 (72,'532','Rental and Leasing Services'),
 (73,'533','Lessors of Nonfinancial Intangible Assets (except Copyrighted Works)'),
 (74,'541','Professional, Scientific, and Technical Services'),
 (75,'551','Management of Companies and Enterprises'),
 (76,'561','Administrative and Support Services'),
 (77,'562','Waste Management and Remediation Services'),
 (78,'611','Educational Services'),
 (79,'621','Ambulatory Health Care Services'),
 (80,'622','Hospitals'),
 (81,'623','Nursing and Residential Care Facilities'),
 (82,'624','Social Assistance'),
 (83,'711','Performing Arts, Spectator Sports, and Related Industries'),
 (84,'712','Museums, Historical Sites, and Similar Institutions'),
 (85,'713','Amusement, Gambling, and Recreation Industries'),
 (86,'721','Accommodation'),
 (87,'722','Food Services and Drinking Places'),
 (88,'811','Repair and Maintenance'),
 (89,'812','Personal and Laundry Services'),
 (90,'813','Religious, Grantmaking, Civic, Professional, and Similar Organizations'),
 (91,'814','Private Households'),
 (92,'921','Executive, Legislative, and Other General Government Support '),
 (93,'922','Justice, Public Order, and Safety Activities '),
 (94,'923','Administration of Human Resource Programs '),
 (95,'924','Administration of Environmental Quality Programs '),
 (96,'925','Administration of Housing Programs, Urban Planning, and Community Development '),
 (97,'926','Administration of Economic Programs '),
 (98,'927','Space Research and Technology '),
 (99,'928','National Security and International Affairs ');
/*!40000 ALTER TABLE `sm_naic_master` ENABLE KEYS */;


--
-- Definition of table `sm_pricing_master`
--

DROP TABLE IF EXISTS `sm_pricing_master`;
CREATE TABLE `sm_pricing_master` (
  `pricing_key` bigint(20) NOT NULL auto_increment,
  `plan` varchar(255) default NULL,
  `max_employee` bigint(15) default NULL,
  `max_transaction` bigint(20) default NULL,
  `disk_quota` bigint(20) default NULL,
  PRIMARY KEY  (`pricing_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_pricing_master`
--

/*!40000 ALTER TABLE `sm_pricing_master` DISABLE KEYS */;
INSERT INTO `sm_pricing_master` (`pricing_key`,`plan`,`max_employee`,`max_transaction`,`disk_quota`) VALUES 
 (1,'Basic',25,250000,250),
 (2,'Plus',100,1000000,500),
 (3,'Premium',0,0,750);
/*!40000 ALTER TABLE `sm_pricing_master` ENABLE KEYS */;


--
-- Definition of table `sm_quantity_type_master`
--

DROP TABLE IF EXISTS `sm_quantity_type_master`;
CREATE TABLE `sm_quantity_type_master` (
  `quantity_key` bigint(20) NOT NULL auto_increment,
  `quantity_code` varchar(50) default NULL,
  `quantity_description` varchar(255) default NULL,
  PRIMARY KEY  (`quantity_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_quantity_type_master`
--

/*!40000 ALTER TABLE `sm_quantity_type_master` DISABLE KEYS */;
INSERT INTO `sm_quantity_type_master` (`quantity_key`,`quantity_code`,`quantity_description`) VALUES 
 (1,'BAG','BAGS'),
 (2,'BAL','BALE'),
 (3,'BDL','BUNDLES'),
 (4,'BKL','BUCKLES'),
 (5,'BOU','BILLIONS OF UNITS'),
 (6,'BOX','BOX'),
 (7,'BTL','BOTTLES'),
 (8,'BUN','BUNCHES'),
 (9,'CAN','CANS'),
 (10,'CAS','CASE'),
 (11,'CBM','CUBIC METER'),
 (12,'CCM','CUBIC CENTIMETER'),
 (13,'CMS','CENTIMETER'),
 (14,'CTN','CARTONS'),
 (15,'DOZ','DOZEN'),
 (16,'DRM','DRUM'),
 (17,'FTS','FEET'),
 (18,'GGR','GREAT GROSS'),
 (19,'GMS','GRAMS'),
 (20,'GRS','GROSS'),
 (21,'GYD','GROSS YARDS'),
 (22,'KGA','KILOGRAM ACTIVITY'),
 (23,'KGS','KILOGRAMS'),
 (24,'KIT','KITS'),
 (25,'KLR','KILOLITER'),
 (26,'KME','KILOMETERS'),
 (27,'KWH','KILOWATTHOUR'),
 (28,'LBS','POUNDS'),
 (29,'LTR','LITERS'),
 (30,'MLT','MILLILITER'),
 (31,'MTR','METER'),
 (32,'MTS','METRIC TON'),
 (33,'NOS','NUMBER'),
 (34,'PAC','PACKS'),
 (35,'PCS','PIECES'),
 (36,'PRS','PAIRS'),
 (37,'QTL','QUINTAL'),
 (38,'RLS','ROLLS'),
 (39,'ROL','ROLLS'),
 (40,'SET','SETS'),
 (41,'SQF','SQUARE FEET'),
 (42,'SQM','SQUARE METER'),
 (43,'SQY','SQUARE YARDS');
/*!40000 ALTER TABLE `sm_quantity_type_master` ENABLE KEYS */;


--
-- Definition of table `sm_state_master`
--

DROP TABLE IF EXISTS `sm_state_master`;
CREATE TABLE `sm_state_master` (
  `state_key` bigint(20) NOT NULL auto_increment,
  `country_key` bigint(20) default NULL,
  `state_name` varchar(255) default NULL,
  PRIMARY KEY  (`state_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_state_master`
--

/*!40000 ALTER TABLE `sm_state_master` DISABLE KEYS */;
INSERT INTO `sm_state_master` (`state_key`,`country_key`,`state_name`) VALUES 
 (1,219,'Alabama'),
 (2,219,'Alaska'),
 (3,219,'Arizona'),
 (4,219,'Arkansas'),
 (5,219,'California'),
 (6,219,'Colorado'),
 (7,219,'Connecticut'),
 (8,219,'Delaware'),
 (9,219,'Florida'),
 (10,219,'Georgia'),
 (11,219,'Hawaii'),
 (12,219,'Idaho'),
 (13,219,'Illinois'),
 (14,219,'Indiana'),
 (15,219,'Iowa'),
 (16,219,'Kansas'),
 (17,219,'Kentucky'),
 (18,219,'Louisiana'),
 (19,219,'Maine'),
 (20,219,'Maryland'),
 (21,219,'Massachusetts'),
 (22,219,'Michigan'),
 (23,219,'Minnesota'),
 (24,219,'Mississippi'),
 (25,219,'Missouri'),
 (26,219,'Montana'),
 (27,219,'Nebraska'),
 (28,219,'Nevada'),
 (29,219,'New Hampshire'),
 (30,219,'New Jersey'),
 (31,219,'New Mexico'),
 (32,219,'New York'),
 (33,219,'North Carolina'),
 (34,219,'North Dakota'),
 (35,219,'Ohio'),
 (36,219,'Oklahoma'),
 (37,219,'Oregon'),
 (38,219,'Pennsylvania'),
 (39,219,'Rhode Island'),
 (40,219,'South Carolina'),
 (41,219,'South Dakota'),
 (42,219,'Tennessee'),
 (43,219,'Texas'),
 (44,219,'Utah'),
 (45,219,'Vermont'),
 (46,219,'Virginia'),
 (47,219,'Washington'),
 (48,219,'West Virginia'),
 (49,219,'Wisconsin'),
 (50,219,'Wyoming'),
 (51,96,'Andaman and Nikobar'),
 (52,96,'Andhra Pradesh'),
 (53,96,'Arunachal Pradesh'),
 (54,96,'Assam'),
 (55,96,'Bihar'),
 (56,96,'Chandigarh'),
 (57,96,'Chhattisgarh'),
 (58,96,'Dadra and Nagar Haveli'),
 (59,96,'Daman and Diu'),
 (60,96,'New Delhi'),
 (61,96,'Goa'),
 (62,96,'Gurjrat'),
 (63,96,'Haryana'),
 (64,96,'Himachal Pradesh'),
 (65,96,'Jammu and Kashmir'),
 (66,96,'Jharkhand'),
 (67,96,'Karnataka'),
 (68,96,'Kerala'),
 (69,96,'Lakshadeep'),
 (70,96,'Madhya Pradesh'),
 (71,96,'Maharashtra'),
 (72,96,'Manipur'),
 (73,96,'Meghalaya'),
 (74,96,'Mijoram'),
 (75,96,'Nagaland'),
 (76,96,'Orissa'),
 (77,96,'Puducherry'),
 (78,96,'Punjab'),
 (79,96,'Rajasthan'),
 (80,96,'Sikkim'),
 (81,96,'Tamilnadu'),
 (82,96,'Telangana'),
 (83,96,'Tripura'),
 (84,96,'Uttar Pradesh'),
 (85,96,'Uttrakhanad'),
 (86,96,'West Bengal');
/*!40000 ALTER TABLE `sm_state_master` ENABLE KEYS */;


--
-- Definition of table `terms_conditions_master`
--

DROP TABLE IF EXISTS `terms_conditions_master`;
CREATE TABLE `terms_conditions_master` (
  `tc_key` bigint(20) NOT NULL auto_increment,
  `company_key` bigint(20) default NULL,
  `transaction_type` varchar(3) default NULL,
  `text_message` varchar(1000) default NULL,
  `created_on` datetime default NULL,
  PRIMARY KEY  (`tc_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `terms_conditions_master`
--

/*!40000 ALTER TABLE `terms_conditions_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `terms_conditions_master` ENABLE KEYS */;


--
-- Definition of table `trans_rfq_item_master`
--

DROP TABLE IF EXISTS `trans_rfq_item_master`;
CREATE TABLE `trans_rfq_item_master` (
  `trans_rqf_item_key` bigint(20) NOT NULL auto_increment,
  `trans_rqf_key` bigint(20) default NULL,
  `rfq_no` varchar(50) default NULL,
  `item_key` bigint(20) default NULL,
  `part_no` varchar(50) default NULL,
  `barcode` varchar(50) default NULL,
  `quantity` decimal(12,2) default NULL,
  `quantity_unit_key` bigint(20) default NULL,
  `ship_date` datetime default NULL,
  PRIMARY KEY  (`trans_rqf_item_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trans_rfq_item_master`
--

/*!40000 ALTER TABLE `trans_rfq_item_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `trans_rfq_item_master` ENABLE KEYS */;


--
-- Definition of table `trans_rfq_master`
--

DROP TABLE IF EXISTS `trans_rfq_master`;
CREATE TABLE `trans_rfq_master` (
  `trans_rfq_key` bigint(20) NOT NULL auto_increment,
  `rfq_no` varchar(20) default NULL,
  `company_key_from` bigint(20) default NULL,
  `company_key_to` bigint(20) default NULL,
  `user_key_from` bigint(20) default NULL,
  `user_key_to` bigint(20) default NULL,
  `trans_status` varchar(50) default NULL,
  `trans_action` varchar(50) default NULL,
  `quote_ref` varchar(20) default NULL,
  `is_outside` varchar(3) default NULL,
  `is_outside_address` varchar(200) default NULL,
  `recurring` varchar(20) default NULL,
  `is_quote_created` varchar(3) default NULL,
  `created_on` datetime default NULL,
  `action_on` datetime default NULL,
  PRIMARY KEY  (`trans_rfq_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trans_rfq_master`
--

/*!40000 ALTER TABLE `trans_rfq_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `trans_rfq_master` ENABLE KEYS */;


--
-- Definition of table `transaction_item_master`
--

DROP TABLE IF EXISTS `transaction_item_master`;
CREATE TABLE `transaction_item_master` (
  `trans_item_key` bigint(20) NOT NULL auto_increment,
  `trans_key` bigint(20) default NULL,
  `q_quote_no` varchar(20) default NULL,
  `q_item_key` bigint(20) default NULL,
  `q_part_no` varchar(50) default NULL,
  `q_barcode` varchar(50) default NULL,
  `q_quantity` double(10,2) default NULL,
  `q_quantity_type_key` bigint(20) default NULL,
  `q_ship_date` datetime default NULL,
  `q_price` double(20,2) default NULL,
  `q_currency_key` bigint(20) default NULL,
  `q_multiplier` int(11) default NULL,
  `po_po_no` varchar(20) default NULL,
  `po_item_key` bigint(20) default NULL,
  `po_part_no` varchar(50) default NULL,
  `po_barcode` varchar(50) default NULL,
  `po_quantity` double(10,2) default NULL,
  `po_quantity_type_key` bigint(20) default NULL,
  `po_ship_date` datetime default NULL,
  `po_price` double(20,2) default NULL,
  `po_currency_key` bigint(20) default NULL,
  `po_multiplier` int(11) default NULL,
  `inv_inv_no` varchar(20) default NULL,
  `inv_item_key` bigint(20) default NULL,
  `inv_part_no` varchar(50) default NULL,
  `inv_barcode` varchar(50) default NULL,
  `inv_quantity_oredered` double(10,2) default NULL,
  `inv_quantity_oredered_type_key` bigint(20) default NULL,
  `inv_quantity_shipped` double(10,2) default NULL,
  `inv_quantity_shipped_type_key` bigint(20) default NULL,
  `inv_ship_date` datetime default NULL,
  `inv_price` double(20,2) default NULL,
  `inv_currency_key` bigint(20) default NULL,
  `inv_multiplier` int(11) default NULL,
  PRIMARY KEY  (`trans_item_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction_item_master`
--

/*!40000 ALTER TABLE `transaction_item_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_item_master` ENABLE KEYS */;


--
-- Definition of table `transaction_master`
--

DROP TABLE IF EXISTS `transaction_master`;
CREATE TABLE `transaction_master` (
  `trans_key` bigint(20) NOT NULL auto_increment,
  `company_key_from` bigint(20) default NULL,
  `company_key_to` bigint(20) default NULL,
  `user_key_from` bigint(20) default NULL,
  `user_key_to` bigint(20) default NULL,
  `transaction_type` varchar(20) default NULL,
  `q_trans_rqf_key` bigint(20) default NULL,
  `q_quote_no` varchar(20) default NULL,
  `q_trans_status` varchar(50) default NULL,
  `q_trans_action` varchar(50) default NULL,
  `q_quote_ref` varchar(20) default NULL,
  `q_is_outside` varchar(3) default NULL,
  `q_is_outside_address` varchar(200) default NULL,
  `q_recurring` varchar(20) default NULL,
  `q_total_amount` double(20,2) default NULL,
  `q_tax_percentage` double(20,2) default NULL,
  `q_total_billing_amount` double(20,2) default NULL,
  `q_is_po_created` varchar(3) default NULL,
  `q_created_on` datetime default NULL,
  `q_action_on` datetime default NULL,
  `po_trans_rqf_key` bigint(20) default NULL,
  `po_no` varchar(20) default NULL,
  `po_trans_status` varchar(50) default NULL,
  `po_trans_action` varchar(50) default NULL,
  `po_quote_ref` varchar(20) default NULL,
  `po_is_outside` varchar(3) default NULL,
  `po_is_outside_address` varchar(200) default NULL,
  `po_recurring` varchar(20) default NULL,
  `po_total_amount` double(20,2) default NULL,
  `po_tax_percentage` double(20,2) default NULL,
  `po_total_billing_amount` double(20,2) default NULL,
  `po_is_inv_created` varchar(3) default NULL,
  `po_created_on` datetime default NULL,
  `po_action_on` datetime default NULL,
  `inv_trans_rqf_key` bigint(20) default NULL,
  `inv_quote_no` varchar(20) default NULL,
  `inv_trans_status` varchar(50) default NULL,
  `inv_trans_action` varchar(50) default NULL,
  `inv_quote_ref` varchar(20) default NULL,
  `inv_is_outside` varchar(3) default NULL,
  `inv_is_outside_address` varchar(200) default NULL,
  `inv_recurring` varchar(20) default NULL,
  `inv_total_amount` double(20,2) default NULL,
  `inv_tax_percentage` double(20,2) default NULL,
  `inv_total_billing_amount` double(20,2) default NULL,
  `inv_is_po_created` varchar(3) default NULL,
  `inv_created_on` datetime default NULL,
  `inv_action_on` datetime default NULL,
  `freight_handling` double(10,2) default NULL,
  `discount` double(20,2) default NULL,
  `invoice_billing_period` varchar(255) default NULL,
  `invoice_due_date` datetime default NULL,
  `invoice_no` varchar(255) default NULL,
  `freight_carrier` varchar(1024) default NULL,
  `bill_of_landing` varchar(50) default NULL,
  `freight_weight` double(10,2) default NULL,
  `freight_weight_unit` bigint(20) default NULL,
  `shipped_date` date default NULL,
  `is_nonpo_invoice` varchar(3) default NULL,
  `po_num` varchar(255) default NULL,
  `is_diff_address` varchar(3) default NULL,
  `diff_address` varchar(500) default NULL,
  PRIMARY KEY  (`trans_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction_master`
--

/*!40000 ALTER TABLE `transaction_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_master` ENABLE KEYS */;


--
-- Definition of table `user_mailing_address_master`
--

DROP TABLE IF EXISTS `user_mailing_address_master`;
CREATE TABLE `user_mailing_address_master` (
  `mailing_key` bigint(20) NOT NULL auto_increment,
  `user_key` varchar(100) default NULL,
  `country` varchar(100) default NULL,
  `address` varchar(200) default NULL,
  `city` varchar(200) default NULL,
  `state` varchar(200) default NULL,
  `zipcode` varchar(200) default NULL,
  `created_on` datetime default NULL,
  PRIMARY KEY  (`mailing_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_mailing_address_master`
--

/*!40000 ALTER TABLE `user_mailing_address_master` DISABLE KEYS */;
INSERT INTO `user_mailing_address_master` (`mailing_key`,`user_key`,`country`,`address`,`city`,`state`,`zipcode`,`created_on`) VALUES 
 (1,'1','96','','','78','','2014-11-27 18:40:22'),
 (2,'2','96','','','78','','2014-11-27 18:41:53'),
 (3,'3','96','','','78','','2014-11-27 18:43:03');
/*!40000 ALTER TABLE `user_mailing_address_master` ENABLE KEYS */;


--
-- Definition of table `user_master`
--

DROP TABLE IF EXISTS `user_master`;
CREATE TABLE `user_master` (
  `user_key` bigint(20) NOT NULL auto_increment,
  `company_key` varchar(100) default NULL,
  `user_type` varchar(20) default NULL,
  `first_name` varchar(200) default NULL,
  `last_name` varchar(200) default NULL,
  `user_pic_path` varchar(50) default NULL,
  `title` varchar(50) default NULL,
  `department` varchar(100) default NULL,
  `manager_supervisor` varchar(100) default NULL,
  `phone` varchar(50) default NULL,
  `cell` varchar(50) default NULL,
  `fax` varchar(50) default NULL,
  `email` varchar(100) default NULL,
  `pass_word` varchar(100) default NULL,
  `created_on` datetime default NULL,
  `account_status` varchar(20) default NULL,
  `account_timestamp` varchar(20) default NULL,
  `account_activated_on` datetime default NULL,
  PRIMARY KEY  (`user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_master`
--

/*!40000 ALTER TABLE `user_master` DISABLE KEYS */;
INSERT INTO `user_master` (`user_key`,`company_key`,`user_type`,`first_name`,`last_name`,`user_pic_path`,`title`,`department`,`manager_supervisor`,`phone`,`cell`,`fax`,`email`,`pass_word`,`created_on`,`account_status`,`account_timestamp`,`account_activated_on`) VALUES 
 (1,'1','Admin','lokesh','kakkar',' ','MR','','','9815990067','','','info@webkrit.com','123456@a','2014-11-27 18:40:22','Activated','1417093822726','2014-11-27 18:41:57'),
 (2,'2','Admin','lucky','lokesh',' ','MR','','','8288033280','','','info@edukrit.com','123456@a','2014-11-27 18:41:53','Activated','1417093913671','2014-11-27 18:42:14'),
 (3,'3','Admin','lucky','kakkar',' ','MR','','','00000','','','kakkar.lokesh@gmail.com','123456@a','2014-11-27 18:43:03','Activated','1417093983401','2014-11-27 18:43:25');
/*!40000 ALTER TABLE `user_master` ENABLE KEYS */;


--
-- Definition of table `user_setting_master`
--

DROP TABLE IF EXISTS `user_setting_master`;
CREATE TABLE `user_setting_master` (
  `user_key` bigint(20) NOT NULL,
  `user_status` varchar(25) default NULL,
  `new_email` varchar(50) default NULL,
  `use_registered_email` varchar(3) default NULL,
  `new_mobile` varchar(10) default NULL,
  `workinghours_notify` varchar(3) default NULL,
  `nonworkinghours_notify` varchar(3) default NULL,
  `workinghours_mode` varchar(10) default NULL,
  `nonworkinghours_mode` varchar(10) default NULL,
  `stop_notify` varchar(3) default NULL,
  `stoptime_from` date default NULL,
  `stoptime_to` date default NULL,
  `working_days` varchar(7) default NULL,
  `sun_from` time default NULL,
  `sun_to` time default NULL,
  `mon_from` time default NULL,
  `mon_to` time default NULL,
  `tue_from` time default NULL,
  `tue_to` time default NULL,
  `wed_from` time default NULL,
  `wed_to` time default NULL,
  `thu_from` time default NULL,
  `thu_to` time default NULL,
  `fri_from` time default NULL,
  `fri_to` time default NULL,
  `sat_from` time default NULL,
  `sat_to` time default NULL,
  PRIMARY KEY  (`user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_setting_master`
--

/*!40000 ALTER TABLE `user_setting_master` DISABLE KEYS */;
INSERT INTO `user_setting_master` (`user_key`,`user_status`,`new_email`,`use_registered_email`,`new_mobile`,`workinghours_notify`,`nonworkinghours_notify`,`workinghours_mode`,`nonworkinghours_mode`,`stop_notify`,`stoptime_from`,`stoptime_to`,`working_days`,`sun_from`,`sun_to`,`mon_from`,`mon_to`,`tue_from`,`tue_to`,`wed_from`,`wed_to`,`thu_from`,`thu_to`,`fri_from`,`fri_to`,`sat_from`,`sat_to`) VALUES 
 (1,'Not Connected','','no','','yes','yes','Email','Email','no','2014-11-27','2014-11-27','1234567','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00'),
 (2,'Not Connected','','no','','yes','yes','Email','Email','no','2014-11-27','2014-11-27','1234567','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00'),
 (3,'Not Connected','','no','','yes','yes','Email','Email','no','2014-11-27','2014-11-27','1234567','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00','09:00:00','17:00:00');
/*!40000 ALTER TABLE `user_setting_master` ENABLE KEYS */;


--
-- Definition of table `vendor_registration_master`
--

DROP TABLE IF EXISTS `vendor_registration_master`;
CREATE TABLE `vendor_registration_master` (
  `vr_key` bigint(20) NOT NULL auto_increment,
  `company_key_from` bigint(20) default NULL,
  `company_key_to` bigint(20) default NULL,
  `user_key_from` bigint(20) default NULL,
  `user_key_to` bigint(20) default NULL,
  `request_sender_type` varchar(10) default NULL,
  `company_type` varchar(100) default NULL,
  `business_contact_name` varchar(50) default NULL,
  `business_tax_id` varchar(50) default NULL,
  `naics_code` bigint(20) default NULL,
  `w9tax_form_status` varchar(3) default NULL,
  `w9tax_form_path` varchar(200) default NULL,
  `business_size` varchar(5) default NULL,
  `business_classification` varchar(14) default NULL,
  `additional_details` varchar(1000) default NULL,
  `request_status` varchar(20) default NULL,
  `sent_on` datetime default NULL,
  `accepted_on` datetime default NULL,
  PRIMARY KEY  (`vr_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vendor_registration_master`
--

/*!40000 ALTER TABLE `vendor_registration_master` DISABLE KEYS */;
INSERT INTO `vendor_registration_master` (`vr_key`,`company_key_from`,`company_key_to`,`user_key_from`,`user_key_to`,`request_sender_type`,`company_type`,`business_contact_name`,`business_tax_id`,`naics_code`,`w9tax_form_status`,`w9tax_form_path`,`business_size`,`business_classification`,`additional_details`,`request_status`,`sent_on`,`accepted_on`) VALUES 
 (1417094139608,1,2,1,2,'Supplier','Individual/Sole Proprietor','lokesh','',0,'no','','Large','0000000000000','hi','Accepted','2014-11-27 18:45:39','2014-11-27 18:45:39'),
 (1417094213762,2,1,2,1,'Buyer','Individual/Sole Proprietor','lucky lokesh','',0,'no','','Large','0000000000000','','Accepted','2014-11-27 18:46:53','2014-11-27 18:46:53');
/*!40000 ALTER TABLE `vendor_registration_master` ENABLE KEYS */;


--
-- Definition of table `watchlist_master`
--

DROP TABLE IF EXISTS `watchlist_master`;
CREATE TABLE `watchlist_master` (
  `watchlist_key` bigint(20) NOT NULL auto_increment,
  `user_key` bigint(20) default NULL,
  `watch_list_name` varchar(50) default NULL,
  PRIMARY KEY  (`watchlist_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watchlist_master`
--

/*!40000 ALTER TABLE `watchlist_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `watchlist_master` ENABLE KEYS */;


--
-- Definition of table `watchlist_member_master`
--

DROP TABLE IF EXISTS `watchlist_member_master`;
CREATE TABLE `watchlist_member_master` (
  `watchlist_member_key` bigint(20) NOT NULL auto_increment,
  `watchlist_key` bigint(20) default NULL,
  `user_key` bigint(20) default NULL,
  PRIMARY KEY  (`watchlist_member_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watchlist_member_master`
--

/*!40000 ALTER TABLE `watchlist_member_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `watchlist_member_master` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
