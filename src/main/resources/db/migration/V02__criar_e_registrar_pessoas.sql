CREATE TABLE IF NOT EXISTS pessoa (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    ativo TINYINT(1) NOT NULL DEFAULT 1,
    logradouro  VARCHAR(100),
    numero      VARCHAR(10),
    complemento VARCHAR(50),
    bairro      VARCHAR(60),
    cep         VARCHAR(9),
    cidade      VARCHAR(60),
    estado      CHAR(2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES
('Ana Souza',        1, 'Rua das Flores',      '123', 'Apto 45',  'Centro',        '01010-000', 'São Paulo',      'SP'),
('Bruno Lima',       1, 'Av. Brasil',          '2000', NULL,      'Jardim América','22041-001', 'Rio de Janeiro', 'RJ'),
('Carla Mendes',     0, 'Rua das Acácias',     '55',  'Casa 2',   'Vila Nova',     '30110-012', 'Belo Horizonte', 'MG'),
('Diego Ferraz',     0, 'Praça da Sé',         '1',   NULL,       'Sé',            '01001-000', 'São Paulo',      'SP'),
('Elisa Andrade',    1, 'Rua João Pessoa',     '789', 'Bloco B',  'Centro',        '80010-000', 'Curitiba',       'PR');
