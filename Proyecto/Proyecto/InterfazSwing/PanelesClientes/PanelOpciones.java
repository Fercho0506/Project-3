package PanelesClientes;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelOpciones extends JPanel implements ActionListener{
	private static final String CERRAR = "volver";
	private static final String CONTINUAR = "continuar";
	private JFrame ventanaAnterior;
	private NotificationsListener ventanaPrincipal;
	private JButton cerrar;
	private JButton continuar;
	
	public PanelOpciones(String texto, JFrame ventanaAnterior, NotificationsListener ventanaPrincipal) {
		this.ventanaPrincipal=ventanaPrincipal;
		this.ventanaAnterior=ventanaAnterior;
		setLayout(new FlowLayout());

		continuar= new JButton(texto);
		continuar.setActionCommand(CONTINUAR); 
		continuar.addActionListener(this); 

		cerrar= new JButton("Cancelar");
		cerrar.setActionCommand(CERRAR); 
		cerrar.addActionListener(this); 
		
		continuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        cerrar.setAlignmentX(Component.CENTER_ALIGNMENT); 
        
        add(continuar); 
        add(cerrar); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand( );
		if (CERRAR.equals(comando)) {
			ventanaAnterior.setVisible(true);
			((JFrame) ventanaPrincipal).dispose();
		}
		else if (CONTINUAR.equals(comando)) {
			ventanaPrincipal.accionDesdePanel();
		}
		
	}
	
	
}
