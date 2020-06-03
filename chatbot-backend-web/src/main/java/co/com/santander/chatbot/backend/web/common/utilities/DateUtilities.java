package co.com.santander.chatbot.backend.web.common.utilities;

import lombok.extern.java.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Log
public class DateUtilities {

    private DateUtilities() {
        throw new IllegalStateException("DateUtilities class");
    }

    public static Long generateDifferenceDates(Date fIni, Date fFin) {
        return Math.abs (getDifferenceMillseconds(fIni, fFin) / (60 * 1000));
    }

    private static long getDifferenceMillseconds(Date fIni, Date fFin){
        long milis1;
        long milis2;
        long diff;
        //INSTANCIA DEL CALENDARIO GREGORIANO
        Calendar cinicio = Calendar.getInstance();
        Calendar cfinal = Calendar.getInstance();

        //ESTABLECEMOS LA FECHA DEL CALENDARIO CON EL DATE GENERADO ANTERIORMENTE
        cinicio.setTime(fIni);
        cfinal.setTime(fFin);

        milis1 = cinicio.getTimeInMillis();
        milis2 = cfinal.getTimeInMillis();
        diff = milis2-milis1;
        return diff;
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
            log.severe(e.getMessage());
        }
        return fechaDocumento;
    }

    public static String getDataFromDateString(String fecha, int cal){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(fecha);
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(date);
            return "" + calendario.get(cal);
        }catch (Exception e){
            log.severe(e.getMessage());
        }
        return "";
    }

    public static Long generateDifferenceInDays(Date fIni, Date fFin) {
        long diff;
        diff = getDifferenceMillseconds(fIni, fFin);
        Long minutos = Math.abs (diff / (60 * 1000));
        Long horas = Math.abs( minutos / 60 );
        return Math.abs( horas / 24  );
    }
}
