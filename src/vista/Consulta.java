/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Container;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alvaro García <alvarogarcia1010 at github.com>
 */
public class Consulta extends JFrame{
    public JLabel lblCodigo, lblMarca, lblStock, lblExistencia;
    public JTextField codigo, descripcion, stock;
    public JComboBox marca;

    public ButtonGroup existencia;
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;
    
    public JPanel table;
    
    public JButton buscar, eliminar, insertar, limpiar, actualizar;
    
    private static final int ANCHOC = 130, ALTOC = 30;
    
    DefaultTableModel tm;
    
    public Consulta(){
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container contenedor = getContentPane();
    }

    private void agregarLabels() {
        this.lblCodigo = new JLabel("Codigo");
        this.lblMarca = new JLabel("Marca");
        this.lblStock = new JLabel("Stock");
        this.lblExistencia = new JLabel("Stock en tienda");
        this.lblCodigo.setBounds(10,10, this.ANCHOC,this.ALTOC);
        this.lblMarca.setBounds(10,60, this.ANCHOC,this.ALTOC);
        this.lblStock.setBounds(10,100, this.ANCHOC,this.ALTOC);
        this.lblExistencia.setBounds(10,140, this.ANCHOC,this.ALTOC);
    }

    private void formulario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void llenarTabla() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
