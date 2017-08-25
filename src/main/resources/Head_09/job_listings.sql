CREATE TABLE job_listings
(
 job_id INT NOT NULL AUTO_INCREMENT,
 title VARCHAR(50) NOT NULL,
 salary INT NOT NULL,
 zip INT NOT NULL,
 description VARCHAR(100),
 PRIMARY KEY(job_id)
);