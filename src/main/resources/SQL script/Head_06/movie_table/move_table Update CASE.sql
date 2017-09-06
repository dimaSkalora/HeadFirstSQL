
UPDATE movie_table SET category =
CASE
WHEN drama ='Y' THEN 'драма' 
WHEN comedy ='Y' THEN 'комедия' 
WHEN action ='Y' THEN 'боевик' 
WHEN gore = 'Y' THEN 'ужасы' 
WHEN scifi ='Y' THEN'фантастика' 
WHEN for_kids ='Y' THEN 'семейное' 
WHEN cartoon = 'Y' AND rating ='G' THEN 'семейное' 
ELSE 'разное'
END;