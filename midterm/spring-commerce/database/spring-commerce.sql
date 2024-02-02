-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for spring_commerce
CREATE DATABASE IF NOT EXISTS `spring_commerce` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `spring_commerce`;

-- Dumping structure for table spring_commerce.brands
CREATE TABLE IF NOT EXISTS `brands` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table spring_commerce.brands: ~2 rows (approximately)
INSERT INTO `brands` (`id`, `name`) VALUES
	(1, 'Apple'),
	(2, 'Samsung');

-- Dumping structure for table spring_commerce.categories
CREATE TABLE IF NOT EXISTS `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `img` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table spring_commerce.categories: ~2 rows (approximately)
INSERT INTO `categories` (`id`, `name`, `img`) VALUES
	(1, 'Phone', 'https://johnlewis.scene7.com/is/image/JohnLewis/mobiles-nav-card-apple2-200323'),
	(2, 'Tablet', 'https://media-ik.croma.com/prod/https://media.croma.com/image/upload/v1667851459/Croma%20Assets/Computers%20Peripherals/Tablets%20and%20iPads/Images/249129_0_mds3pq.png?tr=w-600');

-- Dumping structure for table spring_commerce.colors
CREATE TABLE IF NOT EXISTS `colors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table spring_commerce.colors: ~2 rows (approximately)
INSERT INTO `colors` (`id`, `name`) VALUES
	(1, 'Red'),
	(2, 'Pink');

-- Dumping structure for table spring_commerce.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` date DEFAULT NULL,
  `note` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `total_selling_price` double NOT NULL,
  `user_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`email`),
  CONSTRAINT `orders_chk_1` CHECK ((`status` between 0 and 3))
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table spring_commerce.orders: ~8 rows (approximately)
INSERT INTO `orders` (`id`, `created_at`, `note`, `status`, `total_selling_price`, `user_id`) VALUES
	(5, '2023-08-07', NULL, 0, 9100, 'user@gmail.com'),
	(6, '2023-08-07', 'Test Note', 3, 5000, NULL),
	(10, '2023-08-08', NULL, 0, 6800, 'user@gmail.com'),
	(11, '2023-08-08', NULL, 0, 5300, 'user@gmail.com'),
	(12, '2023-08-08', NULL, 0, 1400, 'user@gmail.com'),
	(13, '2023-08-08', NULL, 0, 2800, 'user@gmail.com'),
	(14, '2023-08-08', NULL, 0, 4500, 'user@gmail.com'),
	(16, '2023-08-09', NULL, 0, 5300, 'user@gmail.com');

-- Dumping structure for table spring_commerce.order_detail
CREATE TABLE IF NOT EXISTS `order_detail` (
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `FKc7q42e9tu0hslx6w4wxgomhvn` (`product_id`),
  CONSTRAINT `FKc7q42e9tu0hslx6w4wxgomhvn` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKrws2q0si6oyd6il8gqe2aennc` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table spring_commerce.order_detail: ~13 rows (approximately)
INSERT INTO `order_detail` (`order_id`, `product_id`, `quantity`) VALUES
	(5, 1, 1),
	(5, 2, 1),
	(5, 3, 3),
	(5, 5, 3),
	(11, 1, 1),
	(11, 2, 2),
	(11, 3, 2),
	(12, 1, 2),
	(13, 1, 4),
	(14, 2, 3),
	(16, 1, 1),
	(16, 2, 2),
	(16, 3, 2);

-- Dumping structure for table spring_commerce.products
CREATE TABLE IF NOT EXISTS `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `illustration` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `brand_id` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `color_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa3a4mpsfdf4d2y6r8ra3sc8mv` (`brand_id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  KEY `FKebociq5k3b2tkcxh3q5dg4eed` (`color_id`),
  CONSTRAINT `FKa3a4mpsfdf4d2y6r8ra3sc8mv` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`),
  CONSTRAINT `FKebociq5k3b2tkcxh3q5dg4eed` FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table spring_commerce.products: ~5 rows (approximately)
INSERT INTO `products` (`id`, `description`, `illustration`, `name`, `price`, `brand_id`, `category_id`, `color_id`) VALUES
	(1, 'This is Iphone 7', 'https://cdn.tgdd.vn/Products/Images/42/74110/iphone-7-gold-600x600-1-600x600.jpg', 'Iphone 7', 700, 1, 1, 1),
	(2, 'This is Samsung Flip', 'https://cdn.tgdd.vn/Products/Images/42/258047/samsung-galaxy-z-flip4-5g-128gb-thumb-tim-600x600.jpg', 'Samsung Flip', 1500, 2, 1, 2),
	(3, 'This is Iphone 8', 'https://cdn.tgdd.vn/Products/Images/42/114113/iphone-8-64gb-hh-600x600.jpg', 'Iphone 8', 800, 1, 1, 1),
	(5, 'This is Ipad Pro', 'https://cdn.tgdd.vn/Products/Images/522/221775/ipad-pro-12-9-inch-wifi-128gb-2020-bac-600x600-1-200x200.jpg', 'Ipad Pro', 1500, 1, 2, 1),
	(7, 'This is Samsung Galaxy Tab S7', 'https://cdn.hoanghamobile.com/i/productlist/dsp/Uploads/2021/06/30/image-removebg-preview-1.png', 'Samsung Galaxy Tab S7', 1200, 2, 2, 1);

-- Dumping structure for table spring_commerce.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table spring_commerce.roles: ~2 rows (approximately)
INSERT INTO `roles` (`id`, `name`) VALUES
	(1, 'USER'),
	(2, 'ADMIN');

-- Dumping structure for table spring_commerce.users
CREATE TABLE IF NOT EXISTS `users` (
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`email`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table spring_commerce.users: ~1 rows (approximately)
INSERT INTO `users` (`email`, `address`, `enabled`, `first_name`, `last_name`, `password`, `phone`, `role_id`) VALUES
	('user@gmail.com', 'HCM', b'1', 'John', 'Doe', '$2a$10$PgSJbez0TsZjqw4Vw6UAZeWVEbs/K2lVe8rxLxR8jXwv11EGzlMEu', '0931024306', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
