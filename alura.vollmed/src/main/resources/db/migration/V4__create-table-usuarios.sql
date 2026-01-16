create table usuarios(

    id BIGINT NOT NULL IDENTITY(1,1),
    login varchar(100) not null,
    senha varchar(255) not null,

    CONSTRAINT pk_usuarios PRIMARY KEY (id),
);