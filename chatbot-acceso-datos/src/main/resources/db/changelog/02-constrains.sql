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