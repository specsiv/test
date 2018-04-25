drop table if exists basket;
drop table if exists orders;
drop table if exists customer;
drop table if exists items;

drop sequence if exists customerseq;
drop sequence if exists itemsseq;
drop sequence if exists orderseq;
drop sequence if exists basketseq;

create sequence customerseq minvalue 0 start with 4;
create sequence itemsseq minvalue 0 start with 4;
create sequence orderseq minvalue 0 start with 3;
create sequence basketseq minvalue 0 start with 5;

create table customer(
  id int not null
    constraint customer_pkey
    primary key,
  fio varchar(90) not null,
  vipStatus boolean not null
);

create table items(
  id int not null
    constraint items_pkey
    primary key,
  name varchar(90) not null,
  weight integer not null,
  price integer not null
);

create table orders(
  id int not null
    constraint orders_pkey
    primary key,
  customerId int not null
    constraint customerId_fkey
    references customer,
  date date not null
);

create table basket(
  id int not null
    constraint basket_pkey
    primary key,
  orderId int not null
    constraint orderId_fkey
    references orders,
  itemId int not null
    constraint itemId_fkey
    references items
);