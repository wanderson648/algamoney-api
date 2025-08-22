CREATE TABLE lancamento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(100) NOT NULL,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE NOT NULL,               -- agora NOT NULL
    valor DECIMAL(10,2) NOT NULL,
    observacao VARCHAR(255),
    tipo VARCHAR(10) NOT NULL,                  -- RECEITA | DESPESA
    categoria_id BIGINT,
    pessoa_id BIGINT,                  -- novo relacionamento
    CONSTRAINT fk_lancamento_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(codigo),
    CONSTRAINT fk_lancamento_pessoa    FOREIGN KEY (pessoa_id)    REFERENCES pessoa(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
VALUES ('Supermercado junho',            '2025-07-02', '2025-07-02',  520.00, 'Compra do mês',            'DESPESA', 1, 1);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
VALUES ('Mensalidade faculdade julho',  '2025-07-15', '2025-07-16', 1200.00, 'Curso Engenharia',          'DESPESA', 4, 2);

-- Agosto (1ª quinzena)
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
VALUES ('Passagem de ônibus agosto',    '2025-08-03', '2025-08-03',    4.50, 'Transporte público diário', 'DESPESA', 2, 3);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
VALUES ('Salário agosto',               '2025-08-01', '2025-08-01', 6500.00, 'Recebimento da empresa',    'RECEITA', 1, 1);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
VALUES ('Consulta médica agosto',       '2025-08-10', '2025-08-12',  250.00, 'Clínica geral',             'DESPESA', 3, 4);

-- Agosto (2ª quinzena)
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
VALUES ('Cinema agosto',                '2025-08-18', '2025-08-18',   60.00, 'Sessão família',            'DESPESA', 5, 5);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
VALUES ('Freelancer agosto',            '2025-08-20', '2025-08-21', 1800.00, 'Projeto API REST',          'RECEITA', 4, 2);

-- Setembro (1ª quinzena)
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
VALUES ('Plano de saúde setembro',      '2025-09-05', '2025-09-06',  450.00, 'Convênio mensal',           'DESPESA', 3, 3);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
VALUES ('Curso online setembro',        '2025-09-08', '2025-09-09',  300.00, 'Spring Boot Avançado',      'DESPESA', 4, 4);

-- Setembro (2ª quinzena)
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, categoria_id, pessoa_id)
VALUES ('Show musical setembro',        '2025-09-25', '2025-09-26',  200.00, 'Ingresso pista premium',    'DESPESA', 5, 5);
