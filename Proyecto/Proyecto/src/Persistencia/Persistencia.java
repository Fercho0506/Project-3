package Persistencia;

import java.io.*;

public class Persistencia {

	public static void guardarObjeto(Object objeto, String rutaArchivo) {
	    try {
	        File archivo = new File(rutaArchivo);
	        archivo.getParentFile().mkdirs(); // 👉 Crea la carpeta si no existe

	        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
	        oos.writeObject(objeto);
	        oos.close();

	        System.out.println("✅ Objeto guardado en: " + rutaArchivo);
	    } catch (IOException e) {
	        System.err.println("❌ Error al guardar:");
	        e.printStackTrace();  // 👉 Muestra el error completo
	    }
	}


    public static Object cargarObjeto(String rutaArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("⚠️ Error al cargar:");
            e.printStackTrace();  // 👉 Mucho más útil que solo e.getMessage()
            return null;
        }
    }

}


