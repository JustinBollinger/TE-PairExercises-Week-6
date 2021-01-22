SELECT e.employee_id
    --    , department_id
  --      , first_name
      --  , last_name
    --    , birth_date
  --      , gender
--        , hire_date
FROM employee AS e
INNER JOIN project_employee AS pe
        ON e.employee_id = pe.employee_id
-- INNER JOIN project AS p
        -- ON pe.project_id = p.project_id
WHERE NOT EXISTS
        (SELECT pe.project_id
        FROM project_employee);




BEGIN TRANSACTION;

INSERT INTO department (department_id, name)
VALUES (?, ?);

ROLLBACK TRANSACTION;
-- COMMIT TRANSACTION;