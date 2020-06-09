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


--
--Secuencias
--
create sequence usuario_app_seq start 1 increment 1;

create sequence canal_seq start 1 increment 1;

create sequence servicio_seq start 1 increment 1;

create sequence log_seq start 1 increment 1;

create sequence infowhatsappws_seq start 1 increment 1;

create sequence id_documentos_seq start 1 increment 1;

create sequence pse_param_seq start 1 increment 1;

create sequence terminos_condiciones_seq start 1 increment 1;

create sequence parametros_app_seq start 1 increment 1;


ALTER TABLE log_cliente
    ADD CONSTRAINT fk_log_servicio
    FOREIGN KEY (servicio_id) references servcios(id)
    ;

ALTER TABLE log_cliente
    ADD CONSTRAINT fk_log_canal
    FOREIGN KEY (canal_id) references canal(id)
    ;

ALTER TABLE parametros_servicio
    ADD CONSTRAINT fk_parametros_servicio
    FOREIGN KEY (servicio_id) references servcios(id)
    ;

ALTER TABLE parametros_servicio
    ADD CONSTRAINT fk_parametros_canal
    FOREIGN KEY (canal_id) references canal(id)
    ;

ALTER TABLE pse_param ADD CONSTRAINT pse_param_tipo_credito CHECK (
	tipo_credito in (1,2,3)
);

ALTER TABLE terminos_condiciones ADD CONSTRAINT terminos_condiciones_operacion CHECK (
	operacion in (1,2)
);


INSERT INTO consulta_cliente (id, nombrecliente, telefono, cedula, email, numerocredito, banco, estado, idproducto,idbanco,convenio, valorapagar, tipoproducto, valormora, fechadesembolso, saldocapital, valordesembolso) VALUES
(1,'LOPEZ LOPEZ LUIS EMILIO','3005632010','56789066','elisabeth.becerra@samtel.co','6000000456','BANCO COMERCIAL AVVILLAS','Cerrado','9991','52','MARCALI INTERNACIONAL SA', 1500000, 1, 10000, '01/01/2019', 1000000, 10000000),
(2,'GOMEZ GARCIA LUISA CLRA','3005632011','56789098','jesus.sierra@samtel.co','6000000457','BANCO COMERCIAL AVVILLAS','Cerrado','200','52','SIDA S.A.', 1800000,2, 10000, '01/01/2019', 1000000, 10000000),
(3,'ALONSO ALONSO ALONSO','3005632012','1234567','a.alvarezb@ext.santanderconsumer.co','6000000458','BANCO COMERCIAL AVVILLAS','Cerrado','1','52','MARKIA SA', 2100000, 2,200000, '01/01/2019', 1000000, 10000000),
(4,'PEREZ PEREZ MARIA LUISA','3005632013','56789063','elisabeth.becerra@samtel.co','6000000459','BANCO COMERCIAL AVVILLAS','Cerrado','1','52','MOTOVALLE S.A.S', 3500000, 2,25000, '01/01/2019', 1000000, 10000000),
(5,'GARCIA CARBALLO MANUEL CARLOS','3005632014','9535344','jesus.sierra@samtel.co','6000000460','BANCO SANTANDER DE NEGOCIOS COLOMBIA S.A. - BANCO SANTANDER','Cerrado','9991','65','AUTOMOTORES COMAGRO SAS', 800000,2, 350000, '01/01/2019', 1000000, 10000000),
(6,'OUTEIRO LAMAS FERNANDO','3005632015','19977690','alfredoparra67@hotmailcom','6000000461','SANTANDER CONSUMER','Al dia','1','9000','LOS COCHES F SAS',700000, 1,0, '01/01/2019', 1000000, 10000000),
(7,'RADIO RADIO LAURA LUISA','3005632016','1019890877','elisabeth.becerra@samtel.co','6000000462','SANTANDER CONSUMER','Al dia','9991','9000','CALIMA MOTOR S.A',650000, 1,0, '01/01/2019',  1000000, 10000000);

