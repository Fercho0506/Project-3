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

import Modelo.Compra;
import Usuarios.Usuario;
import VentanasClientes.VentanaCompras;

public class PanelListaCompras extends JPanel implements ListSelectionListener{
	private Usuario usuario;
	private DefaultListModel<Compra> dataModel;
    private JList<Compra> compras;
    private VentanaCompras ventanaPrincipal;
    
    public PanelListaCompras(Usuario usuario, VentanaCompras ventanaPrincipal)
    {
    	this.usuario=usuario;
    	this.ventanaPrincipal=ventanaPrincipal;
        setBorder( new TitledBorder( "Compras" ) );
        setLayout( new BorderLayout( ) );
        
        dataModel = new DefaultListModel<>( );
        compras = new JList<>( dataModel );
        compras.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        compras.addListSelectionListener( this );

        JScrollPane scroll = new JScrollPane( compras );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        add( scroll );
    }
    
    public void actualizarCompras()
    {
        List<Compra> nuevasCompras = new ArrayList<Compra>( );
        for(Compra q: usuario.getHistorialCompras())
        {
            if( !dataModel.contains( q ) )
            	nuevasCompras.add( q );
        }
        dataModel.addAll( nuevasCompras );
    }
    
    @Override
    public void valueChanged( ListSelectionEvent e )
    {
        Compra seleccionado = compras.getSelectedValue();
        this.ventanaPrincipal.cambiarCompraSeleccionada(seleccionado);
    }

    public void seleccionarCompra( Compra compra )
    {
        compras.setSelectedValue( compra, true );
    }
    
}
