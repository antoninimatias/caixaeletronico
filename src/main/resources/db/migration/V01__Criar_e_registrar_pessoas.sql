CREATE TABLE pessoa(
	id_pessoa BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL, 
    cpf VARCHAR (20) NOT NULL,
    data_nascimento DATE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, cpf, data_nascimento) values ('Jair Bolsonaro', '240.969.100-58', '1960-06-10');
INSERT INTO pessoa (nome, cpf, data_nascimento) values ('João Amoedo', '855.586.970-67', '1954-06-10');
INSERT INTO pessoa (nome, cpf, data_nascimento) values ('Marina Silva', '385.842.660-11', '1950-06-10');
INSERT INTO pessoa (nome, cpf, data_nascimento) values ('Guilherme Boulos', '097.924.210-09', '1975-06-10');
INSERT INTO pessoa (nome, cpf, data_nascimento) values ('Ciro Gomes', '397.199.820-80', '1952-06-10');
