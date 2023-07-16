CREATE TABLE public.bank_account (
    id bigint not null,
    holder_name text not null,
    bank character varying(255) not null,
    branch character varying(255) not null,
    number character varying(255) not null,
    tx_id character varying(255) not null,
    external boolean not null,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null
);

CREATE TABLE public.bank_account_closing_balance (
    id bigint not null,
    bank_account bigint not null,
    date timestamp with time zone not null,
    balance bigint not null,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null
);

CREATE TABLE public.bank_balance (
    id bigint not null,
    date timestamp with time zone not null,
    balance bigint not null,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null
);

CREATE TABLE public.movement (
    id bigint not null,
    date timestamp with time zone not null,
    description text not null,
    bank_account bigint not null,
    counterparty_bank_account bigint not null,
    type varchar(255) not null,
    transaction_type varchar(255) not null,
    value bigint not null check ( value > 0 ),
    balance bigint not null,
    reference_id text not null,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null
);

ALTER TABLE public.bank_account
    ADD CONSTRAINT bank_account_id_pkey PRIMARY KEY (id);

CREATE INDEX idx_bank_account_tx_id ON public.bank_account USING btree (tx_id);

ALTER TABLE public.bank_account_closing_balance
    ADD CONSTRAINT bank_account_closing_balance_id_pkey PRIMARY KEY (id);

ALTER TABLE public.bank_account_closing_balance
    ADD CONSTRAINT bank_account_closing_balance_bank_account_fkey
        FOREIGN KEY (bank_account) REFERENCES public.bank_account(id);

ALTER TABLE public.bank_balance
    ADD CONSTRAINT bank_balance_id_pkey PRIMARY KEY (id);

ALTER TABLE public.movement
    ADD CONSTRAINT movement_id_pkey PRIMARY KEY (id);

CREATE SEQUENCE movement_id_seq_gen AS bigint INCREMENT BY 1 START WITH 1;

ALTER TABLE public.movement
    ADD CONSTRAINT movement_bank_account_fkey FOREIGN KEY (bank_account) REFERENCES public.bank_account(id);

ALTER TABLE public.movement
    ADD CONSTRAINT movement_counterparty_bank_account_fkey FOREIGN KEY (counterparty_bank_account) REFERENCES public.bank_account(id);

ALTER TABLE public.movement
    ADD CONSTRAINT unique_movement_reference_id UNIQUE (reference_id);