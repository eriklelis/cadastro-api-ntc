CREATE TABLE usuario
(
    id bigserial,
    guid character varying(36) COLLATE pg_catalog."default",
    created_at timestamp without time zone,
    username character varying(100) COLLATE pg_catalog."default",
    client_id character varying(36) COLLATE pg_catalog."default",
    CONSTRAINT usuario_pkey PRIMARY KEY (id),
    CONSTRAINT usuario_guid_key UNIQUE (guid)
)

CREATE TABLE cliente
(
    id bigserial,
    guid character varying(36) COLLATE pg_catalog."default",
    created_at timestamp without time zone,
    nome character varying(100) COLLATE pg_catalog."default",
    telefone character varying(15) COLLATE pg_catalog."default",
    email character varying(100) COLLATE pg_catalog."default",
    usuario_id bigint,
    CONSTRAINT cliente_pkey PRIMARY KEY (id),
    CONSTRAINT cliente_guid_key UNIQUE (guid),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)



CREATE TABLE funcionario
(
    id bigserial,
    guid character varying(36) COLLATE pg_catalog."default",
    created_at timestamp without time zone,
    nome character varying(100) COLLATE pg_catalog."default",
    usuario_id bigint,
    CONSTRAINT funcionario_pkey PRIMARY KEY (id),
    CONSTRAINT funcionario_guid_key UNIQUE (guid),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)










CREATE TABLE hotel
(
    id bigserial,
    guid character varying(36) COLLATE pg_catalog."default",
    created_at timestamp without time zone,
    nome character varying(100) COLLATE pg_catalog."default",
    quantidade_quartos int,
	avalicao_atual int,
	pontos int,
	qtd_avalicao,
	CONSTRAINT usuario_pkey PRIMARY KEY (id),
    CONSTRAINT usuario_guid_key UNIQUE (guid)
)


CREATE TABLE quarto
(
    id bigserial,
    guid character varying(36) COLLATE pg_catalog."default",
    created_at timestamp without time zone,
    numero character varying(100) COLLATE pg_catalog."default",
    quantidade_quartos int,
	avalicao_atual int,
	pontos int,
	qtd_avalicao,
	CONSTRAINT usuario_pkey PRIMARY KEY (id),
    CONSTRAINT usuario_guid_key UNIQUE (guid)
)



CREATE TABLE reserva
(
    id bigserial,
    guid character varying(36) COLLATE pg_catalog."default",
    created_at timestamp without time zone,
    numero character varying(100) COLLATE pg_catalog."default",
    quantidade_quartos int,
	avalicao_atual int,
	pontos int,
	qtd_avalicao,
	CONSTRAINT usuario_pkey PRIMARY KEY (id),
    CONSTRAINT usuario_guid_key UNIQUE (guid)
)













