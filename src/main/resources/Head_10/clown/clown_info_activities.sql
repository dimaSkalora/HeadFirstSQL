CREATE TABLE clown_info_activities
(
 id INT NOT NULL,
 activity_id INT NOT NULL,
 FOREIGN KEY (id) REFERENCES clown_clown_info(id),
 FOREIGN KEY (activity_id) REFERENCES clown_activities(activity_id)

)