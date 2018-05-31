/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import ejemplosql.EjemploSQL;
import ejemplosql.ModificarDatos;
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
public class FrameModificar extends Thread {
    
    Object valorDelBox;
    public static boolean ejecutorMod = false;
    String repeticion = "";
    
    static ResultSet r2;
    
    static DefaultTableModel tm = new DefaultTableModel();
    
    public FrameModificar() {
        
        Timer timer = new Timer();
        timer.schedule(new Task(), 0, 250);
        this.start();
        
    }
    
    public class Task extends TimerTask {
        
        int conta2 = 0;
        int conta = 0;
        
        @Override
        public void run() {
            
            if (ejecutorMod) {
                
                try {
                    
                    tm = new DefaultTableModel();
                    //Pone los nombres de las columnas
                    Statement st = EjemploSQL.conn.createStatement();
                    r2 = st.executeQuery("select * from " + String.valueOf(ModificarDatos.boxMod.getSelectedItem()));
                    
                    for (int i = 0; i < r2.getMetaData().getColumnCount(); i++) {
                        
                        tm.addColumn(r2.getMetaData().getColumnName(i + 1));
                        
                    }
                    //pone el modelo
                    ModificarDatos.jTable1.setModel(tm);
                    
                    if (conta2 == 0 || ModificarDatos.boxMod.getSelectedItem() != valorDelBox) {
                        ModificarDatos.deleteBox.removeAllItems();
                        ModificarDatos.buscarBox.removeAllItems();
                        for (int i = 0; i < ModificarDatos.jTable1.getColumnCount(); i++) {
                            ModificarDatos.deleteBox.addItem(ModificarDatos.jTable1.getColumnName(i));
                            ModificarDatos.buscarBox.addItem(ModificarDatos.jTable1.getColumnName(i));
                            conta2++;
                        }
                        ModificarDatos.buscarBox.addItem("*");
                    }
                    while (r2.next()) {
                        
                        ArrayList<Object> lista = new ArrayList();
                        for (int j = 0; j < r2.getMetaData().getColumnCount(); j++) {
                            lista.add(r2.getString(j + 1));
                        }
                        tm.addRow(lista.toArray());
                        
                    }
                    conta = 0;
                    valorDelBox = ModificarDatos.boxMod.getSelectedItem();
//                    if(ModificarDatos.buscado==true){
//                    if(ModificarDatos.buscarBox.getSelectedItem()=="*"){
//                        ModificarDatos m= new ModificarDatos();
//                        m.buscarTxT.setEnabled(false);
//                    }else{
//                        ModificarDatos m= new ModificarDatos();
//                        m.buscarTxT.setEnabled(true);
//                        ModificarDatos.buscado=false;
//                    }
//                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                repeticion = (String) ModificarDatos.boxMod.getSelectedItem();
                tm = new DefaultTableModel();
                
            }
        }
        
    }
}
