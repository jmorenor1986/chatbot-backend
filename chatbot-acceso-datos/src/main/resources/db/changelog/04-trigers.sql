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