CREATE TABLE parametros_servicio
(
    id                 BIGINT NOT NULL,
    canal              VARCHAR(255) NOT NULL,
    servicio           VARCHAR(255) NOT NULL,
    tiempo_intentos    int8,
    tiempo_posterior   int8,
    PRIMARY KEY (id)
);
--
--Insert parametros servicio
--
INSERT INTO parametros_servicio (id,canal,servicio,tiempo_intentos,tiempo_posterior) VALUES (1,'WhatssApp','Paz y Salvo',1,100);
INSERT INTO parametros_servicio (id,canal,servicio,tiempo_intentos,tiempo_posterior) VALUES (2,'WhatssApp','Debito Automático',1,100);
INSERT INTO parametros_servicio (id,canal,servicio,tiempo_intentos,tiempo_posterior) VALUES (3,'WhatssApp','Informacion  credito',1,100);
INSERT INTO parametros_servicio (id,canal,servicio,tiempo_intentos,tiempo_posterior) VALUES (4,'WhatssApp','Declaracion Renta',1,100);


INSERT INTO canal
(id, nombre_canal)
VALUES(next value for canal_seq, 'WhatsApp');
--
--Insercion para los servicios
--
INSERT INTO servcios
(id, nombre_servicio)
VALUES (nextval('servicio_seq'), 'SERVICIO_PAZ_Y_SALVO');

INSERT INTO servcios
(id, nombre_servicio)
VALUES (nextval('servicio_seq'), 'SERVICIO_VALIDA_CLIENTE');

INSERT INTO servcios
(id, nombre_servicio)
VALUES (nextval('servicio_seq'), 'SERVICIO_ENLACE_PSE');

INSERT INTO servcios
(id, nombre_servicio)
VALUES (nextval('servicio_seq'), 'SERVICIO_DEBITO_AUTOMATICO');

INSERT INTO servcios
(id, nombre_servicio)
VALUES (nextval('servicio_seq'), 'SERVICIO_INFORMACION_CREDITO');

INSERT INTO servcios
(id, nombre_servicio)
VALUES (nextval('servicio_seq'), 'SERVICIO_OBTENER_CREDITOS');

INSERT INTO servcios
(id, nombre_servicio)
VALUES (nextval('servicio_seq'), 'SERVICIO_DECLARACION_RENTA');

--
--Tabla en la que se almacenaran los canales
--
CREATE TABLE pse_param (
  id                    int8            not null  ,
  id_banco              int8            not null  ,
  tipo_credito          int8            not null  ,
  url                   varchar(255)    not null  ,
primary key (id)
);

create sequence pse_param_seq start 1 increment 1;

ALTER TABLE pse_param ADD CONSTRAINT pse_param_tipo_credito CHECK (
	tipo_credito in (1,2,3)
);


insert into pse_param(id,id_banco,tipo_credito,url)
values(nextval('pse_param_seq'), 9000, 1, 'http://ur1'),
(nextval('pse_param_seq'), 9000, 2, 'http://ur1' )     ,
(nextval('pse_param_seq'), 9000, 3, 'http://ur1')      ,
(nextval('pse_param_seq'), 52, 1, 'http://ur1')       ,
(nextval('pse_param_seq'), 52, 2, 'http://ur1' )      ,
(nextval('pse_param_seq'), 52, 3, 'http://ur1')       ,

(nextval('pse_param_seq'), 296, 1, 'http://ur1')      ,
(nextval('pse_param_seq'), 296, 2, 'http://ur1' )     ,
(nextval('pse_param_seq'), 296, 3, 'http://ur1')      ,

(nextval('pse_param_seq'), 65, 1, 'http://ur1')       ,
(nextval('pse_param_seq'), 65, 2, 'http://ur1' )      ,
(nextval('pse_param_seq'), 65, 3, 'http://ur1')       ,

(nextval('pse_param_seq'), 297, 1, 'http://ur1')      ,
(nextval('pse_param_seq'), 297, 2, 'http://ur1' )     ,
(nextval('pse_param_seq'), 297, 3, 'http://ur1');




