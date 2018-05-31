/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplosql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author estudios
 */
public class Metodos {

    int conta3 = 0;
    public static String sentencia, sentenciaF = "", valoresI = "", valoresD = "", valorBuscar = "";
    ResultSet r2;

    public void crearTabla() {
        try {

            PreparedStatement st = EjemploSQL.conn.prepareStatement("create table " + String.valueOf(CreacionDeTabla.nombreTabla.getText()) + "(" + sentenciaF + ",PRIMARY KEY(" + NuevoCampo.sentenciaPK + "));");
            st.execute();
            System.out.println("Tabla creada");
            st.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en los datos recibidos");
        }

    }

    public void insert() {
        try {

            PreparedStatement st = EjemploSQL.conn.prepareStatement("insert into " + String.valueOf(ModificarDatos.boxMod.getSelectedItem()) + " VALUES (" + valoresI + ");");
            st.execute();
            System.out.println("Ha insertado una fila");
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean delete() {
        try {

            PreparedStatement st = EjemploSQL.conn.prepareStatement("delete from " + String.valueOf(ModificarDatos.boxMod.getSelectedItem()) + " where " + ModificarDatos.deleteBox.getSelectedItem() + "='" + valoresD + "';");
            st.execute();
            System.out.println("Ha borrado una fila");

            st.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la ejecucion");
        }
        return true;
    }

    public void buscar() {

    
            Statement st;

            
                try {
                    //algo falla
                    
                    if (ModificarDatos.buscarBox.getSelectedItem() == "*") {
                        
                        st = EjemploSQL.conn.createStatement();
                        r2 = st.executeQuery("select " + "*" + " from " + ModificarDatos.boxMod.getSelectedItem() + ";");
                        while (r2.next()) {
                            while(r2.next())
                            conta3++;
                            for (int i = 0; i < conta3; i++) {
                                
                                System.out.println(r2.getString(i+1));
                                System.out.println(i);
                            }
                            conta3=0;
                        }
                    } else {
                        st = EjemploSQL.conn.createStatement();
                        r2 = st.executeQuery("select " + String.valueOf(ModificarDatos.buscarBox.getSelectedItem()) + " from " + ModificarDatos.boxMod.getSelectedItem()+";");// + " where " + ModificarDatos.buscarBox.getSelectedItem() + "='" + valorBuscar + "';");
                        while(r2.next()){
                        for (int i = 0; i < 1; i++) {
                            System.out.println(r2.getString(i+1));
                        }
                    }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
                }

    }
}

