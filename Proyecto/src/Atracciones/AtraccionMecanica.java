package Atracciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AtraccionMecanica extends Atraccion implements Serializable {
    private static final long serialVersionUID = 1L;

    private float alturaMax;
    private float alturaMin;
    private List<String> restricciones;
    private String nivelRiesgo;

    public AtraccionMecanica(String nombre, int capacidad, String ubicacion, int empleadosMin,
                             String exclusividad, Date temporada, float alturaMax, float alturaMin, String nivelRiesgo) {
        super(nombre, capacidad, ubicacion, empleadosMin, exclusividad, temporada, "mecanica");
        this.alturaMax = alturaMax;
        this.alturaMin = alturaMin;
        this.nivelRiesgo = nivelRiesgo;
        this.restricciones = new ArrayList<>();
    }

    public float getAlturaMax() {
        return alturaMax;
    }

    public float getAlturaMin() {
        return alturaMin;
    }

    public String getNivel() {
        return nivelRiesgo;
    }

    public List<String> getRestricciones() {
        return restricciones;
    }

    public void agregarRestriccion(String restriccion) {
        restricciones.add(restriccion);
    }

    public void setAlturaMax(float altura) {
        alturaMax = altura;
    }

    public void setAlturaMin(float altura) {
        alturaMin = altura;
    }

    public void setNivel(String level) {
        nivelRiesgo = level;
    }

    public void eliminarRestriccion(String restriccion) {
        int i = 0;
        boolean noEncontrado = true;
        while (i < restricciones.size() && noEncontrado) {
            String restrict = restricciones.get(i);
            if (restrict.equals(restriccion)) {
                restricciones.remove(i);
                noEncontrado = false;
            }
            i++;
        }
    }
}

