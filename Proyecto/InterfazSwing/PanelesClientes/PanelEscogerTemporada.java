package PanelesClientes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelEscogerTemporada extends JPanel implements ActionListener{
	private static final String PRIMAVERA = "primavera";
    private static final String VERANO = "verano";
    private static final String OTONO = "otoño";
    private static final String INVIERNO = "invierno";
	private JRadioButton primavera;
	private JRadioButton verano;
	private JRadioButton otono;
	private JRadioButton invierno;
	private String opcion;
	
	public PanelEscogerTemporada() {
		this.opcion="";
		setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel temporada= new JLabel("Escoger temporada del tiquete");
		
		primavera= new JRadioButton("Primavera");
		verano= new JRadioButton("Verano");
		otono= new JRadioButton("Otoño");
		invierno= new JRadioButton("Invierno");

		primavera.setActionCommand(PRIMAVERA);
		verano.setActionCommand(VERANO);
		otono.setActionCommand(OTONO);
		invierno.setActionCommand(INVIERNO);
		
		primavera.addActionListener(this);
		verano.addActionListener(this);
		otono.addActionListener(this);
		invierno.addActionListener(this);
		
		ButtonGroup grupo= new ButtonGroup();
        grupo.add(primavera);
        grupo.add(verano);
        grupo.add(otono);
        grupo.add(invierno);
        
        primavera.setAlignmentX(Component.CENTER_ALIGNMENT);
        verano.setAlignmentX(Component.CENTER_ALIGNMENT); 
        otono.setAlignmentX(Component.CENTER_ALIGNMENT);
        invierno.setAlignmentX(Component.CENTER_ALIGNMENT); 
        temporada.setAlignmentX(Component.CENTER_ALIGNMENT); 
        
        add(temporada);
        add(primavera);
        add(verano);
        add(otono);
        add(invierno);
	}
	
	@Override
    public void actionPerformed( ActionEvent e ) {
		String comando = e.getActionCommand( );
		if (PRIMAVERA.equals(comando)) {
			this.opcion=PRIMAVERA;
		}
		else if (VERANO.equals(comando)) {
			this.opcion=VERANO;
		}
		else if (OTONO.equals(comando)) {
			this.opcion=OTONO;
		}
		else if (INVIERNO.equals(comando)) {
			this.opcion=INVIERNO;
		}
	}
	
	public String getOpcion() {
		return opcion;
	}
}
