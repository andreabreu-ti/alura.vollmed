ALTER TABLE medicos ADD ativo tinyint;
GO
UPDATE medicos SET ativo = 1;