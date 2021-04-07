/*создание таблицы сотрудники*/
create table employees(
	id serial primary key,
	name varchar(255),
	age int,
	passport int
);
/*создание таблицы корпоративные телефоны*/
create table corp_phones(
	id serial primary key,
	model varchar(255),
	serial_number int,
	employee_id int references employees(id);
);
/*заполнение таблицы сотрудники*/
insert into employees(name, age, passport) values('Alex', 35, 4508565);
insert into employees(name, age, passport) values('Roman', 36, 4508755);
insert into employees(name, age, passport) values('Sergey', 28, 4207565);
/*заполнение таблицы корпоративные телефоны*/
insert into corp_phones(model, employee_id) values('Samsung', 2);
insert into corp_phones(model, employee_id) values('Iphone', 2);
insert into corp_phones(model, employee_id) values('Huawei', 1);
/*запросы на объединение таблиц с помощью INNER JOIN и с применением Альясов*/
select name, model from employees e inner join corp_phones p on e.id = p.employee_id;
select name as Имя, model as Модель from employees e inner join corp_phones p on e.id = p.id;
select name, model from employees inner join corp_phones p on employees.id = p.employee_id;