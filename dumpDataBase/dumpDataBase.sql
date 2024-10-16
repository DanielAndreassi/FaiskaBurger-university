--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2024-10-16 17:11:47

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4901 (class 1262 OID 16398)
-- Name: FaiskaBurgerDB; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "FaiskaBurgerDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';


ALTER DATABASE "FaiskaBurgerDB" OWNER TO postgres;

\connect "FaiskaBurgerDB"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4902 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 16400)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria (
    cat_id integer NOT NULL,
    cat_nome character varying(30) NOT NULL
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16399)
-- Name: categoria_cat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categoria_cat_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.categoria_cat_id_seq OWNER TO postgres;

--
-- TOC entry 4903 (class 0 OID 0)
-- Dependencies: 217
-- Name: categoria_cat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categoria_cat_id_seq OWNED BY public.categoria.cat_id;


--
-- TOC entry 227 (class 1259 OID 16455)
-- Name: empresa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.empresa (
    empresa_id integer NOT NULL,
    empresa_razao character varying(60),
    empresa_fantasia character varying(50),
    empresa_cnpj character varying(20) NOT NULL,
    empresa_cep character varying(10) NOT NULL,
    empresa_rua character varying(50),
    empresa_numero character varying(10),
    empresa_bairro character varying(30),
    empresa_cidade character varying(30),
    empresa_uf character varying(2),
    empresa_fone character varying(15),
    empresa_email character varying(100),
    empresa_valor_embalagem numeric(10,2)
);


ALTER TABLE public.empresa OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16454)
-- Name: empresa_empresa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.empresa_empresa_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.empresa_empresa_id_seq OWNER TO postgres;

--
-- TOC entry 4904 (class 0 OID 0)
-- Dependencies: 226
-- Name: empresa_empresa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.empresa_empresa_id_seq OWNED BY public.empresa.empresa_id;


--
-- TOC entry 225 (class 1259 OID 16439)
-- Name: item_pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_pedido (
    produto_id integer NOT NULL,
    pedido_id integer NOT NULL,
    item_qtde integer NOT NULL,
    item_valor numeric(10,2) NOT NULL
);


ALTER TABLE public.item_pedido OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16428)
-- Name: pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pedido (
    pedido_id integer NOT NULL,
    pedido_data date,
    pedido_cli_nome character varying(50),
    pedido_cli_fone character varying(20),
    "pedido_vTotal" numeric(10,2) NOT NULL,
    pedido_viagem character(1) NOT NULL,
    tipo_pagamento_id integer
);


ALTER TABLE public.pedido OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16427)
-- Name: pedido_pedido_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pedido_pedido_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pedido_pedido_id_seq OWNER TO postgres;

--
-- TOC entry 4905 (class 0 OID 0)
-- Dependencies: 223
-- Name: pedido_pedido_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pedido_pedido_id_seq OWNED BY public.pedido.pedido_id;


--
-- TOC entry 222 (class 1259 OID 16414)
-- Name: produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produto (
    produto_id integer NOT NULL,
    produto_nome character varying(30) NOT NULL,
    produto_desc text,
    produto_valor numeric(10,2) NOT NULL,
    produto_foto bytea,
    categoria_id integer
);


ALTER TABLE public.produto OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16413)
-- Name: produto_produto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.produto_produto_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.produto_produto_id_seq OWNER TO postgres;

--
-- TOC entry 4906 (class 0 OID 0)
-- Dependencies: 221
-- Name: produto_produto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.produto_produto_id_seq OWNED BY public.produto.produto_id;


--
-- TOC entry 220 (class 1259 OID 16407)
-- Name: tipo_pagamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_pagamento (
    tipo_pagamento_id integer NOT NULL,
    tipo_pagamento_nome character varying(30) NOT NULL
);


ALTER TABLE public.tipo_pagamento OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16406)
-- Name: tipo_pagamento_tipo_pagamento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_pagamento_tipo_pagamento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tipo_pagamento_tipo_pagamento_id_seq OWNER TO postgres;

--
-- TOC entry 4907 (class 0 OID 0)
-- Dependencies: 219
-- Name: tipo_pagamento_tipo_pagamento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_pagamento_tipo_pagamento_id_seq OWNED BY public.tipo_pagamento.tipo_pagamento_id;


--
-- TOC entry 4719 (class 2604 OID 16403)
-- Name: categoria cat_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria ALTER COLUMN cat_id SET DEFAULT nextval('public.categoria_cat_id_seq'::regclass);


--
-- TOC entry 4723 (class 2604 OID 16458)
-- Name: empresa empresa_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresa ALTER COLUMN empresa_id SET DEFAULT nextval('public.empresa_empresa_id_seq'::regclass);


