/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import ejemplosql.CreacionDeTabla;
import java.sql.ResultSet;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author estudios
 */
public class FrameSecundario extends Thread {

    static ResultSet r2;
    public static boolean ejecutorSecundario = false;
    static DefaultTableModel tm;

    public FrameSecundario() {

        Timer timer = new Timer();
        timer.schedule(new Task(), 0, 250);
        this.start();

    }

    public static class Task extends TimerTask {

        @Override
        public void run() {

            if (ejecutorSecundario) {
                CreacionDeTabla.labelTabla.setText(CreacionDeTabla.nombreTabla.getText());
            }
        }
    }
}
