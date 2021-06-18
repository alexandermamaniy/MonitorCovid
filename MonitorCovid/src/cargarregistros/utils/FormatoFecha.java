package cargarregistros.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatoFecha {
    public String dateToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String dateString = format.format(date);
        return dateString;
    }
}
