#Внутреннее соединение 6 дейстВии: неэквивалентное соединение
#а toy_id — внешний ключ.
#Неэквивалентное соединение — внутреннее соединение с проверкой неравенства.
SELECT boys.boy, toys.toy 
FROM boys INNER JOIN toys
ON boys.toy_id <> toys.toy_id
ORDER BY boys.boy;