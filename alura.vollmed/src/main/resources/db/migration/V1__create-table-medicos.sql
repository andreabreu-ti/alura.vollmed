CREATE TABLE medicos (
    id BIGINT IDENTITY(1,1) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    crm VARCHAR(6) NOT NULL,
    especialidade VARCHAR(100) NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    complemento VARCHAR(100),
    numero VARCHAR(20),
    uf CHAR(2) NOT NULL,
    cidade VARCHAR(100) NOT NULL,

    CONSTRAINT PK_medicos PRIMARY KEY (id),
    CONSTRAINT UQ_medicos_email UNIQUE (email),
    CONSTRAINT UQ_medicos_crm UNIQUE (crm)
);