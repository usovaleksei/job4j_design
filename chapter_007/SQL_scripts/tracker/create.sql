create table rules(
	id serial primary key,
	rule varchar(255)
);
create table roles(
	id serial primary key,
	role varchar(255)
);
create table rules_roles(
	id serial primary key,
	rule_id int references rules(id),
	role_id int references roles(id)
);
create table users(
	id serial primary key,
	name varchar(255),
	role_id int references roles(id)
);
create table states(
	id serial primary key,
	status varchar(255)
);
create table categories(
	id serial primary key,
	category varchar(255)
);
create table items(
    id serial primary key,
    item text,
    status_id int references states(id),
    category_id int references categories(id),
    user_id int references users(id)
);
create table comments(
	id serial primary key,
	comment text,
	item_id int references items(id)
);
create table attachs(
	id serial primary key,
	file_path varchar(255),
	item_id int references items(id)
);