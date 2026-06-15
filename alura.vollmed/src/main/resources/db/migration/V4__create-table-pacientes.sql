CREATE TABLE pacientes (
    id bigint NOT NULL IDENTITY(1,1),
    nome nvarchar(100) NOT NULL,
    email nvarchar(100) NOT NULL,
    cpf nvarchar(14) NOT NULL,
    logradouro nvarchar(100) NOT NULL,
    bairro nvarchar(100) NOT NULL,
    cep nvarchar(9) NOT NULL,
    complemento nvarchar(100),
    numero nvarchar(20),
    uf nchar(2) NOT NULL,
    cidade nvarchar(100) NOT NULL,
    telefone nvarchar(20) NOT NULL,
    ativo tinyint NOT NULL,

    CONSTRAINT PK_pacientes PRIMARY KEY (id),
    CONSTRAINT UN_pacientes_email UNIQUE (email),
    CONSTRAINT UN_pacientes_cpf UNIQUE (cpf)
);