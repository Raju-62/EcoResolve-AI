INSERT INTO locations (name,building,floor) VALUES
('Main Building - Ground Floor','Main Building','Ground Floor'),
('Main Building - First Floor','Main Building','1st Floor'),
('Laboratory Block A','Laboratory Block A','2nd Floor'),
('Laboratory Block B','Laboratory Block B','2nd Floor'),
('Cafeteria','Main Building','Ground Floor'),
('Library','Main Building','1st Floor'),
('Outdoor Campus Area','Campus','Ground Floor')
ON CONFLICT DO NOTHING;
-- Admin is provisioned securely by the application when ADMIN_EMAIL and ADMIN_PASSWORD environment variables are supplied.
