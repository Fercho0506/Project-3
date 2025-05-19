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

import Atracciones.Atraccion;
import Modelo.Parque;

public class PanelListaAtraccion extends JPanel implements ListSelectionListener{
	private DefaultListModel<Atraccion> dataModel;
    private JList<Atraccion> atracciones;
    private Parque parque;
    private Atraccion atraccion;
    private JLabel seleccionada;
    
    public PanelListaAtraccion(Parque parque)
    {
    	this.atraccion=null;
        this.parque=parque;
        setBorder( new TitledBorder( "Atracciones" ) );
        setLayout( new BorderLayout( ) );
        seleccionada= new JLabel("Atracción seleccionada: ");
        
        dataModel = new DefaultListModel<>( );
        atracciones = new JList<>( dataModel );
        atracciones.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        atracciones.addListSelectionListener( this );

        JScrollPane scroll = new JScrollPane( atracciones );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        add( scroll , BorderLayout.CENTER);
        add(Box.createVerticalStrut(10), BorderLayout.AFTER_LAST_LINE);
        add(seleccionada, BorderLayout.AFTER_LAST_LINE);
    }
    
    public void actualizarAtracciones()
    {
        List<Atraccion> nuevasAtracciones = new ArrayList<Atraccion>( );
        for(Atraccion q: parque.getAtracciones())
        {
            if( !dataModel.contains( q ) )
            	nuevasAtracciones.add( q );
        }
        dataModel.addAll( nuevasAtracciones );
    }
    
    @Override
    public void valueChanged( ListSelectionEvent e )
    {
        Atraccion seleccion = atracciones.getSelectedValue( );
        this.atraccion=seleccion;
        seleccionada.setText("Atracción seleccionada: "+seleccion.getNombre());
    }
    
    public void seleccionarAtraccion( Atraccion atraccion )
    {
        atracciones.setSelectedValue( atraccion, true );
        this.atraccion=atraccion;
    	seleccionada.setText("Atracción seleccionada: "+atraccion.getNombre());
    }
    
    public Atraccion getAtraccion() {
    	return atraccion;
    }
    
}
