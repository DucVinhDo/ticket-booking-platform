CREATE TABLE events (
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(255) NOT NULL,

    description TEXT,

    venue VARCHAR(255) NOT NULL,

    start_time TIMESTAMP NOT NULL,

    end_time TIMESTAMP NOT NULL,

    status VARCHAR(30) NOT NULL DEFAULT 'DRAFT',

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_events_time
        CHECK (end_time > start_time)
);


CREATE TABLE ticket_types (
    id BIGSERIAL PRIMARY KEY,

    event_id BIGINT NOT NULL,

    name VARCHAR(100) NOT NULL,

    description TEXT,

    price NUMERIC(19, 2) NOT NULL,

    total_quantity INTEGER NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_ticket_types_event
        FOREIGN KEY (event_id)
        REFERENCES events(id)
        ON DELETE CASCADE,

    CONSTRAINT chk_ticket_types_price
        CHECK (price >= 0),

    CONSTRAINT chk_ticket_types_quantity
        CHECK (total_quantity > 0),

    CONSTRAINT uq_ticket_types_event_name
        UNIQUE (event_id, name)
);