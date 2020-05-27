CREATE TABLE BDPremierPrueba_0306.WhatsAppWS.usuario_app (
    id                  numeric             not null,
    password            varchar(255)                ,
    usuario             varchar(255)        not null,
    primary key (id)
);


CREATE SEQUENCE usuario_app_seq START WITH 1 INCREMENT BY 1 ;


INSERT INTO usuario_app(id, password, usuario)
VALUES ( NEXT VALUE FOR usuario_app_seq , '$2a$10$4uQ2gegiWDykYjkjOf3uzuCPyrCLxD9pPkLix7NuUbIwB5mBQILU2', 'jnsierrac@gmail.com');


--
--Tabla en la que se almacenaran los canales
--
CREATE TABLE BDPremierPrueba_0306.WhatsAppWS.canal (
  id                    numeric            not null  ,
  nombre_canal          varchar(255)                 ,
primary key (id)
);

CREATE SEQUENCE canal_seq START WITH 1 INCREMENT BY 1 ;

--
--Tabla de servicios
--
CREATE TABLE BDPremierPrueba_0306.WhatsAppWS.servcios(
  id                      numeric             not null,
  nombre_servicio         varchar(255)        not null,
  primary key(id)
);

CREATE SEQUENCE servicio_seq START WITH 1 INCREMENT BY 1 ;

--
--Tabla donde se almacenaran los logs de la aplicacion
--
CREATE TABLE BDPremierPrueba_0306.WhatsAppWS.log_cliente(
    id                  numeric            not null,
    canal_id            numeric            not null,
    servicio_id         numeric            not null,
    telefono            varchar(255)       not null,
    fecha               datetime           not null,
    request             text               not null,
    response            text               not null,
    tipo_operacion      numeric                    ,
    identificacion      varchar(255)            ,
    credito             varchar(255)            ,
    correo              varchar(255)            ,
    primary key(id)
);

CREATE SEQUENCE log_seq START WITH 1 INCREMENT BY 1 ;

ALTER TABLE BDPremierPrueba_0306.WhatsAppWS.log_cliente
    ADD CONSTRAINT fk_log_servicio
    FOREIGN KEY (servicio_id) references BDPremierPrueba_0306.WhatsAppWS.servcios(id)
    ;

ALTER TABLE BDPremierPrueba_0306.WhatsAppWS.log_cliente
    ADD CONSTRAINT fk_log_canal
    FOREIGN KEY (canal_id) references BDPremierPrueba_0306.WhatsAppWS.canal(id)
    ;

CREATE SEQUENCE infowhatsappws_seq START WITH 1 INCREMENT BY 1 ;

CREATE TABLE BDPremierPrueba_0306.WhatsAppWS.parametros_servicio
(
    id                  numeric            not null,
    canal_id            numeric            not null,
    servicio_id         numeric            not null,
    tiempo_intentos     numeric                    ,
    tiempo_posterior    numeric                    ,
    PRIMARY KEY (id)
);

ALTER TABLE BDPremierPrueba_0306.WhatsAppWS.parametros_servicio
    ADD CONSTRAINT fk_parametros_servicio
    FOREIGN KEY (servicio_id) references BDPremierPrueba_0306.WhatsAppWS.servcios(id)
    ;

ALTER TABLE BDPremierPrueba_0306.WhatsAppWS.parametros_servicio
    ADD CONSTRAINT fk_parametros_canal
    FOREIGN KEY (canal_id) references BDPremierPrueba_0306.WhatsAppWS.canal(id)
    ;


