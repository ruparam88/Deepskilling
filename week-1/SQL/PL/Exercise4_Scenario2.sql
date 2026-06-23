CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_amount IN NUMBER,
    p_rate IN NUMBER,
    p_years IN NUMBER
) RETURN NUMBER AS
    v_monthly_rate NUMBER;
    v_total_months NUMBER;
    v_installment NUMBER;
BEGIN
    v_monthly_rate := p_rate / 100 / 12;
    v_total_months := p_years * 12;
    
    IF v_monthly_rate = 0 THEN
        v_installment := p_amount / v_total_months;
    ELSE
        v_installment := p_amount * (v_monthly_rate * POWER(1 + v_monthly_rate, v_total_months)) / (POWER(1 + v_monthly_rate, v_total_months) - 1);
    END IF;
    
    RETURN ROUND(v_installment, 2);
END CalculateMonthlyInstallment;
/
