/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.ui;

import ac.cr.ucenfotec.Log;
import ac.cr.ucenfotec.cl.CapaLogica;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import javax.swing.JFrame;

/**
 *
 * @author Pardo
 */
public class Reproductor extends javax.swing.JFrame {

    private final JFXPanel jfxPanel = new JFXPanel();
    CapaLogica capaLogica;
    int videoActual;
    ArrayList<Integer> videos;
    File file;
    MediaPlayer oracleVid;
    int segundos = 0;
    Reproductor repro;

    /**
     * Creates new form Videos
     *
     * @param f
     * @param capaLogica
     * @param videoActual
     * @param videos
     */
    public Reproductor(File f, CapaLogica capaLogica, int videoActual, ArrayList<Integer> videos) {
        initComponents();
        //Añadimos el panel de JavaFX al JPanel Swing
        jfxPanel.setSize(jPanel1.getSize());
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(jfxPanel, BorderLayout.CENTER);
        play(f, capaLogica, videoActual, videos, segundos);

    }

    public Reproductor(File f, CapaLogica capaLogica, int videoActual, ArrayList<Integer> videos, int segundos) {
        initComponents();
        //Añadimos el panel de JavaFX al JPanel Swing
        jfxPanel.setSize(jPanel1.getSize());
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(jfxPanel, BorderLayout.CENTER);
        play(f, capaLogica, videoActual, videos, segundos);
    }

    void play(File f, CapaLogica capaLogica, int videoActual, ArrayList<Integer> videos, int segundos) {
        this.capaLogica = capaLogica;
        this.file = f;
        this.videoActual = videoActual;
        this.videos = videos;
        capaLogica.eliminarReproduccion(capaLogica.getUsuarioActual());
        this.setVisible(true);
        file = new File(capaLogica.getVideoRuta(videoActual + ""));
        try {
            oracleVid = new MediaPlayer(
                    new Media(file.toURI().toString())
            );
            jfxPanel.setScene(new Scene(new Group(new MediaView(oracleVid))));
            oracleVid.setVolume(0.7);//volumen
            oracleVid.play();//play video
            oracleVid.seek(new Duration(segundos * 1000));
            createScene();
        } catch (Exception ex) {
            setVisible(false);
            Platform.exit();
            Log.writeLog(ex.toString());
        }

    }

    /**
     * Creates new form JFrameVideo
     */
    private void createScene() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                oracleVid.currentTimeProperty().addListener((observableValue, oldDuration, newDuration) -> {
                    double tiempoRestante = oracleVid.getMedia().getDuration().toSeconds() - oracleVid.getCurrentTime().toSeconds();
                    double min = Math.floor(tiempoRestante / 60);
                    double sec = ((tiempoRestante / 60) - min) * 60;
                    String minuto = (min + "").contains(".") ? (min + "").substring(0, (min + "").indexOf(".")) : min + "";
                    String segundos = (sec + "").contains(".") ? (sec + "").substring(0, (sec + "").indexOf(".")) : sec + "";
                    jTextField1.setText(minuto + " : " + segundos);

                    if (tiempoRestante <= 30) {
                        int j = 0;
                        for (int i = 0; i < videos.size(); i++) {
                            if (videos.get(i) == videoActual) {
                                j = i;
                            }
                        }
                        j = (j == videos.size() - 1) ? 0 : j;
                        if (videos.size() > 0) {
                            int id = videos.get(j + 1);
                            jTextFieldSiguienteVideo.setText(capaLogica.getVideosId(id + "").get(0)[0]);
                        }
                        int agregado = (videos.isEmpty()) ? 0 : 30;
                        jSpinner1.setValue((int) tiempoRestante + agregado);
                        if (tiempoRestante <= 1) {
                            if (videos.isEmpty()) {
                                setVisible(false);
                                oracleVid.stop();
                                Platform.exit();
                            } else {
                                long t = System.currentTimeMillis();
                                long end = t + 30000;
                                while (System.currentTimeMillis() < end) {
                                    try {
                                        Thread.sleep(1000);
                                        jSpinner1.setValue(Integer.parseInt(jSpinner1.getValue() + "") - 1);
                                    } catch (InterruptedException ex) {

                                    }
                                }
                                setVisible(false);
                                oracleVid.stop();
                                Platform.exit();
                                int id = videos.get(j + 1);
                                play(file, capaLogica, id, videos, 0);
                                jTextFieldSiguienteVideo.setText("");
                                jSpinner1.setValue(0);
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonPlayPause = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextFieldSiguienteVideo = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jButtonPlayPause.setText(" Play");
        jButtonPlayPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlayPauseActionPerformed(evt);
            }
        });

        jButton2.setText("Stop");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 30, 1));

        jLabel1.setText("Siguiente video : ");

        jTextField1.setEditable(false);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setText("Restante : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButtonPlayPause)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldSiguienteVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(151, 151, 151))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPlayPause)
                    .addComponent(jButton2)
                    .addComponent(jTextFieldSiguienteVideo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPlayPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlayPauseActionPerformed
        if (jButtonPlayPause.getText().equals("Play")) {
            oracleVid.pause();
            jButtonPlayPause.setText("Pause");
        } else {
            oracleVid.play();
            jButtonPlayPause.setText("Play");
        }
    }//GEN-LAST:event_jButtonPlayPauseActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int valor = Integer.parseInt(jSpinner1.getValue() + "");
        if (valor == 0 || valor > 30) {
            double tiempo = oracleVid.getCurrentTime().toSeconds();
            String temp = ((tiempo + "")).contains(".") ? (tiempo + "").substring(0, (tiempo + "").indexOf(".")) : (tiempo + "");
            int te = Integer.parseInt(temp);
            capaLogica.agregarReproduccion(te, capaLogica.getUsuarioActual(), videoActual);
            for (Integer vide : videos) {
                if (vide != videoActual) {
                    capaLogica.agregarReproduccion(0, capaLogica.getUsuarioActual(), vide);
                }
            }
            this.setVisible(false);
            oracleVid.stop();
            Platform.exit();
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonPlayPause;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldSiguienteVideo;
    // End of variables declaration//GEN-END:variables
}
