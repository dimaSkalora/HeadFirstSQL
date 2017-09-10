START TRANSACTION;
SELECT * FROM piggy_bank;
UPDATE piggy_bank SET coin ='Q' WHERE coin ='S'
AND coin_year <1960;
SELECT * FROM piggy_bank;
COMMIT;
SELECT * FROM piggy_bank;
