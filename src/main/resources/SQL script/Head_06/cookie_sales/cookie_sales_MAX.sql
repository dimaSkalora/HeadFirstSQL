#MAX - Возвращает максимальное значение
SELECT first_name, MAX(sales) FROM cookie_sales
GROUP BY first_name;

 