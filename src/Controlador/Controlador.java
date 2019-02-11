/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Consultas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KSGAMER
 */
public class Controlador {
    
    public Controlador() {
    }

    Consultas con = new Consultas();
    //Metodos de Modulo de Categoria

    public DefaultTableModel TablaCategoria() {

        String titulos[] = {"Nombre"};
        String[] registros = new String[1];
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        ResultSet Info = con.TablaUsu("SELECT name FROM categoria  where status = 1");
        try {
            while (Info.next()) {
                registros[0] = Info.getString(1);
                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }
    
    public DefaultTableModel TablaVenta(String Buscar) {
        String titulos[] = {"Nombre", "Unidad", "Precio", "Cantidad"};
        String[] registros = new String[4];
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        ResultSet Info = con.TablaUsu("SELECT name, unity, price FROM producto where name like CONCAT('%','"+Buscar+"','%')");
        try {
            while(Info.next()) {
                registros[0] = Info.getString(1);
                registros[1] = Info.getString(2);
                registros[2] = Info.getString(3);
                registros[3] = Info.getString(4);
                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }

    //Metodos de Modulo de Producto
    public String idPrC(String Nombre) {
        String id = null;
        ResultSet rs = con.consultar("id", "categoria", " name = '" + Nombre + "' and status = 1");
        try {
            while (rs.next()) {
                id = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public String idUser(String Usuario, String Password) {
        String id = null;
        ResultSet rs = con.consultar("id", "usuario", "username = '" + Usuario + "' and password = '" + Password + "' and status = 1");
        try {
            while(rs.next()) {
                id = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(id != null) {
            return id;
        }  else {
            JOptionPane.showMessageDialog(null, "Usuario y/o Contraseña incorrectos", "Advertencia", 2);
            return null;
        }
    }
    
    public String idCDUser(String Nombre) {
        String id = null;
        ResultSet rs = con.consultar("id", "usuario", "username = '" + Nombre + "' and status = 1");
        try {
            while(rs.next()) {
                id = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public String idPr(String Nombre) {
        String id = null;
        ResultSet rs = con.consultar("id", "producto", "name = '" + Nombre + "' and status = 1");
        try {
            while(rs.next()) {
                id = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public void ModificarCate(String Nombre, String idC){
        //UPDATE `categoria` SET `status` = '1' WHERE `categoria`.`id` = 3;
        con.ModificarCar("name ='"+Nombre+"' where id= '"+idC+"'",Nombre);
    }
    
    public void ModificarProduc(String nombre, String precio, String unidad, String imagen, String cate,String idP) {
        String id = idPrC(cate);
        con.ModificarPr("name = '" + nombre + "',price='" + precio + "', unity='" + unidad + "', image='" + imagen + "',idCategory='" + id + "' where id = '"+idP+"'");
    }
    
    public void ModificarDama(String nombre, String unidad, String precio, String producto, String categoria, String usuario, String id) {
        String idC = idPrC(categoria);
        String idP = idPr(producto);
        String idU = idCDUser(usuario);
        con.ModificarDama("dama = '" + nombre + "', unity = '" + unidad + "', price = '" + precio + "', idProduct = '" + idP + "', idCategory = '" + idC + "', idUser = '" + idU + "' where id = '" + id + "'");
    }

    public void EliminarCate(String idC){
        con.EliminarPro("Update categoria set status = 0 where id='"+idC+"'");
    }
    
    public void EliminarPro(String Nombre, String Unidad){
        con.EliminarPro("Update producto set status = 0 where name ='"+Nombre+"' and unity='"+Unidad+"'");
    }
    
    public void AgregarCate(String Cat){
        con.AgregarCate(Cat);
    }
    
    public void AgregarProduc(String nombre, String precio, String unidad, String imagen, String cate) {
        String id = idPrC(cate);
        con.AgregarPro(nombre, precio, unidad, imagen, id);
    }
    
    public void AgregarCervDama(String nombre, String unidad, String precio, String producto, String categoria, String usuario) {
        String idC = idPrC(categoria);
        String idU = idCDUser(usuario);
        String idP = idPr(producto);
        con.AgregarCervDama(nombre, unidad, precio, idP, idC, idU);
    }
    
    public void AgregarPago(String total, String pago, String cambio, String usuario) {
        String idU = idCDUser(usuario);
        con.AgregarPago(total, pago, cambio, idU);
    }
    
    public void AgregarCobro(String Producto, String Cantidad) {
        String idP = idPr(Producto);
        con.AgregarCobro(idP, Cantidad);
    }

    public ArrayList ComboxPro() {
        ArrayList array = new ArrayList();
        ResultSet Info = con.TablaUsu("SELECT name FROM categoria  where status = 1");
        try {
            while (Info.next()) {
                array.add(Info.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
    
    public ArrayList TotalPrecio() {
        ArrayList array = new ArrayList();
        ResultSet Info = con.TablaUsu("SELECT p.price FROM venta as v INNER JOIN producto as p on p.id = v.idProduct WHERE v.status = 1");
        try {
            while(Info.next()) {
                array.add(Info.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
    
    public ArrayList TotalCantidad() {
        ArrayList array = new ArrayList();
        ResultSet Info = con.TablaUsu("SELECT v.Cantidad FROM venta as v INNER JOIN producto as p on p.id = v.idProduct WHERE v.status = 1");
        try {
            while(Info.next()) {
                array.add(Info.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
    
    public ArrayList VentaBuscar(String Buscar) {
        ArrayList array = new ArrayList();
        ResultSet Info = con.TablaUsu("SELECT name, unity, price FROM producto where name like CONCAT('%','"+Buscar+"','%') and status = 1");
        try {
            while(Info.next()) {
                array.add(Info.getString(1));
                array.add(Info.getString(2));
                array.add(Info.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
    
    public ArrayList ComboxProduct() {
        ArrayList array = new ArrayList();
        ResultSet Info = con.TablaUsu("SELECT name FROM producto where status = 1");
        try {
            while(Info.next()) {
                array.add(Info.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
    
    public ArrayList Fecha() {
        ArrayList array = new ArrayList();
        ResultSet Info = con.TablaUsu("SELECT date(now())");
        try {
            while(Info.next()) {
                array.add(Info.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
    
    public ArrayList ComboxUser() {
        ArrayList array = new ArrayList();
        ResultSet Info = con.TablaUsu("SELECT username FROM usuario where status = 1");
        try {
            while(Info.next()) {
                array.add(Info.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public DefaultTableModel TablaProductos() {

        //SELECT name,lastname,username FROM usuario
        String titulos[] = {"Nombre", "Precio", "Categoria", "Unidad"};
        String[] registros = new String[4];
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        ResultSet Info = con.TablaUsu("SELECT p.name,p.price,ca.name as Category, unity FROM producto as p inner join categoria as ca on p.idCategory = ca.id  where p.status = 1");
        try {
            while (Info.next()) {
                registros[0] = Info.getString(1);
                registros[1] = Info.getString(2);
                registros[2] = Info.getString(3);
                registros[3] = Info.getString(4);

                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }
    
    public DefaultTableModel TablaCobro() {
        String titulos[] = {"Nombre", "Unidad", "Precio", "Cantidad"};
        String[] registros = new String[4];
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        ResultSet Info = con.TablaUsu("SELECT p.name, p.unity, p.price, v.Cantidad FROM venta as v INNER JOIN producto as p on p.id = v.idProduct WHERE v.status = '1'");
        try {
            while(Info.next()) {
                registros[0] = Info.getString(1);
                registros[1] = Info.getString(2);
                registros[2] = Info.getString(3);
                registros[3] = Info.getString(4);
                
                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }
    
    public DefaultTableModel TablaCervDama() {
        String titulos[] = {"Dama", "Producto", "Categoria", "Precio", "Unidad", "Usuario"};
        String[] registros = new String[6];
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        ResultSet Info = con.TablaUsu("SELECT d.dama, p.name, c.name, d.price, d.unity, u.username FROM cervezadama as d INNER JOIN producto as p on p.id = d.idProduct INNER JOIN categoria as c on c.id = d.idCategory INNER JOIN usuario as u on u.id = d.idUser WHERE d.status = 1");
        try {
            while(Info.next()) {
                registros[0] = Info.getString(1);
                registros[1] = Info.getString(2);
                registros[2] = Info.getString(3);
                registros[3] = Info.getString(4);
                registros[4] = Info.getString(5);
                registros[5] = Info.getString(6);
                
                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }

    //Eliminar Usar
    public void EliminarUser(String id) {
        con.EliminarUser("UPDATE usuario SET status = '0' WHERE id = " + id);
    }
    
    public void EliminarDama(String id) {
        con.EliminarDama("UPDATE cervezadama SET status = '0' WHERE id = '" + id + "'");
    }
    
    public void EliminarCobro(String id) {
        con.EliminarCobro("UPDATE venta SET status = '0' WHERE id = '" + id + "'");
    }
    
    public void limpiarVenta() {
        con.LimpiarVenta("DELETE FROM venta");
    }

    public DefaultTableModel TablaUsuario() {

        //SELECT name,lastname,username FROM usuario
        String titulos[] = {"Nombre", "Apellido", "Usuario"};
        String[] registros = new String[3];
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        ResultSet Info = con.TablaUsu("SELECT name,lastname,username FROM usuario where status = 1");
        try {
            while (Info.next()) {
                registros[0] = Info.getString(1);
                registros[1] = Info.getString(2);
                registros[2] = Info.getString(3);

                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }

    public String id(String Usuario, String Nombre) {
        String id = null;
        ResultSet rs = con.consultar("id", "usuario", " name = '" + Nombre + "' and username = '" + Usuario + "'");
        try {
            while (rs.next()) {
                id = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public String idP(String Nombre, String Unidad ) {
        String id = null;
        ResultSet rs = con.consultar("id", "producto", " name ='"+Nombre+"' and unity='"+Unidad+"'");
        try {
            while (rs.next()) {
                id = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public String idD(String Nombre, String unidad, String precio) {
        String id = null;
        ResultSet rs = con.consultar("id", "cervezadama", " dama = '" + Nombre + "' and unity = '" + unidad + "' and price = '" + precio + "'");
        try {
            while(rs.next()) {
                id = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public String idCobro(String producto, String unidad, String cantidad) {
        String id = null;
        String idP = idP(producto, unidad);
        ResultSet rs = con.consultar("id", "venta", " idProduct = '" + idP + "' and Cantidad = '" + cantidad + "' and status = 1");
        try {
            while(rs.next()){
                id = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
      public String idC(String Nombre) {
        String id = null;
        ResultSet rs = con.consultar("id", "categoria", " name ='"+Nombre+"'");
        try {
            while (rs.next()) {
                id = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public void ModificarUser(String id, String Usuario, String Nombre, String Apellido, String Pass) {
        //name` = 'Praxx' WHERE `usuario`.`id` = 7
        if(Pass != null){
            con.ModificarUser("username = '" + Usuario + "', name ='" + Nombre + "',lastname='" + Apellido + "',`password` = MD5('" + Pass + "') WHERE id =" + id);
        }else{
            con.ModificarUser("username = '" + Usuario + "', name ='" + Nombre + "',lastname='" + Apellido + "' WHERE id =" + id);
        }
    }

    public void AgregarUser(String nombre, String Apellido, String Usuario, String Contrase, String Fecha, String Estado) {
        String Perfil = Perfil(Estado, 1);

        con.AgregarUsuario(nombre, Apellido, Usuario, Contrase, Fecha, Perfil);
    }
    
    public void CorteCaja(String Monto, String Usuario, String Password) {
        String idU = idUser(Usuario, Password);
        con.CorteCaja(Monto, idU);
    }

    public String Perfil(String Estado, int re) {
        String Perfil = "";
        if (re == 1) {
            switch (Estado) {
                case "Administrador":
                    Perfil = "A";
                    break;
                case "Normal":
                    Perfil = "N";
                    break;
                default:
                    Perfil = "E";
                    break;

            }
        } else {
            switch (Estado) {
                case "A":
                    Perfil = "Administrador";
                    break;
                case "N":
                    Perfil = "Normal";
                    break;
                default:
                    Perfil = "X";
                    break;

            }
        }
        return Perfil;
    }
    
}
