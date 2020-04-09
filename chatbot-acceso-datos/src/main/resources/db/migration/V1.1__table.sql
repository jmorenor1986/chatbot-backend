--
--
--
CREATE TABLE cliente (
  id                    int8            not null ,
  cola_identificacion   varchar(255)             ,
  telefono              numeric(20, 2)           ,
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