#НекоррелимВанный подзапрос
#Автономный подзапрос, не содержаищй ссылок
#на данные внешнего запроса, называется некоррелированным подзапросом.
SELECT mc.first_name, mc.last_name, jc.salary
FROM
my_contacts_id AS mc NATURAL JOIN job_current AS jc
WHERE 
jc.salary > (SELECT jc.salary FROM my_contacts_id mc NATURAL JOIN job_current jc
WHERE email = 'andu@m,mwd.com');