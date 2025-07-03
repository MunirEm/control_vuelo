package control_vuelo;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Vuelo extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelconfiguracion;
    private JToggleButton tbonoff;
    private JButton bdestinos;
    private JList<String> listdestinos;
    private DefaultListModel<String> dlmdestinos;
    private JScrollPane sp;
    private JButton bvuelos;
    private JPanel panelalarmas;
    private static JLabel lresul;
    private JLabel ldestino;
    private JComboBox<String> cbdestinos;
    private JLabel lsalida;
    private JSpinner spsalida;
    private JLabel lllegada;
    private JSpinner spllegada;
    private JLabel lduracion;
    private JTextField tfduracion;
    private JTextField tftempint;
    private JLabel ltempint;
    private JLabel ltempext;
    private JTextField tftempext;
    private JLabel lcombustible;
    private JProgressBar pb;
    private JButton bcontroles;
    private static Connection cn;
    private static ResultSet rs, rsnumreg, rsdestino;
    private String[] destinosArray;
    private JPanel panelcontroles;
    private JLabel lvelocidad;
    private JSpinner spflaps;
    private JLabel lflats;
    private JLabel ltren;
    private JLabel lpiloto;
    private JButton bcabecera;
    private JLabel lslats;
    private JSlider slideraltitud;
    private JLabel laltitud;
    private JSlider slidervelocidad;
    private JButton bdespegar;
    private JButton bfinalizado;
    private JRadioButton rbretraidos;
    private JRadioButton rbextendidos;
    private JRadioButton rbabajo;
    private JRadioButton rbarriba;
    private JRadioButton rbactivado;
    private JRadioButton rbdesactivado;
    private JLabel lshowvelocidad;
    private JLabel lshowaltitud;
    private ButtonGroup bgslats, bgta, bgpa;
    private JTextArea ta;
    private JScrollPane spregistros;
    private JLabel ltierra;
    private JLabel laire;
    private JLabel laviontierra;
    private JLabel lavionaire;
    private JLabel lsemaforotierrra;
    private JLabel lsemaforoaire;
    private int xtierra = 735;
    private Timer timertierra = new Timer(150, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            laviontierra.setBounds(xtierra, 119, 59, 66);
            xtierra += 5;
            if (xtierra == 900) {
                timertierra.stop();
                xtierra = 735;
                lsemaforotierrra.setBackground(new Color(255, 0, 0));
                bcabecera.setEnabled(false);
                bdespegar.setEnabled(true);
                lresul.setText("Avión en cabecera de pista --> Listo para DESPEGAR");
            }
        }
    });
    private int xairevarna = 745, yairevarna = 390;
    private Timer timervarna = new Timer(250, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            lavionaire.setBounds(xairevarna, yairevarna, 59, 66);
            xairevarna += 5;
            yairevarna -= 1;
            if (xairevarna == 760) {
                rbarriba.setSelected(true);
                pb.setValue(70);
                slideraltitud.setValue(6000);
            } else if (xairevarna == 820) {
                pb.setValue(60);
                slideraltitud.setValue(10000);
            } else if (xairevarna == 840) {
                pb.setValue(50);
                slideraltitud.setValue(10000);
            } else if (xairevarna == 860) {
                pb.setValue(40);
                slideraltitud.setValue(6000);
            } else if (xairevarna == 880) {
                pb.setValue(30);
                slideraltitud.setValue(3000);
            } else if (xairevarna == 900) {
                timervarna.stop();
                xairevarna = 745;
                yairevarna = 390;
                lsemaforoaire.setBackground(new Color(255, 0, 0));
                bdespegar.setEnabled(false);
                lresul.setText("Avión en DESTINO --> PULSE EN FINALIZADO");
                bfinalizado.setEnabled(true);
                rbabajo.setSelected(true);
                pb.setValue(20);
                slideraltitud.setValue(0);
            }
        }
    });
    private int xairedubro = 745, yairedubro = 390;
    private Timer timerdubro = new Timer(250, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            lavionaire.setBounds(xairedubro, yairedubro, 59, 66);
            xairedubro += 5;
            yairedubro -= 2;
            if (xairedubro == 760) {
                rbarriba.setSelected(true);
                pb.setValue(50);
                slideraltitud.setValue(6000);
            } else if (xairedubro == 790) {
                pb.setValue(40);
                slideraltitud.setValue(10000);
            } else if (xairedubro == 820) {
                pb.setValue(30);
                slideraltitud.setValue(5000);
            } else if (xairedubro == 850) {
                timerdubro.stop();
                xairedubro = 745;
                yairedubro = 390;
                lsemaforoaire.setBackground(new Color(255, 0, 0));
                bdespegar.setEnabled(false);
                lresul.setText("Avión en DESTINO --> PULSE EN FINALIZADO");
                bfinalizado.setEnabled(true);
                rbabajo.setSelected(true);
                pb.setValue(20);
                slideraltitud.setValue(0);
            }
        }
    });
    private int xairenarva = 745, yairenarva = 390;
    private Timer timernarva = new Timer(250, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            lavionaire.setBounds(xairenarva, yairenarva, 59, 66);
            xairenarva += 5;
            yairenarva -= 4;
            if (xairenarva == 760) {
                rbarriba.setSelected(true);
                pb.setValue(80);
                slideraltitud.setValue(6000);
            } else if (xairenarva == 820) {
                pb.setValue(60);
                slideraltitud.setValue(10000);
            } else if (xairenarva == 840) {
                pb.setValue(50);
                slideraltitud.setValue(10000);
            } else if (xairenarva == 860) {
                pb.setValue(40);
                slideraltitud.setValue(6000);
            } else if (xairenarva == 880) {
                pb.setValue(30);
                slideraltitud.setValue(3000);
            } else if (xairenarva == 910) {
                timernarva.stop();
                xairenarva = 745;
                yairenarva = 390;
                lsemaforoaire.setBackground(new Color(255, 0, 0));
                bdespegar.setEnabled(false);
                lresul.setText("Avión en DESTINO --> PULSE EN FINALIZADO");
                bfinalizado.setEnabled(true);
                rbabajo.setSelected(true);
                pb.setValue(20);
                slideraltitud.setValue(0);
            }
        }
    });
    private int xaireduzce = 745, yaireduzce = 390;
    private Timer timerduzce = new Timer(250, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            lavionaire.setBounds(xaireduzce, yaireduzce, 59, 66);
            xaireduzce += 5;
            if (xaireduzce == 760) {
                rbarriba.setSelected(true);
                pb.setValue(80);
                slideraltitud.setValue(6000);
            } else if (xaireduzce == 820) {
                pb.setValue(60);
                slideraltitud.setValue(10000);
            } else if (xaireduzce == 840) {
                pb.setValue(50);
                slideraltitud.setValue(10000);
            } else if (xaireduzce == 860) {
                pb.setValue(40);
                slideraltitud.setValue(6000);
            } else if (xaireduzce == 880) {
                pb.setValue(30);
                slideraltitud.setValue(3000);
            } else if (xaireduzce == 930) {
                timerduzce.stop();
                xaireduzce = 745;
                yaireduzce = 390;
                lsemaforoaire.setBackground(new Color(255, 0, 0));
                bdespegar.setEnabled(false);
                lresul.setText("Avión en DESTINO --> PULSE EN FINALIZADO");
                bfinalizado.setEnabled(true);
                rbabajo.setSelected(true);
                pb.setValue(20);
                slideraltitud.setValue(0);
            }
        }
    });
    private int xairepalermo = 745, yairepalermo = 390;
    private Timer timerpalermo = new Timer(250, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            lavionaire.setBounds(xairepalermo, yairepalermo, 59, 66);
            xairepalermo += 5;
            if (xairepalermo == 760) {
                rbarriba.setSelected(true);
                pb.setValue(35);
                slideraltitud.setValue(6000);
            } else if (xairepalermo == 780) {
                pb.setValue(30);
                slideraltitud.setValue(10000);
            } else if (xairepalermo == 800) {
                pb.setValue(25);
                slideraltitud.setValue(5000);
            } else if (xairepalermo == 835) {
                timerpalermo.stop();
                xairepalermo = 745;
                yairepalermo = 390;
                lsemaforoaire.setBackground(new Color(255, 0, 0));
                bdespegar.setEnabled(false);
                lresul.setText("Avión en DESTINO --> PULSE EN FINALIZADO");
                bfinalizado.setEnabled(true);
                rbabajo.setSelected(true);
                pb.setValue(20);
                slideraltitud.setValue(0);
            }
        }
    });
    private static PreparedStatement pstmt;

    public static void conectarOrigen() {
        try {
            cn = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Mun8ir\\eclipse-workspace\\control_vuelo\\src\\main\\resources\\Origen.accdb");
        } catch (SQLException e) {
            lresul.setText("Error al abrir la base de datos Origen");
        }
    }

    public static void conectarDestino() {
        try {
            cn = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Mun8ir\\eclipse-workspace\\control_vuelo\\src\\main\\resources\\Destino.accdb");
        } catch (SQLException e) {
            lresul.setText("Error al abrir la base de datos Destino");
        }
    }

    private void cargarDestinos() {
        try {
            lresul.setText("");
            conectarOrigen();
            rs = cn.createStatement().executeQuery("select Destino from Origen");
            rsnumreg = cn.createStatement().executeQuery("select count(*) from Origen");
            int totalRegistros = 0;
            while (rsnumreg.next()) {
                totalRegistros = rsnumreg.getInt(1);
            }
            destinosArray = new String[totalRegistros];
            if (!rs.isBeforeFirst()) {
                lresul.setText("No existe ningún destino en la base de datos");
            } else {
                for (int i = 0; i < destinosArray.length; i++) {
                    rs.next();
                    destinosArray[i] = rs.getString("Destino");
                }
                dlmdestinos = new DefaultListModel<String>();
                dlmdestinos.addAll(Arrays.asList(destinosArray));
                listdestinos.setModel(dlmdestinos);
                lresul.setText("Destinos cargados y listos para ser seleccionados");
            }
        } catch (SQLException e) {
            lresul.setText("Error SQL en la base de datos");
        } finally {
            try {
                cn.close();
                rs.close();
                rsnumreg.close();
            } catch (SQLException e) {
                lresul.setText("Error al cerrar la base de datos");
            }
        }
    }

    private void buscarDestino() {
        try {
            lresul.setText("");
            conectarOrigen();
            String consulta = "SELECT Destino, [Temperatura interior], [Temperatura exterior], Combustible FROM Origen WHERE Destino = ?";
            PreparedStatement ps = cn.prepareStatement(consulta);
            ps.setString(1, listdestinos.getSelectedValue());
            rsdestino = ps.executeQuery();

            if (rsdestino.next()) {
                cbdestinos.addItem(rsdestino.getString("Destino"));
                tftempint.setText(String.valueOf(rsdestino.getInt("Temperatura interior")));
                tftempext.setText(String.valueOf(rsdestino.getInt("Temperatura exterior")));
                pb.setValue(rsdestino.getInt("Combustible"));
                lresul.setText("Activado panel de CONFIGURACIÓN con Destino --> " 
                    + listdestinos.getSelectedValue().toUpperCase());
            }
        } catch (SQLException e) {
            lresul.setText("Error SQL en la base de datos");
        } finally {
            try {
                cn.close();
                rsdestino.close();
            } catch (SQLException e) {
                lresul.setText("Error al cerrar la base de datos");
            }
        }
    }

    private void updateControles() {
        try {
            conectarOrigen();
            String sql = "UPDATE Origen SET [Hora de salida] = ?, [Hora de llegada] = ?, [Duración del vuelo] = ? WHERE Destino = ?";
            pstmt = cn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(spsalida.getValue().toString()));
            pstmt.setInt(2, Integer.parseInt(spllegada.getValue().toString()));
            pstmt.setInt(3, Integer.parseInt(tfduracion.getText().toString()));
            pstmt.setString(4, cbdestinos.getSelectedItem().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            lresul.setText("Error SQL en la base de datos");
        } finally {
            try {
                cn.close();
                pstmt.close();
            } catch (SQLException e) {
                lresul.setText("Error de cierre de la base de datos");
            }
        }
    }

    private void insertarCabecera() {
        try {
            conectarDestino();
            String sql = "INSERT INTO Destino(Destino,Modo,Estado,Velocidad,Altitud,[Tren de aterrizaje],[Piloto automático],Slats,Flaps,Fecha) VALUES (?,?,?,?,?,?,?,?,?,?)";
            pstmt = cn.prepareStatement(sql);
            java.util.Date fechaUtil = new Date();
            java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
            boolean tren = false;
            if (rbabajo.isSelected()) {
                tren = true;
            }
            boolean piloto = false;
            if (rbactivado.isSelected()) {
                piloto = true;
            }
            boolean slats = false;
            if (rbextendidos.isSelected()) {
                slats = true;
            }
            pstmt.setString(1, cbdestinos.getSelectedItem().toString());
            pstmt.setString(2, "Cabecera");
            pstmt.setString(3, "En tránsito");
            pstmt.setInt(4, slidervelocidad.getValue());
            pstmt.setInt(5, slideraltitud.getValue());
            pstmt.setBoolean(6, tren);
            pstmt.setBoolean(7, piloto);
            pstmt.setBoolean(8, slats);
            pstmt.setInt(9, Integer.parseInt(spflaps.getValue().toString()));
            pstmt.setDate(10, fechaSql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            lresul.setText("Error SQL en la base de datos");
        } finally {
            try {
                pstmt.close();
                cn.close();
            } catch (Exception e2) {
                lresul.setText("Error en el cierre de la base de datos");
            }
        }
    }

    private void insertarDespegar() {
        try {
            conectarDestino();
            String sql = "INSERT INTO Destino(Destino,Modo,Estado,Velocidad,Altitud,[Tren de aterrizaje],[Piloto automático],Slats,Flaps,Fecha) VALUES (?,?,?,?,?,?,?,?,?,?)";
            pstmt = cn.prepareStatement(sql);
            java.util.Date fechaUtil = new Date();
            java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
            boolean tren = false;
            if (rbabajo.isSelected()) {
                tren = true;
            }
            boolean piloto = false;
            if (rbactivado.isSelected()) {
                piloto = true;
            }
            boolean slats = false;
            if (rbextendidos.isSelected()) {
                slats = true;
            }
            pstmt.setString(1, cbdestinos.getSelectedItem().toString());
            pstmt.setString(2, "Despegar");
            pstmt.setString(3, "En tránsito");
            pstmt.setInt(4, slidervelocidad.getValue());
            pstmt.setInt(5, slideraltitud.getValue());
            pstmt.setBoolean(6, tren);
            pstmt.setBoolean(7, piloto);
            pstmt.setBoolean(8, slats);
            pstmt.setInt(9, Integer.parseInt(spflaps.getValue().toString()));
            pstmt.setDate(10, fechaSql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            lresul.setText("Error SQL en la base de datos");
        } finally {
            try {
                pstmt.close();
                cn.close();
            } catch (Exception e2) {
                lresul.setText("Error en el cierre de la base de datos");
            }
        }
    }

    private void updateFinalizado() {
        try {
            conectarDestino();
            String sql = "UPDATE Destino SET Estado = ? WHERE Destino = ?";
            pstmt = cn.prepareStatement(sql);
            pstmt.setString(1, "Finalizado");
            pstmt.setString(2, cbdestinos.getSelectedItem().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            lresul.setText("Error SQL en la base de datos");
        } finally {
            try {
                cn.close();
                pstmt.close();
            } catch (SQLException e) {
                lresul.setText("Error de cierre de la base de datos");
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
                    Vuelo frame = new Vuelo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Vuelo() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Vuelo.class.getResource("/multimedia/avion_tierra.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setTitle("Flight cabin");
        setResizable(false);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lavionaire = new JLabel("");
        lavionaire.setIcon(new ImageIcon(Vuelo.class.getResource("/multimedia/avion_aire.png")));
        lavionaire.setBounds(744, 390, 50, 46);
        contentPane.add(lavionaire);
        
        laviontierra = new JLabel("");
        laviontierra.setIcon(new ImageIcon(Vuelo.class.getResource("/multimedia/avion_tierra.png")));
        laviontierra.setBounds(734, 119, 59, 66);
        contentPane.add(laviontierra);
        
        ta = new JTextArea();
        ta.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "<html><b>Registro de vuelo<b></html>", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 128)));
        spregistros = new JScrollPane(ta);
        spregistros.setBounds(725, 454, 249, 196);
        contentPane.add(spregistros);
        
        tbonoff = new JToggleButton("ON");
        tbonoff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int seleccion = JOptionPane.showOptionDialog(null, "Panel de cabina", "Control cabina",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
                    new Object[] { "Encender" }, "Encender");
                if (seleccion == 0) {
                    tbonoff.setBackground(new Color(0, 0, 255));
                    tbonoff.setForeground(new Color(255, 255, 255));
                    tbonoff.setText("ON");
                    ta.setText(null);
                    bdestinos.setEnabled(true);
                    lresul.setText("Consola de vuelo encendida");
                    java.util.Date fechaUtil = new Date();
                    java.sql.Timestamp fecha = new java.sql.Timestamp(fechaUtil.getTime());
                    ta.append("[" + fecha + "]\n" + "Consola iniciada...\n");
                }
            }
        });
        tbonoff.setOpaque(true);
        tbonoff.setBackground(new Color(230, 230, 250));
        tbonoff.setForeground(new Color(0, 0, 0));
        tbonoff.setFont(new Font("Tahoma", Font.BOLD, 20));
        tbonoff.setBounds(10, 6, 116, 46);
        contentPane.add(tbonoff);
        
        bdestinos = new JButton("1\u00BA CARGAR DESTINOS");
        bdestinos.setIcon(new ImageIcon(Vuelo.class.getResource("/multimedia/sql.png")));
        bdestinos.setForeground(new Color(0, 0, 255));
        bdestinos.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        bdestinos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listdestinos.setEnabled(true);
                bvuelos.setEnabled(true);
                cargarDestinos();
                java.util.Date fechaUtil = new Date();
                java.sql.Timestamp fecha = new java.sql.Timestamp(fechaUtil.getTime());
                ta.append("[" + fecha + "]\n" + "Destinos cargados...\n");
            }
        });
        bdestinos.setEnabled(false);
        bdestinos.setFont(new Font("Tahoma", Font.BOLD, 14));
        bdestinos.setBounds(136, 18, 213, 34);
        contentPane.add(bdestinos);
        
        listdestinos = new JList<String>();
        listdestinos.setFont(new Font("Tahoma", Font.BOLD, 12));
        listdestinos.setForeground(new Color(0, 0, 255));
        listdestinos.setEnabled(false);
        listdestinos.setBorder(new LineBorder(new Color(0, 0, 0)));
        sp = new JScrollPane(listdestinos);
        sp.setBounds(364, 6, 139, 56);
        contentPane.add(sp);
        
        bvuelos = new JButton("2\u00BA CARGAR VUELO");
        bvuelos.setIcon(new ImageIcon(Vuelo.class.getResource("/multimedia/sql.png")));
        bvuelos.setForeground(new Color(0, 0, 255));
        bvuelos.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        bvuelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listdestinos.getSelectedValue() == null) {
                    lresul.setText("Debe seleccionar un Destino de la lista superior");
                } else {
                    cbdestinos.setEnabled(true);
                    spsalida.setEnabled(true);
                    spllegada.setEnabled(true);
                    bcontroles.setEnabled(true);
                    bdestinos.setEnabled(false);
                    buscarDestino();
                    java.util.Date fechaUtil = new Date();
                    java.sql.Timestamp fecha = new java.sql.Timestamp(fechaUtil.getTime());
                    ta.append("[" + fecha + "]\n" + "Destino seleccionado: " + cbdestinos.getSelectedItem() + "\n");
                }
            }
        });
        bvuelos.setEnabled(false);
        bvuelos.setFont(new Font("Tahoma", Font.BOLD, 14));
        bvuelos.setBounds(513, 18, 202, 34);
        contentPane.add(bvuelos);
        
        panelalarmas = new JPanel();
        panelalarmas.setLayout(null);
        panelalarmas.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 1, true), "<html><b>Alarmas</b></html>", TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
        panelalarmas.setBackground(new Color(230, 230, 250));
        panelalarmas.setBounds(10, 576, 705, 74);
        contentPane.add(panelalarmas);
        
        lresul = new JLabel("Bienvenidos al panel de control de vuelo del IES \u00CDtaca");
        lresul.setForeground(new Color(128, 0, 0));
        lresul.setHorizontalAlignment(SwingConstants.CENTER);
        lresul.setFont(new Font("Tahoma", Font.BOLD, 14));
        lresul.setBounds(10, 11, 685, 40);
        panelalarmas.add(lresul);
        
        panelcontroles = new JPanel();
        panelcontroles.setLayout(null);
        panelcontroles.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 1, true), "<html><b>Controles</b></html>", TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
        panelcontroles.setBackground(new Color(230, 230, 250));
        panelcontroles.setBounds(10, 257, 705, 308);
        contentPane.add(panelcontroles);
        
        lvelocidad = new JLabel("<html><center>\r\nV<br>\r\ne<br>\r\nl<br>\r\no<br>\r\nc<br>\r\ni<br>\r\nd<br>\r\na<br>\r\nd<br>\r\n(Kmh)</center></html>");
        lvelocidad.setHorizontalAlignment(SwingConstants.CENTER);
        lvelocidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lvelocidad.setBounds(10, 84, 38, 176);
        panelcontroles.add(lvelocidad);
        
        spflaps = new JSpinner();
        spflaps.setForeground(new Color(0, 0, 255));
        spflaps.setFont(new Font("Tahoma", Font.BOLD, 16));
        spflaps.setEnabled(false);
        spflaps.setBounds(320, 190, 64, 38);
        panelcontroles.add(spflaps);
        
        lflats = new JLabel("\u00C1ngulo Flaps");
        lflats.setHorizontalAlignment(SwingConstants.CENTER);
        lflats.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lflats.setBounds(301, 154, 103, 25);
        panelcontroles.add(lflats);
        
        ltren = new JLabel("Tren de aterrizaje");
        ltren.setHorizontalAlignment(SwingConstants.CENTER);
        ltren.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ltren.setBounds(394, 20, 138, 25);
        panelcontroles.add(ltren);
        
        lpiloto = new JLabel("Piloto autom\u00E1tico");
        lpiloto.setHorizontalAlignment(SwingConstants.CENTER);
        lpiloto.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lpiloto.setBounds(548, 20, 147, 25);
        panelcontroles.add(lpiloto);
        
        bcabecera = new JButton("<html><center>4\u00BA CABECERA<br>DE PISTA</center></html>");
        bcabecera.setIcon(new ImageIcon(Vuelo.class.getResource("/multimedia/sql.png")));
        bcabecera.setForeground(new Color(0, 0, 255));
        bcabecera.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        bcabecera.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (slidervelocidad.getValue() == 0) {
                    lresul.setText("La velocidad no puede ser 0 kmh");
                } else if (slidervelocidad.getValue() > 51) {
                    lresul.setText("La velocidad debe estar entre 1 y 50 kmh");
                } else if (Integer.parseInt(spflaps.getValue().toString()) < 5 
                        || Integer.parseInt(spflaps.getValue().toString()) > 15) {
                    lresul.setText("El ángulo de los Flaps debe estar entre 5 y 15 grados");
                } else if (bgslats.getSelection() == null) {
                    lresul.setText("Debe seleccionar un valor para los SLATS");
                } else {
                    lsemaforotierrra.setBackground(new Color(0, 255, 0));
                    timertierra.start();
                    insertarCabecera();
                    bcontroles.setEnabled(false);
                    java.util.Date fechaUtil = new Date();
                    java.sql.Timestamp fecha = new java.sql.Timestamp(fechaUtil.getTime());
                    ta.append("[" + fecha + "]\n" + "Cabecera de pista iniciada...\n");
                    lresul.setText("Configuración modificada --> Avión en MOVIMIENTO hacia CABECERA DE PISTA");
                }
            }
        });
        bcabecera.setFont(new Font("Tahoma", Font.BOLD, 14));
        bcabecera.setEnabled(false);
        bcabecera.setBounds(493, 141, 158, 43);
        panelcontroles.add(bcabecera);
        
        lslats = new JLabel("Slats");
        lslats.setHorizontalAlignment(SwingConstants.CENTER);
        lslats.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lslats.setBounds(271, 20, 103, 25);
        panelcontroles.add(lslats);
        
        lshowvelocidad = new JLabel("0 kmh");
        lshowvelocidad.setForeground(new Color(0, 0, 255));
        lshowvelocidad.setFont(new Font("Tahoma", Font.BOLD, 14));
        lshowvelocidad.setHorizontalAlignment(SwingConstants.CENTER);
        lshowvelocidad.setBounds(41, 5, 95, 25);
        panelcontroles.add(lshowvelocidad);
        
        lshowaltitud = new JLabel("0 m");
        lshowaltitud.setHorizontalAlignment(SwingConstants.CENTER);
        lshowaltitud.setForeground(Color.BLUE);
        lshowaltitud.setFont(new Font("Tahoma", Font.BOLD, 14));
        lshowaltitud.setBounds(181, 5, 90, 25);
        panelcontroles.add(lshowaltitud);
        
        slidervelocidad = new JSlider();
        slidervelocidad.setEnabled(false);
        slidervelocidad.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                String velocidad = Integer.toString(slidervelocidad.getValue());
                lshowvelocidad.setText(velocidad + " kmh");
            }
        });
        slidervelocidad.setBorder(new LineBorder(new Color(0, 0, 0)));
        slidervelocidad.setBackground(new Color(255, 255, 224));
        slidervelocidad.setMajorTickSpacing(100);
        slidervelocidad.setMinorTickSpacing(50);
        slidervelocidad.setMaximum(900);
        slidervelocidad.setValue(0);
        slidervelocidad.setPaintTicks(true);
        slidervelocidad.setOrientation(SwingConstants.VERTICAL);
        slidervelocidad.setPaintLabels(true);
        slidervelocidad.setBounds(58, 37, 56, 254);
        panelcontroles.add(slidervelocidad);
        
        slideraltitud = new JSlider();
        slideraltitud.setEnabled(false);
        slideraltitud.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                String altitud = Integer.toString(slideraltitud.getValue());
                lshowaltitud.setText(altitud + " m");
            }
        });
        slideraltitud.setValue(0);
        slideraltitud.setPaintTicks(true);
        slideraltitud.setPaintLabels(true);
        slideraltitud.setOrientation(SwingConstants.VERTICAL);
        slideraltitud.setMinorTickSpacing(500);
        slideraltitud.setMaximum(10000);
        slideraltitud.setMajorTickSpacing(1000);
        slideraltitud.setBorder(new LineBorder(new Color(0, 0, 0)));
        slideraltitud.setBackground(new Color(255, 255, 224));
        slideraltitud.setBounds(186, 36, 70, 255);
        panelcontroles.add(slideraltitud);
        laltitud = new JLabel("<html><center>A<br>l<br>t<br>i<br>t<br>u<br>d<br>(m)</center></html>");
        laltitud.setHorizontalAlignment(SwingConstants.CENTER);
        laltitud.setFont(new Font("Tahoma", Font.PLAIN, 14));
        laltitud.setBounds(138, 82, 38, 163);
        panelcontroles.add(laltitud);

        bdespegar = new JButton("5º DESPEGAR");
        bdespegar.setIcon(new ImageIcon(Vuelo.class.getResource("/multimedia/sql.png")));
        bdespegar.setForeground(new Color(0, 0, 255));
        bdespegar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        bdespegar.setFont(new Font("Tahoma", Font.BOLD, 14));
        bdespegar.setEnabled(false);
        bdespegar.setBounds(493, 194, 158, 43);
        panelcontroles.add(bdespegar);

        bdespegar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rbactivado.setEnabled(true);
                rbdesactivado.setEnabled(true);
                if (slidervelocidad.getValue() < 251) {
                    lresul.setText("La velocidad de DESPEGUE debe ser superior a 250 kmh");
                } else if (slideraltitud.getValue() > 3000) {
                    lresul.setText("Altitud mínima de 3.000 metros");
                } else if (bgpa.getSelection() == null) {
                    lresul.setText("Debe seleccionar un valor para el PILOTO AUTOMÁTICO");
                } else {
                    lsemaforoaire.setBackground(new Color(0, 255, 0));
                    String destino = (String) cbdestinos.getSelectedItem();
                    switch (destino) {
                        case "Varna": timervarna.start(); break;
                        case "Dubrovnik": timerdubro.start(); break;
                        case "Narva": timernarva.start(); break;
                        case "Düzce": timerduzce.start(); break;
                        case "Palermo": timerpalermo.start(); break;
                    }
                    insertarDespegar();
                    java.util.Date fechaUtil = new Date();
                    java.sql.Timestamp fecha = new java.sql.Timestamp(fechaUtil.getTime());
                    ta.append("[" + fecha + "]\nDespegue-Ruta iniciada...\n");
                    lresul.setText("Configuración modificada - Avión en fase de DESPEGUE-RUTA");
                }
            }
        });
        bfinalizado = new JButton("6º FINALIZADO");
        bfinalizado.setIcon(new ImageIcon(Vuelo.class.getResource("/multimedia/sql.png")));
        bfinalizado.setForeground(new Color(0, 0, 255));
        bfinalizado.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        bfinalizado.setFont(new Font("Tahoma", Font.BOLD, 14));
        bfinalizado.setEnabled(false);
        bfinalizado.setBounds(493, 248, 158, 43);
        panelcontroles.add(bfinalizado);

        bfinalizado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateFinalizado();
                tbonoff.setBackground(new Color(230, 230, 255));
                tbonoff.setForeground(new Color(0, 0, 0));
                tbonoff.setText("ON / OFF");
                lresul.setText("Consola de vuelo APAGADA");

                bcontroles.setEnabled(false);
                bfinalizado.setEnabled(false);
                pb.setValue(0);
                tfduracion.setText(null);
                spsalida.setValue(1);
                spllegada.setValue(1);
                cbdestinos.removeAllItems();
                tftempint.setText(null);
                tftempext.setText(null);
                slidervelocidad.setValue(0);
                slideraltitud.setValue(0);
                bgslats.clearSelection();
                rbabajo.setSelected(true);
                rbdesactivado.setSelected(true);
                rbactivado.setEnabled(false);
                rbdesactivado.setEnabled(false);
                spflaps.setValue(0);
                dlmdestinos.clear();

                java.sql.Timestamp fecha = new java.sql.Timestamp(new Date().getTime());
                ta.append("[" + fecha + "]\nVuelo finalizado\n");
                laviontierra.setBounds(734, 119, 59, 66);
                lavionaire.setBounds(744, 390, 50, 46);
            }
        });

        // Grupo de Slats
        bgslats = new ButtonGroup();
        rbretraidos = new JRadioButton("Retraídos");
        rbretraidos.setEnabled(false);
        rbretraidos.setBackground(new Color(230, 230, 250));
        rbretraidos.setBounds(283, 52, 95, 23);
        bgslats.add(rbretraidos);
        panelcontroles.add(rbretraidos);

        rbextendidos = new JRadioButton("Extendidos");
        rbextendidos.setEnabled(false);
        rbextendidos.setBackground(new Color(230, 230, 250));
        rbextendidos.setBounds(283, 78, 95, 23);
        bgslats.add(rbextendidos);
        panelcontroles.add(rbextendidos);
     // Grupo Tren de Aterrizaje
        bgta = new ButtonGroup();
        rbabajo = new JRadioButton("Abajo");
        rbabajo.setEnabled(false);
        rbabajo.setSelected(true);
        rbabajo.setBackground(new Color(230, 230, 250));
        rbabajo.setBounds(435, 52, 65, 23);
        bgta.add(rbabajo);
        panelcontroles.add(rbabajo);

        rbarriba = new JRadioButton("Arriba");
        rbarriba.setEnabled(false);
        rbarriba.setBackground(new Color(230, 230, 250));
        rbarriba.setBounds(435, 78, 65, 23);
        bgta.add(rbarriba);
        panelcontroles.add(rbarriba);

        // Grupo Piloto Automático
        bgpa = new ButtonGroup();
        rbactivado = new JRadioButton("Activado");
        rbactivado.setEnabled(false);
        rbactivado.setBackground(new Color(230, 230, 250));
        rbactivado.setBounds(571, 52, 96, 23);
        bgpa.add(rbactivado);
        panelcontroles.add(rbactivado);

        rbdesactivado = new JRadioButton("Desactivado");
        rbdesactivado.setEnabled(false);
        rbdesactivado.setSelected(true);
        rbdesactivado.setBackground(new Color(230, 230, 250));
        rbdesactivado.setBounds(571, 78, 96, 23);
        bgpa.add(rbdesactivado);
        panelcontroles.add(rbdesactivado);

        // Panel Configuración final
        panelconfiguracion = new JPanel();
        panelconfiguracion.setBackground(new Color(230, 230, 250));
        panelconfiguracion.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 1, true),
                "<html><b>Configuración</b></html>", TitledBorder.CENTER,
                TitledBorder.BOTTOM, null, new Color(0, 0, 0)));
        panelconfiguracion.setBounds(10, 70, 705, 176);
        contentPane.add(panelconfiguracion);
        panelconfiguracion.setLayout(null);

        // Semáforos e imágenes de pista y aire
        laire = new JLabel();
        laire.setIcon(new ImageIcon(Vuelo.class.getResource("/multimedia/aire.png")));
        laire.setOpaque(true);
        laire.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        laire.setBounds(725, 268, 249, 169);
        contentPane.add(laire);

        ltierra = new JLabel();
        ltierra.setIcon(new ImageIcon(Vuelo.class.getResource("/multimedia/pista.png")));
        ltierra.setOpaque(true);
        ltierra.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        ltierra.setBounds(725, 70, 249, 169);
        contentPane.add(ltierra);

        lsemaforotierrra = new JLabel();
        lsemaforotierrra.setOpaque(true);
        lsemaforotierrra.setBackground(Color.RED);
        lsemaforotierrra.setBorder(new LineBorder(new Color(0, 0, 0)));
        lsemaforotierrra.setBounds(845, 54, 23, 14);
        contentPane.add(lsemaforotierrra);

        lsemaforoaire = new JLabel();
        lsemaforoaire.setOpaque(true);
        lsemaforoaire.setBackground(Color.RED);
        lsemaforoaire.setBorder(new LineBorder(new Color(0, 0, 0)));
        lsemaforoaire.setBounds(845, 252, 23, 14);
        contentPane.add(lsemaforoaire);
     // Label "Destino"
        ldestino = new JLabel("Destino");
        ldestino.setHorizontalAlignment(SwingConstants.CENTER);
        ldestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ldestino.setBounds(10, 11, 68, 25);
        panelconfiguracion.add(ldestino);

        // ComboBox de destinos
        cbdestinos = new JComboBox<>();
        cbdestinos.setForeground(new Color(0, 0, 255));
        cbdestinos.setFont(new Font("Tahoma", Font.BOLD, 12));
        cbdestinos.setEnabled(false);
        cbdestinos.setBounds(88, 13, 103, 25);
        panelconfiguracion.add(cbdestinos);

        // Spinner salida
        lsalida = new JLabel("Salida (1-12)");
        lsalida.setHorizontalAlignment(SwingConstants.CENTER);
        lsalida.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lsalida.setBounds(201, 11, 105, 25);
        panelconfiguracion.add(lsalida);

        spsalida = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        spsalida.setForeground(new Color(0, 0, 255));
        spsalida.setFont(new Font("Tahoma", Font.BOLD, 14));
        spsalida.setEnabled(false);
        spsalida.setBounds(316, 10, 47, 28);
        panelconfiguracion.add(spsalida);

        // Spinner llegada
        lllegada = new JLabel("Llegada (1-12)");
        lllegada.setHorizontalAlignment(SwingConstants.CENTER);
        lllegada.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lllegada.setBounds(373, 11, 103, 25);
        panelconfiguracion.add(lllegada);

        spllegada = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        spllegada.setForeground(new Color(0, 0, 255));
        spllegada.setFont(new Font("Tahoma", Font.BOLD, 14));
        spllegada.setEnabled(false);
        spllegada.setBounds(486, 10, 47, 28);
        panelconfiguracion.add(spllegada);

        // Campo duración
        lduracion = new JLabel("<html><center>Duración<br>del vuelo</center></html>");
        lduracion.setHorizontalAlignment(SwingConstants.CENTER);
        lduracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lduracion.setBounds(543, 11, 78, 34);
        panelconfiguracion.add(lduracion);

        tfduracion = new JTextField();
        tfduracion.setForeground(new Color(0, 0, 255));
        tfduracion.setFont(new Font("Tahoma", Font.BOLD, 12));
        tfduracion.setHorizontalAlignment(SwingConstants.CENTER);
        tfduracion.setEnabled(false);
        tfduracion.setBounds(631, 13, 64, 25);
        panelconfiguracion.add(tfduracion);
        tfduracion.setColumns(10);

        // Temperatura interior
        ltempint = new JLabel("<html><center>Temperatura<br>interior</center></html>");
        ltempint.setHorizontalAlignment(SwingConstants.CENTER);
        ltempint.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ltempint.setBounds(16, 117, 93, 34);
        panelconfiguracion.add(ltempint);

        tftempint = new JTextField();
        tftempint.setForeground(new Color(0, 0, 255));
        tftempint.setFont(new Font("Tahoma", Font.BOLD, 12));
        tftempint.setHorizontalAlignment(SwingConstants.CENTER);
        tftempint.setEnabled(false);
        tftempint.setBounds(119, 126, 53, 25);
        panelconfiguracion.add(tftempint);
        tftempint.setColumns(10);

        // Temperatura exterior
        ltempext = new JLabel("<html><center>Temperatura<br>exterior</center></html>");
        ltempext.setHorizontalAlignment(SwingConstants.CENTER);
        ltempext.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ltempext.setBounds(214, 117, 93, 34);
        panelconfiguracion.add(ltempext);

        tftempext = new JTextField();
        tftempext.setForeground(new Color(0, 0, 255));
        tftempext.setFont(new Font("Tahoma", Font.BOLD, 12));
        tftempext.setHorizontalAlignment(SwingConstants.CENTER);
        tftempext.setEnabled(false);
        tftempext.setBounds(319, 126, 53, 25);
        panelconfiguracion.add(tftempext);
        tftempext.setColumns(10);

        // Barra de combustible
        lcombustible = new JLabel("Combustible (Kg)");
        lcombustible.setHorizontalAlignment(SwingConstants.CENTER);
        lcombustible.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lcombustible.setBounds(120, 67, 123, 25);
        panelconfiguracion.add(lcombustible);

        pb = new JProgressBar();
        pb.setFont(new Font("Tahoma", Font.BOLD, 12));
        pb.setForeground(new Color(0, 0, 255));
        pb.setStringPainted(true);
        pb.setBounds(270, 70, 270, 25);
        panelconfiguracion.add(pb);
        bcontroles = new JButton("3º CONTROLES");
        bcontroles.setIcon(new ImageIcon(Vuelo.class.getResource("/multimedia/sql.png")));
        bcontroles.setForeground(new Color(0, 0, 255));
        bcontroles.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        bcontroles.setFont(new Font("Tahoma", Font.BOLD, 14));
        bcontroles.setEnabled(false);
        bcontroles.setBounds(486, 117, 169, 34);
        panelconfiguracion.add(bcontroles);

        bcontroles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int salida = (Integer) spsalida.getValue();
                int llegada = (Integer) spllegada.getValue();

                if (salida == llegada) {
                    lresul.setText("Las horas de salida y llegada no pueden ser iguales");
                } else if (salida > llegada) {
                    lresul.setText("La hora de salida debe ser anterior a la de llegada");
                } else {
                    // Calculamos la duración
                    int duracion = llegada - salida;
                    tfduracion.setText(String.valueOf(duracion));

                    // Activamos el panel de controles
                    slidervelocidad.setEnabled(true);
                    slideraltitud.setEnabled(true);
                    rbretraidos.setEnabled(true);
                    rbextendidos.setEnabled(true);
                    spflaps.setEnabled(true);
                    bcabecera.setEnabled(true);

                    // Desactivamos elementos previos
                    bvuelos.setEnabled(false);
                    listdestinos.setEnabled(false);
                    spsalida.setEnabled(false);
                    spllegada.setEnabled(false);

                    // Guardamos la configuración en la BBDD
                    updateControles();

                    // Añadimos mensaje a la consola
                    java.util.Date fechaUtil = new Date();
                    java.sql.Timestamp fecha = new java.sql.Timestamp(fechaUtil.getTime());
                    ta.append("[" + fecha + "]\n" + "Configuración de controles realizada...\n");

                    lresul.setText("CONFIGURACIÓN modificada --> Activado panel CONTROLES --> Configure CABECERA DE PISTA");
                }
            }
        });
        bcabecera = new JButton("<html><center>4º CABECERA<br>DE PISTA</center></html>");
        bcabecera.setIcon(new ImageIcon(Vuelo.class.getResource("/multimedia/sql.png")));
        bcabecera.setForeground(new Color(0, 0, 255));
        bcabecera.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        bcabecera.setFont(new Font("Tahoma", Font.BOLD, 14));
        bcabecera.setEnabled(false);
        bcabecera.setBounds(493, 141, 158, 43);
        panelcontroles.add(bcabecera);

        bcabecera.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int velocidad = slidervelocidad.getValue();
                int flaps = (Integer) spflaps.getValue();

                if (velocidad == 0) {
                    lresul.setText("La velocidad no puede ser 0 kmh");
                } else if (velocidad > 51) {
                    lresul.setText("La velocidad debe estar entre 1 y 50 kmh");
                } else if (flaps < 5 || flaps > 15) {
                    lresul.setText("El ángulo de los Flaps debe estar entre 5 y 15 grados");
                } else if (bgslats.getSelection() == null) {
                    lresul.setText("Debe seleccionar un valor para los SLATS");
                } else {
                    // Movimiento visual del avión hacia cabecera de pista
                    lsemaforotierrra.setBackground(new Color(0, 255, 0));
                    timertierra.start();

                    // Guardamos datos en la base de datos
                    insertarCabecera();

                    // Desactivamos controles durante el movimiento
                    bcontroles.setEnabled(false);

                    // Registramos en consola
                    java.util.Date fechaUtil = new Date();
                    java.sql.Timestamp fecha = new java.sql.Timestamp(fechaUtil.getTime());
                    ta.append("[" + fecha + "]\nCabecera de pista iniciada...\n");

                    lresul.setText("Configuración modificada --> Avión en MOVIMIENTO hacia CABECERA DE PISTA");
                }
            }
        });
        bdespegar = new JButton("5º DESPEGAR");
        bdespegar.setIcon(new ImageIcon(Vuelo.class.getResource("/multimedia/sql.png")));
        bdespegar.setForeground(new Color(0, 0, 255));
        bdespegar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        bdespegar.setFont(new Font("Tahoma", Font.BOLD, 14));
        bdespegar.setEnabled(false);
        bdespegar.setBounds(493, 194, 158, 43);
        panelcontroles.add(bdespegar);

        bdespegar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Habilitamos las opciones del piloto automático
                rbactivado.setEnabled(true);
                rbdesactivado.setEnabled(true);

                int velocidad = slidervelocidad.getValue();
                int altitud = slideraltitud.getValue();

                if (velocidad < 251) {
                    lresul.setText("La velocidad de DESPEGUE debe ser superior a 250 kmh");
                } else if (altitud < 3000) {
                    lresul.setText("Altitud mínima de 3.000 metros");
                } else if (bgpa.getSelection() == null) {
                    lresul.setText("Debe seleccionar un valor para el PILOTO AUTOMÁTICO");
                } else {
                    // Activamos el semáforo verde del aire
                    lsemaforoaire.setBackground(new Color(0, 255, 0));

                    // Elegimos el destino y arrancamos el vuelo con su timer correspondiente
                    String destino = cbdestinos.getSelectedItem().toString();
                    switch (destino) {
                        case "Varna":
                            timervarna.start();
                            break;
                        case "Dubrovnik":
                            timerdubro.start();
                            break;
                        case "Narva":
                            timernarva.start();
                            break;
                        case "Düzce":
                            timerduzce.start();
                            break;
                        case "Palermo":
                            timerpalermo.start();
                            break;
                        default:
                            lresul.setText("Destino no reconocido");
                            return;
                    }

                    // Insertamos los datos del vuelo en la base de datos
                    insertarDespegar();

                    // Mostramos en el log
                    java.util.Date fechaUtil = new Date();
                    java.sql.Timestamp fecha = new java.sql.Timestamp(fechaUtil.getTime());
                    ta.append("[" + fecha + "]\nDespegue-Ruta iniciada...\n");

                    lresul.setText("Configuración modificada - Avión en fase de DESPEGUE-RUTA");
                }
            }
        });
        bfinalizado = new JButton("6º FINALIZADO");
        bfinalizado.setIcon(new ImageIcon(Vuelo.class.getResource("/multimedia/sql.png")));
        bfinalizado.setForeground(new Color(0, 0, 255));
        bfinalizado.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        bfinalizado.setFont(new Font("Tahoma", Font.BOLD, 14));
        bfinalizado.setEnabled(false);
        bfinalizado.setBounds(493, 248, 158, 43);
        panelcontroles.add(bfinalizado);

        bfinalizado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Actualizamos el estado del vuelo a "Finalizado" en la BD
                updateFinalizado();

                // Reinicio visual del panel de control
                tbonoff.setBackground(new Color(230, 230, 255));
                tbonoff.setForeground(new Color(0, 0, 0));
                tbonoff.setText("ON / OFF");

                lresul.setText("Consola de vuelo APAGADA");
                bcontroles.setEnabled(false);
                bfinalizado.setEnabled(false);

                pb.setValue(0);
                tfduracion.setText(null);
                spsalida.setValue(1);
                spllegada.setValue(1);
                cbdestinos.removeAllItems();
                tftempint.setText(null);
                tftempext.setText(null);
                slidervelocidad.setValue(0);
                slideraltitud.setValue(0);
                bgslats.clearSelection();
                rbabajo.setSelected(true);
                rbdesactivado.setSelected(true);
                rbactivado.setEnabled(false);
                rbdesactivado.setEnabled(false);
                spflaps.setValue(0);
                dlmdestinos.clear();

                // Reinicio del log y avión en pantalla
                java.util.Date fechaUtil = new Date();
                java.sql.Timestamp fecha = new java.sql.Timestamp(fechaUtil.getTime());
                ta.append("[" + fecha + "]\nVuelo finalizado\n");

                laviontierra.setBounds(734, 119, 59, 66);
                lavionaire.setBounds(744, 390, 50, 46);
            }
        });
    }
    }







