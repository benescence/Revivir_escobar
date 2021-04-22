package com.ungs.revivir.negocios;

import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.persistencia.definidos.Sector;
import com.ungs.revivir.persistencia.definidos.SubSector;

public class Localizador {
	
	public static List<Sector> traerSectores() {
		List<Sector> ret = new ArrayList<>();
		ret.add(Sector.SEPULTURAS);
		ret.add(Sector.NICHERA);
		ret.add(Sector.BOVEDA);
		return ret;
	}
	
	public static List<SubSector> traerSubSectores(Sector sector) {
		List<SubSector> ret = new ArrayList<>();
		ret.add(SubSector.SECCION_A);
		ret.add(SubSector.SECCION_B);
		ret.add(SubSector.SECCION_C);
		return ret;
	}
	
	public static SubSector mapearSector(Sector sector) {
		if (sector == Sector.SEPULTURAS)
			return SubSector.SECCION_A;
		if (sector == Sector.BOVEDA)
			return SubSector.SECCION_B;
		if (sector == Sector.NICHERA)
			return SubSector.SECCION_C;
		return null;
	}

	public static Sector mapearSector(SubSector subsector) {
		if (subsector == SubSector.SECCION_A)
			return Sector.SEPULTURAS;
		if (subsector == SubSector.SECCION_B)
			return Sector.BOVEDA;
		if (subsector == SubSector.SECCION_C)
			return Sector.NICHERA;
		return null;
	}	
	
}