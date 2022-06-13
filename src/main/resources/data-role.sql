-- Use database
USE cheetah;

-- Insert role customer if not exists
INSERT INTO role (id, name)
    SELECT 1,'Customer'
        WHERE NOT EXISTS
                (SELECT name FROM cheetah.role WHERE name = 'Customer');

-- Insert role admin if not exists
INSERT INTO role (id, name)
    SELECT 2,'Admin'
        WHERE NOT EXISTS
                (SELECT name FROM cheetah.role WHERE name = 'Admin');

-- Insert role supper admin if not exists
INSERT INTO role (id, name)
    SELECT 3,'Supper-Admin'
        WHERE NOT EXISTS
                (SELECT name FROM cheetah.role WHERE name = 'Supper-Admin');