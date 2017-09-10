START TRANSACTION;
SELECT * FROM piggy_bank;
UPDATE piggy_bank SET coin ='Q' WHERE coin ='P';
SELECT * FROM piggy_bank;
COMMIT;
SELECT * FROM piggy_bank;