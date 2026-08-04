INSERT INTO transactions
(id, account_id, funds, fee_factor, recipient, currency, transaction_status, timestamp)
VALUES
(1, 'ACC-001', '1000', '0.02', 'John', 'PLN', 'CREATED', '2026-08-04T18:00:00');

INSERT INTO transactions
(id, account_id, funds, fee_factor, recipient, currency, transaction_status, timestamp)
VALUES
(2, 'ACC-002', '250', '0.01', 'Alice', 'EUR', 'PENDING', '2026-08-04T18:10:00');