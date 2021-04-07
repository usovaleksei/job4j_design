/*получение всех продуктов с типом СЫР (type_id=2)*/
select * from products p join type t on t.id = p.type_id where t.name = 'СЫР';
/*получение продуктов, в имени которых есть слово 'мороженное'*/
select * from products where name like '%мороженное%';
/*все продукты, срок годности которых заканчивается в следующем месяце*/
select * from products where expired_date < current_date + interval '1 month';
/*самый дорогой продукт*/
select name as "Наименование", expired_date as "Срок годности", price as "Цена" from products
where price = (select max(price) from products);
/*для каждого типа количество продуктов к нему принадлежащих*/
select t.name as "Имя типа", count(t.name) as "Количество, шт." from products p
join type t on t.id = p.type_id
group by t.name;
/*получение всех продуктов с типом "СЫР" и "МОЛОКО"*/
select * from products p
join type t on t.id = p.type_id
where t.name = 'СЫР' or t.name = 'МОЛОКО';
/*вывести тип продуктов, которых осталось меньше 10 штук*/
select t.name from products p
join type t on t.id = p.type_id
group by t.name
having count(p.id) < 10;
/*вывести все продукты и их тип.*/
select p.name, t.name from products p
join type t on t.id = p.type_id;

