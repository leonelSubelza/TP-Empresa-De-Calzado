package persistencia.dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.StockDAO;
import dto.StockDTO;

public class StockDAOSQL implements StockDAO {
	private static final String readall = "SELECT * FROM stock";



	public List<StockDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<StockDTO> stock = new ArrayList<StockDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				stock.add(getStockDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stock;
	}

	private StockDTO getStockDTO(ResultSet resultSet) throws SQLException {
		int idStock = resultSet.getInt("IdStock");
		int idSucursal = resultSet.getInt("IdSucursal");
		int idProducto = resultSet.getInt("IdProducto");
		String codigoLote = resultSet.getString("CodigoLote");
		int stockDisponible = resultSet.getInt("StockDisponible");
		
		return new StockDTO(idStock,idSucursal,idProducto,codigoLote,stockDisponible);
	}
}
