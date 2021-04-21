package com.ungs.revivir.persistencia.interfaces;

import java.sql.Date;
import java.util.List;

import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Pago;

public interface PagoOBD {

	// METODOS COMUNES
	
	public void insert(Pago pago);
	
	public void update(Pago pago);
	
	public void delete(Pago pago);

	public Pago selectByID(Integer ID);

	public Pago ultimoInsertado();
	
	public List<Pago > select();
	
	// METODOS ESPECIFICOS
		
	public List<Pago> selectByCargo(Cargo cargo);
	
	public List<Pago> selectByFecha(Date fecha);
	
	public List<Pago> selectByCargosDesdeHasta(List<Cargo> cargos, Date desde, Date hasta);
	
}