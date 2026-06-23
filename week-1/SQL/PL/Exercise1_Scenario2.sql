ALTER TABLE Customers ADD IsVIP VARCHAR2(5);

BEGIN
    FOR cust_rec IN (SELECT CustomerID, Balance FROM Customers) LOOP
        IF cust_rec.Balance > 10000 THEN
            UPDATE Customers SET IsVIP = 'TRUE' WHERE CustomerID = cust_rec.CustomerID;
        ELSE
            UPDATE Customers SET IsVIP = 'FALSE' WHERE CustomerID = cust_rec.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/
