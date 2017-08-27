CREATE TABLE clown_info_location
(
 id INT NOT NULL,
 location_id INT NOT NULL,
 whens VARCHAR(50) NOT NULL, 
 FOREIGN KEY (id) REFERENCES clown_clown_info(id),
 FOREIGN KEY (location_id) REFERENCES clown_location(location_id)

)