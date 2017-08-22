CREATE TABLE movie_table_demo (
  `movie_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `rating` char(1) NOT NULL,
  `drama` char(1) NOT NULL,
  `comedy` char(1) NOT NULL,
  `action` char(1) NOT NULL,
  `gore` char(1) NOT NULL,
  `scifi` char(1) NOT NULL,
  `for_kids` char(1) NOT NULL,
  `cartoon` char(1) NOT NULL,
  `published` date NOT NULL,
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8