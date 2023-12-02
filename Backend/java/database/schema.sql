CREATE TABLE users (
   user_id SERIAL PRIMARY KEY,
   username varchar(50) NOT NULL UNIQUE,
   password_hash varchar(200) NOT NULL,
   role varchar(50) NOT NULL
);

--any data goes here

CREATE TABLE mentors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    user_id INT NOT NULL REFERENCES users(user_id),
    chatgpt_id VARCHAR(255),
    training_status VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE audio_files (
    id SERIAL PRIMARY KEY,
    file_path VARCHAR(255) NOT NULL,
    transcription TEXT,
    transcribed_at TIMESTAMP,
    mentor_id INT NOT NULL REFERENCES mentors(id),
    created_at TIMESTAMP NOT NULL
);