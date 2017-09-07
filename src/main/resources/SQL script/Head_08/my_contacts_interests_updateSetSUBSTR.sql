SELECT * FROM headfirstsql.my_contacts_interests;
UPDATE my_contacts_interests
SET interests = SUBSTR(interests, LENGTH(interest1)+2);