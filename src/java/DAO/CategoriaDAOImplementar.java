/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Categoria;
import java.util.List;
import Factory.FactoryConexionDB;
import Factory.ConexionDB;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDAOImplementar implements CategoriaDAO {

    ConexionDB conn;
    
    public CategoriaDAOImplementar() {
        //Definir a la db que se conectara por default
        
        
    }

    
    
    @Override
    public List<Categoria> Listar() {
        this.conn = FactoryConexionDB.open(FactoryConexionDB.MySQL);
        StringBuilder miSQL = new StringBuilder();
        miSQL.append("SELECT * FROM tb_categoria;");
        List<Categoria> lista = new ArrayList<Categoria>();
        try{
            //Se crea el objeto ResultSet omplemantando el metodo ConsultaSQL 
            ResultSet resultadoSQL = this.conn.consultaSQL(miSQL.toString());
            while(resultadoSQL.next()){
            Categoria categoria = new Categoria();//Declara el objeto categoria
            //Asigna a cada campo consultado al atributo de la clase
            categoria.setId_categoria(resultadoSQL.getInt("id_categoria"));
            categoria.setNom_categoria(resultadoSQL.getString("nom_categoria"));
            categoria.setEstado_categoria(resultadoSQL.getInt("estado_categoria"));
            lista.add(categoria);//Agrega al arreglo cada registro encontrado
            }
        }catch(Exception ex){
        this.conn.cerrarConexion();//pa cerrar conexion señores
        }
        return lista;
    }

    @Override
    public List<Categoria> Listar2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria editarCat(int id_cat_edit) {
        this.conn = FactoryConexionDB.open(FactoryConexionDB.MySQL);
        Categoria categoria = new Categoria();//Objeto categoria para devolver datos
        StringBuilder miSQL = new StringBuilder();//Construye la consulta
        //Agregar la consulta SQL
        miSQL.append("SELECT * FROM tb_categoria WHERE id_categoria =").append(id_cat_edit);
        try{// Realiza la consulta
            ResultSet resultadoSQL = this.conn.consultaSQL(miSQL.toString());
            while(resultadoSQL.next()){
            categoria.setId_categoria(resultadoSQL.getInt("id_categoria"));
            categoria.setNom_categoria(resultadoSQL.getString("nom_categoria"));
            categoria.setEstado_categoria(resultadoSQL.getInt("estado_categoria"));
            }       
        }catch(Exception ex){
              
        }finally{
        this.conn.cerrarConexion();//esto la cierra
        }
        return categoria;
        
    }

    @Override
    public boolean guardarCat(Categoria categoria) {
        this.conn = FactoryConexionDB.open(FactoryConexionDB.MySQL);
        boolean guarda = false; //bandera de resultado
        try{
            if (categoria.getId_categoria()== 0) {//Para cuando es una nueva categoria
            StringBuilder miSQL = new StringBuilder();    
            //Agregar consulta SQL, el id_categoria es autoincrement
            miSQL.append("INSERT INTO tb_categoria(nom_categoria, estado_categoria) VALUES ('");
            miSQL.append(categoria.getNom_categoria()+ "',").append(categoria.getEstado_categoria());
            miSQL.append(");");
            //Invoca al diablo perdon digo el metodo para ejecutar la consulta
            this.conn.ejecutarSQL(miSQL.toString());
            }else if (categoria.getId_categoria() > 0){//Actualizar, id_categoira mayores a 0
            }
        }catch(Exception e){
        }finally{
        this.conn.cerrarConexion();
        }
        return guarda;
    }

    @Override
    public boolean borrarCat(int id_cat_borrar) {
        this.conn = FactoryConexionDB.open(FactoryConexionDB.MySQL);
        boolean borra = false; //Bandera de resultado
        try {
            StringBuilder miSQL = new StringBuilder();
             miSQL.append("DELETE FROM tb_categoria WHERE id_categoria =").append(id_cat_borrar);
            //Invoca al diablo perdon digo el metodo para ejecutar la consulta
            this.conn.ejecutarSQL(miSQL.toString());
             borra = true;
        }catch(Exception e){
        
        }finally{
        this.conn.cerrarConexion();
        }
      return borra;
        
    
    }
    
}
