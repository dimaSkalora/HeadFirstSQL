#COUNT - Возвращает количество записей в столбце
SELECT first_name, COUNT(sale_date) FROM cookie_sales
GROUP BY first_name;
 