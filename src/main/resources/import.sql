insert into ROLES (ROLE) values ('ROLE_ADMIN');
insert into ROLES (ROLE) values ('ROLE_GUEST');

insert into users (date_of_birth,email_address,firstname,lastname,password,terms_and_conditions)
Select '1982-06-22','moodleyk@gmail.com','Kemendran','Moodley','pdntspa',1
Where not exists(select * from users where email_address='moodleyk@gmail.com');

insert into USER_ROLES select id,1 from users where email_address='moodleyk@gmail.com';


