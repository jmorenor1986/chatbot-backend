CREATE TABLE parametros_servicio
(
    id                 BIGINT NOT NULL,
    canal              VARCHAR(255) NOT NULL,
    servicio           VARCHAR(255) NOT NULL,
    numero_intentos    int8,
    tiempo_intentos    int8,
    tiempo_posterior   int8,
    PRIMARY KEY (id)
);
--
--Insert parametros servicio
--
INSERT INTO parametros_servicio (id,canal,servicio,numero_intentos,tiempo_intentos,tiempo_posterior) VALUES (1,'WhatssApp','Paz y Salvo',2,1,100);
INSERT INTO parametros_servicio (id,canal,servicio,numero_intentos,tiempo_intentos,tiempo_posterior) VALUES (2,'WhatssApp','Debito Automático',2,1,100);
INSERT INTO parametros_servicio (id,canal,servicio,numero_intentos,tiempo_intentos,tiempo_posterior) VALUES (3,'WhatssApp','Informacion  credito',2,1,100);
INSERT INTO parametros_servicio (id,canal,servicio,numero_intentos,tiempo_intentos,tiempo_posterior) VALUES (4,'WhatssApp','Declaracion Renta',2,1,100);


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




