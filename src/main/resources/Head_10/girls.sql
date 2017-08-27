CREATE TABLE girls (
girl_id INT(11) NOT NULL AUTO_INCREMENT,
girl VARCHAR(20) NOT NULL,
toy_id int(11) NOT NULL,
PRIMARY KEY (girl_id),
FOREIGN KEY (toy_id) REFERENCES toys(toy_id)

);

INSERT INTO `girls` (`girl_id`,`girl`,`toy_id`) VALUES ('1','Jane','3');
INSERT INTO `girls` (`girl_id`,`girl`,`toy_id`) VALUES ('2','Sally','4');
INSERT INTO `girls` (`girl_id`,`girl`,`toy_id`) VALUES ('3','Cindy','1');
INSERT INTO `girls` (`girl_id`,`girl`,`toy_id`) VALUES ('4','Mandy','1');