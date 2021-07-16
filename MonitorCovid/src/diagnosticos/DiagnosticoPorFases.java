package diagnosticos;

import monitor.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class DiagnosticoPorFases extends FuncionDiagnostico {

    private Map<Sintoma,String> sintomasDeUnaFase;
    private Map<String,Integer> nroSintomasDeCadaFase;
    private Map<String,Sintomas> sintomasDeUnaFecha;
    private Map<String,Map<String,Double>> estadoDiagnosticoFechas;

    public DiagnosticoPorFases(Sintomas ls) {
        super(ls);
        cargarFaseSintomasMonitor(ls);
    }

    @Override
    public int diagnostico(Registros registros) {
        int fase1=0, fase2=0;
        int dia=0;
        cargarFaseRegistroMonitor(registros);
        hallarEstadoDeDiagnostico();

        List<String> keySorters = new ArrayList<>(estadoDiagnosticoFechas.keySet());

        for (String fechaString: keySorters) {
            if(estadoDiagnosticoFechas.get(fechaString).get("PrimeraFase") >= 50 && fase1 < 3){
                fase1++;
            } else if ( fase1 >= 3 && estadoDiagnosticoFechas.get(fechaString).get("SegundaFase") >= 50 ) {
                fase2++;
            } else if ( fase1 < 3 ) {
                fase1 = 0;
            }
        }

        if (fase2>0) {
            dia = 20+fase2;
        } else if (fase1>0) {
            dia = 10+fase1;
        }
        return dia;
    }

    private String dateToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
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
                Double procentajeFaseSintoma = (double) (100 / nroSintomasFase);
                estadoDiagnosticoFechas.get(fechaRegistro).put(fase, porcentajeActualSintomaFase + procentajeFaseSintoma );
            }
        }

    }
}
