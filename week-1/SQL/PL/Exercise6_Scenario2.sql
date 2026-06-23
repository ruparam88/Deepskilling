DECLARE
    CURSOR acc_cursor IS
        SELECT AccountID, Balance FROM Accounts FOR UPDATE;
    v_fee NUMBER := 50; 
BEGIN
    FOR acc_rec IN acc_cursor LOOP
        UPDATE Accounts
        SET Balance = acc_rec.Balance - v_fee
        WHERE CURRENT OF acc_cursor;
    END LOOP;
    COMMIT;
END;
/
