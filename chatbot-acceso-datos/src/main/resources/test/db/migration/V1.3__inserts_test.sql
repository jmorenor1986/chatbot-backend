INSERT INTO cliente (id,Nombre_Cliente,Telefono,Cedula,Email,Numero_Credito,Banco,Estado,id_Producto,id_Banco,convenio) VALUES
(1,'LOPEZ LOPEZ LUIS EMILIO','3005632010','56789066','elisabeth.becerra@samtel.co','6000000456','BANCO COMERCIAL AVVILLAS','Cerrado','9991','52','MARCALI INTERNACIONAL SA'),
(2,'GOMEZ GARCIA LUISA CLRA','3005632010','56789098','jesus.sierra@samtel.co','6000000457','BANCO COMERCIAL AVVILLAS','Cerrado','200','52','SIDA S.A.'),
(3,'ALONSO ALONSO ALONSO','3005632010','1234567','a.alvarezb@ext.santanderconsumer.co','6000000458','BANCO COMERCIAL AVVILLAS','Cerrado','1','52','MARKIA SA'),
(4,'PEREZ PEREZ MARIA LUISA','3005632010','56789063','elisabeth.becerra@samtel.co','6000000459','BANCO COMERCIAL AVVILLAS','Cerrado','1','52','MOTOVALLE S.A.S'),
(5,'GARCIA CARBALLO MANUEL CARLOS','3005632010','9535344','jesus.sierra@samtel.co','6000000460','BANCO SANTANDER DE NEGOCIOS COLOMBIA S.A. - BANCO SANTANDER','Cerrado','9991','65','AUTOMOTORES COMAGRO SAS'),
(6,'OUTEIRO LAMAS FERNANDO','3005632010','19977690','alfredoparra67@hotmailcom','6000000461','SANTANDER CONSUMER','Al dia','1','9000','LOS COCHES F SAS'),
(7,'RADIO RADIO LAURA LUISA','3005632010','1019890877','elisabeth.becerra@samtel.co','6000000462','SANTANDER CONSUMER','Al dia','9991','9000','CALIMA MOTOR S.A');
--
--Usuario para hacer el login inicial pass: 123
--
INSERT INTO usuario_app(id, password, usuario)
 VALUES (1, '$2a$10$4uQ2gegiWDykYjkjOf3uzuCPyrCLxD9pPkLix7NuUbIwB5mBQILU2', 'jnsierrac@gmail.com');

--
--Tabla para la creacion
--
INSERT INTO info_whats_appws (id, fecha_envio, estado, num_credito_banco, num_peticion_servicio , numero_identificacion )
                       VALUES(next value for infowhatsappws_seq, CURRENT_TIMESTAMP(), 0, '12345678', '1', '1234');
INSERT INTO info_whats_appws (id, fecha_envio, estado, num_credito_banco, num_peticion_servicio , numero_identificacion )
                       VALUES(next value for infowhatsappws_seq, CURRENT_TIMESTAMP(), 0, '12345678', '2', '1234');
INSERT INTO info_whats_appws (id, fecha_envio, estado, num_credito_banco, num_peticion_servicio , numero_identificacion )
                       VALUES(next value for infowhatsappws_seq, CURRENT_TIMESTAMP(), 1, '12345678', '3', '1234');
INSERT INTO info_whats_appws (id, fecha_envio, estado, num_credito_banco, num_peticion_servicio , numero_identificacion )
                       VALUES(next value for infowhatsappws_seq, CURRENT_TIMESTAMP(), 1, '12345678', '4', '1234');
INSERT INTO info_whats_appws (id, fecha_envio, estado, num_credito_banco, num_peticion_servicio , numero_identificacion )
                       VALUES(next value for infowhatsappws_seq, CURRENT_TIMESTAMP(), 1, '12345678', '5', '1234');

--
--Insert parametros servicio
--
INSERT INTO parametros_servicio (id,canal,servicio,numero_intentos,tiempo_intentos) VALUES (1,'WhatssApp','Paz y Salvo',2,1);
INSERT INTO parametros_servicio (id,canal,servicio,numero_intentos,tiempo_intentos) VALUES (2,'WhatssApp','Debito Automático',2,1);
INSERT INTO parametros_servicio (id,canal,servicio,numero_intentos,tiempo_intentos) VALUES (3,'WhatssApp','Informacion  credito',2,1);
INSERT INTO parametros_servicio (id,canal,servicio,numero_intentos,tiempo_intentos) VALUES (4,'WhatssApp','Declaracion Renta',2,1);