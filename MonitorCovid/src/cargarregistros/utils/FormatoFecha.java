package cargarregistros.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatoFecha {
    public String dateAndHourToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        return format.format(date);
    }

    public String dateToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
