#Коррелированные подзапросы
#Чтобы значение mc.contact_id могло использоваться во внутреннем запросе, 
#сначала должен быть выполнен внешний запрос
SELECT mc.first_name, mc.last_name
FROM my_contacts_id AS mc
WHERE 
3 = (SELECT COUNT(*) FROM contact_interest
WHERE contact_id = mc.contact_id);