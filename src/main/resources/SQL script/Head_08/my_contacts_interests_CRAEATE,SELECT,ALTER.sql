#CREATE TABLE, SELECT, ALTER
CREATE TABLE profession AS
SELECT profession FROM my_contacts_interests
GROUP BY profession
ORDER BY profession;
ALTER TABLE profession
ADD COLUMN id INT NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY(id);