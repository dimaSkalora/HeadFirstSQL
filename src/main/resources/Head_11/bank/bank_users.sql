CREATE TABLE bank_users
(
 account_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 last_name VARCHAR(20) NOT NULL,
 first_name VARCHAR(20) NOT NULL,
 phone VARCHAR(15) NOT NULL,
 email VARCHAR(30) NOT NULL,
 address VARCHAR(50) NOT NULL 
)