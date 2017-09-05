#MIN - Возвращает минимальноне значение
SELECT first_name, MIN(sales) FROM cookie_sales
GROUP BY first_name;

 