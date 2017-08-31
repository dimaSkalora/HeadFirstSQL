# NOT IN - выводит результат запросоа которые не входят в заданый набор
SELECT date_name
FROM black_book
WHERE rating NOT IN('origin','rating','lol');