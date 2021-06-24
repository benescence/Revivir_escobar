package com.ungs.revivir.vista.menu.cargos;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.vista.tablas.TablaCargos;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.TextoCentrado;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.VentanaInterna;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaCargoABM extends VentanaInterna {
	private static final long serialVersionUID = 1L;
	private TablaCargos tabla;
	private Boton btnAgregar, btnModificar, btnEliminar;
	private Boton btnCargarFallecido, btnSelFallecido;
	private Boton btnCargarCliente, btnSelCliente;
	private EntradaTexto inNombreFal, inApellidoFal, inCODFal;
	
	public VentanaCargoABM() {
		super("Gestion de cargos", 500, 500);
		
		tabla = new TablaCargos(new ArrayList<>());
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		Dimension dimBoton = new Dimension(100, 25);
		btnAgregar = new Boton("Agregar", dimBoton);
		btnModificar = new Boton("Modificar", dimBoton);
		btnEliminar = new Boton("Eliminar", dimBoton);

		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnAgregar);
		panelBotones.add(btnModificar);
		panelBotones.add(btnEliminar);
		
		PanelHorizontal panelBusqueda = new PanelHorizontal();
		panelBusqueda.add(panelFallecido());
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(panelBusqueda);
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
	}
	
	private PanelVertical panelFallecido() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(300, 25);
		Dimension dimBoton = new Dimension(150, 25);
		
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
		
		PanelVertical ret = new PanelVertical();
		ret.setBorder(new EmptyBorder(0, 0, 10, 10));
		ret.add(new TextoCentrado("Datos del fallecido"));
		ret.add(inNombreFal);
		ret.add(inApellidoFal);
		ret.add(inCODFal);
		ret.add(panelBotones);
		return ret;
	}
	
	public TablaCargos getTabla() {
		return tabla;
	}
	
	public Boton botonAgregar() {
		return btnAgregar;
	}

	public Boton botonModificar() {
		return btnModificar;
	}

	public Boton botonEliminar() {
		return btnEliminar;
	}

	public Boton botonSelCliente() {
		return btnSelCliente;
	}
	
	public Boton botonSelFallecido() {
		return btnSelFallecido;
	}

	public Boton botonCargarCliente() {
		return btnCargarCliente;
	}
		
	public Boton botonCargarFallecido() {
		return btnCargarFallecido;
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
	
}