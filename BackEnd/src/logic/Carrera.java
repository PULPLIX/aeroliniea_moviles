
package logic;


public class Carrera {
    private String codigo,nombre,tituloCarrera;

    public Carrera(){
        this.codigo = "";
        this.nombre = "";
        this.tituloCarrera = "";
    }

    public Carrera(String codigo, String nombre, String tituloCarrera) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tituloCarrera = tituloCarrera;
    }    

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTituloCarrera(String tituloCarrera) {
        this.tituloCarrera = tituloCarrera;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTituloCarrera() {
        return tituloCarrera;
    }

    @Override
    public String toString() {
        return "Carrera{" + "codigo=" + codigo + ", nombre=" + nombre + ", tituloCarrera=" + tituloCarrera + '}';
    }
    
    
}
