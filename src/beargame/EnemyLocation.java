package beargame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Class to get the enemies location from the database
 * @author Antonio Jesús Ibáñez García
 */
public class EnemyLocation {
    
    static String nombreBD = "";
    static String usuario = "";
    static String password = "";
    static String url = "jdbc:mysql://" + nombreBD;
    private int id = 0;
    private int tiempo = 0;
    private int type = 0;
    private double width = 0;
    private double height = 0;
    private double vX = 0;
    private double vY = 0;
    
    /**
     * Empty constructor of the class
     */
    public EnemyLocation() {
        id = 0;
        tiempo = 0;
        type = 0;
        width = 0;
        height = 0;
        vX = 0;
        vY = 0;
    }
    
    /**
     * Constructor of the class
     * @param id It is the id
     * @param tiempo It is the time when the enemy enter into the game
     * @param type It is the type's enemy
     * @param width It is the X position in the game
     * @param height It is the Y position in the game
     * @param vX It is the velocity of the X axis
     * @param vY It is the velocity in the Y axis
     */
    public EnemyLocation(int id, int tiempo, int type, double width, double height, double vX, double vY) {
        this.id = id;
        this.tiempo = tiempo;
        this.type = type;
        this.width = width;
        this.height = height;
        this.vX = vX;
        this.vY = vY;
    }
    
    /**
     * Method to get the enemy with the ID
     * @param id_ref It is the ID of the enemy
     */
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
    
    /**
     * Method to get the last ID in the database of an enemy
     * @return The max ID
     */
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
    
    /**
     * Method to connect with the database
     * @return The connection
     */
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
    
    /**
     * Method to disconnect the database
     * @param con It is the connection
     */
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
