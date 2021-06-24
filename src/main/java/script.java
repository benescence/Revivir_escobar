import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class script {

	
	//INSERT INTO rev_ubicaciones_totales (subsector, seccion, macizo, unidad, sepultura, bis_macizo, bis)
	//VALUES
	//(1,'C4',3,5,1,0,0),
	
	public static void imprimir() {
		String subsector = "1";
		String seccion = "4";
		String macizo = "32";
		String unidad = "5";
		int sepulturaInicio = 1;
		int sepulturaFin = 85;
		String bis_macizo = "0";
		boolean tieneBis = false;
		//tieneBis = true;
		List<Integer> bises = Arrays.asList(80,1 ,40,43,42);
		
		for (int i = sepulturaInicio; i < sepulturaFin +1; i++) {
			String fin = ",";
			if (sepulturaFin == i)
				fin = "";
			if (tieneBis && bises.contains(i))
				imprimir(subsector, seccion, macizo, unidad, new Integer(i).toString(), bis_macizo, "1", fin);	
			imprimir(subsector, seccion, macizo, unidad, new Integer(i).toString(), bis_macizo, "0", fin);
		}
	}
	/*
MAC32-SEPULTURAS 79+6 BIS (SEP1BIS-SEP80BIS-SEP41BIS-SEP42BIS-SEP43BIS-SEP40BIS)
// revisar la 33 

MAC34-SEPULTURAS 80+2BIS (SEP80BIS-SEP1BIS)

MAC35-SEPULTURAS 80+3BIS (SEP80BIS-SEP1BIS-SEP43BIS)

MAC35BIS-SEPULTURAS 43

MAC36-SEPULTURAS 122+2BIS (SEP 1BIS-SEP 80BIS)

MAC37-SEPULTURAS 48+ 7 BIS (SEP47BIS-48BIS-49BIS-50BIS-51BIS-52BIS-53BIS).

MAC38-SEPULTURAS 60 +4 BIS (42BIS-43BIS-30BIS-31BIS)

MAC39-SEPULTURAS 285

MAC40-SEPULTURAS 270

MAC41-SEPULTURAS 360-

MAC41BIS-SEPULTURAS 64-

MAC42-SEPULTURAS 242-

MAC43-SEPULTURAS 72-
	 */
	public static void imprimir(String subsector,String seccion,String macizo,String
		unidad,String sepultura,String bis_macizo,String bis, String fin) {
		String insert =String.format("(%s, '%s', %s, %s, %s, %s, %s)%s",subsector, seccion, macizo, unidad, sepultura, bis_macizo, bis,fin);
		System.out.println(insert);
	
	}	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("INSERT INTO rev_ubicaciones_totales (subsector, seccion, macizo, unidad, sepultura, bis_macizo, bis)");
		System.out.println("VALUES");
		imprimir();
		System.out.println(";");
	}

}
