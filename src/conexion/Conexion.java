
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alvaro García <alvarogarcia1010 at github.com>
 */

public class Conexion {
    private String user;
    private String pass;
    private String driver;
    private String url;
    private Connection cnx;
    
    public static Conexion instance;
    
    private Conexion(){
        cargarCredenciales();
        
        try{
            Class.forName(this.driver);
            this.cnx = (Connection) DriverManager.getConnection(this.url, this.user, this.pass);
            
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public synchronized static Conexion conectar(){
        if(instance == null){
            return new Conexion();
        }
        
        return instance;
    }
    
    //CREDENCIALES
    private void cargarCredenciales(){
        this.user = "root";
        this.pass = "6ce69ba8";
        this.driver = "com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3206/filtros";
    }
    
    public Connection getCnx(){
        return cnx;
    }
    
    public void cerrarConexion(){
        Conexion.instance = null;
    }
    
}
