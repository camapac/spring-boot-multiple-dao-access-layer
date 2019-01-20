/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : weather

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 20/01/2019 12:24:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `country_code` varchar(20) NOT NULL,
  `coord_lon` double(255,0) DEFAULT NULL,
  `coord_lat` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=209580 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for weather
-- ----------------------------
DROP TABLE IF EXISTS `weather`;
CREATE TABLE `weather` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_id` int(255) DEFAULT NULL,
  `temp` double(255,0) DEFAULT NULL,
  `temp_min` double(255,0) DEFAULT NULL,
  `temp_max` double(255,0) DEFAULT NULL,
  `pressure` double(255,0) DEFAULT NULL,
  `sea_level` double(255,0) DEFAULT NULL,
  `grnd_level` double(255,0) DEFAULT NULL,
  `humidity` int(255) DEFAULT NULL,
  `temp_kf` double(255,0) DEFAULT NULL,
  `clouds_all` int(255) DEFAULT NULL,
  `wind_speed` double DEFAULT NULL,
  `wind_deg` double(255,0) DEFAULT NULL,
  `sys_pod` varchar(255) DEFAULT NULL,
  `dt` int(255) DEFAULT NULL,
  `dt_txt` datetime DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `city_weather` (`city_id`),
  CONSTRAINT `city_weather` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=298 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for weather_status
-- ----------------------------
DROP TABLE IF EXISTS `weather_status`;
CREATE TABLE `weather_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `weather_id` int(11) DEFAULT NULL,
  `main` varchar(100) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `weather_status_reference` (`weather_id`),
  CONSTRAINT `weather_status_reference` FOREIGN KEY (`weather_id`) REFERENCES `weather` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=298 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
