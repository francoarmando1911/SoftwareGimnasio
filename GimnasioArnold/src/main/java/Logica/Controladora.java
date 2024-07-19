package Logica;

import Persistencia.controladoraPersistencia;
import java.util.List;

public class Controladora {
    
    private controladoraPersistencia controlPersis;

    public Controladora(controladoraPersistencia controlPersis) {
        this.controlPersis = controlPersis;
    }

    public Usuario validarUsuario(String usuario, String contrasena) {
        Usuario usr = null;
        List<Usuario> listaUsuarios = controlPersis.traerUsuario();

        for (Usuario usu : listaUsuarios) {
            if (usu.getNombre().equals(usuario)) {
                if (usu.getContrasena().equals(contrasena)) {
                    usr = usu;
                    return usr; 
                } else {
                    return null; // Contraseña incorrecta
                }
            }
        }
        return null; // Usuario no encontrado
    }

    public List<Usuario> traerUsuarios() {
        return controlPersis.traerUsuario();
    } 
    
    public List<Rol> traerRoles() {
        return controlPersis.traerRoles();
    }
    
    public void crearUsuario(String usuario, String contra, String rolRecibido) {
        Usuario usu = new Usuario();
        usu.setNombre(usuario);
        usu.setContrasena(contra);
        
        Rol rolEncontrado = this.traerRol(rolRecibido);
        
        if(rolEncontrado != null) {
            usu.setRol(rolEncontrado);
        }
        
        int id = this.buscarUltimaIdUsuarios();
        usu.setId(id + 1);
        
        try {
            controlPersis.crearUsuario(usu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private Rol traerRol(String rolRecibido) {
        List<Rol> listaRoles = controlPersis.traerRoles();
    
        for (Rol rol : listaRoles) {
            if (rol.getNombreRol().equals(rolRecibido)) {
                return rol;
            }
        }
        return null;
    }
    
    private int buscarUltimaIdUsuarios() {
        List<Usuario> listaUsuarios = this.traerUsuarios();
        
        if (!listaUsuarios.isEmpty()) {
            Usuario usu = listaUsuarios.get(listaUsuarios.size() - 1);
            return usu.getId();
        } else {
            return 0; // Si la lista está vacía, devolvemos 0 o un valor por defecto
        }
    }
    
    public void borrarUsuario(int id_usuario) {
        try {
            controlPersis.borrarUsuario(id_usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Usuario traerUsuario(int id_usuario) {
        return controlPersis.traerUsuario(id_usuario);
    }
    
    public void editarUsuario(Usuario usu, String usuario, String contra, String rolRecibido) throws Exception {
        usu.setNombre(usuario);
        usu.setContrasena(contra);
        
        Rol rolEncontrado = this.traerRol(rolRecibido);
        
        if(rolEncontrado != null){
            usu.setRol(rolEncontrado);
        }
        
        controlPersis.editarUsuario(usu);
    }
}

    
    

