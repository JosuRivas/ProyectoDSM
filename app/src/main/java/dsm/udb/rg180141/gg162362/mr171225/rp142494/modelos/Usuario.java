package dsm.udb.rg180141.gg162362.mr171225.rp142494.modelos;

public class Usuario {
    private String email;
    private String tipo;
    private String uid;
    private String idNegocio;

    public Usuario(String email, String tipo, String uid) {
        this.email = email;
        this.tipo = tipo;
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(String idNegocio) {
        this.idNegocio = idNegocio;
    }
}

