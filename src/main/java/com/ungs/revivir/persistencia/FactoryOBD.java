package com.ungs.revivir.persistencia;

import com.ungs.revivir.persistencia.interfaces.CargoOBD;
import com.ungs.revivir.persistencia.interfaces.ClienteOBD;
import com.ungs.revivir.persistencia.interfaces.ConfiguracionOBD;
import com.ungs.revivir.persistencia.interfaces.ExpensasOBD;
import com.ungs.revivir.persistencia.interfaces.FallecidoOBD;
import com.ungs.revivir.persistencia.interfaces.MovimientoOBD;
import com.ungs.revivir.persistencia.interfaces.PDFOBD;
import com.ungs.revivir.persistencia.interfaces.PagoOBD;
import com.ungs.revivir.persistencia.interfaces.ResponsableOBD;
import com.ungs.revivir.persistencia.interfaces.ServicioOBD;
import com.ungs.revivir.persistencia.interfaces.UbicacionLibreOBD;
import com.ungs.revivir.persistencia.interfaces.UbicacionOBD;
import com.ungs.revivir.persistencia.interfaces.UbicacionesTotalesOBD;
import com.ungs.revivir.persistencia.interfaces.UsuarioOBD;
import com.ungs.revivir.persistencia.mysql.CargoOBDMySQL;
import com.ungs.revivir.persistencia.mysql.ClienteOBDMySQL;
import com.ungs.revivir.persistencia.mysql.ConfiguracionOBDMySQL;
import com.ungs.revivir.persistencia.mysql.ExpensasOBDMySQL;
import com.ungs.revivir.persistencia.mysql.FallecidoOBDMySQL;
import com.ungs.revivir.persistencia.mysql.MovimientoOBDMySQL;
import com.ungs.revivir.persistencia.mysql.PDFOBDMySQL;
import com.ungs.revivir.persistencia.mysql.PagoOBDMySQL;
import com.ungs.revivir.persistencia.mysql.ResponsableOBDMySQL;
import com.ungs.revivir.persistencia.mysql.ServicioOBDMySQL;
import com.ungs.revivir.persistencia.mysql.UbicacionLibreOBDMySQL;
import com.ungs.revivir.persistencia.mysql.UbicacionOBDMySQL;
import com.ungs.revivir.persistencia.mysql.UbicacionTotalOBDMySQL;
import com.ungs.revivir.persistencia.mysql.UsuarioOBDMYSQL;

public class FactoryOBD {
	
	public static CargoOBD crearCargoOBD() {
		return new CargoOBDMySQL();
	}
	
	public static ClienteOBD crearClienteOBD() {
		return new ClienteOBDMySQL();
	}
	
	public static ConfiguracionOBD crearConfiguracionOBD() {
		return new ConfiguracionOBDMySQL();
	}
	
	public static ExpensasOBD crearExpensasOBD() {
		return new ExpensasOBDMySQL();
	}

	public static FallecidoOBD crearFallecidoOBD() {
		return new FallecidoOBDMySQL();
	}

	public static MovimientoOBD crearMovimientoOBD() {
		return new MovimientoOBDMySQL();
	}

	public static PagoOBD crearPagoOBD() {
		return new PagoOBDMySQL();
	}
	
	public static PDFOBD crearPDFOBD() {
		return new PDFOBDMySQL();
	}

	public static ResponsableOBD crearResponsableOBD() {
		return new ResponsableOBDMySQL();
	}

	public static ServicioOBD crearServicioOBD() {
		return new ServicioOBDMySQL();
	}

	public static UbicacionesTotalesOBD crearUbicacionTotalOBD() {	
		return new UbicacionTotalOBDMySQL();
	}
	
	public static UbicacionLibreOBD crearUbicacionLibreOBD() {
		return new UbicacionLibreOBDMySQL();
	}

	public static UbicacionOBD crearUbicacionOBD() {
		return new UbicacionOBDMySQL();
	}
	
	public static UsuarioOBD crearUsuarioOBD() {
		return new UsuarioOBDMYSQL();
	}
		
}