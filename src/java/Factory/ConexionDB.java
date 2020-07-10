/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public abstract class ConexionDB {
    protected String[] parametros;//Arreglo para recibir toda la conexion
    protected Connection conexion;
    //El siguiente metodo abstracto no se implementa solo se declara
    //implementara en la subclase
    abstract Connection open();//Method abstracto que devuelve un objeto connetion

    public ResultSet consultaSQL (String consulta){
   Statement st;
   ResultSet rs = null;
   try{
     st = conexion.createStatement();
     rs = st.executeQuery(consulta);//pa ejecutar la consulta
    }catch (SQLException ex){
    ex.printStackTrace();
    }
   return rs;
  }
    
 public boolean ejecutarSQL(String consulta){
  Statement st;
  boolean guardar = true;
   try{
    st = conexion.createStatement();
    st.executeUpdate(consulta);
   }catch(SQLException ex){
    guardar = false;
    ex.printStackTrace();
   }
  return guardar;
 }   
 
  public boolean cerrarConexion(){
  boolean ok = true;
  try{
  conexion.close();
  }catch (Exception es){
   ok = false;
   es.printStackTrace();
  }
  return ok;
  }

    
}
