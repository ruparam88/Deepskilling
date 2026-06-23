DECLARE
    CURSOR loan_cursor IS
        SELECT LoanID, InterestRate FROM Loans FOR UPDATE;
BEGIN
    FOR loan_rec IN loan_cursor LOOP
        UPDATE Loans
        SET InterestRate = loan_rec.InterestRate + 0.5 
        WHERE CURRENT OF loan_cursor;
    END LOOP;
    COMMIT;
END;
/
