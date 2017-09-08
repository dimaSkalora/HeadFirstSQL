SELECT some_column, another_column 
FROM table
WHERE column = (SELECT column FROM table);