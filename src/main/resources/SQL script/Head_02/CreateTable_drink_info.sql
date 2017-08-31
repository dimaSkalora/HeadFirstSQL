CREATE TABLE drink_info (
  drink_name varchar(16) DEFAULT NULL,
  cost decimal(4,2) DEFAULT NULL,
  carbs decimal(4,2) DEFAULT NULL,
  color varchar(20) DEFAULT NULL,
  ice char(1) DEFAULT NULL,
  calories int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1