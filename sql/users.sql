use aurora;
create table users(
username varchar(50) not null primary key,
password varchar(500) not null,
enabled boolean not null);

create table authorities (
id int(11) not null primary key auto_increment,
username varchar(50) not null,
authority varchar(50) not null,
constraint fk_authorities_users foreign key(username) references users(username));

create unique index ix_auth_username on authorities (username,authority);

INSERT INTO `aurora`.`users` (`username`, `password`, `enabled`) VALUES ('admin', 'spring@123', '1')
INSERT INTO `aurora`.`users` (`username`, `password`, `enabled`) VALUES ('user', 'spring@123', '1')

INSERT INTO `aurora`.`authorities` (`username`, `authority`) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO `aurora`.`authorities` (`username`, `authority`) VALUES ('user', 'ROLE_USER');
