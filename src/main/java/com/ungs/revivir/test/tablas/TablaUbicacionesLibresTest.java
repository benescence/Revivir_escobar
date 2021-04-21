package com.ungs.revivir.test.tablas;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.manager.UbicacionManager;
import com.ungs.revivir.vista.tablas.TablaUbicacionesLibres;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;

public class TablaUbicacionesLibresTest extends Ventana{
	private static final long serialVersionUID = 1L;

	public TablaUbicacionesLibresTest() {
		super("Prueba tabla de ubicaciones libres", 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		TablaUbicacionesLibres tabla = new TablaUbicacionesLibres(UbicacionManager.traerTodo());
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		PanelVertical panel = new PanelVertical();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panel);
		
		panel.add(panelTabla);
		compactar();
	}
	
	public static void main(String[] args) {
		new TablaUbicacionesLibresTest();
	}

}