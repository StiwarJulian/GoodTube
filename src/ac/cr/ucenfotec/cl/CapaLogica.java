package ac.cr.ucenfotec.cl;

import ac.cr.ucenfotec.bl.administrador.Administrador;
import ac.cr.ucenfotec.bl.canton.Canton;
import ac.cr.ucenfotec.bl.categoria.Categoria;
import ac.cr.ucenfotec.bl.distrito.Distrito;
import ac.cr.ucenfotec.bl.lista.ListaReproduccion;
import ac.cr.ucenfotec.bl.opciones.Opciones;
import ac.cr.ucenfotec.bl.provincia.Provincia;
import ac.cr.ucenfotec.bl.reproduccion.Reproduccion;
import ac.cr.ucenfotec.bl.tema.Tema;
import ac.cr.ucenfotec.bl.usuarios.Usuarios;
import ac.cr.ucenfotec.bl.video.Video;
import ac.cr.ucenfotec.tl.AdministradorController;
import ac.cr.ucenfotec.tl.ControllerCanton;
import ac.cr.ucenfotec.tl.ControllerCategoria;
import ac.cr.ucenfotec.tl.ControllerTema;
import ac.cr.ucenfotec.tl.ControllerDistrito;
import ac.cr.ucenfotec.tl.ControllerProvincia;
import ac.cr.ucenfotec.tl.ControllerListaReproduccion;
import ac.cr.ucenfotec.tl.ControllerOpciones;
import ac.cr.ucenfotec.tl.ControllerReproduccion;
import ac.cr.ucenfotec.tl.ControllerVideo;
import ac.cr.ucenfotec.tl.UsuariosController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    private HashMap<Integer, Usuarios> usuarios = new HashMap<>();
    private HashMap<Integer, ListaReproduccion> listas = new HashMap<>();
    private HashMap<Integer, Opciones> opciones = new HashMap<>();
    private HashMap<Integer, Reproduccion> reproduccion = new HashMap<>();

    public CapaLogica() {
        categorias = ControllerCategoria.listar();
        videos = ControllerVideo.listar();
        provincias = ControllerProvincia.listar();
        cantones = ControllerCanton.listar();
        distritos = ControllerDistrito.listar();
        temas = ControllerTema.listar();
        usuarios = UsuariosController.listar();
        listas = ControllerListaReproduccion.listar();
        opciones = ControllerOpciones.listar();
        reproduccion = ControllerReproduccion.listar();
    }

    public void agregarVideo(String nombre, Date fecha, String ruta, int calificacion, int categoria, int usuario, double valor) {
        ControllerVideo.registrar(nombre, fecha, ruta, calificacion, categoria, usuario, valor);
        int id = (videos.size() > 0) ? Collections.max(videos.keySet()) : 0;
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

    public void agregarReproduccion(int tiempo, int usuario, int video) {
        ControllerReproduccion.registrar(tiempo, usuario, video);
        reproduccion.put(Integer.parseInt(video + "" + usuario), new Reproduccion(tiempo, usuario, video));
    }

    public void eliminarReproduccion(int usuario) {
        ControllerReproduccion.eliminar(usuario);
        for (Reproduccion reproduccio : reproduccion.values()) {
            if (reproduccio.getUsuario() == usuario) {
                reproduccion.remove(Integer.parseInt(reproduccio.getVideo() + "" + reproduccio.getUsuario()));
            }
        }
    }

    public ArrayList<Integer> getReproduccionesVideos(int usuario) {
        ArrayList<Integer> videos = new  ArrayList<>();
        for (Reproduccion reproduccio : reproduccion.values()) {
            if (reproduccio.getUsuario() == usuario) {
                videos.add(reproduccio.getVideo());
            }
        }
        return videos;
    }

    public void agregarTema(String nombre, String descripcion) {
        ControllerTema.registrar(nombre, descripcion);
        int id = (temas.size() > 0) ? Collections.max(temas.keySet()) : 0;
        temas.put(id + 1, new Tema(id + 1, nombre, descripcion));
    }

    public void agregarLista(String nombre, Date fecha, int usuario, int tema, ArrayList<Integer> videos) {
        ControllerListaReproduccion.registrar(nombre, fecha, usuario, tema);
        listas = ControllerListaReproduccion.listar();
        int id = getLista(nombre);
        for (Integer video : videos) {
            ControllerListaReproduccion.agregarVideo(id, video);
        }
    }

    public int getLista(String nombre) {
        for (ListaReproduccion lista : listas.values()) {
            if (lista.getNombre().equals(nombre)) {
                return lista.getId();
            }
        }
        return -1;
    }

    public void agregarOpcion(String color, String vista, int usuario) {
        eliminarOpcion(usuario);
        ControllerOpciones.registrar(color, vista, usuario);
        int id = (opciones.size() > 0) ? Collections.max(listas.keySet()) : 0;
        opciones.put(id + 1, new Opciones(color, vista, usuario));
    }

    public void eliminarOpcion(int id) {
        opciones.remove(id);
        ControllerOpciones.eliminar(id);
    }

    public void modificarLista(int id, String nombre, Date fecha, int usuario, int tema, ArrayList<Integer> videos) {
        ControllerListaReproduccion.modificar(id, nombre, fecha, usuario, tema);
        for (Integer video : listas.get(id).getVideos()) {
            ControllerListaReproduccion.eliminarVideo(id, video);
        }
        listas.replace(id, new ListaReproduccion(id, nombre, fecha, tema, usuario, videos));
        for (Integer video : videos) {
            ControllerListaReproduccion.agregarVideo(id, video);
        }
        listas = ControllerListaReproduccion.listar();
    }

    public void modificarTema(int id, String nombre, String descripcion) {
        ControllerTema.modificar(id, nombre, descripcion);
        categorias.replace(id, new Categoria(id, nombre, descripcion));
    }

    public void agregarProvincia(String nombre) {
        ControllerProvincia.registrar(nombre);
        int id = (provincias.size() > 0) ? Collections.max(provincias.keySet()) : 0;
        provincias.put(id + 1, new Provincia(id + 1, nombre));
    }

    public void modificarProvincia(int id, String nombre) {
        ControllerProvincia.modificar(id, nombre);
        provincias.replace(id, new Provincia(id, nombre));
    }

    public void agregarCanton(String nombre, int provincia) {
        ControllerCanton.registrar(nombre, provincia);
        int id = (cantones.size() > 0) ? Collections.max(cantones.keySet()) : 0;
        cantones.put(id + 1, new Canton(id + 1, nombre, provincia));
    }

    public void modificarCanton(int id, String nombre, int provincia) {
        ControllerCanton.modificar(id, nombre, provincia);
        cantones.replace(id, new Canton(id, nombre, provincia));
    }

    public void agregarDistrito(String nombre, int canton) {
        ControllerDistrito.registrar(nombre, canton);
        int id = (distritos.size() > 0) ? Collections.max(distritos.keySet()) : 0;
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

    public ArrayList<Integer> getVideosLista(int lista) {
        return listas.get(lista).getVideos();
    }

    public int getTema(String nombre) {
        for (Tema tema : temas.values()) {
            if (tema.getNombre().equals(nombre)) {
                return tema.getId();
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

    public int getVideo(String nombre) {
        for (Video video : videos.values()) {
            if (video.getNombre().equals(nombre)) {
                return video.getId();
            }
        }
        return -1;
    }

    public String getVideoRuta(String id) {
        for (Video video : videos.values()) {
            if ((video.getId() + "").equals(id)) {
                return video.getRuta();
            }
        }
        return "";
    }

    public String getOpcionesColor(int usuario) {
        for (Opciones opcion : opciones.values()) {
            if (opcion.getUsuario() == usuario) {
                return opcion.getColor();
            }
        }
        return "";
    }

    public String getOpcionesVista(int usuario) {
        for (Opciones opcion : opciones.values()) {
            if (opcion.getUsuario() == usuario) {
                return opcion.getVista();
            }
        }
        return "";
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

    public String[] getTemasCombo() {
        String[] nombres = new String[temas.values().size()];
        int i = 0;
        for (Tema tema : temas.values()) {
            nombres[i] = tema.getNombre();
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

    public String[] getCantonesCombo(int provincia) {
        ArrayList<String> nombres = new ArrayList<>();
        int i = 0;
        for (Canton canton : cantones.values()) {
            if (canton.getProvincia() == provincia) {
                nombres.add(canton.getNombre());
                i++;
            }
        }
        return nombres.toArray(new String[i]);
    }

    public String[] getDistritosCombo() {
        String[] nombres = new String[distritos.values().size()];
        int i = 0;
        for (Distrito distrito : distritos.values()) {
            nombres[i] = distrito.getNombre();
            i++;
        }
        return nombres;
    }

    public String[] getDistritosCombo(int canton) {
        ArrayList<String> nombres = new ArrayList<>();
        int i = 0;
        for (Distrito distrito : distritos.values()) {
            if (distrito.getCanton() == canton) {
                nombres.add(distrito.getNombre());
                i++;
            }
        }
        return nombres.toArray(new String[i]);
    }

    public ArrayList<String[]> getVideosUsuario(int usuario) {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Video video : videos.values()) {
            if (video.getUsuario() == (usuario)) {
                String[] fila = videos.toString().split(",");
                fila[0] = video.getNombre();
                fila[1] = video.getFecha().toString();
                fila[2] = categorias.get(video.getCategoria()).getNombre();
                fila[3] = usuarios.get(video.getUsuario()).getNombres();
                fila[4] = video.getValor() + "$";
                fila[5] = Video.CALIFICACIONES[video.getCalificacion() - 1];
                datos.add(fila);
            }
        }
        return datos;
    }

    public ArrayList<String[]> getVideosId(String id) {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Video video : videos.values()) {
            if ((video.getId() + "").equals(id)) {
                String[] fila = videos.toString().split(",");
                fila[0] = video.getNombre();
                fila[1] = video.getFecha().toString();
                fila[2] = categorias.get(video.getCategoria()).getNombre();
                fila[3] = usuarios.get(video.getUsuario()).getNombres();
                fila[4] = video.getValor() + "$";
                fila[5] = Video.CALIFICACIONES[video.getCalificacion() - 1];
                datos.add(fila);
            }
        }
        return datos;
    }

    public ArrayList<String[]> getVideosFecha(String fecha) {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Video video : videos.values()) {
            if (video.getFecha().toString().equals(fecha)) {
                String[] fila = videos.toString().split(",");
                fila[0] = video.getNombre();
                fila[1] = video.getFecha().toString();
                fila[2] = categorias.get(video.getCategoria()).getNombre();
                fila[3] = usuarios.get(video.getUsuario()).getNombres();
                fila[4] = video.getValor() + "$";
                fila[5] = Video.CALIFICACIONES[video.getCalificacion() - 1];
                datos.add(fila);
            }
        }
        return datos;
    }

    public ArrayList<String[]> getVideosCalificacion(String calificacion) {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Video video : videos.values()) {
            if ((video.getCalificacion() + "").equals(calificacion)) {
                String[] fila = videos.toString().split(",");
                fila[0] = video.getNombre();
                fila[1] = video.getFecha().toString();
                fila[2] = categorias.get(video.getCategoria()).getNombre();
                fila[3] = usuarios.get(video.getUsuario()).getNombres();
                fila[4] = video.getValor() + "$";
                fila[5] = Video.CALIFICACIONES[video.getCalificacion() - 1];
                datos.add(fila);
            }
        }
        return datos;
    }

    public ArrayList<String[]> getVideosCategoria(String categoria) {
        ArrayList<String[]> datos = new ArrayList<>();
        int id = getCategoria(categoria);
        for (Video video : videos.values()) {
            if ((video.getCategoria() + "").equals(id)) {
                String[] fila = videos.toString().split(",");
                fila[0] = video.getNombre();
                fila[1] = video.getFecha().toString();
                fila[2] = categorias.get(video.getCategoria()).getNombre();
                fila[3] = usuarios.get(video.getUsuario()).getNombres();
                fila[4] = video.getValor() + "$";
                fila[5] = Video.CALIFICACIONES[video.getCalificacion() - 1];
                datos.add(fila);
            }
        }
        return datos;
    }

    public ArrayList<String[]> getVideosValor(String valor) {
        ArrayList<String[]> datos = new ArrayList<>();
        for (Video video : videos.values()) {
            if ((video.getValor() + "").equals(valor)) {
                String[] fila = videos.toString().split(",");
                fila[0] = video.getNombre();
                fila[1] = video.getFecha().toString();
                fila[2] = categorias.get(video.getCategoria()).getNombre();
                fila[3] = usuarios.get(video.getUsuario()).getNombres();
                fila[4] = video.getValor() + "$";
                fila[5] = Video.CALIFICACIONES[video.getCalificacion() - 1];
                datos.add(fila);
            }
        }
        return datos;
    }

    public ArrayList<String[]> getListas(int usuario) {
        ArrayList<String[]> datos = new ArrayList<>();
        for (ListaReproduccion lista : listas.values()) {
            if ((lista.getUsuario() + "").equals(usuario + "")) {
                String[] fila = new String[4];
                fila[0] = lista.getNombre();
                fila[1] = lista.getFecha().toString();
                fila[2] = lista.getVideos().size() + "";
                int calificacion = 0;
                for (Integer video : lista.getVideos()) {
                    calificacion = +(videos.get(video).getCalificacion() / lista.getVideos().size());
                }
                fila[3] = calificacion + "";
                datos.add(fila);
            }
        }
        return datos;
    }

    public int getListaNombre(int usuario, String nombre) {
        ArrayList<String[]> datos = new ArrayList<>();
        for (ListaReproduccion lista : listas.values()) {
            if ((lista.getUsuario() + "").equals(usuario + "") && lista.getNombre().equals(nombre)) {
                return lista.getId();
            }
        }
        return -1;
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
            String[] fila = videos.toString().split(",");
            fila[0] = video.getNombre();
            fila[1] = video.getFecha().toString();
            fila[2] = categorias.get(video.getCategoria()).getNombre();
            fila[3] = usuarios.get(video.getUsuario()).getNombres();
            fila[4] = video.getValor() + "$";
            fila[5] = Video.CALIFICACIONES[video.getCalificacion() - 1];
            datos.add(fila);
        }
        return datos;
    }

    public int getUsuarioActual() {
        return usuarioActual;
    }

    public String copiarVideo(String ruta, String usuario, String nombre) {
        File origen = new File(ruta);
        File destino = new File(".\\videos\\" + usuario + "\\" + nombre + ".mov");
        try {
            destino.getParentFile().mkdirs();
            Files.copy(Paths.get(origen.getAbsolutePath()), Paths.get(destino.getAbsolutePath()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(CapaLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ".\\\\videos\\\\" + usuario + "\\\\" + nombre + ".mov";
    }

    // ADMINISTRADOR
    public String validarCamposAdministrador(long identificacion, String nombres, String apellidos, int edad, String correo,
            String usuario, String clave, String avatar) {

        String error_message = "";

        if (edad < 18) {
            error_message = "La edad requerida es de 18 años en adelante";
        }

        // comprueba que la clave contena los caracteres adecuados y la cantidad permitida
        error_message = comprobacionClavePermitida(clave, error_message);
        error_message = comprobacionValoresUnicos(correo, usuario, error_message);

        if (!"".equals(error_message)) {
            return error_message;
        } else {

            if (!"".equals(avatar)) {
                avatar = guardarImagen(avatar);
            }

            guardarUsuarioAdministrador(identificacion, nombres, apellidos, edad, correo,
                    usuario, clave, avatar);

        }

        return error_message;
    }

    private String comprobacionValoresUnicos(String correo, String usuario, String error_message) {

        boolean correoValidado;
        boolean usuarioValidado;

        AdministradorController adminCtrl = new AdministradorController();

        correoValidado = adminCtrl.comprobarCorreoUnico(correo);
        usuarioValidado = adminCtrl.comprobarUsuarioUnico(usuario);

        if (correoValidado) {
            error_message = "El correo ingresado ya se encuentra registrado ";
        }

        if (usuarioValidado) {
            error_message = "El usuario ingresado ya se encuentra registrado ";
        }

        return error_message;
    }

    private String guardarImagen(String avatar) {

        File file = new File(avatar);
        AdministradorController adminCtrl = new AdministradorController();

        int temp, ultimo_id_usuario;
        String nombre = file.getName();
        try {

            temp = adminCtrl.ultimoIdUsuario();
            ultimo_id_usuario = temp + 1;

            BufferedImage imagen;
            File directorio = new File(Usuarios.URL_AVATARS + ultimo_id_usuario);

            if (!directorio.isDirectory()) {
                directorio.mkdir();
            }

            nombre = file.getName();
            imagen = ImageIO.read(file);

            ImageIO.write(imagen, "jpg", new File(Usuarios.URL_AVATARS + ultimo_id_usuario + "\\" + nombre));

            return ultimo_id_usuario + "/" + nombre;

        } catch (IOException ex) {
            Logger.getLogger(CapaLogica.class.getName()).log(Level.SEVERE, null, ex);
            return 1 + "/" + nombre;
        }

    }

    private String comprobacionClavePermitida(String clave, String message) {

        if (clave.length() >= 6 && clave.length() <= 8) {

            int contador[];
            contador = new int[3];

            for (int i = 0; i < clave.length(); i++) {

                if (Character.isUpperCase(clave.charAt(i))) {
                    contador[0] = 1;
                }
                if (Character.isLowerCase(clave.charAt(i))) {
                    contador[1] = 2;
                }
                if (!Character.isLetterOrDigit(clave.charAt(i))) {
                    contador[2] = 3;
                }
            }

            if ((contador[0] != 1) || (contador[1] != 2) || (contador[2] != 3)) {
                message = "La contraseña debe contener 1 mayuscula, 1 minuscula y 1 caracter especial!";
            }

        } else if (clave.length() < 6) {
            message = "La contraseña debe contar con minimo 6 caracteres";
        } else {
            message = "La contraseña debe contar con maximo 8 caracteres";
        }

        return message;
    }

    private void guardarUsuarioAdministrador(long identificacion, String nombres, String apellidos, int edad, String correo,
            String usuario, String clave, String avatar) {

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
        int id = (usuarios.size() > 0) ? Collections.max(usuarios.keySet()) : 0;
        admin.setId_usuario(id + 1);
        usuarios.put(id + 1, admin);
    }

    // VALIDACION USUARIO O ADMINISTRADOR INICIO DE SESION
    public String comprobarUsuario(String Usuario, String clave) {
        usuarioActual = getUsuario(Usuario, clave);
        String error_message = "";
        Usuarios user;

        UsuariosController usuarioCtrl = new UsuariosController();
        user = usuarioCtrl.comprobarUsuario(Usuario, clave);

        if (user == null) {
            error_message = "Usuario y/o contraseña incorrecta";
        } else {
            if (user.getCodigo_verificacion() == 0) {
                error_message = enviarCodigoVerificacion(user.getId_usuario(), user.getCodigo_verificacion(), user.getCorreo(), error_message);

                if (!"".equals(error_message)) {
                    return error_message;
                }
            }

            if (user.getId_rol() == 1) {
                error_message = "administrador_" + user.getId_usuario();
            } else {
                error_message = "usuario_" + user.getId_usuario();
            }
            return error_message;
        }
        return error_message;
    }

    public int getUsuario(String usuario, String clave) {
        for (Usuarios user : usuarios.values()) {
            if (user.getUsuario().equals(usuario) && user.getClave().equals(clave)) {
                return user.getId_usuario();
            }
        }
        return -1;
    }

    private String enviarCodigoVerificacion(int id_usuario, int codigo_verificacion, String correo, String error_message) {

        Random r = new Random();
        String asunto = "Codigo Verificacion";
        codigo_verificacion = r.nextInt(200000 - 100000 + 1) + 100000;
        boolean envioCorreo = enviarCorreoCodigo(codigo_verificacion, correo, asunto);

        if (!envioCorreo) {
            error_message = "No ha sido posible enviar el codigo de verificacion";
            return error_message;
        }
        int codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el codigo de verificacion"));

        if (codigo != codigo_verificacion) {
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

        if (!actualizacion) {
            error_message = "No se ha podido realizar la verificacion";
        }

        return error_message;
    }

    // USUARIO 
    public String validarCamposUsuario(long identificacion, String nombres, String apellidos, int edad, String correo,
            String usuario, String clave, String avatar, int distrito) {

        String error_message = "";

        if (edad < 18) {
            error_message = "La edad requerida es de 18 años en adelante";
        }

        // comprueba que la clave contena los caracteres adecuados y la cantidad permitida
        error_message = comprobacionClavePermitida(clave, error_message);
        error_message = comprobacionValoresUnicos(correo, usuario, error_message);

        if (!"".equals(error_message)) {
            return error_message;
        } else {

            if (!"".equals(avatar)) {
                avatar = guardarImagen(avatar);
            }

            guardarUsuario(identificacion, nombres, apellidos, edad, correo,
                    usuario, clave, avatar, distrito);
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
        int id = (usuarios.size() > 0) ? Collections.max(usuarios.keySet()) : 0;
        users.setId_distrito(id + 1);
        usuarios.put(id + 1, users);
    }

    public String comprobarExisteUsuario(String usuario) {

        String error_message = "";

        UsuariosController user = new UsuariosController();
        Usuarios users = user.comprobarExisteUsuario(usuario);

        if (users == null) {
            error_message = "El usuario ingresado no existe";
            return error_message;
        }

        error_message = cambiarClave(users.getId_usuario(), users.getCorreo(), error_message);

        return error_message;
    }

    private String cambiarClave(int id_usuario, String Correo, String error_message) {

        Random r = new Random();
        String asunto = "Cambio de clave";
        boolean enviarMail, actualizar;
        int clave = 0;

        clave = r.nextInt(200000 - 100000 + 1) + 100000;

        UsuariosController user = new UsuariosController();

        actualizar = user.cambiarClave(id_usuario, clave);

        if (!actualizar) {
            error_message = "No se ha podido generar la clave";
            return error_message;
        }

        enviarMail = enviarCorreoCodigo(clave, Correo, asunto);

        if (!enviarMail) {
            error_message = "No se ha podido enviar la clave generada";
        }

        return error_message;
    }

    public boolean verificarExisteUsuarioAdministrador() {
        return AdministradorController.verificarExisteUsuarioAdministrador();
    }

    public void eliminarVideo(String id) {
        ControllerVideo.eliminar(id);
        eliminarArchivoVideo(new File(videos.get(Integer.parseInt(id)).getRuta()));
        videos.remove(Integer.parseInt(id));
        eliminarReproduccionVideo(Integer.parseInt(id));
        eliminarListaVideo(Integer.parseInt(id));
    }

    private void eliminarListaVideo(int video) {
        for (ListaReproduccion lista : listas.values()) {
            for (Integer vid : lista.getVideos()) {
                if (vid == video) {
                    ControllerListaReproduccion.eliminarVideo(lista.getId(), video);
                }
            }
        }
        listas = ControllerListaReproduccion.listar();
    }

    private void eliminarReproduccionVideo(int video) {
        for (Reproduccion reproducc : reproduccion.values()) {
            if (reproducc.getVideo() == video) {
                eliminarReproduccion(reproducc.getUsuario());
            }
        }
        reproduccion = ControllerReproduccion.listar();
    }

    public void eliminarLista(int id) {
        for (Integer video : listas.get(id).getVideos()) {
            ControllerListaReproduccion.eliminarVideo(id, video);
        }
        ControllerListaReproduccion.eliminar(id);
        listas.remove(id);
    }

    public void comprarVideo(int usuario, int video) {
        if (getTema("compras") == -1) {
            agregarTema("compras", "Videos comprados por un usuario");
        }
        ControllerListaReproduccion.comprar(usuario, video, getTema("compras"));
    }

    private void eliminarArchivoVideo(File file) {
        file.delete();
    }

    public long getReproduccionTiempo(int videoActual) {
        long tiempo = 0;
        if (reproduccion.get(videoActual) != null) {
            tiempo = reproduccion.get(videoActual).getTiempo();
        }
        return tiempo;
    }

    public int getDistrito(String nombre) {
        for (Distrito distrito : distritos.values()) {
            if (distrito.getNombre().equals(nombre)) {
                return distrito.getId();
            }
        }
        return -1;
    }

    public void eliminarDistrito(int id) {
        for (Usuarios usuari : usuarios.values()) {
            if (usuari.getId_distrito() == id) {
                eliminarUsuario(usuari.getId_usuario());
            }
        }
        ControllerDistrito.eliminar(id);
        distritos.remove(id);
    }

    public void eliminarCanton(int id) {
        for (Distrito distrito : distritos.values()) {
            if (distrito.getCanton() == id) {
                eliminarDistrito(distrito.getId());
            }
        }
        ControllerCanton.eliminar(id);
        cantones.remove(id);
    }

    private void eliminarUsuario(int id) {
        for (Video video : videos.values()) {
            if (video.getUsuario() == id) {
                eliminarVideo(video.getId() + "");
            }
        }
        for (ListaReproduccion lista : listas.values()) {
            if (lista.getUsuario() == id) {
                eliminarLista(lista.getId());
            }
        }
        UsuariosController.eliminar(id);
        usuarios.remove(id);
    }

    public void eliminarProvincia(int id) {
        for (Canton canton : cantones.values()) {
            if (canton.getProvincia() == id) {
                eliminarCanton(canton.getId());
            }
        }
        ControllerProvincia.eliminar(id);
        provincias.remove(id);
    }

    public void eliminarCategoria(int id) {
        for (Video video : videos.values()) {
            if (video.getCategoria() == id) {
                eliminarVideo(video.getId() + "");
            }
        }
        ControllerCategoria.eliminar(id);
        categorias.remove(id);
    }

    public void eliminarTema(int id) {
        for (ListaReproduccion lista : listas.values()) {
            if (lista.getTema() == id) {
                eliminarLista(id);
            }
        }
        ControllerTema.eliminar(id);
        temas.remove(id);
    }

    public String getUsuarioAvatar(int usuarioActual) {
        return usuarios.get(usuarioActual).getAvatar();
    }

    public String getUsuarioNombre(int usuarioActual) {
        return usuarios.get(usuarioActual).getUsuario();
    }

    public ArrayList<String[]> getReproduccionUsuario(int usuarioActual) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getReproduccionVideo(int usuarioActual) {
        for(Reproduccion reproducc: reproduccion.values()){
            if(reproducc.getUsuario() == usuarioActual && reproducc.getTiempo() != 0){
                return reproducc.getVideo();
            }
        }
        return -1;
    }

}
