CREATE TABLE my_contacts (
  list_name varchar(30) NOT NULL,
  first_name varchar(20) DEFAULT NULL,
  email varchar(50) DEFAULT NULL,
  gender char(1) DEFAULT NULL,
  birthday date DEFAULT NULL,
  profession varchar(50) DEFAULT NULL,
  location varchar(50) DEFAULT NULL,
  status varchar(20) DEFAULT NULL,
  interests varchar(100) DEFAULT NULL,
  seeking varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8