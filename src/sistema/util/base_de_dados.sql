CREATE TABLE funcionario
(
  cod serial NOT NULL,
  nome character varying(50),
  rg character varying(10),
  cpf character varying(11),
  pis character varying(11),
  CONSTRAINT funcionario_pkey PRIMARY KEY (cod)
);

CREATE TABLE ponto
(
  nsr character varying(10),
  cod serial NOT NULL,
  data_horario timestamp without time zone,
  cod_funcionario integer,
  CONSTRAINT ponto_pkey PRIMARY KEY (cod),
  CONSTRAINT ponto_cod_funcionario_fkey FOREIGN KEY (cod_funcionario)
      REFERENCES funcionario (cod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE usuario
(
  cod numeric(6,0) NOT NULL,
  login character varying(20),
  senha character varying(200),
  CONSTRAINT usuario_pkey PRIMARY KEY (cod),
  CONSTRAINT usuario_login_key UNIQUE (login)
);

update funcionario set nome = Trim(nome);
update funcionario set rg = Trim(rg);
update funcionario set cpf = Trim(cpf);
update funcionario set pis = Trim(pis);
update funcionario set sexo = Trim(sexo);
update funcionario set dtnascimento = Trim(dtnascimento);
update funcionario set endereco = Trim(endereco);
update funcionario set complemento = Trim(complemento);
update funcionario set bairro = Trim(bairro);
update funcionario set cidade = Trim(cidade);
update funcionario set dtadmissao = Trim(dtadmissao);
update funcionario set funcao = Trim(funcao);

