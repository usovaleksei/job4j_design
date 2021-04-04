create table orders(
	id serial primary key,
	name char(255),
	order_date date,
	cost int,
	pay_status bool
);
insert into orders(name, order_date, cost, pay_status) values('Alex', '4/4/2021', 7500, true);
select * from orders;
update orders set cost = 12000;
select * from orders;
delete from orders;
select * from orders;