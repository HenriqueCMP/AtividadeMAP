create table produto(
	codigo serial primary key,
	nome varchar(60),
	preco numeric,
	codigo_especificacao integer references especificacao(codigo) on delete cascade
);

create table especificacao(
	codigo serial primary key,
	fabricante varchar(60),
	cor varchar(60),
	sistema varchar(60),
	detalhes varchar(60)
);