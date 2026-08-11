CREATE SCHEMA IF NOT EXISTS trans;

SET search_path TO trans;


CREATE TABLE users (
    user_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    surname VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE accounts (
    account_id BIGSERIAL PRIMARY KEY,
    funds NUMERIC(19,2) NOT NULL DEFAULT 0,
    blocked_funds NUMERIC(19,2) NOT NULL DEFAULT 0,
    currency CHAR(3) NOT NULL,
    user_id BIGINT NOT NULL,

    CONSTRAINT fk_accounts_user
        FOREIGN KEY (user_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE,

    CONSTRAINT uq_user_currency
        UNIQUE (user_id, currency),

    CONSTRAINT chk_accounts_funds
        CHECK (funds >= 0),

    CONSTRAINT chk_accounts_blocked_funds
        CHECK (blocked_funds >= 0)
);

CREATE TABLE transactions (
    trans_id UUID PRIMARY KEY,
    account_id BIGINT NOT NULL,
    recipient VARCHAR(255) NOT NULL,
    destination VARCHAR(255) NOT NULL,
    amount NUMERIC(19,2) NOT NULL,
    fee NUMERIC(19,2) NOT NULL,
    fee_factor NUMERIC(10,4) NOT NULL,
    currency CHAR(3) NOT NULL,
    transaction_status VARCHAR(30) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_transactions_account
        FOREIGN KEY (account_id)
        REFERENCES accounts(account_id)
);