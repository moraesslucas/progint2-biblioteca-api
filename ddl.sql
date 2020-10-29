CREATE TABLE livro (
	id serial PRIMARY KEY,
	isbn VARCHAR ( 50 ) NOT NULL,
	nome VARCHAR ( 255 ) NOT NULL,
	autor VARCHAR ( 255 ) NOT NULL,
	editora VARCHAR ( 255 ) NOT NULL,
	ano_publicacao INTEGER NOT NULL
);

CREATE TABLE cliente (
	matricula serial PRIMARY KEY,
	nome VARCHAR ( 255 ) NOT NULL,
	email VARCHAR ( 255 ) NOT NULL,
	telefone VARCHAR ( 20 ) NOT NULL
);

CREATE TABLE emprestimo (
	id serial PRIMARY KEY,
	data_emprestimo DATE NOT NULL,
	data_prevista_entrega DATE NOT NULL,
	data_efetiva_entrega DATE,
	id_livro integer REFERENCES livro (id),
	matricula_cliente integer REFERENCES cliente (matricula)
);

CREATE TABLE roles (
	id serial PRIMARY KEY,
	nome VARCHAR ( 255 ) NOT NULL
);

CREATE TABLE funcionario (
	matricula serial PRIMARY KEY,
	nome VARCHAR ( 255 ) NOT NULL,
	telefone VARCHAR ( 255 ) NOT NULL,
	senha VARCHAR ( 120 ) NOT NULL
);

CREATE TABLE funcionario_Role (
	id_funcionario integer REFERENCES funcionario (matricula),
	id_role integer REFERENCES roles (id)
);