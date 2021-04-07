/*средняя цена устройства*/
select avg(d.price) as "Средняя цена устройств" from devices d;

/*средняя цена устройства для каждого человека*/
select p.name, avg(d.price) from devices_people dp
join people p on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name;

/*средняя цена устройства для каждого человека превышающая порог в 5000*/
select p.name, avg(d.price) from devices_people dp
join people p on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000.0;