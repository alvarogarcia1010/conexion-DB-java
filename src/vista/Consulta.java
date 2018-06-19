/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Filtro;

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
        Container container = getContentPane();
        container.add(lblCodigo);
        container.add(lblMarca);
        container.add(lblExistencia);
        container.add(codigo);
        container.add(marca);
        container.add(stock);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600,600);
        eventos();
        System.out.println("hola");
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
        this.codigo = new JTextField();
        this.marca = new JComboBox();
        this.stock = new JTextField();
        this.si = new JRadioButton("si",true);
        this.no = new JRadioButton("no");
        this.resultados = new JTable();
        this.buscar = new JButton("Buscar");
        this.insertar = new JButton("Insertar");
        this.eliminar = new JButton("Eliminar");
        this.actualizar = new JButton("Actualizar");
        this.limpiar = new JButton("Limpiar");
        
        this.table = new JPanel();
        //agregar elementos al combox marca
        this.marca.addItem("FRAM");
        this.marca.addItem("WIX");
        this.marca.addItem("Luber Finer");
        this.marca.addItem("OSK");
        //agregando los radio a un grupo
        this.existencia = new ButtonGroup();
        this.existencia.add(si);
        this.existencia.add(no);
        
        this.codigo.setBounds(140, 10, ANCHOC, ALTOC);
        this.marca.setBounds(140, 60, ANCHOC, ALTOC);
        this.si.setBounds(140,140,50,ALTOC);
        this.no.setBounds(210,140,50,ALTOC);
        
        this.buscar.setBounds(300, 10, ANCHOC,ALTOC);
        this.insertar.setBounds(10, 210, ANCHOC,ALTOC);
        this.actualizar.setBounds(150, 210, ANCHOC,ALTOC);
        this.eliminar.setBounds(300, 210, ANCHOC,ALTOC);
        this.limpiar.setBounds(450, 210, ANCHOC,ALTOC);
        this.resultados=new JTable();
        this.table.setBounds(10, 250, 500,200);
        this.table.add(new JScrollPane(resultados));
            
    }

    private void llenarTabla() {

        tm = new DefaultTableModel(){
            public Class<?> getColumnClass(int column){
                switch (column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
          
            
        };
        
        tm.addColumn("Codigo");
        tm.addColumn("Marca");
        tm.addColumn("Stock");
        tm.addColumn("Stock en Sucursal");
        
        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();
        
        for (Filtro fi : filtros){
            this.tm.addRow(new Object[]{fi.getCodigo(),fi.getMarca(),fi.getStock(),fi.getExistencia()});
        }
        
        this.resultados.setModel(this.tm);    
    }
    
    
    public void eventos(){
        insertar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(codigo.getText(),marca.getSelectedItem().toString(),
                Integer.parseInt(stock.getText()),true);
                
                if(no.isSelected()){
                    f.setExistencia(false);
                }
                
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null,"Filtro registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de crear el filtro");
                }
            }
        });
        
    actualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(codigo.getText(),marca.getSelectedItem().toString(),
                Integer.parseInt(stock.getText()),true);
                
                if(no.isSelected()){
                    f.setExistencia(false);
                }
                
                if(fd.update(f)){
                    JOptionPane.showMessageDialog(null,"Filtro Modificado con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de modificar el filtro");
                }
            }
        
        });
        
        eliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                FiltroDao fd = new FiltroDao();
                if(fd.delete(codigo.getText())){
                    JOptionPane.showMessageDialog(null,"Filtro eliminado");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de eliminar el filtro");
                }
            }
        });
    
        buscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(codigo.getText());
                if(f == null){
                    JOptionPane.showMessageDialog(null,"El filtro buscado no se ha encontrado");
                }else{
                    codigo.setText(f.getCodigo());
                    marca.setSelectedItem(f.getMarca());
                    stock.setText(Integer.toString(f.getStock()));

                    if(f.getExistencia()){
                        si.setSelected(true);
                    }else{
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                limpiarCampos();
            }
        });
        
    }
    
    public void limpiarCampos(){
        codigo.setText("");
        marca.setSelectedItem("FRAM");
        stock.setText("");
    }
    
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                Consulta c = new Consulta();
                c.setVisible(true);

            }
            
        });

    }    
}
