CREATE TYPE public.movement_entry_type AS ENUM (
    'DEBIT',
    'CREDIT'
);

CREATE TABLE public.bank_account (
    id bigint not null,
    holder_name text not null,
    bank character varying(255) not null,
    branch character varying(255) not null,
    number character varying(255) not null,
    tx_id character varying(255) not null,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null
);

CREATE TABLE public.movement (
    id bigint not null,
    date timestamp with time zone not null,
    description text not null,
    origin bigint not null,
    destination bigint not null,
    type movement_entry_type not null,
    value bigint not null,
    reference_id text not null,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null
);

ALTER TABLE public.bank_account
    ADD CONSTRAINT bank_account_id_pkey PRIMARY KEY (id);

ALTER TABLE public.movement
    ADD CONSTRAINT movement_id_pkey PRIMARY KEY (id);

ALTER TABLE public.movement
    ADD CONSTRAINT movement_origin_fkey FOREIGN KEY (origin) REFERENCES public.bank_account(id);

ALTER TABLE public.movement
    ADD CONSTRAINT movement_destination_fkey FOREIGN KEY (destination) REFERENCES public.bank_account(id);