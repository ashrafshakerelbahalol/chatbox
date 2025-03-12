CREATE TABLE users (  
   user_id BIGINT AUTO_INCREMENT,
    user_login VARCHAR(255) NOT NULL UNIQUE, 
    user_password CHAR(64) NOT NULL, 
    user_email VARCHAR(254) NOT NULL UNIQUE, 
    PRIMARY KEY (user_id)
);


CREATE TABLE chat (
  chat_id BIGINT AUTO_INCREMENT,
  chat_topic VARCHAR(100) NOT NULL, 
  chat_password CHAR(64), 
  chat_owner_user_id BIGINT NOT NULL, 
  PRIMARY KEY (chat_id),
  FOREIGN KEY (chat_owner_user_id) REFERENCES users(user_id)
);


CREATE TABLE user_chat (
   user_chat_chat_id BIGINT NOT NULL,
   user_chat_user_id BIGINT NOT NULL,
   PRIMARY KEY (user_chat_chat_id, user_chat_user_id),
   FOREIGN KEY (user_chat_chat_id) REFERENCES chat(chat_id), 
   FOREIGN KEY (user_chat_user_id) REFERENCES users(user_id)
);

CREATE TABLE message (
  message_id BIGINT AUTO_INCREMENT,
  message_datetime DATETIME NOT NULL,
  message_text TEXT NOT NULL,
  message_chat_id BIGINT NOT NULL,
  message_user_id BIGINT NOT NULL,
  PRIMARY KEY (message_id),
  FOREIGN KEY (message_chat_id) REFERENCES chat(chat_id),
  FOREIGN KEY (message_user_id) REFERENCES users(user_id)
);

CREATE INDEX user_login_idx ON users (user_login);
CREATE INDEX message_chat_idx ON message (message_chat_id); 