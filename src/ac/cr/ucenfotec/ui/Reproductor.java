package ac.cr.ucenfotec.ui;

import ac.cr.ucenfotec.cl.CapaLogica;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.media.*;

class Player extends JFrame implements ActionListener, ControllerListener {
    //normal buttons

    JButton Play;
    JButton Fast_Forward;
    JButton Fast_Rewind;
    JButton Pause;
    JButton Stop;
    //normal slider
    JSlider Slider;
    JTextField tf;
    JPanel Panel_for_buttons = new JPanel();
    //panel will contain buttons and slider
    JPanel Panel_for_Slider = new JPanel();
    File f;
    javax.media.Player created_player = null;
    static float rate;
    //JButton Check;
    java.util.Timer timer;
    long Total_Time;
    long played_time;
    int slider_value;
    //we need to get some container as we cannot directly add panel to JFrame
    Container Content;
    int flag = 0;
    //static int flag_for_display_rate=0;
    Time tt;
    int flag_for_display_rate = 0;
    Time played_time_for_pause;
    double seconds = 0;
    int player_started = 0;
    long minutes_for_textfield;
    long seconds_for_textfield;
    static float forward_rate = 1;
    CapaLogica capaLogica;
    int videoActual;
    ArrayList<Integer> videos;

    public Player(File f, CapaLogica capaLogica, int videoActual, ArrayList<Integer> videos) {
        super("Prat's Player");
        Play = new JButton("►");
        Fast_Forward = new JButton("»");
        Fast_Rewind = new JButton("«");
        Pause = new JButton("ll");
        Stop = new JButton("■");
        Slider = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 0);
        tf = new JTextField(3);
        played_time_for_pause = new Time(capaLogica.getReproduccionTiempo(videoActual));
        this.capaLogica = capaLogica;
        this.f = f;
        this.videoActual = videoActual;
        this.videos = videos;
        capaLogica.eliminarReproduccion(capaLogica.getUsuarioActual());

    }

    public void player_gui() {
        //how buttons arranged
        Panel_for_buttons.setLayout(new FlowLayout());
        Panel_for_Slider.setLayout(new FlowLayout());
        Panel_for_Slider.add(Slider);
        //adding buttons to panel
        Panel_for_buttons.add(Play);
        Panel_for_buttons.add(Fast_Forward);
        Panel_for_buttons.add(Fast_Rewind);
        Panel_for_buttons.add(Pause);
        Panel_for_buttons.add(Stop);
        //Panel_for_buttons.add(Check);
        Panel_for_buttons.add(tf);
        //content pane is good option for our purpose. Others like glasspane
        Content = this.getContentPane();
        //Specifying layout here is necessary Appends the specified component to the end of this container.
        Content.add(Panel_for_buttons, BorderLayout.SOUTH);
        //slider is above buttons
        Content.add(Panel_for_Slider, BorderLayout.NORTH);
        Play.addActionListener(this);
        Stop.addActionListener(this);
        Fast_Forward.addActionListener(this);
        Pause.addActionListener(this);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public void displayrate() {
        long player_time = created_player.getMediaNanoseconds() / 1000000000;
        minutes_for_textfield = player_time / 60;
        seconds_for_textfield = player_time % 60;
        tf.setText(String.valueOf(minutes_for_textfield) + ":" + String.valueOf(seconds_for_textfield));
        Total_Time = (long) (created_player.getDuration().getSeconds());
        played_time = (long) (created_player.getMediaNanoseconds() / 1000000000);
        slider_value = (int) ((played_time * 100) / Total_Time);
        Slider.setValue(slider_value);
        if (played_time >= Total_Time) {
            int j = 0;
            for (int i = 0; i < videos.size() && j == 0; i++) {
                if (videos.get(i) == videoActual) {
                    j = i;
                }
            }
            j = (j == videos.size() - 1) ? 0 : j;
            int id = videos.get(j + 1);
            File file = new File(capaLogica.getVideoRuta(id + ""));
            Player player_obj = new Player(file, capaLogica, id, videos);
            player_obj.setVisible(true);
            player_obj.player_gui();
            player_obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            player_obj.setBackground(Color.pink);
            player_obj.setLocation(300, 300);
            player_obj.setSize(500, 100);
            this.dispose();
        }
    }

    public void Reminder() {
        timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new RemindTask(), 1000, 1);
    }

    class RemindTask extends java.util.TimerTask {

        public void run() {
            displayrate();
            timer.cancel();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Play) {
            if (player_started == 1) {
                created_player.setMediaTime(played_time_for_pause);
            } else {
                try {
                    created_player = Manager.createPlayer(f.getAbsoluteFile().toURI().toURL());
                    created_player.addControllerListener(this);
                    displayrate();
                } catch (Exception ex) {
                    System.err.println("Got exception " + ex);
                }
            }
            created_player.start();
            player_started = 1;
            if (flag_for_display_rate == 0) {
                Reminder();
                Reminder();
                flag_for_display_rate = 1;
            }
        }
        if (e.getSource() == Stop) {

            capaLogica.agregarReproduccion((int) created_player.getMediaTime().getSeconds(), capaLogica.getUsuarioActual(), videoActual);
            for (Integer vide : videos) {
                capaLogica.agregarReproduccion(0, capaLogica.getUsuarioActual(), vide);
            }
            created_player.stop();
            created_player.deallocate();
            played_time_for_pause = new Time(0.0);
            player_started = 0;
            this.dispose();
        }
        if (e.getSource() == Fast_Forward) {
            forward_rate += .5f;
            created_player.setRate(forward_rate);
        }
        if (e.getSource() == Pause) {
            played_time_for_pause = created_player.getMediaTime();
            created_player.stop();
        }
    }

    public synchronized void controllerUpdate(ControllerEvent event) {
        if (event instanceof RealizeCompleteEvent) {
            Component comp;
            if ((comp = created_player.getVisualComponent()) != null) {
                Content.add(comp, BorderLayout.CENTER, 1);
            }
            //resize window as per its components
            pack();
        }
    }

}
