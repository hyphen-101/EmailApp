package domain;

public class Recipiente {
    private String correo;

    public Recipiente() {
    }

    public Recipiente(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return correo;
    }
    
    
}
