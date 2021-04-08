/*создание таблицы кузов*/
create table car_bodys(
	id serial primary key,
	name varchar(255)
);
/*создание таблицы двигатель*/
create table engines(
	id serial primary key,
	name varchar(255)
);
/*создание таблицы коробка передач*/
create table transmissions(
	id serial primary key,
	name varchar(255)
);
/*создание таблицы машина*/
create table cars(
	id serial primary key,
	body_id int references car_bodys(id),
	engine_id int references engines(id),
	trnsmission_id int references transmissions(id),
	vin int,
	name varchar(255)
/*заполнение таблиц*/
insert into car_bodys(name) values('sedan');
insert into car_bodys(name) values('hatchback');
insert into car_bodys(name) values('pikup');
insert into car_bodys(name) values('coupe');
insert into engines(name) values('gasoline 1.6');
insert into engines(name) values('gasoline 1.4');
insert into engines(name) values('diesel 1.6');
insert into engines(name) values('diesel 2.0');
insert into transmissions(name) values('manual');
insert into transmissions(name) values('automatic');
insert into cars(body_id, engine_id, transmission_id, vin) values(1, 1, 1, 75412, 'Mercedes');
insert into cars(body_id, engine_id, transmission_id, vin) values(1, 2, 2, 75413, 'BMV');
insert into cars(body_id, engine_id, transmission_id, vin) values(2, 1, 1, 75414, 'KIA');
insert into cars(body_id, engine_id, transmission_id, vin) values(3, 2, 2, 75415, 'Hyndai');
insert into cars(body_id, engine_id, transmission_id, vin) values(3, 3, 2, 75416, 'BMV');
insert into cars(body_id, engine_id, transmission_id, vin) values(4, 4, 1, 75417, 'Audi');
insert into cars(body_id, engine_id, transmission_id, vin) values(2, 3, 1, 75418, 'Chevrolet');

/*Вывод списка машин с привязанными деталями*/
select c.name as "Марка машины", c.vin, cb.name as "Кузов", e.name "Двигатель", t.name "Коробка передач"
from cars c
join car_bodys cb on c.body_id = cb.id
join engines e on engine_id = e.id
join transmissions t on transmission_id = t.id;

/*Вывод отдельно детали (1 деталь - 1 запрос), которые не используются в машине*/
select cb.name as "Тип кузова" from cars c
right join car_bodys cb on c.body_id = cb.id
where c.id is null;

select e.name as "Тип двигателя" from cars c
right join engines e on c.engine_id = e.id
where c.id is null;

select t.name as "Коробка передач" from cars c
right join transmissions t on c.transmission_id = t.id
where c.id is null;

