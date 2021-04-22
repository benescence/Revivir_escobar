package com.ungs.revivir.vista.menu.principal;

import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.negocios.Localizador;
import com.ungs.revivir.persistencia.definidos.Sector;
import com.ungs.revivir.persistencia.definidos.TipoFallecimiento;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.TextoCentrado;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaFecha;
import com.ungs.revivir.vista.util.entradas.EntradaLista;
import com.ungs.revivir.vista.util.entradas.EntradaNumero;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaAltaCompleta extends Ventana {
	private static final long serialVersionUID = 1L;
	private Boton btnAceptar, btnCancelar, btnLimpiarTodo;
		
	// DATOS CLIENTE
	private EntradaTexto inNombreCli, inApellidoCli, inDNICli, inTelefono, inEmail, inDomicilio;
	private Boton btnCargarCliente, btnSelCliente, btnLimpiarCliente;
	
	// DATOS DEL DIFUNTO
	private EntradaTexto inNombreFal, inApellidoFal, inCocheria,inCodFal;
	private EntradaFecha inFFallecimiento, inFIngreso;
	private EntradaLista<TipoFallecimiento> inTipo;

	// DATOS DE UBICACION
	private EntradaNumero inUnidad, inFila, inMacizo, inNicho, inSepultura, inParcela, inPozo, inBoveda, inMueble;
	private EntradaTexto inSeccion, inCementerio;
	private JCheckBox inCheckMacizo, inCheckBis;
	private EntradaLista<Sector> inSector;
	private EntradaFecha inVencimiento;
	
	public VentanaAltaCompleta() {
		super("Alta completa de servicio");
		Dimension dimBoton = new Dimension(150, 25);
		
		btnAceptar = new Boton("Aceptar", dimBoton);
		btnCancelar = new Boton("Cancelar", dimBoton);
		btnLimpiarTodo = new Boton("Limpiar todo", dimBoton);
		
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnAceptar);
		panelBotones.add(btnLimpiarTodo);
		panelBotones.add(btnCancelar);
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		PanelHorizontal panelPersonas = new PanelHorizontal();
		panelPersonas.add(panelCliente());
		panelPersonas.add(new JSeparator(SwingConstants.VERTICAL));
		panelPersonas.add(panelFallecido());
		panelPersonas.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		panelPrincipal.add(panelPersonas);
		panelPrincipal.add(new JSeparator());
		panelPrincipal.add(panelUbicacion());
		panelPrincipal.add(panelBotones);
		compactar();
	}

	private PanelVertical panelCliente() {
		Dimension dimTexto = new Dimension(140, 25);
		Dimension dimEntrada = new Dimension(200, 25);
		Dimension dimBoton = new Dimension(110, 25);

		inNombreCli = new EntradaTexto("Nombres", dimTexto, dimEntrada);
		inApellidoCli = new EntradaTexto("Apellidos", dimTexto, dimEntrada);
		inDNICli = new EntradaTexto("DNI", dimTexto, dimEntrada);
		inTelefono = new EntradaTexto("Telefono", dimTexto, dimEntrada);
		inEmail = new EntradaTexto("E-Mail", dimTexto, dimEntrada);
		inDomicilio = new EntradaTexto("Domicilio", dimTexto, dimEntrada);
		
		btnCargarCliente = new Boton("Cargar", dimBoton);
		btnSelCliente = new Boton("Seleccionar", dimBoton);
		btnLimpiarCliente = new Boton("Limpiar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnCargarCliente);
		panelBotones.add(btnSelCliente);
		panelBotones.add(btnLimpiarCliente);

		TextoCentrado titulo = new TextoCentrado("Datos del cliente");
		titulo.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		PanelVertical ret = new PanelVertical();
		ret.setBorder(new EmptyBorder(0, 0, 0, 15));
		ret.add(titulo);
		ret.add(inNombreCli);
		ret.add(inApellidoCli);
		ret.add(inDNICli);
		ret.add(inTelefono);
		ret.add(inEmail);
		ret.add(inDomicilio);
		ret.add(panelBotones);
		return ret;
	}
	
	private PanelVertical panelFallecido() {
		Dimension dimTexto = new Dimension(140, 25);
		Dimension dimEntrada = new Dimension(200, 25);
		
		inNombreFal = new EntradaTexto("Nombres", dimTexto, dimEntrada);
		inApellidoFal = new EntradaTexto("Apellidos", dimTexto, dimEntrada);
		inCodFal = new EntradaTexto("Cod Fallecido", dimTexto, dimEntrada);
		inCocheria = new EntradaTexto("Cochería", dimTexto, dimEntrada);
		inFFallecimiento = new EntradaFecha(null, "Fecha de fallecimiento", dimTexto, dimEntrada);
		inFIngreso = new EntradaFecha(Almanaque.hoy(), "Fecha de Ingreso", dimTexto, dimEntrada);
		inTipo = new EntradaLista<>("Tipo de fallecimiento", dimTexto, dimEntrada);
		for (TipoFallecimiento tipo : TipoFallecimiento.values())
			inTipo.getComboBox().addItem(tipo);
		inTipo.getComboBox().setSelectedItem(TipoFallecimiento.NO_TRAUMATICO);
		
		TextoCentrado titulo = new TextoCentrado("Datos del fallecido");
		titulo.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		PanelVertical ret = new PanelVertical();
		ret.setBorder(new EmptyBorder(0, 15, 0, 0));
		ret.add(titulo);
		ret.add(inNombreFal);
		ret.add(inApellidoFal);
		ret.add(inCodFal);
		ret.add(inCocheria);
		ret.add(inTipo);
		ret.add(inFFallecimiento);
		ret.add(inFIngreso);
		return ret;
	}
	
	private PanelVertical panelUbicacion() {
		Dimension dimTexto1 = new Dimension(140, 25);
		Dimension dimTexto2 = new Dimension(140, 25);
		Dimension dimEntrada = new Dimension(200, 25);
		Dimension dimEntradaVencimiento = new Dimension(570, 25);

		inBoveda = new EntradaNumero("Boveda", dimTexto2, dimEntrada);
		inSeccion = new EntradaTexto("Sección", dimTexto1, dimEntrada);
		inMacizo = new EntradaNumero("Macizo", dimTexto1, dimEntrada);
		inParcela = new EntradaNumero("Parcela", dimTexto2, dimEntrada);
		inUnidad = new EntradaNumero("Unidad", dimTexto1, dimEntrada);
		inNicho = new EntradaNumero("Nicho", dimTexto2, dimEntrada);
		inFila = new EntradaNumero("Fila", dimTexto2, dimEntrada);
		inMueble = new EntradaNumero("Mueble", dimTexto2, dimEntrada);
		inSepultura = new EntradaNumero("Sepultura", dimTexto1, dimEntrada);
		inPozo = new EntradaNumero("Pozo", dimTexto1, dimEntrada);
		inCementerio = new EntradaTexto("Cementerio", dimTexto1, dimEntrada);
		inVencimiento = new EntradaFecha(Almanaque.hoy(), "Vencimiento", dimTexto1, dimEntradaVencimiento);

		inCheckBis = new JCheckBox("Bis");
		inCheckMacizo = new JCheckBox("Macizo");
		PanelHorizontal panelCheck = new PanelHorizontal();
		panelCheck.add(inCheckBis);
		panelCheck.add(inCheckMacizo);
		
		inSector = new EntradaLista<>("Sector", dimTexto1, dimEntrada);

		for (Sector sector : Localizador.traerSectores())
			inSector.getComboBox().addItem(sector);
		
		// EL SUB SECTOR DEPENDE DEL SECTOR ESCOGIDO
		inSector.getComboBox().addActionListener(e -> seleccionarSector());
		inSector.getComboBox().setSelectedIndex(0);

	
		// ORGANIZACION DE PANELES
		PanelVertical panelIzquierdo = new PanelVertical();
		panelIzquierdo.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelIzquierdo.add(inSector);
		panelIzquierdo.add(inBoveda);
		panelIzquierdo.add(inSeccion);
		panelIzquierdo.add(inMacizo);
		panelIzquierdo.add(inParcela);
		panelIzquierdo.add(inUnidad);
		
		PanelVertical panelDerecho = new PanelVertical();
		panelDerecho.setBorder(new EmptyBorder(10, 30, 0, 0));
		panelDerecho.add(inNicho);
		panelDerecho.add(inFila);
		panelDerecho.add(inMueble);
		panelDerecho.add(inSepultura);
		panelDerecho.add(inPozo);
		panelDerecho.add(panelCheck);
		panelDerecho.add(inCementerio);
		
		PanelHorizontal panelHorizontal = new PanelHorizontal();
		panelHorizontal.add(panelIzquierdo);
		panelHorizontal.add(panelDerecho);

		PanelVertical ret = new PanelVertical();
		ret.setBorder(new EmptyBorder(10, 0, 0, 0));
		ret.add(new TextoCentrado("Datos de la ubicación"));
		ret.add(panelHorizontal);
		ret.add(inVencimiento);
		return ret;
	}
	
	private void seleccionarSector() {
		
		Sector sector = (Sector) inSector.getComboBox().getSelectedItem();
		habilitarCamposUbicacion(false);
		
		// La seccion esta habilitada para los 3 sectores
		inSeccion.habilitado(true);
		
		if (sector == Sector.SEPULTURAS) {
			inFila.habilitado(true);
			inSepultura.habilitado(true);
			inPozo.habilitado(true);
		}

		if (sector == Sector.NICHERA) {
			inFila.habilitado(true);
			inNicho.habilitado(true);
		}

		if (sector == Sector.BOVEDA) {
			inBoveda.habilitado(true);
		}
		
	}
	
	private void habilitarCamposUbicacion(boolean habilitado) {
		inSeccion.habilitado(habilitado);
		inCementerio.habilitado(habilitado);
		inMacizo.habilitado(habilitado);
		inUnidad.habilitado(habilitado);
		inSepultura.habilitado(habilitado);
		inPozo.habilitado(habilitado);
		inNicho.habilitado(habilitado);
		inFila.habilitado(habilitado);
		inBoveda.habilitado(habilitado);
		inParcela.habilitado(habilitado);
		inMueble.habilitado(habilitado);
		inCheckMacizo.setEnabled(habilitado);
		inCheckBis.setEnabled(habilitado);
	}
	

	
	
	//************************************* SOLO GETTERS A PARTIR DE ESTE PUNTO *********************************************
	
	public Boton botonAceptar() {
		return btnAceptar;
	}

	public Boton botonCancelar() {
		return btnCancelar;
	}

	public Boton botonLimpiarTodo() {
		return btnLimpiarTodo;
	}

	public EntradaTexto getNombreCli() {
		return inNombreCli;
	}

	public EntradaTexto getApellidoCli() {
		return inApellidoCli;
	}

	public EntradaTexto getDNICli() {
		return inDNICli;
	}

	public EntradaTexto getTelefono() {
		return inTelefono;
	}

	public EntradaTexto getEmail() {
		return inEmail;
	}

	public EntradaTexto getDomicilio() {
		return inDomicilio;
	}

	public Boton botonCargarCliente() {
		return btnCargarCliente;
	}

	public Boton botonSelCliente() {
		return btnSelCliente;
	}

	public Boton botonLimpiarCliente() {
		return btnLimpiarCliente;
	}

	public EntradaTexto getNombreFal() {
		return inNombreFal;
	}

	public EntradaTexto getApellidoFal() {
		return inApellidoFal;
	}

	public EntradaTexto getCodFal() {
		return inCodFal;
	}
	
	public EntradaTexto getCocheria() {
		return inCocheria;
	}

	public EntradaFecha getFFallecimiento() {
		return inFFallecimiento;
	}

	public EntradaFecha getFIngreso() {
		return inFIngreso;
	}

	public EntradaLista<TipoFallecimiento> getTipo() {
		return inTipo;
	}

	public EntradaNumero getUnidad() {
		return inUnidad;
	}

	public EntradaNumero getFila() {
		return inFila;
	}

	public EntradaNumero getMacizo() {
		return inMacizo;
	}

	public EntradaNumero getNicho() {
		return inNicho;
	}

	public EntradaNumero getSepultura() {
		return inSepultura;
	}

	public EntradaNumero getParcela() {
		return inParcela;
	}

	public EntradaNumero getInhumacion() {
		return inPozo;
	}

	public EntradaNumero getCirc() {
		return inBoveda;
	}

	public EntradaNumero getMueble() {
		return inMueble;
	}

	public EntradaTexto getSeccion() {
		return inSeccion;
	}

	public EntradaTexto getCementerio() {
		return inCementerio;
	}

	public JCheckBox getCheckMacizo() {
		return inCheckMacizo;
	}

	public JCheckBox getCheckBis() {
		return inCheckBis;
	}

	public JComboBox<Sector> getSector() {
		return inSector.getComboBox();
	}

	public EntradaFecha getVencimiento() {
		return inVencimiento;
	}
	
}