CREATE TABLE BDPremierPrueba_0306.WhatsAppWS.id_documentos
(
    id                 numeric           NOT NULL,
    id_documentos      text              NOT NULL,
    anio               varchar(5)        NOT NULL,
    mes                varchar(5)        NOT NULL,
    fecha_ini          varchar(20)       NOT NULL,
    fecha_fin          varchar(20)       NOT NULL,
    producto           varchar(50)       NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE id_documentos_seq START WITH 1 INCREMENT BY 1 ;

--
--Insercion para el canal
--
INSERT INTO BDPremierPrueba_0306.WhatsAppWS.canal (id, nombre_canal)
VALUES(NEXT VALUE FOR canal_seq , 'WhatsApp');
--
--Insercion para los servicios
--
INSERT INTO BDPremierPrueba_0306.WhatsAppWS.servcios
(id, nombre_servicio)
VALUES (NEXT VALUE FOR servicio_seq, 'SERVICIO_PAZ_Y_SALVO');

INSERT INTO BDPremierPrueba_0306.WhatsAppWS.servcios
(id, nombre_servicio)
VALUES (NEXT VALUE FOR servicio_seq, 'SERVICIO_VALIDA_CLIENTE');

INSERT INTO BDPremierPrueba_0306.WhatsAppWS.servcios
(id, nombre_servicio)
VALUES (NEXT VALUE FOR servicio_seq, 'SERVICIO_ENLACE_PSE');

INSERT INTO BDPremierPrueba_0306.WhatsAppWS.servcios
(id, nombre_servicio)
VALUES (NEXT VALUE FOR servicio_seq, 'SERVICIO_DEBITO_AUTOMATICO');

INSERT INTO BDPremierPrueba_0306.WhatsAppWS.servcios
(id, nombre_servicio)
VALUES (NEXT VALUE FOR servicio_seq, 'SERVICIO_INFORMACION_CREDITO');

INSERT INTO BDPremierPrueba_0306.WhatsAppWS.servcios
(id, nombre_servicio)
VALUES (NEXT VALUE FOR servicio_seq, 'SERVICIO_OBTENER_CREDITOS');

INSERT INTO BDPremierPrueba_0306.WhatsAppWS.servcios
(id, nombre_servicio)
VALUES (NEXT VALUE FOR servicio_seq, 'SERVICIO_DECLARACION_RENTA');

INSERT INTO BDPremierPrueba_0306.WhatsAppWS.servcios
(id, nombre_servicio)
VALUES (NEXT VALUE FOR servicio_seq, 'SERVICIO_TERMINOS_CONDICIONES');

--
--Insert parametros servicio
--
INSERT INTO BDPremierPrueba_0306.WhatsAppWS.parametros_servicio (id,canal_id,servicio_id,tiempo_intentos, tiempo_posterior) VALUES (1,1,1,100,100);
INSERT INTO BDPremierPrueba_0306.WhatsAppWS.parametros_servicio (id,canal_id,servicio_id,tiempo_intentos, tiempo_posterior) VALUES (2,1,4,100,100);
INSERT INTO BDPremierPrueba_0306.WhatsAppWS.parametros_servicio (id,canal_id,servicio_id,tiempo_intentos, tiempo_posterior) VALUES (3,1,5,100,100);
INSERT INTO BDPremierPrueba_0306.WhatsAppWS.parametros_servicio (id,canal_id,servicio_id,tiempo_intentos, tiempo_posterior) VALUES (4,1,7,100,100);

--
--Tabla en la que se almacenaran los canales
--
CREATE TABLE BDPremierPrueba_0306.WhatsAppWS.pse_param (
  id                    numeric            not null  ,
  id_banco              numeric            not null  ,
  tipo_credito          numeric            not null  ,
  url                   varchar(255)       not null  ,
primary key (id)
);

CREATE SEQUENCE pse_param_seq START WITH 1 INCREMENT BY 1 ;

ALTER TABLE BDPremierPrueba_0306.WhatsAppWS.pse_param ADD CONSTRAINT pse_param_tipo_credito CHECK (
	tipo_credito in (1,2,3)
);


CREATE TABLE BDPremierPrueba_0306.WhatsAppWS.terminos_condiciones (
  id                    numeric            not null  ,
  telefono              numeric            not null  ,
  hora_enviado_term     datetime           not null  ,
  hora_operacion        datetime           not null  ,
  operacion             numeric            not null  ,
primary key (id)
);

CREATE SEQUENCE terminos_condiciones_seq START WITH 1 INCREMENT BY 1 ;

ALTER TABLE BDPremierPrueba_0306.WhatsAppWS.terminos_condiciones ADD CONSTRAINT terminos_condiciones_operacion CHECK (
	operacion in (1,2)
);

CREATE TABLE BDPremierPrueba_0306.WhatsAppWS.parametros_app (
  id                    numeric                 not null  ,
  clave                 varchar(255)            not null  ,
  valor                 varchar(255)            not null  ,
primary key (id)
);