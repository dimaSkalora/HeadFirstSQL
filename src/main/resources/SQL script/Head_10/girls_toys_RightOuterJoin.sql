SELECT g.girl, t.toy
FROM girls g
RIGHT OUTER JOIN toys t
ON g.toy_id = t.toy_id;