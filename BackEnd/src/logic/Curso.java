
package logic;


public class Curso {
    
    private String codigoCurso, nombre, codigoCarrera;
    private int nivel, ciclo, horasSemanales, creditos;

    public Curso(String codigo, String nombre, String codigoCarrera, int nivel, int ciclo, int horasSemanales, int creditos) {
        this.codigoCurso = codigo;
        this.nombre = nombre;
        this.codigoCarrera = codigoCarrera;
        this.nivel = nivel;
        this.ciclo = ciclo;
        this.horasSemanales = horasSemanales;
        this.creditos = creditos;
    }

    public Curso() {
        this.codigoCurso = "";
        this.nombre = "";
        this.codigoCarrera = "";
        this.nivel = -1;
        this.ciclo = -1;
        this.horasSemanales = -1;
        this.creditos = -1;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigoCarrera() {
        return codigoCarrera;
    }

    public int getNivel() {
        return nivel;
    }

    public int getCiclo() {
        return ciclo;
    }

    public int getHorasSemanales() {
        return horasSemanales;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCodigoCurso(String codigo) {
        this.codigoCurso = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigoCarrera(String codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public void setHorasSemanales(int horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    @Override
    public String toString() {
        return "Curso{" + "codigo=" + codigoCurso + ", nombre=" + nombre + ", codigoCarrera=" + codigoCarrera + ", nivel=" + nivel + ", ciclo=" + ciclo + ", horasSemanales=" + horasSemanales + ", creditos=" + creditos + '}';
    }
    
}
