-- Insert 10,000 sample records into user_order table
DO $$
DECLARE
    i INT;
BEGIN
    FOR i IN 1..10000 LOOP
        INSERT INTO user_order (user_id, order_amount)
        VALUES (
            -- Using a modulo to limit user_ids to 1000 different users
            -- This creates a distribution where each user has multiple orders
            (i % 1000) + 1,
            -- Random order amount between 10.00 and 1000.00
            ROUND((RANDOM() * 990 + 10)::numeric, 2)
        );
    END LOOP;
END $$;

-- Create some sample user profiles for the users
DO $$
DECLARE
    i INT;
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO user_profile (user_id, name)
        VALUES (
            i,
            'User ' || i
        );
    END LOOP;
END $$;
