#LIKE - ищет часть текстовой строки и возвращает совпадение
#_ - представляет ровно один произвольный символ
SELECT * FROM my_contacts
WHERE first_name LIKE '_nne';