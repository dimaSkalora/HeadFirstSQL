CREATE TABLE `my_contacts_interests` (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(30) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `profession` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `interests` varchar(100) DEFAULT NULL,
  `seeking` varchar(100) DEFAULT NULL,
  `interest1` varchar(50) DEFAULT NULL,
  `interest2` varchar(50) DEFAULT NULL,
  `interest3` varchar(50) DEFAULT NULL,
  `interest4` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`contact_id`)
)
 