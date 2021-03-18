
package logic;


public class Usuario {
    
    private String cedula, clave;
    private int rol;

    public Usuario() {
        this.cedula = "";
        this.clave = "";
        this.rol = -1;
    }

    public Usuario(String cedula, String clave, int rol) {
        this.cedula = cedula;
        this.clave = clave;
        this.rol = rol;
    }

    public String getCedula() {
        return cedula;
    }

    public String getClave() {
        return clave;
    }

    public int getRol() {
        return rol;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "cedula=" + cedula + ", clave=" + clave + ", rol=" + rol + '}';
    }
    
    
}
