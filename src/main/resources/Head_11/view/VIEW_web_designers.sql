CREATE VIEW web_designers AS
SELECT mc.first_name, mc.last_name, mc.phone, mc.email
FROM my_contacts_id mc
NATURAL JOIN job_desired jd
WHERE jd.title = "Web-dizain";