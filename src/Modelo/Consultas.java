/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KSGAMER
 */
public class Consultas {

    BD cc = new BD();
    Connection cn = cc.GetConnection();
    String cons;
    DefaultTableModel model;
    int id;
    Statement st;

    //
    //Tabla de Usuarios
    public ResultSet TablaUsu(String sql) {
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //Consulta
    public ResultSet consultar(String Campos, String tabla, String p1) {
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery("select " + Campos + " from " + tabla + " where " + p1);
            //System.out.println("select " + Campos + " from " + tabla + " where " + p1);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Error Consulta BD");
            System.out.println("Rompio aki");
            return null;
        }
    }

    //modificar Usuario
    public void ModificarUser(String Rsql) {
        //UPDATE `usuario` SET `name` = 'Praxx' WHERE `usuario`.`id` = 7;
        try {
            st = cn.createStatement();
            st.executeUpdate("UPDATE usuario SET " + Rsql);
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Eliminar Usuario
    public void EliminarUser(String sql) {
        try {
            st = cn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void EliminarDama(String sql) {
        try {
            st = cn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void EliminarCobro(String sql) {
        try {
            st = cn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void LimpiarVenta(String sql) {
        try {
            st = cn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //agregar usuario nuevo 
    public void AgregarUsuario(String nombre, String Apellido, String Usuario, String Contrase, String Fecha, String Estado) {
        try {
            st = cn.createStatement();
            //INSERT INTO `usuario` (`id`, `name`, `lastname`, `username`, `password`, `profile`, `date`, `status`) VALUES (NULL, 'Pedro', 'Gonzalez', 'Praxx', MD5('123456'), '1', '2019-01-24', '1');
            st.executeUpdate("INSERT INTO usuario(name,lastname,username,password,profile,date,status)"
                    + " VALUES ('" + nombre + "','" + Apellido + "','" + Usuario + "', MD5('" + Contrase + "'),'" + Estado + "','" + Fecha + "', '1')");
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Metodos de Categoria
    public void AgregarCate(String nombre) {
        try {
            st = cn.createStatement();
            ResultSet rs = consultar("id", "categoria", " name = '" + nombre + "'");
            String y = "";
            while (rs.next()) {
                y = rs.getString(1);
            }
            if (!y.equals("")) {
                st.executeUpdate("UPDATE categoria SET status = 1 WHERE id = '" + y + "'");
            } else {

                //INSERT INTO `categoria` (`id`, `name`, `status`) VALUES (NULL, 'ca2', '');
                st.executeUpdate("INSERT INTO categoria (name,status) VALUES"
                        + "('" + nombre + "',1)");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ModificarCar(String Rsql, String nombre) {
        //UPDATE `usuario` SET `name` = 'Praxx' WHERE `usuario`.`id` = 7;
        try {
            st = cn.createStatement();
            ResultSet rs = consultar("id", "categoria", " name = '" + nombre + "'");
            String y = "";
            while (rs.next()) {
                y = rs.getString(1);
            }
            if (!y.equals("")) {
                JOptionPane.showMessageDialog(null, "Esta categoria ya existe");
            } else {

                st.executeUpdate("UPDATE categoria SET " + Rsql);
                //        System.out.println("UPDATE producto SET "+Rsql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Metodos de Producto
    //modificar
    public void ModificarPr(String Rsql) {
        //UPDATE `usuario` SET `name` = 'Praxx' WHERE `usuario`.`id` = 7;
        try {
            st = cn.createStatement();
            st.executeUpdate("UPDATE producto SET " + Rsql);
            //        System.out.println("UPDATE producto SET "+Rsql);
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ModificarDama(String sql) {
        try {
            st = cn.createStatement();
            st.executeUpdate("UPDATE cervezadama SET " + sql);
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Eliminar
    public void EliminarPro(String sql) {
        try {
            st = cn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //agregar
    public void AgregarPro(String nombre, String precio, String unidad, String imagen, String cate) {
        try {
            st = cn.createStatement();
            //INSERT INTO `producto` (`id`, `name`, `price`, `unity`, `image`, `idCategory`, `status`) VALUES (NULL, 'Victoria', '34', '12', 'No hay', '2', '1');
            st.executeUpdate("INSERT INTO producto(name,price,unity,image,idCategory,status)"
                    + " VALUES ('" + nombre + "','" + precio + "','" + unidad + "','" + imagen + "','" + cate + "','1')");
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //agregar Cerveza Dama nuevo
    public void AgregarCervDama(String nombre, String unidad, String precio, String producto, String categoria, String usuario) {
        try {
            st = cn.createStatement();
            st.executeUpdate("INSERT INTO cervezadama (dama, unity, price, idProduct, idCategory, idUser, status)"
                    + " VALUES ('" + nombre + "' , '" + unidad + "', '" + precio + "', '" + producto + "', '" + categoria + "', '" + usuario + "', '1')");
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AgregarPago(String total, String pago, String cambio, String usuario) {
        try {
            st = cn.createStatement();
            st.executeUpdate("INSERT INTO pago (Total, Pago, Cambio, idUser, Fecha) VALUES ('" + total + "', '" + pago + "', '" + cambio + "', '" + usuario + "', now())");
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AgregarCobro(String producto, String cantidad) {
        try {
            st = cn.createStatement();
            st.executeUpdate("INSERT INTO venta (idProduct, Cantidad, status) VALUES ('" + producto + "', '" + cantidad + "', 1)");
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Corte Caja nuevo
    public void CorteCaja(String Monto, String Usuario) {
        try {
            if (Usuario != null) {
                st = cn.createStatement();
                st.executeUpdate("INSERT INTO corte (monto, idUser) VALUES ('" + Monto + "', '" + Usuario + "')");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
