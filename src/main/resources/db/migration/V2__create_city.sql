create table city (
    id  bigserial not null,
    accent varchar(255),
    alternative_names varchar(255),
    capital boolean not null,
    ibge_id int8 not null,
    latitude float8 not null,
    longitude float8 not null,
    meso_region varchar(255),
    micro_region varchar(255),
    name varchar(100) not null,
    state_id int8,
    primary key (id),
    CONSTRAINT fk_state FOREIGN KEY (state_id) REFERENCES state(id)
);

