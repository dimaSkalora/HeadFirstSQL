SELECT first_name, SUM(sales) FROM cookie_sales
GROUP BY first_name 
#LIMIT - Определяет, сколько именно записей должен вернут запрос, и скакой записи следует начать 
LIMIT 0 , 3;