SELECT last_name, first_name
FROM my_contacts_id
WHERE zip_code = (SELECT zip_code FROM zip_code WHERE city ='city' AND state ='TN');