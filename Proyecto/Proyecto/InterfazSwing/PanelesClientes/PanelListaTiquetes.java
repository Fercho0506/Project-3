package PanelesClientes;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Tiquetes.Tiquete;
import Usuarios.Usuario;

public class PanelListaTiquetes extends JPanel implements ListSelectionListener{
	private Usuario usuario;
	private DefaultListModel<Tiquete> dataModel;
    private JList<Tiquete> tiquetes;
    private Tiquete tiquete;
    private PanelDetalllesTiquetes panelDetalles;
    
    public PanelListaTiquetes(Usuario usuario, PanelDetalllesTiquetes panelDetalles)
    {
    	this.panelDetalles=panelDetalles;
    	this.tiquete=null;
    	this.usuario=usuario;
        setBorder( new TitledBorder( "Tiquetes" ) );
        setLayout( new BorderLayout( ) );
        
        dataModel = new DefaultListModel<>( );
        tiquetes = new JList<>( dataModel );
        tiquetes.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        tiquetes.addListSelectionListener( this );

        JScrollPane scroll = new JScrollPane( tiquetes );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        add( scroll );
    }
    
    public void actualizarTiquetes()
    {
        List<Tiquete> nuevosTiquetes = new ArrayList<Tiquete>( );
        for(Tiquete q: usuario.getTiquetesPorUsar())
        {
            if( !dataModel.contains( q ) )
            	nuevosTiquetes.add( q );
        }
        dataModel.addAll( nuevosTiquetes );
    }
    
    @Override
    public void valueChanged( ListSelectionEvent e )
    {
        Tiquete seleccionado = tiquetes.getSelectedValue();
        this.tiquete=seleccionado;
        panelDetalles.actualizarTiquete(seleccionado, usuario.getFastPassPorUsar().size());
    }

    public void seleccionarTiquete( Tiquete tiquete )
    {
        tiquetes.setSelectedValue( tiquete, true );
        this.tiquete=tiquete;
        panelDetalles.actualizarTiquete(tiquete, usuario.getFastPassPorUsar().size());
    }
    
    public Tiquete getTiquete() {
    	return tiquete;
    }
}
