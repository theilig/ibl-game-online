-- !Ups
DROP TABLE UserAuth;
-- !Downs
CREATE TABLE UserAuth (
                          user_auth_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                          expires TIMESTAMP NOT NULL,
                          user_id INTEGER NOT NULL,
                          token_value VARCHAR(256) NOT NULL,
                          UNIQUE KEY token_value (token_value),
                          UNIQUE KEY user_id (user_id),
                          KEY expires (expires)
);
