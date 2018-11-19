CREATE TABLE conta(
	id_conta BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	id_pessoa BIGINT(20) NOT NULL,
    saldo DECIMAL(10, 2) NOT NULL, 
    limite_saque_diario DECIMAL (10, 2) NOT NULL,
    flag_ativo BOOLEAN NOT NULL,
    tipo_conta VARCHAR (20) NOT NULL,
    data_criacao DATE NOT NULL,
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO conta (id_pessoa, saldo, limite_saque_diario, flag_ativo, tipo_conta, data_criacao) values (1, 3500.00, 1500.00, TRUE, 'CORRENTE', '2010-06-10');
INSERT INTO conta (id_pessoa, saldo, limite_saque_diario, flag_ativo, tipo_conta, data_criacao) values (2, 4500.00, 1500.00, FALSE, 'POUPANÇA', '1985-06-10');
INSERT INTO conta (id_pessoa, saldo, limite_saque_diario, flag_ativo, tipo_conta, data_criacao) values (3, 5500.00, 1500.00, TRUE, 'CORRENTE', '1990-06-10');
INSERT INTO conta (id_pessoa, saldo, limite_saque_diario, flag_ativo, tipo_conta, data_criacao) values (4, 6500.00, 1500.00, FALSE, 'POUPANÇA', '2000-06-10');
INSERT INTO conta (id_pessoa, saldo, limite_saque_diario, flag_ativo, tipo_conta, data_criacao) values (5, 8500.00, 1500.00, TRUE, 'CORRENTE', '2011-06-10');

