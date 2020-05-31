package co.com.santander.chatbot.backend.web.common.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtilities {

    public static Long generateDifferenceDates(Date fIni, Date fFin) {
        long milis1, milis2, diff;

        //INSTANCIA DEL CALENDARIO GREGORIANO
        Calendar cinicio = Calendar.getInstance();
        Calendar cfinal = Calendar.getInstance();

        //ESTABLECEMOS LA FECHA DEL CALENDARIO CON EL DATE GENERADO ANTERIORMENTE
        cinicio.setTime(fIni);
        cfinal.setTime(fFin);

        milis1 = cinicio.getTimeInMillis();
        milis2 = cfinal.getTimeInMillis();
        diff = milis2-milis1;
        return Math.abs (diff / (60 * 1000));
    }

    public static Long transformMinutesToMonths(Long minutes){
        Long horas = Math.abs( minutes / 60 );
        Long dias = Math.abs( horas / 24 );
        return ( dias / 30 );
    }

    public static Date stringToDate(String sFecha){
        Date fechaDocumento = null;
        try {
            fechaDocumento = new SimpleDateFormat("dd/MM/yyyy").parse(sFecha);
        }catch (Exception e){
        }
        return fechaDocumento;
    }

    public static Long generateDifferenceInDays(Date fIni, Date fFin) {
        long milis1, milis2, diff;

        //INSTANCIA DEL CALENDARIO GREGORIANO
        Calendar cinicio = Calendar.getInstance();
        Calendar cfinal = Calendar.getInstance();

        //ESTABLECEMOS LA FECHA DEL CALENDARIO CON EL DATE GENERADO ANTERIORMENTE
        cinicio.setTime(fIni);
        cfinal.setTime(fFin);

        milis1 = cinicio.getTimeInMillis();
        milis2 = cfinal.getTimeInMillis();
        diff = milis2-milis1;
        Long minutos = Math.abs (diff / (60 * 1000));
        Long horas = Math.abs( minutos / 60 );
        return Math.abs( horas / 24  );
    }
}
