/*
Navicat MySQL Data Transfer

Source Server         : localhostMYSQL
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : logback

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2015-02-02 14:48:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for logging_event
-- ----------------------------
DROP TABLE IF EXISTS `logging_event`;
CREATE TABLE `logging_event` (
  `timestmp` mediumtext NOT NULL,
  `formatted_message` text NOT NULL,
  `logger_name` varchar(255) NOT NULL,
  `level_string` varchar(255) NOT NULL,
  `thread_name` varchar(255) NOT NULL,
  `reference_flag` smallint(6) NOT NULL,
  `caller_filename` varchar(255) NOT NULL,
  `arg0` varchar(255) DEFAULT NULL,
  `arg1` varchar(255) DEFAULT NULL,
  `arg2` varchar(255) DEFAULT NULL,
  `arg3` varchar(255) DEFAULT NULL,
  `caller_class` varchar(255) NOT NULL,
  `caller_method` varchar(255) NOT NULL,
  `caller_line` varchar(255) NOT NULL,
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logging_event
-- ----------------------------

-- ----------------------------
-- Table structure for logging_event_exception
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_exception`;
CREATE TABLE `logging_event_exception` (
  `event_id` int(11) NOT NULL,
  `i` smallint(6) NOT NULL,
  `trace_line` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logging_event_exception
-- ----------------------------

-- ----------------------------
-- Table structure for logging_event_property
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_property`;
CREATE TABLE `logging_event_property` (
  `event_id` int(11) NOT NULL,
  `mapped_key` varchar(255) NOT NULL,
  `mapped_value` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logging_event_property
-- ----------------------------
