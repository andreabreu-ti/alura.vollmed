CREATE TABLE usuarios (
    id bigint NOT NULL IDENTITY(1,1),
    login nvarchar(100) NOT NULL,
    senha nvarchar(255) NOT NULL,

    CONSTRAINT PK_usuarios PRIMARY KEY (id)
);