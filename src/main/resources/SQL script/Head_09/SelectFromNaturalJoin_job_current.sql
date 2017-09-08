SELECT mc.first_name, mc.last_name, jc.salary
FROM my_contacts_id AS mc
NATURAL JOIN job_current AS jc;