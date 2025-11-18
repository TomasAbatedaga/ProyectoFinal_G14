/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Estilo.EstiloVisual;
import Modelo.Consultorio;
import Modelo.Dia_de_Spa;
import Modelo.Instalacion;
import Modelo.Masajista;
import Modelo.Sesion;
import Modelo.Tratamiento;
import Persistencia.ConsultorioData;
import Persistencia.InstalacionData;
import Persistencia.MasajistaData;
import Persistencia.SesionData;
import Persistencia.TratamientoData;
import java.util.ArrayList;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import Persistencia.Dia_de_SpaData;

/**
 *
 * @author abate
 */
public class GestionDeSesiones extends javax.swing.JInternalFrame {

    private ArrayList<Tratamiento> listaTratamiento;
    private ArrayList<Consultorio> listaConsultorio;
    private ArrayList<Masajista> listaMasajista;
    private ArrayList<Instalacion> listaInstalacion;
    private ArrayList<Dia_de_Spa> listaDiaDeSpa;
    private Dia_de_SpaData abmDia_de_Spa;
    private InstalacionData abmInstalacion;
    private MasajistaData abmMasajista;
    private ConsultorioData abmConsultorio;
    private TratamientoData abmTratamiento;
    private int codigoSeleccionado;
    private String modoDeSesion;

    /**
     * Creates new form GestionDeSesiones
     */
    public GestionDeSesiones() {
        initComponents();
        EstiloVisual.aplicarEstiloJDPanel(jDesktopPane1);
        EstiloVisual.aplicarEstiloBoton(jBtnBorrar);
        EstiloVisual.aplicarEstiloBoton(jBtnAgregar);
        EstiloVisual.aplicarEstiloBoton(JBtnModificar);
        EstiloVisual.aplicarEstiloBoton(jBtnBuscar);

        abmConsultorio = new ConsultorioData();
        abmTratamiento = new TratamientoData();
        abmMasajista = new MasajistaData();
        abmInstalacion = new InstalacionData();
        abmDia_de_Spa = new Dia_de_SpaData();
        listaDiaDeSpa = (ArrayList<Dia_de_Spa>) abmDia_de_Spa.listarDia_de_Spa();
        listaTratamiento = (ArrayList<Tratamiento>) abmTratamiento.listarTratamientos();
        listaConsultorio = (ArrayList<Consultorio>) abmConsultorio.listarConsultorios();
        listaMasajista = (ArrayList<Masajista>) abmMasajista.listarMasajista();
        listaInstalacion = (ArrayList<Instalacion>) abmInstalacion.listarInstalaciones();
        cargarComboTratamientos();
        cargarComboConsultorio();
        cargarComboMasajista();
        cargarComboInstalacion();
        cargarComboDiaDeSpa();
    }

    public GestionDeSesiones(int codPack) {
        initComponents();
        this.codigoSeleccionado = codPack;
        inicializarDatos();
        seleccionarDiaDeSpa();
        EstiloVisual.aplicarEstiloJDPanel(jDesktopPane1);
        EstiloVisual.aplicarEstiloBoton(jBtnBorrar);
        EstiloVisual.aplicarEstiloBoton(jBtnAgregar);
        EstiloVisual.aplicarEstiloBoton(JBtnModificar);
        EstiloVisual.aplicarEstiloBoton(jBtnBuscar);
    }

    private void inicializarDatos() {
        abmConsultorio = new ConsultorioData();
        abmTratamiento = new TratamientoData();
        abmMasajista = new MasajistaData();
        abmInstalacion = new InstalacionData();
        abmDia_de_Spa = new Dia_de_SpaData();

        listaDiaDeSpa = (ArrayList<Dia_de_Spa>) abmDia_de_Spa.listarDia_de_Spa();
        listaTratamiento = (ArrayList<Tratamiento>) abmTratamiento.listarTratamientos();
        listaConsultorio = (ArrayList<Consultorio>) abmConsultorio.listarConsultorios();
        listaMasajista = (ArrayList<Masajista>) abmMasajista.listarMasajista();
        listaInstalacion = (ArrayList<Instalacion>) abmInstalacion.listarInstalaciones();

        cargarComboTratamientos();
        cargarComboConsultorio();
        cargarComboMasajista();
        cargarComboInstalacion();
        cargarComboDiaDeSpa();

        Object[] opciones = {"Sesion de Tratamiento", "Sesion de Instalacion"};
        int eleccion = JOptionPane.showOptionDialog(
                this, "Que tipo de sesion desea agregar?", "Seleccione un Tipo de Sesion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (eleccion == JOptionPane.YES_OPTION) {
            this.modoDeSesion = "TRATAMIENTO";
            jCbInstalacion.setEnabled(false);
            jCbInstalacion.setSelectedIndex(0);

        } else if (eleccion == JOptionPane.NO_OPTION) {
            this.modoDeSesion = "INSTALACION";
            jCbtratamiento.setEnabled(false);
            jCbconsultorio.setEnabled(false);
            jCbmasajista.setEnabled(false);
            jCbtratamiento.setSelectedIndex(0);
            jCbconsultorio.setSelectedIndex(0);
            jCbmasajista.setSelectedIndex(0);

        } else {

            this.modoDeSesion = "CANCELADO";

            javax.swing.SwingUtilities.invokeLater(() -> {
                this.dispose();
            });
        }
    }

