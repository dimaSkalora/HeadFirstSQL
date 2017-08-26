CREATE TABLE clown_boss
(
 id INT NOT NULL AUTO_INCREMENT, 
 boss_id INT NOT NULL,
 PRIMARY KEY(id),
 FOREIGN KEY (boss_id) REFERENCES clown_clown_info(id)
)