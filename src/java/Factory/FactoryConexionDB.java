/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;


public class FactoryConexionDB {
    public static final int MySQL = 1; //Para conectar Mynosque señores
    public static String[] configMySQL ={"bd_inventario","root",""};
    
    public static ConexionDB open (int tipoDB){
    switch(tipoDB){
        case FactoryConexionDB.MySQL:
        return new MySQLConexionFactory(configMySQL);
        default:
            return null;
    }
    }
}
