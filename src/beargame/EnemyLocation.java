package beargame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Class to get the enemies location from the database
 * This class was built because it was a class requirement, but now it doesn't connect with the game
 * @author Antonio Jesús Ibáñez García
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
    
    /**
     * Method to get the enemy's ID
     * @return The enemy's ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Method to get the variable Time when the enemy enter into the game
     * @return The enemy's time
     */
    public int getTiempo() {
        return tiempo;
    }
    
    /**
     * Method to get the enemy's type
     * @return The enemy's type
     */
    public int getType() {
        return type;
    }

    /**
     * Method to get the enemy's X position in the game
     * @return The enemy's X position 
     */
    public double getWidth() {
        return width;
    }

    /**
     * Method to get the enemy's Y position in the game
     * @return The enemy's Y position 
     */
    public double getHeight() {
        return height;
    }

    /**
     * Method to get the enemy's X velocity in the game
     * @return The enemy's X velocity 
     */
    public double getvX() {
        return vX;
    }

    /**
     * Method to get the enemy's Y velocity in the game
     * @return The enemy's Y velocity 
     */
    public double getvY() {
        return vY;
    }

    /**
     * Method to set the enemy's ID
     * @param id It is the enemy's ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method to set the enemy's time
     * @param tiempo It is the enemy's time when enter into the game
     */
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * Method to set the enemy's type
     * @param type It is the enemy's type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Method to set the enemy's X position in the game
     * @param width It is the enemy's X position 
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Method to set the enemy's Y position in the game
     * @param height It is the enemy's Y position 
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Method to set the enemy's X velocity in the game
     * @param vX It is the enemy's X velocity 
     */
    public void setvX(double vX) {
        this.vX = vX;
    }

    /**
     * Method to set the enemy's Y velocity in the game
     * @param vY It is the enemy's Y velocity 
     */
    public void setvY(double vY) {
        this.vY = vY;
    }
    
    /**
     * Method to add every enemy in the game
     */
    /*
    public void getEnemy() {
            for (int i = 0; i < enemies.size(); i++) {
                if (tiempo == enemies.get(i).getTiempo()) {
                    ObjectGame object = createEnemy(enemies.get(i).getType(),enemies.get(i).getWidth(),enemies.get(i).getHeight(), enemies.get(i).getvX(), enemies.get(i).getvY());
                    createDisplayedObject(object);
                    addNewGameObjectNodes(object.spriteFrame);
                    id++;
                }
                if (maxId == id) {
                    tiempo = 0;
                    id = 0;
                }
            }
    }
    
    /**
     * Method to get every enemy from the database
     */
    /*
    public void loadEnemies() {
        maxId = EnemyLocation.getMaxID();
        for (int i = 1; i < maxId+1; i++) {
            EnemyLocation enemy = new EnemyLocation();
            enemy.selectEnemy(i);
            enemies.add(enemy);
        }
    }*/
    
    
}
