-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Feb 25, 2019 at 05:43 AM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `custompos`
--
CREATE DATABASE IF NOT EXISTS `custompos` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `custompos`;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `description` varchar(455) NOT NULL,
  `category` varchar(455) NOT NULL,
  `price` varchar(455) NOT NULL,
  `image` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `description`, `category`, `price`, `image`) VALUES
(3, 'Pizza Bread1', '0', '8.99', '/Users/devansh/DKDinersPOS/1PizzaBread1.png'),
(4, 'Pizza Bread2', '0', '8.99', '/Users/devansh/DKDinersPOS/2PizzaBread2.png'),
(5, 'Cut Potatoes', '0', '5.99', '/Users/devansh/DKDinersPOS/3PotatoesCut.png'),
(6, 'Flatbread Pizza', '0', '9.99', '/Users/devansh/DKDinersPOS/4FlatbreadPizza.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
