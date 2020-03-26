-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 17, 2019 at 10:06 AM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cbir`
--
CREATE DATABASE `cbir` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `cbir`;

-- --------------------------------------------------------

--
-- Table structure for table `imageinfo`
--

CREATE TABLE IF NOT EXISTS `imageinfo` (
  `name` char(100) DEFAULT NULL,
  `description` char(100) DEFAULT NULL,
  `path` char(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `imageinfo`
--

INSERT INTO `imageinfo` (`name`, `description`, `path`) VALUES
('Mohan', 'Mohan 2010 batch student', '2018_10_26_12_59_08.jpg'),
('Mohan1', 'Mohan 2010 batch student', '2018_10_26_12_59_31.jpg'),
('Niro', 'Niro Image', '2018_10_26_01_14_03.jpg'),
('Sohan', 'Sohan Image', '2018_10_26_01_14_22.jpg'),
('Sohan1', 'Sohan1 Image', '2018_10_26_01_14_42.jpg'),
('initial stage', 'Must contact with the doctor', '2018_10_28_04_15_46.jpg'),
('initial stage1', 'contact with the doctor', '2018_10_28_04_16_13.jpg'),
('final stage of cancer', 'must contct with dr', '2018_11_03_12_58_42.jpg'),
('Mole on Body', 'Initial stage of cancer must contact', '2018_11_29_02_02_58.jpg'),
('Mole on Body', 'Initial stage of cancer must contact', '2018_11_29_02_03_32.jpg'),
('hi', 'rbc-300 wbc-200', '2019_01_17_02_02_26.jpg'),
('Low Blood', 'hi hello', '2019_01_30_02_07_17.jpg'),
('Tissue Problem', 'tissue Problem1', '2019_01_30_02_09_01.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `user_details`
--

CREATE TABLE IF NOT EXISTS `user_details` (
  `userid` char(100) DEFAULT NULL,
  `password` char(100) DEFAULT NULL,
  `usertype` char(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_details`
--

INSERT INTO `user_details` (`userid`, `password`, `usertype`) VALUES
('admin', 'admin', 'a'),
('Rishav', '123', 'm'),
('Monika', '123', 'm');
