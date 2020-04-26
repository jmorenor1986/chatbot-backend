--
--
--
CREATE TABLE cliente (
  id                    int8            not null ,
  Nombre_Cliente         varchar                  ,
  Telefono              varchar                  ,
  Cedula                varchar                  ,
  Email                 varchar                  ,
  Numero_Credito         varchar                  ,
  Banco                 varchar                  ,
  Estado                varchar                  ,
  id_Producto            varchar                  ,
  id_Banco               varchar                  ,
  convenio              varchar                  ,
primary key (id)
);
--
--
--
CREATE TABLE usuario_app (
    id int8 not null,
    password varchar(255),
    usuario varchar(255) not null,
    primary key (id)
);


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


