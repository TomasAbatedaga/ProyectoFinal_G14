package Estilo;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.JTableHeader;


public class EstiloVisual {

    //Paleta de colores
    public static final Color COLOR_FONDO = new Color(210, 240, 255);   // Celeste claro
    public static final Color COLOR_VERDE_MENTA = new Color(150, 200, 170); // Verde menta
    public static final Color COLOR_VERDE_AGUA = new Color(190, 250, 250);  // Verde agua 
    public static final Color COLOR_BOTON = new Color(100, 160, 200); // Azul suave
    
    public static final Font FUENTE_TEXTO = new Font("Segoe UI", Font.PLAIN, 14);
    
    // Tabla
    public static void aplicarEstiloTabla(JTable tabla) {
        tabla.setRowHeight(25);

        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
      
    }
    
    // Boton
    public static void aplicarEstiloBoton(JButton boton) {

        boton.setBackground(COLOR_BOTON);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
    }

    // Panel de fondo
    public static void aplicarEstiloPanel(JPanel panel) {
        panel.setOpaque(true);
        panel.setBackground(COLOR_FONDO);
    }

    public static void aplicarEstiloJDPanel(JDesktopPane JDesktopPane) {
        JDesktopPane.setOpaque(true);
        JDesktopPane.setBackground(COLOR_FONDO);
    }

}
