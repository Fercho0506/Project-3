package PanelesClientes;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import Tiquetes.Entrada;
import Tiquetes.Tiquete;
import Tiquetes.TiqueteRegular;
import Tiquetes.TiqueteTemporada;

public class PanelDetalllesTiquetes extends JPanel{
	private JLabel tipo;
	private JLabel exclusividad;
	private JLabel temporada;
	private JLabel fastPass;
	
	public PanelDetalllesTiquetes()
    {
    	setLayout(new GridLayout(4, 1));
    	tipo= new JLabel("Tipo:");
        add(tipo);
        
        exclusividad= new JLabel("Exclusividad:");
        add(exclusividad);
        
        temporada= new JLabel("Temporada:");
        add(temporada);
        
        fastPass= new JLabel("fastPass disponibles:");
        add(fastPass);
    }
	
	private void actualizarTiquete( String type, String exclu, String temp, int fast)
    {
    	tipo.setText("Tipo tiquete: "+type);
    	exclusividad.setText("Exclusividad: "+exclu);
    	temporada.setText("Valido en: "+temp);
    	fastPass.setText("fastPass disponibles: "+fast);
    }

    public void actualizarTiquete( Tiquete tiquete, int fast)
    {
    	if (tiquete != null) {
    		if (tiquete.getTipo().equals("regular")) {
        		TiqueteRegular regular= (TiqueteRegular) tiquete;
        		this.actualizarTiquete("regular", regular.getExclusividad(), regular.getFechaExpedicion(), fast);
        	}
        	else if(tiquete.getTipo().equals("temporada")) {
        		TiqueteTemporada temporada= (TiqueteTemporada) tiquete;
        		String valido= "fechas desde el "+temporada.getInicio()+" hasta el "+temporada.getFin();
        		this.actualizarTiquete("temporada", temporada.getExclusividad(), valido, fast);
        	}
        	else {
        		Entrada entrada= (Entrada)tiquete;
        		String exclu= "Atraccion "+entrada.getAtraccion().getNombre();
        		this.actualizarTiquete("entrada", exclu, entrada.getFechaExpedicion(), fast);
        	}
    	}
    }
}
