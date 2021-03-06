#Коррелированные подзапросы
#Чтобы значение mc.contact_id могло использоваться во внутреннем запросе, 
#сначала должен быть выполнен внешний запрос
SELECT mc.first_name firstname, mc.last_name lastname, mc.email email
FROM my_contacts_id AS mc
WHERE NOT EXISTS
(SELECT * FROM job_current jc
WHERE contact_id = jc.contact_id);