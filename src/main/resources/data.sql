insert into subject (id, name, description, course)
	select 1, 'Matemáticas', 'Desc 1', 1 from dual where not exists (select 1 from subject where id = 1);

insert into subject (id, name, description, course)
	select 2, 'Física', 'Desc 2', 1 from dual where not exists (select 1 from subject where id = 2);
	
	
insert into rol (id, rol)
	select 1, 'ADMIN' from dual where not exists (select 1 from rol where id = 1);
insert into rol (id, rol)
	select 2, 'CONSULTA' from dual where not exists (select 1 from rol where id = 2);
/* pass = 1111 */
insert into user (username, firstname, surnames, password, rol_id)
	select 'admin', 'Javier', 'López López', '$2a$10$5xOe75pbLcAjp0TbVWaluunrSshgYdH82YNwGd.b0Os4hAWbIEkry', 1 from dual where not exists (select 1 from user where username = 'admin');
insert into user (username, firstname, surnames, password, rol_id)
	select 'consulta', 'user', 'test', '$2a$10$5xOe75pbLcAjp0TbVWaluunrSshgYdH82YNwGd.b0Os4hAWbIEkry', 2 from dual where not exists (select 1 from user where username = 'consulta');