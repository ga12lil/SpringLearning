-- liquibase formatted sql

-- changeset me:create_link_table
CREATE TABLE IF NOT EXISTS link
(
    id BIGINT PRIMARY KEY,
    url TEXT NOT NULL,
    updated_at TIMESTAMP DEFAULT now() NOT NULL
);