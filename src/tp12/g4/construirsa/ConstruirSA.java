/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp12.g4.construirsa;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Marco
 */
public class ConstruirSA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        try{ 
        Class.forName("org.mariadb.jdbc.Driver");
        String URL=("jdbc:mariadb://localhost:3306/construirsa");
        String usuario = "root";
        String password = "";
        Connection con = DriverManager.getConnection(URL,usuario,password);
        
        //---------------------------------------------------------Agregar 3 empleados y 2 herramientas
        
            System.out.println("Cargando empleados");
        String sql="INSERT INTO empleado (dni,apellido,nombre,acceso,estado)"
                + "VALUES (108978,'Camisa','Juanes',3,true),"
                + "(125879,'Lorelei','Marisa',2,true),"
                + "(189745,'Holanda','Karen',3,true)";
        
            System.out.println("Cargando herramientas");
            
        String sqlh="INSERT INTO herramienta (nombre,descripcion,stock,estado)"
                + "VALUES ('martillo','martillo de goma', 12, true),"
                + "('taladradora','electrica',6, true)";        
        PreparedStatement ps=con.prepareStatement(sql);
        PreparedStatement ps2=con.prepareStatement(sqlh);
        
        int filas= ps.executeUpdate();
        int filas2=ps2.executeUpdate();
        
        if (filas>0){
            JOptionPane.showMessageDialog(null, "Empleado/s Agregado/s");
        }
          if (filas2>0){
            JOptionPane.showMessageDialog(null, "Herramienta/s Agregado/s");
        }
          
        // ----------------------------------------------------Listar herramientas con stock 10 o mas
        
        String stock1="SELECT * FROM `herramienta` WHERE stock > 10;";
        PreparedStatement ps3=con.prepareStatement(stock1);
        ResultSet rs = ps3.executeQuery();
        
        while (rs.next()){
            int id=rs.getInt("idHerramienta");
            String nombre=rs.getString("nombre");
            String descripcion=rs.getString("descripcion");
            int stock=rs.getInt("stock");
            boolean estado=rs.getBoolean("estado");
            System.out.println("| id : " + id + "| nombre de la herramienta: " + nombre +"| descripcion: "
                    + descripcion + " | stock: " + stock + "| estado: " + estado);
        }
        
        // ---------------------------------------------Dar de baja al primer empleado ingresado a la base de datos      
        
        String eliminarE = "UPDATE empleado SET estado = 0 WHERE idEmpleado = 2 ";
        
        PreparedStatement ps4 = con.prepareStatement(eliminarE);
        int fila4= ps4.executeUpdate();
        if (fila4>0){
            JOptionPane.showMessageDialog(null, "El empleado fue dado de baja exitosamente.");
        }

       }catch (ClassNotFoundException cn){
           JOptionPane.showMessageDialog(null,"Error en carga de Driver");
       } catch (SQLException ex){
           JOptionPane.showMessageDialog(null,"Error de conexión");
           ex.printStackTrace();
       }
        
       
        

        
    }
    
}
