#CREATE TABLE, INSERT, SELECT
CREATE TABLE profession
(
id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
profession VARCHAR(20)
); 
INSERT INTO profession (proffesion)
SELECT profession FROM my_contacts_interests
GROUP BY profession 
ORDER BY profession;