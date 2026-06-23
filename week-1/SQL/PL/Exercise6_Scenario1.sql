DECLARE
    CURSOR stmt_cursor IS
        SELECT c.Name, a.AccountID, t.TransactionDate, t.Amount, t.TransactionType
        FROM Customers c
        JOIN Accounts a ON c.CustomerID = a.CustomerID
        JOIN Transactions t ON a.AccountID = t.AccountID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE);
    v_rec stmt_cursor%ROWTYPE;
BEGIN
    OPEN stmt_cursor;
    LOOP
        FETCH stmt_cursor INTO v_rec;
        EXIT WHEN stmt_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Customer: ' || v_rec.Name || ' | Account: ' || v_rec.AccountID || ' | ' || v_rec.TransactionType || ' of $' || v_rec.Amount || ' on ' || TO_CHAR(v_rec.TransactionDate, 'YYYY-MM-DD'));
    END LOOP;
    CLOSE stmt_cursor;
END;
/
