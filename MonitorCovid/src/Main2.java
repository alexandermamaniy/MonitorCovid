import diagnosticos.DiagnosticoPorFase;
import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;
import sintomas.PrimeraFase;
import sintomas.SegundaFase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2 {

    public static void main( String[] args ){
        Registros r = new Registros();
        Sintoma s1 = new PrimeraFase("fiebre");
        Sintoma s2 = new PrimeraFase("tos");
        Sintoma s3 = new PrimeraFase("resfrio");
        Sintoma s4 = new PrimeraFase("dolor de cabeza");
        Sintoma s5 = new SegundaFase("perdida olfato");
        Sintoma s6 = new SegundaFase("escalofrio");


        Sintomas s = new Sintomas();
        s.add(s1);
        s.add(s2);
        s.add(s3);
        s.add(s4);
        s.add(s5);
        s.add(s6);

        Sintomas a = new Sintomas();
        a.add(s1);
        a.add(s2);
        r.push(new Registro(parseDate("2021-07-01"), a));

        a = new Sintomas();
        a.add(s3);
        a.add(s4);
        r.push(new Registro(parseDate("2021-07-02"), a));

        a = new Sintomas();
        a.add(s4);
        a.add(s5);
        r.push(new Registro(parseDate("2021-07-02"), a));

        DiagnosticoPorFase di = new DiagnosticoPorFase(s);
        System.out.println(di.diagnostico(r));
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}
