CREATE TABLE job_desired

(
 
contact_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,

 title VARCHAR(30) NOT NULL,
 salary_low int(11) NOT NULL,

 salary_high int(11) NOT NULL,

 available VARCHAR(20) NOT NULL,

 years_exp int(11) NOT NULL,

 FOREIGN KEY (contact_id ) REFERENCES job_current(contact_id)

);