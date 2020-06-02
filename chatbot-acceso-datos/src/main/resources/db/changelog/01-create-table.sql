--
--Usuarios
--
CREATE TABLE usuario_app (
    id              numeric         not null,
    password        varchar(255)            ,
    usuario         varchar(255)    not null,
    primary key (id)
);
--
--Tabla clientes la cual la da el cliente
--
CREATE TABLE consulta_cliente (
  id                     numeric            not null ,
  nombrecliente          varchar                  ,
  Telefono               varchar                  ,
  Cedula                 varchar                  ,
  Email                  varchar                  ,
  numerocredito          varchar                  ,
  Banco                  varchar                  ,
  Estado                 varchar                  ,
  idproducto             varchar                  ,
  idbanco                varchar                  ,
  convenio               varchar                  ,
  valorapagar            numeric                  ,
  valormora              numeric                  ,
  tipoproducto           numeric                  ,
  fechadesembolso        date                     ,
  saldocapital           numeric                  ,
  valordesembolso        numeric                  ,
primary key (id)
);
--
--Tabla dada por el banco
--
CREATE TABLE infowhatsappws
(
     id                     BIGINT      NOT NULL,
     fechaenvio             TIMESTAMP           ,
     estado                 BIGINT              ,
     numcreditobanco        VARCHAR(255)        ,
     numpeticionservicio    BIGINT              ,
     numeroidentificacion   VARCHAR(255)        ,
     PRIMARY KEY (id)
);
--
--Tabla en la que se almacenaran los canales
--
CREATE TABLE canal (
  id                    numeric            not null  ,
  nombre_canal          varchar                   ,
primary key (id)
);
--
--Tabla de servicios
--
CREATE TABLE servcios(
  id                      numeric          not null,
  nombre_servicio         varchar       not null,
  primary key(id)
);
--
--Tabla donde se almacenaran los logs de la aplicacion
--
CREATE TABLE log_cliente(
    id                  numeric            not null,
    canal_id            numeric            not null,
    servicio_id         numeric            not null,
    telefono            varchar(255)    not null,
    fecha               timestamp       not null,
    request             text            not null,
    response            text            not null,
    tipo_operacion      numeric                    ,
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
    canal_id            numeric            not null,
    servicio_id         numeric            not null,
    tiempo_intentos     numeric                    ,
    tiempo_posterior    numeric                    ,
    PRIMARY KEY (id)
);
--
--
--
CREATE TABLE id_documentos
(
    id                 BIGINT            NOT NULL,
    id_documentos      text              NOT NULL,
    anio               varchar(5)        NOT NULL,
    mes                varchar(5)        NOT NULL,
    fecha_ini          varchar(20)       NOT NULL,
    fecha_fin          varchar(20)       NOT NULL,
    fecha              TIMESTAMP         NOT NULL,
    producto           varchar(50)       NOT NULL,
    PRIMARY KEY (id)
);
--
--
--
CREATE TABLE terminos_condiciones (
  id                    numeric            not null  ,
  telefono              numeric            not null  ,
  hora_enviado_term     timestamp       not null  ,
  hora_operacion        timestamp       not null  ,
  operacion             numeric            not null  ,
  fecha                 timestamp       not null  ,
primary key (id)
);
--
--
--
CREATE TABLE pse_param (
  id                    numeric            not null  ,
  id_banco              numeric            not null  ,
  tipo_credito          numeric            not null  ,
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