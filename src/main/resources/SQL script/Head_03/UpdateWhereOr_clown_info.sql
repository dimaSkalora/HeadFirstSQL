#UPDATE - Команда обновляет текущие значение столбца или групы столбцов.
#SET - Ключевое слово используется в команде UPDATE для изменение существеного столбца
UPDATE drink_info
SET cost = cost+1
WHERE drink_name = 'Blue Moon'
OR 
drink_name ='name2'
OR
drink_name ='name3';