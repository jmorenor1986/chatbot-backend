package co.com.santander.chatbot.backend.web.common.utilities;

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
}
