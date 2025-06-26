CREATE EXTENSION IF NOT EXISTS citus;
-- Uncomment below if you add worker nodes
SELECT *
from master_add_node('worker', 5432);
-- Create user_order table
CREATE TABLE user_order
(
    id           BIGSERIAL,
    user_id      BIGINT NOT NULL,
    order_amount NUMERIC,
    PRIMARY KEY (user_id, id)
);
CREATE TABLE user_profile
(
    id      BIGSERIAL,
    user_id BIGINT NOT NULL,
    name    TEXT,
    PRIMARY KEY (user_id, id)
);

SELECT create_distributed_table('user_order', 'user_id');
SELECT create_distributed_table('user_profile', 'user_id');
