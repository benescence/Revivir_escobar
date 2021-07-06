package com.ungs.revivir.vista.menu.vencimientos.modificar;

import com.ungs.revivir.persistencia.entidades.Ubicacion;

public interface VencimientoInvocable {

	public void mostrar();
	
	public void actualizarVencimientos();
	
	public void actualizarVencimientos(Ubicacion nueva);
	
}