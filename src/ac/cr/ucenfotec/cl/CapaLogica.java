package ac.cr.ucenfotec.cl;

import ac.cr.ucenfotec.bl.canton.Canton;
import ac.cr.ucenfotec.bl.categoria.Categoria;
import ac.cr.ucenfotec.bl.distrito.Distrito;
import ac.cr.ucenfotec.bl.provincia.Provincia;
import ac.cr.ucenfotec.bl.tema.Tema;
import ac.cr.ucenfotec.bl.usuario.Usuario;
import ac.cr.ucenfotec.bl.video.Video;
import ac.cr.ucenfotec.tl.ControllerCanton;
import ac.cr.ucenfotec.tl.ControllerCategoria;
import ac.cr.ucenfotec.tl.ControllerTema;
import ac.cr.ucenfotec.tl.ControllerDistrito;
import ac.cr.ucenfotec.tl.ControllerProvincia;
import ac.cr.ucenfotec.tl.ControllerVideo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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
}
