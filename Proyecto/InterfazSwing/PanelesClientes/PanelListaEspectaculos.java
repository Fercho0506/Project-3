package PanelesClientes;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Atracciones.Espectaculo;
import Modelo.Parque;

public class PanelListaEspectaculos extends JPanel implements ListSelectionListener{
	private DefaultListModel<Espectaculo> dataModel;
    private JList<Espectaculo> espectaculos;
    private Parque parque;
    private Espectaculo espectaculo;
    private JLabel seleccionado;
    
    public PanelListaEspectaculos(Parque parque)
    {
    	this.espectaculo=null;
        this.parque=parque;
        setBorder( new TitledBorder( "Espectaculos" ) );
        setLayout( new BorderLayout( ) );
        seleccionado= new JLabel("Espectaculo seleccionado: ");
        
        dataModel = new DefaultListModel<>( );
        espectaculos = new JList<>( dataModel );
        espectaculos.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        espectaculos.addListSelectionListener( this );

        JScrollPane scroll = new JScrollPane( espectaculos );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        add( scroll , BorderLayout.CENTER);
        add(Box.createVerticalStrut(10), BorderLayout.AFTER_LAST_LINE);
        add(seleccionado, BorderLayout.AFTER_LAST_LINE);
    }
    
    public void actualizarEspectaculos()
    {
        List<Espectaculo> nuevosEspectaculos = new ArrayList<Espectaculo>( );
        for(Espectaculo q: parque.getEspectaculos())
        {
            if( !dataModel.contains( q ) )
            	nuevosEspectaculos.add( q );
        }
        dataModel.addAll( nuevosEspectaculos );
    }
    
    @Override
    public void valueChanged( ListSelectionEvent e )
    {
        Espectaculo seleccion = espectaculos.getSelectedValue( );
        this.espectaculo=seleccion;
        seleccionado.setText("Espectaculo seleccionado: "+seleccion.getNombre());
    }
    
    public void seleccionarEspectaculo( Espectaculo espectaculo )
    {
    	espectaculos.setSelectedValue( espectaculo, true );
    	this.espectaculo=espectaculo;
    	seleccionado.setText("Espectaculo seleccionado: "+espectaculo.getNombre());
    }
    
    public Espectaculo getEspectaculo() {
    	return espectaculo;
    }

}
