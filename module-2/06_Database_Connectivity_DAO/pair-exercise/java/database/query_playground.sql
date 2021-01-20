SELECT *
FROM project;

-- UPDATE project
-- SET to_date = '2021-06-30'
-- WHERE project_id = 4;

SELECT project_id
        , name
        , from_date
        , to_date
FROM project
WHERE NOT (to_date IS NULL AND from_date IS NULL)
        AND (to_date IS NULL OR to_date > now());