-- User Table: Stores user information.
CREATE TABLE users     
   user_id BIGINT AUTO_INCREMENT,
    user_login VARCHAR(255) NOT NULL UNIQUE, -- Added NOT NULL and UNIQUE
    user_password CHAR(64) NOT NULL, -- Consider using VARCHAR for variable-length hashes
    user_email VARCHAR(254) NOT NULL UNIQUE, -- Standard email length (254 chars)
    PRIMARY KEY (user_id)
);

-- Chat Table: Stores chat room details.
CREATE TABLE chat (
  chat_id BIGINT AUTO_INCREMENT,
  chat_topic VARCHAR(100) NOT NULL, -- Increased length for flexibility
  chat_password CHAR(64), -- Optional password for private chats
  chat_owner_user_id BIGINT NOT NULL, -- Added owner to avoid circular dependency
  PRIMARY KEY (chat_id),
  FOREIGN KEY (chat_owner_user_id) REFERENCES user(user_id)
);

-- User_Chat Junction Table: Manages many-to-many users-chats.
CREATE TABLE user_chat (
   user_chat_chat_id BIGINT NOT NULL,
   user_chat_user_id BIGINT NOT NULL,
   PRIMARY KEY (user_chat_chat_id, user_chat_user_id),
   FOREIGN KEY (user_chat_chat_id) REFERENCES chat(chat_id), 
   FOREIGN KEY (user_chat_user_id) REFERENCES user(user_id)
);

-- Message Table: Stores messages sent in chats.
CREATE TABLE message (
  message_id BIGINT AUTO_INCREMENT,
  message_datetime DATETIME NOT NULL,
  message_text TEXT NOT NULL,
  message_chat_id BIGINT NOT NULL,
  message_user_id BIGINT NOT NULL,
  PRIMARY KEY (message_id),
  FOREIGN KEY (message_chat_id) REFERENCES chat(chat_id),
  FOREIGN KEY (message_user_id) REFERENCES user(user_id)
);

-- Indexes for faster lookups.
CREATE INDEX user_login_idx ON user (user_login);
CREATE INDEX message_chat_idx ON message (message_chat_id); -- Improves message retrieval by chat    explain the dtad base with columns