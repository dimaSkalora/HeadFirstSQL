SELECT mc.first_name, mc.last_name, mc.phone, jc.title
From job_current AS jc NATURAL JOIN my_contacts_id AS mc
WHERE jc.title IN (SELECT title FROM job_listings);