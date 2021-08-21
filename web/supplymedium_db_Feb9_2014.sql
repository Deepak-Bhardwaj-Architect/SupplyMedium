-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 08, 2014 at 10:53 AM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `supplymedium_db`
--
drop database if exists `supplyme_db`;
CREATE DATABASE `supplyme_db` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `supplyme_db`;

CREATE TABLE IF NOT EXISTS `news_feed_like_comment_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `news_feed_id` bigint(20) default NULL,
  `comment_detail` varchar(10000) default NULL,
  `commented_on` timestamp default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

CREATE TABLE IF NOT EXISTS `company_feed_like_comment_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `news_feed_id` bigint(20) default NULL,
  `comment_detail` varchar(10000) default NULL,
  `commented_on` timestamp default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

CREATE TABLE IF NOT EXISTS `department_feed_like_comment_master` (
  `id` bigint(20) NOT NULL auto_increment,
  `news_feed_id` bigint(20) default NULL,
  `comment_detail` varchar(10000) default NULL,
  `commented_on` timestamp default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;


create table _error_master
(
    error_id bigint auto_increment primary key,
    error_date  date,
    error_time  time,
    error_message   varchar(60000)

);

create table _logger_master
(
    logger_id bigint auto_increment primary key,
    logger_date  date,
    logger_time  time,
    logger_message   varchar(60000)

);

create table _coupon_master
(
    coupon_id bigint auto_increment primary key,
    coupon_code  varchar(255),
    date_of_issue date,
    valid_from date,
    valid_upto date,
    discount_in_percentage int -- extension_in_days // after few months/years
);

create table _redeem_master
(
    redeem_id bigint auto_increment primary key,
    date_of_redeem date,
    coupon_code varchar(255),
    company_id varchar(255),
    package_selected_by_company varchar(255),
    package_price_actual int,
    discount_in_percent int,
    package_price_discounted int
);

create table wk_user
( wk_user_id int auto_increment primary key,
email varchar(100),
password varchar(50),
status int(2)
); 
-- --------------------------------------------------------

--
-- Table structure for table `account_lockout_policies`
--

CREATE TABLE IF NOT EXISTS `account_lockout_policies` (
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


-- --------------------------------------------------------

--
-- Table structure for table `activation_pending`
--

CREATE TABLE IF NOT EXISTS `activation_pending` (
  `user_rel_key` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `activation_pending`
--


-- --------------------------------------------------------

--
-- Table structure for table `ad_statistic`
--

CREATE TABLE IF NOT EXISTS `ad_statistic` (
  `ad_stat_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ad_rel_id` bigint(20) DEFAULT NULL,
  `date` date NOT NULL,
  `count` int(10) NOT NULL,
  PRIMARY KEY (`ad_stat_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `ad_statistic`
--


-- --------------------------------------------------------

--
-- Table structure for table `advertisement`
--

CREATE TABLE IF NOT EXISTS `advertisement` (
  `ad_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `regn_rel_key` varchar(255) NOT NULL,
  `user_rel_key` varchar(255) NOT NULL,
  `alternate_text` text NOT NULL,
  `link` varchar(255) NOT NULL,
  `ad_image_path` varchar(255) NOT NULL,
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ad_id`),
  KEY `ad_id` (`ad_id`,`regn_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `advertisement`
--


-- --------------------------------------------------------

--
-- Table structure for table `business_categories`
--

CREATE TABLE IF NOT EXISTS `business_categories` (
  `business_category_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `business_category_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`business_category_id`),
  KEY `business_category_name` (`business_category_name`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=38 ;

--
-- Dumping data for table `business_categories`
--

INSERT INTO `business_categories` (`business_category_id`, `business_category_name`) VALUES
(1, 'Aerospace/Defence'),
(2, 'Automobiles and Parts'),
(3, 'Bank/Financial Institution'),
(4, 'Biotechnology'),
(5, 'Cause'),
(6, 'Chemicals'),
(7, 'Church/Religious Organization'),
(8, 'Community Organization'),
(9, 'Company'),
(10, 'Computers/Technology'),
(11, 'Consulting/Business Services'),
(12, 'Education'),
(13, 'Energy/Utility'),
(14, 'Engineering/Construction'),
(15, 'Farming/Agriculture'),
(16, 'Food/Beverages'),
(17, 'Government Organization'),
(18, 'Health/Beauty'),
(19, 'Health/Medical/Pharmaceuticals'),
(20, 'Industrials'),
(21, 'Insurance Company'),
(22, 'Internet/Software'),
(23, 'Legal/Law'),
(24, 'Media/News/Publishing'),
(25, 'Mining/Materials'),
(26, 'Non-Governmental Organization (NGO)'),
(27, 'Non-Profit Organization'),
(28, 'Organization'),
(29, 'Political Organization'),
(30, 'Political Party'),
(31, 'Retail and Consumer Merchandise'),
(32, 'School'),
(33, 'Small Business'),
(34, 'Telecommunication'),
(35, 'Transport/Freight'),
(36, 'Travel/Leisure'),
(37, 'University');

-- --------------------------------------------------------

--
-- Table structure for table `company_feed`
--

CREATE TABLE IF NOT EXISTS `company_feed` (
  `company_feed_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `regn_rel_key` varchar(255) NOT NULL,
  `user_rel_key` varchar(255) NOT NULL,
  `feed_title` text NOT NULL,
  `feed` text NOT NULL,
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`company_feed_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `company_feed`
--


-- --------------------------------------------------------

--
-- Table structure for table `company_registration`
--

CREATE TABLE IF NOT EXISTS `company_registration` (
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `company_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `company_logo_path` text COLLATE utf8_unicode_ci NOT NULL,
  `business_category_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `company_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `company_theme` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pricing_option` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `company_phoneno` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `uuid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `company_status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `segment_division_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `business_unit_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `regn_key` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `is_regn_vendor` tinyint(1) NOT NULL DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `regn_key` (`regn_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `company_registration`
--


-- --------------------------------------------------------

--
-- Table structure for table `country_company_mapper`
--

CREATE TABLE IF NOT EXISTS `country_company_mapper` (
  `regn_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `country_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  KEY `regn_key` (`regn_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `country_company_mapper`
--


-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE IF NOT EXISTS `departments` (
  `dept_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `dept_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `regn_rel_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  KEY `dept_key` (`dept_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `departments`
--


-- --------------------------------------------------------

--
-- Table structure for table `dept_feed`
--

CREATE TABLE IF NOT EXISTS `dept_feed` (
  `dept_feed_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `regn_rel_key` varchar(255) NOT NULL,
  `user_rel_key` varchar(255) NOT NULL,
  `dept_rel_key` varchar(255) NOT NULL,
  `feed_title` text NOT NULL,
  `feed` text NOT NULL,
  `company_feed_flag` tinyint(1) NOT NULL,
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`dept_feed_id`),
  KEY `regn_rel_key` (`regn_rel_key`,`user_rel_key`,`dept_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `dept_feed`
--


-- --------------------------------------------------------

--
-- Table structure for table `dept_files`
--

CREATE TABLE IF NOT EXISTS `dept_files` (
  `dept_file_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `regn_rel_key` varchar(255) NOT NULL,
  `dept_rel_key` varchar(255) NOT NULL,
  `dept_folder_rel_key` varchar(255) NOT NULL,
  `relative_path` varchar(1024) NOT NULL,
  `local_path` varchar(255) NOT NULL,
  `web_url` varchar(255) NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `file_type` varchar(255) NOT NULL,
  `file_size` decimal(20,2) NOT NULL DEFAULT '0.00',
  `recycle_flag` tinyint(1) NOT NULL,
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dept_file_id`),
  KEY `regn_rel_key` (`regn_rel_key`,`dept_rel_key`,`dept_folder_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `dept_files`
--


-- --------------------------------------------------------

--
-- Table structure for table `dept_folder_mapping`
--

CREATE TABLE IF NOT EXISTS `dept_folder_mapping` (
  `dept_rel_key` varchar(255) NOT NULL,
  `dept_folder_rel_key` varchar(255) NOT NULL,
  `recycle_flag` tinyint(1) NOT NULL DEFAULT '0',
  KEY `dept_folder_rel_key` (`dept_folder_rel_key`),
  KEY `dept_rel_key` (`dept_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dept_folder_mapping`
--


-- --------------------------------------------------------

--
-- Table structure for table `dept_folders`
--

CREATE TABLE IF NOT EXISTS `dept_folders` (
  `dept_folder_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `folder_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `folder_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `regn_rel_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  KEY `dept_folder_key` (`dept_folder_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dept_folders`
--


-- --------------------------------------------------------

--
-- Table structure for table `dept_privileges`
--

CREATE TABLE IF NOT EXISTS `dept_privileges` (
  `dept_rel_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `privileges` int(32) NOT NULL,
  KEY `dept_rel_key` (`dept_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dept_privileges`
--


-- --------------------------------------------------------

--
-- Table structure for table `external_page`
--

CREATE TABLE IF NOT EXISTS `external_page` (
  `externalpageid` int(11) NOT NULL AUTO_INCREMENT,
  `compnayRegnKey` varchar(255) NOT NULL,
  `companyURLName` varchar(255) NOT NULL,
  `pageType` varchar(15) NOT NULL,
  PRIMARY KEY (`externalpageid`),
  UNIQUE KEY `compnayRegnKey` (`compnayRegnKey`),
  KEY `compnayRegnKey_2` (`compnayRegnKey`),
  KEY `compnayRegnKey_3` (`compnayRegnKey`),
  KEY `compnayRegnKey_4` (`compnayRegnKey`),
  KEY `compnayRegnKey_5` (`compnayRegnKey`),
  KEY `compnayRegnKey_6` (`compnayRegnKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `external_page`
--


-- --------------------------------------------------------

--
-- Table structure for table `group_dept_mapping`
--

CREATE TABLE IF NOT EXISTS `group_dept_mapping` (
  `group_rel_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `dept_rel_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `group_dept_mapping_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`group_dept_mapping_id`),
  UNIQUE KEY `group_dept_mapping_id` (`group_dept_mapping_id`),
  KEY `group_rel_key` (`group_rel_key`),
  KEY `dept_rel_key` (`dept_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `group_dept_mapping`
--


-- --------------------------------------------------------

--
-- Table structure for table `group_folder_access`
--

CREATE TABLE IF NOT EXISTS `group_folder_access` (
  `group_folder_access_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `regn_rel_key` varchar(255) NOT NULL,
  `dept_rel_key` varchar(255) NOT NULL,
  `group_rel_key` varchar(255) NOT NULL,
  `folder_rel_key` varchar(255) NOT NULL,
  `read_flag` tinyint(1) NOT NULL,
  `read_write_flag` tinyint(1) NOT NULL,
  PRIMARY KEY (`group_folder_access_id`),
  KEY `regn_rel_key` (`regn_rel_key`),
  KEY `group_rel_key` (`group_rel_key`),
  KEY `folder_rel_key` (`folder_rel_key`),
  KEY `dept_rel_key` (`dept_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `group_folder_access`
--


-- --------------------------------------------------------

--
-- Table structure for table `group_privileges`
--

CREATE TABLE IF NOT EXISTS `group_privileges` (
  `group_rel_key` varchar(255) NOT NULL,
  `privileges` bigint(20) NOT NULL,
  KEY `group_rel_key` (`group_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_privileges`
--


-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE IF NOT EXISTS `groups` (
  `group_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `regn_rel_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  KEY `group_key` (`group_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groups`
--


-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE IF NOT EXISTS `invoice` (
  `invoice_id` bigint(20) AUTO_INCREMENT,
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
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`invoice_trans_id` varchar(100),
`outside_supplier_address` varchar(500),
  PRIMARY KEY (`invoice_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



--
-- Dumping data for table `invoice`
--


-- --------------------------------------------------------

--
-- Table structure for table `invoice_item`
--

CREATE TABLE IF NOT EXISTS `invoice_item` (
  `invoice_item_id` bigint(20) NOT NULL AUTO_INCREMENT,
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
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `barcode_id` varchar(100), 
  PRIMARY KEY (`invoice_item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `invoice_item`
--


-- --------------------------------------------------------

--
-- Table structure for table `login_attempts`
--

CREATE TABLE IF NOT EXISTS `login_attempts` (
  `email` varchar(255) NOT NULL,
  `invalid_attempts_count` int(3) NOT NULL,
  `lock_out_timestamp` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `attempt_timestamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  KEY `email` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login_attempts`
--


-- --------------------------------------------------------

--
-- Table structure for table `login_status`
--

CREATE TABLE IF NOT EXISTS `login_status` (
  `user_rel_key` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `login_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY `user_rel_key` (`user_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login_status`
--


-- --------------------------------------------------------

--
-- Table structure for table `mailing_address`
--

CREATE TABLE IF NOT EXISTS `mailing_address` (
  `address_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `address_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` text COLLATE utf8_unicode_ci NOT NULL,
  `city_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `state_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `zipcode` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `country_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `regn_key` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mail_key` bigint(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`address_id`),
  KEY `email` (`email`),
  KEY `regn_key` (`regn_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `mailing_address`
--


-- --------------------------------------------------------

--
-- Table structure for table `my_connection`
--

CREATE TABLE IF NOT EXISTS `my_connection` (
  `from_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'Name of the user who sent the connection request',
  `from_comp_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'Name of the user''s ( who sent the connection request) company',
  `from_user_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'Profile key of the user who is sent the connection request',
  `from_regn_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'Company key of the user who sent the connection request',
  `to_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'Name of the user who receive the connection request',
  `to_comp_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'Name of the user''s ( who receive the connection request) company',
  `to_user_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'Profile key of the user who is receive the connection request',
  `to_regn_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'Company key of the user who receive the connection request',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'Status of the connection',
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Connection created time stamp',
  KEY `from_user_key` (`from_user_key`),
  KEY `to_user_key` (`to_user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `my_connection`
--


-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE IF NOT EXISTS `notification` (
  `notification_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `receiver_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `receiver_regn_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `sender_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `sender_regn_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `notification_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `notification_type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `notification_message` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `status` int(2) default 1,
  PRIMARY KEY (`notification_id`),
  KEY `notification_id` (`notification_id`,`receiver_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `notification`
--


-- --------------------------------------------------------

--
-- Table structure for table `password_history`
--

CREATE TABLE IF NOT EXISTS `password_history` (
  `password_history_id` bigint(40) NOT NULL AUTO_INCREMENT,
  `regn_rel_key` varchar(255) NOT NULL,
  `user_rel_key` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `time_val` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`password_history_id`),
  KEY `user_rel_key` (`user_rel_key`),
  KEY `regn_rel_key` (`regn_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `password_history`
--


-- --------------------------------------------------------

--
-- Table structure for table `password_login_policies`
--

CREATE TABLE IF NOT EXISTS `password_login_policies` (
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


-- --------------------------------------------------------

--
-- Table structure for table `password_policies`
--

CREATE TABLE IF NOT EXISTS `password_policies` (
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


-- --------------------------------------------------------

--
-- Table structure for table `po`
--

CREATE TABLE IF NOT EXISTS `po` (
  `po_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trans_id` bigint(20) NOT NULL,
  `po_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
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
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `po_trans_id` varchar(100),
`outside_supplier_address` varchar(500),
  PRIMARY KEY (`po_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


--
-- Dumping data for table `po`
--


-- --------------------------------------------------------

--
-- Table structure for table `po_item`
--

CREATE TABLE IF NOT EXISTS `po_item` (
  `po_item_id` bigint(20) NOT NULL AUTO_INCREMENT,
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
   `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `barcode_id` varchar(100),
  PRIMARY KEY (`po_item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `po_item`
--


-- --------------------------------------------------------

--
-- Table structure for table `private_message`
--

CREATE TABLE IF NOT EXISTS `private_message` (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user_key` varchar(255) NOT NULL,
  `to_user_key` varchar(255) NOT NULL,
  `message` text NOT NULL,
  `from_delete_flag` tinyint(1) NOT NULL DEFAULT '0',
  `to_delete_flag` tinyint(1) NOT NULL DEFAULT '0',
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`message_id`),
  KEY `message_id` (`message_id`,`from_user_key`,`to_user_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `private_message`
--


-- --------------------------------------------------------

--
-- Table structure for table `product_catalog`
--

CREATE TABLE IF NOT EXISTS `product_catalog` (
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


-- --------------------------------------------------------

--
-- Table structure for table `quote`
--

CREATE TABLE IF NOT EXISTS `quote` (
  `quote_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trans_id` bigint(20) NOT NULL,
  `quote_ref` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
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
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`quote_trans_id` varchar(100),
`outside_supplier_address` varchar(500),

  PRIMARY KEY (`quote_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `quote`
--


-- --------------------------------------------------------

--
-- Table structure for table `quote_item`
--

CREATE TABLE IF NOT EXISTS `quote_item` (
  `quote_item_id` bigint(20) NOT NULL AUTO_INCREMENT,
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
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `barcode_id` varchar(100),
  PRIMARY KEY (`quote_item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `quote_item`
--


-- --------------------------------------------------------

--
-- Table structure for table `ratings_summary`
--

CREATE TABLE IF NOT EXISTS `ratings_summary` (
  `regn_rel_key` varchar(255) NOT NULL,
  `first_rating_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_rating_timestamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `avg_ratings` int(11) NOT NULL,
  `no_of_ratings` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ratings_summary`
--


-- --------------------------------------------------------

--
-- Table structure for table `regn_vendor_mapper`
--

CREATE TABLE IF NOT EXISTS `regn_vendor_mapper` (
  `regn_rel_key` varchar(255) NOT NULL COMMENT 'This refers to the primary company key regn_key in table company_registration.',
  `regn_rel_key_map` varchar(255) NOT NULL COMMENT 'This refers to the mapped company key in the table company_registration.',
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='regn_vendor_mapper table which maps the registered companies';

--
-- Dumping data for table `regn_vendor_mapper`
--


-- --------------------------------------------------------

--
-- Table structure for table `request_for_quote`
--

CREATE TABLE IF NOT EXISTS `request_for_quote` (
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


-- --------------------------------------------------------

--
-- Table structure for table `rfq`
--

CREATE TABLE IF NOT EXISTS `rfq` (
  `rfq_Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trans_Id` bigint(20) NOT NULL,
  `quote_ref` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
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
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `rfq_trans_id` varchar(100),
  `outside_supplier_address` varchar(500),
  PRIMARY KEY (`rfq_Id`),
  KEY `transId_` (`trans_Id`),
  KEY `from_` (`from_regn_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


--
-- Dumping data for table `rfq`
--


-- --------------------------------------------------------

--
-- Table structure for table `rfq_item`
--

CREATE TABLE IF NOT EXISTS `rfq_item` (
  `rfq_item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trans_Id` bigint(20) NOT NULL,
  `rfq_Id` bigint(20) NOT NULL,
  `item_desc` text NOT NULL,
  `part_no` varchar(255) NOT NULL,
  `quantity` double(10,4) NOT NULL,
  `ship_date` date NOT NULL,
  `quantity_unit` varchar(255) NOT NULL,
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `barcode_id` varchar(100),
  PRIMARY KEY (`rfq_item_id`),
  KEY `RFQ_Id` (`rfq_Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `rfq_item`
--


-- --------------------------------------------------------

--
-- Table structure for table `sm_config`
--

CREATE TABLE IF NOT EXISTS `sm_config` (
  `reg_link_expire_days` int(5) NOT NULL,
  `recycle_files_expire_days` int(3) NOT NULL,
  `tax_percentage` double(5,2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config`
--

INSERT INTO `sm_config` (`reg_link_expire_days`, `recycle_files_expire_days`, `tax_percentage`) VALUES
(7, 7, 10.00);

-- --------------------------------------------------------

--
-- Table structure for table `sm_config_account_policies`
--

CREATE TABLE IF NOT EXISTS `sm_config_account_policies` (
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

INSERT INTO `sm_config_account_policies` (`password_history_days`, `password_agemax_days`, `password_agemin_days`, `password_length`, `notification_remainder_nday`, `daily_remainder_flag`, `invalid_login_attempts`, `lockout_duration_min`, `reset_lockout_min`, `admin_unlock_flag`, `expire_session_min`, `password_complexity_flag`, `upper_lower_flag`, `numeric_flag`, `special_characters_flag`) VALUES
(5, 5000, 1, 7, 3, 1, 5, 10, 5, 1, 30, 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `sm_config_carrier`
--

CREATE TABLE IF NOT EXISTS `sm_config_carrier` (
  `carrier_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `carrier_name` varchar(255) NOT NULL,
  PRIMARY KEY (`carrier_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `sm_config_carrier`
--

INSERT INTO `sm_config_carrier` (`carrier_id`, `carrier_name`) VALUES
(1, 'Professional courier'),
(2, 'First Flight');

-- --------------------------------------------------------

--
-- Table structure for table `sm_config_company_email_domain`
--

CREATE TABLE IF NOT EXISTS `sm_config_company_email_domain` (
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email_domain` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  KEY `company_name` (`company_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_company_email_domain`
--

INSERT INTO `sm_config_company_email_domain` (`company_name`, `email_domain`) VALUES
('Royal Dutch Shell', 'shell.com'),
('BP', 'bp.com'),
('Sinopec Group', 'sinopecgroup.com'),
('China National Petroleum', 'cnpc.com.cn'),
('State Grid', 'sgcc.com.cn'),
('Toyota Motor', 'toyota-global.com'),
('Total', 'total.com'),
('Volkswagen', 'volkswagenag.com'),
('Japan Post Holdings', 'japanpost.jp'),
('Glencore International', 'glencore.com'),
('Gazprom', 'gazprom.com'),
('E.ON', 'eon.com'),
('ENI', 'eni.com'),
('ING Group', 'ing.com'),
('Samsung Electronics', 'samsung.com'),
('Daimler', 'daimler.com'),
('Petrobras', 'petrobras.com.br'),
('AXA', 'axa.com'),
('Allianz', 'allianz.com'),
('Nippon Telegraph & Telephone', 'ntt.co.jp'),
('BNP Paribas', 'bnpparibas.com'),
('GDF Suez', 'gdfsuez.com'),
('Pemex', 'pemex.com'),
('PDVSA', 'pdvsa.com'),
('Hitachi', 'hitachi.com'),
('Carrefour', 'carrefour.com'),
('Statoil ', 'statoil.com'),
('JX Holdings', 'hd.jx-group.co.jp'),
('Nissan Motor', 'nissan-global.com'),
('Hon Hai Precision Industry', 'foxconn.com'),
('Banco Santander', 'santander.com'),
('EXOR Group', 'exor.com'),
('Siemens', 'siemens.com'),
('Assicurazioni Generali', 'generali.com'),
('Lukoil', 'lukoil.com'),
('Enel', 'enel.com'),
('HSBC Holdings', 'hsbc.com'),
('Industrial & Commercial Bank of China', 'icbc.com.cn'),
('Tesco', 'tescoplc.com'),
('BASF', 'basf.com'),
('Honda Motor', 'honda.com'),
('SK Holdings ', 'sk.com'),
('Panasonic', 'panasonic.net'),
('Petronas', 'petronas.com.my'),
('BMW', 'bmwgroup.com'),
('ArcelorMittal', 'arcelormittal.com'),
('Metro', 'metrogroup.de'),
('', 'edf.fr'),
('Nippon Life Insurance', 'nissay.co.jp'),
('Munich Re Group', 'munichre.com'),
('China Construction Bank', 'ccb.com'),
('China Mobile Communications', 'chinamobileltd.com'),
('Telef', 'telefonica.com'),
('Indian Oil', 'iocl.com'),
('Agricultural Bank of China', 'abchina.com'),
('Peugeot', 'psa-peugeot-citroen.com'),
('Sony', 'sony.net'),
('Banco do Brasil', 'bb.com.br'),
('Deutsche Telekom', 'telekom.com'),
('Repsol YPF', 'repsol.com'),
('Noble Group', 'thisisnoble.com'),
('Bank of China', 'boc.cn'),
('PTT', 'pttplc.com'),
('Meiji Yasuda Life Insurance', 'meijiyasuda.co.jp'),
('Toshiba', 'toshiba.co.jp'),
('Deutsche Post', 'dp-dhl.com'),
('Reliance Industries', 'ril.com'),
('China State Construction Engineering', 'cscec.com'),
('China National Offshore Oil', 'cnooc.com.cn'),
('Groupe BPCE ', 'bpce.fr'),
('Deutsche Bank', 'deutsche-bank.de'),
('Vodafone Group', 'vodafone.com'),
('BHP Billiton', 'bhpbilliton.com'),
('Robert Bosch', 'bosch.com'),
('China Railway Construction ', 'crcc.cn'),
('China Railway Group', 'crec.cn'),
('Sinochem Group', 'sinochem.com'),
('Mitsubishi ', 'mitsubishicorp.com'),
('Hyundai Motor', 'worldwide.hyundai.com'),
('Barclays', 'barclays.com'),
('ThyssenKrupp', 'thyssenkrupp.com'),
('RWE', 'rwe.com'),
('EADS', 'eads.com'),
('Tokyo Electric Power', 'tepco.co.jp'),
('Landesbank Baden-W', 'lbbw.de'),
('China Life Insurance', 'chinalife.com.cn'),
('SAIC Motor', 'saicmotor.com'),
('Lloyds Banking Group', 'lloydsbankinggroup.com'),
('Mitsui', 'mitsui.co.jp'),
('AEON', 'aeon.info'),
('U.S. Postal Service', 'usps.com'),
('Banco Bradesco', 'bradesco.com.br'),
('Rosneft Oil', 'rosneft.com'),
('Unilever', 'unilever.com'),
('France T', 'orange.com'),
('Dongfeng Motor Group ', 'dfmc.com.cn'),
('Royal Bank of Scotland Group', 'rbs.com'),
('Mitsubishi UFJ Financial Group', 'mufg.jp'),
('Dai-ichi Life Insurance', 'dai-ichi-life.co.jp'),
('POSCO', 'posco.com'),
('Aviva', 'aviva.com'),
('Groupe Auchan', 'groupe-auchan.com'),
('Seven & I Holdings', '7andi.com'),
('China Southern Power Grid', 'csg.cn'),
('Rio Tinto Group', 'riotinto.com'),
('A.P. M', 'maersk.com'),
('Novartis', 'novartis.com'),
('Renault', 'renault.com'),
('Vale', 'vale.com'),
('Bunge', 'bunge.com'),
('Saint-Gobain', 'saint-gobain.com'),
('Prudential ', 'prudential.co.uk'),
('UniCredit Group ', 'unicreditgroup.eu'),
('China FAW Group', 'faw.com'),
('Fujitsu', 'fujitsu.com'),
('Marubeni', 'marubeni.com'),
('China Minmetals', 'minmetals.com'),
('Wesfarmers ', 'wesfarmers.com.au'),
('Itochu', 'itochu.co.jp'),
('Nokia', 'nokia.com'),
('Woolworths', 'woolworthslimited.com.au'),
('Am', 'americamovil.com'),
('Zurich Insurance Group', 'zurich.com'),
('Deutsche Bahn', 'deutschebahn.com'),
('Nippon Steel', 'nsc.co.jp'),
('Manulife Financial', 'manulife.com'),
('CNP Assurances', 'cnp.fr'),
('Vinci', 'vinci.com'),
('LyondellBasell Industries ', 'lyondellbasell.com'),
('Banco Bilbao Vizcaya Argentaria', 'bbva.com'),
('Bayer', 'bayer.com'),
('Sabic', 'sabic.com'),
('SSE', 'sse.com'),
('Sumitomo Mitsui Financial Group', 'smfg.co.jp'),
('Roche Group', 'roche.com'),
('Intesa Sanpaolo ', 'group.intesasanpaolo.com'),
('CITIC Group', 'citic.com'),
('LG Electronics', 'lg.com'),
('Baosteel Group', 'baosteel.com'),
('TNK-BP International', 'tnk-bp.com'),
('Idemitsu Kosan', 'idemitsu.com'),
('Fonci', 'fonciere-euris.fr'),
('Sanofi', 'sanofi.com'),
('Veolia Environnement', 'veolia.com'),
('Hyundai Heavy Industries', 'english.hhi.co.kr'),
('Credit Suisse Group', 'credit-suisse.com'),
('China North Industries Group ', 'norincogroup.com.cn'),
('Volvo', 'volvogroup.com'),
('MS&AD Insurance Group Holdings', 'ms-ad-hd.com'),
('OMV Group', 'omv.com'),
('Mitsubishi Electric', 'mitsubishielectric.com'),
('UBS', 'ubs.com'),
('China Communications Construction', 'ccccltd.cn'),
('Bouygues', 'bouygues.com'),
('SNCF', 'sncf.com'),
('KDDI', 'kddi.com'),
('China Telecommunications', 'chinatelecom.com.cn'),
('Ko', 'koc.com.tr'),
('Wilmar International', 'wilmar-international.com'),
('Canon', 'canon.com'),
('Bharat Petroleum', 'bharatpetroleum.in'),
('Commonwealth Bank of Australia', 'commbank.com.au'),
('Aegon', 'aegon.com'),
('Westpac Banking', 'westpac.com.au'),
('Iberdrola', 'iberdrola.es'),
('GlaxoSmithKline', 'gsk.com'),
('Safeway', 'safeway.com'),
('China Resources National', 'crc.com.hk'),
('Shenhua Group', 'shenhuagroup.com.cn'),
('GS Caltex', 'gscaltex.com'),
('Tokio Marine Holdings ', 'tokiomarinehd.com'),
('China South Industries Group', 'chinasouth.com.cn'),
('Sumitomo Life Insurance', 'sumitomolife.co.jp'),
('ACS', 'grupoacs.com'),
('Continental', 'continental-corporation.com'),
('Ping An Insurance ', 'pingan.com'),
('Royal Ahold', 'ahold.com');

-- --------------------------------------------------------

--
-- Table structure for table `sm_config_countries`
--

CREATE TABLE IF NOT EXISTS `sm_config_countries` (
  `country_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `country_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`country_id`),
  KEY `country_name` (`country_name`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=232 ;

--
-- Dumping data for table `sm_config_countries`
--

INSERT INTO `sm_config_countries` (`country_id`, `country_name`) VALUES
(1, 'Afghanistan'),
(2, 'Albania'),
(3, 'Algeria'),
(4, 'American Samoa'),
(5, 'Andorra'),
(6, 'Angola'),
(7, 'Anguilla'),
(8, 'Antarctica'),
(9, 'Antigua and Barbuda'),
(10, 'Argentina'),
(11, 'Armenia'),
(12, 'Aruba'),
(13, 'Ascension'),
(14, 'Australia'),
(15, 'Austria'),
(16, 'Azerbaijan'),
(17, 'Bahamas'),
(18, 'Bahrain'),
(19, 'Bangladesh'),
(20, 'Barbados'),
(21, 'Belarus'),
(22, 'Belgium'),
(23, 'Belize'),
(24, 'Benin'),
(25, 'Bermuda'),
(26, 'Bhutan'),
(27, 'Bolivia'),
(28, 'Bosnia and Herzegovina'),
(29, 'Botswana'),
(30, 'Brazil'),
(31, 'Brunei'),
(32, 'Bulgaria'),
(33, 'Burkina Faso'),
(34, 'Burundi'),
(35, 'Cambodia'),
(36, 'Cameroon'),
(37, 'Canada'),
(38, 'Cape Verde'),
(39, 'Cayman Islands'),
(40, 'Central African Republic'),
(41, 'Chad'),
(42, 'Chile'),
(43, 'China'),
(44, 'Christmas Island'),
(45, 'Cocos (Keeling) Islands'),
(46, 'Colombia'),
(47, 'Comoros'),
(48, 'Congo'),
(49, 'Cook Islands'),
(50, 'Costa Rica'),
(51, 'C'),
(52, 'Croatia'),
(53, 'Cuba'),
(54, 'Cyprus'),
(55, 'Czech Republic'),
(56, 'Denmark'),
(57, 'Diego Garcia'),
(58, 'Djibouti'),
(59, 'Dominica'),
(60, 'Dominican Republic'),
(61, 'East Timor'),
(62, 'Ecuador'),
(63, 'Egypt'),
(64, 'El Salvador'),
(65, 'Equatorial Guinea'),
(66, 'Eritrea'),
(67, 'Estonia'),
(68, 'Ethiopia'),
(69, 'Faeroe Islands'),
(70, 'Falkland Islands'),
(71, 'Fiji'),
(72, 'Finland'),
(73, 'France'),
(74, 'French Guiana'),
(75, 'French Polynesia'),
(76, 'Gabon'),
(77, 'Gambia'),
(78, 'Georgia'),
(79, 'Germany'),
(80, 'Ghana'),
(81, 'Gibraltar'),
(82, 'Greece'),
(83, 'Greenland'),
(84, 'Grenada'),
(85, 'Guadeloupe'),
(86, 'Guam'),
(87, 'Guatemala'),
(88, 'Guinea'),
(89, 'Guinea-Bissau'),
(90, 'Guyana'),
(91, 'Haiti'),
(92, 'Honduras'),
(93, 'Hong Kong'),
(94, 'Hungary'),
(95, 'Iceland'),
(96, 'India'),
(97, 'Indonesia'),
(98, 'Iran'),
(99, 'Iraq'),
(100, 'Ireland'),
(101, 'Israel'),
(102, 'Italy'),
(103, 'Jamaica'),
(104, 'Japan'),
(105, 'Jordan'),
(106, 'Kazakhstan'),
(107, 'Kenya'),
(108, 'Kiribati'),
(109, 'Korea (North)'),
(110, 'Korea (South)'),
(111, 'Kuwait'),
(112, 'Kyrgyzstan'),
(113, 'Laos'),
(114, 'Latvia'),
(115, 'Lebanon'),
(116, 'Lesotho'),
(117, 'Liberia'),
(118, 'Libya'),
(119, 'Liechtenstein'),
(120, 'Lithuania'),
(121, 'Luxembourg '),
(122, 'Macau'),
(123, 'Macedonia'),
(124, 'Madagascar'),
(125, 'Malawi'),
(126, 'Malaysia'),
(127, 'Maldives'),
(128, 'Mali'),
(129, 'Malta'),
(130, 'Marshall Islands'),
(131, 'Martinique'),
(132, 'Mauritania'),
(133, 'Mauritius'),
(134, 'Mayotte'),
(135, 'Mexico'),
(136, 'Micronesia'),
(137, 'Moldova'),
(138, 'Monaco'),
(139, 'Mongolia'),
(140, 'Montserrat'),
(141, 'Morocco'),
(142, 'Mozambique'),
(143, 'Myanmar'),
(144, 'Namibia'),
(145, 'Nauru'),
(146, 'Nepal'),
(147, 'Netherlands'),
(148, 'Netherlands Antilles'),
(149, 'New Caledonia'),
(150, 'New Zealand'),
(151, 'Nicaragua'),
(152, 'Niger'),
(153, 'Nigeria'),
(154, 'Niue'),
(155, 'Norfolk Island'),
(156, 'Northern Marianas'),
(157, 'Norway'),
(158, 'Oman'),
(159, 'Pakistan'),
(160, 'Palau'),
(161, 'Palestinian Settlements'),
(162, 'Panama'),
(163, 'Papua New Guinea'),
(164, 'Paraguay'),
(165, 'Peru'),
(166, 'Philippines'),
(167, 'Poland'),
(168, 'Portugal'),
(169, 'Puerto Rico'),
(170, 'Qatar'),
(171, 'R'),
(172, 'Romania'),
(173, 'Russia'),
(174, 'Rwanda'),
(175, 'Saint Helena'),
(176, 'Saint Kitts and Nevis'),
(177, 'Saint Lucia'),
(178, 'Saint Pierre and Miquelon'),
(179, 'Saint Vincent and Grenadines'),
(180, 'Samoa'),
(181, 'San Marino'),
(182, 'S'),
(183, 'Saudi Arabia'),
(184, 'Senegal'),
(185, 'Serbia'),
(186, 'Seychelles'),
(187, 'Sierra Leone'),
(188, 'Singapore'),
(189, 'Slovakia'),
(190, 'Slovenia'),
(191, 'Solomon Islands'),
(192, 'Somalia'),
(193, 'South Africa'),
(194, 'Spain'),
(195, 'Sri Lanka'),
(196, 'Sudan'),
(197, 'Suriname'),
(198, 'Swaziland'),
(199, 'Sweden'),
(200, 'Switzerland'),
(201, 'Syria'),
(202, 'Taiwan'),
(203, 'Tajikistan'),
(204, 'Tanzania'),
(205, 'Thailand'),
(206, 'Togo'),
(207, 'Tokelau'),
(208, 'Tonga'),
(209, 'Trinidad and Tobago'),
(210, 'Tunisia'),
(211, 'Turkey'),
(212, 'Turkmenistan'),
(213, 'Turks and Caicos Islands'),
(214, 'Tuvalu'),
(215, 'Uganda'),
(216, 'Ukraine'),
(217, 'United Arab Emirates'),
(218, 'United Kingdom'),
(219, 'United States'),
(220, 'Uruguay'),
(221, 'US Virgin Islands'),
(222, 'Uzbekistan'),
(223, 'Vanuatu'),
(224, 'Venezuela'),
(225, 'Vietnam'),
(226, 'Virgin Islands'),
(227, 'Wake Island'),
(228, 'Wallis and Futuna'),
(229, 'Yemen'),
(230, 'Zambia'),
(231, 'Zimbabwe');

-- --------------------------------------------------------

--
-- Table structure for table `sm_config_country_code`
--

CREATE TABLE IF NOT EXISTS `sm_config_country_code` (
  `country_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `country_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  KEY `country_name` (`country_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_country_code`
--

INSERT INTO `sm_config_country_code` (`country_name`, `country_code`) VALUES
('Afghanistan', '93'),
('Albania', '355'),
('Algeria', '213'),
('American Samoa', '1684'),
('Andorra', '376'),
('Angola', '244'),
('Anguilla', '1264'),
('Antarctica', '6721'),
('Antigua and Barbuda', '1268'),
('Argentina', '54'),
('Armenia', '374'),
('Aruba', '297'),
('Ascension', '247'),
('Australia', '61'),
('Austria', '43'),
('Azerbaijan', '994'),
('Bahamas', '1242'),
('Bahrain', '973'),
('Bangladesh', '880'),
('Barbados', '1246'),
('Belarus', '375'),
('Belgium', '32'),
('Belize', '501'),
('Benin', '229'),
('Bermuda', '1441'),
('Bhutan', '975'),
('Bolivia', '591'),
('Bosnia and Herzegovina', '387'),
('Botswana', '267'),
('Brazil', '55'),
('Brunei', '673'),
('Bulgaria', '359'),
('Burkina Faso', '226'),
('Burundi', '257'),
('Cambodia', '855'),
('Cameroon', '237'),
('Canada', '1'),
('Cape Verde', '238'),
('Cayman Islands', '1345'),
('Central African Republic', '236'),
('Chad', '235'),
('Chile', '56'),
('China', '86'),
('Christmas Island', '618'),
('Cocos (Keeling) Islands', '618'),
('Colombia', '57'),
('Comoros', '269'),
('Congo', '243'),
('Cook Islands', '682'),
('Costa Rica', '506'),
('C', '225'),
('Croatia', '385'),
('Cuba', '53'),
('Cyprus', '357'),
('Czech Republic', '420'),
('Denmark', '45'),
('Diego Garcia', '246'),
('Djibouti', '253'),
('Dominica', '1767'),
('Dominican Republic', '1809'),
('East Timor', '670'),
('Ecuador', '593'),
('Egypt', '20'),
('El Salvador', '503'),
('Equatorial Guinea', '240'),
('Eritrea', '291'),
('Estonia', '372'),
('Ethiopia', '251'),
('Faeroe Islands', '500'),
('Falkland Islands', '298'),
('Fiji', '679'),
('Finland', '358'),
('France', '33'),
('French Guiana', '594'),
('French Polynesia', '689'),
('Gabon', '241'),
('Gambia', '220'),
('Georgia', '995'),
('Germany', '49'),
('Ghana', '233'),
('Gibraltar', '350'),
('Greece', '30'),
('Greenland', '299'),
('Grenada', '1473'),
('Guadeloupe', '590'),
('Guam', '1671'),
('Guatemala', '502'),
('Guinea', '224'),
('Guinea-Bissau', '245'),
('Guyana', '592'),
('Haiti', '509'),
('Honduras', '504'),
('Hong Kong', '852'),
('Hungary', '36'),
('Iceland', '354'),
('India', '91'),
('Indonesia', '62'),
('Iran', '98'),
('Iraq', '964'),
('Ireland', '353'),
('Israel', '972'),
('Italy', '39'),
('Jamaica', '1876'),
('Japan', '81'),
('Jordan', '962'),
('Kazakhstan', '77'),
('Kenya', '254'),
('Kiribati', '686'),
('Korea (North)', '850'),
('Korea (South)', '82'),
('Kuwait', '965'),
('Kyrgyzstan', '996'),
('Laos', '856'),
('Latvia', '371'),
('Lebanon', '961'),
('Lesotho', '266'),
('Liberia', '231'),
('Libya', '218'),
('Liechtenstein', '423'),
('Lithuania', '370'),
('Luxembourg ', '352'),
('Macau', '853'),
('Macedonia', '389'),
('Madagascar', '261'),
('Malawi', '265'),
('Malaysia', '60'),
('Maldives', '960'),
('Mali', '223'),
('Malta', '356'),
('Marshall Islands', '692'),
('Martinique', '596'),
('Mauritania', '222'),
('Mauritius', '230'),
('Mayotte', '52'),
('Mexico', '691'),
('Micronesia', '373'),
('Moldova', '377'),
('Monaco', '976'),
('Mongolia', '382'),
('Montserrat', '1664'),
('Morocco', '212'),
('Mozambique', '258'),
('Myanmar', '95'),
('Namibia', '264'),
('Nauru', '674'),
('Nepal', '977'),
('Netherlands', '31'),
('Netherlands Antilles', '599'),
('New Caledonia', '687'),
('New Zealand', '64'),
('Nicaragua', '505'),
('Niger', '227'),
('Nigeria', '234'),
('Niue', '683'),
('Norfolk Island', '6723'),
('Northern Marianas', '1670'),
('Norway', '47'),
('Oman', '968'),
('Pakistan', '92'),
('Palau', '680'),
('Palestinian Settlements', '970'),
('Panama', '507'),
('Papua New Guinea', '675'),
('Paraguay', '595'),
('Peru', '51'),
('Philippines', '63'),
('Poland', '48'),
('Portugal', '351'),
('Puerto Rico', '1787'),
('Qatar', '974'),
('R', '262'),
('Romania', '40'),
('Russia', '7'),
('Rwanda', '250'),
('Saint Helena', '290'),
('Saint Kitts and Nevis', '1869'),
('Saint Lucia', '1758'),
('Saint Pierre and Miquelon', '508'),
('Saint Vincent and Grenadines', '1784'),
('Samoa', '685'),
('San Marino', '378'),
('S', '239'),
('Saudi Arabia', '966'),
('Senegal', '221'),
('Serbia', '381'),
('Seychelles', '248'),
('Sierra Leone', '232'),
('Singapore', '65'),
('Slovakia', '421'),
('Slovenia', '386'),
('Solomon Islands', '677'),
('Somalia', '252'),
('South Africa', '27'),
('Spain', '34'),
('Sri Lanka', '94'),
('Sudan', '249'),
('Suriname', '597'),
('Swaziland', '268'),
('Sweden', '46'),
('Switzerland', '41'),
('Syria', '963'),
('Taiwan', '886'),
('Tajikistan', '992'),
('Tanzania', '255'),
('Thailand', '66'),
('Togo', '228'),
('Tokelau', '690'),
('Tonga', '676'),
('Trinidad and Tobago', '1868'),
('Tunisia', '216'),
('Turkey', '90'),
('Turkmenistan', '993'),
('Turks and Caicos Islands', '1649'),
('Tuvalu', '688'),
('Uganda', '256'),
('Ukraine', '380'),
('United Arab Emirates', '971'),
('United Kingdom', '44'),
('United States', '1'),
('Uruguay', '598'),
('US Virgin Islands', '1340'),
('Uzbekistan', '998'),
('Vanuatu', '678'),
('Venezuela', '58'),
('Vietnam', '84'),
('Virgin Islands', '1284'),
('Wake Island', '808'),
('Wallis and Futuna', '681'),
('Yemen', '967'),
('Zambia', '260'),
('Zimbabwe', '263');

-- --------------------------------------------------------

--
-- Table structure for table `sm_config_currencies`
--

CREATE TABLE IF NOT EXISTS `sm_config_currencies` (
  `currency_key` varchar(255) NOT NULL COMMENT 'Currency Key. Data limit is 255 max.',
  `currency_name` varchar(255) NOT NULL COMMENT 'Currency name. Data limit is 255 max.',
  PRIMARY KEY (`currency_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Currency Master table that has USD, INR, etc.,';

--
-- Dumping data for table `sm_config_currencies`
--

INSERT INTO `sm_config_currencies` (`currency_key`, `currency_name`) VALUES
('USD', 'US Dollar'),
('AED', 'United Arab Emirates dirham'),
('AFN', 'Afghani'),
('ALL', 'Lek'),
('AMD', 'Armenian Dram'),
('ANG', 'Netherlands Antillian Guilder'),
('AOA', 'Kwanza'),
('ARS', 'Argentine Peso'),
('AUD', 'Australian Dollar'),
('AWG', 'Aruban Guilder'),
('AZN', 'Azerbaijanian Manat'),
('BAM', 'Convertible Marks'),
('BBD', 'Barbados Dollar'),
('BDT', 'Bangladeshi Taka'),
('BGN', 'Bulgarian Lev'),
('BHD', 'Bahraini Dinar'),
('BIF', 'Burundian Franc'),
('BMD', 'Bermudian Dollar (customarily known as Bermuda Dollar)'),
('BND', 'Brunei Dollar'),
('BOB', 'Boliviano'),
('BOV', 'Bolivian Mvdol (Funds code)'),
('BRL', 'Brazilian Real'),
('BSD', 'Bahamian Dollar'),
('BTN', 'Ngultrum'),
('BWP', 'Pula'),
('BYR', 'Belarussian Ruble'),
('BZD', 'Belize Dollar'),
('CAD', 'Canadian Dollar'),
('CDF', 'Franc Congolais'),
('CHE', 'WIR Euro (complementary currency)'),
('CHF', 'Swiss Franc'),
('CHW', 'WIR Franc (complementary currency)'),
('CLF', 'Unidades de formento (Funds code)'),
('CLP', 'Chilean Peso'),
('CNY', 'Yuan Renminbi'),
('COP', 'Colombian Peso'),
('COU', 'Unidad de Valor Real'),
('CRC', 'Costa Rican Colon'),
('CUP', 'Cuban Peso'),
('CVE', 'Cape Verde Escudo'),
('CYP', 'Cyprus Pound'),
('CZK', 'Czech Koruna'),
('DJF', 'Djibouti Franc'),
('DKK', 'Danish Krone'),
('DOP', 'Dominican Peso'),
('DZD', 'Algerian Dinar'),
('EEK', 'Kroon'),
('EGP', 'Egyptian Pound'),
('ERN', 'Nakfa'),
('ETB', 'Ethiopian Birr'),
('EUR', 'Euro'),
('FJD', 'Fiji Dollar'),
('FKP', 'Falkland Islands Pound'),
('GBP', 'Pound Sterling'),
('GEL', 'Lari'),
('GHS', 'Cedi'),
('GIP', 'Gibraltar pound'),
('GMD', 'Dalasi'),
('GNF', 'Guinea Franc'),
('GTQ', 'Quetzal'),
('GYD', 'Guyana Dollar'),
('HKD', 'Hong Kong Dollar'),
('HNL', 'Lempira'),
('HRK', 'Croatian Kuna'),
('HTG', 'Haiti Gourde'),
('HUF', 'Forint'),
('IDR', 'Rupiah'),
('ILS', 'New Israeli Shekel'),
('INR', 'Indian Rupee'),
('IQD', 'Iraqi Dinar'),
('IRR', 'Iranian Rial'),
('ISK', 'Iceland Krona'),
('JMD', 'Jamaican Dollar'),
('JOD', 'Jordanian Dinar'),
('JPY', 'Japanese yen'),
('KES', 'Kenyan Shilling'),
('KGS', 'Som'),
('KHR', 'Riel'),
('KMF', 'Comoro Franc'),
('KPW', 'North Korean Won'),
('KRW', 'South Korean Won'),
('KWD', 'Kuwaiti Dinar'),
('KYD', 'Cayman Islands Dollar'),
('KZT', 'Tenge'),
('LAK', 'Kip'),
('LBP', 'Lebanese Pound'),
('LKR', 'Sri Lanka Rupee'),
('LRD', 'Liberian Dollar'),
('LSL', 'Loti'),
('LTL', 'Lithuanian Litas'),
('LVL', 'Latvian Lats'),
('LYD', 'Libyan Dinar'),
('MAD', 'Moroccan Dirham'),
('MDL', 'Moldovan Leu'),
('MGA', 'Malagasy Ariary'),
('MKD', 'Denar'),
('MMK', 'Kyat'),
('MNT', 'Tugrik'),
('MOP', 'Pataca'),
('MRO', 'Ouguiya'),
('MTL', 'Maltese Lira'),
('MUR', 'Mauritius Rupee'),
('MVR', 'Rufiyaa'),
('MWK', 'Kwacha'),
('MXN', 'Mexican Peso'),
('MXV', 'Mexican Unidad de Inversion (UDI) (Funds code)'),
('MYR', 'Malaysian Ringgit'),
('MZN', 'Metical'),
('NAD', 'Namibian Dollar'),
('NGN', 'Naira'),
('NIO', 'Cordoba Oro'),
('NOK', 'Norwegian Krone'),
('NPR', 'Nepalese Rupee'),
('NZD', 'New Zealand Dollar'),
('OMR', 'Rial Omani'),
('PAB', 'Balboa'),
('PEN', 'Nuevo Sol'),
('PGK', 'Kina'),
('PHP', 'Philippine Peso'),
('PKR', 'Pakistan Rupee'),
('PLN', 'Zloty'),
('PYG', 'Guarani'),
('QAR', 'Qatari Rial'),
('RON', 'Romanian New Leu'),
('RSD', 'Serbian Dinar'),
('RUB', 'Russian Ruble'),
('RWF', 'Rwanda Franc'),
('SAR', 'Saudi Riyal'),
('SBD', 'Solomon Islands Dollar'),
('SCR', 'Seychelles Rupee'),
('SDG', 'Sudanese Pound'),
('SEK', 'Swedish Krona'),
('SGD', 'Singapore Dollar'),
('SHP', 'Saint Helena Pound'),
('SKK', 'Slovak Koruna'),
('SLL', 'Leone'),
('SOS', 'Somali Shilling'),
('SRD', 'Surinam Dollar'),
('STD', 'Dobra'),
('SYP', 'Syrian Pound'),
('SZL', 'Lilangeni'),
('THB', 'Baht'),
('TJS', 'Somoni'),
('TMM', 'Manat'),
('TND', 'Tunisian Dinar'),
('TOP', 'Pa''anga'),
('TRY', 'New Turkish Lira'),
('TTD', 'Trinidad and Tobago Dollar'),
('TWD', 'New Taiwan Dollar'),
('TZS', 'Tanzanian Shilling'),
('UAH', 'Hryvnia'),
('UGX', 'Uganda Shilling'),
('USN', ''),
('USS', ''),
('UYU', 'Peso Uruguayo'),
('UZS', 'Uzbekistan Som'),
('VEB', 'Venezuelan bol'),
('VND', 'Vietnamese ??ng'),
('VUV', 'Vatu'),
('WST', 'Samoan Tala'),
('XAF', 'CFA Franc BEAC'),
('XAG', 'Silver (one Troy ounce)'),
('XAU', 'Gold (one Troy ounce)'),
('XBA', 'European Composite Unit (EURCO) (Bonds market unit)'),
('XBB', 'European Monetary Unit (E.M.U.-6) (Bonds market unit)'),
('XBC', 'European Unit of Account 9 (E.U.A.-9) (Bonds market unit)'),
('XBD', 'European Unit of Account 17 (E.U.A.-17) (Bonds market unit)'),
('XCD', 'East Caribbean Dollar'),
('XDR', 'Special Drawing Rights'),
('XFO', 'Gold franc (special settlement currency)'),
('XFU', 'UIC franc (special settlement currency)'),
('XOF', 'CFA Franc BCEAO'),
('XPD', 'Palladium (one Troy ounce)'),
('XPF', 'CFP franc'),
('XPT', 'Platinum (one Troy ounce)'),
('XTS', 'Code reserved for testing purposes'),
('XXX', 'No currency'),
('YER', 'Yemeni Rial'),
('ZAR', 'South African Rand'),
('ZMK', 'Kwacha'),
('ZWD', 'Zimbabwe Dollar');

-- --------------------------------------------------------

--
-- Table structure for table `sm_config_naics`
--

CREATE TABLE IF NOT EXISTS `sm_config_naics` (
  `naics_code` varchar(255) NOT NULL,
  `naics_description` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_naics`
--

INSERT INTO `sm_config_naics` (`naics_code`, `naics_description`) VALUES
('111', 'Crop Production '),
('112', 'Animal Production and Aquaculture '),
('113', 'Forestry and Logging '),
('114', 'Fishing, Hunting and Trapping'),
('115', 'Support Activities for Agriculture and Forestry'),
('211', 'Oil and Gas Extraction'),
('212', 'Mining (except Oil and Gas)'),
('213', 'Support Activities for Mining'),
('221', 'Utilities '),
('236', 'Construction of Buildings'),
('237', 'Heavy and Civil Engineering Construction'),
('238', 'Specialty Trade Contractors'),
('311', 'Food Manufacturing'),
('312', 'Beverage and Tobacco Product Manufacturing'),
('313', 'Textile Mills'),
('314', 'Textile Product Mills'),
('315', 'Apparel Manufacturing'),
('316', 'Leather and Allied Product Manufacturing'),
('321', 'Wood Product Manufacturing'),
('322', 'Paper Manufacturing'),
('323', 'Printing and Related Support Activities'),
('324', 'Petroleum and Coal Products Manufacturing'),
('325', 'Chemical Manufacturing'),
('326', 'Plastics and Rubber Products Manufacturing'),
('327', 'Nonmetallic Mineral Product Manufacturing'),
('331', 'Primary Metal Manufacturing'),
('332', 'Fabricated Metal Product Manufacturing'),
('333', 'Machinery Manufacturing'),
('334', 'Computer and Electronic Product Manufacturing'),
('335', 'Electrical Equipment, Appliance, and Component Manufacturing'),
('336', 'Transportation Equipment Manufacturing'),
('337', 'Furniture and Related Product Manufacturing'),
('339', 'Miscellaneous Manufacturing'),
('423', 'Merchant Wholesalers, Durable Goods '),
('424', 'Merchant Wholesalers, Nondurable Goods '),
('425', 'Wholesale Electronic Markets and Agents and Brokers '),
('441', 'Motor Vehicle and Parts Dealers '),
('442', 'Furniture and Home Furnishings Stores '),
('443', 'Electronics and Appliance Stores '),
('444', 'Building Material and Garden Equipment and Supplies Dealers '),
('445', 'Food and Beverage Stores '),
('446', 'Health and Personal Care Stores '),
('447', 'Gasoline Stations '),
('448', 'Clothing and Clothing Accessories Stores '),
('451', 'Sporting Goods, Hobby, Musical Instrument, and Book Stores '),
('452', 'General Merchandise Stores '),
('453', 'Miscellaneous Store Retailers '),
('454', 'Nonstore Retailers '),
('481', 'Air Transportation'),
('482', 'Rail Transportation'),
('483', 'Water Transportation'),
('484', 'Truck Transportation'),
('485', 'Transit and Ground Passenger Transportation'),
('486', 'Pipeline Transportation'),
('487', 'Scenic and Sightseeing Transportation'),
('488', 'Support Activities for Transportation'),
('491', 'Postal Service'),
('492', 'Couriers and Messengers'),
('493', 'Warehousing and Storage'),
('511', 'Publishing Industries (except Internet)'),
('512', 'Motion Picture and Sound Recording Industries'),
('515', 'Broadcasting (except Internet)'),
('517', 'Telecommunications'),
('518', 'Data Processing, Hosting, and Related Services'),
('519', 'Other Information Services'),
('521', 'Monetary Authorities-Central Bank'),
('522', 'Credit Intermediation and Related Activities'),
('523', 'Securities, Commodity Contracts, and Other Financial Investments and Related Activities'),
('524', 'Insurance Carriers and Related Activities'),
('525', 'Funds, Trusts, and Other Financial Vehicles '),
('531', 'Real Estate'),
('532', 'Rental and Leasing Services'),
('533', 'Lessors of Nonfinancial Intangible Assets (except Copyrighted Works)'),
('541', 'Professional, Scientific, and Technical Services'),
('551', 'Management of Companies and Enterprises'),
('561', 'Administrative and Support Services'),
('562', 'Waste Management and Remediation Services'),
('611', 'Educational Services'),
('621', 'Ambulatory Health Care Services'),
('622', 'Hospitals'),
('623', 'Nursing and Residential Care Facilities'),
('624', 'Social Assistance'),
('711', 'Performing Arts, Spectator Sports, and Related Industries'),
('712', 'Museums, Historical Sites, and Similar Institutions'),
('713', 'Amusement, Gambling, and Recreation Industries'),
('721', 'Accommodation'),
('722', 'Food Services and Drinking Places'),
('811', 'Repair and Maintenance'),
('812', 'Personal and Laundry Services'),
('813', 'Religious, Grantmaking, Civic, Professional, and Similar Organizations'),
('814', 'Private Households'),
('921', 'Executive, Legislative, and Other General Government Support '),
('922', 'Justice, Public Order, and Safety Activities '),
('923', 'Administration of Human Resource Programs '),
('924', 'Administration of Environmental Quality Programs '),
('925', 'Administration of Housing Programs, Urban Planning, and Community Development '),
('926', 'Administration of Economic Programs '),
('927', 'Space Research and Technology '),
('928', 'National Security and International Affairs ');

-- --------------------------------------------------------

--
-- Table structure for table `sm_config_pricing_option`
--

CREATE TABLE IF NOT EXISTS `sm_config_pricing_option` (
  `pricing_option_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `plan` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `max_employee` bigint(15) NOT NULL,
  `max_transaction` bigint(20) NOT NULL,
  `disk_quota` bigint(20) NOT NULL COMMENT 'The disk_quota is stored in MB',
  KEY `pricing_option_key` (`pricing_option_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_pricing_option`
--

INSERT INTO `sm_config_pricing_option` (`pricing_option_key`, `plan`, `max_employee`, `max_transaction`, `disk_quota`) VALUES
('Basic', 'Basic', 25, 250000, 250),
('Plus', 'Plus', 100, 1000000, 500),
('Premium', 'Premium', 0, 0, 750);

-- --------------------------------------------------------

--
-- Table structure for table `sm_config_privileges`
--

CREATE TABLE IF NOT EXISTS `sm_config_privileges` (
  `user_privileges` bigint(20) NOT NULL,
  `group_privileges` bigint(20) NOT NULL,
  `dept_privileges` int(32) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_privileges`
--

INSERT INTO `sm_config_privileges` (`user_privileges`, `group_privileges`, `dept_privileges`) VALUES
(0, 0, 101010);

-- --------------------------------------------------------

--
-- Table structure for table `sm_config_quantity_type`
--

CREATE TABLE IF NOT EXISTS `sm_config_quantity_type` (
  `quan_type_key` varchar(255) NOT NULL COMMENT 'Quantity Type key to identify quantity type. Max limit is 255.',
  `quan_type` varchar(255) NOT NULL COMMENT 'Quantity type. Max limit is 255.',
  PRIMARY KEY (`quan_type_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Quantity Type Master table with master entires such as KG, u';

--
-- Dumping data for table `sm_config_quantity_type`
--

INSERT INTO `sm_config_quantity_type` (`quan_type_key`, `quan_type`) VALUES
('BAG', 'BAGS'),
('BAL', 'BALE'),
('BDL', 'BUNDLES'),
('BKL', 'BUCKLES'),
('BOU', 'BILLIONS OF UNITS'),
('BOX', 'BOX'),
('BTL', 'BOTTLES'),
('BUN', 'BUNCHES'),
('CAN', 'CANS'),
('CAS', 'CASE'),
('CBM', 'CUBIC METER'),
('CCM', 'CUBIC CENTIMETER'),
('CMS', 'CENTIMETER'),
('CTN', 'CARTONS'),
('DOZ', 'DOZEN'),
('DRM', 'DRUM'),
('FTS', 'FEET'),
('GGR', 'GREAT GROSS'),
('GMS', 'GRAMS'),
('GRS', 'GROSS'),
('GYD', 'GROSS YARDS'),
('KGA', 'KILOGRAM ACTIVITY'),
('KGS', 'KILOGRAMS'),
('KIT', 'KITS'),
('KLR', 'KILOLITER'),
('KME', 'KILOMETERS'),
('KWH', 'KILOWATTHOUR'),
('LBS', 'POUNDS'),
('LTR', 'LITERS'),
('MLT', 'MILLILITER'),
('MTR', 'METER'),
('MTS', 'METRIC TON'),
('NOS', 'NUMBER'),
('PAC', 'PACKS'),
('PCS', 'PIECES'),
('PRS', 'PAIRS'),
('QTL', 'QUINTAL'),
('RLS', 'ROLLS'),
('ROL', 'ROLLS'),
('SET', 'SETS'),
('SQF', 'SQUARE FEET'),
('SQM', 'SQUARE METER'),
('SQY', 'SQUARE YARDS');

-- --------------------------------------------------------

--
-- Table structure for table `sm_config_states`
--

CREATE TABLE IF NOT EXISTS `sm_config_states` (
  `state_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `state_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `country_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`state_id`),
  KEY `state_name` (`state_name`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=51 ;

--
-- Dumping data for table `sm_config_states`
--

INSERT INTO `sm_config_states` (`state_id`, `state_name`, `country_name`) VALUES
(1, 'Alabama', 'United States'),
(2, 'Alaska', 'United States'),
(3, 'Arizona', 'United States'),
(4, 'Arkansas', 'United States'),
(5, 'California', 'United States'),
(6, 'Colorado', 'United States'),
(7, 'Connecticut', 'United States'),
(8, 'Delaware', 'United States'),
(9, 'Florida', 'United States'),
(10, 'Georgia', 'United States'),
(11, 'Hawaii', 'United States'),
(12, 'Idaho', 'United States'),
(13, 'Illinois', 'United States'),
(14, 'Indiana', 'United States'),
(15, 'Iowa', 'United States'),
(16, 'Kansas', 'United States'),
(17, 'Kentucky', 'United States'),
(18, 'Louisiana', 'United States'),
(19, 'Maine', 'United States'),
(20, 'Maryland', 'United States'),
(21, 'Massachusetts', 'United States'),
(22, 'Michigan', 'United States'),
(23, 'Minnesota', 'United States'),
(24, 'Mississippi', 'United States'),
(25, 'Missouri', 'United States'),
(26, 'Montana', 'United States'),
(27, 'Nebraska', 'United States'),
(28, 'Nevada', 'United States'),
(29, 'New Hampshire', 'United States'),
(30, 'New Jersey', 'United States'),
(31, 'New Mexico', 'United States'),
(32, 'New York', 'United States'),
(33, 'North Carolina', 'United States'),
(34, 'North Dakota', 'United States'),
(35, 'Ohio', 'United States'),
(36, 'Oklahoma', 'United States'),
(37, 'Oregon', 'United States'),
(38, 'Pennsylvania', 'United States'),
(39, 'Rhode Island', 'United States'),
(40, 'South Carolina', 'United States'),
(41, 'South Dakota', 'United States'),
(42, 'Tennessee', 'United States'),
(43, 'Texas', 'United States'),
(44, 'Utah', 'United States'),
(45, 'Vermont', 'United States'),
(46, 'Virginia', 'United States'),
(47, 'Washington', 'United States'),
(48, 'West Virginia', 'United States'),
(49, 'Wisconsin', 'United States'),
(50, 'Wyoming', 'United States');

-- --------------------------------------------------------

--
-- Table structure for table `sm_config_trans_reject_reasons`
--

CREATE TABLE IF NOT EXISTS `sm_config_trans_reject_reasons` (
  `reject_resons_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reject_reason` varchar(1024) NOT NULL,
  PRIMARY KEY (`reject_resons_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `sm_config_trans_reject_reasons`
--

INSERT INTO `sm_config_trans_reject_reasons` (`reject_resons_id`, `reject_reason`) VALUES
(1, 'Purchase Order number consists of invalid values'),
(2, 'Amount invoiced as zero'),
(3, 'Invoice amount does not total detail amount'),
(4, 'Invoice number not provided'),
(5, 'Date shipped not a valid date'),
(6, 'Duplicate vendor/invoice from vendor'),
(7, 'Invoice has been set to pay'),
(8, 'Invoice previously paid');

-- --------------------------------------------------------

--
-- Table structure for table `sm_config_user_account`
--

CREATE TABLE IF NOT EXISTS `sm_config_user_account` (
  `change_password_flag` tinyint(1) NOT NULL,
  `disable_account_flag` tinyint(1) NOT NULL,
  `account_expiration_days` int(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_user_account`
--

INSERT INTO `sm_config_user_account` (`change_password_flag`, `disable_account_flag`, `account_expiration_days`) VALUES
(0, 0, 2013);

-- --------------------------------------------------------

--
-- Table structure for table `sm_config_user_notifications`
--

CREATE TABLE IF NOT EXISTS `sm_config_user_notifications` (
  `notify_workinghours_flag` tinyint(1) NOT NULL,
  `notify_nonworkinghours_flag` tinyint(1) NOT NULL,
  `notify_workinghours_mode` varchar(255) NOT NULL,
  `notify_nonworkinghours_mode` varchar(255) NOT NULL,
  `notify_stop_flag` tinyint(1) NOT NULL,
  `notify_stop_fromtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `notify_stop_totime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sm_config_user_notifications`
--

INSERT INTO `sm_config_user_notifications` (`notify_workinghours_flag`, `notify_nonworkinghours_flag`, `notify_workinghours_mode`, `notify_nonworkinghours_mode`, `notify_stop_flag`, `notify_stop_fromtime`, `notify_stop_totime`) VALUES
(1, 1, 'Email', 'Email', 0, '2013-05-04 00:00:00', '2013-05-04 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `sm_config_user_workinghours`
--

CREATE TABLE IF NOT EXISTS `sm_config_user_workinghours` (
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

INSERT INTO `sm_config_user_workinghours` (`working_days`, `sun_fromtime`, `sun_totime`, `mon_fromtime`, `mon_totime`, `tue_fromtime`, `tue_totime`, `wed_fromtime`, `wed_totime`, `thu_fromtime`, `thu_totime`, `fri_fromtime`, `fri_totime`, `sat_fromtime`, `sat_totime`) VALUES
(1111111, '09:00:00', '17:00:00', '09:00:00', '17:00:00', '09:00:00', '17:00:00', '09:00:00', '17:00:00', '09:00:00', '17:00:00', '09:00:00', '17:00:00', '09:00:00', '17:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `trans_inquire`
--

CREATE TABLE IF NOT EXISTS `trans_inquire` (
  `trans_id` bigint(20) NOT NULL,
  `trans_type` varchar(255) NOT NULL,
  `trans_type_id` bigint(20) NOT NULL,
  `from_regn_key` varchar(255) NOT NULL,
  `to_regn_key` varchar(255) NOT NULL,
  `user_from` varchar(255) NOT NULL,
  `user_to` varchar(255) NOT NULL,
  `details` text NOT NULL,
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY `trans_type_id` (`trans_type_id`),
  KEY `from` (`from_regn_key`),
  KEY `to` (`to_regn_key`),
  KEY `user_from` (`user_from`),
  KEY `user_to` (`user_to`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trans_inquire`
--


-- --------------------------------------------------------

--
-- Table structure for table `trans_reject`
--

CREATE TABLE IF NOT EXISTS `trans_reject` (
  `trans_reject_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trans_id` bigint(20) NOT NULL,
  `trans_type_id` bigint(20) NOT NULL,
  `trans_type` varchar(255) NOT NULL,
  `from_regn_key` varchar(255) NOT NULL,
  `to_regn_key` varchar(255) NOT NULL,
  `user_from` varchar(255) NOT NULL,
  `user_to` varchar(255) NOT NULL,
  `reject_reason` varchar(1024) NOT NULL,
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`trans_reject_id`),
  KEY `trans_id` (`trans_id`,`trans_type_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `trans_reject`
--

INSERT INTO `trans_reject` (`trans_reject_id`, `trans_id`, `trans_type_id`, `trans_type`, `from_regn_key`, `to_regn_key`, `user_from`, `user_to`, `reject_reason`, `created_timestamp`) VALUES
(1, 27, 11, 'PO', '4044082222', '4044081111', 'supplymedium.test@gmail.com', 'jaffmd02@gmail.com', '  ', '2013-10-17 00:51:53');

-- --------------------------------------------------------

--
-- Table structure for table `trans_tc`
--

CREATE TABLE IF NOT EXISTS `trans_tc` (
  `regn_rel_key` bigint(10) NOT NULL,
  `trans_tc_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `trans_type` varchar(255) NOT NULL,
  `tc` text NOT NULL,
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`trans_tc_id`),
  KEY `regn_rel_key` (`regn_rel_key`,`trans_type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `trans_tc`
--


-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `trans_table_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trans_id` bigint(20) NOT NULL,
  `trans_type` varchar(255) NOT NULL,
  `trans_type_id` bigint(20) NOT NULL,
  `action` varchar(255) NOT NULL,
  `from_regn_key` varchar(255) NOT NULL,
  `to_regn_key` varchar(255) NOT NULL,
  `user_from` varchar(255) NOT NULL,
  `user_to` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`trans_table_id`),
  KEY `trans_type_id` (`trans_type_id`),
  KEY `from` (`from_regn_key`),
  KEY `to` (`to_regn_key`),
  KEY `user_rom` (`user_from`),
  KEY `user_to` (`user_to`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `transaction`
--


-- --------------------------------------------------------

--
-- Table structure for table `transaction_history`
--

CREATE TABLE IF NOT EXISTS `transaction_history` (
  `trans_history_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trans_rel_id` bigint(20) NOT NULL,
  `from_regn_key` varchar(255) NOT NULL,
  `to_regn_key` varchar(255) NOT NULL,
  `amount` double(20,2) NOT NULL,
  `currency` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `created_time_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`trans_history_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `transaction_history`
--


-- --------------------------------------------------------

--
-- Table structure for table `transaction_rating`
--

CREATE TABLE IF NOT EXISTS `transaction_rating` (
  `trans_rating_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trans_rel_id` bigint(20) NOT NULL,
  `from_regn_key` varchar(255) NOT NULL,
  `to_regn_key` varchar(255) NOT NULL,
  `star` tinyint(1) NOT NULL,
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`trans_rating_id`),
  KEY `from_regn_key` (`from_regn_key`,`to_regn_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `transaction_rating`
--


-- --------------------------------------------------------

--
-- Table structure for table `transaction_remainder`
--

CREATE TABLE IF NOT EXISTS `transaction_remainder` (
  `trans_remainder_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trans_rel_id` bigint(20) NOT NULL,
  `from_regn_key` varchar(255) NOT NULL,
  `to_regn_key` varchar(255) NOT NULL,
  `remainder` text NOT NULL,
  `due_date` datetime NOT NULL,
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`trans_remainder_id`),
  KEY `trans_rel_id` (`trans_rel_id`,`from_regn_key`,`to_regn_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `transaction_remainder`
--


-- --------------------------------------------------------

--
-- Table structure for table `user_account_policies`
--

CREATE TABLE IF NOT EXISTS `user_account_policies` (
  `user_rel_key` varchar(255) NOT NULL,
  `change_password_flag` tinyint(1) NOT NULL,
  `disable_account_flag` tinyint(1) NOT NULL,
  `account_expiration_days` int(3) NOT NULL,
  KEY `user_rel_key` (`user_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_account_policies`
--


-- --------------------------------------------------------

--
-- Table structure for table `user_dept_mapping`
--

CREATE TABLE IF NOT EXISTS `user_dept_mapping` (
  `user_rel_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `dept_rel_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  KEY `user_rel_key` (`user_rel_key`),
  KEY `dept_rel_key` (`dept_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_dept_mapping`
--


-- --------------------------------------------------------

--
-- Table structure for table `user_feed`
--

CREATE TABLE IF NOT EXISTS `user_feed` (
  `user_feed_id` int(25) NOT NULL AUTO_INCREMENT,
  `regn_rel_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_rel_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `feed_title` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `feed` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `relative_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `local_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `web_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_feed_id`),
  KEY `regn_rel_key` (`regn_rel_key`),
  KEY `user_rel_key` (`user_rel_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `user_feed`
--


-- --------------------------------------------------------

--
-- Table structure for table `user_folder_access`
--

CREATE TABLE IF NOT EXISTS `user_folder_access` (
  `user_folder_access_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `regn_rel_key` varchar(255) NOT NULL,
  `dept_rel_key` varchar(255) NOT NULL,
  `user_rel_key` varchar(255) NOT NULL,
  `folder_rel_key` varchar(255) NOT NULL,
  `read_flag` tinyint(1) NOT NULL,
  `read_write_flag` tinyint(1) NOT NULL,
  PRIMARY KEY (`user_folder_access_id`),
  KEY `dept_rel_key` (`dept_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `user_folder_access`
--


-- --------------------------------------------------------

--
-- Table structure for table `user_group_mapping`
--

CREATE TABLE IF NOT EXISTS `user_group_mapping` (
  `user_rel_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `group_rel_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  KEY `user_rel_key` (`user_rel_key`),
  KEY `group_rel_key` (`group_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_group_mapping`
--


-- --------------------------------------------------------

--
-- Table structure for table `user_login`
--

CREATE TABLE IF NOT EXISTS `user_login` (
  `login_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'Use fixed size if you are limiting the password length in your password policy',
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`login_id`),
  KEY `email` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `user_login`
--


-- --------------------------------------------------------

--
-- Table structure for table `user_notifications`
--

CREATE TABLE IF NOT EXISTS `user_notifications` (
  `user_rel_key` varchar(255) NOT NULL,
  `notify_email` varchar(255) NOT NULL,
  `notify_mobile` varchar(255) NOT NULL,
  `notify_workinghours_flag` tinyint(1) NOT NULL,
  `notify_nonworkinghours_flag` tinyint(1) NOT NULL,
  `notify_workinghours_mode` varchar(255) NOT NULL,
  `notify_nonworkinghours_mode` varchar(255) NOT NULL,
  `notify_stop_flag` tinyint(1) NOT NULL,
  `notify_stop_fromtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `notify_stop_totime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  KEY `user_rel_key` (`user_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_notifications`
--


-- --------------------------------------------------------

--
-- Table structure for table `user_privileges`
--

CREATE TABLE IF NOT EXISTS `user_privileges` (
  `user_rel_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `privileges` bigint(20) NOT NULL,
  KEY `user_rel_key` (`user_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_privileges`
--


-- --------------------------------------------------------

--
-- Table structure for table `user_profile`
--

CREATE TABLE IF NOT EXISTS `user_profile` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `regn_key` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_profile_key` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `company_id` bigint(20) unsigned NOT NULL,
  `first_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `department` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_role` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `cell` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fax` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `profile_picture_path` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`user_id`),
  KEY `regn_key` (`regn_key`),
  KEY `user_profile_key` (`user_profile_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `user_profile`
--


-- --------------------------------------------------------

--
-- Table structure for table `user_ratings`
--

CREATE TABLE IF NOT EXISTS `user_ratings` (
  `rattings_key` int(11) NOT NULL AUTO_INCREMENT,
  `regn_rel_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_profile_key` varchar(255) NOT NULL,
  `reviewer_name` varchar(255) NOT NULL,
  `company_regn_key` varchar(255) NOT NULL,
  `review_title` varchar(100) NOT NULL,
  `comments` varchar(255) NOT NULL,
  `review_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ratings` int(11) NOT NULL,
  PRIMARY KEY (`rattings_key`),
  UNIQUE KEY `Rattings_Key` (`rattings_key`),
  KEY `Rattings_Key_2` (`rattings_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `user_ratings`
--


-- --------------------------------------------------------

--
-- Table structure for table `user_working_hours`
--

CREATE TABLE IF NOT EXISTS `user_working_hours` (
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


-- --------------------------------------------------------

--
-- Table structure for table `userkey_uuid_mapper`
--

CREATE TABLE IF NOT EXISTS `userkey_uuid_mapper` (
  `user_rel_key` varchar(255) NOT NULL,
  `uuid` varchar(255) NOT NULL,
  KEY `user_rel_key` (`user_rel_key`),
  KEY `uuid` (`uuid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userkey_uuid_mapper`
--


-- --------------------------------------------------------

--
-- Table structure for table `vendor_inquire_details`
--

CREATE TABLE IF NOT EXISTS `vendor_inquire_details` (
  `vendor_inquire_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vendor_regn_id` bigint(20) NOT NULL,
  `inquire_from` varchar(255) NOT NULL,
  `inquire_to` varchar(255) NOT NULL,
  `inquire_details` text NOT NULL,
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vendor_inquire_id`),
  KEY `inquire_from` (`inquire_from`,`inquire_to`),
  KEY `vendor_regn_id` (`vendor_regn_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `vendor_inquire_details`
--


-- --------------------------------------------------------

--
-- Table structure for table `vendor_registration`
--

CREATE TABLE IF NOT EXISTS `vendor_registration` (
  `vendor_regn_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `regn_rel_key` varchar(255) NOT NULL,
  `regn_rel_map` varchar(255) NOT NULL,
  `user_rel_key` varchar(255) NOT NULL,
  `request_sender_type` varchar(255) NOT NULL DEFAULT 'supplier',
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
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vendor_regn_id`),
  KEY `regn_rel_key` (`regn_rel_key`,`regn_rel_map`,`user_rel_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `vendor_registration`
--


-- --------------------------------------------------------

--
-- Table structure for table `vr_mailing_address`
--

CREATE TABLE IF NOT EXISTS `vr_mailing_address` (
  `addr_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_type` varchar(255) NOT NULL,
  `address` text NOT NULL,
  `city` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `zipcode` varchar(255) NOT NULL,
  `country_region` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `regn_key` bigint(11) NOT NULL,
  PRIMARY KEY (`addr_id`),
  KEY `addr_id` (`addr_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `vr_mailing_address`
--


-- --------------------------------------------------------

--
-- Table structure for table `watchlist`
--

CREATE TABLE IF NOT EXISTS `watchlist` (
  `watchlist_id` int(25) NOT NULL AUTO_INCREMENT COMMENT 'Auto incremented id for watchlist',
  `user_rel_key` varchar(255) NOT NULL COMMENT 'Owner of the watchlist',
  `regn_rel_key` varchar(255) NOT NULL COMMENT 'Company regn key of the watchlist owner',
  `watchlist_name` varchar(255) NOT NULL COMMENT 'Name of the watchlist',
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Watchlist created timestamp',
  PRIMARY KEY (`watchlist_id`),
  KEY `user_rel_key` (`user_rel_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `watchlist`
--


-- --------------------------------------------------------

--
-- Table structure for table `watchlist_member_mapper`
--

CREATE TABLE IF NOT EXISTS `watchlist_member_mapper` (
  `watchlist_rel_id` int(25) NOT NULL,
  `user_rel_key` varchar(255) NOT NULL,
  `created_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `watchlist_rel_id` (`watchlist_rel_id`,`user_rel_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `watchlist_member_mapper`
--
/*
DROP TRIGGER IF EXISTS genrate_invoice_trns_id;
CREATE TRIGGER genrate_invoice_trns_id BEFORE UPDATE ON invoice
  FOR EACH ROW
      SET NEW.invoice_trans_id =CONCAT("QTE",NEW.trans_id)

DROP TRIGGER IF EXISTS genrate_po_trns_id;
CREATE TRIGGER genrate_po_trns_id BEFORE UPDATE ON po
  FOR EACH ROW
      SET NEW.po_trans_id =CONCAT("QTE",NEW.trans_id)

DROP TRIGGER IF EXISTS genrate_quote_trns_id;
CREATE TRIGGER genrate_quote_trns_id BEFORE UPDATE ON quote
  FOR EACH ROW
      SET NEW.quote_trans_id =CONCAT("QTE",NEW.trans_id)

DROP TRIGGER IF EXISTS genrate_rfq_trns_id;
CREATE TRIGGER genrate_rfq_trns_id BEFORE UPDATE ON rfq
  FOR EACH ROW
      SET NEW.rfq_trans_id =CONCAT("RFQ",NEW.trans_id)
*/