package VentanasClientes;
import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import PanelesClientes.PanelDetalllesTiquetes;
import Tiquetes.Entrada;
import Tiquetes.Tiquete;
import Usuarios.Usuario;

public class VentanaImprimirTiquete extends JFrame{
	private Tiquete tiquete;
	private PanelDetalllesTiquetes detalles;
	private ImageIcon qr;
	
	public VentanaImprimirTiquete(Tiquete tiquete, Usuario usuario) {
		this.tiquete=tiquete;
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		detalles= new PanelDetalllesTiquetes();
		detalles.setBackground(Color.RED);
		detalles.actualizarTiquete(tiquete, usuario.getFastPassPorUsar().size());
		
		String texto= generarTexto();
        int ancho= 300;
        int alto= 300;
        BufferedImage qrImage = generarQRBufferedImage(texto, ancho, alto);
        qr= new ImageIcon(qrImage);
        JLabel label= new JLabel(qr);
        
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
		detalles.setAlignmentX(Component.CENTER_ALIGNMENT);
        add (label);
        add(detalles);
        
        setTitle("Imprimir Tiquete");
        
        setSize(ancho + 50, alto + 70);
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
	}
	
	public static BufferedImage generarQRBufferedImage(String texto, int ancho, int alto) {
        try {
            QRCodeWriter qrWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrWriter.encode(texto, BarcodeFormat.QR_CODE, ancho, alto);
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	private String generarTexto() {
		String tipo= "Tipo de tiquete: "+ tiquete.getTipo();
		String id= "\nID: "+tiquete.getCodigo();
		String exclusividad="";
		if (!tiquete.getTipo().equals("entrada")) {
			exclusividad= "\nExclusividad: "+tiquete.getExclusividad();
		}
		else {
			Entrada entrada= (Entrada) tiquete;
			exclusividad= "\nAtracción: "+entrada.getAtraccion().getNombre();
		}
		String fecha= "\nFecha expedición: "+tiquete.getFechaExpedicion();
		return tipo+id+exclusividad+fecha;
	}
}
