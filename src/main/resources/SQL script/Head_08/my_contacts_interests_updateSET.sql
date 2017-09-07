SELECT * FROM headfirstsql.my_contacts_interests;
UPDATE my_contacts_interests
SET interest1 = SUBSTRING_INDEX(interests,',',1);