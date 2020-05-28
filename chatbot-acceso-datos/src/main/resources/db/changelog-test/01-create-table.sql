--
--Usuarios
--
CREATE TABLE usuario_app (
    id int8 not null,
    password varchar(255),
    usuario varchar(255) not null,
    primary key (id)
);
--
--Tabla clientes la cual la da el cliente
--
CREATE TABLE cliente (
  id                     int8            not null ,
  nombrecliente         varchar                  ,
  Telefono               varchar                  ,
  Cedula                 varchar                  ,
  Email                  varchar                  ,
  numerocredito         varchar                  ,
  Banco                  varchar                  ,
  Estado                 varchar                  ,
  idproducto            varchar                  ,
  idbanco               varchar                  ,
  convenio               varchar                  ,
  valorapagar          numeric                  ,
  valormora             numeric                  ,
  tipoproducto           numeric                  ,
primary key (id)
);
--
--Tabla dada por el banco
--
CREATE TABLE info_whats_appws
(
     id                    BIGINT NOT NULL,
     fecha_envio           TIMESTAMP,
     estado                BIGINT,
     num_credito_banco     VARCHAR(255),
     num_peticion_servicio BIGINT,
     numero_identificacion VARCHAR(255),
     PRIMARY KEY (id)
);
--
--Tabla en la que se almacenaran los canales
--
CREATE TABLE canal (
  id                    int8            not null  ,
  nombre_canal          varchar                   ,
primary key (id)
);
--
--Tabla de servicios
--
CREATE TABLE servcios(
  id                      int8          not null,
  nombre_servicio         varchar       not null,
  primary key(id)
);
--
--Tabla donde se almacenaran los logs de la aplicacion
--
CREATE TABLE log_cliente(
    id                  int8            not null,
    canal_id            int8            not null,
    servicio_id         int8            not null,
    telefono            varchar(255)    not null,
    fecha               timestamp       not null,
    request             text            not null,
    response            text            not null,
    tipo_operacion      int8                    ,
    identificacion      varchar(255)            ,
    credito             varchar(255)            ,
    correo              varchar(255)            ,
    primary key(id)
);
--
--
--
CREATE TABLE parametros_servicio
(
    id                 BIGINT           NOT NULL,
    canal_id            int8            not null,
    servicio_id         int8            not null,
    tiempo_intentos     int8                    ,
    tiempo_posterior    int8                    ,
    PRIMARY KEY (id)
);
--
--
--
CREATE TABLE id_documentos
(
    id                 BIGINT           NOT NULL,
    id_documentos      text             NOT NULL,
    anio               varchar(5)       NOT NULL,
    mes                varchar(5)       NOT NULL,
    fecha_ini          varchar(20)       NOT NULL,
    fecha_fin          varchar(20)       NOT NULL,
    producto           varchar(50)       NOT NULL,
    PRIMARY KEY (id)
);
--
--
--
CREATE TABLE terminos_condiciones (
  id                    int8            not null  ,
  telefono              int8            not null  ,
  hora_enviado_term     timestamp       not null  ,
  hora_operacion        timestamp       not null  ,
  operacion             int8            not null  ,
  fecha                 timestamp       not null  ,
primary key (id)
);
--
--
--
CREATE TABLE pse_param (
  id                    int8            not null  ,
  id_banco              int8            not null  ,
  tipo_credito          int8            not null  ,
  url                   varchar(255)    not null  ,
primary key (id)
);
--
--
--
CREATE TABLE parametros_app (
  id                    numeric                 not null  ,
  clave                 varchar(255)            not null  ,
  valor                 varchar(255)            not null  ,
primary key (id)
);