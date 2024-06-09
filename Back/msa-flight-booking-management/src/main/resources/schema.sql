-- -----------------------------------------------------
-- Table characters
-- -----------------------------------------------------

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE'
                                                         AND TABLE_NAME = 'characters')
CREATE TABLE characters (
                            characters_id int IDENTITY(1,1) NOT NULL,
                            client_id int NOT NULL
                            CONSTRAINT pk_characters PRIMARY KEY (characters_id)
);

-- -----------------------------------------------------
-- Table favorites
-- -----------------------------------------------------

IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE'
                                                         AND TABLE_NAME = 'favorites')
CREATE TABLE favorites (
                           favorites_id int IDENTITY(1,1) NOT NULL,
                           user_id int NOT NULL,
                           characters_id int NOT NULL,
                           CONSTRAINT pk_favorites PRIMARY KEY (user_id, characters_id),
                           CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users(user_id),
                           CONSTRAINT fk_characters FOREIGN KEY (characters_id) REFERENCES characters(characters_id)
);