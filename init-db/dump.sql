create type type_room as enum ('ECONOMY', 'STANDARD', 'LUXURY');
CREATE TABLE rooms
(
    id     serial primary key,
    number integer   NOT NULL,
    price  integer   NOT NULL,
    type   type_room NOT NULL
);

INSERT INTO rooms (number, type, price)
VALUES (1, 'ECONOMY', 3000),
       (2, 'ECONOMY', 3000),
       (3, 'ECONOMY', 3000),
       (4, 'STANDARD', 5000),
       (5, 'STANDARD', 5000),
       (6, 'LUXURY', 10000);
CREATE TABLE guests
(
    id              serial primary key,
    name            varchar(500) DEFAULT NULL,
    surname         varchar(500) DEFAULT NULL,
    passportnumber  varchar(500) DEFAULT NULL,
    roomid         integer      DEFAULT NULL,
    firstdateofstay date         DEFAULT NULL,
    lastdateofstay  date         DEFAULT NULL,
    FOREIGN KEY (roomid) REFERENCES rooms (Id)
);



