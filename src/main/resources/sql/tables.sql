-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.23-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  10.2.0.5683
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 mybatis.good 结构
CREATE TABLE IF NOT EXISTS `good` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '',
  `stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4;

-- 数据导出被取消选择。

CREATE TABLE `order` (
	`id` BIGINT(11) NOT NULL AUTO_INCREMENT,
	`good_id` BIGINT(11) NOT NULL DEFAULT '0',
	`buy_num` INT(11) NOT NULL,
	`pay_state` INT(11) NOT NULL DEFAULT '0' COMMENT '支付状态 1支付中 ，2已支付',
	`create_time` DATETIME NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `pay_state` (`pay_state`),
	INDEX `create_time` (`create_time`),
	INDEX `good_id` (`good_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4;

-- 导出  表 mybatis.order_history 结构
CREATE TABLE IF NOT EXISTS `order_history` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `good_id` bigint(11) NOT NULL DEFAULT '0',
  `buy_num` int(11) NOT NULL,
  `pay_state` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4;

-- 数据导出被取消选择。

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
