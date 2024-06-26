-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Jun 12, 2023 at 03:21 PM
-- Server version: 8.0.18
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `abccompany`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
CREATE TABLE IF NOT EXISTS `booking` (
  `bookingId` int(100) NOT NULL AUTO_INCREMENT,
  `startDate` varchar(60) NOT NULL,
  `endDate` varchar(60) NOT NULL,
  `bill` double NOT NULL,
  `nic` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `idHall` varchar(11) NOT NULL,
  `bookingtype` varchar(60) NOT NULL,
  PRIMARY KEY (`bookingId`),
  KEY `nic` (`nic`),
  KEY `idHall` (`idHall`)
) ENGINE=MyISAM AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`bookingId`, `startDate`, `endDate`, `bill`, `nic`, `idHall`, `bookingtype`) VALUES
(25, '2023-05-05', '2023-05-15', 1100000, '1111', 'h28', 'Continues Period'),
(23, '2023-05-04', '2023-05-24', 2100000, '112', 'we', 'Continues Period'),
(26, '2023-05-04', '2023-05-05', 75000, '12', 'w2', 'Specific Day'),
(28, '2023-05-02', '2023-05-04', 300000, '112', 'h56', 'Continues Period'),
(29, '2023-05-05', '2023-05-10', 600000, '112', 'h56', 'Continues Period'),
(30, '2023-05-03', '2023-05-22', 2000000, '112', 'h2', 'Continues Period'),
(31, '2023-05-26', '2023-05-28', 300000, '112', '66', 'Continues Period'),
(33, '2023-08-01', '2023-12-03', 12500000, '112', 'h45', 'Continues Period'),
(34, '2023-03-06', '2023-04-03', 2900000, '112', '61', 'Continues Period'),
(35, '2020-05-04', '2020-05-15', 1200000, '112', '61', 'Continues Period'),
(36, '2021-05-06', '2021-05-20', 1500000, '112', '61', 'Continues Period'),
(38, '2023-06-01', '2023-06-15', 700000, '112', 'h45', 'Continues Period');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `nic` varchar(12) NOT NULL,
  `name` varchar(100) NOT NULL,
  `tel` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`nic`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `customerlogin`
--

DROP TABLE IF EXISTS `customerlogin`;
CREATE TABLE IF NOT EXISTS `customerlogin` (
  `loginId` int(11) NOT NULL AUTO_INCREMENT,
  `nic` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`loginId`),
  UNIQUE KEY `username` (`nic`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hall`
--

DROP TABLE IF EXISTS `hall`;
CREATE TABLE IF NOT EXISTS `hall` (
  `idHall` varchar(11) NOT NULL,
  `size` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`idHall`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `hall`
--

INSERT INTO `hall` (`idHall`, `size`) VALUES
('h56', 'LARGE'),
('h2', 'LARGE'),
('h45', 'SMALL'),
('h44', 'LARGE');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE IF NOT EXISTS `payment` (
  `paymentId` int(100) NOT NULL AUTO_INCREMENT,
  `method` varchar(10) NOT NULL,
  `paidDate` varchar(12) NOT NULL,
  `bookingId` int(11) NOT NULL,
  PRIMARY KEY (`paymentId`),
  KEY `bookingId` (`bookingId`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`paymentId`, `method`, `paidDate`, `bookingId`) VALUES
(17, 'CASH', '2023-05-28', 7),
(18, 'CASH', '2023-05-28', 10),
(19, 'CASH', '2023-05-28', 26),
(20, 'CASH', '2023-05-28', 26),
(21, 'CASH', '2023-05-29', 23),
(22, 'CASH', '2023-05-29', 29),
(23, 'CASH', '2023-05-29', 28),
(24, 'CASH', '2023-05-29', 30),
(25, 'CASH', '2023-05-31', 33),
(26, 'CASH', '2023-06-08', 40);

-- --------------------------------------------------------

--
-- Table structure for table `specificday`
--

DROP TABLE IF EXISTS `specificday`;
CREATE TABLE IF NOT EXISTS `specificday` (
  `specificDayId` int(11) NOT NULL AUTO_INCREMENT,
  `bookingId` int(11) NOT NULL,
  `specificDay` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`specificDayId`),
  KEY `bookingId` (`bookingId`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `specificday`
--

INSERT INTO `specificday` (`specificDayId`, `bookingId`, `specificDay`) VALUES
(9, 26, 'SUNDAY');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
