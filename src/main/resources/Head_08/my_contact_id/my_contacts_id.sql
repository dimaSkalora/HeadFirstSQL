CREATE TABLE my_contacts_id3 (
  contact_id int(11) NOT NULL AUTO_INCREMENT,
  last_name varchar(30) DEFAULT NULL,
  first_name varchar(20) DEFAULT NULL,
  phone varchar(11) DEFAULT NULL,
  email varchar(50) DEFAULT NULL,
  gender char(1) DEFAULT NULL,
  birthday date DEFAULT NULL,
  prof_id INT(11) NOT NULL, 
  zip_code INT(11) NOT NULL,
  status_id INT(11)NOT NULL,   
  PRIMARY KEY (contact_id),
  FOREIGN KEY (prof_id) REFERENCES profession(prof_id),
  FOREIGN KEY (zip_code) REFERENCES zip_code(zip_code),
  FOREIGN KEY (status_id) REFERENCES status(status_id)
)