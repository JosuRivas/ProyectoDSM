package dsm.udb.rg180141.gg162362.mr171225.rp142494.modelos;

public class Negocio {

    private String nombre;
    private String departamento;
    private String municipio;
    private String telefono;
    private String direccion;
    private String rangoPrecios;
    private String horario;
    private String tipo;

    public Negocio(String nombre, String departamento, String municipio, String telefono, String direccion, String rangoPrecios, String horario,String tipo) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.municipio = municipio;
        this.telefono = telefono;
        this.direccion = direccion;
        this.rangoPrecios = rangoPrecios;
        this.horario = horario;
        this.tipo = tipo;
    }

    public Negocio(){

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRangoPrecios() {
        return rangoPrecios;
    }

    public void setRangoPrecios(String rangoPrecios) {
        this.rangoPrecios = rangoPrecios;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
