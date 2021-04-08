/*создание таблицы департаменты*/
create table departments(
	id serial primary key,
	name varchar(255)
);
/*создание таблицы сотрудники*/
create table employees(
	id serial primary key,
	name varchar(255),
	depart_id int references departments(id)
);
/*заполнение таблицы департаменты*/
insert into departments(name) values('sales');
insert into departments(name) values('marketing');
insert into departments(name) values('IT');
insert into departments(name) values('logistic');
/*заполнение таблицы сотрудники*/
insert into employees(name, depart_id) values('Igor', 1);
insert into employees(name, depart_id) values('Sergei', 1);
insert into employees(name, depart_id) values('Ivan', 1);
insert into employees(name, depart_id) values('Dmitrii', 1);
insert into employees(name, depart_id) values('Alex', 3);
insert into employees(name, depart_id) values('Roman', 3);
insert into employees(name, depart_id) values('Platon', null);
insert into employees(name, depart_id) values('German', null);
/*запрос с left join*/
select * from employees e left join departments d on e.depart_id = d.id;
/*запрос с right join*/
select * from employees e right join departments d on e.depart_id = d.id;
/*запрос с full join*/
select * from employees e full join departments d on e.depart_id = d.id;
/*запрос с cross join, результат - декартово множество (все пары элементов)*/
select * from employees e cross join departments d
/*вывод департаментов, в которых нет работников*/
select * from departments d left join employees e on d.id = e.depart_id where e.name is null;
/*запросы с left и right join, дающие один результат: вывод всех сотрудников, которые принадлежат какому-либо департаменту*/
select * from employees e left join departments d on e.depart_id = d.id where d.name is not null;
select * from employees e right join departments d on e.depart_id = d.id where e.name is not null;
/*вывод всех возможных разнополых пар*/
select * from teens t1 cross join teens t2 where t1.gender_id != t2.gender_id;