SELECT * FROM headfirstsql.my_contacts_interests;
ALTER TABLE my_contacts_interests
#Добовляем новые столбцы
ADD COLUMN interest1 VARCHAR(50),
ADD COLUMN interest2 VARCHAR(50),
ADD COLUMN interest3 VARCHAR(50),
ADD COLUMN interest4 VARCHAR(50);
#Затем первое увлечение переносим в первый столбец
UPDATE my_contacts_interests
SET interest1 = SUBSTRING_INDEX(interests,',',1);
#Удаляем из столбца interests значение которое мы перенесли в новый столбец
UPDATE my_contacts_interests SET interests = TRIM(RIGHT(interests,
(LENGHT(interests)-LENGHT(interest1)-1)));
SET interest2 = SUBSTRING_INDEX(interests,',',1);
UPDATE my_contacts_interests SET interests = TRIM(RIGHT(interests,
(LENGHT(interests)-LENGHT(interest2)-1)));
SET interest3 = SUBSTRING_INDEX(interests,',',1);
UPDATE my_contacts_interests SET interests = TRIM(RIGHT(interests,
(LENGHT(interests)-LENGHT(interest3)-1)));
UPDATE my_contacts_interests SET interest4 = interests;