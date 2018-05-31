/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import ejemplosql.EjemploSQL;
import ejemplosql.Tabla;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author estudios
 */
public class FramePrincipal extends Thread {

    public static boolean ejecutorPrincipal = false;
    String repeticion = "";

    static ResultSet r2;

    static DefaultTableModel tm = new DefaultTableModel();

    public FramePrincipal() {

        Timer timer = new Timer();
        timer.schedule(new Task(), 0, 250);
        this.start();

    }

    public class Task extends TimerTask {

        int conta = 0;

        @Override
        public void run() {

            if (ejecutorPrincipal) {

                try {
                    //Pone los nombres de las columnas
                    Statement st = EjemploSQL.conn.createStatement();
                    r2 = st.executeQuery("select * from " + String.valueOf(Tabla.tablaL.getSelectedItem()));

                    if (repeticion.equalsIgnoreCase((String) Tabla.tablaL.getSelectedItem())) {

                    } else {
                        for (int i = 0; i < r2.getMetaData().getColumnCount(); i++) {

                            tm.addColumn(r2.getMetaData().getColumnName(i + 1));

                        }
                        Tabla.tabla.setModel(tm);
                        while (r2.next()) {
                            conta++;

                            for (int i = 0; i < conta; i++) {
                                ArrayList<Object> lista = new ArrayList();
                                for (int j = 0; j < r2.getMetaData().getColumnCount(); j++) {
                                    lista.add(r2.getString(j + 1));
                                }
                                tm.addRow(lista.toArray());
                                lista=null;
                            }
                        }
                        tm=null;
                        conta = 0;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                repeticion = (String) Tabla.tablaL.getSelectedItem();
                tm = new DefaultTableModel();

            }
        }

    }
}
