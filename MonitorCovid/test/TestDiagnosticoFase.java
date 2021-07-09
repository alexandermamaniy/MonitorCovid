import diagnosticos.DiagnosticoPorFase;
import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sintomas.PrimeraFase;
import sintomas.SegundaFase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TestDiagnosticoFase {
    static Sintomas sintomasMonitor;
    static Registros registrosMonitor;
    static Sintoma s1f1 = new PrimeraFase("dolor corporal");
    static Sintoma s2f1 = new PrimeraFase("dolor de ojos");
    static Sintoma s3f1 = new PrimeraFase("dolor de cabeza");
    static Sintoma s4f1 = new PrimeraFase("Vomitos");
    static Sintoma s5f2 = new SegundaFase("Perdida del gusto");
    static Sintoma s6f2 = new SegundaFase("Dolor en el pecho");
    static Sintoma s7f2 = new SegundaFase("Tos seca");
    static Sintoma s8f2 = new SegundaFase("Alta temperatura");

    @BeforeEach
    public void setup(){
        registrosMonitor = new Registros();
        sintomasMonitor = new Sintomas();
        sintomasMonitor.add(s1f1);
        sintomasMonitor.add(s2f1);
        sintomasMonitor.add(s3f1);
        sintomasMonitor.add(s4f1);
        sintomasMonitor.add(s5f2);
        sintomasMonitor.add(s6f2);
        sintomasMonitor.add(s7f2);
        sintomasMonitor.add(s8f2);
    }

    @Test
    void testCasoRegistrosVacios(){
        DiagnosticoPorFase di = new DiagnosticoPorFase(sintomasMonitor);
        assertEquals(di.diagnostico(registrosMonitor), 0);
    }

    @Test
    void testFaseUnoConUndiaDePuroSintomasPrimeraFase(){

        Sintomas a ;

        a = new Sintomas();
        a.add(s2f1);
        a.add(s3f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-08"), a));

        DiagnosticoPorFase di = new DiagnosticoPorFase(sintomasMonitor);
        assertEquals(di.diagnostico(registrosMonitor), 1);
    }

    @Test
    void testFaseUnoConDosdiasPuroSintomasPrimeraFase(){

        Sintomas a ;

        a = new Sintomas();
        a.add(s2f1);
        a.add(s1f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-07"), a));

        a = new Sintomas();
        a.add(s2f1);
        a.add(s3f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-08"), a));

        DiagnosticoPorFase di = new DiagnosticoPorFase(sintomasMonitor);
        assertEquals(di.diagnostico(registrosMonitor), 2);
    }

    @Test
    void testFaseUnoConTresdiasPuroSintomasPrimeraFase(){

        Sintomas a ;

        a = new Sintomas();
        a.add(s2f1);
        a.add(s1f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-06"), a));

        a = new Sintomas();
        a.add(s2f1);
        a.add(s1f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-07"), a));

        a = new Sintomas();
        a.add(s2f1);
        a.add(s3f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-08"), a));

        DiagnosticoPorFase di = new DiagnosticoPorFase(sintomasMonitor);
        assertEquals(di.diagnostico(registrosMonitor), 3);
    }


    @Test
    void testFaseUnoConCuatroDiasPuroSintomasPrimeraFase(){

        Sintomas a ;
        a = new Sintomas();
        a.add(s1f1);
        a.add(s2f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-05"), a));

        a = new Sintomas();
        a.add(s1f1);
        a.add(s2f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-05"), a));

        a = new Sintomas();
        a.add(s1f1);
        a.add(s3f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-05"), a));

        a = new Sintomas();
        a.add(s1f1);
        a.add(s2f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-06"), a));

        a = new Sintomas();
        a.add(s2f1);
        a.add(s1f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-07"), a));

        a = new Sintomas();
        a.add(s2f1);
        a.add(s3f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-08"), a));

        DiagnosticoPorFase di = new DiagnosticoPorFase(sintomasMonitor);
        assertEquals(di.diagnostico(registrosMonitor), 3);
    }


    @Test
    void testFaseDosConSintomasDeTresDiasFaseUnoyUnDiaFaseDos(){

        Sintomas a ;
        a = new Sintomas();
        a.add(s1f1);
        a.add(s2f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-05"), a));

        a = new Sintomas();
        a.add(s1f1);
        a.add(s2f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-06"), a));

        a = new Sintomas();
        a.add(s2f1);
        a.add(s1f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-07"), a));

        a = new Sintomas();
        a.add(s5f2);
        a.add(s6f2);
        registrosMonitor.push(new Registro(parseDate("2021-07-08"), a));

        DiagnosticoPorFase di = new DiagnosticoPorFase(sintomasMonitor);
        assertEquals(di.diagnostico(registrosMonitor), 4);
    }

    @Test
    void testFaseDosConSintomasDeTresDiasFaseUnoyCuatroDiasFaseDos(){

        Sintomas a ;
        a = new Sintomas();

        a.add(s1f1);
        a.add(s2f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-02"), a));

        a = new Sintomas();
        a.add(s1f1);
        a.add(s2f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-03"), a));

        a = new Sintomas();
        a.add(s2f1);
        a.add(s1f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-04"), a));


        a.add(s5f2);
        a.add(s6f2);
        registrosMonitor.push(new Registro(parseDate("2021-07-05"), a));

        a = new Sintomas();
        a.add(s5f2);
        a.add(s6f2);
        registrosMonitor.push(new Registro(parseDate("2021-07-06"), a));

        a = new Sintomas();
        a.add(s5f2);
        a.add(s6f2);
        registrosMonitor.push(new Registro(parseDate("2021-07-07"), a));

        a = new Sintomas();
        a.add(s7f2);
        a.add(s8f2);
        registrosMonitor.push(new Registro(parseDate("2021-07-08"), a));

        DiagnosticoPorFase di = new DiagnosticoPorFase(sintomasMonitor);
        assertEquals(di.diagnostico(registrosMonitor), 7);
    }

    @Test
    void testFaseDosConDiasSinSintomas(){

        Sintomas a ;
        a = new Sintomas();

        a.add(s1f1);
        a.add(s2f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-02"), a));

        a = new Sintomas();
        registrosMonitor.push(new Registro(parseDate("2021-07-03"), a));

        a = new Sintomas();
        registrosMonitor.push(new Registro(parseDate("2021-07-04"), a));

        a = new Sintomas();
        a.add(s4f1);
        a.add(s3f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-05"), a));

        a = new Sintomas();
        a.add(s2f1);
        a.add(s1f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-06"), a));

        a = new Sintomas();
        a.add(s2f1);
        a.add(s3f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-07"), a));

        a = new Sintomas();
        a.add(s7f2);
        a.add(s8f2);
        registrosMonitor.push(new Registro(parseDate("2021-07-08"), a));

        DiagnosticoPorFase di = new DiagnosticoPorFase(sintomasMonitor);
        assertEquals(di.diagnostico(registrosMonitor), 4);
    }

    @Test
    void testFaseUnoConTresDiasSintomasPrimeraySegundaFase(){

        Sintomas a ;
        a = new Sintomas();
        a.add(s1f1);
        a.add(s2f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-02"), a));

        a = new Sintomas();
        registrosMonitor.push(new Registro(parseDate("2021-07-03"), a));

        a = new Sintomas();
        registrosMonitor.push(new Registro(parseDate("2021-07-04"), a));

        a = new Sintomas();
        registrosMonitor.push(new Registro(parseDate("2021-07-05"), a));

        a = new Sintomas();
        a.add(s2f1);
        a.add(s1f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-06"), a));

        a = new Sintomas();
        a.add(s2f1);
        a.add(s3f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-07"), a));

        a = new Sintomas();
        a.add(s1f1);
        a.add(s2f1);
        a.add(s7f2);
        a.add(s8f2);
        registrosMonitor.push(new Registro(parseDate("2021-07-08"), a));

        DiagnosticoPorFase di = new DiagnosticoPorFase(sintomasMonitor);
        assertEquals(di.diagnostico(registrosMonitor), 3);
    }


    @Test
    void s(){

        Sintomas a ;
        a = new Sintomas();
        a.add(s1f1);
        a.add(s2f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-02"), a));

        a = new Sintomas();
        a.add(s1f1);
        a.add(s2f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-03"), a));

        a = new Sintomas();
        registrosMonitor.push(new Registro(parseDate("2021-07-04"), a));

        a = new Sintomas();
        registrosMonitor.push(new Registro(parseDate("2021-07-05"), a));

        a = new Sintomas();
        a.add(s2f1);
        a.add(s1f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-06"), a));

        DiagnosticoPorFase di = new DiagnosticoPorFase(sintomasMonitor);
        assertEquals(di.diagnostico(registrosMonitor), 1);
    }


    @Test
    void d(){

        Sintomas a ;
        a = new Sintomas();
        a.add(s1f1);
        a.add(s2f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-02"), a));

        a = new Sintomas();
        a.add(s1f1);
        a.add(s2f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-03"), a));

        a = new Sintomas();
        a.add(s1f1);
        a.add(s2f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-04"), a));

        a = new Sintomas();
        a.add(s5f2);
        a.add(s6f2);
        registrosMonitor.push(new Registro(parseDate("2021-07-05"), a));

        a = new Sintomas();
        a.add(s6f2);
        a.add(s7f2);
        registrosMonitor.push(new Registro(parseDate("2021-07-06"), a));

        a = new Sintomas();
        a.add(s1f1);
        a.add(s2f1);
        registrosMonitor.push(new Registro(parseDate("2021-07-07"), a));

        a = new Sintomas();

        registrosMonitor.push(new Registro(parseDate("2021-07-08"), a));

        DiagnosticoPorFase di = new DiagnosticoPorFase(sintomasMonitor);
        assertEquals(di.diagnostico(registrosMonitor), 5);
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}
