/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplosql;

import Threads.FrameModificar;
import Threads.FramePrincipal;
import Threads.FrameSecundario;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estudios
 */
public class EjemploSQL {

    public static DatabaseMetaData meta;
    public static Connection conn;
    static ResultSet r;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            //Inicio Threads
            FramePrincipal t1 = new FramePrincipal();
            FrameSecundario t2 = new FrameSecundario();
            FrameModificar t3= new FrameModificar();
            //Instancia de los frame
            Tabla t = new Tabla();

            //conecxion a la base de datos
            String url = "jdbc:sqlite:C:\\Users\\estudios\\Desktop\\BaseDatosSQLite\\PrimeraBase.db";
            conn = DriverManager.getConnection(url);
            //Si se conecta
            if (conn != null) {
                EjemploSQL.meta = EjemploSQL.conn.getMetaData();
                //coger las tablas de la base
                EjemploSQL.r = meta.getTables(null, null, null, null);
                //rellenar el combobox con los nombres de las tablas
                while (r.next()) {

                    Tabla.tablaL.addItem(r.getString("TABLE_NAME"));

                    ModificarDatos.boxMod.addItem(r.getString("TABLE_NAME"));

                }
                //visualizar el frame principal
                t.setVisible(true);
                //Activar Thread del FramePrincipal
                FramePrincipal.ejecutorPrincipal = true;
            }

//            try {
//
////                String ejec = "select * from Persona";
//
////                if (conn != null) {
////                    System.out.println("Conectado");
////                    Statement st = conn.createStatement();
////                    ResultSet rs = st.executeQuery(ejec);
////
////                    while (rs.next()) {
////                        System.out.print(rs.getString(i));
////                        System.out.println(" " + rs.getString(3));
////
////                    }
////                    st.close();
////                    rs.close();
////                }
////            } catch (SQLException ex) {
////                Logger.getLogger(EjemploSQL.class.getName()).log(Level.SEVERE, null, ex);
////            }
//
        } catch (SQLException ex) {
            Logger.getLogger(EjemploSQL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
