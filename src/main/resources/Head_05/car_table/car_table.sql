CREATE TABLE `car_table` (
  `car_id` int(11) NOT NULL AUTO_INCREMENT,
  `VIN` varchar(16) DEFAULT NULL,
  `make` varchar(20) DEFAULT NULL,
  `model` varchar(20) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `price` decimal(7,2) DEFAULT NULL,
  `year` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8