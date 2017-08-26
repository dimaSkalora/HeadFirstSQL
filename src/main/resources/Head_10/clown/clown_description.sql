CREATE TABLE clown_description
(
 id INT NOT NULL,
 gender CHAR(1) NOT NULL,
 whens VARCHAR(50) NOT NULL,
 FOREIGN KEY (id) REFERENCES clown_clown_info(id)
)