--
--Usuario para hacer el login inicial pass: 123
--
INSERT INTO usuario_app(id, password, usuario)
 VALUES (1, '$2a$10$4uQ2gegiWDykYjkjOf3uzuCPyrCLxD9pPkLix7NuUbIwB5mBQILU2', 'jnsierrac@gmail.com');


 INSERT INTO consulta_cliente (id, nombrecliente, telefono, cedula, email, numerocredito, banco, estado, idproducto,idbanco,convenio, valorapagar, tipoproducto, valormora, fechadesembolso, saldocapital, valordesembolso) VALUES
 (8,'JESUS NICOLAS SIERRA CHAPARRO','3229034455','1030589632','jesus.sierraa@gmail.com','6000000463','BANCO COMERCIAL AVVILLAS','Cerrado','9991','52','MARCALI INTERNACIONAL SA', 500000, 1,10000, '01/01/2019', 1000000, 10000000),
 (9,'JESUS  CHAPARRO','3229034456','1030589633','jesus.sierrac@gmail.com','6000000464','BANCO COMERCIAL AVVILLAS','Cerrado','9991','52','MARCALI INTERNACIONAL SA',800000, 2, 50000, '01/01/2019', 1000000, 10000000),
 (10,'JESUS  CHAPARRO','3229034456','1030589633','jesus.sierrac@gmail.com','6000000465','BANCO COMERCIAL AVVILLAS','Al dia','9991','200','MARCALI INTERNACIONAL SA',560000, 3,0 , '01/01/2019', 1000000, 10000000),
 (11,'JOHN  MORENO','3229039985','1030589656','john.moreno@gmail.com','6000000466','BANCO COMERCIAL AVVILLAS','Cerrado','9991','52','MARCALI INTERNACIONAL SA', 350000,1, 10000, '01/01/2019', 1000000, 10000000),
 (12,'JOHN  MORENO','3229039985','1030589656','john.moreno@gmail.com','6000000466','BANCO COMERCIAL AVVILLAS','Cerrado','9991','52','MARCALI INTERNACIONAL SA', 350000,1, 25000, '01/01/2019', 1000000, 10000000),
 (13,'Pepito','3229039988','41584206','john.moreno1@gmail.com','830000000185','BANCO COMERCIAL AVVILLAS','Al dia','9991','200','MARCALI INTERNACIONAL SA', 960000,1,85000, '01/01/2017', 1000000, 10000000);

ALTER SEQUENCE canal_seq RESTART WITH 1;
 --
 --Insercion para el canal
 --
 INSERT INTO canal
 (id, nombre_canal)
 VALUES(nextval('canal_seq'), 'WhatsApp');
 --
 ALTER SEQUENCE servicio_seq RESTART WITH 1;
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
 INSERT INTO parametros_servicio (id,canal_id,servicio_id,tiempo_intentos, tiempo_posterior) VALUES (1,1,1,100,100);
 INSERT INTO parametros_servicio (id,canal_id,servicio_id,tiempo_intentos, tiempo_posterior) VALUES (2,1,4,100,100);
 INSERT INTO parametros_servicio (id,canal_id,servicio_id,tiempo_intentos, tiempo_posterior) VALUES (3,1,5,100,100);
 INSERT INTO parametros_servicio (id,canal_id,servicio_id,tiempo_intentos, tiempo_posterior) VALUES (4,1,7,100,100);


insert into pse_param(id,id_banco,tipo_credito,url)
values(nextval('pse_param_seq'), 9000, 1, 'https://www.pagosvirtualesavvillas.com.co/personal/pagos/12328'),
(nextval('pse_param_seq'), 9000, 2, 'https://www.pagosvirtualesavvillas.com.co/personal/pagos/12328' )     ,
(nextval('pse_param_seq'), 9000, 3, 'https://www.pagosvirtualesavvillas.com.co/personal/pagos/12328')      ,
(nextval('pse_param_seq'), 52, 1, 'https://www.pagosvirtualesavvillas.com.co/personal/pagos/')       ,
(nextval('pse_param_seq'), 52, 2, 'https://www.pagosvirtualesavvillas.com.co/personal/pagos/' )      ,
(nextval('pse_param_seq'), 52, 3, 'https://www.pagosvirtualesavvillas.com.co/personal/pagos/')       ,

(nextval('pse_param_seq'), 296, 1, 'https://financierajuriscoop.com.co/oficina-virtual/')      ,
(nextval('pse_param_seq'), 296, 2, 'https://financierajuriscoop.com.co/oficina-virtual/' )     ,
(nextval('pse_param_seq'), 296, 3, 'https://financierajuriscoop.com.co/oficina-virtual/')      ,

(nextval('pse_param_seq'), 65, 1, 'http://ur1')       ,
(nextval('pse_param_seq'), 65, 2, 'http://ur1' )      ,
(nextval('pse_param_seq'), 65, 3, 'http://ur1')       ,

(nextval('pse_param_seq'), 297, 1, 'http://ur1')      ,
(nextval('pse_param_seq'), 297, 2, 'http://ur1' )     ,
(nextval('pse_param_seq'), 297, 3, 'http://ur1');


insert into parametros_app (id, clave, valor ) values
 (nextval ('parametros_app_seq'),'MESES_EXTRACTO' , '6'),
 (nextval ('parametros_app_seq'),'DIAS_MACHINELERNING' , '700'),
 (nextval ('parametros_app_seq'),'PORCENTAJE_MACHINELERNING' , '70')
;


--
-- Trigger el cual se encargara de relacionar la cuenta con el grupo
--
CREATE OR REPLACE FUNCTION f_ins_infowhatsapp() RETURNS trigger AS $f_ins_infowhatsapp$
    DECLARE

        c_count CURSOR FOR
        SELECT count(*) + 1 registros
          FROM infowhatsappws i
         ;

        v_count             NUMERIC :=0;

    BEGIN

        OPEN c_count;
        FETCH c_count INTO v_count;
        CLOSE c_count;

        NEW.id := v_count;

        RETURN NEW;


    END;
$f_ins_infowhatsapp$ LANGUAGE plpgsql;


CREATE TRIGGER f_ins_infowhatsapp
    BEFORE INSERT OR UPDATE ON infowhatsappws
    FOR EACH ROW
    EXECUTE PROCEDURE f_ins_infowhatsapp()
    ;