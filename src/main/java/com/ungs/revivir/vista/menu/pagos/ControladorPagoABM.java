package com.ungs.revivir.vista.menu.pagos;

import java.sql.Date;
import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.Busqueda;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.PagoManager;
import com.ungs.revivir.negocios.verificador.VerificadorBorrado;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.vista.menu.pagos.pagoAM.ControladorPagoAM;
import com.ungs.revivir.vista.menu.pagos.pagoAM.PagoInvocable;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.reportes.ReporteMovimientosDiarios;
import com.ungs.revivir.vista.reportes.ReporteVariosCargos;
import com.ungs.revivir.vista.util.Popup;

public class ControladorPagoABM implements ControladorInterno, PagoInvocable {
	private VentanaPagoABM ventana;
	private ControladorPrincipal invocador;
	
	public ControladorPagoABM(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaPagoABM();
		ventana.botonAgregar().addActionListener(e -> agregar());
		ventana.botonModificar().addActionListener(e -> modificar());
		ventana.botonEliminar().addActionListener(e -> eliminar());
		ventana.botonFactura().addActionListener(e -> factura());
		ventana.botonMovimientos().addActionListener(e -> movimientos());
		ventana.botonBuscar().setAccion(e -> actualizarPagos());
		ventana.botonLimpiar().setAccion(e -> limpiar());
	}
	
	private void limpiar() {
		ventana.getNombreFal().getTextField().setText("");
		ventana.getApellidoFal().getTextField().setText("");
		ventana.getCODFal().getTextField().setText("");
	}
	
	private void movimientos() {
		Date fecha = ventana.getFechaDesde().getValor();
		new ReporteMovimientosDiarios(fecha);
	}
	
	private void factura() {
		List<Pago> lista = ventana.getTabla().obtenerSeleccion();
		new ReporteVariosCargos(lista);
	}
	
	private void agregar() {
		invocador.getVentana().setEnabled(false);
		new ControladorPagoAM(this);
	}

	private void modificar() {
		List<Pago> lista = ventana.getTabla().obtenerSeleccion();
		
		if (lista.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 pago para modificarlo");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorPagoAM(this, lista.get(0));
	}
	
	private void eliminar() {
		try {
			List<Pago> lista = ventana.getTabla().obtenerSeleccion();
			if (lista.size() != 1) {
				Popup.mostrar("Debe seleccionar exactamente 1 pago para borrarlo.");
				return;
			}
			
			if (VerificadorBorrado.puedeBorrar(lista.get(0)) &&
					Popup.confirmar("¿Seguro de que desea eliminar los registros seleccionados?"))
				PagoManager.eliminar(lista.get(0));
			
			actualizarPagos();
		
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
	public void actualizarPagos() {
		Date desde = ventana.getFechaDesde().getValor();
		Date hasta = ventana.getFechaHasta().getValor();
		String nombre = ventana.getNombreFal().getValor();
		String apellido = ventana.getApellidoFal().getValor();
		Integer codigo = ventana.getCODFal().getValor();
		System.out.println(nombre);
		System.out.println(apellido);
		System.out.println(codigo);

		try {
			List<Fallecido> fallecidos = FallecidoManager.traer(nombre, apellido, codigo);
			if (fallecidos.size() != 1) {
				Popup.mostrar("Debe especificar exactamente un fallecido para poder ver sus pagos.");
				return;
			}
			
			Fallecido fallecido = fallecidos.get(0);		
			List<Pago> pagos = Busqueda.traerPagos(fallecido, desde, hasta);
			ventana.getTabla().recargar(pagos);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}