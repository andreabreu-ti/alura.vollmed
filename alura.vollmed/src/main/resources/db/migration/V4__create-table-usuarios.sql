create table usuarios(

    id bigint not null identity(1,1),
    login varchar(100) not null,
    senha varchar(255) not null,

    primary key(id)

);