CREATE TABLE parametros_servicio
(
    id                 BIGINT NOT NULL,
    canal              VARCHAR(255) NOT NULL,
    servicio           VARCHAR(255) NOT NULL,
    numero_intentos    int8,
    tiempo_intentos    int8,
    PRIMARY KEY (id)
);
--
--Insert parametros servicio
--
INSERT INTO parametros_servicio (id,canal,servicio,numero_intentos,tiempo_intentos) VALUES (1,'WhatssApp','Paz y Salvo',2,1);
INSERT INTO parametros_servicio (id,canal,servicio,numero_intentos,tiempo_intentos) VALUES (2,'WhatssApp','Debito Automático',2,1);
INSERT INTO parametros_servicio (id,canal,servicio,numero_intentos,tiempo_intentos) VALUES (3,'WhatssApp','Informacion  credito',2,1);
INSERT INTO parametros_servicio (id,canal,servicio,numero_intentos,tiempo_intentos) VALUES (4,'WhatssApp','Declaracion Renta',2,1);

--
--Tabla para la creacion
--
INSERT INTO info_whats_appws (id, fecha_envio, estado, num_credito_banco, num_peticion_servicio , numero_identificacion )
                       VALUEs(next value for infowhatsappws_seq , now(), 0, '12345678', '1', '1234');
INSERT INTO info_whats_appws (id, fecha_envio, estado, num_credito_banco, num_peticion_servicio , numero_identificacion )
                       VALUES(next value for infowhatsappws_seq , now(), 0, '12345678', '2', '1234');
INSERT INTO info_whats_appws (id, fecha_envio, estado, num_credito_banco, num_peticion_servicio , numero_identificacion )
                       VALUES(next value for infowhatsappws_seq, now(), 1, '12345678', '3', '1234');
INSERT INTO info_whats_appws (id, fecha_envio, estado, num_credito_banco, num_peticion_servicio , numero_identificacion )
                       VALUES(next value for infowhatsappws_seq , now(), 1, '12345678', '4', '1234');
INSERT INTO info_whats_appws (id, fecha_envio, estado, num_credito_banco, num_peticion_servicio , numero_identificacion )
                       VALUES(next value for infowhatsappws_seq, now(), 1, '12345678', '5', '1234');

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




