CREATE TABLE medicos (
    id BIGINT NOT NULL IDENTITY(1,1),
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    crm VARCHAR(20) NOT NULL,
    especialidade VARCHAR(100) NOT NULL,
    
    -- Campos provenientes do @Embedded Endereco
    logradouro VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    complemento VARCHAR(100),
    numero VARCHAR(20),
    uf CHAR(2) NOT NULL,
    cidade VARCHAR(100) NOT NULL,

    CONSTRAINT pk_medicos PRIMARY KEY (id),
    CONSTRAINT uk_medicos_crm UNIQUE (crm),
    CONSTRAINT uk_medicos_email UNIQUE (email)
);