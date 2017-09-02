#UPDATE - Команда обновляет текущие значение столбца или групы столбцов.
#SET - Ключевое слово используется в команде UPDATE для изменение существеного столбца
UPDATE clown_info
SET last_seen ='new last'
WHERE name = 'name'
AND last_seen = 'last';