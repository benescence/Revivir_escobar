package com.ungs.revivir.vista.menu.movimientos.movimientoAM;

import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.negocios.Localizador;
import com.ungs.revivir.persistencia.definidos.Sector;
import com.ungs.revivir.persistencia.entidades.Movimiento;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.TextoCentrado;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaFecha;
import com.ungs.revivir.vista.util.entradas.EntradaLista;
import com.ungs.revivir.vista.util.entradas.EntradaNumero;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaMovimientoAM extends Ventana {
	private static final long serialVersionUID = 1L;
	private Boton btnAceptar, btnCancelar;
	private EntradaTexto inObservaciones, inCausa;
	private EntradaFecha inFecha;
	private EntradaTexto inNombreFal, inApellidoFal, inCODFal;
	private Boton btnCargarFallecido, btnSelFallecido;
	
	// DATOS DE UBICACION
	private EntradaTexto inSeccion, inCementerio;
	private EntradaNumero inMacizo, inUnidad, inSepultura, pozo,
	inNicho, inFila, boveda, inParcela, inMueble;
	private JCheckBox inCheckMacizo, inCheckBis;
	private EntradaFecha inVencimiento;
	private EntradaLista<Sector> inSector;
	
	public VentanaMovimientoAM() {
		super("Alta de translado de un fallecido", 500, 500);
		inicializar();
	}
	
	public VentanaMovimientoAM(Movimiento movimiento) {
		super("Modificacion de translado de un fallecido", 200, 200);
		inicializar();
		inCausa.getTextField().setText(movimiento.getCausaTraslado());
		inObservaciones.getTextField().setText(movimiento.getObservaciones());
		inFecha.getDataChooser().setDate(movimiento.getFecha());
	}

	public void inicializar() {
		Dimension dimBoton = new Dimension(100, 25);
		
		btnAceptar = new Boton("Aceptar", dimBoton);
		btnCancelar = new Boton("Cancelar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);

		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);

		panelPrincipal.add(panelFallecido());
		panelPrincipal.add(new JSeparator());
		panelPrincipal.add(panelUbicacion());
		panelPrincipal.add(new JSeparator());
		panelPrincipal.add(panelMovimiento());
		panelPrincipal.add(panelBotones);
		compactar();
	}

	private PanelVertical panelMovimiento() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(430, 25);

		inFecha = new EntradaFecha(Almanaque.hoy(), "Fecha", dimTexto, dimEntrada);
		inCausa= new EntradaTexto("Causa Translado", dimTexto, dimEntrada);
		inObservaciones = new EntradaTexto("Observaciones", dimTexto, dimEntrada);
		
		TextoCentrado titulo = new TextoCentrado("Datos del traslado");
		titulo.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		PanelVertical ret = new PanelVertical();
		ret.setBorder(new EmptyBorder(10, 0, 0, 0));
		ret.add(titulo);
		ret.add(inFecha);
		ret.add(inCausa);
		ret.add(inObservaciones);
		return ret;		
	}

	private PanelVertical panelFallecido() {
		Dimension dimBoton = new Dimension(150, 25);
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(430, 25);
		
		inNombreFal = new EntradaTexto("Nombres", dimTexto, dimEntrada);
		inApellidoFal = new EntradaTexto("Apellidos", dimTexto, dimEntrada);
		inCODFal = new EntradaTexto("Cod Fallecido", dimTexto, dimEntrada);
		inNombreFal.habilitado(false);
		inApellidoFal.habilitado(false);
		
		btnCargarFallecido = new Boton("Cargar", dimBoton);
		btnSelFallecido = new Boton("Seleccionar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnCargarFallecido);
		panelBotones.add(btnSelFallecido);
		
		TextoCentrado titulo = new TextoCentrado("Datos del fallecido");
		titulo.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		PanelVertical ret = new PanelVertical();
		ret.setBorder(new EmptyBorder(0, 0, 10, 0));
		ret.add(titulo);
		ret.add(inNombreFal);
		ret.add(inApellidoFal);
		ret.add(inCODFal);
		ret.add(panelBotones);
		return ret;
	}

	private PanelVertical panelUbicacion() {
		Dimension dimTexto1 = new Dimension(100, 25);
		Dimension dimTexto2 = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(150, 25);
		Dimension dimEntradaVencimiento = new Dimension(430, 25);

		boveda = new EntradaNumero("Bóveda", dimTexto2, dimEntrada);
		inSeccion = new EntradaTexto("Sección", dimTexto1, dimEntrada);
		inMacizo = new EntradaNumero("Macizo", dimTexto1, dimEntrada);
		inParcela = new EntradaNumero("Parcela", dimTexto2, dimEntrada);
		inUnidad = new EntradaNumero("Unidad", dimTexto1, dimEntrada);
		inNicho = new EntradaNumero("Nicho", dimTexto2, dimEntrada);
		inFila = new EntradaNumero("Fila", dimTexto2, dimEntrada);
		inMueble = new EntradaNumero("Mueble", dimTexto2, dimEntrada);
		inSepultura = new EntradaNumero("Sepultura", dimTexto1, dimEntrada);
		pozo = new EntradaNumero("Pozo", dimTexto1, dimEntrada);
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

		TextoCentrado titulo = new TextoCentrado("Datos de la nueva ubicación");
		titulo.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		// ORGANIZACION DE PANELES
		PanelVertical panelIzquierdo = new PanelVertical();
		panelIzquierdo.add(inSector);
		panelIzquierdo.add(boveda);
		panelIzquierdo.add(inSeccion);
		panelIzquierdo.add(inMacizo);
		panelIzquierdo.add(inParcela);
		panelIzquierdo.add(inUnidad);
		
		PanelVertical panelDerecho = new PanelVertical();
		panelDerecho.setBorder(new EmptyBorder(0, 30, 0, 0));
		panelDerecho.add(inNicho);
		panelDerecho.add(inFila);
		panelDerecho.add(inMueble);
		panelDerecho.add(inSepultura);
		panelDerecho.add(pozo);
		panelDerecho.add(panelCheck);
		panelDerecho.add(inCementerio);
		
		PanelHorizontal panelHorizontal = new PanelHorizontal();
		panelHorizontal.add(panelIzquierdo);
		panelHorizontal.add(panelDerecho);

		PanelVertical ret = new PanelVertical();
		ret.setBorder(new EmptyBorder(10, 0, 10, 0));
		ret.add(titulo);
		ret.add(panelHorizontal);
		ret.add(inVencimiento);
		return ret;
	}

	private void seleccionarSector() {
		Sector sector = (Sector) inSector.getComboBox().getSelectedItem();
		habilitarCamposUbicacion(false);
		
		// Seccion esta habilitado para los 3 sectores
		inSeccion.habilitado(true);
		
		if (sector == Sector.SEPULTURAS) {
			inFila.habilitado(true);
			inSepultura.habilitado(true);
			pozo.habilitado(true);
		}

		if (sector == Sector.NICHERA) {
			inFila.habilitado(true);
			inNicho.habilitado(true);
		}

		if (sector == Sector.BOVEDA) {
			boveda.habilitado(true);
		}
		
	}
	
	private void habilitarCamposUbicacion(boolean habilitado) {
		inSeccion.habilitado(habilitado);
		inCementerio.habilitado(habilitado);
		inMacizo.habilitado(habilitado);
		inUnidad.habilitado(habilitado);
		inSepultura.habilitado(habilitado);
		pozo.habilitado(habilitado);
		inNicho.habilitado(habilitado);
		inFila.habilitado(habilitado);
		boveda.habilitado(habilitado);
		inParcela.habilitado(habilitado);
		inMueble.habilitado(habilitado);
		inCheckMacizo.setEnabled(habilitado);
		inCheckBis.setEnabled(habilitado);
	}
	
	public Boton botonAceptar() {
		return btnAceptar;
	}
	
	public Boton botonCancelar() {
		return btnCancelar;
	}
	
	public EntradaTexto getObservaciones() {
		return inObservaciones;
	}
	
	public EntradaTexto getCausa() {
		return inCausa;
	}
	
	public EntradaFecha getFecha() {
		return inFecha;
	}
	
	public EntradaTexto getNombreFal() {
		return inNombreFal;
	}
	
	public EntradaTexto getApellidoFal() {
		return inApellidoFal;
	}
	
	public EntradaTexto getCODFal() {
		return inCODFal;
	}
	
	public Boton botonCargarFallecido() {
		return btnCargarFallecido;
	}
	
	public Boton botonSelFallecido() {
		return btnSelFallecido;
	}

	public EntradaTexto getSeccion() {
		return inSeccion;
	}

	public EntradaTexto getCementerio() {
		return inCementerio;
	}
	
	public EntradaNumero getMacizo() {
		return inMacizo;
	}
	
	public EntradaNumero getUnidad() {
		return inUnidad;
	}
	
	public EntradaNumero getSepultura() {
		return inSepultura;
	}
	
	public EntradaNumero getInhumacion() {
		return pozo;
	}
	
	public EntradaNumero getNicho() {
		return inNicho;
	}
	
	public EntradaNumero getFila() {
		return inFila;
	}
	
	public EntradaNumero getCirc() {
		return boveda;
	}
	
	public EntradaNumero getParcela() {
		return inParcela;
	}
	
	public EntradaNumero getMueble() {
		return inMueble;
	}
	
	public JCheckBox getCheckMacizo() {
		return inCheckMacizo;
	}
	
	public JCheckBox getCheckBis() {
		return inCheckBis;
	}
	
	public EntradaLista<Sector> getSector() {
		return inSector;
	}

	public EntradaFecha getVencimiento() {
		return inVencimiento;
	}
	
}