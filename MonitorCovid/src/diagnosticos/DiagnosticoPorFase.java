package diagnosticos;

import monitor.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DiagnosticoPorFase extends FuncionDiagnostico {

    private Map<Sintoma,String> sintomasDeUnaFase;   // {s1: F1, s2: F2. s3: F1,....}   Monitor Sintomas
    private Map<String,Integer> nroSintomasDeCadaFase;  // {F1: 8, F2: 9 }   Hallar el  nro de sintomas de la fase N
    private Map<Date,Sintomas> sintomasDeUnaFecha;   // {01-07: {s1, s2, s3}, 02-07: {s4,s2,s5},.... } Monitor Registros
    private Map<Date,Map<String,Double>> estadoDiagnosticoFechas;   // {01-07: {F1: 50%, F2: 20}, 02-07: {F1: 70%, F2:0%}}

    // 8 F1         100/8 = 12.8
    // 7 F2         100/7 = 14.28

    // 3   0 + 12.5 + 12.5 + 12.5 = 37.5
    // 2  14.28 + 14.28 = 28.56

    public DiagnosticoPorFase(Sintomas ls) {
        super(ls);
        cargarFaseSintomasMonitor(ls);
    }

    @Override
    public int diagnostico(Registros registros) {
        int diaDeLaFase = 0;
        cargarFaseRegistroMonitor(registros);

        for (Iterator<Map.Entry<Date, Sintomas>> entries = sintomasDeUnaFecha.entrySet().iterator(); entries.hasNext(); ) {
            Map.Entry<Date, Sintomas> entry = entries.next();

            Date fechaRegistro = entry.getKey();
            for (Sintoma s: entry.getValue()){
                String fase = sintomasDeUnaFase.get(s);
                Integer nroSintomasFase = nroSintomasDeCadaFase.get(fase);
                Double porcentajeActualSintomaFase =  estadoDiagnosticoFechas.get(fechaRegistro).get(fase);
                Double procentajeFaseSintoma = Double.valueOf(100/nroSintomasFase);
                estadoDiagnosticoFechas.get(fechaRegistro).put(fase, porcentajeActualSintomaFase + procentajeFaseSintoma );
//                System.out.println(s +" ,  "+ fase + " , " + nroSintomasFase);
            }
        }

        for (Iterator<Map.Entry<Date, Map<String,Double>>> entries = estadoDiagnosticoFechas.entrySet().iterator(); entries.hasNext(); ) {
            Map.Entry<Date, Map<String, Double>> entry1 = entries.next();
            System.out.println(dateToString(entry1.getKey()));

            for (Iterator<Map.Entry<String, Double>> entries2 = entry1.getValue().entrySet().iterator(); entries2.hasNext(); ) {
                Map.Entry<String, Double> entry2 = entries2.next();
                System.out.println(entry2.getKey() + ": " +entry2.getValue() );
            }
        }
        return diaDeLaFase;
    }

    private String dateToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    private void cargarFaseSintomasMonitor(Sintomas ls) {
        sintomasDeUnaFase = new HashMap<>();
        nroSintomasDeCadaFase = new HashMap<>();
        nroSintomasDeCadaFase.put("PrimeraFase",0);
        nroSintomasDeCadaFase.put("SegundaFase",0);

        for (Sintoma s: ls) {
            String nombre = s.getClass().getName().split("\\.")[1];
            sintomasDeUnaFase.put(s,nombre);
            nroSintomasDeCadaFase.put(nombre, nroSintomasDeCadaFase.get(nombre)+1);
        }
    }

    private void cargarFaseRegistroMonitor(Registros registros) {
        sintomasDeUnaFecha = new HashMap<Date, Sintomas>();
        estadoDiagnosticoFechas = new HashMap<>();
        for (Registro r: registros) {
            Date fechaRegistro = parseDate(dateToString(r.getFecha()));

            if (!sintomasDeUnaFecha.containsKey(fechaRegistro)) {
                sintomasDeUnaFecha.put(fechaRegistro, new Sintomas());
                estadoDiagnosticoFechas.put(fechaRegistro, new HashMap<>());
                estadoDiagnosticoFechas.get(fechaRegistro).put("PrimeraFase", new Double(0));
                estadoDiagnosticoFechas.get(fechaRegistro).put("SegundaFase", new Double(0));

            }
            for(Sintoma s: r.getSintomas()){
                sintomasDeUnaFecha.get(fechaRegistro).add(s);
            }
        }
    }

}
