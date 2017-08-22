CREATE TABLE interests
(
int_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
interest VARCHAR(50) NOT NULL,
contact_id INT(11) NOT NULL,
CONSTRAINT my_contacts_interests_contact_id_fk
FOREIGN KEY(contact_id)
REFERENCES my_contacts_interests(contact_id)
);