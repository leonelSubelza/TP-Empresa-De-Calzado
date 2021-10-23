package presentacion.vista.Cajero;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
//import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import persistencia.conexion.Conexion;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaRealizarVenta {

	private JFrame frame;
	private final JPanel panel = new JPanel();
	
	private JTable tableMedioPago;
	private DefaultTableModel modelTablaMedioPago;
	private String[] nombreColumnasMedioPago = {"M�todo","Moneda","Num. Operaci�n","Cantidad","Cant. (en AR$)"};
	private JScrollPane scrollPaneMedioPago;
	
	private JComboBox<String> comboBoxMetodoPago;
	private JTextField textNumOperacion;
	
	@SuppressWarnings("unused")
	private SpinnerModel spinnerModelCant;


	private JButton btnAgregarMedioPago;
	private JButton btnCancelarVenta;
	private JButton btnFinalizarVenta;
	private JButton btnQuitarMedioPago;

	private JLabel lblTotalAPagarValor;
	private JLabel lblPrecioVentaValor;
	private JLabel lblDescuentoDescontado;

	private JTextField textCantidad;
	private JTextField textDescuento;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRealizarVenta window = new VentanaRealizarVenta();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaRealizarVenta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 854, 554);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 840, 517);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		frame.setResizable(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 10, 827, 41);
		panel.add(panel_1);
		
		JLabel lblTitulo = new JLabel("Zapateria");
		lblTitulo.setFont(new Font("Comic Sans MS", Font.PLAIN, 38));
		lblTitulo.setBounds(10, 0, 310, 41);
		panel_1.add(lblTitulo);
		
		JLabel lblNewLabel = new JLabel("Venta");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 29));
		lblNewLabel.setBounds(20, 50, 234, 47);
		panel.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 96, 827, 360);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMediosDePago = new JLabel("Medios de pago");
		lblMediosDePago.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblMediosDePago.setBounds(10, 10, 158, 21);
		panel_2.add(lblMediosDePago);
		
		JLabel lblDineroRestante = new JLabel("Pagando");
		lblDineroRestante.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		lblDineroRestante.setBounds(542, 312, 158, 34);
		panel_2.add(lblDineroRestante);
		
		JLabel lblMtodoDePago = new JLabel("M�todo de pago");
		lblMtodoDePago.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblMtodoDePago.setBounds(10, 41, 100, 21);
		panel_2.add(lblMtodoDePago);
		
		JLabel lblltimosDgitos = new JLabel("Numero de operacion");
		lblltimosDgitos.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblltimosDgitos.setBounds(218, 10, 138, 21);
		panel_2.add(lblltimosDgitos);

		
		JLabel lblTotalAPagar = new JLabel("Total a pagar:");
		lblTotalAPagar.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		lblTotalAPagar.setBounds(10, 312, 158, 34);
		panel_2.add(lblTotalAPagar);
		
		lblTotalAPagarValor = new JLabel("$0");
		lblTotalAPagarValor.setBackground(new Color(255, 255, 255));
		lblTotalAPagarValor.setForeground(Color.BLACK);
		lblTotalAPagarValor.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblTotalAPagarValor.setBounds(126, 312, 177, 34);
		panel_2.add(lblTotalAPagarValor);
		
		//TABLAS
		
		//Tabla Medio Pago
		this.modelTablaMedioPago = new DefaultTableModel(null, this.nombreColumnasMedioPago){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int filas, int columnas) {	
				if(columnas == 6) {
					return true;
				}else {
					return false;
				}
			}
		};
		scrollPaneMedioPago = new JScrollPane(this.tableMedioPago, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneMedioPago.setBounds(0, 94, 820, 208);
		
		tableMedioPago = new JTable(modelTablaMedioPago);
		tableMedioPago.setBounds(334, 254, 641, 208);
		this.tableMedioPago.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.tableMedioPago.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPaneMedioPago.setViewportView(tableMedioPago);
		panel_2.add(scrollPaneMedioPago);
		//
		
		//ComboBox
		comboBoxMetodoPago = new JComboBox<String>();
		comboBoxMetodoPago.addItem("Sin seleccionar");
		comboBoxMetodoPago.setBounds(10, 63, 168, 21);
		panel_2.add(comboBoxMetodoPago);
		
		
		
		//
		//Spinner
		spinnerModelCant = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		
		JLabel lblModificarCantidad = new JLabel("Monto");
		lblModificarCantidad.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblModificarCantidad.setBounds(318, 41, 38, 21);
		panel_2.add(lblModificarCantidad);
		
		lblPrecioVentaValor = new JLabel("$0");
		lblPrecioVentaValor.setBounds(617, 312, 177, 34);
		panel_2.add(lblPrecioVentaValor);
		lblPrecioVentaValor.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblPrecioVentaValor.setForeground(new Color(0, 128, 0));
		
		//TXT
		textCantidad = new JTextField();
		textCantidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textCantidad.getText().length()>=15) {
                    e.consume();
                }
            }
        });
		textCantidad.setBounds(362, 40, 133, 19);
		panel_2.add(textCantidad);
		textCantidad.setColumns(10);
		
		textDescuento = new JTextField();
		textDescuento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textDescuento.getText().length()>=15) {
                    e.consume();
                }
            }
        });
		textDescuento.setColumns(10);
		textDescuento.setBounds(362, 64, 133, 19);
		panel_2.add(textDescuento);
		
		
		textNumOperacion = new JTextField();
		textNumOperacion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textNumOperacion.getText().length()>=15) {
                    e.consume();
                }
            }
        });
		textNumOperacion.setColumns(10);
		textNumOperacion.setBounds(362, 9, 133, 20);
		panel_2.add(textNumOperacion);
		
		//
		
		btnAgregarMedioPago = new JButton("");
		btnAgregarMedioPago.setBounds(668, 32, 55, 52);
		cambiarIconoBotones(btnAgregarMedioPago,  "pay.png");
		panel_2.add(btnAgregarMedioPago);
		
		btnQuitarMedioPago = new JButton("");
		btnQuitarMedioPago.setBounds(755, 32, 46, 52);
		cambiarIconoBotones(btnQuitarMedioPago,  "trash.png");
		panel_2.add(btnQuitarMedioPago);
		
		JLabel lblAadirDescuento = new JLabel("A\u00F1adir descuento");
		lblAadirDescuento.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblAadirDescuento.setBounds(240, 65, 116, 21);
		panel_2.add(lblAadirDescuento);
		

		
		JLabel lblDescuento = new JLabel("Descuento:");
		lblDescuento.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		lblDescuento.setBounds(256, 312, 158, 34);
		panel_2.add(lblDescuento);
		
		lblDescuentoDescontado = new JLabel("$0");
		lblDescuentoDescontado.setForeground(Color.BLACK);
		lblDescuentoDescontado.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblDescuentoDescontado.setBackground(Color.WHITE);
		lblDescuentoDescontado.setBounds(355, 312, 177, 34);
		panel_2.add(lblDescuentoDescontado);
		
		btnCancelarVenta = new JButton("");
		btnCancelarVenta.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		btnCancelarVenta.setBounds(127, 460, 61, 47);
		btnCancelarVenta.setBackground(null);
		cambiarIconoBotones(btnCancelarVenta,  "cancel.png");
		panel.add(btnCancelarVenta);
		
		btnFinalizarVenta = new JButton("");
		btnFinalizarVenta.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		btnFinalizarVenta.setBounds(689, 460, 67, 47);
