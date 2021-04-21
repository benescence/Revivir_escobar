package com.ungs.revivir.vista.menu.usuarios.usuarioAM;

import com.ungs.revivir.negocios.manager.UsuarioManager;
import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.definidos.Rol;
import com.ungs.revivir.persistencia.entidades.Usuario;
import com.ungs.revivir.vista.principal.ControladorExterno;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorUsuarioAM implements ControladorExterno{
	private VentanaUsuarioAM ventana;
	private UsuarioInvocable invocador;
	private Usuario modificar;
	
	public ControladorUsuarioAM(UsuarioInvocable invocador, Usuario modificar) {
		this.invocador = invocador;
		this.modificar = modificar;
		ventana = new VentanaUsuarioAM(modificar);
		inicializar();
	}
	
	public ControladorUsuarioAM(UsuarioInvocable invocador) {
		this.invocador = invocador;
		ventana = new VentanaUsuarioAM();
		inicializar();
	}
	
	private void inicializar() {
		ventana.botonAceptar().setAccion(e -> aceptar());
		ventana.botonCancelar().setAccion(e -> cancelar());
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));
	} 
	
	private void aceptar() {
		ventana.requestFocusInWindow();
		
		try {
			Usuario usuario = traerUsuarioVerificado();
			
			// AGREGAR USUARIO
			if (modificar == null)
				UsuarioManager.guardar(usuario);
			
			// MODIFICAR USUARIO
			else
				UsuarioManager.modificar(usuario);
			
			invocador.actualizarUsuarios();
			ventana.dispose();
			invocador.mostrar();
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
	}
	
	private Usuario traerUsuarioVerificado() throws Exception {
		String usuario = ventana.getUsuario().getText();
		String password = ventana.getPassword().getText();
		Rol rol = (Rol) ventana.getPermisos().getSelectedItem();
		Usuario nuevo = new Usuario(-1, usuario, password, rol);
		if (modificar != null)
			nuevo.setID(modificar.getID());
		return Verificador.usuario(nuevo);
	}
	
	private void cancelar() {
		if (Popup.confirmar("¿Seguro de que desea cancelar la operación?")) {
			ventana.dispose();
			invocador.mostrar();
		}
	}

}