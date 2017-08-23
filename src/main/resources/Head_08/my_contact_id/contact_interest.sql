CREATE TABLE contact_interest (
contact_id int NOT NULL,
interest_id int NOT NULL,
PRIMARY KEY (contact_id, interest_id),
FOREIGN KEY (contact_id) REFERENCES my_contacts_id(contact_id),
FOREIGN KEY (interest_id) REFERENCES interests(interest_id)
)