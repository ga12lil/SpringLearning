-- liquibase formatted sql

-- changeset me:create_chat_table
CREATE TABLE IF NOT EXISTS chat
(
    id BIGINT PRIMARY KEY
);