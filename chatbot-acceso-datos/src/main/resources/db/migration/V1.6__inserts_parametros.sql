--
--Insercion para el canal
--
INSERT INTO canal
(id, nombre_canal)
VALUES(nextval('canal_seq'), 'WhatsApp');
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

INSERT INTO servcios
(id, nombre_servicio)
VALUES (nextval('servicio_seq'), 'SERVICIO_TERMINOS_CONDICIONES');

--
--Insert parametros servicio
--
INSERT INTO parametros_servicio (id,canal_id,servicio_id,tiempo_intentos, tiempo_posterior) VALUES (1,1,1,1,100);
INSERT INTO parametros_servicio (id,canal_id,servicio_id,tiempo_intentos, tiempo_posterior) VALUES (2,1,4,1,100);
INSERT INTO parametros_servicio (id,canal_id,servicio_id,tiempo_intentos, tiempo_posterior) VALUES (3,1,5,1,100);
INSERT INTO parametros_servicio (id,canal_id,servicio_id,tiempo_intentos, tiempo_posterior) VALUES (4,1,7,1,100);
