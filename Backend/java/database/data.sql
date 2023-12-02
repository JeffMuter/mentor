--any data goes here
--inserts, updates, deletes

--create.

BEGIN TRANSACTION;

--INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
--INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

--makes java, js tables with data

-- Insert a user first
INSERT INTO users (username, password_hash, role) 
VALUES ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

-- Now insert a mentor associated with that user
WITH new_user AS (
  SELECT user_id FROM users WHERE username = 'user'
)
INSERT INTO mentors (name, user_id, chatgpt_id, training_status, created_at, updated_at) 
SELECT 'Mentor Name', user_id, 'chatgpt_id', 'Training Status', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP FROM new_user;

COMMIT;

