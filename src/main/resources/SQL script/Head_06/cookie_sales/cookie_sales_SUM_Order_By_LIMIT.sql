SELECT first_name, SUM(sales) FROM cookie_sales
GROUP BY first_name 
ORDER BY SUM(sales) DESC
#LIMIT - Позволяет указать колическтво записей
LIMIT 0 , 3;