//		btnFinalizarVenta.setBorder(null);
//		btnFinalizarVenta.setBackground(null);
		cambiarIconoBotones(btnFinalizarVenta,  "dollar-symbol.png");
		panel.add(btnFinalizarVenta);

		JLabel lblCancelar = new JLabel("Cancelar");
		lblCancelar.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblCancelar.setBounds(34, 452, 114, 55);
		panel.add(lblCancelar);
		
		JLabel lblRealizarCobro = new JLabel("Realizar cobro");
		lblRealizarCobro.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblRealizarCobro.setBounds(536, 452, 188, 55);
		panel.add(lblRealizarCobro);
	}
	
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "�Est�s seguro que quieres salir?. Se perder� todos los progresos que se hayan realizado", 
		             "Advertencia", JOptionPane.YES_NO_OPTION,
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		        	Conexion.getConexion().cerrarConexion();
		           System.exit(0);
		        }
		    }
		});
		this.frame.setVisible(true);
	}

	public void cambiarIconoBotones(JButton boton, String ruta) {
		ImageIcon Imagen = new ImageIcon(getClass().getResource("/imagenes/"+ruta));
		ImageIcon Icono = new ImageIcon(Imagen.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_DEFAULT));
		boton.setIcon(Icono);
	}
	
	public void cerrar() {
		frame.setVisible(false);
	}
	

	public JTable getTableMedioPago() {
		return tableMedioPago;
	}

	public JComboBox<String> getComboBoxMetodoPago() {
		return comboBoxMetodoPago;
	}


	public JScrollPane getScrollPaneMedioPago() {
		return scrollPaneMedioPago;
	}


	public JTextField getTextNumOperacion() {
		return textNumOperacion;
	}

	public JButton getBtnAgregarMedioPago() {
		return btnAgregarMedioPago;
	}

	public JLabel getLblTotalAPagarValor() {
		return lblTotalAPagarValor;
	}

	public JLabel getLblPrecioVentaValor() {
		return lblPrecioVentaValor;
	}

	public DefaultTableModel getModelTablaMedioPago() {
		return modelTablaMedioPago;
	}

	public String[] getNombreColumnasMedioPago() {
		return nombreColumnasMedioPago;
	}

	public JButton getBtnCancelarVenta() {
		return btnCancelarVenta;
	}

	public JButton getBtnFinalizarVenta() {
		return btnFinalizarVenta;
	}
	public JTextField getTextCantidad() {
		return textCantidad;
	}
	
	public JButton getBtnQuitarMedioPago() {
		return btnQuitarMedioPago;
	}
	
	public JTextField getTextDescuento() {
		return textDescuento;
	}
	
	public JLabel getLblDescuentoDescontado() {
		return lblDescuentoDescontado;
	}
}
