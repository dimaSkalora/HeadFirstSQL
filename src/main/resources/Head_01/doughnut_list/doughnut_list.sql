CREATE TABLE doughnut_list (
  doughnut_name varchar(10) NOT NULL,
  doughnut_type varchar(6) NOT NULL,
  doughnut_cost decimal(3,2) NOT NULL DEFAULT '1.00',
  PRIMARY KEY (`doughnut_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8