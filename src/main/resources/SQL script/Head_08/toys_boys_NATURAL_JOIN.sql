#Внутреннее соединение 6 дейстВии: естественное соединение
#а toy_id — внешний ключ.
#Естественное соединение связывает записи но значениям одноименных столбцов..
SELECT boys.boy, toys.toy 
FROM boys NATURAL JOIN toys
