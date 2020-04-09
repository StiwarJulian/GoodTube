package ac.cr.ucenfotec.cl;

import ac.cr.ucenfotec.bl.administrador.Administrador;
import ac.cr.ucenfotec.bl.canton.Canton;
import ac.cr.ucenfotec.bl.categoria.Categoria;
import ac.cr.ucenfotec.bl.distrito.Distrito;
import ac.cr.ucenfotec.bl.provincia.Provincia;
import ac.cr.ucenfotec.bl.tema.Tema;
import ac.cr.ucenfotec.bl.usuarios.Usuarios;
import ac.cr.ucenfotec.bl.video.Video;
import ac.cr.ucenfotec.tl.AdministradorController;
import ac.cr.ucenfotec.tl.ControllerCanton;
import ac.cr.ucenfotec.tl.ControllerCategoria;
import ac.cr.ucenfotec.tl.ControllerTema;
import ac.cr.ucenfotec.tl.ControllerDistrito;
import ac.cr.ucenfotec.tl.ControllerProvincia;
import ac.cr.ucenfotec.tl.ControllerVideo;
import ac.cr.ucenfotec.tl.UsuariosController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class CapaLogica {

    int usuarioActual;
    private HashMap<Integer, Video> videos = new HashMap<>();
    private HashMap<Integer, Categoria> categorias = new HashMap<>();
    private HashMap<Integer, Provincia> provincias = new HashMap<>();
    private HashMap<Integer, Canton> cantones = new HashMap<>();
    private HashMap<Integer, Distrito> distritos = new HashMap<>();
    private HashMap<Integer, Tema> temas = new HashMap<>();

    public CapaLogica() {
        categorias = ControllerCategoria.listar();
        videos = ControllerVideo.listar();
        provincias = ControllerProvincia.listar();
        cantones = ControllerCanton.listar();
        distritos = ControllerDistrito.listar();
        temas = ControllerTema.listar();
    }

    public void agregarVideo(String nombre, Date fecha, String ruta, int calificacion, int categoria, int usuario, double valor) {
        ControllerVideo.registrar(nombre, fecha, ruta, calificacion, categoria, usuario, valor);
        int id = Collections.max(videos.keySet());
        videos.put(id + 1, new Video(id + 1, nombre, fecha, ruta, calificacion, categoria, usuario, valor));
    }

    public void ModificarVideo(int id, String nombre, Date fecha, String ruta, int calificacion, int categoria, int usuario, double valor) {
        ControllerVideo.modificar(id, nombre, fecha, ruta, calificacion, categoria, usuario, valor);
        videos.replace(id, new Video(id, nombre, fecha, ruta, calificacion, categoria, usuario, valor));
    }

    public void agregarCategoria(String nombre, String descripcion) {
        ControllerCategoria.registrar(nombre, descripcion);
        int id = Collections.max(categorias.keySet());
        categorias.put(id + 1, new Categoria(id + 1, nombre, descripcion));
    }

    public void agregarTema(String nombre, String descripcion) {
        ControllerTema.registrar(nombre, descripcion);
        int id = Collections.max(temas.keySet());
        temas.put(id + 1, new Tema(id + 1, nombre, descripcion));
    }

    public void modificarTema(int id, String nombre, String descripcion) {
        ControllerTema.modificar(id, nombre, descripcion);
        categorias.replace(id, new Categoria(id, nombre, descripcion));
    }

    public void agregarProvincia(String nombre) {
        ControllerProvincia.registrar(nombre);
        int id = Collections.max(provincias.keySet());
        provincias.put(id + 1, new Provincia(id + 1, nombre));
    }

    public void modificarProvincia(int id, String nombre) {
        ControllerProvincia.modificar(id, nombre);
        provincias.replace(id, new Provincia(id, nombre));
    }

    public void agregarCanton(String nombre, int provincia) {
        ControllerCanton.registrar(nombre, provincia);
        int id = Collections.max(cantones.keySet());
        cantones.put(id + 1, new Canton(id + 1, nombre, provincia));
    }

    public void modificarCanton(int id, String nombre, int provincia) {
        ControllerCanton.modificar(id, nombre, provincia);
        cantones.replace(id, new Canton(id, nombre, provincia));
    }

    public void agregarDistrito(String nombre, int canton) {
        ControllerDistrito.registrar(nombre, canton);
        int id = Collections.max(distritos.keySet());
        distritos.put(id + 1, new Distrito(id + 1, nombre, canton));
    }

    public void modificarDistrito(int id, String nombre, int canton) {
        ControllerDistrito.modificar(id, nombre, canton);
        distritos.replace(id, new Distrito(id, nombre, canton));
    }

    public void modificarCategoria(int id, String nombre, String descripcion) {
        ControllerCategoria.modificar(id, nombre, descripcion);
        categorias.replace(id, new Categoria(id, nombre, descripcion));
    }

    public int getCategoria(String nombre) {
        for (Categoria categoria : categorias.values()) {
            if (categoria.getNombre().equals(nombre)) {
                return categoria.getId();
            }
        }
        return -1;
    }

    public int getCanton(String nombre) {
        for (Canton canton : cantones.values()) {
            if (canton.getNombre().equals(nombre)) {
                return canton.getId();
            }
        }
        return -1;
    }

    public int getProvincia(String nombre) {
        for (Provincia provincia : provincias.values()) {
            if (provincia.getNombre().equals(nombre)) {
                return provincia.getId();
            }
        }
        return -1;
    }

    public String[] getCategoriasCombo() {
        String[] nombres = new String[categorias.values().size()];
        int i = 0;
        for (Categoria categoria : categorias.values()) {
            nombres[i] = categoria.getNombre();
            i++;
        }
        return nombres;
    }

    public String[] getProvinciasCombo() {
        String[] nombres = new String[provincias.values().size()];
        int i = 0;
        for (Provincia provincia : provincias.values()) {
            nombres[i] = provincia.getNombre();
            i++;
        }
        return nombres;
    }

    public String[] getCantonesCombo() {
        String[] nombres = new String[cantones.values().size()];
        int i = 0;
        for (Canton canton : cantones.values()) {
            nombres[i] = canton.getNombre();
            i++;
        }
        return nombres;
    }

    public ArrayList<String[]> getVideosUsuario(String usuario) {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Video video : videos.values()) {
            if ((video.getUsuario() + "").equals(usuario)) {
                String fila = video.toString().substring(0, video.toString().lastIndexOf(','));
                datos.add(fila.split(","));
            }
        }
        return datos;
    }

    public ArrayList<String[]> getVideosId(String id) {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Video video : videos.values()) {
            if ((video.getId() + "").equals(id)) {
                String fila = video.toString();
                datos.add(fila.split(","));
            }
        }
        return datos;
    }

    public ArrayList<String[]> getVideosFecha(String fecha) {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Video video : videos.values()) {
            if (video.getFecha().toString().equals(fecha)) {
                String fila = video.toString();
                datos.add(fila.split(","));
            }
        }
        return datos;
    }

    public ArrayList<String[]> getVideosCalificacion(String calificacion) {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Video video : videos.values()) {
            if ((video.getCalificacion() + "").equals(calificacion)) {
                String fila = video.toString();
                datos.add(fila.split(","));
            }
        }
        return datos;
    }

    public ArrayList<String[]> getVideosCategoria(String categoria) {
        ArrayList<String[]> datos = new ArrayList<>();
        int id = getCategoria(categoria);
        for (Video video : videos.values()) {
            if ((video.getCategoria() + "").equals(id)) {
                String fila = video.toString();
                datos.add(fila.split(","));
            }
        }
        return datos;
    }

    public ArrayList<String[]> getVideosValor(String valor) {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Video video : videos.values()) {
            if ((video.getValor() + "").equals(valor)) {
                String fila = video.toString();
                datos.add(fila.split(","));
            }
        }
        return datos;
    }

    public ArrayList<String[]> getProvincias() {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Provincia provincia : provincias.values()) {
            String fila = provincia.toString();
            datos.add(fila.split(","));
        }
        return datos;
    }

    public ArrayList<String[]> getProvincias(String nombre) {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Provincia provincia : provincias.values()) {
            if (nombre.equals(provincia.getNombre())) {
                String fila = provincia.toString();
                datos.add(fila.split(","));
            }
        }
        return datos;
    }

    public ArrayList<String[]> getCantones() {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Canton canton : cantones.values()) {
            String fila = canton.toString();
            datos.add(fila.split(","));
        }
        return datos;
    }

    public ArrayList<String[]> getCantones(String nombre) {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Canton canton : cantones.values()) {
            if (nombre.equals(canton.getNombre())) {
                String fila = canton.toString();
                datos.add(fila.split(","));
            }
        }
        return datos;
    }

    public ArrayList<String[]> getDistritos() {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Distrito distrito : distritos.values()) {
            String fila = distrito.toString();
            datos.add(fila.split(","));
        }
        return datos;
    }

    public ArrayList<String[]> getDistritos(String nombre) {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Distrito distrito : distritos.values()) {
            if (nombre.equals(distrito.getNombre())) {
                String fila = distrito.toString();
                datos.add(fila.split(","));
            }
        }
        return datos;
    }

    public ArrayList<String[]> getCategorias() {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Categoria categoria : categorias.values()) {
            String fila = categoria.toString();
            datos.add(fila.split(","));
        }
        return datos;
    }

    public ArrayList<String[]> getCategorias(String nombre) {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Categoria categoria : categorias.values()) {
            if (nombre.equals(categoria.getNombre())) {
                String fila = categoria.toString();
                datos.add(fila.split(","));
            }
        }
        return datos;
    }

    public ArrayList<String[]> getTemas() {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Tema tema : temas.values()) {
            String fila = tema.toString();
            datos.add(fila.split(","));
        }
        return datos;
    }

    public ArrayList<String[]> getTemas(String nombre) {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Tema tema : temas.values()) {
            if (nombre.equals(tema.getNombre())) {
                String fila = tema.toString();
                datos.add(fila.split(","));
            }
        }
        return datos;
    }

    public ArrayList<String[]> getVideos() {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Video video : videos.values()) {
            String fila = video.toString();
            datos.add(fila.split(","));
        }
        return datos;
    }

    public int getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(int usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    private void moverVideo(String ruta, String nombre) {
        try {
            Files.move(Paths.get(ruta),
                    Paths.get("...\\video\\nombre"));
        } catch (IOException ex) {
            Logger.getLogger(CapaLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       // ADMINISTRADOR
    public String validarCamposAdministrador(long identificacion, String nombres,String apellidos, int edad, String correo,
            String usuario, String clave, String avatar){
        
        String error_message = "";
        
        if(edad < 18){
            error_message = "La edad requerida es de 18 años en adelante";
        }
        
        // comprueba que la clave contena los caracteres adecuados y la cantidad permitida
        error_message = comprobacionClavePermitida(clave, error_message);
        error_message = comprobacionValoresUnicos(correo, usuario, error_message);

        
        if(!"".equals(error_message)){
            return error_message;
        }else{
            
            
            if(!"".equals(avatar)){
               avatar = guardarImagen(avatar);
            }
            
            guardarUsuarioAdministrador(identificacion, nombres, apellidos, edad, correo,
            usuario ,clave , avatar); 
            
        }
        
        return error_message;
    }
    
    private String comprobacionValoresUnicos(String correo, String usuario, String error_message) {
        
        boolean correoValidado;
        boolean usuarioValidado;
        
        AdministradorController adminCtrl = new AdministradorController();
        
        correoValidado = adminCtrl.comprobarCorreoUnico(correo);
        usuarioValidado = adminCtrl.comprobarUsuarioUnico(usuario);
        
        if(correoValidado){error_message = "El correo ingresado ya se encuentra registrado ";}
        
        if(usuarioValidado){error_message = "El usuario ingresado ya se encuentra registrado ";}

        
        return error_message;
    }
    
    private String guardarImagen(String avatar) {
        
            File file = new File(avatar);
            AdministradorController adminCtrl = new AdministradorController();
            
            int temp,ultimo_id_usuario;
            String nombre = file.getName();
        try {
            
            temp = adminCtrl.ultimoIdUsuario();
            ultimo_id_usuario = temp+1;
            
            BufferedImage imagen;
            File directorio = new File(Usuarios.URL_AVATARS+ultimo_id_usuario);

            if(!directorio.isDirectory())directorio.mkdir();

            nombre = file.getName();
            imagen = ImageIO.read(file);

            ImageIO.write(imagen, "jpg", new File(Usuarios.URL_AVATARS+ultimo_id_usuario+"\\"+nombre));

            return ultimo_id_usuario+"/"+nombre;
            
        } catch (IOException ex) {
            Logger.getLogger(CapaLogica.class.getName()).log(Level.SEVERE, null, ex);
            return 1+"/"+nombre;
        }

    }
    
    private String comprobacionClavePermitida(String clave,String message){
        
        if(clave.length() >= 6  && clave.length() <= 8){
            
            int contador[];
            contador = new int[3];
            
            for(int i = 0; i < clave.length(); i++){
           
                if(Character.isUpperCase(clave.charAt(i))){ contador[0] = 1; }
                if(Character.isLowerCase(clave.charAt(i))){ contador[1] = 2; }
                if(!Character.isLetterOrDigit(clave.charAt(i))){ contador[2] = 3; }
            }
            
            if((contador[0] != 1) || (contador[1] != 2) || (contador[2] != 3) ){
                message = "La contraseña debe contener 1 mayuscula, 1 minuscula y 1 caracter especial!";
            }
            
        }else if(clave.length() < 6){
            message = "La contraseña debe contar con minimo 6 caracteres";
        }else{
            message = "La contraseña debe contar con maximo 8 caracteres";
        }
        
        return message;
    }
    
    private void guardarUsuarioAdministrador(long identificacion, String nombres, String apellidos, int edad, String correo,
            String usuario, String clave, String avatar){
        
            Administrador admin = new Administrador();
            AdministradorController adminCtrl = new AdministradorController();
            
            admin.setId_usuario(0);
            admin.setIdentificacion(identificacion);
            admin.setNombres(nombres);
            admin.setApellidos(apellidos);
            admin.setEdad(edad);
            admin.setCorreo(correo);
            admin.setUsuario(usuario);
            admin.setClave(clave);
            admin.setAvatar(avatar);
            admin.setCodigo_verificacion(0);
            admin.setId_distrito(0);
            admin.setId_rol(1);
            
            adminCtrl.guardarUsuarioAdministrador(admin);
    }

    
    // VALIDACION USUARIO O ADMINISTRADOR INICIO DE SESION
    
    public String comprobarUsuario(String Usuario, String clave){
        
        String error_message = "";
        Usuarios user;
        
        UsuariosController usuarioCtrl = new UsuariosController();
        user = usuarioCtrl.comprobarUsuario(Usuario, clave);
        
        if(user == null){
            error_message = "Usuario y/o contraseña incorrecta";
        }else{   
            if(user.getCodigo_verificacion() == 0){
                error_message = enviarCodigoVerificacion(user.getId_usuario() ,user.getCodigo_verificacion(), user.getCorreo() ,error_message);
                
                if(!"".equals(error_message)){
                    return error_message;
                }
            }   
            
            if(user.getId_rol() == 1){
                error_message = "administrador"; 
            }else{
                error_message = "usuario";
            }
            return error_message;
        }
        
        return error_message;
    }

    private String enviarCodigoVerificacion(int id_usuario, int codigo_verificacion, String correo, String error_message) {
        
        Random r = new Random();
        String asunto = "Codigo Verificacion";
        codigo_verificacion = r.nextInt(200000-100000+1)+100000;
        boolean envioCorreo = enviarCorreoCodigo(codigo_verificacion, correo, asunto);
        
        if(!envioCorreo){
            error_message = "No ha sido posible enviar el codigo de verificacion";
            return error_message;
        }
        int codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el codigo de verificacion"));
        
        if(codigo != codigo_verificacion){
            error_message = "El codigo de verificacion ingresado es incorrecto";  
            return error_message;

        }
        
        error_message = actualizarUsuarioCodigoVerificacion(id_usuario, codigo_verificacion, error_message);
        
        return error_message;
    }

    private boolean enviarCorreoCodigo(int codigo_verificacion, String correo, String asunto) {

        String to = correo;

        String from = "correobiblioteca123@gmail.com";
        String PASSWORD = "123456789aA_";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(asunto);
            message.setText(String.valueOf(codigo_verificacion));
            Transport.send(message);
            return true;
        } catch (MessagingException mex) {
            System.out.println(mex);
            return false;
        }
        
        
    }

    private String actualizarUsuarioCodigoVerificacion(int id_usuario, int codigo_verificacion, String error_message) {
        
        UsuariosController usuarioCtrl = new UsuariosController();
        
        boolean actualizacion = usuarioCtrl.actualizarCodigoVerificacion(id_usuario, codigo_verificacion);
        
        if(!actualizacion){
            error_message = "No se ha podido realizar la verificacion";
        }
        
        return error_message;
    }
    
 
    // USUARIO 
    
    public String validarCamposUsuario(long identificacion, String nombres,String apellidos, int edad, String correo,
            String usuario, String clave, String avatar,int distrito){
        
        String error_message = "";
        
        if(edad < 18){
            error_message = "La edad requerida es de 18 años en adelante";
        }
        
        // comprueba que la clave contena los caracteres adecuados y la cantidad permitida
        error_message = comprobacionClavePermitida(clave, error_message);
        error_message = comprobacionValoresUnicos(correo, usuario, error_message);

        
        if(!"".equals(error_message)){
            return error_message;
        }else{

            if(!"".equals(avatar)){
               avatar = guardarImagen(avatar);
            }
            
            guardarUsuario(identificacion, nombres, apellidos, edad, correo,
            usuario ,clave , avatar, distrito);
        }
        
        return error_message;
    }

    private void guardarUsuario(long identificacion, String nombres, String apellidos, int edad, String correo, String usuario, String clave, String avatar, int distrito) {
        Usuarios users = new Usuarios();
            UsuariosController userCtrl = new UsuariosController();
            
            users.setId_usuario(0);
            users.setIdentificacion(identificacion);
            users.setNombres(nombres);
            users.setApellidos(apellidos);
            users.setEdad(edad);
            users.setCorreo(correo);
            users.setUsuario(usuario);
            users.setClave(clave);
            users.setAvatar(avatar);
            users.setCodigo_verificacion(0);
            users.setId_distrito(distrito);
            users.setId_rol(2);
            
            userCtrl.guardarUsuario(users);
    }

    public String comprobarExisteUsuario(String usuario) {
        
        String error_message = "";
        
        UsuariosController user = new UsuariosController();
        Usuarios users = user.comprobarExisteUsuario(usuario);
        
        if(users  == null){
            error_message = "El usuario ingresado no existe";
            return error_message;
        }
        
        error_message = cambiarClave(users.getId_usuario(), users.getCorreo(), error_message);
        
        return error_message;
    }

    private String cambiarClave(int id_usuario,String Correo, String error_message) {
        
        Random r = new Random();
        String asunto = "Cambio de clave";
        boolean enviarMail, actualizar;
        int clave = 0;
        
        clave = r.nextInt(200000-100000+1)+100000;
        
        UsuariosController user = new UsuariosController();
        
        actualizar = user.cambiarClave(id_usuario,clave);
        
        if(!actualizar){
            error_message = "No se ha podido generar la clave";
            return error_message;
        }
        
        enviarMail = enviarCorreoCodigo(clave, Correo, asunto);
        
        if(!enviarMail){
            error_message = "No se ha podido enviar la clave generada";
        }
        
        return error_message;
    }
    
    public boolean verificarExisteUsuarioAdministrador(){
        return  AdministradorController.verificarExisteUsuarioAdministrador();
    }
}
