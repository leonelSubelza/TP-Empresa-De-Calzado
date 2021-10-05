package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.MaestroProductoDAO;

import dto.MaestroProductoDTO;

public class MaestroProductoDAOSQL implements MaestroProductoDAO {
	private static final String readall = "SELECT * FROM maestroProductos";
	
	public List<MaestroProductoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<MaestroProductoDTO> maestroProducto = new ArrayList<MaestroProductoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				maestroProducto.add(getMaestroProductoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maestroProducto;
	}

	private MaestroProductoDTO getMaestroProductoDTO(ResultSet resultSet) throws SQLException {
		int idMaestroProducto = resultSet.getInt("IdMaestroProducto");
		String descripcion = resultSet.getString("Descripcion");
		String tipo = resultSet.getString("Tipo");
		String fabricado = resultSet.getString("Fabricado");
		int precioCosto = resultSet.getInt("PrecioCosto");
		int precioVenta = resultSet.getInt("PrecioVenta");
		int puntoRepositorio = resultSet.getInt("PuntoRepositorio");
		int idProveedor = resultSet.getInt("IdProveedor");
		String talle = resultSet.getString("Talle");
		int unidadMedida = resultSet.getInt("UnidadMedida");
		String estado = resultSet.getString("Estado");
		
		int CantidadAReponer = resultSet.getInt("CantidadAReponer");
		int DiasParaReponer = resultSet.getInt("DiasParaReponer");
		return new MaestroProductoDTO(idMaestroProducto, descripcion, tipo,fabricado,precioCosto,precioVenta,puntoRepositorio,idProveedor,talle,unidadMedida,estado, CantidadAReponer, DiasParaReponer);
	}

	@Override
	public List<MaestroProductoDTO> getMaestroProductoAproximado(String nombreColumna1, String txtAprox1, String nombreColumna2, String txtAprox2, String nombreColumna3, String txtAprox3) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		String sel =  "SELECT * FROM maestroProductos WHERE "+nombreColumna1+" like '%"+txtAprox1+"%'";
		
		if(nombreColumna2!=null && txtAprox2!=null) {
			sel = sel + "AND "+nombreColumna2+" LIKE '%"+txtAprox2+"%'";
		}
		if(nombreColumna3!=null && txtAprox3!=null) {
			sel = sel + "AND "+nombreColumna3+" LIKE '%"+txtAprox3+"%'";
		}
		
		ArrayList<MaestroProductoDTO> maestroProducto = new ArrayList<MaestroProductoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(sel);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				maestroProducto.add(getMaestroProductoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maestroProducto;
	}
}

