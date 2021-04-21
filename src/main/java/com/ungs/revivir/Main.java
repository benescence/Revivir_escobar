package com.ungs.revivir;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import com.ungs.revivir.vista.sesion.iniciar.ControladorIniciarSesion;

public class Main {
//ahdgfsahgf
	public static void configurarApariencia() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*try {
			Properties props = new Properties();
			props.put("logoString", "Revivir");
			AcrylLookAndFeel.setCurrentTheme(props);
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");} 
			catch (Exception e) {
			e.printStackTrace();
		}*/
		
	
	}
	public static void main(String[] args) {
		configurarApariencia();
		new ControladorIniciarSesion();
	}
	
}