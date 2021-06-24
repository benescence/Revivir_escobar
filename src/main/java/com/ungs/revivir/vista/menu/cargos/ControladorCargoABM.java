package com.ungs.revivir.vista.menu.cargos;

import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.manager.CargoManager;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.verificador.VerificadorBorrado;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.vista.menu.cargos.cargoAM.CargoInvocable;
import com.ungs.revivir.vista.menu.cargos.cargoAM.ControladorCargoAM;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.seleccion.fallecidos.ControladorSeleccionarFallecido;
import com.ungs.revivir.vista.seleccion.fallecidos.FallecidoSeleccionable;
import com.ungs.revivir.vista.util.Popup;

public class ControladorCargoABM implements ControladorInterno, FallecidoSeleccionable, CargoInvocable {
	private VentanaCargoABM ventana;
	private ControladorPrincipal invocador;
	private Fallecido fallecido;
	private Cliente cliente;
	
	public ControladorCargoABM(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaCargoABM();
		
		ventana.botonAgregar().addActionListener(e -> agregar());
		ventana.botonModificar().addActionListener(e -> modificar());
		ventana.botonEliminar().addActionListener(e -> eliminar());
		
		ventana.botonSelFallecido().setAccion(e -> seleccionarFallecido());
		ventana.botonCargarFallecido().setAccion(e -> cargarFallecido());
	}
	
	private void seleccionarFallecido() {
		ventana.deshabilitar();
		new ControladorSeleccionarFallecido(this);
	}

	private void cargarFallecido() {
		ventana.requestFocusInWindow();
		
		Integer cod_fallecido = Integer.parseInt(ventana.getCODFal().getTextField().getText());
		if (!Validador.cod_fallecido(Integer.toString(cod_fallecido))) {
			Popup.mostrar("El Codigo solo puede consistir de numeros");
			return;
		}

		Fallecido directo = FallecidoManager.traerPorCOD(cod_fallecido);
		if (directo == null) {
			Popup.mostrar("No hay registros de un fallecido con el codigo: "+cod_fallecido+".");
			return;
		}
		
		seleccionarFallecido(directo);
		ventana.getCODFal().getTextField().requestFocusInWindow();
	}

	private void agregar() { 
		invocador.getVentana().setEnabled(false);
		if (fallecido == null)
			new ControladorCargoAM(this);
		else
			new ControladorCargoAM(this, fallecido);
	}

	private void modificar() {
		List<Cargo> lista = ventana.getTabla().obtenerSeleccion();
		
		if (lista.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 cargo para modificarlo");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorCargoAM(this, lista.get(0));
	}
	
	private void eliminar() {
		try {
			List<Cargo> lista = ventana.getTabla().obtenerSeleccion();
			if (lista.size() != 1) {
				Popup.mostrar("Debe seleccionar exactamente 1 cargo para borrarlo.");
				return;
			}
			
			if (VerificadorBorrado.puedeBorrar(lista.get(0)) &&
					Popup.confirmar("¿Esta seguro de que desea eliminar los registros seleccionados?"))
				CargoManager.eliminar(lista.get(0));
			
			actualizarCargos();
		
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}

	}

	@Override
	public boolean finalizar() {
		return true;
	}
	
	public void mostrar() {
		invocador.getVentana().mostrar();
		invocador.getVentana().toFront();
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
		ventana.getCODFal().getTextField().setText(Integer.toString(fallecido.getCod_fallecido()));
		actualizarCargos();
	}

	@Override
	public void actualizarCargos() {
		if (cliente != null || fallecido != null) {
			List<Cargo> lista = CargoManager.traerPorFallecidoCliente(fallecido, cliente);
			ventana.getTabla().recargar(lista);
		}		
	}

}