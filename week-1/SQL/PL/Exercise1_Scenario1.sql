DECLARE
    v_age NUMBER;
BEGIN
    FOR loan_rec IN (SELECT l.LoanID, c.DOB, l.InterestRate FROM Loans l JOIN Customers c ON l.CustomerID = c.CustomerID) LOOP
        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, loan_rec.DOB) / 12);
        IF v_age > 60 THEN
            UPDATE Loans SET InterestRate = InterestRate - 1 WHERE LoanID = loan_rec.LoanID;
        END IF;
    END LOOP;
    COMMIT;
END;
/
