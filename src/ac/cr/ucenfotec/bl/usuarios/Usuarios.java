
package ac.cr.ucenfotec.bl.usuarios;


public class Usuarios {
    
    public static String URL_AVATARS =  System.getProperty("user.dir")+"\\imagenes\\";

    private int id_usuario;
    private Long identificacion;
    private String nombres;
    private String apellidos;
    private int edad;
    private String correo;
    private String usuario;
    private String clave;
    private String avatar;
    private int codigo_verificacion;
    private int id_distrito;
    private int id_rol;

    public Usuarios() {
    }

    public Usuarios(int id_usuario, Long identificacion, String nombres, String apellidos, int edad, String correo, String usuario, String clave, String avatar, int codigo_verificacion, int id_distrito, int id_rol) {
        this.id_usuario = id_usuario;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.correo = correo;
        this.usuario = usuario;
        this.clave = clave;
        this.avatar = avatar;
        this.codigo_verificacion = codigo_verificacion;
        this.id_distrito = id_distrito;
        this.id_rol = id_rol;
    }

    

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Long identificacion) {
        this.identificacion = identificacion;
    }
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getCodigo_verificacion() {
        return codigo_verificacion;
    }

    public void setCodigo_verificacion(int codigo_verificacion) {
        this.codigo_verificacion = codigo_verificacion;
    }

    public int getId_distrito() {
        return id_distrito;
    }

    public void setId_distrito(int id_distrito) {
        this.id_distrito = id_distrito;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }
    
    
    
    
}
