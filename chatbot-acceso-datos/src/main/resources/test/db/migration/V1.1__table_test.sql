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