    private void seleccionarDiaDeSpa() {
        jCbCodPaquete.setEnabled(false);
        for (int i = 1; i < jCbCodPaquete.getItemCount(); i++) {
            Dia_de_Spa dia = (Dia_de_Spa) jCbCodPaquete.getItemAt(i);
            if (dia.getCodPack() == codigoSeleccionado) {
                jCbCodPaquete.setSelectedIndex(i);
                return;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();
        jservicio = new javax.swing.JLabel();
        jServicio1 = new javax.swing.JLabel();
        jBtnAgregar = new javax.swing.JButton();
        jServicio2 = new javax.swing.JLabel();
        jServicio3 = new javax.swing.JLabel();
        jServicio4 = new javax.swing.JLabel();
        jCbmasajista = new javax.swing.JComboBox<>();
        jServicio5 = new javax.swing.JLabel();
        jCheckEstado = new javax.swing.JCheckBox();
        jBtnBorrar = new javax.swing.JButton();
        JBtnModificar = new javax.swing.JButton();
        jCbtratamiento = new javax.swing.JComboBox<>();
        jCbconsultorio = new javax.swing.JComboBox<>();
        jTfhoraInicio = new javax.swing.JTextField();
        jTfhoraFin = new javax.swing.JTextField();
        jServicio6 = new javax.swing.JLabel();
        jCbInstalacion = new javax.swing.JComboBox<>();
        jServicio7 = new javax.swing.JLabel();
        jCbCodPaquete = new javax.swing.JComboBox<>();
        jServicio8 = new javax.swing.JLabel();
        jTfCodSesion = new javax.swing.JTextField();
        jBtnBuscar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel2.setFont(new java.awt.Font("SimSun", 1, 24)); // NOI18N
        jLabel2.setText("Agregar Sesiones");

        jservicio.setText("Tipo de tratamiento:");

        jServicio1.setText("Hora Inicio:");

        jBtnAgregar.setText("Agregar");
        jBtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAgregarActionPerformed(evt);
            }
        });

        jServicio2.setText("Hora Final:");

        jServicio3.setText("Consultorio:");

        jServicio4.setText("Masajista:");

