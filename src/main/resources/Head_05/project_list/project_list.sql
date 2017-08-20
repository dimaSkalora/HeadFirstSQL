CREATE TABLE project_list (
  `proj_id` int(11) NOT NULL AUTO_INCREMENT,
  `proj_desc` varchar(120) DEFAULT NULL,
  `com_name` varchar(30) DEFAULT NULL,
  `con_phone` varchar(10) DEFAULT NULL,
  `est_cost` decimal(7,2) DEFAULT NULL,
  PRIMARY KEY (`proj_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1