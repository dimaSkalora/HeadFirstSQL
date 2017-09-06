CREATE TABLE movie_table (
  `movie_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `rating` char(1) NOT NULL,
  `published` date NOT NULL,
  `category` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`movie_id`)
