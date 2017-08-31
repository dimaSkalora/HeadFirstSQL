CREATE TABLE doughnut_ratings (
  location varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  time time DEFAULT NULL,
  date date DEFAULT NULL,
  type varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  rating tinyint(4) DEFAULT NULL,
  comments varchar(50) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8