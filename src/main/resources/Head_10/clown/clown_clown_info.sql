CREATE TABLE clown_clown_info
(
 id INT NOT NULL AUTO_INCREMENT,
 name VARCHAR(50) NOT NULL,
 boss_id INT NOT NULL,
 PRIMARY KEY(id),
 FOREIGN KEY (boss_id) REFERENCES clown_boss(boss_id)

)