-- Enable UUID generation
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Drop and recreate contacts table
DROP TABLE IF EXISTS contacts;

CREATE TABLE contacts (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                          name VARCHAR(255),
                          email VARCHAR(255)
);

-- Drop and recreate outbox_events table
DROP TABLE IF EXISTS outbox_events;

CREATE TABLE outbox_events (
                               id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                               aggregate_type VARCHAR(255),
                               aggregate_id VARCHAR(255),
                               event_type VARCHAR(255),
                               payload JSONB,
                               created_at TIMESTAMP DEFAULT NOW(),
                               processed BOOLEAN DEFAULT FALSE
);
