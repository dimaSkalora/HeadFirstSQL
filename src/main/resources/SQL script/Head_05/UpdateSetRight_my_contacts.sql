#Чтобы заполнить данными новые столбцы c i t y и s t a t e , мы включим вызов функции RIGHT () в команду UPDATE.
#Функция выделяет два последних символа из старого столбца lo c a t io n и помещает их в новый столбец s ta te .

UPDATE my_contacts
SET state = RIGHT(location, 2);