SELECT * FROM headfirstsql.cookie_sales;
#SUM - суммирует значение столбца sales
SELECT SUM(sales) FROM cookie_sales
#Складывает данные только по Nicole
WHERE first_name ='Nicole'; 