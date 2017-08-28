CREATE TABLE bank_savings
(
 account_id INT NOT NULL,
 balance INT,
 FOREIGN KEY (account_id) REFERENCES bank_users (account_id)
)