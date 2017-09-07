#Перекрестное соединение(CROSSJOIN) возвращаеткомбинации каждойзаписи первой таблицы 
#с каждой записью второй таблицы.

SELECT t.toy, b.boy
 FROM toys AS t CROSS JOIN boys_demo AS b;