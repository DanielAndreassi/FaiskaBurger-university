PGDMP      7            	    |            FaiskaBurgerDB    17.0    17.0 0    "           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            #           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            $           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            %           1262    16398    FaiskaBurgerDB    DATABASE     �   CREATE DATABASE "FaiskaBurgerDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
     DROP DATABASE "FaiskaBurgerDB";
                     postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                     pg_database_owner    false            &           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                        pg_database_owner    false    4            �            1259    16400 	   categoria    TABLE     l   CREATE TABLE public.categoria (
    cat_id integer NOT NULL,
    cat_nome character varying(30) NOT NULL
);
    DROP TABLE public.categoria;
       public         heap r       postgres    false    4            �            1259    16399    categoria_cat_id_seq    SEQUENCE     �   CREATE SEQUENCE public.categoria_cat_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.categoria_cat_id_seq;
       public               postgres    false    218    4            '           0    0    categoria_cat_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.categoria_cat_id_seq OWNED BY public.categoria.cat_id;
          public               postgres    false    217            �            1259    16455    empresa    TABLE     >  CREATE TABLE public.empresa (
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
    DROP TABLE public.empresa;
       public         heap r       postgres    false    4            �            1259    16454    empresa_empresa_id_seq    SEQUENCE     �   CREATE SEQUENCE public.empresa_empresa_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.empresa_empresa_id_seq;
       public               postgres    false    4    227            (           0    0    empresa_empresa_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.empresa_empresa_id_seq OWNED BY public.empresa.empresa_id;
          public               postgres    false    226            �            1259    16439    item_pedido    TABLE     �   CREATE TABLE public.item_pedido (
    produto_id integer NOT NULL,
    pedido_id integer NOT NULL,
    item_qtde integer NOT NULL,
    item_valor numeric(10,2) NOT NULL
);
    DROP TABLE public.item_pedido;
       public         heap r       postgres    false    4            �            1259    16428    pedido    TABLE       CREATE TABLE public.pedido (
    pedido_id integer NOT NULL,
    pedido_data date,
    pedido_cli_nome character varying(50),
    pedido_cli_fone character varying(20),
    "pedido_vTotal" numeric(10,2) NOT NULL,
    pedido_viagem character(1) NOT NULL,
    tipo_pagamento_id integer
);
    DROP TABLE public.pedido;
       public         heap r       postgres    false    4            �            1259    16427    pedido_pedido_id_seq    SEQUENCE     �   CREATE SEQUENCE public.pedido_pedido_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.pedido_pedido_id_seq;
       public               postgres    false    224    4            )           0    0    pedido_pedido_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.pedido_pedido_id_seq OWNED BY public.pedido.pedido_id;
          public               postgres    false    223            �            1259    16414    produto    TABLE     �   CREATE TABLE public.produto (
    produto_id integer NOT NULL,
    produto_nome character varying(30) NOT NULL,
    produto_desc text,
    produto_valor numeric(10,2) NOT NULL,
    produto_foto bytea,
    categoria_id integer
);
    DROP TABLE public.produto;
       public         heap r       postgres    false    4            �            1259    16413    produto_produto_id_seq    SEQUENCE     �   CREATE SEQUENCE public.produto_produto_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.produto_produto_id_seq;
       public               postgres    false    222    4            *           0    0    produto_produto_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.produto_produto_id_seq OWNED BY public.produto.produto_id;
          public               postgres    false    221            �            1259    16407    tipo_pagamento    TABLE     �   CREATE TABLE public.tipo_pagamento (
    tipo_pagamento_id integer NOT NULL,
    tipo_pagamento_nome character varying(30) NOT NULL
);
 "   DROP TABLE public.tipo_pagamento;
       public         heap r       postgres    false    4            �            1259    16406 $   tipo_pagamento_tipo_pagamento_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tipo_pagamento_tipo_pagamento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE public.tipo_pagamento_tipo_pagamento_id_seq;
       public               postgres    false    4    220            +           0    0 $   tipo_pagamento_tipo_pagamento_id_seq    SEQUENCE OWNED BY     m   ALTER SEQUENCE public.tipo_pagamento_tipo_pagamento_id_seq OWNED BY public.tipo_pagamento.tipo_pagamento_id;
          public               postgres    false    219            o           2604    16403    categoria cat_id    DEFAULT     t   ALTER TABLE ONLY public.categoria ALTER COLUMN cat_id SET DEFAULT nextval('public.categoria_cat_id_seq'::regclass);
 ?   ALTER TABLE public.categoria ALTER COLUMN cat_id DROP DEFAULT;
       public               postgres    false    218    217    218            s           2604    16458    empresa empresa_id    DEFAULT     x   ALTER TABLE ONLY public.empresa ALTER COLUMN empresa_id SET DEFAULT nextval('public.empresa_empresa_id_seq'::regclass);
 A   ALTER TABLE public.empresa ALTER COLUMN empresa_id DROP DEFAULT;
       public               postgres    false    227    226    227            r           2604    16431    pedido pedido_id    DEFAULT     t   ALTER TABLE ONLY public.pedido ALTER COLUMN pedido_id SET DEFAULT nextval('public.pedido_pedido_id_seq'::regclass);
 ?   ALTER TABLE public.pedido ALTER COLUMN pedido_id DROP DEFAULT;
       public               postgres    false    223    224    224            q           2604    16417    produto produto_id    DEFAULT     x   ALTER TABLE ONLY public.produto ALTER COLUMN produto_id SET DEFAULT nextval('public.produto_produto_id_seq'::regclass);
 A   ALTER TABLE public.produto ALTER COLUMN produto_id DROP DEFAULT;
       public               postgres    false    221    222    222            p           2604    16410     tipo_pagamento tipo_pagamento_id    DEFAULT     �   ALTER TABLE ONLY public.tipo_pagamento ALTER COLUMN tipo_pagamento_id SET DEFAULT nextval('public.tipo_pagamento_tipo_pagamento_id_seq'::regclass);
 O   ALTER TABLE public.tipo_pagamento ALTER COLUMN tipo_pagamento_id DROP DEFAULT;
       public               postgres    false    220    219    220                      0    16400 	   categoria 
   TABLE DATA                 public               postgres    false    218   �7                 0    16455    empresa 
   TABLE DATA                 public               postgres    false    227   �7                 0    16439    item_pedido 
   TABLE DATA                 public               postgres    false    225   �7                 0    16428    pedido 
   TABLE DATA                 public               postgres    false    224   �7                 0    16414    produto 
   TABLE DATA                 public               postgres    false    222   �7                 0    16407    tipo_pagamento 
   TABLE DATA                 public               postgres    false    220   8       ,           0    0    categoria_cat_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.categoria_cat_id_seq', 1, false);
          public               postgres    false    217            -           0    0    empresa_empresa_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.empresa_empresa_id_seq', 1, false);
          public               postgres    false    226            .           0    0    pedido_pedido_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.pedido_pedido_id_seq', 1, false);
          public               postgres    false    223            /           0    0    produto_produto_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.produto_produto_id_seq', 1, false);
          public               postgres    false    221            0           0    0 $   tipo_pagamento_tipo_pagamento_id_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('public.tipo_pagamento_tipo_pagamento_id_seq', 1, false);
          public               postgres    false    219            u           2606    16405    categoria categoria_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (cat_id);
 B   ALTER TABLE ONLY public.categoria DROP CONSTRAINT categoria_pkey;
       public                 postgres    false    218                       2606    16460    empresa empresa_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.empresa
    ADD CONSTRAINT empresa_pkey PRIMARY KEY (empresa_id);
 >   ALTER TABLE ONLY public.empresa DROP CONSTRAINT empresa_pkey;
       public                 postgres    false    227            }           2606    16443    item_pedido item_pedido_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT item_pedido_pkey PRIMARY KEY (produto_id, pedido_id);
 F   ALTER TABLE ONLY public.item_pedido DROP CONSTRAINT item_pedido_pkey;
       public                 postgres    false    225    225            {           2606    16433    pedido pedido_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (pedido_id);
 <   ALTER TABLE ONLY public.pedido DROP CONSTRAINT pedido_pkey;
       public                 postgres    false    224            y           2606    16421    produto produto_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (produto_id);
 >   ALTER TABLE ONLY public.produto DROP CONSTRAINT produto_pkey;
       public                 postgres    false    222            w           2606    16412 "   tipo_pagamento tipo_pagamento_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY public.tipo_pagamento
    ADD CONSTRAINT tipo_pagamento_pkey PRIMARY KEY (tipo_pagamento_id);
 L   ALTER TABLE ONLY public.tipo_pagamento DROP CONSTRAINT tipo_pagamento_pkey;
       public                 postgres    false    220            �           2606    16422    produto FK_CAT_ID    FK CONSTRAINT        ALTER TABLE ONLY public.produto
    ADD CONSTRAINT "FK_CAT_ID" FOREIGN KEY (categoria_id) REFERENCES public.categoria(cat_id);
 =   ALTER TABLE ONLY public.produto DROP CONSTRAINT "FK_CAT_ID";
       public               postgres    false    222    4725    218            �           2606    16449    item_pedido FK_PEDIDO_ID    FK CONSTRAINT     �   ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT "FK_PEDIDO_ID" FOREIGN KEY (pedido_id) REFERENCES public.pedido(pedido_id) NOT VALID;
 D   ALTER TABLE ONLY public.item_pedido DROP CONSTRAINT "FK_PEDIDO_ID";
       public               postgres    false    225    4731    224            �           2606    16444    item_pedido FK_PRODUTO_ID    FK CONSTRAINT     �   ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT "FK_PRODUTO_ID" FOREIGN KEY (produto_id) REFERENCES public.produto(produto_id);
 E   ALTER TABLE ONLY public.item_pedido DROP CONSTRAINT "FK_PRODUTO_ID";
       public               postgres    false    225    4729    222            �           2606    16434    pedido FK_TIPO_PAGAMENTO_ID    FK CONSTRAINT     �   ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT "FK_TIPO_PAGAMENTO_ID" FOREIGN KEY (tipo_pagamento_id) REFERENCES public.tipo_pagamento(tipo_pagamento_id);
 G   ALTER TABLE ONLY public.pedido DROP CONSTRAINT "FK_TIPO_PAGAMENTO_ID";
       public               postgres    false    224    4727    220               
   x���             
   x���             
   x���             
   x���             
   x���             
   x���         