        jCbmasajista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbmasajistaActionPerformed(evt);
            }
        });

        jServicio5.setText("Estado:");

        jCheckEstado.setSelected(true);

        jBtnBorrar.setText("Borrar");
        jBtnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBorrarActionPerformed(evt);
            }
        });

        JBtnModificar.setText("Modificar");
        JBtnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBtnModificarActionPerformed(evt);
            }
        });

        jCbconsultorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbconsultorioActionPerformed(evt);
            }
        });

        jTfhoraInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTfhoraInicioActionPerformed(evt);
            }
        });

        jServicio6.setText("Instalacion:");

        jCbInstalacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbInstalacionActionPerformed(evt);
            }
        });

        jServicio7.setText("Cod. Paquete:");

        jCbCodPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbCodPaqueteActionPerformed(evt);
            }
        });

        jServicio8.setText("Buscar Sesion:");

        jBtnBuscar.setText("Buscar");
        jBtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jservicio, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jServicio1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jBtnAgregar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jServicio2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jServicio3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jServicio4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jCbmasajista, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jServicio5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jCheckEstado, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jBtnBorrar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(JBtnModificar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jCbtratamiento, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jCbconsultorio, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jTfhoraInicio, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jTfhoraFin, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jServicio6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jCbInstalacion, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jServicio7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jCbCodPaquete, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jServicio8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jTfCodSesion, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jBtnBuscar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jservicio, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jServicio7, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGap(15, 15, 15))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jServicio8, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jServicio1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jServicio3, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jServicio4, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jServicio6, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jServicio5, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGap(18, 18, 18)))
                            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jCbconsultorio, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                        .addComponent(jTfCodSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jBtnBuscar))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                            .addComponent(jTfhoraInicio)
                                            .addGap(18, 18, 18)
                                            .addComponent(jServicio2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTfhoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jCbtratamiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jCbmasajista, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCbInstalacion, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckEstado)
                                    .addComponent(jCbCodPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(57, 57, 57))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(137, 137, 137)))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jBtnAgregar)
                        .addGap(131, 131, 131)
                        .addComponent(JBtnModificar)
                        .addGap(117, 117, 117)
                        .addComponent(jBtnBorrar)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jServicio8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTfCodSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jservicio)
                    .addComponent(jCbtratamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jServicio1)
                    .addComponent(jServicio2)
                    .addComponent(jTfhoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTfhoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jServicio3)
                    .addComponent(jCbconsultorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jServicio4)
                    .addComponent(jCbmasajista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jServicio6)
                    .addComponent(jCbInstalacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCbCodPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jServicio7))
                .addGap(30, 30, 30)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckEstado)
                    .addComponent(jServicio5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBtnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAgregarActionPerformed
        // TODO add your handling code here:
        try {
            SesionData sd = new SesionData();

            if (jCbCodPaquete.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un Codigo Paquete.");
                return;
            }
            Dia_de_Spa diaDeSpa = (Dia_de_Spa) jCbCodPaquete.getSelectedItem();
            boolean estado = jCheckEstado.isSelected();
            LocalDate fechaDiaDeSpa = diaDeSpa.getFechaYHora();

            // VALIDACION DE LA HORA
            String horaInicioStr = jTfhoraInicio.getText();
            String horaFinStr = jTfhoraFin.getText();
            int horaInicioInt;
            int horaFinInt;

            try {
                horaInicioInt = Integer.parseInt(horaInicioStr);
                horaFinInt = Integer.parseInt(horaFinStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error al ingresar la hora, ingrese un numero (ej: 10).");
                return;
            }

            if (horaFinInt <= horaInicioInt) {
                JOptionPane.showMessageDialog(this, "La hora final debe ser posterior a la de inicio");
                return;
            }

            if (horaInicioStr.length() <= 2) {
                horaInicioStr += ":00:00";
            } else if (horaInicioStr.length() == 5) {
                horaInicioStr += ":00";
            }
            if (horaFinStr.length() <= 2) {
                horaFinStr += ":00:00";
            } else if (horaFinStr.length() == 5) {
                horaFinStr += ":00";
            }
            Time horaInicio = Time.valueOf(horaInicioStr);
            Time horaFin = Time.valueOf(horaFinStr);

            // VALIDACION DEL SELECTOR DE TIPO DE GESTION 
            Tratamiento tratamiento = null;
            Consultorio consultorio = null;
            Masajista masajista = null;
            Instalacion instalacion = null;

            // SI SELECCIONA EL TIPO DE GESTION DE TRATAMIENTO
            if ("TRATAMIENTO".equals(this.modoDeSesion)) {
                if (jCbtratamiento.getSelectedIndex() == 0 || jCbconsultorio.getSelectedIndex() == 0 || jCbmasajista.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(this, "Para agregar una sesion de tratamiento debe seleccionar tratamiento, consultorio y masajista.");
                    return;
                }

                tratamiento = (Tratamiento) jCbtratamiento.getSelectedItem();
                consultorio = (Consultorio) jCbconsultorio.getSelectedItem();
                masajista = (Masajista) jCbmasajista.getSelectedItem();

                // VALIDACION DE LAS DURACIONES DE TRATAMIENTO
                int duracionAgendada = horaFinInt - horaInicioInt;
                int duracionRequerida = tratamiento.getDuracion();
                if (duracionAgendada != duracionRequerida) {
                    JOptionPane.showMessageDialog(this, "Duracion Agendada: " + duracionAgendada + "h | Requerida: " + duracionRequerida + "h");
                    return;
                }
                if (masajista.getEspecialidad() != tratamiento.getEspecialidad()) {
                    JOptionPane.showMessageDialog(this, "Masajista: " + masajista.getEspecialidad() + " y " + "Tratamiento: " + tratamiento.getEspecialidad());
                    return;
                }

                // VALIDACIONES DE LA DISPONIBILIDAD DE MASAJISTA Y CONSULTORIO
                if (sd.masajistaOcupado(masajista.getCod_Masajista(), fechaDiaDeSpa, horaInicio, horaFin)) {
                    JOptionPane.showMessageDialog(this, "El masajista " + masajista.getNombreCompleto() + " esta ocupado.");
                    return;
                }
                if (sd.consultorioOcupado(consultorio.getCodConsultorio(), fechaDiaDeSpa, horaInicio, horaFin)) {
                    JOptionPane.showMessageDialog(this, "El consultorio " + consultorio.toString() + " esta ocupado.");
                    return;
                }
                Sesion sesion = new Sesion(horaInicio, horaFin, tratamiento, consultorio, masajista, null, diaDeSpa, estado);
                sd.agregarSesionTratamiento(sesion);

            } else if ("INSTALACION".equals(this.modoDeSesion)) {
                if (jCbInstalacion.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(this, "Para agregar una sesion de instalacion debe seleccionar una instalacion.");
                    return;
                }

                instalacion = (Instalacion) jCbInstalacion.getSelectedItem();

                // VALIDACION DE DISPONIBILIDAD DE INSTALACION
                if (sd.instalacionOcupada(instalacion.getCodInstal(), fechaDiaDeSpa, horaInicio, horaFin)) {
                    JOptionPane.showMessageDialog(this, "La instalacion " + instalacion.getNombre() + " esta ocupada.");
                    return;
                }

                Sesion sesion = new Sesion(horaInicio, horaFin, null, null, null, instalacion, diaDeSpa, estado);
                sd.agregarSesionInstalacion(sesion);

            } else {
                JOptionPane.showMessageDialog(this, "Entre desde la vista Crear Dia De Spa para poder gestionar los datos");
                return;
            }

            jTfhoraInicio.setText("");
            jTfhoraFin.setText("");
            jCheckEstado.setSelected(false);

            jCbtratamiento.setSelectedIndex(0);
            jCbconsultorio.setSelectedIndex(0);
            jCbmasajista.setSelectedIndex(0);
            jCbInstalacion.setSelectedIndex(0);

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error en el formato de hora");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar la sesion: " + e.getMessage());
        }
    }//GEN-LAST:event_jBtnAgregarActionPerformed

    private void jCbmasajistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbmasajistaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbmasajistaActionPerformed

    private void jBtnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBorrarActionPerformed
        // TODO add your handling code here:
        try {
            String idStr = jTfCodSesion.getText();
            if (idStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe buscar una sesion");
                return;
            }
            int codSesion = Integer.parseInt(idStr);

            int respuesta = JOptionPane.showConfirmDialog(this,
                    "Seguo queres eliminar la sesion " + codSesion + "?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {

                SesionData sd = new SesionData();
                sd.eliminarSesion(codSesion);
                jTfCodSesion.setText("");
                jTfhoraInicio.setText("");
                jTfhoraFin.setText("");
                jCheckEstado.setSelected(false);

                jCbtratamiento.setSelectedIndex(0);
                jCbconsultorio.setSelectedIndex(0);
                jCbmasajista.setSelectedIndex(0);
                jCbInstalacion.setSelectedIndex(0);
                // jCbCodPaquete.setSelectedIndex(0); 
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El codigo es invalido");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la sesion: " + e.getMessage());
        }

    }//GEN-LAST:event_jBtnBorrarActionPerformed

    private void JBtnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtnModificarActionPerformed
        // TODO add your handling code here:
        try {
            SesionData sd = new SesionData();

            int codSesion;
            try {
                codSesion = Integer.parseInt(jTfCodSesion.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Codigo de sesion no existe o es invalida");
                return;
            }

            Dia_de_Spa diaDeSpa = (Dia_de_Spa) jCbCodPaquete.getSelectedItem();
            boolean estado = jCheckEstado.isSelected();
            LocalDate fechaDiaDeSpa = diaDeSpa.getFechaYHora();

            String horaInicioStr = jTfhoraInicio.getText();
            String horaFinStr = jTfhoraFin.getText();
            int horaInicioInt = Integer.parseInt(horaInicioStr);
            int horaFinInt = Integer.parseInt(horaFinStr);

            if (horaFinInt <= horaInicioInt) {
                return;
            }

            if (horaInicioStr.length() <= 2) {
                horaInicioStr += ":00:00";
            } else if (horaInicioStr.length() == 5) {
                horaInicioStr += ":00";
            }
            if (horaFinStr.length() <= 2) {
                horaFinStr += ":00:00";
            } else if (horaFinStr.length() == 5) {
                horaFinStr += ":00";
            }
            Time horaInicio = Time.valueOf(horaInicioStr);
            Time horaFin = Time.valueOf(horaFinStr);

            Tratamiento tratamiento = null;
            Consultorio consultorio = null;
            Masajista masajista = null;
            Instalacion instalacion = null;

            Sesion sesion;

            if ("TRATAMIENTO".equals(this.modoDeSesion)) {
                // MODO TRATAMIENTO
                if (jCbtratamiento.getSelectedIndex() == 0 || jCbconsultorio.getSelectedIndex() == 0 || jCbmasajista.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar tratamiento, consultorio y masajista.");
                    return;
                }
                tratamiento = (Tratamiento) jCbtratamiento.getSelectedItem();
                consultorio = (Consultorio) jCbconsultorio.getSelectedItem();
                masajista = (Masajista) jCbmasajista.getSelectedItem();

                // VALIDACIONES
                if (sd.masajistaOcupado(masajista.getCod_Masajista(), fechaDiaDeSpa, horaInicio, horaFin, codSesion)) {
                    JOptionPane.showMessageDialog(this, "El masajista esta ocupado.");
                    return;
                }
                if (sd.consultorioOcupado(consultorio.getCodConsultorio(), fechaDiaDeSpa, horaInicio, horaFin, codSesion)) {
                    JOptionPane.showMessageDialog(this, "El consultorio esta ocupado.");
                    return;
                }

                sesion = new Sesion(horaInicio, horaFin, tratamiento, consultorio, masajista, null, diaDeSpa, estado);
                sesion.setCodSesion(codSesion);

                sd.actualizarSesionTratamiento(sesion);

            } else if ("INSTALACION".equals(this.modoDeSesion)) {
                if (jCbInstalacion.getSelectedIndex() == 0) {
                    return;
                }
                instalacion = (Instalacion) jCbInstalacion.getSelectedItem();

                if (sd.instalacionOcupada(instalacion.getCodInstal(), fechaDiaDeSpa, horaInicio, horaFin, codSesion)) {
                    JOptionPane.showMessageDialog(this, "La instalacion esta ocupada.");
                    return;
                }

                sesion = new Sesion(horaInicio, horaFin, null, null, null, instalacion, diaDeSpa, estado);
                sesion.setCodSesion(codSesion);

                sd.actualizarSesionInstalacion(sesion);

            } else {
                JOptionPane.showMessageDialog(this, "Entre desde la vista Crear Dia De Spa para poder gestionar los datos");
                return;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar la sesion: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_JBtnModificarActionPerformed

    private void jCbInstalacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbInstalacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbInstalacionActionPerformed

    private void jCbCodPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbCodPaqueteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbCodPaqueteActionPerformed

    private void jBtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarActionPerformed
        // TODO add your handling code here:
        try {
            String idStr = jTfCodSesion.getText();
            if (idStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el codigo para buscar");
                return;
            }
            int codSesion = Integer.parseInt(idStr);

            SesionData sd = new SesionData();
            Sesion sesion = sd.buscarSesiones(codSesion); // Asegúrate que este método ya use la nueva estructura (con cod_instalacion)

            if (sesion != null) {
                jTfhoraInicio.setText(sesion.getFechaHoraInicio().toString());
                jTfhoraFin.setText(sesion.getFechaHoraFin().toString());
                jCheckEstado.setSelected(sesion.isEstado());

                for (int i = 1; i < jCbCodPaquete.getItemCount(); i++) {
                    Dia_de_Spa d = (Dia_de_Spa) jCbCodPaquete.getItemAt(i);
                    if (d.getCodPack() == sesion.getDiaDeSpa().getCodPack()) {
                        jCbCodPaquete.setSelectedIndex(i);
                        break;
                    }
                }

                if (sesion.getTratamiento() != null && sesion.getTratamiento().getCodTratam() > 0) {

                    this.modoDeSesion = "TRATAMIENTO";

                    jCbtratamiento.setEnabled(true);
                    jCbconsultorio.setEnabled(true);
                    jCbmasajista.setEnabled(true);
                    jCbInstalacion.setEnabled(false);
                    jCbInstalacion.setSelectedIndex(0);

                    for (int i = 1; i < jCbtratamiento.getItemCount(); i++) {
                        Tratamiento t = (Tratamiento) jCbtratamiento.getItemAt(i);
                        if (t.getCodTratam() == sesion.getTratamiento().getCodTratam()) {
                            jCbtratamiento.setSelectedIndex(i);
                            break;
                        }
                    }
                    for (int i = 1; i < jCbconsultorio.getItemCount(); i++) {
                        Consultorio c = (Consultorio) jCbconsultorio.getItemAt(i);
                        if (c.getCodConsultorio() == sesion.getConsultorio().getCodConsultorio()) {
                            jCbconsultorio.setSelectedIndex(i);
                            break;
                        }
                    }
                    for (int i = 1; i < jCbmasajista.getItemCount(); i++) {
                        Masajista m = (Masajista) jCbmasajista.getItemAt(i);
                        if (m.getCod_Masajista() == sesion.getMasajista().getCod_Masajista()) {
                            jCbmasajista.setSelectedIndex(i);
                            break;
                        }
                    }

                } else if (sesion.getInstalaciones() != null && sesion.getInstalaciones().getCodInstal() > 0) {

                    this.modoDeSesion = "INSTALACION";

                    jCbInstalacion.setEnabled(true);
                    jCbtratamiento.setEnabled(false);
                    jCbconsultorio.setEnabled(false);
                    jCbmasajista.setEnabled(false);
                    jCbtratamiento.setSelectedIndex(0);
                    jCbconsultorio.setSelectedIndex(0);
                    jCbmasajista.setSelectedIndex(0);

                    for (int i = 1; i < jCbInstalacion.getItemCount(); i++) {
                        Instalacion ins = (Instalacion) jCbInstalacion.getItemAt(i);
                        if (ins.getCodInstal() == sesion.getInstalaciones().getCodInstal()) {
                            jCbInstalacion.setSelectedIndex(i);
                            break;
                        }
                    }
                }

            } else {
                JOptionPane.showMessageDialog(this, "No hay ninguna sesion con ese codigo");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un codigo valido");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_jBtnBuscarActionPerformed

    private void jCbconsultorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbconsultorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbconsultorioActionPerformed

    private void jTfhoraInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTfhoraInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTfhoraInicioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GestionDeSesiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionDeSesiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionDeSesiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionDeSesiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionDeSesiones().setVisible(true);
            }
        });
    }

    private void cargarComboTratamientos() {
        jCbtratamiento.addItem("Seleccione un tratamiento");
        for (Tratamiento tratamiento : listaTratamiento) {
            jCbtratamiento.addItem(tratamiento);
        }
    }

    private void cargarComboConsultorio() {
        jCbconsultorio.addItem(("Seleccione un consultorio"));
        for (Consultorio consultorio : listaConsultorio) {
            jCbconsultorio.addItem(consultorio);
        }
    }

    private void cargarComboMasajista() {
        jCbmasajista.addItem("Seleccione un masajista");
        for (Masajista masajista : listaMasajista) {
            jCbmasajista.addItem(masajista);
        }
    }

    private void cargarComboInstalacion() {
        jCbInstalacion.addItem("Seleccione una instalacion");
        for (Instalacion instalacion : listaInstalacion) {
            jCbInstalacion.addItem(instalacion);
        }
    }

    private void cargarComboDiaDeSpa() {
        jCbCodPaquete.addItem("Seleccione un paquete");
        for (Dia_de_Spa diaDeSpa : listaDiaDeSpa) {
            jCbCodPaquete.addItem(diaDeSpa);

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBtnModificar;
    private javax.swing.JButton jBtnAgregar;
    private javax.swing.JButton jBtnBorrar;
    private javax.swing.JButton jBtnBuscar;
    private javax.swing.JComboBox<Object> jCbCodPaquete;
    private javax.swing.JComboBox<Object> jCbInstalacion;
    private javax.swing.JComboBox<Object> jCbconsultorio;
    private javax.swing.JComboBox<Object> jCbmasajista;
    private javax.swing.JComboBox<Object> jCbtratamiento;
    private javax.swing.JCheckBox jCheckEstado;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jServicio1;
    private javax.swing.JLabel jServicio2;
    private javax.swing.JLabel jServicio3;
    private javax.swing.JLabel jServicio4;
    private javax.swing.JLabel jServicio5;
    private javax.swing.JLabel jServicio6;
    private javax.swing.JLabel jServicio7;
    private javax.swing.JLabel jServicio8;
    private javax.swing.JTextField jTfCodSesion;
    private javax.swing.JTextField jTfhoraFin;
    private javax.swing.JTextField jTfhoraInicio;
    private javax.swing.JLabel jservicio;
    // End of variables declaration//GEN-END:variables
}
