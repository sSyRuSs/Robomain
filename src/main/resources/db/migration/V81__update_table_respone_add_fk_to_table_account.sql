ALTER TABLE if exists respone_issue
add FOREIGN KEY (create_by) REFERENCES account(id) ON DELETE set null;