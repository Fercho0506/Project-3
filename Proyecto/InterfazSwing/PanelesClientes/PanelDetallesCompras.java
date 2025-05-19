package PanelesClientes;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modelo.Compra;
import Modelo.CompraAtracciones;
import Modelo.CompraServicio;

public class PanelDetallesCompras extends JPanel{
	private JLabel tipo;
	private JLabel cantidadProductos;
	private JLabel codigo;
	private JLabel precio;
	
	public PanelDetallesCompras()
    {
    	setLayout(new GridLayout(4, 1));
    	tipo= new JLabel("Tipo:");
        add(tipo);
        
        cantidadProductos= new JLabel("Cantidad productos:");
        add(cantidadProductos);
        
        codigo= new JLabel("Codigo compra:");
        add(codigo);
        
        precio= new JLabel("Precio compra:");
        add(precio);
    }
	
	private void actualizarCompra( String type, int cantidad, int code, float price)
    {
    	tipo.setText("Tipo: "+type);
    	cantidadProductos.setText("Cantidad de productos: "+cantidad);
    	codigo.setText("Codigo compra: "+code);
    	precio.setText("Precio compra: "+price);
    }

    public void actualizarCompra( Compra compra )
    {
    	if (compra != null) {
    		if (compra.getTipo().equals("atracciones")) {
        		CompraAtracciones atract= (CompraAtracciones) compra;
        		int cantidad= atract.getTiquetes().size()+atract.getFastPass().size();
        		this.actualizarCompra("tiquetes", cantidad, atract.getCodigo(), atract.getPrecio());
        	}
        	else {
        		CompraServicio servi= (CompraServicio) compra;
        		int cantidad= servi.getComprar().size();
        		this.actualizarCompra("Comida", cantidad, servi.getCodigo(), servi.getPrecio());
        	}
    	}
    }

}
