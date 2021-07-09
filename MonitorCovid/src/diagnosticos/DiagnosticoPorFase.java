package diagnosticos;

import monitor.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DiagnosticoPorFase extends FuncionDiagnostico {

    private Map<Sintoma,String> sintomasDeUnaFase;   // {s1: F1, s2: F2. s3: F1,....}   Monitor Sintomas
    private Map<String,Integer> nroSintomasDeCadaFase;  // {F1: 8, F2: 9 }   Hallar el  nro de sintomas de la fase N
    private Map<String,Sintomas> sintomasDeUnaFecha;   // {01-07: {s1, s2, s3}, 02-07: {s4,s2,s5},.... } Monitor Registros
    private Map<String,Map<String,Double>> estadoDiagnosticoFechas;   // {01-07: {F1: 50%, F2: 20}, 02-07: {F1: 70%, F2:0%}}

    public DiagnosticoPorFase(Sintomas ls) {
        super(ls);
        cargarFaseSintomasMonitor(ls);
    }

    @Override
    public int diagnostico(Registros registros) {
        int fase1=0, fase2=0;
        cargarFaseRegistroMonitor(registros);
        hallarEstadoDeDiagnostico();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (Registro r: registros) {
            String fechaString= format.format (r.getFecha());
            if(estadoDiagnosticoFechas.get(fechaString).get("PrimeraFase") >= 50 && fase1 < 3){
                fase1++;
            } else if ( fase1 >= 3 && estadoDiagnosticoFechas.get(fechaString).get("SegundaFase") >= 50 ) {
                fase2++;
            } else if ( fase1 < 3 ) {
                fase1 = 0;
            }
        }
        return fase1+fase2;
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
        sintomasDeUnaFecha = new HashMap<String, Sintomas>();
        estadoDiagnosticoFechas = new HashMap<>();
        for (Registro r: registros) {
            String fechaRegistro = dateToString(r.getFecha());

            if (!sintomasDeUnaFecha.containsKey(fechaRegistro)) {
                sintomasDeUnaFecha.put(fechaRegistro, new Sintomas());
                estadoDiagnosticoFechas.put(fechaRegistro, new HashMap<>());
                estadoDiagnosticoFechas.get(fechaRegistro).put("PrimeraFase", 0.0);
                estadoDiagnosticoFechas.get(fechaRegistro).put("SegundaFase", 0.0);
            }
            for(Sintoma s: r.getSintomas()){
                sintomasDeUnaFecha.get(fechaRegistro).add(s);
            }
        }
    }

    private void hallarEstadoDeDiagnostico() {
        for (Iterator<Map.Entry<String, Sintomas>> entries = sintomasDeUnaFecha.entrySet().iterator(); entries.hasNext(); ) {
            Map.Entry<String, Sintomas> entry = entries.next();

            String fechaRegistro = entry.getKey();
            for (Sintoma s: entry.getValue()){
                String fase = sintomasDeUnaFase.get(s);
                Integer nroSintomasFase = nroSintomasDeCadaFase.get(fase);
                Double porcentajeActualSintomaFase =  estadoDiagnosticoFechas.get(fechaRegistro).get(fase);
                Double procentajeFaseSintoma = Double.valueOf(100/nroSintomasFase);
                estadoDiagnosticoFechas.get(fechaRegistro).put(fase, porcentajeActualSintomaFase + procentajeFaseSintoma );
            }
        }

    }

}
