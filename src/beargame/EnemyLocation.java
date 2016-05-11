package beargame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Anto
 */
public class EnemyLocation {
    
    static String nombreBD = "savebear";
    static String usuario = "root";
    static String password = "";
    static String url = "jdbc:mysql://localhost/" + nombreBD;
    private int id = 0;
    private int tiempo = 0;
    private int type = 0;
    private double width = 0;
    private double height = 0;
    private double vX = 0;
    private double vY = 0;
    
    public EnemyLocation() {
        id = 0;
        tiempo = 0;
        type = 0;
        width = 0;
        height = 0;
        vX = 0;
        vY = 0;
    }
    
    public EnemyLocation(int id, int tiempo, int type, double width, double height, double vX, double vY) {
        this.id = id;
        this.tiempo = tiempo;
        this.type = type;
        this.width = width;
        this.height = height;
        this.vX = vX;
        this.vY = vY;
    }
    
    
    public void selectEnemy(int id_ref) {
        Connection con = conectarBD();
        
        try {
            // Ejecutamos un SELECT
            Statement st = con.createStatement(); 
            String sql = "SELECT id, tiempo, tipo, width, height, vX, vY FROM enemigos"
                    + " WHERE id = " + id_ref;
            ResultSet rs = st.executeQuery(sql);
    
            while (rs.next()) {
                id = rs.getInt("id");
                tiempo = rs.getInt("tiempo");
                type = rs.getInt("tipo");
                width = rs.getDouble("width");
                height = rs.getDouble("height");
                vX = rs.getDouble("vX");
                vY = rs.getDouble("vY");
            }
            rs.close();
            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        desconectarBD(con);
    }
    
    public static int getMaxID () {
        Connection con = conectarBD();
        int id = 0;
        try {
            // Ejecutamos un SELECT
            Statement st = con.createStatement(); 
            String sql = "Select max(id) from enemigos";
            ResultSet rs = st.executeQuery(sql);
    
            while (rs.next()) {
                id = rs.getInt("max(id)");
            }
            rs.close();
            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        desconectarBD(con);
        return id;
    }
    
    public static Connection conectarBD() {
        Connection con = null;
        // Conectamos con la BD
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url,usuario,password);
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        return con;
        
    }
    
    public static void desconectarBD(Connection con) {
        try {
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public int getTiempo() {
        return tiempo;
    }
    
    public int getType() {
        return type;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getvX() {
        return vX;
    }

    public double getvY() {
        return vY;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setvX(double vX) {
        this.vX = vX;
    }

    public void setvY(double vY) {
        this.vY = vY;
    }
    
    
}
