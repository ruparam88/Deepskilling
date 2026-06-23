CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(p_id IN NUMBER, p_name IN VARCHAR2, p_pos IN VARCHAR2, p_sal IN NUMBER, p_dept IN VARCHAR2);
    PROCEDURE UpdateDetails(p_id IN NUMBER, p_pos IN VARCHAR2, p_sal IN NUMBER);
    FUNCTION CalculateAnnualSalary(p_id IN NUMBER) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
    PROCEDURE HireEmployee(p_id IN NUMBER, p_name IN VARCHAR2, p_pos IN VARCHAR2, p_sal IN NUMBER, p_dept IN VARCHAR2) IS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_id, p_name, p_pos, p_sal, p_dept, SYSDATE);
        COMMIT;
    END HireEmployee;

    PROCEDURE UpdateDetails(p_id IN NUMBER, p_pos IN VARCHAR2, p_sal IN NUMBER) IS
    BEGIN
        UPDATE Employees SET Position = p_pos, Salary = p_sal WHERE EmployeeID = p_id;
        COMMIT;
    END UpdateDetails;

    FUNCTION CalculateAnnualSalary(p_id IN NUMBER) RETURN NUMBER IS
        v_sal NUMBER;
    BEGIN
        SELECT Salary INTO v_sal FROM Employees WHERE EmployeeID = p_id;
        RETURN v_sal * 12;
    END CalculateAnnualSalary;
END EmployeeManagement;
/