--
-- TOC entry 4722 (class 2604 OID 16431)
-- Name: pedido pedido_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido ALTER COLUMN pedido_id SET DEFAULT nextval('public.pedido_pedido_id_seq'::regclass);


--
-- TOC entry 4721 (class 2604 OID 16417)
-- Name: produto produto_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto ALTER COLUMN produto_id SET DEFAULT nextval('public.produto_produto_id_seq'::regclass);


--
-- TOC entry 4720 (class 2604 OID 16410)
-- Name: tipo_pagamento tipo_pagamento_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_pagamento ALTER COLUMN tipo_pagamento_id SET DEFAULT nextval('public.tipo_pagamento_tipo_pagamento_id_seq'::regclass);


--
-- TOC entry 4886 (class 0 OID 16400)
-- Dependencies: 218
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.categoria VALUES (1, 'Hamburguer');
INSERT INTO public.categoria VALUES (2, 'Refrigerante');
INSERT INTO public.categoria VALUES (4, 'lanche do bom');


--
-- TOC entry 4895 (class 0 OID 16455)
-- Dependencies: 227
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4893 (class 0 OID 16439)
-- Dependencies: 225
-- Data for Name: item_pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4892 (class 0 OID 16428)
-- Dependencies: 224
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4890 (class 0 OID 16414)
-- Dependencies: 222
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.produto VALUES (1, 'coca cola', 'refri gelado', 13.12, NULL, 1);
INSERT INTO public.produto VALUES (3, 'x-tudo', 'lanche com tudao', 30.00, NULL, 4);


--
-- TOC entry 4888 (class 0 OID 16407)
-- Dependencies: 220
-- Data for Name: tipo_pagamento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4908 (class 0 OID 0)
-- Dependencies: 217
-- Name: categoria_cat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categoria_cat_id_seq', 4, true);


--
-- TOC entry 4909 (class 0 OID 0)
-- Dependencies: 226
-- Name: empresa_empresa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.empresa_empresa_id_seq', 1, false);


--
-- TOC entry 4910 (class 0 OID 0)
-- Dependencies: 223
-- Name: pedido_pedido_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pedido_pedido_id_seq', 1, false);


--
-- TOC entry 4911 (class 0 OID 0)
-- Dependencies: 221
-- Name: produto_produto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.produto_produto_id_seq', 3, true);


--
-- TOC entry 4912 (class 0 OID 0)
-- Dependencies: 219
-- Name: tipo_pagamento_tipo_pagamento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_pagamento_tipo_pagamento_id_seq', 1, false);


--
-- TOC entry 4725 (class 2606 OID 16405)
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (cat_id);


--
-- TOC entry 4735 (class 2606 OID 16460)
-- Name: empresa empresa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresa
    ADD CONSTRAINT empresa_pkey PRIMARY KEY (empresa_id);


--
-- TOC entry 4733 (class 2606 OID 16443)
-- Name: item_pedido item_pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT item_pedido_pkey PRIMARY KEY (produto_id, pedido_id);


--
-- TOC entry 4731 (class 2606 OID 16433)
-- Name: pedido pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (pedido_id);


--
-- TOC entry 4729 (class 2606 OID 16421)
-- Name: produto produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (produto_id);


--
-- TOC entry 4727 (class 2606 OID 16412)
-- Name: tipo_pagamento tipo_pagamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_pagamento
    ADD CONSTRAINT tipo_pagamento_pkey PRIMARY KEY (tipo_pagamento_id);


--
-- TOC entry 4736 (class 2606 OID 16422)
-- Name: produto FK_CAT_ID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT "FK_CAT_ID" FOREIGN KEY (categoria_id) REFERENCES public.categoria(cat_id);


--
-- TOC entry 4738 (class 2606 OID 16449)
-- Name: item_pedido FK_PEDIDO_ID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT "FK_PEDIDO_ID" FOREIGN KEY (pedido_id) REFERENCES public.pedido(pedido_id) NOT VALID;


--
-- TOC entry 4739 (class 2606 OID 16444)
-- Name: item_pedido FK_PRODUTO_ID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT "FK_PRODUTO_ID" FOREIGN KEY (produto_id) REFERENCES public.produto(produto_id);


--
-- TOC entry 4737 (class 2606 OID 16434)
-- Name: pedido FK_TIPO_PAGAMENTO_ID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT "FK_TIPO_PAGAMENTO_ID" FOREIGN KEY (tipo_pagamento_id) REFERENCES public.tipo_pagamento(tipo_pagamento_id);


-- Completed on 2024-10-16 17:11:47

--
-- PostgreSQL database dump complete
--

