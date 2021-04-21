package com.ungs.revivir.vista.menu.movimientos;

import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.busqueda.Relacionador;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.MovimientoManager;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Movimiento;
import com.ungs.revivir.vista.menu.movimientos.movimientoAM.ControladorMovimientoAM;
import com.ungs.revivir.vista.menu.movimientos.movimientoAM.MovimientoInvocable;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.seleccion.fallecidos.ControladorSeleccionarFallecido;
import com.ungs.revivir.vista.seleccion.fallecidos.FallecidoSeleccionable;
import com.ungs.revivir.vista.util.Popup;

public class ControladorMovimientoABM implements ControladorInterno, FallecidoSeleccionable, MovimientoInvocable {
	private VentanaMovimientoABM ventana;
	private Fallecido fallecido;
	
	public ControladorMovimientoABM(ControladorPrincipal invocador) {
		ventana = new VentanaMovimientoABM();
		ventana.botonAgregar().setAccion(e -> agregar());
		ventana.botonSelFallecido().setAccion(e -> seleccionarFallecido());
		ventana.botonCargarFallecido().setAccion(e -> cargarFallecido());
		ventana.botonEliminar().setAccion(e -> eliminar());
	}
	
	private void eliminar() {
		try {
			List<Movimiento> lista = ventana.getTabla().obtenerSeleccion();
			if (lista.size() != 1) {
				Popup.mostrar("Debe seleccionar exactamente 1 movimiento para borrarlo.");
				return;
			}
			
			if (Popup.confirmar("¿Esta seguro de que desea eliminar los registros seleccionados?"))
				MovimientoManager.eliminar(lista.get(0));
			
			actualizarMovimientos();
		
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}

	}
	
	private void agregar() {
		ventana.deshabilitar();
		if (fallecido == null)
			new ControladorMovimientoAM(this);
		else
			new ControladorMovimientoAM(this, fallecido);
	}

	private void seleccionarFallecido() {
		ventana.deshabilitar();
		new ControladorSeleccionarFallecido(this);
	}

	public void actualizarMovimientos() {
		List<Movimiento> lista = Relacionador.traerMovimiento(fallecido);
		ventana.getTabla().recargar(lista);
		if (lista.size() == 0)
			Popup.mostrar("No se ha encontrado ningun resultado con los criterios ingresados.");
	}
	
	private void cargarFallecido() {
		ventana.requestFocusInWindow();
		
		/*String DNI = ventana.getDNIFal().getTextField().getText();
		if (!Validador.DNI(DNI)) {
			Popup.mostrar("El DNI solo puede consistir de numeros");
			return;
		}*/
		Integer cod_fallecido = Integer.parseInt(ventana.getCODFal().getTextField().getText());
		if (!Validador.cod_fallecido(Integer.toString(cod_fallecido))) {
			Popup.mostrar("El codigo solo puede consistir de numeros");
			return;
		}
		//Fallecido directo = FallecidoManager.traerPorDNI(DNI);
		Fallecido directo = FallecidoManager.traerPorCOD(cod_fallecido);
		if (directo == null) {
			Popup.mostrar("No hay registros de un fallecidos con el codigo "+cod_fallecido+".");
			return;
		}
		seleccionarFallecido(directo);
	}

	@Override
	public boolean finalizar() {
		ventana.dispose();
		ventana = null;
		return true;
	}

	@Override
	public JInternalFrame getVentana() {
		return ventana;
	}

	@Override
	public void seleccionarFallecido(Fallecido fallecido) {
		this.fallecido = fallecido;
		ventana.getNombreFal().getTextField().setText(fallecido.getNombre());
		ventana.getApellidoFal().getTextField().setText(fallecido.getApellido());
		//ventana.getDNIFal().getTextField().setText(fallecido.getDNI());
		ventana.getCODFal().getTextField().setText(Integer.toString(fallecido.getCod_fallecido()));
		actualizarMovimientos();
	}

	@Override
	public void mostrar() {
		ventana.mostrar();
	}

}