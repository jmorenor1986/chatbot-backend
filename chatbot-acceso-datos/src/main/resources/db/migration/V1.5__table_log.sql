--
--Tabla en la que se almacenaran los canales
--
CREATE TABLE canal (
  id                    int8            not null  ,
  nombre_canal          varchar                   ,
primary key (id)
);

create sequence canal_seq start 1 increment 1;
--
--Tabla de servicios
--
CREATE TABLE servcios(
  id                      int8          not null,
  nombre_servicio         varchar       not null,
  primary key(id)
);

create sequence servicio_seq start 1 increment 1;
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

create sequence log_seq start 1 increment 1;

ALTER TABLE log_cliente
    ADD CONSTRAINT fk_log_servicio
    FOREIGN KEY (servicio_id) references servcios(id)
    ;

ALTER TABLE log_cliente
    ADD CONSTRAINT fk_log_canal
    FOREIGN KEY (canal_id) references canal(id)
    ;

create sequence infowhatsappws_seq start 1 increment 1;

CREATE TABLE parametros_servicio
(
    id                 BIGINT           NOT NULL,
    canal_id            int8            not null,
    servicio_id         int8            not null,
    tiempo_intentos     int8                    ,
    tiempo_posterior    int8                    ,
    PRIMARY KEY (id)
);

ALTER TABLE parametros_servicio
    ADD CONSTRAINT fk_parametros_servicio
    FOREIGN KEY (servicio_id) references servcios(id)
    ;

ALTER TABLE parametros_servicio
    ADD CONSTRAINT fk_parametros_canal
    FOREIGN KEY (canal_id) references canal(id)
    ;

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

create sequence id_documentos_seq start 1 increment 1;