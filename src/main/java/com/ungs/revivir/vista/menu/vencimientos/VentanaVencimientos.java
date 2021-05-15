package com.ungs.revivir.vista.menu.vencimientos;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.Almanaque;
import com.ungs.revivir.negocios.Localizador;
import com.ungs.revivir.persistencia.definidos.Sector;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.tablas.TablaVencimientos;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.VentanaInterna;
import com.ungs.revivir.vista.util.entradas.EntradaFecha;
import com.ungs.revivir.vista.util.entradas.EntradaLista;

public class VentanaVencimientos extends VentanaInterna {
	private static final long serialVersionUID = 1L;
	private TablaVencimientos tabla;
	private EntradaLista<Sector> inSector;
	private EntradaFecha inDesde, inHasta;
	private Boton btnBuscar, btnLimpiar, btnVerClientes, btnModificar,btnImpLista, btnImpNotificaciones;
	
	public VentanaVencimientos() {
		super("Gestion de vencimientos", 500, 500);
		tabla = new TablaVencimientos(new ArrayList<Ubicacion>());
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		Dimension dimBoton = new Dimension(150, 25);
		btnBuscar = new Boton("Buscar", dimBoton);
		btnLimpiar = new Boton("Limpiar", dimBoton);
		btnVerClientes = new Boton("Ver clientes", dimBoton);
		btnModificar = new Boton("Modificar", dimBoton);
		btnImpLista = new Boton("Imprimir Listado", dimBoton);
		btnImpNotificaciones= new Boton("Notificaciones", dimBoton);
		
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnBuscar);
		panelBotones.add(btnLimpiar);
		panelBotones.add(btnVerClientes);
		panelBotones.add(btnModificar);
		panelBotones.add(btnImpLista);
		panelBotones.add(btnImpNotificaciones);
		
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(panelBusqueda());
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
	}
	
	private PanelHorizontal panelBusqueda() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(300, 25);

		inSector = new EntradaLista<>("Sector", dimTexto, dimEntrada);

		for (Sector sector : Localizador.traerSectores())
			inSector.getComboBox().addItem(sector);
		
		inSector.getComboBox().setSelectedIndex(0);

		inDesde = new EntradaFecha(Almanaque.hoy(), "Desde", dimTexto, dimEntrada);
		inHasta = new EntradaFecha(Almanaque.hoy(), "Hasta", dimTexto, dimEntrada);
		
		PanelVertical panelSector = new PanelVertical();
		panelSector.setBorder(new EmptyBorder(0, 0, 0, 10));
		panelSector.add(inSector);
		
		PanelVertical panelFecha = new PanelVertical();
		panelFecha.setBorder(new EmptyBorder(0, 10, 0, 0));
		panelFecha.add(inDesde);
		panelFecha.add(inHasta);
		
		PanelHorizontal ret = new PanelHorizontal();
		ret.setBorder(new EmptyBorder(0, 0, 10, 0));
		ret.add(panelSector);
		ret.add(panelFecha);
		return ret;
	}
	
	public Boton botonImpLista() {
		return btnImpLista;
	}

	public Boton botonImpNotificaciones() {
		return btnImpNotificaciones;
	}

	public TablaVencimientos getTabla() {
		return tabla;
	}

	public Boton botonBuscar() {
		return btnBuscar;
	}	

	public Boton botonLimpiar() {
		return btnLimpiar;
	}

	public Boton botonVerClientes() {
		return btnVerClientes;
	}
	
	public Boton botonModificar() {
		return btnModificar;
	}
	
	public EntradaLista<Sector> getSector() {
		return inSector;
	}
	
	public EntradaFecha getDesde() {
		return inDesde;
	}

	public EntradaFecha getHasta() {
		return inHasta;
	}
	
}