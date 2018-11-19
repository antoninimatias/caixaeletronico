CREATE TABLE transacao(
	id_transacao BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	id_conta BIGINT(20) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL, 
    data_transacao DATE NOT NULL,
    FOREIGN KEY (id_conta) REFERENCES conta(id_conta)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
