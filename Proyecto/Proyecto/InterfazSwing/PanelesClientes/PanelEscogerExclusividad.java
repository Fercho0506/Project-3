package PanelesClientes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelEscogerExclusividad extends JPanel implements ActionListener{
	private static final String BASICO = "basico";
    private static final String FAMILIAR = "familiar";
    private static final String ORO = "oro";
    private static final String DIAMANTE = "diamante";
	private JRadioButton basico;
	private JRadioButton familiar;
	private JRadioButton oro;
	private JRadioButton diamante;
	private String opcion;
	
	public PanelEscogerExclusividad() {
		this.opcion="";
		setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel exclusividad= new JLabel("Escoger exclusividad del tiquete");
		
		basico= new JRadioButton("Básico");
		familiar= new JRadioButton("Familiar");
		oro= new JRadioButton("Oro");
		diamante= new JRadioButton("Diamante");

		basico.setActionCommand(BASICO);
		familiar.setActionCommand(FAMILIAR);
		oro.setActionCommand(ORO);
		diamante.setActionCommand(DIAMANTE);
		
		basico.addActionListener(this);
		familiar.addActionListener(this);
		oro.addActionListener(this);
		diamante.addActionListener(this);
		
		ButtonGroup grupo= new ButtonGroup();
        grupo.add(basico);
        grupo.add(familiar);
        grupo.add(oro);
        grupo.add(diamante);
        
        basico.setAlignmentX(Component.CENTER_ALIGNMENT);
        familiar.setAlignmentX(Component.CENTER_ALIGNMENT); 
        oro.setAlignmentX(Component.CENTER_ALIGNMENT);
        diamante.setAlignmentX(Component.CENTER_ALIGNMENT); 
        exclusividad.setAlignmentX(Component.CENTER_ALIGNMENT); 
        
        add(exclusividad);
        add(basico);
        add(familiar);
        add(oro);
        add(diamante);
	}
	
	@Override
    public void actionPerformed( ActionEvent e ) {
		String comando = e.getActionCommand( );
		if (BASICO.equals(comando)) {
			this.opcion=BASICO;
		}
		else if (FAMILIAR.equals(comando)) {
			this.opcion=FAMILIAR;
		}
		else if (ORO.equals(comando)) {
			this.opcion=ORO;
		}
		else if (DIAMANTE.equals(comando)) {
			this.opcion=DIAMANTE;
		}
	}
	
	public String getOpcion() {
		return opcion;
	}
	
}

