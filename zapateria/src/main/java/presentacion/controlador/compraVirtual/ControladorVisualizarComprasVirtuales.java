package presentacion.controlador.compraVirtual;

import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;

import dto.ClienteDTO;
import dto.FacturaDTO;
import dto.IngresosDTO;
import dto.RechazoCompraVirtualDTO;
import modelo.Cliente;
import modelo.DetalleFactura;
import modelo.Factura;
import modelo.Ingresos;
import modelo.compraVirtual.RechazoCompraVirtual;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.compraVirtual.VentanaVerComprasVirtuales;

public class ControladorVisualizarComprasVirtuales {
	
	private final static String  stringQueIndicaQueUnDatoEstaVacio = "[dato inexistente]"; 
	
	VentanaVerComprasVirtuales ventanaPrincipal;
	
	Factura modeloFactura;
	DetalleFactura modeloDetalleFactura;
	Ingresos modeloIngresos;
	Cliente modeloCliente;
	
	RechazoCompraVirtual modeloRechazoVirtual;
	List<IngresosDTO> ingresosEnLista;
	List<RechazoCompraVirtualDTO> rechazosEnLista;
	
	public ControladorVisualizarComprasVirtuales() {
		modeloRechazoVirtual = new RechazoCompraVirtual(new DAOSQLFactory());
		modeloFactura = new Factura(new DAOSQLFactory());
		modeloDetalleFactura = new DetalleFactura(new DAOSQLFactory());
		modeloIngresos = new Ingresos(new DAOSQLFactory());
		modeloCliente = new Cliente(new DAOSQLFactory());
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		ventanaPrincipal = new VentanaVerComprasVirtuales();
	}
	
	public void inicializar() {
		refrescarTabla();
		ventanaPrincipal.mostrarVentana();
	}
	
	public void refrescarTabla() {
		vaciarTablaPrincipal();
		ingresosEnLista = recuperarComprasVirtuales();
		llenarTablaConIngresos(ingresosEnLista);
		
		rechazosEnLista = recuperarRechazos();
		llenarTablaConRechazos(rechazosEnLista);
	}

	private void vaciarTablaPrincipal() {
		ventanaPrincipal.getModelDeTablaPrincipal().setRowCount(0);
		ventanaPrincipal.getModelDeTablaPrincipal().setColumnCount(0);
		ventanaPrincipal.getModelDeTablaPrincipal().setColumnIdentifiers(ventanaPrincipal.getNombreColumnas());
		/*
		ventanaPrincipal.getTablaFabricacionesEnMarcha().getColumnModel().getColumn(0).setPreferredWidth(5);
		ventanaPrincipal.getTablaFabricacionesEnMarcha().getColumnModel().getColumn(1).setPreferredWidth(5);
		ventanaPrincipal.getTablaFabricacionesEnMarcha().getColumnModel().getColumn(3).setPreferredWidth(6);
		*/
	}
	
	private List<IngresosDTO> recuperarComprasVirtuales(){
		List<IngresosDTO> ret  = new ArrayList<IngresosDTO>();
		List<IngresosDTO> todasLosIngresos = this.modeloIngresos.readAll();
		for(IngresosDTO ingreso: todasLosIngresos) {
			if(ingreso.getMedioPago().equals("PV")) {
				ret.add(ingreso);
			}
		}
		return ret;
	}
	
	private void llenarTablaConIngresos(List<IngresosDTO> ingresos) {
		for(IngresosDTO i: ingresos) {
			String idIngreso = i.getId()+"";
			String idSucursal = i.getIdSucursal()+"";
			
			ClienteDTO cliente = getCliente(i.getIdCliente());
			
			String cuil = cliente.getCUIL();
			String nombre = cliente.getApellido()+", "+cliente.getNombre();
			
			String fecha = i.getFecha();
			String hora = i.getHora();
			
			Object[] agregar = {idIngreso, idSucursal, cuil, nombre, fecha, hora};
			ventanaPrincipal.getModelDeTablaPrincipal().addRow(agregar);
		}
	}

	private ClienteDTO getCliente(int idCliente) {
		for(ClienteDTO c: modeloCliente.readAll()) {
			if(c.getIdCliente() == idCliente) {
				return c;
			}
		}
		return null;
	}
	
	private void llenarTablaConRechazos(List<RechazoCompraVirtualDTO> rechazos) {
		// TODO Auto-generated method stub
		//stringQueIndicaQueUnDatoEstaVacio
		for(RechazoCompraVirtualDTO re: rechazos) {
			String idRechazo = re.getId()+"";
			String idSucursal = re.getIdSucursal()+"";
			
			String cuil = "";
			if(re.getCUIL().equals("")) {
				cuil = stringQueIndicaQueUnDatoEstaVacio;
			}else {
				cuil = re.getIdSucursal()+"";
			}
			
			String nombre = "";
			if(re.getNombre().equals("")) {
				nombre = stringQueIndicaQueUnDatoEstaVacio;
			}else {
				nombre = re.getIdSucursal()+"";
			}
			String fecha = re.getFecha();
			String hora = re.getHora();
			Object[] agregar = {idRechazo, idSucursal, cuil, nombre, fecha, hora};
			ventanaPrincipal.getModelDeTablaPrincipal().addRow(agregar);
		}
		
	}
	
	private List<RechazoCompraVirtualDTO> recuperarRechazos(){
		List<RechazoCompraVirtualDTO> todosRechazos = modeloRechazoVirtual.readAllRechazosComprasVirtuales();
		return todosRechazos;
	}
	
	public static void main(String[] args) {
		ControladorVisualizarComprasVirtuales co = new ControladorVisualizarComprasVirtuales();
		co.inicializar();
	}

}
