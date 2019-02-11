/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author KSGAMER
 */
public class BD {
    protected Connection con;
    protected Statement pst = null;
    
    
    public static Connection GetConnection() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost/bar";
            String usuarioDB = "root";
            String passwordDB = "";
            conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error 1 en la Conexión con la Base de Datos "+ ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
            conexion = null;
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error 2 en la Conexión con la Base de Datos "+ ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } finally {
            return conexion;
        }
    }
}
