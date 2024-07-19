
package Persistencia;

import Logica.Rol;
import Logica.Usuario;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class controladoraPersistencia {
    
    private EntityManagerFactory emf = null;

    public controladoraPersistencia() {
        this.emf = Persistence.createEntityManagerFactory("GimnasioArnold");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    UsuarioJpaController usuJpa = new UsuarioJpaController();
    RolJpaController rolJpa = new RolJpaController();
    SocioJpaController SocioJpa = new SocioJpaController();
    
    public List<Usuario> traerUsuario() {
        return usuJpa.findUsuarioEntities();
    }
    
    public List<Rol> traerRoles() {
        return rolJpa.findRolEntities();
    }
    
    public void crearUsuario(Usuario usu) {
        try {
            usuJpa.create(usu);
        } catch (Exception e) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void borrarUsuario(Integer id_usuario) {
        try {
            usuJpa.destroy(id_usuario);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Usuario traerUsuario(int id_usuario) {
        return usuJpa.findUsuario(id_usuario);
    }

    public void editarUsuario(Usuario usu) {
        try {
            usuJpa.edit(usu);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void crearCliente(Usuario usu) {
        try {
            usuJpa.create(usu);
        } catch (Exception e) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
